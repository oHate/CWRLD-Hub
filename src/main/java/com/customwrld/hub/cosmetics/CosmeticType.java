package com.customwrld.hub.cosmetics;

import java.util.List;
import java.util.stream.Collectors;

import com.customwrld.commonlib.util.MC;
import com.customwrld.hub.Hub;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;

@Getter
public enum CosmeticType {
    ARMOR("Armor", Component.text("Armor", MC.CC.GREEN.getTextColor())
            .decoration(TextDecoration.ITALIC, false), Material.DIAMOND_CHESTPLATE),
    PARTICLE("Particles", Component.text("Particles", MC.CC.GREEN.getTextColor())
            .decoration(TextDecoration.ITALIC, false), Material.BLAZE_POWDER),
    GADGET("Gadgets", Component.text("Gadgets", MC.CC.GREEN.getTextColor())
            .decoration(TextDecoration.ITALIC, false), Material.IRON_HORSE_ARMOR),
    PET("Pets", Component.text("Pets", MC.CC.GREEN.getTextColor())
            .decoration(TextDecoration.ITALIC, false), Material.BONE);

    private final String name;
    private final Component display;
    private final Material icon;


    CosmeticType(String name, Component display, Material icon) {
        this.name = name;
        this.display = display;
        this.icon = icon;
    }

    public List<Cosmetic> getCosmetics() {
        return Hub.get().getCosmeticHandler().getCosmetics().stream().filter(iCosmetic -> (iCosmetic.getCosmeticType() == this)).collect(Collectors.toList());
    }
}
