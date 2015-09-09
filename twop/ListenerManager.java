package twop;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import twop.gamestate.GameState;

public class ListenerManager {
   private GamePanel myGamePanel;

   public ListenerManager(GamePanel gamePanel) {
      myGamePanel = gamePanel;
   }

   public void addListeners() {
      myGamePanel.addKeyListener(new KeyListener());
      myGamePanel.addMouseListener(new MouseListener());
      myGamePanel.addMouseMotionListener(new MouseListener());
      myGamePanel.addFocusListener(new FocusListener());
   }

   public class KeyListener extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent event) {
         for (GameState gameState : myGamePanel.getGameStates()) {
            if (gameState.getGameMode().equals(myGamePanel.getGameMode())) {
               gameState.getKeyListener().keyPressed(event);
               break;
            }
         }
      }
      @Override
      public void keyReleased(KeyEvent event) {
         for (GameState gameState : myGamePanel.getGameStates()) {
            if (gameState.getGameMode().equals(myGamePanel.getGameMode())) {
               gameState.getKeyListener().keyReleased(event);
               break;
            }
         }
      }
      @Override
      public void keyTyped(KeyEvent event) {
         for (GameState gameState : myGamePanel.getGameStates()) {
            if (gameState.getGameMode().equals(myGamePanel.getGameMode())) {
               gameState.getKeyListener().keyTyped(event);
               break;
            }
         }
      }
   }
   public class MouseListener extends MouseAdapter {
      @Override
      public void mouseClicked(MouseEvent event) {
         for (GameState gameState : myGamePanel.getGameStates()) {
            if (gameState.getGameMode().equals(myGamePanel.getGameMode())) {
               gameState.getMouseListener().mouseClicked(event);
               break;
            }
         }
      }
      @Override
      public void mouseDragged(MouseEvent event) {
         for (GameState gameState : myGamePanel.getGameStates()) {
            if (gameState.getGameMode().equals(myGamePanel.getGameMode())) {
               gameState.getMouseListener().mouseDragged(event);
               break;
            }
         }
      }
      @Override
      public void mouseEntered(MouseEvent event) {
         for (GameState gameState : myGamePanel.getGameStates()) {
            if (gameState.getGameMode().equals(myGamePanel.getGameMode())) {
               gameState.getMouseListener().mouseEntered(event);
               break;
            }
         }
      }
      @Override
      public void mouseExited(MouseEvent event) {
         for (GameState gameState : myGamePanel.getGameStates()) {
            if (gameState.getGameMode().equals(myGamePanel.getGameMode())) {
               gameState.getMouseListener().mouseExited(event);
               break;
            }
         }
      }
      @Override
      public void mouseMoved(MouseEvent event) {
         for (GameState gameState : myGamePanel.getGameStates()) {
            if (gameState.getGameMode().equals(myGamePanel.getGameMode())) {
               gameState.getMouseListener().mouseMoved(event);
               break;
            }
         }
      }
      @Override
      public void mousePressed(MouseEvent event) {
         for (GameState gameState : myGamePanel.getGameStates()) {
            if (gameState.getGameMode().equals(myGamePanel.getGameMode())) {
               gameState.getMouseListener().mousePressed(event);
               break;
            }
         }
      }
      @Override
      public void mouseReleased(MouseEvent event) {
         for (GameState gameState : myGamePanel.getGameStates()) {
            if (gameState.getGameMode().equals(myGamePanel.getGameMode())) {
               gameState.getMouseListener().mouseReleased(event);
               break;
            }
         }
      }
      @Override
      public void mouseWheelMoved(MouseWheelEvent event) {
         for (GameState gameState : myGamePanel.getGameStates()) {
            if (gameState.getGameMode().equals(myGamePanel.getGameMode())) {
               gameState.getMouseListener().mouseWheelMoved(event);
               break;
            }
         }
      }
   }
   private class FocusListener extends FocusAdapter {
      @Override
      public void focusLost(FocusEvent e) {
         for (GameState state : myGamePanel.getGameStates()) {
            state.getGUIManager().resetInputs();
         }
      }
   }
}





