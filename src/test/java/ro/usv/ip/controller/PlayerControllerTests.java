package ro.usv.ip.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ro.usv.ip.dto.PlayerDto;
import ro.usv.ip.model.Player;
import ro.usv.ip.service.PlayerService;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PlayerService playerService;


    @Test
    void itShouldRetrievePlayer() throws Exception {
        Player player = getPlayer();
        Long playerId = player.getId();

        given(playerService.findPlayerById(playerId)).willReturn(PlayerDto.from(player));

        mockMvc.perform(get("/api/player/" + playerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(playerId))
                .andExpect(jsonPath("firstName").value("Alin"))
                .andExpect(jsonPath("lastName").value("Chiperi"))
                .andExpect(jsonPath("nationality").value("romana"))
                .andExpect(jsonPath("description").value("Alin e cel mai tare"))
                .andExpect(jsonPath("shirtNumber").value("10"))
                .andExpect(jsonPath("category").value("Senior"));
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
