package me.galaxy1007.tutoplug.Commands;

import me.galaxy1007.tutoplug.UltimateMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    UltimateMain plugin;

    public HealCommand(UltimateMain plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
                for (Player people : Bukkit.getOnlinePlayers()){
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "UltimatePlugin" + ChatColor.GRAY + "] " + ChatColor.GRAY + "U bent genezen!");
                    player.setHealth(20);
                    player.setFoodLevel(20);
                }

        }
        return true;
    }
}
