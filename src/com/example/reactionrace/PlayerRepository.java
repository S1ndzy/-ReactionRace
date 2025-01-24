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
 * Репозиторій для гравців із кешуванням і збереженням у файл.
 */
public class PlayerRepository implements Repository<Player> {
  private final List<Player> cache = new ArrayList<>();
  private final String filename;
  private final Gson gson = new Gson();

  public PlayerRepository(String filename) {
    this.filename = filename;
    loadFromFile();
  }

  @Override
  public void create(Player entity) {
    cache.add(entity);
    saveToFile();
  }

  @Override
  public List<Player> readAll() {
    return new ArrayList<>(cache);
  }

  @Override
  public Optional<Player> readById(String id) {
    return cache.stream().filter(player -> player.getId().equals(id)).findFirst();
  }

  @Override
  public void update(String id, Player entity) {
    delete(id);
    create(entity);
  }

  @Override
  public void delete(String id) {
    cache.removeIf(player -> player.getId().equals(id));
    saveToFile();
  }

  private void loadFromFile() {
    try (FileReader reader = new FileReader(filename)) {
      Type listType = new TypeToken<List<Player>>() {}.getType();
      List<Player> players = gson.fromJson(reader, listType);
      if (players != null) {
        cache.clear();
        cache.addAll(players);
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

  public List<Player> searchByName(String name) {
    return cache.stream()
        .filter(player -> player.getName().toLowerCase().contains(name.toLowerCase()))
        .collect(Collectors.toList());
  }
}
