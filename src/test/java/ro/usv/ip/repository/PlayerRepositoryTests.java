package ro.usv.ip.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ro.usv.ip.model.Player;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PlayerRepositoryTests {

    @Autowired
    private PlayerRepository repositoryTest;

    @AfterEach
    void cleanUp() {
        repositoryTest.deleteAll();
    }
    @Test
    void itShouldReturnNullWhenIdNotExists(){
        Player player= Player.builder()
                .firstName("firstName")
                .lastName("lastName")
                .build();
        assertThat(player.getId()).isNull();
    }

    @Test
    void itShouldRetrievePlayerById(){
        Player player= Player.builder()
                .firstName("firstName")
                .lastName("lastName")
                .build();
        player =repositoryTest.save(player);

        assertThat(player.getId()).isNotNull();
    }
}
