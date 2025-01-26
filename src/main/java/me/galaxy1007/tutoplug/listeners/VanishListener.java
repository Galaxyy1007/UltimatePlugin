package me.galaxy1007.tutoplug.listeners;

import me.galaxy1007.tutoplug.UltimateMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class VanishListener implements Listener {

    UltimateMain plugin;

    public VanishListener(UltimateMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        // Controleer of het doelwit een speler is
        if (event.getTarget() instanceof Player) {
            Player player = (Player) event.getTarget();

            // Controleer of de speler in de 'invisible_list' staat
            if (plugin.invisible_list.contains(player)) {
                // Annuleer het event zodat de speler niet wordt aangevallen
                event.setCancelled(true);
            } else if (!plugin.invisible_list.contains(player)) {
                event.setCancelled(false);
            }
        }
    }
}