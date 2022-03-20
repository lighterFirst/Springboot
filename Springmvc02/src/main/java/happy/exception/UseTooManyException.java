package happy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户数量太多")
public class UseTooManyException extends RuntimeException{

    public UseTooManyException() {
    }

    public UseTooManyException(String message) {
        super(message);
    }
}
