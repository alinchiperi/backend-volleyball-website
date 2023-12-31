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
import ro.usv.ip.dto.PlayerDetailsDto;
import ro.usv.ip.dto.PlayerDto;
import ro.usv.ip.dto.PlayerStatisticDto;
import ro.usv.ip.service.PlayerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService playerService;

    /**
     * Add a player
     * @param playerDto Player body
     * @param file Profile Image
     * @return Player added
     */
    @PostMapping(value = "/create", consumes = {
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

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<PlayerDto> deletePlayer(@PathVariable Long id) {
        return ResponseEntity.ok().body(playerService.deletePlayer(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok().body(playerService.findPlayerById(id));
    }

    @PutMapping("/update/body")
    public PlayerDto updatePlayerBody(@RequestBody PlayerDto playerDto) {
        return playerService.updatePlayerBody(playerDto);
    }


    @PutMapping(value="/update", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public PlayerDto updatePlayer(@RequestPart("player") PlayerDto playerDto, @RequestParam(value= "imagefile", required = false)  MultipartFile file) {
        return playerService.updatePlayer(playerDto, file);
    }

    @PutMapping("/{id}/update/image")
    public void updatePlayerPicture(@PathVariable("id") Long id, @RequestParam("imagefile") MultipartFile file) {
        playerService.updateProfileImage(id, file);
    }

    /**
     * Return just profile image for a player
     * @param id id for player
     * @return image as byte array
     */
    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getPlayerImage(@PathVariable Long id) {
        return playerService.getPlayerImage(id);
    }

    /**
     * Method to return all player for a specific category
     * @param category players category
     * @return A list with players
     */
    @GetMapping("/category")
    public List<PlayerDto> getPlayersByCategory(@RequestParam String category) {
        return playerService.getPlayerByCategory(category);
    }

    /**
     * Post player statistic for a specific player
     * @param playerStatisticDto player statistic body
     */
    @PostMapping("/statistic")
    public void postPlayerStatistic(@RequestBody PlayerStatisticDto playerStatisticDto) {
        playerService.addPlayerStatistic(playerStatisticDto);
    }

    @GetMapping("/statistic/{id}")
    public ResponseEntity<PlayerStatisticDto> getPlayerStatistic(@PathVariable Long id) {
        return ResponseEntity.ok().body(playerService.getPlayerStatistic(id));
    }

    /**
     * Get all information about a player
     * @param id  player id
     * @return ro.usv.ip.dto.PlayerDetailsDto
     */
    @GetMapping("/{id}/details")
    public ResponseEntity<PlayerDetailsDto> getAllPlayerData(@PathVariable Long id) {
        return ResponseEntity.ok().body(playerService.getAllPlayerData(id));
    }
}
