package dev.myserver.selection;

import org.hytale.api.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Stockage en mémoire des sélections actives, indexées par joueur.
 */
public class SelectionManager {

    private final Map<Player, Selection> selections = new HashMap<>();

    public Selection get(Player player) {
        return selections.get(player);
    }

    public Selection getOrCreate(Player player) {
        Selection existing = selections.get(player);
        if (existing != null) {
            return existing;
        }

        Selection created = new Selection(player);
        selections.put(player, created);
        return created;
    }

    public void set(Player player, Selection selection) {
        selections.put(player, selection);
    }

    public void remove(Player player) {
        selections.remove(player);
    }
}
