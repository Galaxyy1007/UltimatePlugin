package me.galaxy1007.tutoplug.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class VliegCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("flyplugin.fly")) {
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.DARK_GRAY + "Vliegen is nu ingeschakeld!");
            }

        }
        return true;
    }


}
