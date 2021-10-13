package yoho.vargas.employeemanager.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String s) {
        super(s);
    }
}
