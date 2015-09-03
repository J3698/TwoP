package twop.util;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class StringDraw {
   public static Font playResumeFont() {
      return new Font("Ariel", Font.BOLD, 90);
   }
   public static Font keyFont() {
      return new Font("Roboto", Font.BOLD, 25);
   }
   public static Font keyFont2() {
      return new Font("Roboto", Font.BOLD, 18);
   }
   public static Font versionFont() {
      return new Font("Sans", 1, 15);
   }
   public static Font healthPlaneFont() {
      return new Font("Sans", Font.BOLD, 25);
   }

   public static Font menuFont() {
      return new Font("Ariel", Font.BOLD, 40);
   }

   public static Font instructionsButtonsFont() {
      return new Font("Ariel", Font.ITALIC, 20);
   }

   public static void drawStringCenter(Graphics pen, String string, int x, int y) {
      Graphics2D pen2D = (Graphics2D) pen;
      FontMetrics fontMetrics = pen2D.getFontMetrics();
      Rectangle2D stringRect = fontMetrics.getStringBounds(string, pen2D);
      x = (int) (x - stringRect.getWidth() / 2);
      y = (int) (y + stringRect.getHeight() / 2 - fontMetrics.getAscent() / 5);
      pen.drawString(string, x, y);
   }
}