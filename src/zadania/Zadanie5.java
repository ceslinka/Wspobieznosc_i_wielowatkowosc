package zadania;

// Mamy do wykonania 5 ciężkich zadań. Zamiast tworzyć
// ręcznie 5 wątków, utwórz pulę wątków (ThreadPool) o rozmiarze 3
// (czyli 3 "robotników"), korzystając z klasy Executors.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Zadanie5 {
    public static void main(String[] args) {

        // TODO: Zastąp 'null' utworzeniem puli wątków o stałym rozmiarze 3 (FixedThreadPool)
        ExecutorService pulaRobotnikow = null;

        for (int i = 1; i <= 5; i++) {
            final int idZadania = i;
            pulaRobotnikow.submit(() -> {
                System.out.println("Wykonywanie zadania " + idZadania + " przez: " + Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            });
        }

        // Zamykamy pulę, żeby program mógł się zakończyć
        pulaRobotnikow.shutdown();
    }
}
