package twop.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import twop.GamePanel;
import twop.gui.GameOverButton;
import twop.sound.Sound;
import twop.util.StringDraw;
import twop.util.Vector2;

public class QuickInstructions extends GameState {
   private static Font myKeyFont = StringDraw.keyFont();
   private static Font myKeyFont2 = StringDraw.keyFont2();

   private int myGameWidth;
   private int myGameHeight;

   private InstructRect[] myInstructRects = new InstructRect[12];

   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;

   public QuickInstructions(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super(gamePanel, "quickinstructions");
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
      getGUIManager().addButton(new GameOverButton(new MainMenuListener(), "Back", new Vector2(10, 420), myGameWidth, myGameHeight));
      getGUIManager().addButton(new GameOverButton(new SkipListener(), "Skip", new Vector2(500, 420), myGameWidth, myGameHeight));

      Color c = getGamePanel().getPlay().getFirstPlayer().getColor();
      Color c2 = getGamePanel().getPlay().getSecondPlayer().getColor();
      Font f = myKeyFont;
      Font f2 = myKeyFont2;
      myInstructRects[0] = new InstructRect("d", c, f, KeyEvent.VK_D, 150, 150, 50, 50);
      myInstructRects[1] = new InstructRect("f", c, f, KeyEvent.VK_F, 210, 150, 50, 50);
      myInstructRects[2] = new InstructRect("s", c, f, KeyEvent.VK_S, 90, 150, 50, 50);
      myInstructRects[3] = new InstructRect("e", c, f, KeyEvent.VK_E, 150, 90, 50, 50);
      myInstructRects[4] = new InstructRect("1", c, f, KeyEvent.VK_1, 30, 65, 50, 50);
      myInstructRects[5] = new InstructRect("2", c, f, KeyEvent.VK_2, 90, 65, 50, 50);
      myInstructRects[6] = new InstructRect("down", c2, f2, KeyEvent.VK_DOWN, 470, 150, 50, 50);
      myInstructRects[7] = new InstructRect("right", c2, f2, KeyEvent.VK_RIGHT, 530, 150, 50, 50);
      myInstructRects[8] = new InstructRect("left", c2, f2, KeyEvent.VK_LEFT, 410, 150, 50, 50);
      myInstructRects[9] = new InstructRect("up", c2, f, KeyEvent.VK_UP, 470, 90, 50, 50);
      myInstructRects[10] = new InstructRect(",", c2, f, KeyEvent.VK_COMMA, 350, 65, 50, 50);
      myInstructRects[11] = new InstructRect(".", c2, f, KeyEvent.VK_PERIOD, 410, 65, 50, 50);
   }

   @Override
   public void draw(Graphics pen) {
      pen.setColor(bg);
      pen.fillRect(0, 0, myGameWidth, myGameHeight);

      pen.setFont(myKeyFont2);
      pen.setColor(getGamePanel().getPlay().getFirstPlayer().getColor());
      StringDraw.drawStringCenter(pen, "1  -  shoot", myGameWidth / 4, 230 + 40);
      StringDraw.drawStringCenter(pen, "2  -  toggle gun spinning", myGameWidth / 4, 260 + 40);
      StringDraw.drawStringCenter(pen, "d  -  toggle gun direction", myGameWidth / 4, 290 + 40);
      StringDraw.drawStringCenter(pen, "e, s, f  -  movement", myGameWidth / 4, 320 + 40);

      pen.setColor(getGamePanel().getPlay().getSecondPlayer().getColor());
      StringDraw.drawStringCenter(pen, ",  -  shoot", myGameWidth * 3 / 4, 230 + 40);
      StringDraw.drawStringCenter(pen, ".  -  toggle gun spinning", myGameWidth * 3 / 4, 260 + 40);
      StringDraw.drawStringCenter(pen, "down  -  toggle gun direction", myGameWidth * 3 / 4, 290 + 40);
      StringDraw.drawStringCenter(pen, "up, left, right  -  movement", myGameWidth * 3 / 4, 320 + 40);

      pen.setColor(new Color(50, 50, 50, 100));
      pen.setFont(myKeyFont);
      StringDraw.drawStringCenter(pen, "[Press your keys...]", myGameWidth / 2, 400);

      for (InstructRect rect: myInstructRects)
         rect.draw(pen);

      getGUIManager().draw(pen);
   }

   @Override
   public void update() {
      boolean readyToStart = true;
      for (InstructRect rect: myInstructRects) {
         if (rect.isActivated() == false)
            readyToStart = false;
      }
      if (readyToStart) {
         getGamePanel().setGameMode("play");
         getGUIManager().resetInputs();
      }
   }

   @Override
   public KeyAdapter getKeyListener() { return myKeyListener; }
   @Override
   public MouseAdapter getMouseListener() { return myMouseListener; }

   private class KeyListener extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent event) {
         for (InstructRect rect: myInstructRects)
            rect.keyPressed(event);
      }
   }

   private class MouseListener extends MouseAdapter {
      @Override
      public void mousePressed(MouseEvent event) {
         getGUIManager().mousePressed(event);
      }
      @Override
      public void mouseMoved(MouseEvent event) {
         getGUIManager().mouseMoved(event);
      }
   }

   public class MainMenuListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent event) {
         getGamePanel().setGameMode("mainmenu");
         getGUIManager().resetInputs();
      }
   }

   public class SkipListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent event) {
         getGamePanel().setGameMode("play");
         getGUIManager().resetInputs();
      }
   }

   private class InstructRect {
      private String myText;
      private Color myColor;
      private Font myFont;
      private int myKeyCode;
      private int myPositionX;
      private int myPositionY;
      private int myWidth;
      private int myHeight;
      private boolean myIsActivated;
      private int myTick;
      private int myBlinkDelay;

      public InstructRect(String text, Color color, Font font, int keyCode, int positionX,
            int positionY, int width, int height) {

         myText = text;
         myColor = color;
         myFont = font;
         myKeyCode = keyCode;
         myPositionX = positionX;
         myPositionY = positionY;
         myWidth = width;
         myHeight = height;
         myIsActivated = false;
         myTick = 23;
         myBlinkDelay = 45;
      }

      public void draw(Graphics pen) {
         if (myTick % myBlinkDelay >= myBlinkDelay / 2 || myIsActivated) {
            pen.setColor(myColor);
            pen.fillRect(myPositionX, myPositionY, myWidth, myHeight);
            int xPos = myPositionX + myWidth / 2;
            int yPos = myPositionY + myHeight / 2;
            pen.setColor(Color.white);
            pen.setFont(myFont);
            StringDraw.drawStringCenter(pen, myText, xPos, yPos);
         }
         myTick++;
      }
      public void keyPressed(KeyEvent event) {
         if (event.getKeyCode() == myKeyCode && myIsActivated == false) {
            myIsActivated = true;
            new Sound("keytyped", true).play();
         }
      }
      public boolean isActivated() {
         return myIsActivated;
      }
   }
}