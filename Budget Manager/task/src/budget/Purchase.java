package budget;

import java.util.Objects;

public class Purchase {
    private double price;
    private String name;

    public Purchase(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Purchase() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return Double.compare(purchase.getPrice(), getPrice()) == 0 &&
                Objects.equals(getName(), purchase.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getName());
    }
}
