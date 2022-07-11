package me.aq.plugin.ntirmoney.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class tab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(!command.getName().equalsIgnoreCase("money"))return null;

        if(args.length == 1)return null;

        ArrayList<String> tab = new ArrayList<>();

        tab.add("pay");

        return tab;
    }
}
