package budget;

import java.util.ArrayList;
import java.util.List;

public class Budget {
    private List<Purchase> purchaseList;
    private double balance; //income

    public Budget(List<Purchase> list) {
        this.purchaseList = list;
    }

    public Budget() {
        purchaseList = new ArrayList<>();
    }

    public void addIncome(double amount) {
        this.balance += amount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void addPurchace(Purchase add) {
        this.purchaseList.add(add);
    }

    public void addPurchace(String name, double price) {
        this.purchaseList.add(new Purchase(price, name));
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public List<Purchase> getPurchaseList(int type) {
        List<Purchase> newList= new ArrayList<>();

        for (Purchase e : purchaseList) {
            //this category is present
            if (e.getCategory().equals(Purchase.getCategory(type))) {
                newList.add(e);//add
            }
        }
        return newList;
    }

}
