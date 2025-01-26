package me.galaxy1007.tutoplug.listeners;

import me.galaxy1007.tutoplug.managers.BackpackManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AutoCollectListener implements Listener {

    private final BackpackManager backpackManager;

    public AutoCollectListener(BackpackManager backpackManager) {
        this.backpackManager = backpackManager;
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (!backpackManager.isBackpack(mainHand) || !backpackManager.isAutoCollectEnabled(mainHand)) {
            return;
        }

        var itemToAdd = event.getItem().getItemStack();
        var backpackContents = backpackManager.loadContents(mainHand);
        var tempInventory = Bukkit.createInventory(null, 27);
        tempInventory.setContents(backpackContents);

        if (hasRoom(tempInventory, itemToAdd)){
            addItemToInventory(tempInventory, itemToAdd);
            backpackManager.saveBackpackContents(tempInventory, mainHand);
            event.setCancelled(true);
            event.getItem().remove();
            player.sendMessage(ChatColor.GRAY + "Item collected into backpack!");
        }
    }

    private boolean hasRoom(Inventory inventory, ItemStack item) {
        for (ItemStack content : inventory.getContents()) {
            if (content != null && content.isSimilar(item) &&
                    content.getAmount() < content.getMaxStackSize()) {
                return true;
            }
        }
        return inventory.firstEmpty() != -1;
    }

    private void addItemToInventory(Inventory inventory, ItemStack item) {
        for (ItemStack content : inventory.getContents()) {
            if (content != null && content.isSimilar(item) &&
                    content.getAmount() < content.getMaxStackSize()) {
                int canAdd = content.getMaxStackSize() - content.getAmount();
                if (canAdd >= item.getAmount()) {
                    content.setAmount(content.getAmount() + item.getAmount());
                    return;
                }
            }
        }

        inventory.addItem(item);
    }

}
