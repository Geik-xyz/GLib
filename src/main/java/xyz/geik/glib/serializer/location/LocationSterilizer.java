package xyz.geik.glib.serializer.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationSterilizer {

    public LocationSterilizer() {
    }

    /**
     * Converting string to location
     *
     * @param s
     * @return
     */
    public Location getLocation(String s, String spliter) {

        if (s == null || s.trim() == "") {

            return null;

        }

        final String[] parts = s.split(spliter);

        if (parts.length == 6) {

            World w = Bukkit.getServer().getWorld(parts[0]);

            double x = Double.parseDouble(parts[1]);

            double y = Double.parseDouble(parts[2]);

            double z = Double.parseDouble(parts[3]);

            float yaw = Float.parseFloat(parts[4]);

            float pitch = Float.parseFloat(parts[5]);

            return new Location(w, x, y, z, yaw, pitch);

        }
        return null;

    }

    /**
     * Converting Location to String
     *
     * @param loc
     * @return
     */
    public String getString(Location loc, String parser) {
        return loc.getWorld().getName() + parser + loc.getX() + parser + loc.getY() + parser + loc.getZ() + parser + loc.getYaw() + parser + loc.getPitch();
    }
}