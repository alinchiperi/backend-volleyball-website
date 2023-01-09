package ro.usv.ip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
