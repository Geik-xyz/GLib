package xyz.geik.glib.hologram.temp.workers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TempHologram {
    UUID player;

    ArrayList<TempHoloNPC> NPCs = new ArrayList<TempHoloNPC>();

    int UpdateTask;

    double distanceToPlayer = 4.0D;

    Plugin Instance;

    public TempHologram(Plugin Instance, Player player, double distanceToPlayer,
                        HashMap<String, Double> textWithHeigth) {
        this.player = player.getUniqueId();
        this.distanceToPlayer = distanceToPlayer;
        this.Instance = Instance;
        for (Map.Entry<String, Double> entry : textWithHeigth.entrySet())
            this.NPCs.add(new TempHoloNPC(player, distanceToPlayer, entry.getKey(),
                    ((Double) entry.getValue()).doubleValue()));
        spawn();
    }

    public void setDistanceToPlayer(double distanceToPlayer) {
        this.distanceToPlayer = distanceToPlayer;
        for (TempHoloNPC npc : this.NPCs)
            npc.setDistance(distanceToPlayer);
    }

    public double getDistanceToPlayer() {
        return this.distanceToPlayer;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.player);
    }

    public void spawn() {
        for (TempHoloNPC npc : this.NPCs)
            npc.spawn();
        this.UpdateTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.Instance, new Runnable() {
            public void run() {
                for (TempHoloNPC npc : TempHologram.this.NPCs)
                    npc.teleport();
            }
        }, 1L, 1L);
    }

    public void delete() {
        for (TempHoloNPC npc : this.NPCs)
            npc.destroy();
        Bukkit.getScheduler().cancelTask(this.UpdateTask);
    }
}
