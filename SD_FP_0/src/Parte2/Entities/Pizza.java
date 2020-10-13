package Parte2.Entities;

import Parte2.Enums.PizzaSize;
import Parte2.Enums.Units;

public class Pizza {
    private final int MAX_INGREDIENTS = 5;
    private String code;
    private String name;
    private String description;
    private double price;
    private PizzaSize size;
    private int nIngredients = 0;
    private Ingredients[] ingredients;
    private int[] quantidades;

    /**
     * Método construtor
     * @param code
     * @param name
     * @param description
     * @param price
     * @param size
     */
    public Pizza(String code, String name, String description, double price, PizzaSize size) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
        this.ingredients = new Ingredients[MAX_INGREDIENTS];
        this.quantidades = new int[MAX_INGREDIENTS];
    }

    /**
     * Adicionar ingrediente
     * @param ingredient ingrediente
     * @param quantidade quantidade
     */
    public void addIngredient(Ingredients ingredient, int quantidade) {
        addIngred(ingredient, quantidade);
    }

    private void addIngred(Ingredients ingredient, int quantidade) {
        if (nIngredients == MAX_INGREDIENTS) {
            System.out.println("Máximo de ingredientes atingido!!!");
            return;
        }

        this.ingredients[nIngredients] = ingredient;
        this.quantidades[nIngredients] = quantidade;
        this.nIngredients++;
    }

    /**
     * Remover ingrediente
     * @param code codigo do ingrediente
     */
    public void removeIngrediente(String code) {
        if (findByCode(code) == -1) {
            System.out.println("Missing ingredient");
        } else {
            remIngred(findByCode(code));
            System.out.println("Ingrediente removido!!");
        }
    }

    private void remIngred(int id) {
        for (int i = id; i < this.ingredients.length - 1; i++) {
            this.ingredients[i] = this.ingredients[i + 1];
            this.quantidades[i] = this.quantidades[i + 1];
        }

        this.quantidades[nIngredients - 1] = 0;
        this.ingredients[nIngredients - 1] = null;
        this.nIngredients--;
    }

    private int findByCode(String code) {
        for (int i = 0; i < MAX_INGREDIENTS; i++) {
            if (this.ingredients[i].getCode().equals(code)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Adicionar quantidade ao ingrediente
     * @param code ingrediente code
     * @param quantity quantidade
     */
    public void addIngredientQuantity(String code, int quantity) {
        addQuantity(code, quantity);
    }

    private void addQuantity(String code, int quantity){
        int id = findByCode(code);
        this.quantidades[id] += quantity;
    }

    public void removeIngredientQuantity(String code, int quantity) {
        removeQuantity(code,quantity);
    }

    private void removeQuantity(String code, int quantity){
        int id = findByCode(code);

        if(this.quantidades[id] - quantity > 0){
            this.quantidades[id] -= quantity;
        } else {
            System.out.println("A quantidade é 0 e não pode ser negativa");
        }
    }

    /**
     * Mostrar ingredientes
     * @return
     */
    private String showIngredients() {
        String text = "";

        for (int i = 0; i < nIngredients; i++) {
            if (this.ingredients[i] != null) {
                text += this.ingredients[i].toString() +
                        ", quantidade= " + this.quantidades[i] + " " +
                        Units.UnitToString(this.ingredients[i].getMeasure()) + "}";
            }
        }

        return text;
    }

    /**
     * Obter calorias da pizza
     * @return calorias da pizza
     */
    public int getPizzaKcal() {
        return getKcal();
    }

    private int getKcal() {
        int totalkcal = 0;

        for (int i = 0; i < this.ingredients.length; i++) {
            if (this.ingredients[i] != null) {
                totalkcal += (this.ingredients[i].getKcal() * this.quantidades[i]);
            }

        }
        return totalkcal;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "code='" + code + '\'' +
                ", name=" + name +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", size=" + PizzaSize.PizzaSizeToString(size) +
                ", nIngredients=" + nIngredients +
                ", Calorias da Pizza=" + getKcal() + "Kcal" +
                "\n" + ", ingredients=" + showIngredients() +
                '}';
    }
}
