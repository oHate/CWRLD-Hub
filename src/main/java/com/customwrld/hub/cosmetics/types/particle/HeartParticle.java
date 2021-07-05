package com.customwrld.hub.cosmetics.types.particle;

import com.customwrld.customlib.util.ItemFactory;
import com.customwrld.commonlib.util.MC;
import com.customwrld.hub.cosmetics.Cosmetic;
import com.customwrld.hub.cosmetics.CosmeticType;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class HeartParticle extends Cosmetic {

    public String getName() {
        return "Heart Particle";
    }

    public Component getDisplayName() {
        return Component.text("Heart Particle", MC.CC.DARK_RED.getTextColor());
    }

    public CosmeticType getCosmeticType() {
        return CosmeticType.PARTICLE;
    }

    public boolean hasPermission(Player player) {
        return player.hasPermission("hub.particle.heart");
    }

    public List<String> getDescription() {
        return null;
    }

    public ItemStack getIcon() {
        return new ItemFactory(Material.RED_DYE)
                .build();
    }

    public void apply(Player player) {}

    public void tick(Player player) {
        if (player == null || !player.isOnline())
            return;
        player.getWorld().spawnParticle(Particle.HEART, player.getLocation(), 1);
    }

    public void remove(Player player) {
        unselectCosmetic(player);
    }

    public boolean noPermissionHide() {
        return false;
    }
}
