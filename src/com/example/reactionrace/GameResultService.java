package com.example.reactionrace;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервіс для роботи з результатами гри.
 */
public class GameResultService {
  private final GameResultRepository gameResultRepository;

  public GameResultService(GameResultRepository gameResultRepository) {
    this.gameResultRepository = gameResultRepository;
  }

  public void saveGameResult(GameResult result) {
    gameResultRepository.create(result);
  }

  public List<GameResult> getAllResults() {
    return gameResultRepository.readAll();
  }

  public List<GameResult> filterResultsByReactionTime(double maxTime) {
    return gameResultRepository.filterByReactionTime(maxTime);
  }

  public List<GameResult> getResultsByPlayer(String playerId) {
    return gameResultRepository.readAll().stream()
        .filter(result -> result.getPlayerId().equals(playerId))
        .collect(Collectors.toList());
  }
}
