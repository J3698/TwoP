package twop;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Launcher {
   public Launcher() {}

   public boolean isLatestVersion() {
      if (getCurrentVersion() < getLatestVersion()) {
         return false;
      }
      return true;
   }

   public double getLatestVersion() {
      // declare URL, reader and version number
      URL latestVersionURL;
      BufferedReader fromWeb = null;
      double latestVersion = 0.0;

      try {
         // get latest version
         latestVersionURL = new URL("https://github.com/J3698/TwoP/raw/master/release/current.version");
         fromWeb = new BufferedReader(
               new InputStreamReader(latestVersionURL.openStream()));
         latestVersion = Double.parseDouble(fromWeb.readLine());
      } catch (Exception e) {
         // print errors and exit on error
         e.printStackTrace();
         System.exit(0);
      } finally {
         try {
            // close reader
            if (fromWeb != null) { fromWeb.close(); }
         } catch (Exception e) {
            // print errors and exit on error
            e.printStackTrace();
            System.exit(0);
         }
      }
      // return latest version number
      return latestVersion;
   }

   public double getCurrentVersion() {
      // declare reader and version number
      BufferedReader fromHere = null;
      double currentVersion = 0.0;

      try {
         // instantiate reader and get version number
         fromHere = new BufferedReader(
               new InputStreamReader(getClass().getClassLoader().getResourceAsStream("twop/current.version")));
         currentVersion = Double.parseDouble(fromHere.readLine());
      } catch (Exception e){
         // print error and exit on error
         e.printStackTrace();
         System.exit(0);
      } finally {
         try {
            // close reader if instantiated
            if (fromHere != null) {
               fromHere.close();
            }
         } catch(Exception e) {
            // print error and exit on error
            e.printStackTrace();
            System.exit(0);
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

      return true;
   }
}
