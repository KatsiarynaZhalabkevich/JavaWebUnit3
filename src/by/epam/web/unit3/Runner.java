package by.epam.web.unit3;

public class Runner {
    public static void main(String[] args) {
        int participantNum = 20;
        Auction auction = Auction.getInstance();
        auction.createLots(5);

        for (int i = 0; i <participantNum ; i++) {
            Participant participant = new Participant(i+1, auction);
            new Thread(participant).start();
        }
    }
}
