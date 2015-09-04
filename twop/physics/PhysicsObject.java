package twop.physics;

import java.awt.Rectangle;

public interface PhysicsObject {
	boolean collides(PhysicsCircle circle);
	boolean collides(PhysicsRect rect);
	void keepInBounds();
	Rectangle getBounds();
	void setBounds(Rectangle bounds);
	int getWidth();
	int getHeight();
	boolean isSolid();
}