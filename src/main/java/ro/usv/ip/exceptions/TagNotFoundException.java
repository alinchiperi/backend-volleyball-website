package ro.usv.ip.exceptions;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(Long id) {
        super("Tag not found for provided id: " + id);
    }

}
