package me.galaxy1007.tutoplug.Events;


import me.galaxy1007.tutoplug.UltimateMain;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuHandler implements Listener {

    UltimateMain plugin;

    public MenuHandler(UltimateMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();

        //Menu list
        final String MAIN_MENU = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("main-menu"));
        final String CREATE_MENU = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("create-menu"));
        final String CONFIRM_MENU = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("confirm-menu"));
        final String ARMOR_MENU = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("armor-menu"));

        if (e.getView().getTitle().equalsIgnoreCase(MAIN_MENU)){
            switch(e.getCurrentItem().getType()){
                case ARMOR_STAND:
                    player.sendMessage("Opened Armor Stand Create Menu");
                    player.closeInventory();
                    //Open the armor stand menu
                    plugin.openCreateMenu(player);
                    break;
                case BARRIER:
                    player.sendMessage("Closing Main Menu");
                    player.closeInventory();
                    break;
            }
        }else if(e.getView().getTitle().equalsIgnoreCase(CREATE_MENU)){

            if (!plugin.armorstands.containsKey(player)){
                ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
                stand.setVisible(false);
                plugin.armorstands.put(player, stand);
            }

            switch(e.getCurrentItem().getType()){
                case ARMOR_STAND:
                    plugin.openConfirmMenu(player, Material.ARMOR_STAND);
                    break;
                case BEACON:
                    plugin.openConfirmMenu(player, Material.BEACON);
                    break;
                case LEATHER_CHESTPLATE:
                    plugin.openArmorMenu(player);
                    break;
                case STONE_SLAB:
                    plugin.openConfirmMenu(player, Material.STONE_SLAB);
                    break;
                case GREEN_WOOL:
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("create-message")));
                    if (plugin.armorstands.containsKey(player)){
                        ArmorStand stand = plugin.armorstands.get(player);
                        stand.setVisible(true);
                        plugin.armorstands.remove(player);
                    }
                    player.closeInventory();
                    break;
                case RED_WOOL:
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("delete-message")));
                    if (plugin.armorstands.containsKey(player)){
                        ArmorStand stand = plugin.armorstands.get(player);
                        stand.remove();
                        plugin.armorstands.remove(player);
                    }
                    player.closeInventory();
                    break;
            }
            e.setCancelled(true);
        }else if(e.getView().getTitle().equalsIgnoreCase(CONFIRM_MENU)){
            if (e.getClickedInventory().contains(Material.ARMOR_STAND)){
                switch(e.getCurrentItem().getType()){
                    case GREEN_WOOL:
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("confirm-option")));
                        if (plugin.armorstands.containsKey(player)){
                            ArmorStand stand = plugin.armorstands.get(player);
                            stand.setArms(true);
                        }
                        plugin.openCreateMenu(player);
                        break;
                    case RED_WOOL:
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("cancel-option")));
                        if (plugin.armorstands.containsKey(player)){
                            ArmorStand stand = plugin.armorstands.get(player);
                            stand.setArms(false);
                        }
                        plugin.openCreateMenu(player);
                        break;
                }
            }else if (e.getClickedInventory().contains(Material.BEACON)){
                switch(e.getCurrentItem().getType()){
                    case GREEN_WOOL:
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("confirm-option")));
                        if (plugin.armorstands.containsKey(player)){
                            ArmorStand stand = plugin.armorstands.get(player);
                            stand.setGlowing(true);
                        }
                        plugin.openCreateMenu(player);
                        break;
                    case RED_WOOL:
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("cancel-option")));
                        if (plugin.armorstands.containsKey(player)){
                            ArmorStand stand = plugin.armorstands.get(player);
                            stand.setGlowing(false);
                        }
                        plugin.openCreateMenu(player);
                        break;
                }
            }if (e.getClickedInventory().contains(Material.STONE_SLAB)){
                switch(e.getCurrentItem().getType()){
                    case GREEN_WOOL:
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("confirm-option")));
                        if (plugin.armorstands.containsKey(player)){
                            ArmorStand stand = plugin.armorstands.get(player);
                            stand.setBasePlate(true);
                        }
                        plugin.openCreateMenu(player);
                        break;
                    case RED_WOOL:
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("cancel-option")));
                        if (plugin.armorstands.containsKey(player)){
                            ArmorStand stand = plugin.armorstands.get(player);
                            stand.setBasePlate(false);
                        }
                        plugin.openCreateMenu(player);
                        break;
                }
            }
            e.setCancelled(true);
        }else if(e.getView().getTitle().equalsIgnoreCase(ARMOR_MENU)){

            if (plugin.armorstands.containsKey(player)){
                ArmorStand stand = plugin.armorstands.get(player);
                switch (e.getCurrentItem().getType()){
                    case DIAMOND_HELMET:
                        if (stand.getHelmet().getType() == Material.DIAMOND_HELMET){
                            stand.setHelmet(null);
                            player.sendMessage("Removed");
                        }else{
                            stand.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                            player.sendMessage("Added");
                        }
                        break;
                    case DIAMOND_CHESTPLATE:
                        if (stand.getHelmet().getType() == Material.DIAMOND_CHESTPLATE){
                            stand.setChestplate(null);
                            player.sendMessage("Removed");
                        }else{
                            stand.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                            player.sendMessage("Added");
                        }
                        break;
                    case DIAMOND_LEGGINGS:
                        if (stand.getHelmet().getType() == Material.DIAMOND_LEGGINGS){
                            stand.setLeggings(null);
                            player.sendMessage("Removed");
                        }else{
                            stand.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                            player.sendMessage("Added");
                        }
                        break;
                    case DIAMOND_BOOTS:
                        if (stand.getHelmet().getType() == Material.DIAMOND_BOOTS){
                            stand.setBoots(null);
                            player.sendMessage("Removed");
                        }else{
                            stand.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                            player.sendMessage("Added");
                        }
                        break;
                    case GREEN_WOOL:
                        plugin.openCreateMenu(player);
                        break;
                }
            }

            e.setCancelled(true);
        }
    }

}
