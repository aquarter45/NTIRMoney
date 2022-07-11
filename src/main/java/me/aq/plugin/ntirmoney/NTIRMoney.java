package me.aq.plugin.ntirmoney;

import SQL.MySQL;
import SQL.PlayerDefault;
import SQL.SQLediter;
import me.aq.plugin.ntirmoney.Command.money;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import javax.print.DocFlavor;
import java.sql.SQLException;

public final class NTIRMoney extends JavaPlugin {

    private static NTIRMoney plugin;


    public MySQL SQL;
    public SQLediter data;
    public EconomyCore economyCore;
    private static Economy econ = null;
    private static Permission perms = null;

    public String prefix = ChatColor.DARK_GREEN + "[" + ChatColor.GOLD + "金錢" + ChatColor.DARK_GREEN + "]" + ChatColor.RESET + "";

    public static NTIRMoney getPlugin() {
        return plugin;
    }


    @Override
    public void onEnable() {
        this.plugin = this;

        this.SQL = new MySQL();
        this.data = new SQLediter();
        data.SQLGetter(this);
        economyCore = new EconomyCore();

        try {
            SQL.connect();
            data.createTable();

        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getLogger().info("DB not connected");
            Bukkit.getLogger().info("資料庫是該插件的必要功能");
            Bukkit.getLogger().info("DISABLING THE PLUGIN...");
            this.getServer().getPluginManager().disablePlugin(this);
        }

        if (!setupEconomy() ) {
            Bukkit.getLogger().info("Vault是該插件的必要功能");
            Bukkit.getLogger().info("DISABLING THE PLUGIN...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();

        getServer().getPluginManager().registerEvents(new PlayerDefault(),this);
        getCommand("money").setExecutor(new money());
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        getServer().getServicesManager().register(Economy.class, new EconomyCore(), this, ServicePriority.Highest);

        return true;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }
}
