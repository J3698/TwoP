
package twop.gui;

import twop.util.Vector2;
import twop.util.StringDraw;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.Random;

public class InstructionsButton extends Button {
   public InstructionsButton(ActionListener listener, String text, Vector2 position, int gameWidth, int gameHeight) {
      super(listener, text, Color.black, Color.black, StringDraw.instructionsButtonsFont(), position, new Vector2(140, 50), gameWidth, gameHeight);
      Random r = new Random();
      setBodyColor(new Color(150 + r.nextInt(50), 150 + r.nextInt(50), 150 + r.nextInt(50)));
      setTextColor(new Color(100, 100, 100));
   }
}
