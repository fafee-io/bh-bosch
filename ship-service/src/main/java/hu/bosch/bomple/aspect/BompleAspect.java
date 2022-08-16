package hu.bosch.bomple.aspect;

import hu.bosch.bomple.ship.model.ShipEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class BompleAspect {

    private final Clock clock;

    @Pointcut("within(hu.bosch.bomple.ship.model.ShipRepository)")
    private void shipRepository() {}

    @Pointcut("execution(* *..find*(Long,..))")
    public void repositoryFinds() {}

    @Before("repositoryFinds()")
    public void logSearchedId(JoinPoint jp) {
        log.info("Repository method called: " + jp.getSignature().getName());
        log.info(String.valueOf(jp.getArgs()[0]));
    }

    @AfterReturning(value = "shipRepository()", returning = "entity")
    public void entityReturned(JoinPoint jp, Object entity) {
        if (ShipEntity.class.isAssignableFrom(entity.getClass())) {
            log.info("A visszaadott objektum egy űrhajó!");
        } else {
            log.info("Nagy a baj!");
        }
    }

    @Around("@annotation(Timed)")
    public Object methodExecutionTiming(ProceedingJoinPoint pjp) throws Throwable {
        Instant begin = Instant.now(clock);

        Object proceed = pjp.proceed();

        Duration executionTime = Duration.between(begin, Instant.now(clock));

        log.info("Execution of ".concat(pjp.getSignature().toString()).concat(" completed in ").concat(executionTime.toString()));

        return proceed;
    }
}
