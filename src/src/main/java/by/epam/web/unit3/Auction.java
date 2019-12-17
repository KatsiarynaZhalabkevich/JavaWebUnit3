package by.epam.web.unit3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class Auction {

    private List<Lot> lotsList;
    private Random random = new Random();
    private static ReentrantLock locker = new ReentrantLock();
    private Lot currentLot;
    private AtomicLong currentPrice; //текущая цена не содержится в самом лоте
    private boolean auctionFlag; //определяет начало и конец торгов
    private final static double BET = 1.5;
    private static Auction instance;

    Logger logger = LogManager.getLogger();

    private Auction() {

        lotsList = new ArrayList<>();
    }

    public static Auction getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new Auction();
            }
            locker.unlock();
        }
        return instance;
    }


    public boolean isAuction() {
        return auctionFlag;
    }

    public void createLots(int countLots) {

        logger.info("There are lots for today:");

        for (int i = 0; i < countLots; i++) {

            long priceMaker = random.nextInt(20) + 3;
            Lot lot = new Lot(Integer.toString(i + 1), priceMaker);
            lotsList.add(lot);
            logger.info(lot);

        }


    }

    public void changePrice(long limit, Participant participant) {
        double callPrice;
        long price;


        if (currentLot != null) {
            locker.lock();
            callPrice = BET * currentPrice.get();
            if (callPrice < limit) {
                price = currentPrice.getAndSet((long) callPrice);
                logger.info("Participant " +participant.getId()  + " rate  lot " + currentLot.getName() + " from " + price + " to price  " + currentPrice.get());

            } else logger.info("Participant " + participant.getId() + " dont't want to make a rate for lot " + currentLot.getName());
            locker.unlock();
        }


    }

    public void startAuction() throws InterruptedException {
        auctionFlag = true;

        for (int i = 0; i < lotsList.size(); i++) {

            currentLot = lotsList.get(i);
            currentPrice = new AtomicLong(currentLot.getStartPrice());

            logger.info("Играет лот " + currentLot.getName());

            try {
                TimeUnit.MILLISECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            locker.lock();
            System.out.println("--->before" + currentPrice.get());
            logger.info("Текущая цена " + currentPrice.get() + " Начальная цена " + currentLot.getStartPrice());
            Thread.sleep(3000);
            System.out.println("--->after" + currentPrice.get());
            if (currentPrice.get() == currentLot.getStartPrice()) {
                logger.info("Lot " + currentLot.getName() + " deleted from auction! ");
                currentLot = null;
            } else {
                logger.info("One two Three...");
                logger.info("Lot " + currentLot.getName() + " is sold! ");
                logger.info("**************************************");
                currentLot = null;
            }
            locker.unlock();

        }
        auctionFlag = false;
        logger.info("Auction is closed!");


    }


}
