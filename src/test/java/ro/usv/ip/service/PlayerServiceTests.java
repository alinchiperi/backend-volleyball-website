package ro.usv.ip.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import ro.usv.ip.dto.PlayerDto;
import ro.usv.ip.exceptions.PlayerNotFoundException;
import ro.usv.ip.model.Player;
import ro.usv.ip.repository.PlayerRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTests {

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    PlayerService playerService;

    @Test
    void returnExistingPlayerDto_withValidId() {
        Player player = getPlayer();

        given(playerRepository.findById(1L)).willReturn(Optional.of(player));

        PlayerDto result = playerService.findPlayerById(player.getId());

        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("Alin");
        assertThat(result.getLastName()).isEqualTo("Chiperi");
        assertThat(result.getShirtNumber()).isEqualTo(10);
        assertThat(result.getDob()).isEqualTo(LocalDate.parse("2000-06-20"));
        assertThat(result.getCategory()).isEqualTo("Senior");
        assertThat(result.getNationality()).isEqualTo("romana");
        assertThat(result.getDescription()).isEqualTo("Alin e cel mai tare");
    }

    @Test
    void itShouldThrowException_PlayerWithInvalidId() {
        Player player = getPlayer();

        given(playerRepository.findById(any())).willReturn(Optional.empty());

        assertThatThrownBy(() -> playerService.findPlayerById(player.getId()))
                .isInstanceOf(PlayerNotFoundException.class)
                .hasMessage("Player with id " + player.getId() + " not found");
    }

    @Test
    void playerProfileImageShouldNotBeNull() {
        Player player = getPlayer();
        given(playerRepository.findById(1L)).willReturn(Optional.of(player));
        MockMultipartFile mockMultipartFile = new MockMultipartFile("images", "image1", "image/png", "Test".getBytes());
        playerService.updateProfileImage(1L, mockMultipartFile);

        assertThat(player.getPhoto()).isNotNull();
    }

    private Player getPlayer() {
        return Player.builder()
                .id(1l)
                .firstName("Alin")
                .lastName("Chiperi")
                .dob(LocalDate.parse("2000-06-20"))
                .shirtNumber(10)
                .category("Senior")
                .nationality("romana")
                .description("Alin e cel mai tare")
                .build();
    }
}
