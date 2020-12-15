package machine;

import java.util.Scanner;

enum Coffee {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);
    public final int water;
    public final int milk;
    public final int coffeeBeans;
    public final int costs;


    Coffee(int water, int milk, int coffeeBeans, int costs) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.costs = costs;
    }
}

public class CoffeeMachine {
    private final Scanner scanner;
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;

    public CoffeeMachine(int water, int milk, int coffeeBeans, int cups, int money) {
        scanner = new Scanner(System.in);
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
    }

    public static void main(String[] args) {
        final CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        while (true) {
            if (!coffeeMachine.action()) break;
        }

    }

    private boolean action() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = this.scanner.nextLine();
        switch (action) {
            case "buy":
                this.buy();
                break;
            case "fill":
                this.fill();
                break;
            case "take":
                this.take();
                break;
            case "remaining":
                this.remaining();
                break;
            case "exit":
                return false;
            default:
                break;
        }
        return true;
    }

    private void remaining() {
        System.out.println("The coffee machine has:\n" +
                water + " of water\n" +
                milk + " of milk\n" +
                coffeeBeans + " of coffee beans\n" +
                cups + " of disposable cups\n" +
                money + " of money\n");
    }

    private void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        final int inputWater = scanner.nextInt();
        this.water += inputWater;
        System.out.println("Write how many ml of milk do you want to add: ");
        final int inputMilk = scanner.nextInt();
        this.milk += inputMilk;
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        final int inputCoffeeBeans = scanner.nextInt();
        this.coffeeBeans += inputCoffeeBeans;
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        final int inputCups = scanner.nextInt();
        this.cups += inputCups;
    }

    private void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        final String inputBuyCoffee = scanner.nextLine();
        if ("back".equals(inputBuyCoffee)) {
            return;
        }
        Coffee buyCoffee;
        switch (Integer.parseInt(inputBuyCoffee)) {
            case 1:
                buyCoffee = Coffee.ESPRESSO;
                break;
            case 2:
                buyCoffee = Coffee.LATTE;
                break;
            case 3:
                buyCoffee = Coffee.CAPPUCCINO;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + inputBuyCoffee);
        }
        if (water < buyCoffee.water) {
            System.out.println("Sorry, not enough water!");
            return;
        }
        if (milk < buyCoffee.milk) {
            System.out.println("Sorry, not enough milk!");
            return;
        }
        if (coffeeBeans < buyCoffee.coffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
            return;
        }
        if (coffeeBeans < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return;
        }

        water -= buyCoffee.water;
        milk -= buyCoffee.milk;
        coffeeBeans -= buyCoffee.coffeeBeans;
        cups -= 1;
        money += buyCoffee.costs;

    }
}

