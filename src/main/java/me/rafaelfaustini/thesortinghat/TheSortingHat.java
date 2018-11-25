package me.rafaelfaustini.thesortinghat;
import me.rafaelfaustini.thesortinghat.commands.Sort;
import org.bukkit.plugin.java.JavaPlugin;

public final class TheSortingHat extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[TheSortingHat] Plugin activated successfully");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("sort").setExecutor(new Sort());

    }

    @Override
    public void onDisable() {
        System.out.println("[TheSortingHat] Plugin disabled");
        // Plugin shutdown logic
    }
}
