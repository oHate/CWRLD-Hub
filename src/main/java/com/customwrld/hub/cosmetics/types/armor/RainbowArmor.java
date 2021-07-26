package com.customwrld.hub.cosmetics.types.armor;

import com.customwrld.customlib.util.ItemFactory;
import com.customwrld.hub.Hub;
import com.customwrld.hub.cosmetics.Cosmetic;
import java.util.Arrays;
import java.util.List;

import com.customwrld.hub.cosmetics.CosmeticType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class RainbowArmor extends Cosmetic {
    private static final List<Color> colorList = Arrays.asList(
            Color.fromRGB(255, 0, 0),
            Color.fromRGB(255, 68, 0),
            Color.fromRGB(255, 111, 0),
            Color.fromRGB(255, 171, 0),
            Color.fromRGB(255, 255, 0),
            Color.fromRGB(188, 255, 0),
            Color.fromRGB(128, 255, 0),
            Color.fromRGB(43, 255, 0),
            Color.fromRGB(0, 255, 9),
            Color.fromRGB(0, 255, 51),
            Color.fromRGB(0, 255, 111),
            Color.fromRGB(0, 255, 162),
            Color.fromRGB(0, 255, 230),
            Color.fromRGB(0, 239, 255),
            Color.fromRGB(0, 196, 255),
            Color.fromRGB(0, 173, 255),
            Color.fromRGB(0, 162, 255),
            Color.fromRGB(0, 137, 255),
            Color.fromRGB(0, 100, 255),
            Color.fromRGB(0, 77, 255),
            Color.fromRGB(0, 34, 255),
            Color.fromRGB(17, 0, 255),
            Color.fromRGB(37, 0, 255),
            Color.fromRGB(68, 0, 255),
            Color.fromRGB(89, 0, 255),
            Color.fromRGB(102, 0, 255),
            Color.fromRGB(124, 0, 255),
            Color.fromRGB(154, 0, 255),
            Color.fromRGB(222, 0, 255),
            Color.fromRGB(255, 0, 247),
            Color.fromRGB(255, 0, 247),
            Color.fromRGB(255, 0, 179),
            Color.fromRGB(255, 0, 128));

    static int lastSelected = 1;

    public RainbowArmor() {
        Bukkit.getScheduler().runTaskTimer(Hub.get(), () -> {
            if (lastSelected >= colorList.size() - 1) {
                lastSelected = 0;
            } else {
                lastSelected++;
            }
        }, 0L, 1L);
    }

    public String getName() {
        return "Rainbow Armor";
    }

    public Component getDisplayName() {
        return Component.text("Rainbow Armor", TextColor.color(getColor().asRGB()));
    }

    public CosmeticType getCosmeticType() {
        return CosmeticType.ARMOR;
    }

    public boolean hasPermission(Player player) {
        return player.hasPermission("hub.armor.rainbow");
    }

    public List<String> getDescription() {
        return null;
    }

    public ItemStack getIcon() {
        return new ItemFactory(Material.LEATHER_CHESTPLATE)
                .setLeatherColor(getColor())
                .addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DYE)
                .build();
    }

    public void apply(Player player) {}

    public void tick(Player player) {
        if (player == null || !player.isOnline())
            return;
        Color color = getColor();

        ItemStack helmet = getColorArmor(Material.LEATHER_HELMET, color);
        ItemStack chestplate = getColorArmor(Material.LEATHER_CHESTPLATE, color);
        ItemStack leggings = getColorArmor(Material.LEATHER_LEGGINGS, color);
        ItemStack boots = getColorArmor(Material.LEATHER_BOOTS, color);

        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
        player.updateInventory();
    }

    public boolean noPermissionHide() {
        return false;
    }

    public void remove(Player player) {
        unselectCosmetic(player);
        player.getInventory().setArmorContents(null);
        player.updateInventory();
    }

    public static Color getColor() {
        return colorList.get(lastSelected);
    }

    public ItemStack getColorArmor(Material material, Color color) {
        return new ItemFactory(material)
                .setName(Component.text("Rainbow Armor", TextColor.color(color.asRGB()))
                        .decoration(TextDecoration.ITALIC, false))
                .setLeatherColor(color)
                .addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DYE)
                .build();
    }
}
