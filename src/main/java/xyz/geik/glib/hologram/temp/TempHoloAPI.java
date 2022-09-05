package xyz.geik.glib.hologram.temp;

import java.util.HashMap;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.geik.glib.GLibAPI;
import xyz.geik.glib.hologram.HologramAPI;
import xyz.geik.glib.hologram.temp.workers.TempHologram;
import xyz.geik.glib.utils.ColorAPI;

import java.util.List;

public class TempHoloAPI {

    public TempHoloAPI() {
    }

    /**
     * Creates temporary hologram
     *
     */
    public TempHologram createTempHologram(List<String> holoText, Player player, int seconds) {

        TempHologram hologram = new TempHologram(GLibAPI.getInstance(), player, 3, createHoloText(holoText, player));

        HologramAPI.getTempHoloList().add(hologram);

        Bukkit.getScheduler().runTaskLaterAsynchronously(GLibAPI.getInstance(), () -> {

            hologram.delete();

            HologramAPI.getTempHoloList().remove(hologram);

        }, 20 * seconds);

        return hologram;
    }

    /**
     * Removes temporary hologram
     *
     * @param hologram
     */
    public void deleteTempHologram(TempHologram hologram) {
        hologram.delete();
    }

    /**
     * Creating holo text list
     *
     * @param lines
     * @param player
     * @return
     */
    public HashMap<String, Double> createHoloText(List<String> lines, Player player) {
        HashMap<String, Double> map = new HashMap<String, Double>();
        double doubleCalculator = 0.0;
        for (String line : lines) {
            map.put(ColorAPI.colorize(PlaceholderAPI.setPlaceholders(player, line)), doubleCalculator);
            doubleCalculator = doubleCalculator + 0.3;
        }
        return map;
    }

}
