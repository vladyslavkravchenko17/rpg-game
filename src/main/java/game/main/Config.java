package main.java.game.main;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    GamePanel gamePanel;

    public Config(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void saveConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            //FULLSCREEN
            if (gamePanel.fullscreenOn) {
                bw.write("Fullscreen: on");
            } else {
                bw.write("Fullscreen: off");
            }
            bw.newLine();

            //MUSIC
            bw.write(String.valueOf(gamePanel.music.volumeScale));
            bw.newLine();

            //SOUNDS
            bw.write(String.valueOf(gamePanel.sound.volumeScale));
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();
            if (s.equals("Fullscreen: on")) {
                gamePanel.fullscreenOn = true;
            } else if (s.equals("Fullscreen: off")){
                gamePanel.fullscreenOn = false;
            }

            s = br.readLine();
            gamePanel.music.volumeScale = Integer.parseInt(s);

            s = br.readLine();
            gamePanel.sound.volumeScale = Integer.parseInt(s);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
