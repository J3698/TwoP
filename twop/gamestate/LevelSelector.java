package twop.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import twop.GamePanel;
import twop.Player;
import twop.campaign.Platform;
import twop.gui.InstructionsButton;
import twop.physics.PhysicsManager;
import twop.util.Vector2;

public class LevelSelector extends GameState {
   private int myGameWidth;
   private int myGameHeight;

   private KeyListener myKeyListener;
   private MouseListener myMouseListener;

   private PhysicsManager myPhysicsManager;
   private Player myPlayer;
   private ArrayList<Platform> myPlatforms;

   private int tick = 1;
   private int tickDir = 1;

   public LevelSelector(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super(gamePanel, "levelselector");
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myKeyListener = new KeyListener();
      myMouseListener = new MouseListener();
      myPhysicsManager = new PhysicsManager();
      myPlayer = new Player(new Vector2(400, 400), 19, new Rectangle(0, 0, 1000, 1000));
      this.getGUIManager().addButton(new InstructionsButton(
            new LeaveListener(), "Leave", new Vector2(480, 450), myGameWidth, myGameHeight));

      // need list of campaign objects to draw
      myPlatforms = new ArrayList<Platform>();

      Platform firstPlatform = new Platform(new Vector2(300, 900), 200, 30);

      myPlatforms.add(firstPlatform);
      //      myPlatform = new Platform(new Vector2(300, 900), 200, 30);

      myPhysicsManager.add(myPlayer.getPhysics());
      myPhysicsManager.add(firstPlatform.getPhysics());
   }

   public void init() {
      myPlayer.getControls().setSecondControls();
      getGamePanel().getCamera().setImageSize(1000, 1000);
   }

   @Override
   public void draw(Graphics pen) {
      fixCamera();

      pen.setColor(Color.black);
      pen.fillRect(0, 0, 1000, 1000);

      drawBackground(pen);

      myPlayer.draw(pen);

      for (Platform platform : myPlatforms) {
         platform.draw(pen);
      }

      getGUIManager().draw(pen);
   }

   public void drawBackground(Graphics pen) {
      pen.setColor(Color.white);
      pen.fillRect(-1, 01, 1002, 1002);

      Vector2 centerOffset = new Vector2(500, 500);
      centerOffset.subtractVector(myPlayer.getCenter());
      centerOffset.divideX(3);
      int xOffset = (int) centerOffset.getX();
      int yOffset = (int) centerOffset.getY();

      pen.setColor(Color.blue);
      for (int x = 0; x < 100; x++){
         for (int y = 0; y < 100; y++){
            pen.drawRect(x * 70, y * 70, 40, 40);
         }
      }

      pen.setColor(Color.black);
      for (int x = 0; x < 100; x++){
         for (int y = 0; y < 100; y++){
            pen.drawRect(xOffset + x * 40, 2 * yOffset + y * 40, 30, 30);
         }
      }

      pen.setColor(Color.gray);
      for (int x = 0; x < 100; x++){
         for (int y = 0; y < 100; y++){
            pen.fillOval(2 * xOffset + x * 80, yOffset + y * 80, 60, 60);
         }
      }
   }

   @Override
   public void update() {
      myPlayer.update();
      myPhysicsManager.update();
   }

   public void fixCamera() {
      Vector2 newPos1 = myPlayer.getCenter().copy();
      Vector2 newPos2 = myPlayer.getCenter().copy();
      Vector2 half = new Vector2(getGamePanel().getGameWidth() / 2, getGamePanel().getGameHeight() / 2);
      newPos1.subtractVector(half);
      newPos2.addVector(half);
      getGamePanel().getCamera().setPerspective(newPos1, newPos2);
   }

   private class MouseListener extends MouseAdapter {
      @Override
      public void mousePressed(MouseEvent event) {
         getGUIManager().mousePressed(event);

         Vector2 platformPosition = new Vector2(event.getX() - getGamePanel().getCamera().getPos1().getX(),
               event.getY() - getGamePanel().getCamera().getPos1().getY());
         Platform newPlatform= new Platform(platformPosition, 200, 30);
         myPlatforms.add(newPlatform);
         myPhysicsManager.add(newPlatform.getPhysics());
      }

      @Override
      public void mouseMoved(MouseEvent event) {
         getGUIManager().mouseMoved(event);
      }
   }






   @Override
   public KeyAdapter getKeyListener() {
      return myKeyListener;
   }

   @Override
   public MouseAdapter getMouseListener() {
      return myMouseListener;
   }

   private class KeyListener extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent event) {
         myPlayer.getControls().keyDown(event);
         if (event.getKeyCode() == KeyEvent.VK_R) {
         }
      }
      @Override
      public void keyReleased(KeyEvent event) {
         myPlayer.getControls().keyUp(event);
      }
   }

   public class LeaveListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent event) {
         getGamePanel().setGameMode("mainmenu");
         getGamePanel().getCamera().reset();
         getGUIManager().resetInputs();
      }
   }
}
