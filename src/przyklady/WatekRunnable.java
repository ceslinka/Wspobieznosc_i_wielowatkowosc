package przyklady;

class MojeZadanie implements Runnable {
    @Override
    public void run() {
        System.out.println("Wykonuje zadanie w tle!");
    }
}

public class WatekRunnable {
    public static void main(String[] args) {
        MojeZadanie zadanie = new MojeZadanie();
        Thread watek = new Thread(zadanie);
        watek.start();
    }
}
