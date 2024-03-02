package me.galaxy1007.tutoplug.Events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ClickEvent implements Listener {

    @EventHandler
    public boolean clickEvent(InventoryClickEvent e){

        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA + "Tutoplug GUI"))

            switch(e.getCurrentItem().getType()){
                case TNT:
                    player.closeInventory();
                    player.setHealth(0.0);
                    player.sendMessage(ChatColor.RED + "Je hebt zelfmoord gepleegd");
                    break;
                case BREAD:
                    player.closeInventory();
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.GREEN + "De honger is gestild");
                    break;
                case DIAMOND_SWORD:
                    player.closeInventory();
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
                    player.sendMessage(ChatColor.BLUE + "Je hebt een zwaard ontvangen!");
                    break;
            }
            e.setCancelled(true);
        return true;
    }
}

