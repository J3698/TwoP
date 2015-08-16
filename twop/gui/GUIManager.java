package twop.gui;

import java.util.ArrayList;

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

   public void addButton(Button button) {
      myButtons.add(button);
   }
}