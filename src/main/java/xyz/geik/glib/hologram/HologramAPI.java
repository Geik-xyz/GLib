package xyz.geik.glib.hologram;

import de.leonhard.storage.Json;
import xyz.geik.glib.GLibAPI;
import xyz.geik.glib.file.FileRegisterUtil;
import xyz.geik.glib.hologram.perma.PermaHologramAPI;
import xyz.geik.glib.hologram.perma.workers.HologramObject;
import xyz.geik.glib.hologram.temp.TempHoloAPI;
import xyz.geik.glib.hologram.temp.workers.TempHologram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HologramAPI {

    private static List<TempHologram> tempHolos = new ArrayList<TempHologram>();
    private static HashMap<String, HologramObject> holos = new HashMap<String, HologramObject>();

    public HologramAPI() {
        FileRegisterUtil.hologramData = new Json("holograms.json", "plugins/" + GLibAPI.getInstance().getDescription().getName(), GLibAPI.getInstance().getResource("holograms.json"));
    }

    /**
     * @TempHolo storage
     */

    public static List<TempHologram> getTempHoloList() {
        return tempHolos;
    }

    /**
     * @Holos storage
     */

    public static HashMap<String, HologramObject> getHoloList() {
        return holos;
    }

    /**
     * @return
     * @TEMP HoloManager
     */
    public static TempHoloAPI getTempHoloManager() {
        return new TempHoloAPI();
    }

    /**
     * @return
     * @PERMA HoloManager
     */
    public static PermaHologramAPI getPermaHoloManager() {
        return new PermaHologramAPI();
    }


}
