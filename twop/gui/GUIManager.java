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
      for (Button button: myButtons)
         button.draw(pen);
   }

   public void mouseListen(MouseEvent event) {
      if (event.getButton() == MouseEvent.BUTTON1) {
         for (Button button: myButtons) {
            if (button.collidesPoint(event.getX() * 480 / 580, event.getY() * 640 / 770))
               button.doAction();
         }
      }

   }

   public void addButton(Button button) {
      myButtons.add(button);
   }
}