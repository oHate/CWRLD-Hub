package com.customwrld.hub.cosmetics.player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.customwrld.hub.Hub;
import com.customwrld.hub.cosmetics.Cosmetic;
import com.customwrld.hub.cosmetics.CosmeticType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CosmeticPlayer {
    private final UUID uuid;

    public UUID getUuid() {
        return this.uuid;
    }

    private final List<Cosmetic> selectedCosmetics = new ArrayList<>();

    public List<Cosmetic> getSelectedCosmetics() {
        return this.selectedCosmetics;
    }

    public CosmeticPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean isSelected(Cosmetic cosmetic) {
        return this.selectedCosmetics.contains(cosmetic);
    }

    public void selectCosmetic(Cosmetic cosmetic) {
        getPlayer().sendMessage(cosmetic.getDisplayName());
        cosmetic.apply(getPlayer());
        this.selectedCosmetics.add(cosmetic);
    }

    public List<Cosmetic> getAvailableCosmetics() {
        return Hub.get().getCosmeticHandler().getCosmetics().stream().filter(cosmetic -> cosmetic.hasPermission(getPlayer())).collect(Collectors.toList());
    }

    public Cosmetic getCosmeticFromCategory(CosmeticType cosmeticType) {
        return getSelectedCosmetics().stream().filter(cosmetic -> (cosmetic.getCosmeticType() == cosmeticType)).findFirst().orElse(null);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }
}
