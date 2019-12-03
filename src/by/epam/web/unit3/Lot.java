package by.epam.web.unit3;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;


public class Lot {
    private String name;
    private AtomicLong price;
    private AtomicLong startPrice;

    public Lot(String name, AtomicLong price) {
        this.name = name;
        this.price = price;
        startPrice = price;
    }

    public String getName() {
        return name;
    }

    public AtomicLong getPrice() {
        return price;
    }

    public void setPrice(AtomicLong price) {
        this.price = price;
    }

    public AtomicLong getStartPrice() {
        return startPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lot lot = (Lot) o;
        return Objects.equals(name, lot.name) &&
                Objects.equals(price, lot.price);
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
