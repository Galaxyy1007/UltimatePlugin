package me.galaxy1007.tutoplug.listeners;

import me.galaxy1007.tutoplug.managers.BackpackManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BackpackListener implements Listener {

    private final BackpackManager backpackManager;

    public BackpackListener(BackpackManager backpackManager) {
        this.backpackManager = backpackManager;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        ItemStack item = event.getItem();
        if (item == null) return;

        // Prevent placing the backpack as a block
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && backpackManager.isBackpack(item)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You cant place this on the ground");
            return;
        }

        var backpackInventory = backpackManager.getBackpackInventory(item);
        if (backpackInventory == null) return;

        if (event.getPlayer().isSneaking()) {
            var newState = backpackManager.toggleAutoCollect(item);
            event.getPlayer().sendMessage(
                    ChatColor.GOLD + "Auto-collect mode: " +
                            (newState ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled")
            );
            return;
        }

        event.getPlayer().openInventory(backpackInventory);
    }

    @EventHandler
    public void onBackpackClose(InventoryCloseEvent event) {
        // Figure out if the player is closing a backpack inventory
        // If they are, save the contents of the inventory to the item's persistent data container
        var backpackId = backpackManager.getBackpackId(event.getInventory());
        if (backpackId == null) return;

        for (var item : event.getPlayer().getInventory().getContents()) {
            if (item != null && backpackManager.isBackpackItemById(item, backpackId)) {
                // Save the contents to the item
                backpackManager.saveBackpackContents(event.getInventory(), item);
                break;
            }
        }
    }
}