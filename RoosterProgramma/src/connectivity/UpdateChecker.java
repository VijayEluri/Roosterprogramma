package connectivity;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import roosterprogramma.RoosterProgramma;
import view.Login;

public class UpdateChecker {

    public int checkForUpdate() {
        String latestVersion = getLatestVersion();
        String currentVersion = getVersion();
        if (!latestVersion.isEmpty())
        {
            if (currentVersion.isEmpty())
            {
                int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Er kan niet worden gecontroleerd op updates omdat het versiebestand ontbreekt.\nDit kan worden opgelost door de applicatie opnieuw te installeren.\nEr zullen geen gegevens verloren gaan.\nWilt u dit nu doen?",
                    "Versie bestand ontbreekt!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if (choice == JOptionPane.YES_OPTION)
                {
                    installNewVersion();
                }
                return 3;
            }
            else if(!currentVersion.equals(latestVersion))
            {
                int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Wilt u nu de update installeren?",
                    "Er is een update beschikbaar!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                if (choice == JOptionPane.YES_OPTION)
                {
                    installNewVersion();
                }
                return 1;
            }
            return 0;
        }
        else
        {
            return 2;
        }
    }

    private void installNewVersion() {
        // Start de thread dat de update gaat uitvoeren
        ClientUpdater updater = new ClientUpdater();
        updater.start();
    }

    private String getVersion() {
        String version = "";
        try {
            // Kijk wat de versie is die we hebben
            File versie = new File("includes/version.txt");
            if (versie.isFile())
            {
                FileReader fr = new FileReader(versie);
                BufferedReader br = new BufferedReader(fr);
                version = br.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(UpdateChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return version;
    }

    private String getLatestVersion() {
        String inputLine = "";
        try {
            // Kijk wat de versie op de website nu is
            URL versie = new URL("http://oege.ie.hva.nl/~fritz10/Outfit4You/version.txt");
            URLConnection url = versie.openConnection();
            if (url.getInputStream().available() != 0)
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(url.getInputStream()));
                inputLine = in.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(UpdateChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inputLine;
    }

    private class ClientUpdater extends Thread {

        private String ApplicatieFile = "WinkelApplicatieOut2.jar";
        private String VersionFile = "includes/version.txt";

        @Override
        public void run() {
            OutputStream outStream = null;
            try {
                File oldFile = new File(ApplicatieFile);
                File prevVersion = new File(VersionFile);
                if (oldFile.isFile())
                {
                    oldFile.delete();
                }

                if (prevVersion.isFile())
                {
                    prevVersion.delete();
                }

                URL Url = new URL("http://oege.ie.hva.nl/~fritz10/Outfit4You/WinkelApplicatieOut2.jar");
                outStream = new BufferedOutputStream(new FileOutputStream(ApplicatieFile));
                URLConnection uCon = Url.openConnection();
                InputStream is = uCon.getInputStream();
                byte[] buf = new byte[1024];
                int ByteRead;
                while ((ByteRead = is.read(buf)) != -1)
                {
                    outStream.write(buf, 0, ByteRead);
                }

                Url = new URL("http://oege.ie.hva.nl/~fritz10/Outfit4You/version.txt");
                outStream = new BufferedOutputStream(new FileOutputStream(prevVersion));
                uCon = Url.openConnection();
                is = uCon.getInputStream();
                buf = new byte[1024];
                while ((ByteRead = is.read(buf)) != -1)
                {
                    outStream.write(buf, 0, ByteRead);
                }

                RoosterProgramma.getInstance().showPanel(new Login(0));
            } catch (IOException ex) {
                Logger.getLogger(ClientUpdater.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    outStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(ClientUpdater.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}