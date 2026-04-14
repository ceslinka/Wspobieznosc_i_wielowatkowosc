package zadania;

// TRAGEDIA W BANKU!
// Mąż i żona (dwa wątki) próbują jednocześnie wypłacać pieniądze ze wspólnego konta podczas Black Friday.
// Niestety, system bankowy pisał praktykant, który zapomniał o blokadach.
// Zamiast uczciwie odjąć kwoty, system gubi transakcje, a pieniądze dosłownie "wyparowują"
// w informatyczną czarną dziurę!
//
// Twoim zadaniem jest uratowanie ich oszczędności poprzez dodanie słowa kluczowego,
// które wpuści do metody tylko jedną osobę naraz.

class WspolneKonto {
    private int saldo = 2000; // Zaczynają z fajną kwotą

    // TODO: Dodaj odpowiednie słowo kluczowe przed typem zwracanym (void),
    // aby zapobiec kradzieży pieniędzy przez cyber-chochliki!
    public void wyplac(int kwota) {
        if (saldo >= kwota) {
            int obecneSaldo = saldo;

            // System bankowy "myśli" (tu wątek idzie spać, a drugi w tym czasie wbija mu nóż w plecy)
            try { Thread.sleep(2); } catch (InterruptedException e) {}

            saldo = obecneSaldo - kwota;
        }
    }

    public int getSaldo() {
        return saldo;
    }
}

public class Zadanie5 {
    public static void main(String[] args) throws InterruptedException {
        WspolneKonto konto = new WspolneKonto();

        // Cel: Oboje wypłacają po 10 zł dokładnie 100 razy (każde z nich powinno wypłacić po 1000 zł)
        // Matematyka: 2000 zł - (1000 zł + 1000 zł) = na koncie powinno zostać 0 zł.
        Runnable szalZakupow = () -> {
            for (int i = 0; i < 100; i++) {
                konto.wyplac(10);
            }
        };

        Thread maz = new Thread(szalZakupow, "Mąż w bankomacie nr 1");
        Thread zona = new Thread(szalZakupow, "Żona w bankomacie nr 2");

        System.out.println("Stan konta przed zakupami: " + konto.getSaldo() + " zł");
        System.out.println("Rozpoczynamy zmasowany atak na bankomaty!...");

        maz.start();
        zona.start();

        // Czekamy, aż oboje skończą pustoszyć konto
        maz.join();
        zona.join();

        // Moment prawdy...
        System.out.println("--------------------------------------------------");
        System.out.println("Spodziewane saldo po zakupach: 0 zł (wszystko wydane)");
        System.out.println("Rzeczywiste saldo konta: " + konto.getSaldo() + " zł");

        if (konto.getSaldo() != 0) {
            System.out.println("\nBANK WARIUJE! Saldo się nie zgadza!");
            System.out.println("Pieniądze zniknęły w czarnej dziurze Race Condition.");
            System.out.println("Szybko, dopisz 'synchronized', zanim obudzi się Urząd Skarbowy!");
        } else {
            System.out.println("\nUdało się! Konto jest zsynchronizowane i bezpieczne (choć puste)!");
        }
    }
}