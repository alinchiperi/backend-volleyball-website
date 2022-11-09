package ro.usv.ip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.usv.ip.dto.PlayerDto;
import ro.usv.ip.service.PlayerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping("")
    public PlayerDto addPlayer(@RequestBody PlayerDto playerDto) {
        return playerService.addPlayer(playerDto);
    }

    @GetMapping("/players")
    public List<PlayerDto> getPlayers() {
        return playerService.getPlayers();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PlayerDto> deletePlayer(@PathVariable Long id) {

        return ResponseEntity.ok().body(playerService.deletePlayer(id));
    }

    @PutMapping("")
    public PlayerDto updatePlayer(@RequestBody PlayerDto playerDto){
        return playerService.updatePlayer(playerDto);
    }
}
