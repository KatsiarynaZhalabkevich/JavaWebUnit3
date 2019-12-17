package by.epam.web.unit3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Participant implements Runnable {

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

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        logger.info("Participant " + id + " start playing!");
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            logger.error(e);
        }
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
        Lot lot;
        if (auction.isAuction()) {

//            lot = auction.getCurrentLot();

            if (auction.isAuction()) {
                auction.changePrice(limit, this);
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
