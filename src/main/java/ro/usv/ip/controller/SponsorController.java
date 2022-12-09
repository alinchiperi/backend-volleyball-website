package ro.usv.ip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ro.usv.ip.dto.PlayerDto;
import ro.usv.ip.dto.SponsorDto;
import ro.usv.ip.service.SponsorService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/sponsor")
public class SponsorController {
    private final SponsorService sponsorService;

    @PostMapping(value = "/create", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public SponsorDto addSponsor(@RequestPart("sponsor") SponsorDto sponsorDto, @RequestParam("imagefile") MultipartFile file) {
        return sponsorService.addSponsor(sponsorDto, file);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SponsorDto> deleteSponsor(@PathVariable Long id) {
        return ResponseEntity.ok().body(sponsorService.deleteSponsor(id));
    }

    @GetMapping(value="/sponsors")
    List<SponsorDto>getSponsors(){
        return sponsorService.getSponsors();
    }
    @GetMapping("/{id}")
    public ResponseEntity<SponsorDto> getSponsor(@PathVariable Long id) {
        return ResponseEntity.ok().body(sponsorService.getSponsorById(id));
    }
    @GetMapping(
            value = "/{id}/logo",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getSponsorLogo(@PathVariable Long id) throws IOException {
        return sponsorService.getSponsorLogo(id);
    }


}
