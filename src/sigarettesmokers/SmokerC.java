package sigarettesmokers;

import static sigarettesmokers.Main.*;

public class SmokerC implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                while (!tobacco && !paper) {
                    try {
                        smokerC.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Курьльщик C крутит и курит сигарету");
                dblmok();
                tobacco = false;
                paper = false;
                matches = false;
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