package Parte2.Entities;

import Parte2.Enums.Units;

public class Ingredients {
    private String code;
    private String name;
    private Units measure;
    private int kcal;

    public Ingredients(String code, String name, Units measure, int kcal) {
        this.code = code;
        this.name = name;
        this.measure = measure;
        this.kcal = kcal;
    }

    public String getCode() {
        return code;
    }

    public Units getMeasure() {
        return measure;
    }

    public int getKcal() {
        return kcal;
    }

    @Override
    public String toString() {
        return "\n" + "Ingredients{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", measure=" + Units.UnitToString(measure) +
                ", kcal=" + kcal;
    }
}
