package by.epam.web.unit3;

import java.util.Objects;

public class Lot {
    private String name;
    private int price;

    public Lot(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lot lot = (Lot) o;
        return Double.compare(lot.getPrice(), getPrice()) == 0 &&
                Objects.equals(getName(), lot.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice());
    }

    @Override
    public String toString() {
        return "\nLot " +
                "name='" + name +
                ", price=" + price ;
    }
}
