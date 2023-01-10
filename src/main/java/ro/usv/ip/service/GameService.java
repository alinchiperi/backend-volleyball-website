package ro.usv.ip.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.usv.ip.dto.GameDto;
import ro.usv.ip.dto.GameDetailsDto;
import ro.usv.ip.exceptions.GameNotFoundException;
import ro.usv.ip.exceptions.TeamNotFoundException;
import ro.usv.ip.model.Game;
import ro.usv.ip.model.Team;
import ro.usv.ip.repository.GameRepository;
import ro.usv.ip.repository.TeamRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    public GameDto createNewGame(GameDto gameDto) {
        Game newGame = new Game();
        return createNewGame(gameDto, newGame);
    }


    public GameDetailsDto getGameDetails(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        Team homeTeam = teamRepository.findById(game.getHomeTeamId()).orElseThrow(() -> new TeamNotFoundException(game.getHomeTeamId()));
        Team awayTeam = teamRepository.findById(game.getAwayTeamId()).orElseThrow(() -> new TeamNotFoundException(game.getAwayTeamId()));

        GameDetailsDto gameDetails = new GameDetailsDto();
        gameDetails.setAwayTeam(awayTeam);
        gameDetails.setHomeTeam(homeTeam);
        gameDetails.setLocation(game.getLocation());
        gameDetails.setDate(game.getDate());

        return gameDetails;
    }

    public GameDto deleteGame(Long id) {
        Game gameToDelete = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        gameRepository.delete(gameToDelete);
        return GameDto.from(gameToDelete);
    }

    public GameDto updateGame(GameDto gameDto) {
        Game newGame = gameRepository.findById(gameDto.getId()).orElseThrow(() -> new GameNotFoundException(gameDto.getId()));

        return createNewGame(gameDto, newGame);
    }

    private GameDto createNewGame(GameDto gameDto, Game newGame) {
        Team homeTeam = teamRepository.findById(gameDto.getHomeTeamId()).orElseThrow(() -> new TeamNotFoundException(gameDto.getHomeTeamId()));
        Team awayTeam = teamRepository.findById(gameDto.getAwayTeamId()).orElseThrow(() -> new TeamNotFoundException(gameDto.getAwayTeamId()));

        newGame.setHomeTeamId(homeTeam.getId());
        newGame.setAwayTeamId(awayTeam.getId());
        newGame.setDate(gameDto.getDate());
        newGame.setLocation(gameDto.getLocation());

        newGame = gameRepository.save(newGame);
        return GameDto.from(newGame);
    }
}