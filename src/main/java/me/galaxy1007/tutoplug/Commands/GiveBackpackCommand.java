package me.galaxy1007.tutoplug.Commands;

import me.galaxy1007.tutoplug.managers.BackpackManager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveBackpackCommand implements CommandExecutor {

    private final BackpackManager backpackManager;

    public GiveBackpackCommand(BackpackManager backpackManager) {
        this.backpackManager = backpackManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Alleen spelers kunnen dit commando gebruiken!");
            return true;
        }

        Player player = (Player) sender;
        if (player.isOp()) {
            // Maak een nieuwe backpack en geef deze aan de speler
            player.getInventory().addItem(backpackManager.createBackpack());
            player.sendMessage(ChatColor.GREEN + "Je hebt een rugtas ontvangen!");
        } else if (player.getGameMode().equals(GameMode.CREATIVE)) {
            player.getInventory().addItem(backpackManager.createBackpack());
            player.sendMessage(ChatColor.GREEN + "Je hebt een rugtas ontvangen!");
        } else if (!player.isOp() || !player.getGameMode().equals(GameMode.CREATIVE)) {
            player.sendMessage(ChatColor.RED + "Je hebt speciale rechten nodig om de rugtas via een commando te verkrijgen!");
        }
        return true;
    }
}