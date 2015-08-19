package twop.effect;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import twop.Player;
import twop.util.Vector2;
import twop.util.ColorCross;

public class HealthPackEffect extends Effect {
   private Vector2 myPosition;
   private double firstRadius;
   private double secondRadius;
   private Color myColor;
   private int myOpacity;
   private int myOpacityIncrement;

   public HealthPackEffect(Player player, Vector2 position) {
		super(player, 15);
		myPosition = position;
		firstRadius = 20;
		secondRadius = 10;
		myColor = Color.pink;
		myOpacity = 200;
		myOpacityIncrement = 10;
   }

	@Override
	public void update() {
	   if (myOpacity - myOpacityIncrement >= 0) {
	      myOpacity -= myOpacityIncrement;
	   } else {
	      myOpacity = 0;
	   }

	   firstRadius += 10;
	   secondRadius += 10;
	   loseLife(1);
	}

	@Override
	public void draw(Graphics pen) {
      // Put into StringDraw, change StringDraw to DrawUtils?
	   double centerX = myPosition.getX();
      double centerY = myPosition.getY();
      Area donut = new Area(new Ellipse2D.Double(centerX - firstRadius, centerY - firstRadius, 2 * firstRadius, 2 * firstRadius));
      Area hole = new Area(new Ellipse2D.Double(centerX - secondRadius, centerY - secondRadius, 2 * secondRadius, 2 * secondRadius));
      donut.subtract(hole);
      Graphics2D pen2D = (Graphics2D) pen;
      pen2D.setColor(ColorCross.alpha(myColor, myOpacity));
      pen2D.fill(donut);
	}

}
