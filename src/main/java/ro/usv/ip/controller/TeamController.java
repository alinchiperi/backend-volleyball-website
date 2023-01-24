package ro.usv.ip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ro.usv.ip.dto.TeamDto;
import ro.usv.ip.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/teams")
    private ResponseEntity<List<TeamDto>> getAllTeams(){
        return ResponseEntity.ok().body(teamService.getAllTeams());
    }

    @PostMapping(value = "/create", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    private ResponseEntity<TeamDto> createTeam(@RequestPart("team") TeamDto teamDto, @RequestPart("imagefile") MultipartFile file ){
        return ResponseEntity.ok().body(teamService.createTeam(teamDto, file));
    }
    @PutMapping("/update")
    private ResponseEntity<TeamDto> update(@RequestPart("team") TeamDto teamDto, @RequestPart(value= "imagefile", required = false) MultipartFile file ){
        return ResponseEntity.ok().body(teamService.updateTeam(teamDto, file));
    }
    @DeleteMapping("{id}/delete")
    private ResponseEntity<TeamDto> delete(@PathVariable Long id){
        return ResponseEntity.ok().body(teamService.delete(id));
    }
}
