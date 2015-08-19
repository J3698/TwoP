package twop.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import twop.util.Vector2;

public class GameOverButton extends Button {
	public GameOverButton(ActionListener listener, String text, Vector2 position, int gameWidth, int gameHeight) {
		super(listener, text, new Color(0, 0, 0, 0), new Color(150, 150, 150), new Font("Ariel", 0, 20),
												   position, new Vector2(70, 30), gameWidth, gameHeight);
	}

	public void draw(Graphics pen) {
		
	}

	public void drawHovered(Graphics pen) {
		
	}
}
