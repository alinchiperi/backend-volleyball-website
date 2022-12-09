package ro.usv.ip.exceptions;

public class SponsorNotFoundException extends RuntimeException {
    public SponsorNotFoundException(Long id) {
        super("Sponsor with id:" + id + " not found");
    }
}
