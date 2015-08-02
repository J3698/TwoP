import java.awt.Graphics;

public abstract class Effect {
   private int myLife;
   private Player myPlayer;

   public Effect(Player player, int life) {
      myPlayer = player;
      myLife = life;
   }

   public abstract void update();
   public abstract void draw(Graphics pen);

   public Player getPlayer() {
      return myPlayer;
   }
   public void setPlayer(Player player) {
      myPlayer = player;
   }
   public boolean isDead() {
      if (myLife <= 0)
         return true;
      return false;
   }
   public void loseLife(int damage) {
      myLife -= damage;
   }
   public int getLife() {
      return myLife;
   }
   public void setLife(int life) {
      myLife = life;
   }
}