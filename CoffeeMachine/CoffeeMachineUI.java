package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachineUI {
    private final CoffeeMachine coffeeMachine;
    private final Scanner scanner;


    private final String adminUsername = "admin";
    private final String adminPassword = "admin123";

    public CoffeeMachineUI(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        while (true) {
            System.out.print("Write action (buy, login, exit): ");
            String action = scanner.next();

            if (action.equals("exit")) {
                break;
            }

            switch (action) {
                case "buy":
                    handleBuy();
                    break;
                case "login":
                    handleLogin();
                    break;
                default:
                    System.out.println("Unknown action");
            }
        }
    }

    private void handleBuy() {

        System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String choice = scanner.next();
        if (choice.equals("back")) return;

        CoffeeType selectedCoffee = null;
        switch (choice) {
            case "1":
                selectedCoffee = new CoffeeType("Espresso", 250, 0, 16, 4);
                break;
            case "2":
                selectedCoffee = new CoffeeType("Latte", 350, 75, 20, 7);
                break;
            case "3":
                selectedCoffee = new CoffeeType("Cappuccino", 200, 100, 12, 6);
                break;
            default:
                System.out.println("Unknown coffee type");
        }

        if (selectedCoffee != null) {
            coffeeMachine.buyCoffee(selectedCoffee);
        }
    }

    private void handleLogin() {

        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            adminMenu();
        } else {
            System.out.println("Wrong username or password");
        }
    }

    private void adminMenu() {

        while (true) {
            System.out.print("Write action (fill, remaining, take, exit): ");
            String action = scanner.next();

            if (action.equals("exit")) {
                break;
            }

            switch (action) {
                case "fill":
                    handleFill();
                    break;
                case "remaining":
                    coffeeMachine.displayResources();
                    break;
                case "take":
                    coffeeMachine.takeMoney();
                    break;
                default:
                    System.out.println("Unknown action");
            }
        }
    }

    private void handleFill() {

        System.out.print("Write how many ml of water you want to add: ");
        int addWater = scanner.nextInt();
        System.out.print("Write how many ml of milk you want to add: ");
        int addMilk = scanner.nextInt();
        System.out.print("Write how many grams of coffee beans you want to add: ");
        int addBeans = scanner.nextInt();
        System.out.print("Write how many disposable cups you want to add: ");
        int addCups = scanner.nextInt();
        coffeeMachine.fill(addWater, addMilk, addBeans, addCups);
    }
}
