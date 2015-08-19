package twop.gui;

import java.util.ArrayList;

import java.awt.event.MouseEvent;
import java.awt.Graphics;

public class GUIManager {
   private ArrayList<Button> myButtons;

   public GUIManager() {
      myButtons = new ArrayList<Button>();
   }

   public void draw(Graphics pen) {
      for (Button button: myButtons) {
          if (button.getMouseHovering()) {
    		  button.drawHovered(pen);
          } else {
    	     button.draw(pen);
          }
      }
   }

   public void mousePressed(MouseEvent event) {
      if (event.getButton() == MouseEvent.BUTTON1) {
         for (Button button: myButtons) {
            if (button.collidesPoint(event.getX() * 480 / (double) 580, event.getY() * 640 / (double) 770))
        	   button.doAction();
         }
      }
   }

   public void mouseMoved(MouseEvent event) {
       for (Button button: myButtons) {
           if (button.collidesPoint(event.getX() * 480 / (double) 580, event.getY() * 640 / (double) 770)) {
        	   button.setMouseHovering(true);
           } else {
        	   button.setMouseHovering(false);
           }
       }
   }

   public void addButton(Button button) {
      myButtons.add(button);
   }
}
