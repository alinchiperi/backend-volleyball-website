package ro.usv.ip.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;

    @Override
    public String toString() {
        return "{\"token\" : \"" + token + "\"}";
    }
}
