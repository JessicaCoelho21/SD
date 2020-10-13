package Parte2.Enums;

public enum Units {
    GRAMS, LITERS, UNITS;

    public static String UnitToString(Units unit){
        switch (unit){
            case GRAMS:
                return "grams";
            case LITERS:
                return "liters";
            case UNITS:
                return "units";
            default:
                return "units";
        }
    }
}
