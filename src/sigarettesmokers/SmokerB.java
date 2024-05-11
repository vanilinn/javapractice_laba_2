package sigarettesmokers;

import static sigarettesmokers.Main.*;

public class SmokerB implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                while (!tobacco && !matches) {
                    try {
                        smokerB.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Курьльщик B крутит и курит сигарету");
                tobacco = false;
                matches = false;
                paper = false;
                dblmok();
                barman.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    private void dblmok() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}