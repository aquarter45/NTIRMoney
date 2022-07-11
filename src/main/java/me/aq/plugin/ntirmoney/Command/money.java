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

            p.sendMessage(plugin.prefix + ChatColor.LIGHT_PURPLE + "該玩家的剩餘餘額" + ChatColor.GRAY + ":"
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

                if (!plugin.data.hasMainAccount(p) | !plugin.data.hasMainAccount(target)) {
                    return false;
                }

                //小數點偵測
                if(args[2].contains(".")){

                    String[] afterDot = args[2].split(".");

                    if(afterDot.length >2){
                        p.sendMessage(plugin.prefix + ChatColor.RED + "請輸入有效的轉帳金額!");
                        return false;
                    }

                    int length = afterDot[2].length();

                    if(length > 2){
                        p.sendMessage(plugin.prefix + ChatColor.RED + "小數點至多兩位!");
                        return false;
                    }

                }

                Double withDrawMoney = Double.parseDouble(args[2]);


                if(plugin.data.GETMoney(p.getUniqueId().toString()) < withDrawMoney){
                    p.sendMessage(plugin.prefix + ChatColor.RED + "你的餘額不足!");
                    return false;
                }

                plugin.data.withdrawMoney(withDrawMoney, p.getUniqueId().toString());
                plugin.data.addMoney(withDrawMoney, target.getUniqueId().toString());
                p.sendMessage(plugin.prefix + ChatColor.GREEN + "你成功轉帳" + ChatColor.RED + withDrawMoney + ChatColor.LIGHT_PURPLE + target.getDisplayName() );
                target.sendMessage(plugin.prefix + ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.GREEN + "轉帳了" + withDrawMoney + ChatColor.GREEN +"給你");
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
