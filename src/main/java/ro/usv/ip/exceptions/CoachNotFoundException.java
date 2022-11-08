package ro.usv.ip.exceptions;

public class CoachNotFoundException extends RuntimeException{
    public CoachNotFoundException(Long id) {
        super("Coach with id "+ id +" not found");
    }
}
