package com.example.reactionrace;

import java.util.Optional;

/**
 * Сервіс для аутентифікації та реєстрації користувачів.
 */
public class AuthService {
  private final PlayerService playerService;

  public AuthService(PlayerService playerService) {
    this.playerService = playerService;
  }

  public void register(String id, String name, String email, String password) {
    Player player = new Player(id, name, email, password);
    playerService.registerPlayer(player);
  }

  public Optional<Player> login(String email, String password) {
    return playerService.findAllPlayers().stream()
        .filter(player -> player.getEmail().equals(email) && player.getPassword().equals(password))
        .findFirst();
  }
}
