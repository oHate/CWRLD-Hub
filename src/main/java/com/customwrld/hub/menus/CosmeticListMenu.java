//package com.customwrld.hub.menus;
//
//import com.customwrld.customlib.smartinvs.ClickableItem;
//import com.customwrld.customlib.smartinvs.SmartInventory;
//import com.customwrld.customlib.smartinvs.content.InventoryContents;
//import com.customwrld.customlib.smartinvs.content.InventoryProvider;
//import com.customwrld.customlib.smartinvs.content.Pagination;
//import com.customwrld.customlib.util.ItemFactory;
//import com.customwrld.hub.Hub;
//import com.customwrld.hub.cosmetics.Cosmetic;
//import com.customwrld.hub.cosmetics.CosmeticType;
//import com.customwrld.hub.cosmetics.player.CosmeticPlayer;
//import net.kyori.adventure.text.Component;
//import org.bukkit.ChatColor;
//import org.bukkit.Material;
//import org.bukkit.enchantments.Enchantment;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemFlag;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CosmeticListMenu implements InventoryProvider {
//
//    private final CosmeticType cosmeticType;
//    private final SmartInventory inventory;
//
//    CosmeticListMenu(Player player, CosmeticType cosmeticType) {
//        this.cosmeticType = cosmeticType;
//        this.inventory = SmartInventory.builder()
//                .id("punishmentsListMenu")
//                .provider(this)
//                .size(4, 9)
//                .title(cosmeticType.getName() + " Cosmetics").build();
//        this.inventory.open(player, 0);
//    }
//
//    @Override
//    public void init(Player player, InventoryContents contents) {
//        Pagination pagination = contents.pagination();
//
//        ClickableItem[] items = new ClickableItem[cosmeticType.getCosmetics().size()];
//
//        contents.fill(ClickableItem.empty(new ItemFactory(Material.GRAY_STAINED_GLASS_PANE).setName(Component.empty()).build()));
//
//        int i = 0;
//
//        for (Cosmetic cosmetic : this.cosmeticType.getCosmetics()) {
//            if (cosmetic.getCosmeticType() == this.cosmeticType) {
//                CosmeticPlayer cosmeticPlayer = Hub.get().getCosmeticHandler().getPlayer(player);
//
//                List<String> lore = new ArrayList<>();
//                lore.add(CC.MENU_BAR);
//                lore.add(cosmetic.hasPermission(player) ? (cosmeticPlayer.getSelectedCosmetics().contains(cosmetic) ? (ChatColor.RED + "Click to deselect this Cosmetic!") : (ChatColor.GREEN + "Click to select this Cosmetic!")) : (ChatColor.RED + "You do not have permission for this cosmetic!"));
//                lore.add(CC.MENU_BAR);
//
//                ItemBuilder cosmeticItem = new ItemBuilder(cosmetic.getIcon()).title(cosmetic.getDisplayName()).lores(lore);
//
//                if(cosmeticPlayer.isSelected(cosmetic)) {
//                    cosmeticItem.addFlag(ItemFlag.HIDE_ENCHANTS);
//                    cosmeticItem.enchantment(Enchantment.LUCK, 1);
//                }
//
//                items[i] = ClickableItem.of(cosmeticItem.build(),
//                        e -> {
//                            if(!cosmetic.hasPermission(player)) {
//                                return;
//                            }
//                            if(cosmeticPlayer.isSelected(cosmetic)) {
//                                cosmetic.remove(player);
//                            } else {
//                                Hub.get().getCosmeticHandler().deselectType(player, cosmeticType);
//                                cosmeticPlayer.selectCosmetic(cosmetic);
//                            }
//                        });
//                i++;
//            }
//        }
//
//        pagination.setItems(items);
//        pagination.setItemsPerPage(27);
//
//        pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL, 1, 0));
//
//        contents.set(0, 0, ClickableItem.of(new ItemBuilder(Material.ARROW).title("&6Previous Page").build(),
//                e -> inventory.open(player, pagination.previous().getPage())));
//
//        contents.set(0, 8, ClickableItem.of(new ItemBuilder(Material.ARROW).title("&6Next Page").build(),
//                e -> inventory.open(player, pagination.next().getPage())));
//
//    }
//
//    @Override
//    public void update(Player player, InventoryContents contents) {
//        Pagination pagination = contents.pagination();
//
//        ClickableItem[] items = new ClickableItem[cosmeticType.getCosmetics().size()];
//
//        int i = 0;
//
//        for (Cosmetic cosmetic : this.cosmeticType.getCosmetics()) {
//            if (cosmetic.getCosmeticType() == this.cosmeticType) {
//                CosmeticPlayer cosmeticPlayer = Hub.get().getCosmeticHandler().getPlayer(player);
//
//                List<String> lore = new ArrayList<>();
//                lore.add(CC.MENU_BAR);
//                lore.add(cosmetic.hasPermission(player) ? (cosmeticPlayer.getSelectedCosmetics().contains(cosmetic) ? (ChatColor.RED + "Click to deselect this Cosmetic!") : (ChatColor.GREEN + "Click to select this Cosmetic!")) : (ChatColor.RED + "You do not have permission for this cosmetic!"));
//                lore.add(CC.MENU_BAR);
//
//                ItemBuilder cosmeticItem = new ItemBuilder(cosmetic.getIcon()).title(cosmetic.getDisplayName()).lores(lore);
//
//                if(cosmeticPlayer.isSelected(cosmetic)) {
//                    cosmeticItem.addFlag(ItemFlag.HIDE_ENCHANTS);
//                    cosmeticItem.enchantment(Enchantment.LUCK, 1);
//                }
//
//                items[i] = ClickableItem.of(cosmeticItem.build(),
//                        e -> {
//                            if(!cosmetic.hasPermission(player)) {
//                                return;
//                            }
//                            if(cosmeticPlayer.isSelected(cosmetic)) {
//                                cosmetic.remove(player);
//                            } else {
//                                cosmeticPlayer.selectCosmetic(cosmetic);
//                            }
//                        });
//                i++;
//            }
//        }
//
//        pagination.setItems(items);
//        pagination.setItemsPerPage(27);
//
//        pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL, 1, 0));
//    }
//}
