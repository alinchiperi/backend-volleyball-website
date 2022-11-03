package ro.usv.ip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.usv.ip.dto.PlayerDto;
import ro.usv.ip.exceptions.PlayerNotFoundException;
import ro.usv.ip.model.Player;
import ro.usv.ip.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    public final PlayerRepository playerRepository;

    public PlayerDto addPlayer(PlayerDto playerDto) {
        Player player = new Player();

        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());
        player.setShirtNumber(playerDto.getShirtNumber());
        player.setCategory(playerDto.getCategory());
        player.setDescription(player.getDescription());
        player.setDob(playerDto.getDob());
        player.setHeight(playerDto.getHeight());
        player.setDescription(playerDto.getDescription());
        player.setNationality(playerDto.getNationality());

        player = playerRepository.save(player);

        return PlayerDto.from(player);

    }

    public List<PlayerDto> getPlayers() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDto> playerDtos = new ArrayList<>();
        for (Player pl :
                players) {
            playerDtos.add(PlayerDto.from(pl));
        }

        return playerDtos;
    }

    public PlayerDto deletePlayer(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        playerRepository.delete(player.get());
        return player.map(PlayerDto::from).orElse(null);
    }

    public PlayerDto updatePlayer(PlayerDto playerDto) {
        Player player = playerRepository.findById(playerDto.getId()).orElseThrow(() -> new PlayerNotFoundException(playerDto.getId()));

        player.setId(player.getId());
        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());
        player.setShirtNumber(playerDto.getShirtNumber());
        player.setCategory(playerDto.getCategory());
        player.setDescription(player.getDescription());
        player.setDob(playerDto.getDob());
        player.setHeight(playerDto.getHeight());
        player.setDescription(playerDto.getDescription());
        player.setNationality(playerDto.getNationality());

        player = playerRepository.save(player);

        return PlayerDto.from(player);
    }
}
