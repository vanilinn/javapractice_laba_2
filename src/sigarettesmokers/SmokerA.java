package sigarettesmokers;

import static sigarettesmokers.Main.*;

public class SmokerA implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                while (!paper && !matches) {
                    try {
                        smokerA.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Курьльщик A крутит и курит сигарету");
                paper = false;
                matches = false;
                tobacco = false;
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