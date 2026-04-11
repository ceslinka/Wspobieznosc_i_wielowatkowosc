package przyklady;


class KontoBankowe {
    private int saldo = 100;

    // Brak słowa 'synchronized'
    public void wplac(int kwota) {
        int obecneSaldo = saldo;
        System.out.println(Thread.currentThread().getName() + " widzi saldo: " + obecneSaldo);

        try { Thread.sleep(10); } catch (InterruptedException e) {} // Symulacja czasu przetwarzania

        saldo = obecneSaldo + kwota;
        System.out.println(Thread.currentThread().getName() + " kończy wpłatę. Nowe saldo: " + saldo);
    }

    public int getSaldo() { return saldo; }
}

public class Przyklad1Wyscig {
    public static void main(String[] args) throws InterruptedException {
        KontoBankowe konto = new KontoBankowe();

        Runnable zadanieWplaty = () -> {
            for (int i = 0; i < 5; i++) konto.wplac(10);
        };

        Thread w1 = new Thread(zadanieWplaty, "Wątek-A");
        Thread w2 = new Thread(zadanieWplaty, "Wątek-B");

        w1.start();
        w2.start();

        w1.join();
        w2.join();

        System.out.println("Oczekiwane saldo: 200");
        System.out.println("Rzeczywiste saldo: " + konto.getSaldo() + " (Pieniądze wyparowały!)");
    }
}