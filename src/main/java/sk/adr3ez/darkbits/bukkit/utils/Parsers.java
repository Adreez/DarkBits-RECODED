package sk.adr3ez.darkbits.bukkit.utils;

public class Parsers {

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isBoolean(String s) {
        try {
            Boolean.parseBoolean(s);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

}
