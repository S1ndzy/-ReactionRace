package com.example.reactionrace;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    // Очищення файлів перед запуском
    clearFile("players.json");
    clearFile("game_results.json");

    // Ініціалізація репозиторіїв
    PlayerRepository playerRepository = new PlayerRepository("players.json");
    GameResultRepository gameResultRepository = new GameResultRepository("game_results.json");

    // Ініціалізація сервісів
    PlayerService playerService = new PlayerService(playerRepository);
    GameResultService gameResultService = new GameResultService(gameResultRepository);
    AuthService authService = new AuthService(playerService);

    // Запуск консольного інтерфейсу
    ConsoleUI consoleUI = new ConsoleUI(authService);
    consoleUI.start();
  }

  private static void clearFile(String filename) {
    try (FileWriter writer = new FileWriter(filename)) {
      writer.write("[]");
    } catch (IOException e) {
      System.err.println("Не вдалося очистити файл " + filename + ": " + e.getMessage());
    }
  }
}
