package twop.effect;

import twop.Player;

import java.awt.Graphics;

public abstract class Effect {
   private Player myPlayer;
   private int myLife;
   private int myIntensity = 1;

   public Effect(Player player, int life) {
      myPlayer = player;
      myLife = life;
   }

   public abstract void update();
   public abstract void draw(Graphics pen);
   public void onDeath() {}

   public boolean isDead() {
      if (myLife <= 0)
         return true;
      return false;
   }

   public Player getPlayer() { return myPlayer; }
   public void setPlayer(Player player) { myPlayer = player; }
   public void loseLife(int damage) { myLife -= damage; }
   public int getLife() { return myLife; }
   public void setLife(int life) { myLife = life; }
   public void increaseIntensity() { myIntensity++; }
   public int getIntensity() { return myIntensity; }
   public void setIntensity(int intensity) { myIntensity = intensity; }

}