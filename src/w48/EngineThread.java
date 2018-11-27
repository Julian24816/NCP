package w48;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

class EngineThread extends Thread {
    private String name;
    private int initTime, shutDownTime;
    private CountDownLatch initLatch, shutdownLatch;

    private EngineThread(String name, int initTime, int shutDownTime,
                         CountDownLatch initLatch, CountDownLatch shutdownLatch) {
        this.name = name;
        this.initTime = initTime;
        this.shutDownTime = shutDownTime;
        this.initLatch = initLatch;
        this.shutdownLatch = shutdownLatch;
    }

    @Override
    public void run() {
        init();
        mainloop();
    }

    private void mainloop() {
        while (!isInterrupted()) try {
            sleep(1000);
        } catch (InterruptedException e) {
            shutdown();
            interrupt();
        }
    }

    private void init(){
        try {
            System.out.println(name + " starting initialization.");
            sleep(initTime);
            System.out.println(name + " finished initialization.");
            initLatch.countDown();
        } catch (InterruptedException e) {
            interrupt();
        }
    }

    private void shutdown() {
        //shutdown
        try {
            System.out.println(name + " starting shutdown.");
            sleep(shutDownTime);
            System.out.println(name + " finished shutdown.");
            shutdownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Main Thread is Game Logic Thread
        String[] engineNames = {"Physics Engine", "Network Engine", "Graphics Engine", "Audio Engine"};

        try {
            Random random = new Random();

            CountDownLatch initGate = new CountDownLatch(engineNames.length),
                    shutdownGate = new CountDownLatch(engineNames.length);
            EngineThread[] engines = new EngineThread[engineNames.length];
            for (int i = 0; i < engineNames.length; i++)
                engines[i] = new EngineThread(engineNames[i], random.nextInt(1000), random.nextInt(1000),
                        initGate, shutdownGate);

            for (EngineThread thread : engines)
                thread.start();

            initGate.await();
            System.out.println("Now Game is starting...");
            for (int i = 0; i < 7; i++) {
                Thread.sleep(16);
                System.out.println("update - new tick");
            }

            for (EngineThread thread : engines)
                thread.interrupt();

            shutdownGate.await();
            System.out.println("shutdown complete.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
