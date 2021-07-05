package com.customwrld.hub.cosmetics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.customwrld.hub.Hub;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class Cosmetic implements Listener {
    public void unselectCosmetic(Player player) {
        Hub.get().getCosmeticHandler().getPlayer(player).getSelectedCosmetics().removeIf(cosmetic -> cosmetic.equals(this));
    }

    public boolean hasSelected(Player player) {
        return Hub.get().getCosmeticHandler().getPlayer(player).isSelected(this);
    }

    public List<Player> getNearbyPlayers(Player player, int radius, boolean ignoreVis) {
        List<Player> playerList = new ArrayList<>(Collections.singletonList(player));
        playerList.addAll(player.getNearbyEntities(radius, radius, radius).stream().filter(entity -> (entity instanceof Player && (ignoreVis || player.canSee((Player)entity)))).map(entity -> (Player)entity).collect(Collectors.toList()));
        return playerList;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Cosmetic && ((Cosmetic)obj).getName().equals(getName()) && ((Cosmetic)obj).getCosmeticType() == getCosmeticType());
    }

    public abstract String getName();

    public abstract Component getDisplayName();

    public abstract CosmeticType getCosmeticType();

    public abstract boolean hasPermission(Player paramPlayer);

    public abstract List<String> getDescription();

    public abstract ItemStack getIcon();

    public abstract void apply(Player paramPlayer);

    public abstract void tick(Player paramPlayer);

    public abstract void remove(Player paramPlayer);

    public abstract boolean noPermissionHide();
}
