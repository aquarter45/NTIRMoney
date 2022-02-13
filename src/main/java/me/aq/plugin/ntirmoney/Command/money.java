package me.aq.plugin.ntirmoney.Command;

import me.aq.plugin.ntirmoney.NTIRMoney;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class money implements CommandExecutor {

    private NTIRMoney plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        plugin = NTIRMoney.getPlugin();
        if(args.length == 0) {

            if (plugin.getServer().getMotd().equalsIgnoreCase("Eco") ) {
                if (!plugin.data.hasEcoAccount(p)) {
                    return false;
                }
                p.sendMessage(plugin.prefix + ChatColor.LIGHT_PURPLE + "你的剩餘餘額:" + ChatColor.GRAY + ":"
                        + ChatColor.RED + plugin.data.GETPoint(p.getUniqueId().toString()));
                return true;
            }

            if (!plugin.data.hasMainAccount(p)) {
                return false;
            }

            p.sendMessage(plugin.prefix + ChatColor.LIGHT_PURPLE + "你的剩餘餘額:" + ChatColor.GRAY + ":"
                    + ChatColor.RED + plugin.data.GETMoney(p.getUniqueId().toString()));

            return true;
        }

        if(args.length == 1){

            Player target = Bukkit.getOfflinePlayer(args[0]).getPlayer();

            if (target == null) {
                p.sendMessage(plugin.prefix + ChatColor.RED + "該玩家並不存在");
                return false;
            }

            if (plugin.getServer().getMotd().equalsIgnoreCase("Eco")) {
                if (!plugin.data.hasEcoAccount(p)) {
                    return false;
                }
                p.sendMessage(plugin.prefix + ChatColor.LIGHT_PURPLE + "該玩家的剩餘餘額:" + ChatColor.GRAY + ":"
                        + ChatColor.RED + plugin.data.GETPoint(target.getUniqueId().toString()));

                return true;
            }

            if (!plugin.data.hasMainAccount(p)) {
                return false;
            }

            p.sendMessage(plugin.prefix + ChatColor.LIGHT_PURPLE + "該玩家的剩餘餘額:" + ChatColor.GRAY + ":"
                    + ChatColor.RED + plugin.data.GETMoney(target.getUniqueId().toString()));

            return true;
        }

        if(args.length == 3){

            Player target = Bukkit.getOfflinePlayer(args[1]).getPlayer();
            if(args[0].equalsIgnoreCase("pay")) {


                if (target == null) {
                    p.sendMessage(plugin.prefix + ChatColor.RED + "該玩家並不存在");
                    return false;
                }

                if (plugin.getServer().getMotd() == "Eco") {
                    if (!plugin.data.hasEcoAccount(p) | !plugin.data.hasEcoAccount(target)) {
                        return false;
                    }

                    plugin.data.withdrawPoint(Double.parseDouble(args[2]), p.getUniqueId().toString());
                    plugin.data.addPoint(Double.parseDouble(args[2]), target.getUniqueId().toString());
                    p.sendMessage(plugin.prefix + ChatColor.GREEN + "你成功轉帳" + ChatColor.RED + args[2] + ChatColor.GREEN +"給" + p.getDisplayName() );
                }


                if (!plugin.data.hasMainAccount(p) | !plugin.data.hasMainAccount(target)) {
                    return false;
                }

                plugin.data.withdrawMoney(Double.parseDouble(args[2]), p.getUniqueId().toString());
                plugin.data.addMoney(Double.parseDouble(args[2]), target.getUniqueId().toString());
                p.sendMessage(plugin.prefix + ChatColor.GREEN + "你成功轉帳" + ChatColor.RED + args[2] + ChatColor.GREEN +"給" + p.getDisplayName() );
            }

            if(args[0].equalsIgnoreCase("set")){
                if(!p.hasPermission("NTIR.Admin")){
                    p.sendMessage(plugin.prefix + ChatColor.RED + "你不具操作此的權限!");
                    return false;
                }
                if (target == null) {
                    p.sendMessage(plugin.prefix + ChatColor.RED + "該玩家並不存在");
                    return false;
                }

                if (plugin.getServer().getMotd() == "Eco") {
                }

                if (!plugin.data.hasMainAccount(target)) {
                    return false;
                }

                plugin.data.SETMoney(Double.parseDouble(args[2]), target.getUniqueId().toString());
                p.sendMessage(plugin.prefix + ChatColor.GREEN + "你成功將玩家" + ChatColor.RED + target.getDisplayName() + ChatColor.GREEN +"的餘額設置成"
                        + plugin.data.GETMoney(target.getUniqueId().toString()) );
                return true;

            }

            if(args[0].equalsIgnoreCase("add")){
                if(!p.hasPermission("NTIR.Admin")){
                    p.sendMessage(plugin.prefix + ChatColor.RED + "你不具操作此的權限!");
                    return false;
                }
                if (target == null) {
                    p.sendMessage(plugin.prefix + ChatColor.RED + "該玩家並不存在");
                    return false;
                }

                if (plugin.getServer().getMotd() == "Eco") {
                }

                if (!plugin.data.hasMainAccount(target)) {
                    return false;
                }

                plugin.data.addMoney(Double.parseDouble(args[2]), target.getUniqueId().toString());
                p.sendMessage(plugin.prefix + ChatColor.GREEN + "你成功將玩家" + ChatColor.RED + target.getDisplayName() + ChatColor.GREEN +"的餘額增加成"
                        + plugin.data.GETMoney(target.getUniqueId().toString()) );
                return true;

            }
        }

            return false;

    }

}
