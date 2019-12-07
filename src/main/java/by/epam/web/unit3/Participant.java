package by.epam.web.unit3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Participant implements Runnable {
    public ReentrantLock locker = new ReentrantLock();
    private int id;
    private long limit; //максимально возможная ставка
    private Auction auction;

    private final static double BET = 1.5;

    Logger logger = LogManager.getLogger();


    public Participant(int id, Auction auction) {
        this.id = id;
        this.auction = auction;
        limit = new Random().nextInt(150) + 1;
    }


    @Override
    public void run() {
        getLot();
    }

    public void getLot() {

        while (auction.isAuction()) {

                //if (new Random().nextInt(20) % 2 == 0) {
                if (true) {  //заглушка, чтобы чаще поднимали цену
                    makeRate();
                    try {
                        TimeUnit.MILLISECONDS.sleep(2000);
                    } catch (InterruptedException e) {
                        logger.error(e);
                    }
                } else {
                    logger.info("Participant " + id + " don't want make a rate!");
                    try {
                        TimeUnit.MILLISECONDS.sleep(2000);
                    } catch (InterruptedException e) {
                        logger.error(e);
                    }
                }

        }
        logger.info("Auction has no lots!!!");
    }

    private void makeRate() {

        double callPrice;
        Lot lot;
        long price;

        if (auction.isAuction()) {

            lot = auction.getCurrentLot();

            if (lot != null) {

                callPrice = BET * lot.getPrice().get(); //каждый шаг повышение на 50%
                price = lot.getPrice().get();
                if (callPrice < limit) {

                    lot.getPrice().set((long) callPrice); // операция не атомарна!!! зато правильно показывает

                    //   lot.getPrice().getAndSet(Double.doubleToLongBits(callPrice));
                    logger.info("Participant " + this.id + " rate  lot " + lot.getName() + " from " + price + " to price  " + lot.getPrice().get());


                } else logger.info("Participant " + id + " dont't want to make a rate for lot " + lot.getName());
            } else {
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    logger.error(e);
                }
            }


        }
    }

}
