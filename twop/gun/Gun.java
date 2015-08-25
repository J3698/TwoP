package twop.gun;

import twop.Player;
import twop.Gun.Bullet;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Gun {
   private Player myPlayer;
   private ArrayList<Bullet> myBullets = new ArrayList<Bullet>();

   public Gun(Player player) {
      myPlayer = player;
   }

   public abstract void draw(Graphics pen);
   public abstract void update();
   public abstract void shoot();
}