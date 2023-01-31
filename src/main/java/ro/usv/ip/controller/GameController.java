package ro.usv.ip.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.usv.ip.dto.GameDto;
import ro.usv.ip.dto.GameDetailsDto;
import ro.usv.ip.service.GameService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final static Logger logger = LoggerFactory.getLogger(GameController.class);

    @PostMapping("/game/create")
    public ResponseEntity<GameDto> addNewGame(@RequestBody  GameDto game){
        return ResponseEntity.ok().body(gameService.createNewGame(game));
    }

    /**
     * Get all details about a game
     * @param id id for a game
     * @return GameDetailsDto
     */
    @GetMapping("/game/{id}/details")
    public ResponseEntity<GameDetailsDto> getGameDetails(@PathVariable Long id){
        return ResponseEntity.ok().body(gameService.getGameDetails(id));
    }
    @DeleteMapping("/game/{id}/delete")
    public ResponseEntity<GameDto> deleteGame(@PathVariable Long id){
        return ResponseEntity.ok().body(gameService.deleteGame(id));
    }

    @PutMapping("/game/update")
    public ResponseEntity<GameDto> updateGame(@RequestBody  GameDto game){
        return ResponseEntity.ok().body(gameService.updateGame(game));
    }

    @GetMapping("/game/games")
    public ResponseEntity<List<GameDetailsDto>> getAllGames(){
        return ResponseEntity.ok().body(gameService.getAllGames());
    }

}
