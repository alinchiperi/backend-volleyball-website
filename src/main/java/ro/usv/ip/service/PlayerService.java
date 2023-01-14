package ro.usv.ip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.usv.ip.dto.PlayerDetailsDto;
import ro.usv.ip.dto.PlayerDto;
import ro.usv.ip.dto.PlayerStatisticDto;
import ro.usv.ip.exceptions.PlayerNotFoundException;
import ro.usv.ip.model.Player;
import ro.usv.ip.model.PlayerStatistic;
import ro.usv.ip.repository.PlayerRepository;
import ro.usv.ip.repository.PlayerStatisticRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    public final PlayerRepository playerRepository;
    public final PlayerStatisticRepository playerStatisticRepository;

    public PlayerDto addPlayer(PlayerDto playerDto, MultipartFile file) {
        Player player = new Player();

        playerForSave(playerDto, player);

        //This is for profile picture.
        try {
            byte[] byteObjects = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            player.setPhoto(byteObjects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        player = playerRepository.save(player);

        /**This is for save image locally if blob isn't accepted */

        /*String playerImageName = String.valueOf(player.getId()).concat("-").concat(player.getFirstName());

        String fileName = playerImageName.concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename()));


        String directoryPath = makeDirectory();
        Path fileNamePath = Paths.get(directoryPath, fileName);

        try {
            Files.write(fileNamePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        return PlayerDto.from(player);

    }

    private void playerForSave(PlayerDto playerDto, Player player) {
        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());
        player.setShirtNumber(playerDto.getShirtNumber());
        player.setCategory(playerDto.getCategory());
        player.setDescription(playerDto.getDescription());
        player.setDob(playerDto.getDob());
        player.setHeight(playerDto.getHeight());
        player.setDescription(playerDto.getDescription());
        player.setNationality(playerDto.getNationality());
        player.setPosition(playerDto.getPosition());
        player.setTitle(playerDto.getTitle());
        player.setWeight(playerDto.getWeight());
    }

    @Transactional
    public void updateProfileImage(Long id, MultipartFile file) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
        try {
            byte[] byteOnjects = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteOnjects[i++] = b;
            }
            player.setPhoto(byteOnjects);
            playerRepository.save(player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
        playerRepository.delete(player);
        return PlayerDto.from(player);
    }

    public PlayerDto updatePlayerBody(PlayerDto playerDto) {
        Player player = playerRepository.findById(playerDto.getId()).orElseThrow(() -> new PlayerNotFoundException(playerDto.getId()));

        player.setId(player.getId());
        playerForSave(playerDto, player);


        player = playerRepository.save(player);
        return PlayerDto.from(player);
    }

    public PlayerDto updatePlayer(PlayerDto playerDto, MultipartFile file){
        Player player = playerRepository.findById(playerDto.getId()).orElseThrow(() -> new PlayerNotFoundException(playerDto.getId()));
        playerForSave(playerDto, player);
        //This is for profile picture.
        try {
            byte[] byteObjects = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            player.setPhoto(byteObjects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        player= playerRepository.save(player);
        return PlayerDto.from(player);
    }
    public PlayerDto findPlayerById(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
        return PlayerDto.from(player);
    }

    public byte[] getPlayerImage(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
        return player.getPhoto();
    }

//    public String makeDirectory() {
//        Path currentPath = Paths.get(".");
//        Path absolutePath = currentPath.toAbsolutePath();
//        String imagePath = absolutePath + "/src/main/resources/images/players/";
//        File directory = new File(imagePath);
//        if (!directory.exists()) {
//            directory.mkdir();
//        }
//
//        return imagePath;
//    }

    public List<PlayerDto> getPlayerByCategory(String category) {
        List<Player> playersByCategory = playerRepository.getPlayerByCategory(category);
        List<PlayerDto> playerDtos = new ArrayList<>();
        for (Player pl :
                playersByCategory) {
            playerDtos.add(PlayerDto.from(pl));
        }
        return playerDtos;
    }

    public void addPlayerStatistic(PlayerStatisticDto playerStatisticDto) {

        Long playerId = playerStatisticDto.getPlayerId();
        PlayerStatistic playerStatistic = new PlayerStatistic();
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));

        playerStatistic.setAces(playerStatisticDto.getAces());
        playerStatistic.setAttacks(playerStatisticDto.getAttacks());
        playerStatistic.setBlocks(playerStatisticDto.getBlocks());
        playerStatistic.setPlayer(player);
        playerStatistic.setSeasonStart(playerStatisticDto.getSeasonStart());
        playerStatistic.setSeasonStop(playerStatisticDto.getSeasonStop());

        playerStatisticRepository.save(playerStatistic);

    }

    public PlayerStatisticDto getPlayerStatistic(Long playerId) {
        List<PlayerStatistic> playerStatistics = playerStatisticRepository.findByPlayerIdOrderBySeasonStartDesc(playerId);

        PlayerStatistic playerStatistic = playerStatistics.get(0);
        return PlayerStatisticDto.from(playerStatistic);
    }

    public PlayerDetailsDto getAllPlayerData(Long playerId){
        Player player = playerRepository.findById(playerId).orElseThrow(()->new PlayerNotFoundException(playerId));
        List<PlayerStatistic> playerStatistics = playerStatisticRepository.findByPlayerIdOrderBySeasonStartDesc(playerId);

        PlayerStatistic playerStatistic = playerStatistics.get(0);
        if(playerStatistic == null){
            throw new RuntimeException("This player have not statistics");
        }else {

            return PlayerDetailsDto.from(player, playerStatistic);

        }
    }
}
