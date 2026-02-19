package dev.myserver.handlers;

import org.hytale.api.entity.Player;
import org.hytale.api.events.player.InteractionType;

/**
 * Interface commune des handlers d'interaction.
 */
public interface InteractionHandler {
    void handle(Player player, Object target, InteractionType interactionType);
}
