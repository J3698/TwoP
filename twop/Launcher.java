package twop;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Launcher {
   private GamePanel myGamePanel;

   public Launcher(GamePanel gamePanel) {
      myGamePanel = gamePanel;
   }

   public void checkAndInstallUpdates() {
      if (!isLatestVersion()) {
         downloadLatestVersion();
      }
   }

   public boolean isLatestVersion() {
      // get arrays of decimal places for versions
      String[] currentVers = getCurrentVersion().split("\\.");
      String[] latestVers =  getLatestVersion().split("\\.");
      // keep track of current decimal place
      int index = 0;
      int currentVersInt;
      int latestVersInt;
      // until we run out of indices
      while (index != currentVers.length && index != latestVers.length) {
         // avoid formatting errors
         if (currentVers[index].equals("") || latestVers[index].equals("")) {
            JOptionPane.showMessageDialog(myGamePanel, "Could not read version number.");
            break;
         }

         // get next nubmers to compare
         currentVersInt = Integer.parseInt(currentVers[index]);
         latestVersInt = Integer.parseInt(latestVers[index]);
         // compare numbers
         if (currentVersInt < latestVersInt) {
            JOptionPane.showMessageDialog(myGamePanel, "No new updates.");
            return true;
         } else if (currentVersInt > latestVersInt) {
            return false;
         }
         // move to next index
         index++;
      }
      // handle if indices ahve been exhausted
      if (index == currentVers.length && index == latestVers.length) {
         JOptionPane.showMessageDialog(myGamePanel, "No new updates.");
         return true;
      }
      else if (index == currentVers.length) {
         return false;
      } else if (index == latestVers.length) {
         JOptionPane.showMessageDialog(myGamePanel, "No new updates.");
         return true;
      }
      // because we need a return statement
      return true;
   }

   public String getLatestVersion() {
      // declare URL, reader and version number
      URL latestVersionURL;
      BufferedReader fromWeb = null;
      String latestVersion = "";

      try {
         // get latest version
         latestVersionURL = new URL("https://github.com/J3698/TwoP/raw/master/twop/current.version");
         fromWeb = new BufferedReader(
               new InputStreamReader(latestVersionURL.openStream()));
         latestVersion = fromWeb.readLine();
      } catch(UnknownHostException e) {
         JOptionPane.showMessageDialog(myGamePanel, "Couldn't connect to server.");
      } catch (Exception e) {
         // print errors
         e.printStackTrace();
      } finally {
         try {
            // close reader
            if (fromWeb != null) { fromWeb.close(); }
         } catch (Exception e) {
            // print errors
            e.printStackTrace();
         }
      }
      // return latest version number
      return latestVersion;
   }

   public String getCurrentVersion() {
      // declare reader and version number
      BufferedReader fromHere = null;
      String currentVersion = "";

      try {
         // instantiate reader and get version number
         fromHere = new BufferedReader(
               new InputStreamReader(getClass().getClassLoader().getResourceAsStream("twop/current.version")));
         currentVersion = fromHere.readLine();
      } catch (Exception e){
         // print error
         e.printStackTrace();
      } finally {
         try {
            // close reader if instantiated
            if (fromHere != null) {
               fromHere.close();
            }
         } catch(Exception e) {
            // print error
            e.printStackTrace();
         }
      }
      // return version number
      return currentVersion;
   }

   public boolean downloadLatestVersion() {
      // set variables for creating file name
      String fileName = "TwoP" + ("" + getCurrentVersion()).replace(".", "_") + ".jar";
      File file = new File(fileName);
      int number = 0;
      // get available file name
      while (file.exists() && !file.isDirectory()) {
         number++;
         fileName = "TwoP" + ("" + getCurrentVersion()).replace(".", "_") + "(" + number + ")" + ".jar";
         file = new File(fileName);
      }

      URL downloadSite;
      InputStream in = null;
      ByteArrayOutputStream out = null;
      FileOutputStream fileWriter = null;
      byte[] buffer = new byte[1024 * 16];
      int n = 0;

      try {
         downloadSite = new URL("https://github.com/J3698/TwoP/blob/master/release/TwoP.jar?raw=true");

         in = new BufferedInputStream(downloadSite.openStream());
         out = new ByteArrayOutputStream();

         while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
         }

         byte[] response = out.toByteArray();

         fileWriter = new FileOutputStream(fileName);

         fileWriter.write(response);
      } catch(Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (out != null) {
               out.close();
            }
            if (in != null) {
               in.close();
            }
            if (fileWriter != null) {
               fileWriter.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      JOptionPane.showMessageDialog(myGamePanel,
            "Check the folder containing this jar for the latest version of TwoP!");

      return true;
   }
}
