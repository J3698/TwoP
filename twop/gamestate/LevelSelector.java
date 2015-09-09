package twop.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import twop.GamePanel;
import twop.Player;
import twop.campaign.Platform;
import twop.physics.PhysicsManager;
import twop.util.Vector2;

public class LevelSelector extends GameState {
   private int myGameWidth;
   private int myGameHeight;

   private KeyListener myKeyListener;
   private MouseListener myMouseListener;

   private PhysicsManager myPhysicsManager;
   private Player myPlayer;
   private Platform myPlatform;

   public LevelSelector(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super(gamePanel, "levelselector");
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myKeyListener = new KeyListener();
      myMouseListener = new MouseListener();
      myPhysicsManager = new PhysicsManager();
      myPlayer = new Player(new Vector2(400, 400), 19, new Rectangle(0, 0, 1000, 1000));
      myPlatform = new Platform(new Vector2(300, 900), 200, 30);
      myPhysicsManager.add(myPlayer.getPhysics());
      myPhysicsManager.add(myPlatform.getPhysics());
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

      pen.setColor(Color.red);
      for (int x = 0; x < 10; x++) {
         pen.fillRect(x * 100, 0, 50, 1000);
      }
      pen.setColor(Color.blue);
      for (int y = 0; y < 100; y++) {
         pen.fillRect(0, y * 100, 1000, 50);
      }

      myPlayer.draw(pen);
      myPlatform.draw(pen);
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
      }
      @Override
      public void keyReleased(KeyEvent event) {
         myPlayer.getControls().keyUp(event);
      }
   }

   private class MouseListener extends MouseAdapter {
   }
}
