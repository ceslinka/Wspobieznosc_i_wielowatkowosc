package przyklady;


class LicznikPriorytetow extends Thread {
    private volatile boolean dziala = true;
    public long licznik = 0;

    public LicznikPriorytetow(String nazwa) {
        super(nazwa);
    }

    public void run() {
        while (dziala) {
            licznik++;
        }
    }

    public void zatrzymaj() {
        dziala = false;
    }
}

public class Przyklad2Priorytety {
    public static void main(String[] args) throws InterruptedException {
        LicznikPriorytetow watekNiski = new LicznikPriorytetow("Wątek-Niski (MIN)");
        LicznikPriorytetow watekWysoki = new LicznikPriorytetow("Wątek-Wysoki (MAX)");

        // Ustawiamy priorytety
        watekNiski.setPriority(Thread.MIN_PRIORITY); // Priorytet 1
        watekWysoki.setPriority(Thread.MAX_PRIORITY); // Priorytet 10

        watekNiski.start();
        watekWysoki.start();


        Thread.sleep(2000);

        watekNiski.zatrzymaj();
        watekWysoki.zatrzymaj();

        System.out.println(watekNiski.getName() + " doliczył do: " + watekNiski.licznik);
        System.out.println(watekWysoki.getName() + " doliczył do: " + watekWysoki.licznik);
    }
}
