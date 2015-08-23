package twop.weather;

import twop.GamePanel;

import java.awt.Graphics;

public abstract class Weather {
   private GamePanel myGamePanel;

   public Weather(GamePanel gamePanel) {
      myGamePanel = gamePanel;
   }

   public abstract void update();
   public abstract void draw(Graphics pen);
// public abstract void fade();

   public GamePanel getGamePanel() { return myGamePanel; }
}
