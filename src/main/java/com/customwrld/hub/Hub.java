package com.customwrld.hub;

import com.customwrld.hub.cosmetics.CosmeticHandler;
import com.customwrld.hub.util.Visibility;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;

@Getter
public class Hub extends JavaPlugin {

    private static Hub hub;

    private Map<UUID, Integer> visibilityMap;
    private LinkedHashSet<NettyInject> nettyUsers;
    @Setter private Location spawn;
    private CosmeticHandler cosmeticHandler;

    @Override
    public void onEnable() {
        hub = this;
        visibilityMap = new HashMap<>();
        nettyUsers = new LinkedHashSet<>();
        Initialize.init(hub);
        (this.cosmeticHandler = new CosmeticHandler()).setup();
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(o -> nettyUsers.stream().filter(playerNetty -> playerNetty.getUuid().equals(o.getUniqueId())).findFirst().ifPresent(NettyInject::ejectNetty));
        this.cosmeticHandler.getCosmeticPlayers().forEach(cosmeticPlayer -> cosmeticPlayer.getSelectedCosmetics().forEach(cosmetic -> cosmetic.remove(cosmeticPlayer.getPlayer())));
    }

    public void setupNetty(Player player) {
        try {
            NettyInject playerNetty = new NettyInject(this, player);
            playerNetty.injectNetty(player);

            this.getNettyUsers().add(playerNetty);
        } catch (Exception exception) {
            throw new RuntimeException("An exception occurred while trying to setup netty for player " + player.getName(), exception);
        }
    }

    public static Hub get() {
        return hub;
    }
}
