package CoffeeMachine;

public class CoffeeType {
    private final String name;
    private final int waterNeeded;
    private final int milkNeeded;
    private final int beansNeeded;
    private final int price;

    public CoffeeType(String name, int waterNeeded, int milkNeeded, int beansNeeded, int price) {
        this.name = name;
        this.waterNeeded = waterNeeded;
        this.milkNeeded = milkNeeded;
        this.beansNeeded = beansNeeded;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    public int getMilkNeeded() {
        return milkNeeded;
    }

    public int getBeansNeeded() {
        return beansNeeded;
    }

    public int getPrice() {
        return price;
    }
}
