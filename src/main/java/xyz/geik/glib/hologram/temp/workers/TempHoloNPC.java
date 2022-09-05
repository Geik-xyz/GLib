package xyz.geik.glib.hologram.temp.workers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TempHoloNPC extends TempHoloReflection {
    int ID;

    double Ydif;

    Player player;

    String text;

    double distance;

    public TempHoloNPC(Player player, double distance, String message, double Ydif) {
        this.MinecraftVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        this.player = player;
        this.Ydif = Ydif;
        this.text = message;
        this.distance = distance;
    }

    public void spawn() {
        try {
            Object CraftWorld = Class.forName("org.bukkit.craftbukkit." + this.MinecraftVersion + ".CraftWorld")
                    .cast(this.player.getLocation().getWorld());
            Object handle = CraftWorld.getClass().getMethod("getHandle", new Class[0]).invoke(CraftWorld,
                    new Object[0]);
            Object EntityArmorStand = Class
                    .forName("net.minecraft.server." + this.MinecraftVersion + ".EntityArmorStand")
                    .getConstructor(
                            new Class[]{Class.forName("net.minecraft.server." + this.MinecraftVersion + ".World")})
                    .newInstance(new Object[]{handle});

            EntityArmorStand.getClass().getMethod("setHealth", new Class[]{float.class}).invoke(EntityArmorStand,
                    new Object[]{Float.valueOf(1.0F)});
            EntityArmorStand.getClass()
                    .getMethod("setLocation",
                            new Class[]{double.class, double.class, double.class, float.class, float.class})
                    .invoke(EntityArmorStand,
                            new Object[]{
                                    Double.valueOf(this.player.getLocation().getX()
                                            + this.player.getLocation().getDirection().multiply(this.distance).getX()),
                                    Double.valueOf(this.player.getLocation().getY()
                                            + this.player.getLocation().getDirection().multiply(this.distance).getY()
                                            - this.Ydif),
                                    Double.valueOf(this.player.getLocation().getZ()
                                            + this.player.getLocation().getDirection().multiply(this.distance).getZ()),
                                    Long.valueOf(System.currentTimeMillis() / 10L % 360L), Integer.valueOf(0)});
            EntityArmorStand.getClass().getMethod("setCustomNameVisible", new Class[]{boolean.class})
                    .invoke(EntityArmorStand, new Object[]{Boolean.valueOf(true)});
            EntityArmorStand.getClass().getMethod("setCustomName", new Class[]{String.class})
                    .invoke(EntityArmorStand, new Object[]{this.text});
            EntityArmorStand.getClass().getMethod("setInvisible", new Class[]{boolean.class})
                    .invoke(EntityArmorStand, new Object[]{Boolean.valueOf(true)});
            this.ID = ((Integer) EntityArmorStand.getClass().getMethod("getId", new Class[0]).invoke(EntityArmorStand,
                    new Object[0])).intValue();
            Object PacketPlayOutSpawnEntityLiving = Class
                    .forName("net.minecraft.server." + this.MinecraftVersion + ".PacketPlayOutSpawnEntityLiving")
                    .getConstructor(new Class[]{
                            Class.forName("net.minecraft.server." + this.MinecraftVersion + ".EntityLiving")})
                    .newInstance(new Object[]{EntityArmorStand});
            sendPacket(PacketPlayOutSpawnEntityLiving, this.player);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                 | java.lang.reflect.InvocationTargetException | NoSuchMethodException | SecurityException
                 | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @SuppressWarnings("deprecation")
    public void teleport() {
        Object packet = null;
        try {
            packet = ClassLoader.getSystemClassLoader()
                    .loadClass("net.minecraft.server." + this.MinecraftVersion + ".PacketPlayOutEntityTeleport")
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        setValue(packet, "a", Integer.valueOf(this.ID));
        setValue(packet, "b", Integer.valueOf((int) Math.floor((this.player.getLocation().getX()
                + this.player.getLocation().getDirection().multiply(this.distance).getX()) * 32.0D)));
        setValue(packet, "c",
                Integer.valueOf((int) Math.floor((this.player.getLocation().getY()
                        + this.player.getLocation().getDirection().multiply(this.distance).getY() - this.Ydif)
                        * 32.0D)));
        setValue(packet, "d", Integer.valueOf((int) Math.floor((this.player.getLocation().getZ()
                + this.player.getLocation().getDirection().multiply(this.distance).getZ()) * 32.0D)));
        setValue(packet, "e", Byte.valueOf(getFixRotation((System.currentTimeMillis() / 10L % 360L))));
        setValue(packet, "f", Byte.valueOf(getFixRotation(0.0D)));
        sendPacket(packet, this.player);
    }

    @SuppressWarnings("deprecation")
    public void destroy() {
        Object packet = null;
        try {
            packet = ClassLoader.getSystemClassLoader()
                    .loadClass("net.minecraft.server." + this.MinecraftVersion + ".PacketPlayOutEntityDestroy")
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
                 | SecurityException e1) {
            e1.printStackTrace();
        }
        int[] id = {this.ID};
        setValue(packet, "a", id);
        sendPacket(packet, this.player);
    }

    public byte getFixRotation(double pitch2) {
        return (byte) (int) (pitch2 * 256.0D / 360.0D);
    }
}
