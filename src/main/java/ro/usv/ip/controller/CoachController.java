package ro.usv.ip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ro.usv.ip.dto.CoachDto;
import ro.usv.ip.service.CoachService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/coach")
public class CoachController {

    private final CoachService coachService;

    @PostMapping(value = "/create", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public CoachDto addCoach(@RequestPart("coach") CoachDto coachDto, @RequestParam("imagefile") MultipartFile file) {
        return coachService.addCoach(coachDto, file);
    }

    @GetMapping("/coaches")
    public List<CoachDto> getCoaches() {
        return coachService.getCoaches();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<CoachDto> deleteCoach(@PathVariable Long id) {

        return ResponseEntity.ok().body(coachService.deleteCoach(id));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CoachDto> getCoach(@PathVariable Long id) {

        return ResponseEntity.ok().body(coachService.getCoach(id));
    }

    @PutMapping("/update")
    public CoachDto updateCoach(@RequestBody CoachDto coachDto) {
        return coachService.updateCoach(coachDto);
    }

    @PutMapping("{id}/update/image")
    public void updateCoachImage(@PathVariable("id") Long id, @RequestParam("imagefile") MultipartFile file) {
        coachService.updateProfileImage(id, file);
    }

    @GetMapping(
            value = "/{id}/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getCoachImage(@PathVariable Long id) throws IOException {
        return coachService.getCoachImage(id);
    }
}
