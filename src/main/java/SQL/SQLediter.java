package SQL;

import me.aq.plugin.ntirmoney.NTIRMoney;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLediter {

    private NTIRMoney plugin;

    public void SQLGetter(NTIRMoney plugin){
        this.plugin = plugin;

    }

    public void createTable(){

        PreparedStatement ps;
        PreparedStatement ps2;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS MainBalance "
                    + "(NAME VARCHAR(100), UUID VARCHAR(100), balance DOUBLE, PRIMARY KEY(NAME))");

            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ps2 = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS EcoPoint "
                    + "(NAME VARCHAR(100), UUID VARCHAR(100), point DOUBLE, PRIMARY KEY(NAME))");

            ps2.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createPlayer(Player p){
        if(!hasMainAccount(p)) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT INTO MainBalance (NAME,UUID,balance) VALUES(?,?,?)");
                ps.setString(1, p.getDisplayName());
                ps.setString(2, p.getUniqueId().toString());
                ps.setDouble(3, 200);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(!hasEcoAccount(p)) {
            try {
                PreparedStatement ps1 = plugin.SQL.getConnection().prepareStatement("INSERT INTO EcoPoint (NAME,UUID,point) VALUES(?,?,?)");
                ps1.setString(1, p.getDisplayName());
                ps1.setString(2, p.getUniqueId().toString());
                ps1.setDouble(3, 200);
                ps1.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void SETMoney(double money,String uuid){
        try {

            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE MainBalance SET balance=?  WHERE UUID=?");
            ps.setDouble(1,money);
            ps.setString(2,uuid);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addMoney(double money,String uuid){
        try {

            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE MainBalance SET balance=?  WHERE UUID=?");
            ps.setDouble(1,GETMoney(uuid) + money);
            ps.setString(2,uuid);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void withdrawMoney(double money,String uuid){
        try {

            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE MainBalance SET balance=?  WHERE UUID=?");
            ps.setDouble(1,GETMoney(uuid) - money);
            ps.setString(2,uuid);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void SETPoint(double money,String uuid){
        try {

            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE EcoPoint SET point=?  WHERE UUID=?");
            ps.setDouble(1,money);
            ps.setString(2,uuid);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addPoint(double money,String uuid){
        try {

            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE EcoPoint SET point=? WHERE UUID=?");
            ps.setDouble(1,GETPoint(uuid) + money);
            ps.setString(2,uuid);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void withdrawPoint(double money,String uuid){
        try {

            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE EcoPoint SET point=?  WHERE UUID=?");
            ps.setDouble(1,GETPoint(uuid) - money);
            ps.setString(2,uuid);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public double GETPoint(String uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT point FROM EcoPoint WHERE UUID=?");
            ps.setString(1,uuid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                double money = rs.getDouble("point");
                return money;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public double GETMoney(String uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT balance FROM MainBalance WHERE UUID=?");
            ps.setString(1,uuid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                double money = rs.getDouble("balance");
                return money;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public boolean hasMainAccount(Player p){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM MainBalance WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasEcoAccount(Player p){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT UUID FROM EcoPoint WHERE UUID=?");
            ps.setString(1,p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
