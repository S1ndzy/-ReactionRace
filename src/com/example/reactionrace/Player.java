package com.example.reactionrace;

/**
 * Клас представляє гравця у грі.
 */
public class Player {
  private final String id;
  private final String name;
  private final String email;
  private final String password;

  public Player(String id, String name, String email, String password) {
    if (id == null || id.isBlank()) {
      throw new IllegalArgumentException("ID не може бути порожнім.");
    }
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Ім'я не може бути порожнім.");
    }
    if (email == null || !email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
      throw new IllegalArgumentException("Некоректна електронна адреса.");
    }
    if (password == null || password.length() < 8) {
      throw new IllegalArgumentException("Пароль має містити щонайменше 8 символів.");
    }
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
