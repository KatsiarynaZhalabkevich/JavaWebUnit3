package by.epam.web.unit3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class Auction {

    private List<Lot> lotsList;
    private Random random = new Random();

    private static final Auction instance = new Auction();

    private Auction() {

        lotsList = new ArrayList<>();
    }

    public static Auction getInstance() {
        return instance;
    }

    public void createLots(int countLots) {

        for (int i = 1; i <= countLots; i++) {

            Lot lot = new Lot("Lot ".concat(Integer.toString(i)), new AtomicLong(Double.doubleToLongBits(random.nextInt(20) + 1)));
            lotsList.add(lot);
            System.out.println(lot);

        }
    }

    //начинаем торги и следим за лотами
    public void startAuction(){
        ReentrantLock locker = new ReentrantLock();
       while (!lotsList.isEmpty()) {
           for (int i = 0; i < lotsList.size(); i++) {
               try {
                   TimeUnit.MILLISECONDS.sleep(20000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               locker.lock();
               Lot lot = lotsList.get(i);


               if(lot.getPrice()==lot.getStartPrice()){
                   System.out.println("Lot "+lot.getName()+" deleted from auction! ");
                   lotsList.remove(i);
               }
               if (lot.getPrice().get()>lot.getStartPrice().get()){
                   System.out.println("One two Three...");
                   System.out.println("Lot "+lot.getName()+" is sold! ");
                   lotsList.remove(i);
               }

               locker.unlock();


           }
       }
        System.out.println("Auction is closed!");



    }

    public List<Lot> getLotsList() {
        return lotsList;
    }
}
