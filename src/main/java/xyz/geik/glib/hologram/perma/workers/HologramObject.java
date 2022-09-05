package xyz.geik.glib.hologram.perma.workers;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import xyz.geik.glib.file.FileRegisterUtil;
import xyz.geik.glib.hologram.HologramAPI;
import xyz.geik.glib.utils.ColorAPI;

import java.util.UUID;

public class HologramObject {

    private String uuid;
    private Location location;
    private String name;
    private int entityID;
    private ArmorStand stand;

    /**
     * Object
     *
     * @param location
     * @param name
     */
    public HologramObject(Location location, String name) {

        this.location = location;

        this.name = name;

        spawn();

    }

    public HologramObject(Location location, String name, String uuid) {

        this.location = location;

        this.name = name;

        this.uuid = uuid;

        spawn();

    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get UUID of stand
     *
     * @return
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Get Location of Stand
     *
     * @return
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Get displayName
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get this EntityID
     *
     * @return
     */
    public int getEntityID() {
        return this.entityID;
    }

    /**
     * Get this ArmorStand
     *
     * @return
     */
    public ArmorStand getArmorStand() {
        return this.stand;
    }

    /**
     * Spawn this hologram
     */
    @SuppressWarnings("deprecation")
    public void spawn() {

        String uuid = this.uuid;

        if (uuid == null)
            uuid = UUID.randomUUID().toString();
        String displayName = ColorAPI.colorize(PlaceholderAPI.setPlaceholders(Bukkit.getOfflinePlayer("Geyik"), this.name));
        ArmorStand hologram = (ArmorStand) this.location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        hologram.setArms(false);
        hologram.setGravity(false);
        hologram.setCanPickupItems(false);
        hologram.setCustomName(displayName);
        hologram.setCustomNameVisible(true);
        hologram.setVisible(false);
        hologram.setSmall(true);
        hologram.setBasePlate(false);

        this.entityID = hologram.getEntityId();
        this.uuid = uuid;
        this.stand = hologram;

        HologramAPI.getHoloList().put(uuid, this);
    }

    /**
     * Removes this Hologram
     */
    public void remove() {
        HologramAPI.getHoloList().remove(this.uuid);
        this.getArmorStand().remove();
        FileRegisterUtil.hologramData.remove(uuid);
    }

}
