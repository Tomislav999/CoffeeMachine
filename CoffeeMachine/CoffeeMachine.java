package CoffeeMachine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    private final List<CoffeeType> coffeeTypes;

    public CoffeeMachine(int water, int milk, int beans, int cups, int money, List<CoffeeType> coffeeTypes) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
        this.coffeeTypes = coffeeTypes;
    }


    public void buyCoffee(CoffeeType coffeeType) {
        if (water < coffeeType.getWaterNeeded()) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < coffeeType.getMilkNeeded()) {
            System.out.println("Sorry, not enough milk!");
        } else if (beans < coffeeType.getBeansNeeded()) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            water -= coffeeType.getWaterNeeded();
            milk -= coffeeType.getMilkNeeded();
            beans -= coffeeType.getBeansNeeded();
            cups--;
            money += coffeeType.getPrice();
            System.out.println("I have enough resources, making you a " + coffeeType.getName());
        }
    }


    public void displayResources() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money\n");
    }


    public void fill(int addWater, int addMilk, int addBeans, int addCups) {
        water += addWater;
        milk += addMilk;
        beans += addBeans;
        cups += addCups;
    }


    public void takeMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }


    public void saveState(String filename, String adminUsername, String adminPassword) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.printf("%d; %d; %d; %d; %d\n", water, milk, beans, cups, money);
            writer.printf("%s; %s\n", adminUsername, adminPassword);

            for (CoffeeType coffeeType : coffeeTypes) {
                writer.printf("%s; %d; %d; %d; %d\n",
                        coffeeType.getName(),
                        coffeeType.getWaterNeeded(),
                        coffeeType.getMilkNeeded(),
                        coffeeType.getBeansNeeded(),
                        coffeeType.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }


    public static CoffeeMachine loadState(String filename) {
        int water = 0, milk = 0, beans = 0, cups = 0, money = 0;
        List<CoffeeType> coffeeTypes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String[] resourceData = reader.readLine().split("; ");
            water = Integer.parseInt(resourceData[0]);
            milk = Integer.parseInt(resourceData[1]);
            beans = Integer.parseInt(resourceData[2]);
            cups = Integer.parseInt(resourceData[3]);
            money = Integer.parseInt(resourceData[4]);

            reader.readLine();

            String coffeeLine;
            while ((coffeeLine = reader.readLine()) != null) {
                String[] coffeeData = coffeeLine.split("; ");
                String name = coffeeData[0];
                int waterNeeded = Integer.parseInt(coffeeData[1]);
                int milkNeeded = Integer.parseInt(coffeeData[2]);
                int beansNeeded = Integer.parseInt(coffeeData[3]);
                int price = Integer.parseInt(coffeeData[4]);
                coffeeTypes.add(new CoffeeType(name, waterNeeded, milkNeeded, beansNeeded, price));
            }
        } catch (IOException e) {
            System.out.println("Error loading state: " + e.getMessage());
            // Default initialization if file is not found
            coffeeTypes.add(new CoffeeType("Espresso", 250, 0, 16, 4));
            coffeeTypes.add(new CoffeeType("Latte", 350, 75, 20, 7));
            coffeeTypes.add(new CoffeeType("Cappuccino", 200, 100, 12, 6));
        }
        return new CoffeeMachine(water, milk, beans, cups, money, coffeeTypes);
    }
}
