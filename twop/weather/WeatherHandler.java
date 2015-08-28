package twop.weather;

import twop.GamePanel;
import twop.sound.Sound;

import java.awt.Graphics;
import java.util.Random;

public class WeatherHandler {
   private GamePanel myGamePanel;
   private Weather myCurrentWeather;
   private Sound mySound = new Sound("rain");

   public WeatherHandler(GamePanel gamePanel) {
      myGamePanel = gamePanel;
   }

   public void update() {
      if (myCurrentWeather != null) {
         if (myCurrentWeather.getTick() == 200)
            mySound.loop();
         myCurrentWeather.update();
         if (myCurrentWeather.isOver()) {
            myCurrentWeather = null;
            myGamePanel.getPlay().getPlaneHandler().enableFire();
            mySound.stop();
         }
      } else {
         if (new Random().nextDouble() < 0.002) {
            myCurrentWeather = new Rain(1200, myGamePanel);
            myGamePanel.getPlay().getPlaneHandler().disableFire();
         }
      }
   }
   public void draw(Graphics pen) {
      if (myCurrentWeather != null) {
        myCurrentWeather.draw(pen);
      }
   }

   public void reset() {
      myCurrentWeather = null;
   }
}
