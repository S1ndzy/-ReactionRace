package com.example.reactionrace;

import java.util.List;
import java.util.Optional;

/**
 * Базовий інтерфейс репозиторію для CRUD-операцій.
 */
public interface Repository<T> {
  void create(T entity);

  List<T> readAll();

  Optional<T> readById(String id);

  void update(String id, T entity);

  void delete(String id);
}
