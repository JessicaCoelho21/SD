package Parte2;

import Parte2.Entities.Ingredients;
import Parte2.Entities.Pizza;
import Parte2.Enums.PizzaSize;
import Parte2.Enums.Units;

public class SD_FP_0_p2_ex1 {
    public static void main(String[] args) {
        Ingredients tomate = new Ingredients("01","Tomate", Units.GRAMS, 100);
        Ingredients chourico = new Ingredients("02","Chouriço", Units.UNITS, 200);
        Ingredients queijo = new Ingredients("03", "Queijo", Units.GRAMS, 50);
        Ingredients molho = new Ingredients("04", "Molho", Units.LITERS, 300);
        Ingredients ananas = new Ingredients("05", "Ananás", Units.GRAMS, 120);
        Ingredients atum = new Ingredients("06","Atum", Units.GRAMS,30);

        Pizza pizza = new Pizza("p-01","Havaiana",
                "Pizza com ananás e presunto",5.00, PizzaSize.BIG);

        pizza.addIngredient(tomate,2);
        pizza.addIngredient(chourico,3);
        pizza.addIngredient(queijo,6);
        pizza.addIngredient(molho,4);
        pizza.addIngredient(ananas,5);
        pizza.addIngredient(atum,7);

        System.out.printf("");
        System.out.println(pizza.toString());

        pizza.removeIngrediente("04");
        pizza.removeIngrediente("05");
        pizza.removeIngrediente("01");
        System.out.println("");

        //pizza.addIngredientQuantity("02", 100);

        pizza.removeIngredientQuantity("02",4);
        System.out.println(pizza.toString());

        //System.out.println("Calorias da pizza = " + pizza.getPizzaKcal() + "Kcal");
    }
}
