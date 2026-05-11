package backend.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.webapp.models.GameDetail;

public interface GameDetailRepo extends JpaRepository<GameDetail, Long> {

    // lấy detail theo game id
    GameDetail findByGameId(Long gameId);
}