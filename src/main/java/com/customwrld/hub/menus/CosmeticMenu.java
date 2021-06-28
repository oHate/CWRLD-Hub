//package com.customwrld.hub.menus;
//
//import com.customwrld.customlib.smartinvs.ClickableItem;
//import com.customwrld.customlib.smartinvs.SmartInventory;
//import com.customwrld.customlib.smartinvs.content.InventoryContents;
//import com.customwrld.customlib.smartinvs.content.InventoryProvider;
//import com.customwrld.customlib.util.ItemFactory;
//import com.customwrld.hub.cosmetics.CosmeticType;
//import net.kyori.adventure.text.Component;
//import org.bukkit.Material;
//import org.bukkit.entity.Player;
//
//public class CosmeticMenu implements InventoryProvider {
//
//    public CosmeticMenu(Player player) {
//        SmartInventory inventory = SmartInventory.builder()
//                .provider(this)
//                .size(3, 9)
//                .title("Cosmetic Selection Menu").build();
//        inventory.open(player);
//    }
//
//    @Override
//    public void init(Player player, InventoryContents contents) {
//        contents.fill(ClickableItem.empty(new ItemFactory(Material.GRAY_STAINED_GLASS_PANE).setName(Component.empty()).build()));
//
//        contents.set(1, 1, ClickableItem.of(new ItemBuilder(CosmeticType.ARMOR.getIcon()).title("Armor Cosmetics").build(),
//                e -> new CosmeticListMenu(player, CosmeticType.ARMOR)));
//
//        contents.set(1, 3, ClickableItem.of(new ItemBuilder(CosmeticType.PARTICLE.getIcon()).title("Particles Cosmetics").build(),
//                e -> new CosmeticListMenu(player, CosmeticType.PARTICLE)));
//
//        contents.set(1, 5, ClickableItem.of(new ItemBuilder(CosmeticType.GADGET.getIcon()).title("Gadgets Cosmetics").build(),
//                e -> new CosmeticListMenu(player, CosmeticType.GADGET)));
//
//        contents.set(1, 7, ClickableItem.of(new ItemBuilder(CosmeticType.PETS.getIcon()).title("Pets Cosmetics").build(),
//                e -> new CosmeticListMenu(player, CosmeticType.PETS)));
//    }
//}
