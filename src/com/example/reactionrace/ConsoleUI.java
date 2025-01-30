package com.example.reactionrace;

import java.util.Optional;
import java.util.Scanner;

/**
 * Консольний інтерфейс для взаємодії з користувачем.
 */
public class ConsoleUI {
  private final AuthService authService;
  private final Scanner scanner;
  private Player currentUser = null; // Зберігає авторизованого користувача

  public ConsoleUI(AuthService authService) {
    this.authService = authService;
    this.scanner = new Scanner(System.in);
  }

  public void start() {
    while (true) {
      if (currentUser == null) {
        showGuestMenu();
      } else {
        showUserMenu();
      }
    }
  }

  private void showGuestMenu() {
    System.out.println("=== ReactionRace ===");
    System.out.println("1. Реєстрація");
    System.out.println("2. Вхід");
    System.out.println("3. Вийти");
    System.out.print("Виберіть опцію: ");

    int choice = scanner.nextInt();
    scanner.nextLine(); // Очищення буфера

    switch (choice) {
      case 1 -> register();
      case 2 -> login();
      case 3 -> exit();
      default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
    }
  }

  private void showUserMenu() {
    System.out.println("=== Головне меню ===");
    System.out.println("1. Налаштування гри");
    System.out.println("2. Почати гру");
    System.out.println("3. Переглянути результати");
    System.out.println("4. Вийти з акаунту");
    System.out.print("Виберіть опцію: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    switch (choice) {
      case 1 -> System.out.println("Тут будуть налаштування гри.");
      case 2 -> System.out.println("Тут буде запуск гри.");
      case 3 -> System.out.println("Тут буде виведення результатів.");
      case 4 -> logout();
      default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
    }
  }

  private void register() {
    System.out.print("Введіть ID: ");
    String id = scanner.nextLine();
    System.out.print("Введіть ім'я: ");
    String name = scanner.nextLine();
    System.out.print("Введіть email: ");
    String email = scanner.nextLine();
    System.out.print("Введіть пароль: ");
    String password = scanner.nextLine();

    authService.register(id, name, email, password);
    System.out.println("Реєстрація успішна!");
  }

  private void login() {
    System.out.print("Введіть email: ");
    String email = scanner.nextLine();
    System.out.print("Введіть пароль: ");
    String password = scanner.nextLine();

    Optional<Player> player = authService.login(email, password);
    if (player.isPresent()) {
      currentUser = player.get();
      System.out.println("Вхід успішний! Вітаємо, " + currentUser.getName() + "!");
    } else {
      System.out.println("Помилка входу. Невірний email або пароль.");
    }
  }

  private void logout() {
    System.out.println("Вихід з акаунту: " + currentUser.getName());
    currentUser = null;
  }

  private void exit() {
    System.out.println("Дякуємо за гру! Вихід...");
    System.exit(0);
  }
}
