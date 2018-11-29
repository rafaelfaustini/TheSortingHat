package me.rafaelfaustini.thesortinghat.commands;

import me.rafaelfaustini.thesortinghat.TheSortingHat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class Sortinghat implements CommandExecutor {

    Plugin plugin = TheSortingHat.getPlugin(TheSortingHat.class);


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){

            Player player = (Player) sender;

            if(!player.hasPermission("thesortinghat.admin")) {
                String texto = plugin.getConfig().getString("NoPermission");
                player.sendMessage(ChatColor.RED+texto);
                return true;
            }

            if(args.length > 0) {
                if (args[0].equalsIgnoreCase("list")) {
                    int length = plugin.getConfig().getConfigurationSection("Choices").getKeys(false).size();
                    int i;
                    player.sendMessage(String.format("%-50s", ChatColor.BOLD.toString()+ChatColor.AQUA.toString()+"[TheSortingHat]"+ChatColor.BOLD.toString()+ChatColor.GOLD.toString()+"Choices list:"));
                    for(i=0;i<length;i++) {
                        String name = plugin.getConfig().getString("Choices."+(i+1)+".name");
                        player.sendMessage(ChatColor.ITALIC+ChatColor.DARK_AQUA.toString()+name);
                    }
                }
            }
        }
        return true;
    }

}
