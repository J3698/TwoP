package twop.effect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import twop.Player;

public class HealthPackEffect extends Effect {

	public HealthPackEffect(Player player) {
		super(player, 30);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics pen) {
		// Put into StringDraw, change StringDraw to DrawUtils?
	    Area donut = new Area(new Ellipse2D.Double(0, 0, 400, 200));
	    Area hole = new Area(new Ellipse2D.Double(100, 50, 200, 100));
	    donut.subtract(hole);
	    Graphics2D pen2D = (Graphics2D) pen;
	    pen2D.fill(donut);
	}

}
