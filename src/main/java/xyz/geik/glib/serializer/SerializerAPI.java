package xyz.geik.glib.serializer;

import xyz.geik.glib.serializer.location.LocationSterilizer;

public class SerializerAPI {
    public static LocationSterilizer getLocSterilizer() {
        return new LocationSterilizer();
    }
}
