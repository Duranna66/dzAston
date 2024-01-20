import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public final static Test test = new Test();

    public static void main(String[] args) {
        Main main = new Main();
        Runnable r1 = test::printNameOfExecutionThread;
        Runnable r2 = test::printNameOfExecutionThread;
        Runnable r3 = test::task1;
        Runnable r4 = test::task2;
        main.startThread(r1);
        main.startThread(r2);
        ExecutorService executors = Executors.newCachedThreadPool();
        executors.submit(r3);
        executors.submit(r4);

    }

    public void startThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static class Test extends Thread {
        synchronized public void printNameOfExecutionThread() {
            System.out.println(currentThread().getName());


        }

        public void task1()  {
            try {
                join(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
            }
            System.out.println(currentThread().getName());
        }
        public void task2() {
            try {
                sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(currentThread().getName());
        }
    }
}
