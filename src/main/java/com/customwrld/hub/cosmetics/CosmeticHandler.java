package com.customwrld.hub.cosmetics;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.customwrld.customlib.util.ClassUtils;
import com.customwrld.hub.Hub;
import com.customwrld.hub.cosmetics.player.CosmeticPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CosmeticHandler implements Runnable, Listener {
    private final List<Cosmetic> cosmetics = new ArrayList<>();

    public List<Cosmetic> getCosmetics() {
        return this.cosmetics;
    }

    private final List<CosmeticPlayer> cosmeticPlayers = new ArrayList<>();

    public List<CosmeticPlayer> getCosmeticPlayers() {
        return this.cosmeticPlayers;
    }

    public void setup() {
        try {
            for (Class<?> aClass : ClassUtils.getClassesInPackage(Hub.class, "com.customwrld.hub.cosmetics.types")) {
                if (!Cosmetic.class.isAssignableFrom(aClass))
                    continue;
                Cosmetic cosmetic = (Cosmetic)aClass.newInstance();
                this.cosmetics.add(cosmetic);
                Bukkit.getPluginManager().registerEvents(cosmetic, (Hub.get()));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Bukkit.getPluginManager().registerEvents(this, Hub.get());
        Bukkit.getScheduler().runTaskTimer(Hub.get(), this, 0L, 1L);
    }

    public void run() {
        this.cosmeticPlayers.forEach(cosmeticPlayer -> cosmeticPlayer.getSelectedCosmetics().forEach(cosmetic -> cosmetic.tick(Bukkit.getPlayer(cosmeticPlayer.getUuid()))));
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.cosmeticPlayers.add(new CosmeticPlayer(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        CosmeticPlayer cosmeticPlayer = getPlayer(event.getPlayer());
        cosmeticPlayer.getSelectedCosmetics().forEach(cosmetic -> cosmetic.remove(event.getPlayer()));
        this.cosmeticPlayers.remove(cosmeticPlayer);
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        CosmeticPlayer cosmeticPlayer = getPlayer(event.getPlayer());
        cosmeticPlayer.getSelectedCosmetics().forEach(cosmetic -> cosmetic.remove(event.getPlayer()));
        this.cosmeticPlayers.remove(cosmeticPlayer);
    }

    public CosmeticPlayer getPlayer(UUID uuid) {
        return this.cosmeticPlayers.stream().filter(cosmeticPlayer -> cosmeticPlayer.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    public CosmeticPlayer getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

    public void deselectType(Player player, CosmeticType cosmeticType) {
        for(Cosmetic cosmetic : cosmeticType.getCosmetics()) {
            if(cosmetic.hasSelected(player)) {
                cosmetic.remove(player);
            }
        }
    }
}
