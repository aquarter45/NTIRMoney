package me.aq.plugin.ntirmoney;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class EconomyCore implements Economy {

    private NTIRMoney plugin = NTIRMoney.getPlugin();

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        String pluginName = "NTIRMoney";
        return pluginName;
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double v) {
        return null;
    }

    @Override
    public String currencyNamePlural() {
        return null;
    }

    @Override
    public String currencyNameSingular() {
        return null;
    }

    @Override
    public boolean hasAccount(String uuid) {

        Player p =Bukkit.getPlayer(uuid);
        if(plugin.getServer().getMotd().equals("eco")){
            return plugin.data.hasEcoAccount(p);
        }
        return plugin.data.hasMainAccount(p);
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {

        Player p = offlinePlayer.getPlayer();

        if(plugin.getServer().getMotd().equals("eco")){
            return plugin.data.hasEcoAccount(p);
        }
        return plugin.data.hasMainAccount(p);
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        return true;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        return true;
    }

    @Override
    public double getBalance(String uuid) {

        if(plugin.getServer().getMotd().equals("Eco")){
            return plugin.data.GETPoint(uuid);
        }

        return plugin.data.GETMoney(uuid);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {

        String uuid = offlinePlayer.getPlayer().getUniqueId().toString();

        if(plugin.getServer().getMotd().equals("Eco")){
            return plugin.data.GETPoint(uuid);
        }

        return plugin.data.GETMoney(uuid);
    }

    @Override
    public double getBalance(String uuid, String s1) {

        if(plugin.getServer().getMotd().equals("Eco")){
            return plugin.data.GETPoint(uuid);
        }

        return plugin.data.GETMoney(uuid);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        String uuid = offlinePlayer.getPlayer().getUniqueId().toString();

        if(plugin.getServer().getMotd().equals("Eco")){
            return plugin.data.GETPoint(uuid);
        }

        return plugin.data.GETMoney(uuid);
    }

    @Override
    public boolean has(String s, double v) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return false;
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        return false;
    }

    @Override
    public EconomyResponse withdrawPlayer(String uuid, double amount) {

        Player p = Bukkit.getServer().getPlayer(uuid);

        if(plugin.getServer().getMotd().equals("Eco")) {
            if (plugin.data.hasEcoAccount(p)) {
                if (amount < plugin.data.GETPoint(p.getUniqueId().toString())) {
                    plugin.data.withdrawPoint(amount, p.getUniqueId().toString());
                    return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                            EconomyResponse.ResponseType.SUCCESS, "你已花費" + amount + "點");
                }
                return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()), EconomyResponse.ResponseType.FAILURE, "餘額不足!");
            }
        }

            if(plugin.data.hasMainAccount(p)){
                if(amount < plugin.data.GETMoney(p.getUniqueId().toString())) {
                    plugin.data.withdrawMoney(amount, p.getUniqueId().toString());
                    return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                            EconomyResponse.ResponseType.SUCCESS, "你已花費" + amount + "元");
                }
                return new EconomyResponse(amount,plugin.data.GETMoney(p.getUniqueId().toString()), EconomyResponse.ResponseType.FAILURE,"餘額不足!");
            }

        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double amount) {

        Player p = offlinePlayer.getPlayer();

        if(plugin.getServer().getMotd().equals("Eco")) {
            if (plugin.data.hasEcoAccount(p)) {
                if (amount < plugin.data.GETPoint(p.getUniqueId().toString())) {
                    plugin.data.withdrawPoint(amount, p.getUniqueId().toString());
                    return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                            EconomyResponse.ResponseType.SUCCESS, "你已花費" + amount + "點");
                }
                return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()), EconomyResponse.ResponseType.FAILURE, "餘額不足!");
            }
        }

            if(plugin.data.hasMainAccount(p)){
                if(amount < plugin.data.GETMoney(p.getUniqueId().toString())) {
                    plugin.data.withdrawMoney(amount, p.getUniqueId().toString());
                    return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                            EconomyResponse.ResponseType.SUCCESS, "你已花費" + amount + "元");
                }
                return new EconomyResponse(amount,plugin.data.GETMoney(p.getUniqueId().toString()), EconomyResponse.ResponseType.FAILURE,"餘額不足!");
            }

            return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(String uuid, String s1, double amount) {
        Player p = Bukkit.getServer().getPlayer(uuid);

        if(plugin.getServer().getMotd().equals("Eco")) {
            if (plugin.data.hasEcoAccount(p)) {
                if (amount < plugin.data.GETPoint(p.getUniqueId().toString())) {
                    plugin.data.withdrawPoint(amount, p.getUniqueId().toString());
                    return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                            EconomyResponse.ResponseType.SUCCESS, "你已花費" + amount + "點");
                }
                return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()), EconomyResponse.ResponseType.FAILURE, "餘額不足!");
            }
        }

            if(plugin.data.hasMainAccount(p)){
                if(amount < plugin.data.GETMoney(p.getUniqueId().toString())) {
                    plugin.data.withdrawMoney(amount, p.getUniqueId().toString());
                    return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                            EconomyResponse.ResponseType.SUCCESS, "你已花費" + amount + "元");
                }
                return new EconomyResponse(amount,plugin.data.GETMoney(p.getUniqueId().toString()), EconomyResponse.ResponseType.FAILURE,"餘額不足!");
            }


        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double amount) {
        Player p = offlinePlayer.getPlayer();

        if(plugin.getServer().getMotd().equals("Eco")) {
            if (plugin.data.hasEcoAccount(p)) {
                if (amount < plugin.data.GETPoint(p.getUniqueId().toString())) {
                    plugin.data.withdrawPoint(amount, p.getUniqueId().toString());
                    return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                            EconomyResponse.ResponseType.SUCCESS, "你已花費" + amount + "點");
                }
                return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()), EconomyResponse.ResponseType.FAILURE, "餘額不足!");
            }
        }

        if(plugin.data.hasMainAccount(p)){
            if(amount < plugin.data.GETMoney(p.getUniqueId().toString())) {
                plugin.data.withdrawMoney(amount, p.getUniqueId().toString());
                return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                        EconomyResponse.ResponseType.SUCCESS, "你已花費" + amount + "元");
            }
            return new EconomyResponse(amount,plugin.data.GETMoney(p.getUniqueId().toString()), EconomyResponse.ResponseType.FAILURE,"餘額不足!");
        }

        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String uuid, double amount) {
        Player p = Bukkit.getServer().getPlayer(uuid);

        if(plugin.getServer().getMotd().equals("Eco")){
            if(plugin.data.hasEcoAccount(p)){
                   plugin.data.addPoint(amount, p.getUniqueId().toString());
                   return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                           EconomyResponse.ResponseType.SUCCESS, "你已獲得" + amount + "點");
            }

            if(plugin.data.hasMainAccount(p)){
                plugin.data.addPoint(amount, p.getUniqueId().toString());
                return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                        EconomyResponse.ResponseType.SUCCESS, "你已獲得" + amount + "元");

            }

        }

        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double amount) {
        Player p = offlinePlayer.getPlayer();

        if(plugin.getServer().getMotd().equals("Eco")) {
            if (plugin.data.hasEcoAccount(p)) {
                plugin.data.addPoint(amount, p.getUniqueId().toString());
                return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                        EconomyResponse.ResponseType.SUCCESS, "你已獲得" + amount + "點");
            }
        }

        if(plugin.data.hasMainAccount(p)){
            plugin.data.addMoney(amount, p.getUniqueId().toString());
            return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                    EconomyResponse.ResponseType.SUCCESS, "你已獲得" + amount + "元");

        }

        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String uuid, String s1, double amount) {
        Player p = Bukkit.getServer().getPlayer(uuid);

        if(plugin.getServer().getMotd().equals("Eco")){
            if(plugin.data.hasEcoAccount(p)){
                plugin.data.addPoint(amount, p.getUniqueId().toString());
                return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                        EconomyResponse.ResponseType.SUCCESS, "你已獲得" + amount + "點");
            }

            if(plugin.data.hasMainAccount(p)){
                plugin.data.addMoney(amount, p.getUniqueId().toString());
                return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                        EconomyResponse.ResponseType.SUCCESS, "你已獲得" + amount + "元");
            }

        }

        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double amount){
        Player p = offlinePlayer.getPlayer();

        if(plugin.getServer().getMotd().equals("Eco")) {
            if (plugin.data.hasEcoAccount(p)) {
                plugin.data.addPoint(amount, p.getUniqueId().toString());
                return new EconomyResponse(amount, plugin.data.GETPoint(p.getUniqueId().toString()),
                        EconomyResponse.ResponseType.SUCCESS, "你已獲得" + amount + "點");
            }
        }

        if(plugin.data.hasMainAccount(p)){
            plugin.data.addMoney(amount, p.getUniqueId().toString());
            return new EconomyResponse(amount, plugin.data.GETMoney(p.getUniqueId().toString()),
                    EconomyResponse.ResponseType.SUCCESS, "你已獲得" + amount + "元");
        }

        return null;
    }


    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String s) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return false;
    }
}