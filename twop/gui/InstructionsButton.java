
package twop.gui;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Random;

import twop.util.StringDraw;
import twop.util.Vector2;

public class InstructionsButton extends BasicButton {
   public InstructionsButton(ActionListener listener, String text, Vector2 position, int gameWidth, int gameHeight) {
      super(listener, text, Color.black, Color.black, StringDraw.instructionsButtonsFont(), position, new Vector2(140, 50));
      Random r = new Random();
      setBodyColor(new Color(150 + r.nextInt(50), 150 + r.nextInt(50), 150 + r.nextInt(50)));
      setTextColor(new Color(100, 100, 100));
   }
}
