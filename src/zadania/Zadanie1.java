package zadania;
// Masz klasę Powitanie, która dziedziczy po klasie Thread.
// Twoim zadaniem jest utworzenie obiektu tego wątku i
// uruchomienie go za pomocą odpowiedniej metody, aby nie
// blokować głównego programu.
class Powitanie extends Thread {
    public void run() {
        System.out.println("Start: Przygotowuję powitanie...");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        System.out.println("Koniec: Cześć, witaj na zajęciach!");
    }
}

public class Zadanie1 {
    public static void main(String[] args) {
        // TODO: 1. Utwórz obiekt klasy Powitanie
        // TODO: 2. Uruchom wątek w tle (nie wywołuj bezpośrednio metody run!)

        System.out.println("Główny program leci dalej, nie czeka!");
    }
}