package ro.usv.ip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.usv.ip.dto.CoachDto;
import ro.usv.ip.dto.PlayerDto;
import ro.usv.ip.service.CoachService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/coach")
public class CoachController {

    private final CoachService coachService;

    @PostMapping
    public CoachDto addCoach(@RequestBody CoachDto coachDto) {
        return coachService.addCoach(coachDto);
    }

    @GetMapping("")
    public List<CoachDto> getPlayers() {
        return coachService.getPlayers();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CoachDto> deleteCoach(@PathVariable Long id) {

        return ResponseEntity.ok().body(coachService.deleteCoach(id));
    }

    @PutMapping("")
    public CoachDto updatePlayer(@RequestBody CoachDto coachDto){
        return coachService.updatePlayer(coachDto);
    }
}
