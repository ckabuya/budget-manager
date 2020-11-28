package budget;

import java.util.*;

public class Main {
    final static Scanner scanner = new Scanner(System.in);
    static double income = 0.0;
    static Budget mainBudget = new Budget();
    static FileProcessor fileProcessor = new FileProcessor();

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
                    processPurchase();
                    break;
                case 3:
                    processShowPurchaseList();
                    break;
                case 4:
                    balance();
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    load();
                    break;
                case 7:
                    processAnalysis();
                    break;
                default:
                    startBudgetApp();
            }
        }
    }

    //display menu
    static int startBudgetApp() {
        System.out.println("\nChoose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit");
        int action = scanner.nextInt();
        if (action < 0 || action > 7) {
            throw new IllegalArgumentException("Invalid action: 0 - 7");
        }
        return action;
    }
    static int displayPurchaseMenu() {
        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back\n");
        int action = scanner.nextInt();
        if (action <= 0 || action > 6) {
            throw new IllegalArgumentException("Invalid action: 1 - 5");
        }
        return action;
    }
    /**
     * Process when user wants to show list of purchases
     *
     */
    static int displayTypeOfPurchaseMenu() {
        System.out.println("\nChoose the type of purchases\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All\n" +
                "6) Back");
        int action = scanner.nextInt();
        if (action <= 0 || action > 7) {
            throw new IllegalArgumentException("Invalid action: 1 - 6");
        }
        return action;
    }
    static int displayAnalysisMenu() {
        System.out.println("\nHow do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back\n");
        int action = scanner.nextInt();
        if (action <= 0 || action > 5) {
            throw new IllegalArgumentException("Invalid action: 1 - 4");
        }
        return action;
    }
    static int displaySortCertainTypeMenu() {
        System.out.println(
                "Choose the type of purchase\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other");
        int action = scanner.nextInt();
        if (action <= 0 || action > 5) {
            throw new IllegalArgumentException("Invalid action: 1 - 4");
        }
        return action;
    }
    private static void processAnalysis() {

        boolean isNotDone = false;

        while (!isNotDone) {
            int action = displayAnalysisMenu();
            switch (action) {
                case 1: //food
                    sortAllPurchases(0);
                    break;
                case 2: //Clothes
                    sortByType(2);
                    break;
                case 3: //Entertainment
                    SortByCertainType();
                    break;
                case 4: //back
                    isNotDone = true;
                    break;
                default:
                    isNotDone = true;
            }
        }
    }
    private static void sortAllPurchases(int type){
        List<Purchase> list = mainBudget.getPurchaseList(); //all sorting
        if(type != 0) {
            list = mainBudget.getPurchaseList(type);

            if(list.isEmpty()){
                System.out.println("\nPurchase list is empty!");
            }
            else
            {
                Collections.sort(list,new PriceComparator());
                showPurchaseList(type);
            }


        }
        else {
            if (list.isEmpty()) {
                System.out.println("\nPurchase list is empty!");
            } else {
                Collections.sort(list, new PriceComparator());
                showPurchaseList();
            }
        }
    }
    private static void SortByCertainType(){
        int action = displaySortCertainTypeMenu();
        switch (action) {
                case 1: //food
                    sortAllPurchases(1);
                    break;
                case 2: //Clothes
                    sortAllPurchases(2);
                    break;
                case 3: //Entertainment
                    sortAllPurchases(3);
                    break;
                case 4: //back
                    sortAllPurchases(4);
                    break;
                default:
                    sortAllPurchases(0);
            }

    }
    private static void sortByType(int type){
        List<PurchaseTypeSort> list = new ArrayList<>();
        double totalSum = 0;
        for(int i = 1; i <= 4; i++) {
            List<Purchase> categery = mainBudget.getPurchaseList(i);
            double total = 0;
            for (Purchase e : categery) {
                double pr = e.getPrice();
                total += pr;
            }
            totalSum += total;
            list.add(new PurchaseTypeSort(Purchase.getCategory(i),total));
        }
        Collections.sort(list);

        for(PurchaseTypeSort typeToSum: list) {
            System.out.println(typeToSum);
        }
        System.out.println("Total sum: $" +totalSum);
        System.out.println();
    }
    private static void processPurchase() {

        boolean isNotDone = false;

        while (!isNotDone) {
            int action = displayPurchaseMenu();
            switch (action) {
                case 1: //food
                    addPurchase(1);
                    break;
                case 2: //Clothes
                    addPurchase(2);
                    break;
                case 3: //Entertainment
                    addPurchase(3);
                    break;
                case 4: //Other
                    addPurchase(4);
                    break;
                case 5: //back
                    isNotDone = true;
                    break;
                default:
                    isNotDone = true;
            }
        }
    }
    private static void processShowPurchaseList() {
        boolean isNotDone = false;

        if (mainBudget.getPurchaseList().isEmpty()) {
            System.out.println("Purchase List is empty");
            System.out.println();
            isNotDone = true;
        }
        while (!isNotDone) {
            int action = displayTypeOfPurchaseMenu();
            switch (action) {
                case 1: //food
                    showPurchaseList(1);
                    break;
                case 2: //Clothes
                    showPurchaseList(2);
                    break;
                case 3: //Entertainment
                    showPurchaseList(3);
                    break;
                case 4: //Other
                    showPurchaseList(4);
                    break;
                case 5: //all
                    showPurchaseList();
                    break;
                case 6: //back
                    isNotDone = true;
                    break;
                default:
                    isNotDone = true;
            }
        }
    }
    static void exit() {
        System.out.println("\nBye!");
    }
    static void addIncome() {
        System.out.println("\nEnter income:");
        mainBudget.addIncome(scanner.nextDouble());
        System.out.println("Income was added!");
        System.out.println();
    }
    static void balance() {
        System.out.printf("\nBalance : $%.2f", mainBudget.getBalance());
        System.out.println();
        // System.out.println();
    }
    static void showPurchaseList(int type) {
        List<Purchase> list = mainBudget.getPurchaseList();
        double total = 0;
        String category = Purchase.getCategory(type);
        System.out.println("\n" + category + ": ");
        boolean isEmpty = true;
        for (Purchase e : list) {
            //this category is present
            if (e.getCategory().equals(category)) {
                double pr = e.getPrice();
                System.out.printf("%s $%.2f\n", e.getName(), pr);
                total += pr;
                isEmpty = false;
            }
        }
        if (isEmpty) {
            System.out.println("Purchase List is empty");
            System.out.println();
        } else {
            System.out.printf("Total sum: $%.2f", total);
            System.out.println();
        }

    }
    static void showPurchaseList() { //all
        List<Purchase> list = mainBudget.getPurchaseList();
        if (list.isEmpty()) {
            System.out.println("Purchase List is empty");
            System.out.println();
        } else {
            double total = 0;

            System.out.println("\nAll: ");
            for (Purchase e : list) {
                double pr = e.getPrice();
                System.out.printf("%s $%.2f\n", e.getName(), pr);
                total += pr;
            }
            System.out.printf("Total sum: $%.2f", total);
            System.out.println();
        }
    }
    /**
     * Option 2 for
     */
    static void addPurchase(int type) {
        System.out.println("\nEnter purchase name:");
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
        Purchase purchase = new Purchase(price, item, type);
        mainBudget.addPurchace(purchase);
        mainBudget.addIncome(-price);//return balance
        System.out.println("Purchase was added!");
    }

    static void save(){
        //save the
        fileProcessor.save(mainBudget);
       // System.out.println("\nPurchases were saved!");
    }
    static void load(){
       // System.out.println("\nPurchases were loaded!");
        fileProcessor.load(mainBudget);
    }
    static class PriceComparator implements Comparator<Purchase>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Purchase a, Purchase b)
        {
            return -Double.compare(a.getPrice() , b.getPrice());
        }
    }
}
