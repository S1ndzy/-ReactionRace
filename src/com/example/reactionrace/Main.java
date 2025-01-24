package com.example.reactionrace;

public class Main {
  public static void main(String[] args) {
    PlayerRepository playerRepository = new PlayerRepository("players.json");

    // Створення нових гравців
    Player player1 = new Player("1", "Alice", "alice@example.com", "password123");
    Player player2 = new Player("2", "Bob", "bob@example.com", "password456");

    playerRepository.create(player1);
    playerRepository.create(player2);

    // Читання всіх гравців
    System.out.println("Усі гравці:");
    playerRepository.readAll().forEach(System.out::println);

    // Пошук гравця за ID
    System.out.println("Гравець з ID 1:");
    playerRepository.readById("1").ifPresent(System.out::println);

    // Оновлення даних гравця
    Player updatedPlayer = new Player("1", "Alice Updated", "alice.updated@example.com", "newpassword123");
    playerRepository.update("1", updatedPlayer);

    // Видалення гравця
    playerRepository.delete("2");

    // Фільтрація за іменем
    System.out.println("Гравці з ім'ям, що містить 'Alice':");
    playerRepository.searchByName("Alice").forEach(System.out::println);
  }
}
