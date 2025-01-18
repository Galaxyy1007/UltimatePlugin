package me.galaxy1007.tutoplug.listeners;

import me.galaxy1007.tutoplug.UltimateMain;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class GUIListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (player.hasMetadata("GUI")) {
            e.setCancelled(true);
            e.setResult(Event.Result.DENY);

            if (e.getSlot() == 11) {
                player.getInventory().addItem(new ItemStack(Material.DIAMOND));
                player.closeInventory();

            } else if (e.getSlot() == 13) {
                player.getInventory().clear();
                player.closeInventory();

            } else if (e.getSlot() == 15) {
                player.getWorld().setTime(0);
                player.getWorld().setStorm(false);
                player.getWorld().setThundering(false);
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();

        if (player.hasMetadata("GUI")) {
            player.removeMetadata("GUI", UltimateMain.getInstance());
        }

    }
}
