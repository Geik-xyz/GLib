package xyz.geik.glib.hologram.perma;

import org.bukkit.Location;
import xyz.geik.glib.file.FileRegisterUtil;
import xyz.geik.glib.hologram.HologramAPI;
import xyz.geik.glib.hologram.perma.workers.HologramObject;
import xyz.geik.glib.utils.ColorAPI;

public class PermaHologramAPI {

    public PermaHologramAPI() {
    }

    /**
     * Creates the hologram and {@link UUID}
     * TODO DataSave holograms.yml
     *
     * @param location
     * @param message
     * @return
     */
    public String createHologram(Location location, String message) {
        HologramObject hologram = new HologramObject(location, message);
        FileRegisterUtil.hologramData.set(hologram.getUUID() + ".location", location.serialize());
        FileRegisterUtil.hologramData.set(hologram.getUUID() + ".text", message);
        HologramAPI.getHoloList().put(hologram.getUUID(), hologram);
        return hologram.getUUID();
    }

    public String installHologram(String uuid) {
        Location location = Location.deserialize(FileRegisterUtil.hologramData.getMapParameterized(uuid + ".location"));

        HologramObject hologram = new HologramObject(location, FileRegisterUtil.hologramData.getString(uuid + ".text"), uuid);

        HologramAPI.getHoloList().put(uuid, hologram);

        return uuid;

    }

    public void updateHologramName(String uuid, String text) {
        HologramObject holo = HologramAPI.getHoloList().get(uuid);
        String displayName = ColorAPI.colorize(text);
        holo.getArmorStand().setCustomName(displayName);
        holo.setName(text);

        FileRegisterUtil.hologramData.set(uuid + ".text", text);
    }

    /**
     * Remove hologram according to {@link UUID}
     *
     * @param uuid
     */
    public void removeHologram(String uuid) {
        HologramObject hologram = HologramAPI.getHoloList().get(uuid);
        hologram.remove();
    }

}