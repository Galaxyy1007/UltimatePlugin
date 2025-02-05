package me.galaxy1007.tutoplug.Commands;

import me.galaxy1007.tutoplug.UltimateMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class VanishCommand implements CommandExecutor {

    UltimateMain plugin;

    public VanishCommand(UltimateMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (plugin.invisible_list.contains(player)) {
                // Speler uit vanish halen
                for (Player people : Bukkit.getOnlinePlayers()) {
                    people.showPlayer(plugin, player);
                }
                plugin.invisible_list.remove(player);
                player.setInvulnerable(false);
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "UltimatePlugin" + ChatColor.GRAY + "] " + ChatColor.GRAY + "Vanish is " + ChatColor.RED + "uitgeschakeld");
                Bukkit.broadcastMessage(ChatColor.YELLOW + player.getName() + " joined the game");

                // Action bar leegmaken
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(""));

            } else {
                // Speler in vanish zetten
                for (Player people : Bukkit.getOnlinePlayers()) {
                    people.hidePlayer(plugin, player);
                }
                plugin.invisible_list.add(player);
                player.setInvulnerable(true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false));
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "UltimatePlugin" + ChatColor.GRAY + "] " + ChatColor.GRAY + "Vanish is " + ChatColor.GREEN + "ingeschakeld");
                Bukkit.broadcastMessage(ChatColor.YELLOW + player.getName() + " left the game");

                // Start een scheduler om de action bar te blijven tonen
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!plugin.invisible_list.contains(player)) {
                            // Stop de scheduler als de speler niet meer in vanish is
                            this.cancel();
                            return;
                        }
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Je bent in vanish!"));
                    }
                }.runTaskTimer(plugin, 0L, 20L); // Herhaal elke seconde (20 ticks)
            }
        }
        return true;
    }
}