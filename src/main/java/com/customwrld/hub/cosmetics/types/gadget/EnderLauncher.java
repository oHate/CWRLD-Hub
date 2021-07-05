package com.customwrld.hub.cosmetics.types.gadget;

import com.customwrld.customlib.util.InventoryUtil;
import com.customwrld.customlib.util.ItemFactory;
import com.customwrld.commonlib.util.MC;
import com.customwrld.hub.cosmetics.Cosmetic;
import com.customwrld.hub.cosmetics.CosmeticType;
import com.customwrld.hub.util.Util;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnderLauncher extends Cosmetic {

    public String getName() {
        return "Heart Particle";
    }

    public Component getDisplayName() {
        return Component.text("Ender Launcher", MC.CC.DARK_PURPLE.getTextColor());
    }

    public CosmeticType getCosmeticType() {
        return CosmeticType.GADGET;
    }

    public boolean hasPermission(Player player) {
        return player.hasPermission("hub.gadget.enderlauncher");
    }

    public List<String> getDescription() {
        return null;
    }

    public ItemStack getIcon() {
        return new ItemFactory(Material.ENDER_PEARL)
                .build();
    }

    public void apply(Player player) {}

    public void tick(Player player) {}

    public void remove(Player player) {
        unselectCosmetic(player);
    }

    public boolean noPermissionHide() {
        return false;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!(player.getGameMode() == GameMode.CREATIVE && player.hasPermission("hub.interact"))) {
            event.setCancelled(true);
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null && event.getHand() == EquipmentSlot.HAND) {
//                if (InventoryUtil.isSimilar(event.getItem(), Util.ENDER_LAUNCHER)) {
//                    event.setCancelled(true);
//                    player.setVelocity(player.getLocation().getDirection().multiply(1.7));
//                    player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0F, 2.0F);
//                    player.sendMessage("Test");
//                }
            }
        }
    }
}
