package me.rafaelfaustini.thesortinghat.commands;

import me.rafaelfaustini.thesortinghat.TheSortingHat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import java.util.List;
import java.util.Random;

public class Sort implements CommandExecutor {

    Plugin plugin = TheSortingHat.getPlugin(TheSortingHat.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){

            Player player = (Player) sender;

            if(!player.hasPermission("thesortinghat.sort") || (player.isOp() == false && player.hasPermission("thesortinghat.sorted") )) {
                String texto = plugin.getConfig().getString("NoPermission");
                player.sendMessage(ChatColor.RED+texto);
                return true;
            }
            Random r = new Random();
            List<String> lista = plugin.getConfig().getStringList("Houses");
            int number = r.nextInt(lista.size());
            String texto = lista.get(number);
            String temp = plugin.getConfig().getString("Sorted");
            String comando = plugin.getConfig().getStringList("Commands").get(number);
            comando = comando.replace("{player}", player.getDisplayName());
            player.sendMessage("§6"+temp+texto);
            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), comando);

        }
        return true;
    }

}
