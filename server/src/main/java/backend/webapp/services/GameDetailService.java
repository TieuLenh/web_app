package backend.webapp.services;

import org.springframework.stereotype.Service;

import backend.webapp.models.GameDetail;
import backend.webapp.repositories.GameDetailRepo;

@Service
public class GameDetailService {

    private final GameDetailRepo gameDetailRepo;

    public GameDetailService(GameDetailRepo gameDetailRepo) {
        this.gameDetailRepo = gameDetailRepo;
    }

    public GameDetail getGameDetailByGameId(Long gameId) {
        return gameDetailRepo.findByGameId(gameId);
    }   
} 