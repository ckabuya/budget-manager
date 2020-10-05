package budget;

import java.util.List;
import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);
    static double income = 0.0;
    static StringBuilder purchasesList = new StringBuilder();
    static Budget mainBudget = new Budget();

    public static void main(String[] args) {
        //starting the app
        boolean isNotExit = false;
        while (!isNotExit) {
            int action = startBudgetApp();
            switch (action) {
                case 0: //exit
                    isNotExit = true;
                    exit();
                    break;
                case 1: //add income
                    addIncome();
                    break;
                case 2:
                    addPurchase();
                    break;
                case 3:
                    showPurchaseList();
                    break;
                case 4:
                    balance();
                    break;
                default:
                    startBudgetApp();
            }
        }
    }

    //display menu
    static int startBudgetApp() {
        System.out.println("Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "0) Exit\n");
        int action = scanner.nextInt();
        if (action < 0 || action > 5) {
            throw new IllegalArgumentException("Invalid action: 0 - 5");
        }
        return action;
    }

    static void exit() {
        System.out.println("Bye!");
    }

    static void addIncome() {
        System.out.println("Enter income:");
        mainBudget.addIncome(scanner.nextDouble());
        System.out.println("Income was added!");
        System.out.println();
    }

    static void balance() {
        System.out.printf("Balance : $%.2f", mainBudget.getBalance());
        System.out.println();
        System.out.println();
    }

    static void showPurchaseList() {
        List<Purchase> list = mainBudget.getPurchaseList();

        if (list.isEmpty()) {
            System.out.println("Purchase List is empty");
            System.out.println();
        } else {
            double total = 0;
            for (Purchase e : list) {
                double pr = e.getPrice();
                System.out.printf("%s $%.2f\n", e.getName(), pr);
                total += pr;
            }
            System.out.printf("\nTotal sum: $%.2f", total);
            System.out.println();
        }
    }

    static void addPurchase() {
        System.out.println("Enter purchase name:");
        String item = null;
        if (scanner.hasNextLine()) {
            scanner.nextLine();
            item = scanner.nextLine();
        }
        double price = 0.0;
        System.out.println("Enter its price:");
        if (scanner.hasNextDouble()) {
            price = scanner.nextDouble();
        }
        Purchase purchase = new Purchase(price, item);
        mainBudget.addPurchace(purchase);
        mainBudget.addIncome(-price);//return balance

        System.out.println("Purchase was added!");
        System.out.println();
        System.out.println();
    }

}
