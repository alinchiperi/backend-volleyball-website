package ro.usv.ip.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Long id) {
        super("Game " + id + " not found");
    }
}
