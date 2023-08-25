package ro.usv.ip.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ro.usv.ip.dto.GameDetailsDto;
import ro.usv.ip.dto.GameDto;
import ro.usv.ip.service.GameService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameControllerTest {

    @InjectMocks
    private GameController gameController;

    @Mock
    private GameService gameService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewGame() {

        GameDto gameDto = GameDto.builder().build();
        gameDto.setId(1L);

        // Mock behavior
        when(gameService.createNewGame(any(GameDto.class))).thenReturn(gameDto);

        // Call the method
        ResponseEntity<GameDto> response = gameController.addNewGame(gameDto);

        // Verify interactions
        verify(gameService).createNewGame(gameDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(gameDto, response.getBody());
    }

    @Test
    public void testGetGameDetails() {
        // Prepare data
        Long gameId = 1L;
        GameDetailsDto gameDetailsDto = new GameDetailsDto();
        gameDetailsDto.setId(1L);

        // Mock behavior
        when(gameService.getGameDetails(gameId)).thenReturn(gameDetailsDto);

        // Call the method
        ResponseEntity<GameDetailsDto> response = gameController.getGameDetails(gameId);

        // Verify interactions
        verify(gameService).getGameDetails(gameId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(gameDetailsDto, response.getBody());
    }

}
