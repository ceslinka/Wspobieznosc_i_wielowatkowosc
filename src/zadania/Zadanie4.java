package zadania;

// Klient chce kupić towar, ale go nie ma.
// Dostawca dowozi towar. Użyj metod wait() oraz notifyAll() w
// odpowiednich miejscach, aby klient grzecznie poczekał na dostawę
// (bez aktywnego przepalania procesora).
class Sklep {
    boolean towarDostepny = false;

    synchronized void kup() throws InterruptedException {
        while (!towarDostepny) {
            System.out.println("Klient czeka na towar...");
            // TODO: 1. Uśpij wątek, dopóki towaru nie ma (użyj odpowiedniej metody)

        }
        System.out.println("Klient: Kupiono towar!");
        towarDostepny = false;
    }

    synchronized void dostarcz() {
        towarDostepny = true;
        System.out.println("Dostawca: Towar dojechał na półki!");
        // TODO: 2. Obudź wszystkie oczekujące wątki (użyj odpowiedniej metody)

    }
}

public class Zadanie4 {
    public static void main(String[] args) {
        Sklep sklep = new Sklep();

        new Thread(() -> { try { sklep.kup(); } catch (Exception e){} }).start();

        try { Thread.sleep(2000); } catch (InterruptedException e) {} // Czas na dojazd

        new Thread(() -> sklep.dostarcz()).start();
    }
}
