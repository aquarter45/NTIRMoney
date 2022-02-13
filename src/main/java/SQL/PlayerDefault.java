package SQL;

import me.aq.plugin.ntirmoney.NTIRMoney;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerDefault implements Listener {

    private NTIRMoney plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        this.plugin = NTIRMoney.getPlugin();
        plugin.data.createPlayer(e.getPlayer());

    }
}
