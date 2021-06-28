package com.customwrld.hub.cosmetics;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.stream.Collectors;

import com.customwrld.hub.Hub;
import org.bukkit.Material;

public enum CosmeticType {
    ARMOR("Armor", "&b&lArmor Cosmetics", Material.DIAMOND_CHESTPLATE),
    PETS("Pets", "&a&lPet Cosmetics", Material.BONE),
    GADGET("Gadgets", "&3&lGadget Cosmetics", Material.IRON_HORSE_ARMOR),
    PARTICLE("Particles", "&c&lParticle", Material.BLAZE_POWDER);

    @ConstructorProperties({"name", "displayName", "icon"})
    CosmeticType(String name, String displayName, Material icon) {
        this.name = name;
        this.displayName = displayName;
        this.icon = icon;
    }

    private final String name;

    private final String displayName;

    private final Material icon;

    public String getName() {
        return this.displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Material getIcon() {
        return this.icon;
    }

    public List<Cosmetic> getCosmetics() {
        return Hub.get().getCosmeticHandler().getCosmetics().stream().filter(iCosmetic -> (iCosmetic.getCosmeticType() == this)).collect(Collectors.toList());
    }
}
