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
    private boolean auctionFlag; //определяет начало и конец торгов

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

    public Lot getCurrentLot() {
        return currentLot;
    }

    public boolean isAuction() {
        return auctionFlag;
    }

    public void createLots(int countLots) {

        logger.info("There are lots for today:");
        AtomicLong price = new AtomicLong(0);

        for (int i = 0; i < countLots; i++) {

            long priceMaker = random.nextInt(20) + 1;
            price.set(priceMaker);
            Lot lot = new Lot(Integer.toString(i + 1), price);
            lotsList.add(lot);
            logger.info(lot);

        }


    }




    public void startAuction() {
        auctionFlag = true;

        for (int i = 0; i < lotsList.size(); i++) {

            currentLot = lotsList.get(i);
            logger.info("Играет лот " + currentLot.getName());

            try {
                TimeUnit.MILLISECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            locker.lock();
            logger.info("Текущая цена " + currentLot.getPrice().get() + " Начальная цена " + currentLot.getStartPrice());

            if (currentLot.getPrice().get() == currentLot.getStartPrice()) {
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
