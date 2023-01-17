package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDto {

    private String name;
    private String email;
    private String message;
    private boolean checkboxCopy;

}
