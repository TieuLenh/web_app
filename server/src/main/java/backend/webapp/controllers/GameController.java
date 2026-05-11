package backend.webapp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.webapp.models.Game;
import backend.webapp.models.GameDetail;
import backend.webapp.services.GameDetailService;
import backend.webapp.services.GameService;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private GameService gameService;
    private GameDetailService gameDetailService;

    public GameController(
        GameService gameService, 
        GameDetailService gameDetailService
    ) {
        this.gameService = gameService;
        this.gameDetailService = gameDetailService;
    }
    @GetMapping
    public List<Game> getGames() {
        return gameService.getAllGames();
    }
    @GetMapping("/{id}")
    public GameDetail getGameDetail(@PathVariable Long id) {
        return gameDetailService.getGameDetailByGameId(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGame(
            @PathVariable Long id
    ) {

        gameService.deleteGame(id);

        return ResponseEntity.ok("Delete success");
    }
}
