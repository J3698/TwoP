package twop.item.container;

import twop.item.*;
import twop.util.Vector2;

import java.awt.Graphics;

public class Asteroid extends ItemContainer {

   public Asteroid() {
      super(new HealthPack(new Vector2(0, 0)), new Vector2(0, 0));
   }

   public void update() {
   }
   public void draw(Graphics pen) {
   }
}