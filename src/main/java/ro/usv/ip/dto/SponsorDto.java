package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;
import ro.usv.ip.model.Sponsor;

@Builder
@Data
public class SponsorDto {
    private Long id;
    private String name;
    private String siteLink;
    private byte[] logo;

    public static SponsorDto from(Sponsor sponsor) {
        return SponsorDto.builder()
                .id(sponsor.getId())
                .name(sponsor.getName())
                .siteLink(sponsor.getSiteLink())
                .logo(sponsor.getLogo())
                .build();
    }
}
