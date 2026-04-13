package przyklady;

// Używa bloku synchronized
// dla synchronizacji dostępu
// do metody sumArray.
class SumArray2 {
    private int sum;

    // Brak słowa kluczowego synchronized w deklaracji!
    int sumArray(int nums[]) {
        sum = 0; // zeruje sumę

        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            System.out.println(Thread.currentThread().getName() +
                    " wyliczył sumę częściową równą " + sum);
            try {
                Thread.sleep(10); // umożliwia przełączenie wątków
            }
            catch(InterruptedException exc) {
                System.out.println("Wątek został przerwany.");
            }
        }
        return sum;
    }
}

class MyThread2 implements Runnable {
    Thread thrd;

    static SumArray2 sa = new SumArray2();
    int a[];
    int answer;

    // Tworzy nowy wątek.
    MyThread2(String name, int nums[]) {
        thrd = new Thread(this, name);
        a = nums;
        thrd.start(); // uruchamia wątek
    }

    // Rozpoczyna wykonywanie nowego wątku.
    public void run() {
        System.out.println(thrd.getName() + " rozpoczyna działanie.");

        // Synchronizuje wywołania metody sumArray() na obiekcie 'sa'
        synchronized(sa) {
            answer = sa.sumArray(a);
        }

        System.out.println(thrd.getName() + " wyliczył sumę równą " + answer);
        System.out.println(thrd.getName() + " kończy działanie.");
    }
}

public class Synch_instr {
    public static void main(String args[]) {
        int a[] = {1, 2, 3, 4, 5};

        // POPRAWKA: używamy klasy MyThread2
        MyThread2 mt1 = new MyThread2("Wątek potomny nr 1", a);
        MyThread2 mt2 = new MyThread2("Wątek potomny nr 2", a);

        try {
            mt1.thrd.join();
            mt2.thrd.join();
        }
        catch(InterruptedException exc) {
            System.out.println("Główny wątek został przerwany.");
        }
    }
}