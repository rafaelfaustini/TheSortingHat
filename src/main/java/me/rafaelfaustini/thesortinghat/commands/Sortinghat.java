package me.rafaelfaustini.thesortinghat.commands;

import com.sun.org.apache.xerces.internal.xs.StringList;
import me.rafaelfaustini.thesortinghat.TheSortingHat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


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
                    String choiceslist_title = ChatColor.DARK_PURPLE+"--------------.[ "+ChatColor.YELLOW+"Choices List - "+length+" choices "+ChatColor.DARK_PURPLE+"].--------------";
                    player.sendMessage(choiceslist_title);
                    for(i=0;i<length;i++) {
                        String name = plugin.getConfig().getString("Choices."+(i+1)+".name");

                        int commands_len = plugin.getConfig().getStringList("Choices."+(i+1)+".commands").size();

                        player.sendMessage(ChatColor.ITALIC+ChatColor.DARK_PURPLE.toString()+" • "+ChatColor.YELLOW+name+ChatColor.DARK_PURPLE+" ( "+ChatColor.YELLOW+commands_len+" commands "+ChatColor.DARK_PURPLE+")");
                    }
                }


                if (args[0].equalsIgnoreCase("add")) {
                    if(args.length < 2){
                        player.sendMessage(ChatColor.YELLOW+"Usage:"+ChatColor.LIGHT_PURPLE+" /sorting add [name]");
                        return true;
                    }
                    int length = plugin.getConfig().getConfigurationSection("Choices").getKeys(false).size();
                    plugin.getConfig().set("Choices."+(length+1)+".name", args[1]);
                    player.sendMessage(ChatColor.GREEN+"You successfully added "+args[1]);
                    player.sendMessage(ChatColor.GREEN+"To add commands /sortinghat command "+args[1]+" add [command] ");
                    plugin.saveConfig();


                }

                if (args[0].equalsIgnoreCase("command")) {
                    if(args[2].equals("add")) {
                        if (args.length < 4) {
                            player.sendMessage(ChatColor.YELLOW + "Usage:" + ChatColor.LIGHT_PURPLE + " /sh command [choice] add [command]");
                            return true;
                        }
                        int length = plugin.getConfig().getConfigurationSection("Choices").getKeys(false).size();
                        int id = -90;
                        for (int i = 0; i < length; i++) {
                            String name = plugin.getConfig().getString("Choices." + (i + 1) + ".name");
                            if (name.equals(args[1])) {
                                id = i + 1;
                            }
                        }
                        if (id == -90) {
                            player.sendMessage(ChatColor.RED + "Invalid choice, try again");
                            return true;
                        }
                        List<String> items = new ArrayList<String>();
                        if (!plugin.getConfig().getStringList("Choices." + id + ".commands").isEmpty()) {
                            items = plugin.getConfig().getStringList("Choices." + id + ".commands");
                        }
                        String command_add = "";
                        for (int i = 3; i < args.length; i++) {
                            command_add = command_add.concat(args[i] + " ");
                        }
                        command_add = command_add.trim();
                        ((List) items).add(command_add + " ");

                        if (id != -90) {
                            plugin.getConfig().set("Choices." + (id) + ".commands", items);
                            player.sendMessage(ChatColor.GREEN + "You successfully added the command " + command_add);
                            plugin.saveConfig();
                        } else
                            player.sendMessage(ChatColor.RED + "There was an error while adding the command " + command_add);
                        return true;
                    }



                }

            } else {
                String help_title = ChatColor.DARK_PURPLE+"--------------.[ "+ChatColor.YELLOW+"TheSortingHat Help "+ChatColor.DARK_PURPLE+"].--------------";
                player.sendMessage(help_title);
                player.sendMessage(ChatColor.YELLOW+"/sortinghat list "+ChatColor.LIGHT_PURPLE+" Display the list of choices");
                player.sendMessage(ChatColor.YELLOW+"/sortinghat add [name] "+ChatColor.LIGHT_PURPLE+" Adds a new choice");
                player.sendMessage(ChatColor.YELLOW+"/sortinghat command [choice] add [command] "+ChatColor.LIGHT_PURPLE+" Adds a command to be executed when the choice is sorted");

            }
        }
        return true;
    }

}
