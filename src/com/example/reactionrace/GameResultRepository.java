package com.example.reactionrace;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Репозиторій для результатів гри із кешуванням і збереженням у файл.
 */
public class GameResultRepository implements Repository<GameResult> {
  private final List<GameResult> cache = new ArrayList<>();
  private final String filename;
  private final Gson gson = new Gson();

  public GameResultRepository(String filename) {
    this.filename = filename;
    loadFromFile();
  }

  @Override
  public void create(GameResult entity) {
    cache.add(entity);
    saveToFile();
  }

  @Override
  public List<GameResult> readAll() {
    return new ArrayList<>(cache);
  }

  @Override
  public Optional<GameResult> readById(String id) {
    return cache.stream().filter(result -> result.getPlayerId().equals(id)).findFirst();
  }

  @Override
  public void update(String id, GameResult entity) {
    delete(id);
    create(entity);
  }

  @Override
  public void delete(String id) {
    cache.removeIf(result -> result.getPlayerId().equals(id));
    saveToFile();
  }

  private void loadFromFile() {
    try (FileReader reader = new FileReader(filename)) {
      Type listType = new TypeToken<List<GameResult>>() {}.getType();
      List<GameResult> results = gson.fromJson(reader, listType);
      if (results != null) {
        cache.clear();
        cache.addAll(results);
      }
    } catch (IOException e) {
      System.out.println("Помилка читання файлу: " + e.getMessage());
    }
  }

  private void saveToFile() {
    try (FileWriter writer = new FileWriter(filename)) {
      gson.toJson(cache, writer);
    } catch (IOException e) {
      System.out.println("Помилка запису у файл: " + e.getMessage());
    }
  }

  public List<GameResult> filterByReactionTime(double maxTime) {
    return cache.stream().filter(result -> result.getReactionTime() <= maxTime).collect(Collectors.toList());
  }
}
