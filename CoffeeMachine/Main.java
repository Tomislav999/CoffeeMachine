package CoffeeMachine;

public class Main {
    public static void main(String[] args) {
        String filename = "coffee_machine_status.txt";

        CoffeeMachine coffeeMachine = CoffeeMachine.loadState(filename);

        CoffeeMachineUI ui = new CoffeeMachineUI(coffeeMachine);
        ui.start();

        coffeeMachine.saveState(filename, "admin", "admin123");
    }
}
