package me.galaxy1007.tutoplug.Commands;

import me.galaxy1007.tutoplug.UltimateMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArmorStandCommand implements CommandExecutor {

    UltimateMain plugin;

    public ArmorStandCommand(UltimateMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            plugin.openMainMenu(player);
        }
        return true;
    }
}
