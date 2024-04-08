package me.galaxy1007.tutoplug;
import me.galaxy1007.tutoplug.Commands.BanGUICommand;
import me.galaxy1007.tutoplug.Commands.VliegCommand;
import me.galaxy1007.tutoplug.Commands.GUICommand;
import me.galaxy1007.tutoplug.Events.ClickEvent;
import me.galaxy1007.tutoplug.listeners.BanInventoryListener;
import me.galaxy1007.tutoplug.listeners.standaardlisteners;
import org.bukkit.plugin.java.JavaPlugin;

public class Tutoplug extends JavaPlugin {


    @Override
    public void onEnable() {
        getCommand("fly").setExecutor(new VliegCommand());
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        System.out.println("[Tutoplug] Deze plugin staat aan!");
    }


    @Override
    public void onDisable() {
        System.out.println("[TutoPlug] Deze plugin staat uit");
        // Plugin shutdown logic
    }
}
