package dev.myserver.listener;

import dev.myserver.handlers.InteractionHandler;
import org.hytale.api.entity.Player;
import org.hytale.api.events.EventListener;
import org.hytale.api.events.player.InteractionType;
import org.hytale.api.events.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Listener central qui délègue les interactions à des handlers spécialisés.
 */
public class ListenerCentral {

    private final Map<Class<?>, InteractionHandler> handlers = new HashMap<>();

    public void registerHandler(Class<?> targetClass, InteractionHandler handler) {
        handlers.put(targetClass, handler);
    }

    @EventListener
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Object target = event.getTarget();
        InteractionType interactionType = event.getInteractionType();

        if (target == null) {
            return;
        }

        InteractionHandler handler = resolveHandler(target.getClass());
        if (handler != null) {
            handler.handle(player, target, interactionType);
        }
    }

    private InteractionHandler resolveHandler(Class<?> targetClass) {
        Class<?> current = targetClass;
        while (current != null) {
            InteractionHandler handler = handlers.get(current);
            if (handler != null) {
                return handler;
            }
            current = current.getSuperclass();
        }

        return null;
    }
}
