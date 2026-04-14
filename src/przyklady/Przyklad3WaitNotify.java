package przyklady;

class SkrzynkaPocztowa {
    private String wiadomosc;
    private boolean jestWiadomosc = false;

    public synchronized void zostawWiadomosc(String msg) {
        wiadomosc = msg;
        jestWiadomosc = true;
        System.out.println("Nadawca: Zostawiłem wiadomość.");
        notifyAll(); // Budzimy odbiorcę!
    }

    public synchronized void odbierzWiadomosc() {
        while (!jestWiadomosc) {
            try {
                System.out.println("Odbiorca: Skrzynka pusta, idę spać (wait)...");
                wait(); // Wątek zasypia i zwalnia blokadę
            } catch (InterruptedException e) {}
        }
        System.out.println("Odbiorca: Otrzymałem wiadomość -> " + wiadomosc);
        jestWiadomosc = false;
    }
}

public class Przyklad3WaitNotify {
    public static void main(String[] args) throws InterruptedException {
        SkrzynkaPocztowa skrzynka = new SkrzynkaPocztowa();

        // Wątek odbierający (startuje pierwszy)
        Thread odbiorca = new Thread(() -> skrzynka.odbierzWiadomosc(), "Watek-odbiorca");
        odbiorca.start();

        // Dajemy mu chwilę, żeby na pewno zdążył zasnąć
        Thread.sleep(2000);

        // Wątek nadający (startuje z opóźnieniem)
        Thread nadawca = new Thread(() -> skrzynka.zostawWiadomosc("Cześć, uczymy się Javy!"), "Watek-nadawca");
        nadawca.start();

        Thread.sleep(100000);
    }
}