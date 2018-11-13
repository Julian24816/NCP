package w46;

public class GameLogic extends Thread {
    private String[] sounds = {"alphaSound", "wave.wav", "test", "hiMenschheit"};
    private AudioEngine audioEngine;

    private GameLogic(AudioEngine audioEngine) {
        this.audioEngine = audioEngine;

    }

    @Override
    public void run() {
        int i = 0;
        while (!isInterrupted()) try {
            audioEngine.addToQueue(new AudioEngine.Sound(sounds[i++], 0.3f));
            i %= sounds.length;
        } catch (InterruptedException e) {
            e.printStackTrace();
            interrupt();
        }
    }

    public static void main(String[] args) {
        try {
            AudioEngine audioEngine = new AudioEngine();
            GameLogic gameLogic = new GameLogic(audioEngine);

            audioEngine.start();
            gameLogic.start();

            Thread.sleep(2000);

            gameLogic.interrupt();
            audioEngine.interrupt();

            audioEngine.join();
            gameLogic.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
