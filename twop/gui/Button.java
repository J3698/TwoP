package twop.gui;

import twop.util.Vector2;

import java.awt.Graphics;

public abstract class Button {
   private int myGameWidth;
   private int myGameHeight;
   private Vector2 myPositionRatio;
   private Vector2 mySizeRatio;

   public Button(int gameWidth, int gameHeight, Vector2 positionRatio,
                                                    Vector2 sizeRatio) {
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myPositionRatio = positionRatio;
      mySizeRatio = sizeRatio;
   }

   public abstract void draw(Graphics pen);

}