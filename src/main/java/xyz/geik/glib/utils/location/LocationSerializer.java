package xyz.geik.glib.utils.location;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationSerializer {

    public LocationSerializer() {
    }

    /**
     * Converting string to location
     *
     * @param s
     * @return
     */
    public static Location getLocation(String s) {

        if (s == null || s.trim() == "") {

            return null;

        }

        final String[] parts = s.split("-");

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
    public static String getString(Location loc) {
        return loc.getWorld().getName() + "-" + loc.getX() + "-" + loc.getY() + "-" + loc.getZ() + "-" + loc.getYaw() + "-" + loc.getPitch();
    }
}
