package twop.weather;

import twop.GamePanel;

import java.awt.Graphics;
import java.util.Random;

public class WeatherHandler {
   private GamePanel myGamePanel;
   private Weather myCurrentWeather;

   public WeatherHandler(GamePanel gamePanel) {
      myGamePanel = gamePanel;
   }

   public void update() {
      if (myCurrentWeather != null) {
         myCurrentWeather.update();
         if (new Random().nextDouble() < 0.005) {
            myCurrentWeather = null;
         }
      } else {
         if (new Random().nextDouble() < 0.01) {
            myCurrentWeather = new Rain(myGamePanel);
         }
      }
   }
   public void draw(Graphics pen) {
      if (myCurrentWeather != null) {
        myCurrentWeather.draw(pen);
      }
   }
}
