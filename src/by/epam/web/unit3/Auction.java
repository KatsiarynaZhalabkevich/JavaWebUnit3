package by.epam.web.unit3;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Auction {

    private static final Auction instance = new Auction();
    private int countLots;
    private ConcurrentLinkedQueue<Lot> lotsList;
    private Random random = new Random();


    //можно ли в конструкторе вызывать методы другие?
    private Auction() {

        lotsList = new ConcurrentLinkedQueue<Lot>();
    }

    public static Auction getInstance() {
        return instance;
    }

    public void createLots(int countLots) {
        this.countLots = countLots;
        for (int i = 1; i <= countLots; i++) {

            Lot lot = new Lot("Lot ".concat(Integer.toString(i)), 100 * (random.nextInt(20)+1));
            lotsList.add(lot);
            System.out.println(lot);

        }
    }

    public ConcurrentLinkedQueue<Lot> getLotsList() {
        return lotsList;
    }
}
