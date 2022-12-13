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
import ro.usv.ip.dto.PlayerDto;
import ro.usv.ip.service.PlayerService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping(value = "", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public PlayerDto addPlayer(@RequestPart("player") PlayerDto playerDto, @RequestParam("imagefile") MultipartFile file) {
        return playerService.addPlayer(playerDto, file);
    }

    @GetMapping("/players")
    public List<PlayerDto> getPlayers() {
        return playerService.getPlayers();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PlayerDto> deletePlayer(@PathVariable Long id) {
        return ResponseEntity.ok().body(playerService.deletePlayer(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok().body(playerService.findPlayerById(id));
    }

    @PutMapping("")
    public PlayerDto updatePlayer(@RequestBody PlayerDto playerDto) {
        return playerService.updatePlayer(playerDto);
    }

    @PostMapping("/{id}/update/image")
    public void updatePlayerPicture(@PathVariable("id") Long id, @RequestParam("imagefile") MultipartFile file) {
        playerService.updateProfileImage(id, file);
    }

    @GetMapping(
            value = "/{id}/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getPlayerImage(@PathVariable Long id) throws IOException {
        return playerService.getPlayerImage(id);
    }
}
