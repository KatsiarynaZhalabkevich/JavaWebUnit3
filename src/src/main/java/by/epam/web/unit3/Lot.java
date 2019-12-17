package by.epam.web.unit3;

import java.util.concurrent.locks.ReentrantLock;


public class Lot {
    private String name;
    private final long startPrice;

    ReentrantLock locker = new ReentrantLock();

    public Lot(String name, long price) {
        this.name = name;

        this.startPrice = price;
    }

    public String getName() {
        return name;
    }

    public long getStartPrice() {
        return startPrice;
    }

    @Override
    public String toString() {
        return "Lot "   + name + '\'' +
                ", startPrice=" + startPrice;
    }
}
