package main.java.game.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Sound {
    Clip clip;
    String soundURL[] = new String[30];
    FloatControl fc;
    public int volumeScale = 3;
    public float volume;

    public Sound() {
        soundURL[0] = ("src/main/resources/sound/theme_music.wav");
        soundURL[1] = ("src/main/resources/sound/inventory_grab.wav");
        soundURL[2] = ("src/main/resources/sound/door_open.wav");
        soundURL[3] = ("src/main/resources/sound/drink.wav");
        soundURL[4] = ("src/main/resources/weapon/hand/sound/air_slash.wav");
        soundURL[5] = ("src/main/resources/sound/options_sound.wav");



    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundURL[i]).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void playMusic(int i) {
        setFile(i);
        play();
        loop();
    }

    public void stopMusic() {
        stop();
    }

    public void playSound(int i) {
        setFile(i);
        play();
    }

    private void play() {
        clip.start();
    }

    private void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void stop() {
        clip.stop();
    }

    public void checkVolume() {
        switch (volumeScale) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }
        fc.setValue(volume);
    }
}
