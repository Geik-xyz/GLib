package xyz.geik.glib.hologram.temp.workers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TempHoloReflection {
    protected String MinecraftVersion;

    public void setValue(Object obj, String name, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception exception) {
        }
    }

    public Object getValue(Object obj, String name) {
        try {
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception exception) {
            return null;
        }
    }

    public void sendPacket(Object packet, Player BukkitPlayer) {
        try {
            this.MinecraftVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",")
                    .split(",")[3];
            Object player = Class.forName("org.bukkit.craftbukkit." + this.MinecraftVersion + ".entity.CraftPlayer")
                    .cast(BukkitPlayer);
            Method handle = player.getClass().getMethod("getHandle", new Class[0]);
            Object entityPlayer = handle.invoke(player, new Object[0]);
            Field playerConnection = entityPlayer.getClass().getField("playerConnection");
            Object playerConnection2 = playerConnection.get(entityPlayer);
            playerConnection2.getClass()
                    .getMethod("sendPacket",
                            new Class[]{Class.forName("net.minecraft.server." + this.MinecraftVersion + ".Packet")})
                    .invoke(playerConnection2, new Object[]{packet});
        } catch (IllegalAccessException | IllegalArgumentException | java.lang.reflect.InvocationTargetException
                 | NoSuchFieldException | SecurityException | NoSuchMethodException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void sendPacket(Object packet) {
        for (Player player : Bukkit.getOnlinePlayers())
            sendPacket(packet, player);
    }

    public void sendPacketWithoutPlayer(Object packet, Player p) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != p)
                sendPacket(packet, player);
        }
    }
}