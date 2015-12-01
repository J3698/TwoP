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
            myCurrentWeather.close();
            myCurrentWeather = null;
         }
      } else {
         if (new Random().nextDouble() < 0.002) {
            myCurrentWeather = chooseWeather();
         }
      }
   }
   public void draw(Graphics pen) {
      if (myCurrentWeather != null) {
         myCurrentWeather.draw(pen);
      }
   }

   public Weather chooseWeather() {
      String[] choices = { "Rain", "VolcanicEruption", "VolcanicEruption", "VolcanicEruption" };
      int choice = new Random().nextInt(choices.length);
      if (choice == 0) {
         return new Rain(100, myGamePanel);
      } else if (choice == 1) {
         return new VolcanicEruption(2000, myGamePanel);
      }
      return null;
   }

   public void reset() {
      if (myCurrentWeather != null) {
         myCurrentWeather.close();
         myCurrentWeather = null;
      }
   }
}
