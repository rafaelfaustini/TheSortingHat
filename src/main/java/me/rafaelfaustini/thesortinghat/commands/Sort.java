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

    public String filter_keywords(String str, Player p, String h) {
        str = str.replace("{player}", p.getName());
        str = str.replace("{choice}", h);
        return str;
    }
    public static boolean isNullorZero(Integer i){
        return 0 == ( i == null ? 0 : i);
    }

    Plugin plugin = TheSortingHat.getPlugin(TheSortingHat.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){

            Player player = (Player) sender;

            if(plugin.getConfig().getString("SortedPlayers."+player.getName()+".choice") != null){
                player.sendMessage(ChatColor.RED+"You have already been sorted !");
                return true;
            }

            if(!player.hasPermission("thesortinghat.sort") || (!player.isOp() && !player.hasPermission("*") && player.hasPermission("thesortinghat.sorted") )) {
                String texto = plugin.getConfig().getString("NoPermission");
                player.sendMessage(ChatColor.RED+texto);
                return true;
            }
            Random r = new Random();
            int number_choices = plugin.getConfig().getConfigurationSection("Choices").getKeys(false).size();
            if(number_choices==0){
                player.sendMessage("There was an error while running this command");
                return true;
            }
            int number = r.nextInt(number_choices);


            int tmp = number+1;

            String name = plugin.getConfig().getString("Choices."+tmp+".name");

            String temp = plugin.getConfig().getString("Sorted");

            temp = "&6"+filter_keywords(temp,player,name);
            temp = ChatColor.translateAlternateColorCodes('&', temp);

            player.sendMessage(temp);


            List comando = plugin.getConfig().getStringList("Choices."+tmp+".commands");
            int i;
            for(i=0;i<comando.size();i++) {

                String commandtmp = filter_keywords(comando.get(i).toString(), player, name);
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), commandtmp);
            }

            plugin.getConfig().set("SortedPlayers."+player.getName()+".choice", name);

            plugin.saveConfig();

        }
        return true;
    }

}
