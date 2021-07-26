package com.customwrld.hub.util;

import com.customwrld.commonlib.util.MC;
import com.customwrld.customlib.util.ItemFactory;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

@Getter
public enum Visibility {
    ALL(new ItemFactory(Material.FIREWORK_STAR)
            .setName(Component.text()
                    .append(Component.text("All Players Visible ", MC.CC.GREEN.getTextColor(), TextDecoration.BOLD))
                    .append(Component.text("(Right Click)", MC.CC.GRAY.getTextColor()))
                    .build())
            .setLore(Component.text("Right Click to cycle through visibility!", MC.CC.GRAY.getTextColor()))
            .setRocketEffect(FireworkEffect.builder()
                    .withColor(Color.LIME)
                    .build())
            .addFlags(ItemFlag.HIDE_POTION_EFFECTS)
            .build()),
    STAFF_AND_FRIENDS(new ItemFactory(Material.FIREWORK_STAR)
            .setName(Component.text()
                    .append(Component.text("Staff and VIPs Visible ", MC.CC.DARK_PURPLE.getTextColor(), TextDecoration.BOLD))
                    .append(Component.text("(Right Click)", MC.CC.GRAY.getTextColor()))
                    .build())
            .setLore(Component.text("Right Click to cycle through visibility!", MC.CC.GRAY.getTextColor()))
            .setRocketEffect(FireworkEffect.builder()
                    .withColor(Color.PURPLE)
                    .build())
            .addFlags(ItemFlag.HIDE_POTION_EFFECTS)
            .build()),
    NONE(new ItemFactory(Material.FIREWORK_STAR)
            .setName(Component.text()
                    .append(Component.text("No Players Visible ", MC.CC.RED.getTextColor(), TextDecoration.BOLD))
                    .append(Component.text("(Right Click)", MC.CC.GRAY.getTextColor()))
                    .build())
            .setLore(Component.text("Right Click to cycle through visibility!", MC.CC.GRAY.getTextColor()))
            .setRocketEffect(FireworkEffect.builder()
                    .withColor(Color.RED)
                    .build())
            .addFlags(ItemFlag.HIDE_POTION_EFFECTS)
            .build());

    private final ItemStack itemStack;

    Visibility(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

}
