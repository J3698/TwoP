package twop.weather;

import java.awt.Graphics;
import java.util.Random;

import twop.GamePanel;

public class WeatherHandler {
   private GamePanel myGamePanel;
   private Weather myCurrentWeather;

   public WeatherHandler(GamePanel gamePanel) {
      myGamePanel = gamePanel;
   }

   public void update() {
      if (myCurrentWeather != null) {
         myCurrentWeather.update();
         if (myCurrentWeather.isOver()) {
            myCurrentWeather = null;
            myGamePanel.getPlay().getPlaneHandler().enableFire();
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
      myCurrentWeather.close();
      myCurrentWeather = null;
   }
}
