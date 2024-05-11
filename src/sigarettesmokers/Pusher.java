package sigarettesmokers;
import java.util.Random;

import static sigarettesmokers.Main.*;

public class Pusher implements Runnable {
    private Random rand = new Random();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                while ((tobacco && paper) || (tobacco && matches) || (paper && matches)) {
                    try {
                        Main.barman.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int choice = rand.nextInt(3);
                if (choice == 0) {
                    System.out.println("Бармен дает бумагу и спички");
                    tobacco = false;
                    paper = true;
                    matches = true;
                    pushing();
                    smokerA.signal();
                }
                if (choice == 1) {
                    System.out.println("Бармен дает табак и спички");
                    tobacco = true;
                    paper = false;
                    matches = true;
                    pushing();
                    smokerB.signal();
                }
                if (choice == 2) {
                    System.out.println("Бармен дает бумагу и табак");
                    tobacco = true;
                    paper = true;
                    matches = false;
                    pushing();
                    smokerC.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private void pushing() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}