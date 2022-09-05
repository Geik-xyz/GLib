package xyz.geik.glib.utils;

public class MathUtil {

    public MathUtil() {
    }

    /**
     * Check is String integer
     *
     * @param strNum
     * @return
     */
    public boolean isNumeric(String strNum) {

        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;

    }

    /**
     * Round double to 0.xx which is places
     *
     * @param value
     * @param places
     * @return
     */
    public String roundDouble(double value, int places) {

        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);

        value = value * factor;

        long tmp = Math.round(value);

        double result = (double) tmp / factor;

        return String.valueOf(result);

    }

}