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

    // Реєстрація гравців
    authService.register("1", "Alice", "alice@example.com", "password123");
    authService.register("2", "Bob", "bob@example.com", "password456");

    // Вхід у систему
    authService.login("alice@example.com", "password123")
        .ifPresentOrElse(
            player -> System.out.println("Вхід успішний для гравця: " + player.getName()),
            () -> System.out.println("Помилка входу.")
        );

    // Додавання результатів гри
    gameResultService.saveGameResult(new GameResult("1", 1.23, true));
    gameResultService.saveGameResult(new GameResult("2", 1.56, false));

    // Виведення всіх результатів гри
    System.out.println("Усі результати гри:");
    gameResultService.getAllResults().forEach(System.out::println);

    // Фільтрація результатів гри
    System.out.println("Результати гри з часом <= 1.5 секунди:");
    gameResultService.filterResultsByReactionTime(1.5).forEach(System.out::println);
  }

  private static void clearFile(String filename) {
    try (FileWriter writer = new FileWriter(filename)) {
      writer.write("[]");
    } catch (IOException e) {
      System.err.println("Не вдалося очистити файл " + filename + ": " + e.getMessage());
    }
  }
}
