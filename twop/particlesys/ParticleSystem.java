package twop.particlesys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import twop.particlesys.particle.AsteroidParticle;
import twop.particlesys.particle.FireParticle;
import twop.particlesys.particle.Particle;
import twop.particlesys.particle.PoisonParticle;
import twop.particlesys.particle.RainParticle;
import twop.util.Vector2;

public class ParticleSystem {
   private ArrayList<Particle> myParticles;

   private Vector2 mySourcePosition;
   private Vector2 myMaxSourceOffset;;
   private Vector2 myInitialVelocity = new Vector2(0, 0);
   private String myParticleType;
   private int myEmissionRate = 1;
   private double myLowerAngleLimit = -Math.PI;
   private double myUpperAngleLimit = Math.PI;

   public ParticleSystem(String particleType, Vector2 position, Vector2 velocity,
         double lowerAngleLimit, double upperAngleLimit) {
      myParticleType = particleType;
      mySourcePosition = position;
      myInitialVelocity = velocity;
      myLowerAngleLimit = lowerAngleLimit;
      myUpperAngleLimit = upperAngleLimit;
      myParticles = new ArrayList<Particle>();
   }

   public ParticleSystem(String particleType, Vector2 position) {
      myParticleType = particleType;
      mySourcePosition = position;
      myParticles = new ArrayList<Particle>();
      parseParticleType();
   }

   private void parseParticleType() {
      if (myParticleType.equals("fire")) {
         myMaxSourceOffset = new Vector2(30, 30);
         myInitialVelocity = new Vector2(0, -3);
         myLowerAngleLimit = -Math.toRadians(40);
         myUpperAngleLimit = Math.toRadians(40);

      } else if (myParticleType.equals("poison")) {
         myMaxSourceOffset = new Vector2(30, 30);

      } else if (myParticleType.equals("rain")) {
         myMaxSourceOffset = new Vector2(640, 0);
         myInitialVelocity = new Vector2(0, 10);
         myLowerAngleLimit = -Math.toRadians(1);
         myUpperAngleLimit = Math.toRadians(1);

      } else if (myParticleType.equals("asteroid")) {
      } else {
         System.out.println("Unknown Particle Type");
      }
   }

   public void update() {
      Particle temp;
      for (int i = 0; i < myParticles.size(); i++) {
         temp = myParticles.get(i);
         if (temp.isDead()) {
            myParticles.remove(temp);
         } else {
            temp.update();
         }
      }
      for (int i = 0; i < myEmissionRate; i++) {
         createParticles();
      }
   }

   public void draw(Graphics pen) {
      for (int i = 0; i < myParticles.size(); i++) {
         myParticles.get(i).draw(pen);
      }
   }

   public void createParticles() {
      Vector2 position = mySourcePosition.copy();
      if (myMaxSourceOffset != null) {
         double xLowerLim = -(myMaxSourceOffset.getX() / 2);
         double yLowerLim = -(myMaxSourceOffset.getY() / 2);
         double xUpperLim = myMaxSourceOffset.getX() / 2;
         double yUpperLim = myMaxSourceOffset.getY() / 2;
         position.addVector(Vector2.randSquareVector(xLowerLim, yLowerLim, xUpperLim, yUpperLim));
      }

      Vector2 velocity = myInitialVelocity.copy();

      double angle = myLowerAngleLimit + new Random().nextDouble()
            * (myUpperAngleLimit - myLowerAngleLimit);
      velocity.rotateRadians(angle);
      System.out.println(angle);

      if (myParticleType.equalsIgnoreCase("poison")) {
         if (new Random().nextDouble() < 0.01) {
            myParticles.add(new PoisonParticle(position, velocity));
         }
      } else if (myParticleType.equalsIgnoreCase("fire")) {
         myParticles.add(new FireParticle(position, velocity));
      } else if (myParticleType.equalsIgnoreCase("rain")) {
         myParticles.add(new RainParticle(position, velocity));
      } else if (myParticleType.equalsIgnoreCase("asteroid")) {
         myParticles.add(new AsteroidParticle(position, velocity));
      }
   }

   public Vector2 getSourcePosition() { return mySourcePosition; }
   public void setSourcePosition(Vector2 position) { mySourcePosition = position; }
   public void setSourceOffset(Vector2 offset) { myMaxSourceOffset = offset; }
   @SuppressWarnings("unused")
   private Vector2 getSourceOffset() { return myMaxSourceOffset; }

   public void setEmissionRate(int emissionRate) { myEmissionRate = emissionRate; }
   public void setEmissionAngles(double lowerLimit, double upperLimit) {
      myLowerAngleLimit = lowerLimit;
      myUpperAngleLimit = upperLimit;
   }
   public void setEmissionAngles(double angle) { setEmissionAngles(angle, angle); }
   public double[] getEmissionAngles() { return new double[]{ myLowerAngleLimit, myUpperAngleLimit }; }
   public void setInitialVelocity(Vector2 velocity) { myInitialVelocity = velocity; }
   public Vector2 getInitialVelocity() { return myInitialVelocity; }



   public static void main(String[] args) {
      JFrame frame = new JFrame("Particle Tester");
      frame.setSize(600, 600);
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new ParticlePanel());
      frame.setVisible(true);
   }
}

class ParticlePanel extends JPanel {
   private static final long serialVersionUID = 8683345521292327079L;
   private int refresh = 60;

   public ParticlePanel() {
      setFocusable(true);
      addMouseListener(new MouseAdapter() {});
      new Timer(refresh, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent event) {
            update();
            repaint();
         }}).start();
   }

   @Override
   public void paintComponent(Graphics pen) {
      pen.setColor(Color.black);
      pen.fillRect(-1, -1, 601, 601);
   }
   public void update() {
   }
}