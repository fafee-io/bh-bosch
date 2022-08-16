package hu.bosch.bomple.common;

import hu.bosch.bomple.api.model.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public enum ErrorCode {
    BAD_CREDENTIALS(1,"bad.credentials", "Hibás felhasználónév vagy jelszó!"),
    NOT_IMPLEMENTED(2,"better.luck.next.time", "Ezt még fejlesztjük, mi kérjük az elnézést!"),
    USER_EMAIL_EXIST(3,"user.email.exists", "A megadott email cím már létezik!"),
    USER_NAME_EXIST(4,"user.name.exists", "A megadott felhasználónév már létezik!"),
    USER_NOT_ACTIVE(5,"user.not.active", "Felhasználó nem aktív!"),
    USER_NOT_FOUND(6,"user.not.found", "Felhasználó nem található!"),;

    private final int code;
    private final String token;
    private final String defaultMessage;

    public ErrorMessage toErrorMessage() {
        ErrorMessage msg = new ErrorMessage();
        msg.setCode(this.code);
        msg.setToken(this.token);
        msg.defaultMessage(this.defaultMessage);

        return msg;
    }

}
