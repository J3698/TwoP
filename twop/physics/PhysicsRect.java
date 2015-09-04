package twop.physics;

import java.awt.Rectangle;

public class PhysicsRect implements PhysicsObject {

	@Override
	public boolean collides(PhysicsCircle circle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collides(PhysicsRect rect) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void keepInBounds() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBounds(Rectangle bounds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return false;
	}

}
