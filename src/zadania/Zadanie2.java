package zadania;

//Uruchamiamy 3 wątki, ale główny program wypisuje
// "Wszystko pobrane!" zanim wątki skończą pracę.
// Użyj takiej metody, aby główny wątek
// poczekał na zakończenie działania wątków w1, w2 i w3.

class Pobieranie extends Thread {
    public void run() {
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        System.out.println("Pobrano plik przez: " + Thread.currentThread().getName());
    }
}

public class Zadanie2 {
    public static void main(String[] args) {
        Pobieranie w1 = new Pobieranie();
        Pobieranie w2 = new Pobieranie();
        Pobieranie w3 = new Pobieranie();

//        w1.start(); w2.start(); w3.start();

        try {
            // TODO: Dopisz kod tutaj, aby główny program poczekał na zakończenie w1, w2 i w3

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Wszystko pobrane! Można wyłączyć program.");
    }
}
