package w46;

import java.util.LinkedList;
import java.util.List;

public class AudioEngine extends Thread{
    private List<Sound> soundQueue = new LinkedList<>();

    synchronized void addToQueue(Sound sound) throws InterruptedException {
        while (soundQueue.size() >= 2) wait();
        soundQueue.add(sound);
        notifyAll();
    }

    private synchronized void playSound() throws InterruptedException {
        while (soundQueue.size() == 0) wait();
        Sound sound = soundQueue.remove(0);
        System.out.println(sound.name + " played at volume " + sound.volumeLevel);
        notifyAll();
    }

    static class Sound{
        Sound(String name, float volumeLevel) {
            this.name = name;
            this.volumeLevel = volumeLevel;
        }
        private String name;
        private float volumeLevel;
    }

    @Override
    public void run() {
        while (!isInterrupted()) try {
            playSound();
        } catch (InterruptedException e) {
            e.printStackTrace();
            interrupt();
        }
    }
}
