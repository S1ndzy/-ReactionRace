package com.example.reactionrace;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
  public static void main(String[] args) {
    // Генерація даних гравців
    Faker faker = new Faker();
    List<Player> players = new ArrayList<>();
    try {
      for (int i = 1; i <= 5; i++) {
        players.add(new Player(
            "player-" + i,
            faker.name().firstName(),
            faker.internet().emailAddress(),
            faker.internet().password(8, 16)
        ));
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Помилка при створенні гравця: " + e.getMessage());
      return;
    }

    // Генерація результатів гри
    List<GameResult> gameResults = new ArrayList<>();
    Random random = new Random();
    for (Player player : players) {
      try {
        double reactionTime = 0.5 + (2.5 - 0.5) * random.nextDouble();
        boolean success = random.nextBoolean();
        gameResults.add(new GameResult(player.getId(), reactionTime, success));
      } catch (IllegalArgumentException e) {
        System.out.println("Помилка при створенні результату гри: " + e.getMessage());
      }
    }

    // Збереження даних у файли
    saveToFile(players, "players.json");
    saveToFile(gameResults, "game_results.json");
  }

  private static <T> void saveToFile(List<T> data, String filename) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try (FileWriter writer = new FileWriter(filename)) {
      gson.toJson(data, writer);
      System.out.println("Дані успішно збережені у файл " + filename);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
