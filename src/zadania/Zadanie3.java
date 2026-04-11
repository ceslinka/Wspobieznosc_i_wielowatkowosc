package zadania;

//Dwa wątki dodają po milionie do wspólnego licznika,
// ale wynik jest zawsze błędny, bo nadpisują swoje dane.
// Dodaj jedno słowo kluczowe w odpowiednim miejscu klasy
// Licznik, aby naprawić synchronizację.
class Licznik {
    int suma = 0;

    // TODO: Zabezpiecz tę metodę, aby tylko jeden wątek mógł z niej korzystać w danym czasie
    void dodaj() {
        suma++;
    }
}

public class Zadanie3 {
    public static void main(String[] args) throws InterruptedException {
        Licznik licznik = new Licznik();

        Runnable zadanie = () -> {
            for (int i = 0; i < 1000000; i++) licznik.dodaj();
        };

        Thread w1 = new Thread(zadanie);
        Thread w2 = new Thread(zadanie);

        w1.start(); w2.start();
        w1.join(); w2.join();

        System.out.println("Końcowy wynik: " + licznik.suma + " (Oczekiwano: 2000000)");
    }
}
