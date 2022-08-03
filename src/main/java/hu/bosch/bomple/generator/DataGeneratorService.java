package hu.bosch.bomple.generator;

import hu.bosch.bomple.crew.enums.Division;
import hu.bosch.bomple.crew.enums.Rank;
import hu.bosch.bomple.crew.model.AssignmentEntity;
import hu.bosch.bomple.crew.model.AssignmentRepository;
import hu.bosch.bomple.crew.model.CrewEntity;
import hu.bosch.bomple.crew.model.CrewRepository;
import hu.bosch.bomple.ship.enums.ArmamentType;
import hu.bosch.bomple.ship.enums.ShipClass;
import hu.bosch.bomple.ship.model.ArmamentEmbeddable;
import hu.bosch.bomple.ship.model.PositionEmbeddable;
import hu.bosch.bomple.ship.model.ShipEntity;
import hu.bosch.bomple.ship.model.ShipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataGeneratorService {

    private final CrewRepository crewRepository;
    private final AssignmentRepository assignmentRepository;
    private final ShipRepository shipRepository;
    private final SecretService secretService;

    private List<String> firstNamePool = Arrays.asList("Bence", "Zsolt", "Levente", "Csaba", "Szabolcs", "Botond", "Zsombor", "Soma", "Bendegúz", "Csongor", "Árpád", "Csanád", "Hunor", "Koppány", "Álmos", "Kende", "Előd",  "Vajk", "Bulcsú", "Örs",
            "Nikolett", "Adrienn", "Bernadett", "Edina", "Brigitta", "Vivien", "Adél", "Kitti", "Petra", "Klaudia", "Beatrix", "Zsanett", "Kinga", "Henrietta", "Diána", "Szabina", "Laura", "Evelin", "Ivett", "Fruzsina");
    private List<String> lastNamePool = Arrays.asList("Mókus", "Sas", "Sólyom", "Vöcsök", "Cincér", "Pocok", "Poszáta", "Pillangó", "Csüllő", "Daru", "Futrinka", "Béka", "Harkály", "Holló", "Pityer", "Sáska", "Karvaly", "Csiga", "Sirály", "Varjú", "Denevér", "Sármány", "Teknős", "Nyest", "Gödény", "Szender", "Macska", "Kánya", "Vércse", "Gyík", "Varangy");

    private List<String> shipPool = Arrays.asList("Enterprise", "Orville", "Defiant", "Nova", "Galaxy", "Voyager", "North", "Normandy", "Dead Pool", "Tré-Fa", "Micimackó", "Fluxuskondenzátor", "Neked Csak Dezső", "Pálinka", "Dropshipping", "Ételszállító", "Gyurma", "Vaskarika", "Malom", "Tüzifa", "Élelmiszer", "Croissant", "Burger", "Turbófeltöltő", "Katalizátor", "Karburátor");

    private List<String> armaPool = Arrays.asList("Big Bertha", "Lasers", "Phasers", "Firebolt", "Missiles", "Guns", "Photon Torpedoes", "Quantum Volley");

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {
        log.info("Hi, let's see if you have data!");
        long count = crewRepository.count();
        if (count < 999L) {
            log.info("You don't, so let's make some!");
            List<CrewEntity> allCrew = new ArrayList<>();
            for (int i = 0; i < 1000; ++i) {
                CrewEntity crew = new CrewEntity();
                crew.setFirstName(choose(firstNamePool));
                crew.setLastName(choose(lastNamePool));
                crew.setCallSign(secretService.generateLetters(12));
                crew.setDivision(Division.values()[secretService.nextInt(4)]);
                crew.setRank(Rank.values()[secretService.nextInt(7)]);
                allCrew.add(crew);
            }
            allCrew = crewRepository.saveAll(allCrew);
            log.info("Created the crew!");

            List<ShipEntity> ships = new ArrayList<>();
            for (int i = 0; i < 30; ++i) {
                ShipEntity ship = new ShipEntity();
                ship.setName(choose(shipPool).concat(" ").concat(secretService.generateLetters(3).toUpperCase()));
                ship.setDesignation(secretService.generateLetters(3).toUpperCase().concat(" ").concat(secretService.generateSecretNumber(3)));
                ship.setWeight(secretService.nextDouble(100d, 500d));
                ship.setClassification(ShipClass.values()[secretService.nextInt(7)]);

                PositionEmbeddable position = new PositionEmbeddable();
                position.setX(secretService.nextDouble(-10000d, +10000d));
                position.setY(secretService.nextDouble(-10000d, +10000d));
                position.setZ(secretService.nextDouble(-10000d, +10000d));
                ship.setPosition(position);

                int armamentCount = secretService.nextInt(20);
                Set<ArmamentEmbeddable> armaments = new HashSet<>();
                for (int j = 0; j < armamentCount; ++j) {
                    ArmamentEmbeddable armament = new ArmamentEmbeddable();
                    armament.setName(choose(armaPool));
                    armament.setQuantity(secretService.nextInt(12));
                    armament.setYield(secretService.nextDouble(10d, 55d));
                    armament.setType(ArmamentType.values()[secretService.nextInt(4)]);
                    armaments.add(armament);
                }
                ship.setArmament(armaments);

                ship.setActive(true);
                ship.setRetired(false);

                ship.setCaptain(crewRepository.findById((long)secretService.nextInt(999) + 1).get());

                ships.add(ship);
            }
            ships = shipRepository.saveAll(ships);
            log.info("Created the ships!");

            List<AssignmentEntity> assignments = new ArrayList<>();
            for (int i = 0; i < 1500; ++i) {
                AssignmentEntity assignment = new AssignmentEntity();
                assignment.setStart(Instant.now().minus(secretService.nextInt(500), ChronoUnit.DAYS));
                assignment.setShip(shipRepository.findById((long)secretService.nextInt(29) + 1).get());
                assignment.setCrew(crewRepository.findById((long)secretService.nextInt(999) + 1).get());
                if (secretService.nextInt(10) >= 8) {
                    assignment.setEnd(Instant.now());
                }
                assignments.add(assignment);
            }
            assignments = assignmentRepository.saveAll(assignments);
            log.info("Assigned the crew!");

            List<CrewEntity> assignedCrew = crewRepository.findAllFetchAssignments();
            assignedCrew.stream().forEach(ac -> {
                if (ac.getAssignments() != null && ac.getAssignments().size() > 1) {
                    Iterator<AssignmentEntity> iterator = ac.getAssignments().iterator();
                    AssignmentEntity prev = iterator.next();
                    while (iterator.hasNext()) {
                        AssignmentEntity act = iterator.next();
                        prev.setEnd(act.getStart().minus(1, ChronoUnit.DAYS));
                        prev = act;
                    }
                }
            });
            crewRepository.saveAll(assignedCrew);
            log.info("Fixed all assignments!");
        }
    }

    private String choose(List<String> list) {
        return list.get((int) (Math.random() * (list.size())));
    }
}
