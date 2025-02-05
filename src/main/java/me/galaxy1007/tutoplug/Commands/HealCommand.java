package me.galaxy1007.tutoplug.Commands;

import me.galaxy1007.tutoplug.UltimateMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    private final UltimateMain plugin;

    public HealCommand(UltimateMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Dit commando kan alleen door een speler worden gebruikt.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            // Zelf genezen
            healPlayer(player, player);
        } else {
            // Andere speler genezen
            Player target = Bukkit.getPlayer(args[0]);

            if (target != null && target.isOnline()) {
                healPlayer(player, target);
            } else {
                player.sendMessage(ChatColor.RED + "Speler niet gevonden of offline.");
            }
        }

        return true;
    }

    private void healPlayer(Player sender, Player target) {
        if (target.getGameMode() == GameMode.SURVIVAL || target.getGameMode() == GameMode.ADVENTURE) {
            target.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Je bent genezen!"));
            target.setHealth(20);
            target.setFoodLevel(20);
            sender.sendMessage(ChatColor.GREEN + "Je hebt " + target.getName() + " genezen!");
        } else {
            sender.sendMessage(ChatColor.RED + target.getName() + " is niet in survival of adventure mode en kan niet worden genezen!");
        }
    }
}
