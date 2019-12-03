package by.epam.web.unit3;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class Participant implements Runnable {
    private int id;
    private long limit; //максимально возможная ставка
    private Auction auction;
    private Date date = new Date();


    public Participant(int id, Auction auction) {
        this.id = id;
        this.auction = auction;
        limit = new Random().nextLong();
    }

    public int getId() {
        return id;
    }


    @Override
    public void run() {
        while (!auction.getLotsList().isEmpty()) {
            getLot();
        }

    }

    public void getLot() {
        while (!auction.getLotsList().isEmpty()) {
            try {
                if (new Random().nextInt(20) % 2 == 0) {
                    makeRate();

                    TimeUnit.MILLISECONDS.sleep(2000);

                } else {
                    System.out.println("Participant " + id + " don't want make a rate for this lot!");
                    TimeUnit.MILLISECONDS.sleep(2000);
                }
            } catch (InterruptedException e) {
                System.out.println("Exception");
            }

        }
    }

    private void makeRate() {
        double callPrice;
        ReentrantLock locker = new ReentrantLock();
        if (auction.getLotsList().size() != 0) {

            //берем любой лот из списка
            locker.lock();
            Lot lot = auction.getLotsList().get(new Random().nextInt(auction.getLotsList().size()));

            callPrice = 1.25 * lot.getPrice().get(); //каждый шаг повышение на 25%
            if (callPrice < limit) {

                lot.setPrice(new AtomicLong(Double.doubleToLongBits(callPrice)));
                System.out.println("Participant " + this.id + " rate  " + lot.getName() + " new price is " + lot.getPrice());

            } else System.out.println("Participant " + id + " dont't want to make a rate for lot " + lot.getName());

            locker.unlock();
        }
    }

}
