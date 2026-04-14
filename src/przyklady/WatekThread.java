package przyklady;


class MojWatek extends Thread {
    @Override
    public void run() {
        System.out.println("Dzialam jako osobny watek!");
    }
}

public class WatekThread {
    public static void main(String[] args) {
        MojWatek watek = new MojWatek();
        watek.start();
    }
}