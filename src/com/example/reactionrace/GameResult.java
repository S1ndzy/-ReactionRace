package com.example.reactionrace;

/**
 * Клас представляє результат гри.
 */
public class GameResult {
  private final String playerId;
  private final double reactionTime;
  private final boolean success;

  public GameResult(String playerId, double reactionTime, boolean success) {
    if (playerId == null || playerId.isBlank()) {
      throw new IllegalArgumentException("ID гравця не може бути порожнім.");
    }
    if (reactionTime < 0) {
      throw new IllegalArgumentException("Час реакції не може бути від'ємним.");
    }
    this.playerId = playerId;
    this.reactionTime = reactionTime;
    this.success = success;
  }

  public String getPlayerId() {
    return playerId;
  }

  public double getReactionTime() {
    return reactionTime;
  }

  public boolean isSuccess() {
    return success;
  }

  @Override
  public String toString() {
    return "GameResult{" +
        "playerId='" + playerId + '\'' +
        ", reactionTime=" + reactionTime +
        ", success=" + success +
        '}';
  }
}
