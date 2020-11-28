package budget;

public class PurchaseTypeSort implements Comparable<PurchaseTypeSort>{
    String type;
    double sum;

    public PurchaseTypeSort(String type, double sum) {
        this.type = type;
        this.sum = sum;
    }

    @Override
    public int compareTo(PurchaseTypeSort typeToSum) {
        if (sum > typeToSum.sum) return -1;
        if (sum < typeToSum.sum) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return type + " - $" + String.format("%.2f", sum);
    }
}