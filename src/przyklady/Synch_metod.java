package przyklady;

// Synchronizacja dostępu do metody.
class SumArray {
    private int sum;

    // Dostęp do metody sumArray() jest synchronizowany!
    synchronized int sumArray(int nums[]) {
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

class MyThread implements Runnable {
    Thread thrd;
    static SumArray sa = new SumArray();
    int a[];
    int answer;

    // Tworzy nowy wątek.
    MyThread(String name, int nums[]) {
        thrd = new Thread(this, name);
        a = nums;
        thrd.start(); // uruchamia wątek
    }

    // Rozpoczyna wykonywanie nowego wątku.
    public void run() {
        int sum;

        System.out.println(thrd.getName() + " rozpoczyna działanie.");

        // Tutaj wywołujemy po prostu metodę, a blokadą zajmuje się obiekt SumArray
        answer = sa.sumArray(a);

        System.out.println(thrd.getName() + " wyliczył sumę równą " + answer);
        System.out.println(thrd.getName() + " kończy działanie.");
    }
}

public class Synch_metod {
    public static void main(String args[]) {
        int a[] = {1, 2, 3, 4, 5};

        MyThread mt1 = new MyThread("Wątek potomny nr 1", a);
        MyThread mt2 = new MyThread("Wątek potomny nr 2", a);

        try {
            mt1.thrd.join();
            mt2.thrd.join();
        }
        catch(InterruptedException exc) {
            System.out.println("Wątek główny został przerwany.");
        }
    }
}