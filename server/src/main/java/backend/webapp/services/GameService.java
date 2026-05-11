package backend.webapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.webapp.models.Game;
import backend.webapp.repositories.GameRepo;

@Service
public class GameService {
    private final GameRepo gameRepo;

    public GameService(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

    public List<Game> getAllGames() {
        return gameRepo.findByStatusContaining("ENABLE");
    }

    public void deleteGame(Long id) {
        gameRepo.disableGame(id);
    }
}
