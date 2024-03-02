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
        this.getServer().getPluginManager().registerEvents(new standaardlisteners(), this);
        getCommand("fly").setExecutor(new VliegCommand());
        getCommand("gui").setExecutor(new GUICommand());
        getCommand("bangui").setExecutor(new BanGUICommand());
        getServer().getPluginManager().registerEvents(new BanInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        System.out.println("[Tutoplug] Deze plugin staat aan!");
    }


    @Override
    public void onDisable() {
        System.out.println("[TutoPlug] Deze plugin staat uit");
        // Plugin shutdown logic
    }
}