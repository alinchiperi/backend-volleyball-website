package ro.usv.ip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.usv.ip.dto.SponsorDto;
import ro.usv.ip.exceptions.SponsorNotFoundException;
import ro.usv.ip.model.Sponsor;
import ro.usv.ip.repository.SponsorRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SponsorService {
    private final SponsorRepository sponsorRepository;

    public SponsorDto addSponsor(SponsorDto sponsorDto, MultipartFile file) {
        Sponsor sponsor = new Sponsor();
        sponsor.setName(sponsorDto.getName());
        sponsor.setSiteLink(sponsorDto.getSiteLink());
        sponsorDto.setName(sponsorDto.getName());
        try {
            byte[] byteObjects = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            sponsor.setLogo(byteObjects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sponsorRepository.save(sponsor);
        return SponsorDto.from(sponsor);
    }

    public SponsorDto deleteSponsor(Long id) {
        Sponsor sponsor = sponsorRepository.findById(id).orElseThrow(() -> new SponsorNotFoundException(id));
        sponsorRepository.delete(sponsor);

        return SponsorDto.from(sponsor);
    }

    public List<SponsorDto> getSponsors() {
        List<Sponsor> sponsors = sponsorRepository.findAll();
        List<SponsorDto> sponsorDtos = new ArrayList<>();
        for (Sponsor sp :
                sponsors) {
            sponsorDtos.add(SponsorDto.from(sp));
        }
        return sponsorDtos;
    }

    public SponsorDto getSponsorById(Long id) {
        Sponsor sponsor = sponsorRepository.findById(id).orElseThrow(()->new SponsorNotFoundException(id));
        return SponsorDto.from(sponsor);
    }

    public byte[] getSponsorLogo(Long id) {
        Sponsor sponsor = sponsorRepository.findById(id).orElseThrow(()->new SponsorNotFoundException(id));
        return sponsor.getLogo();
    }
}
