package me.rafaelfaustini.thesortinghat;
import me.rafaelfaustini.thesortinghat.commands.Sort;
import me.rafaelfaustini.thesortinghat.commands.Sortinghat;
import org.bukkit.plugin.java.JavaPlugin;

public final class TheSortingHat extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[TheSortingHat] Plugin activated successfully");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("sort").setExecutor(new Sort());
        getCommand("sortinghat").setExecutor(new Sortinghat());

    }

    @Override
    public void onDisable() {
        System.out.println("[TheSortingHat] Plugin disabled");
        // Plugin shutdown logic
    }
}


