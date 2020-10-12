package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class FileProcessor {
    File file = new File("purchases.txt");

    public FileProcessor() {
        super();
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            System.out.println("Cannot create the file: " + this.file.getPath());
        }
    }

    public File getFile() {
        return file;
    }
    public void save(Budget budget) {
        Double balance = budget.getBalance();
        NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);

        List<Purchase> purchaseList = budget.getPurchaseList();
        try (PrintWriter printWriter = new PrintWriter(file)) {

            printWriter.printf("Balance:%s\n", nf.format(balance));
            for (Purchase p : purchaseList){
                printWriter.printf("%s|%s|%s\n", p.getName(), nf.format(p.getPrice()),p.getCategory());
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }

        System.out.println("\nPurchases were saved!\n");
    }

    public void load(Budget budget) {
        try (Scanner scanner = new Scanner(file)) {
            String balance = scanner.nextLine();
            NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);

            if (balance.contains("Balance")) {
               // System.out.println("Balance : "+balance.split(":")[1]);

                budget.setBalance(nf.parse(balance.split(":")[1]).doubleValue());
            }
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] arr = line.split("\\|");
                String name = arr[0];
                //System.out.println(line+ " price: "+ Arrays.toString(arr));
                double price = nf.parse(arr[1]).doubleValue();
                String type = arr[2];

                budget.addPurchace(new Purchase(price,name,type));
            }
        } catch (FileNotFoundException | ParseException e) {
            System.out.println("No file found: " + this.file);
            System.out.println("No file found: " + e.getMessage());
        }

        System.out.println("\nPurchases were loaded!\n");
    }
}
