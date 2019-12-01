package by.epam.web.unit3;

import java.util.concurrent.TimeUnit;

public class Participant implements Runnable {
    private int id;
    private Auction auction;

    public Participant(int id, Auction auction) {
        this.id = id;
        this.auction = auction;
    }

    public int getId() {
        return id;
    }


    @Override
    public void run() {
        getLot();

    }

    public void getLot() {
        if (!auction.getLotsList().isEmpty()) {
            makeRate();
        } else {
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!auction.getLotsList().isEmpty()) {
                makeRate();
            } else {
                System.out.println("Participant " + this.id + " cant't make a rate! There is no lots");
            }
        }
    }

    private void makeRate() {
        int callPrice;
        if (auction.getLotsList().size() != 0) {
            Lot lot = auction.getLotsList().remove(); //у метода ремув есть свое исключение, может лучше его обработать?

            callPrice =(int)1.5* lot.getPrice();
            System.out.println(callPrice+"*****");
            lot.setPrice(callPrice);
            System.out.println("New price "+lot.getPrice());
            System.out.println("Participant " + this.id + "rate  " + lot.getName() + " price is " + lot.getPrice());


        try {
            TimeUnit.MILLISECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        auction.getLotsList().add(lot);
    }
    }


}
