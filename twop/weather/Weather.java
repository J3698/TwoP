package twop.weather;

import java.awt.Graphics;

import twop.GamePanel;

public abstract class Weather {
   private int myLife;
   private int myTick;
   private GamePanel myGamePanel;

   public Weather(int life, GamePanel gamePanel) {
      myLife = life;
      myGamePanel = gamePanel;
   }

   public abstract void update();
   public abstract void draw(Graphics pen);
   public abstract void close();

   public GamePanel getGamePanel() { return myGamePanel; }
   public int getLife() { return myLife; }
   public void setLife(int life) { myLife = life; }
   public boolean isOver() { return (myLife <= myTick); }
   public void tick() { myTick++; }
   public int getTick() { return myTick; }
}