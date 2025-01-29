package com.example.reactionrace;

import java.util.List;
import java.util.Optional;

/**
 * Сервіс для роботи з гравцями.
 */
public class PlayerService {
  private final PlayerRepository playerRepository;

  public PlayerService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  public void registerPlayer(Player player) {
    if (playerRepository.readById(player.getId()).isPresent()) {
      throw new IllegalArgumentException("Гравець із таким ID вже існує.");
    }
    playerRepository.create(player);
    sendWelcomeEmail(player.getEmail(), player.getName());
  }

  public Optional<Player> findPlayerById(String id) {
    return playerRepository.readById(id);
  }

  public List<Player> findAllPlayers() {
    return playerRepository.readAll();
  }

  public void updatePlayer(String id, Player updatedPlayer) {
    playerRepository.update(id, updatedPlayer);
  }

  public void deletePlayer(String id) {
    playerRepository.delete(id);
  }

  private void sendWelcomeEmail(String email, String name) {
    // Імітація відправки електронного листа
    System.out.println("Лист надіслано на " + email + ":");
    System.out.println("Ласкаво просимо, " + name + "!");
  }
}
