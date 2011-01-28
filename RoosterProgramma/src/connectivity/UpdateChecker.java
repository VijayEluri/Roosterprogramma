package connectivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import roosterprogramma.RoosterProgramma;
import view.Login;

public class UpdateChecker {

    private static final String APPLICATIEFILE = "WinkelApplicatieOut2.jar";
    private static final String APPLICATIELINK = "http://oege.ie.hva.nl/~fritz10/Outfit4You/WinkelApplicatieOut2.jar";

    public int checkForUpdate() {
        if (hasInternet())
        {
            if (!isLatest())
            {
                if (RoosterProgramma.getInstance().promptWarning("Er is een update beschikbaar!\n"
                        + "Wilt u nu de update installeren?"))
                {
                    installNewVersion();
                }
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            return 2;
        }
    }

    private boolean hasInternet() {
        try {
            URL google = new URL("http://www.google.com");
            URLConnection urlc = google.openConnection();
            Object objData = urlc.getContent();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(UpdateChecker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private boolean isLatest() {
        boolean latest = true;
        try {
            Date lastModified = new Date(new URL(APPLICATIELINK).openConnection().getLastModified());
            File executable = new File(APPLICATIEFILE);
            System.out.println(lastModified.getTime() + " > " + executable.lastModified());
            if (lastModified.getTime() > executable.lastModified())
            {
                latest = false;
            }
        } catch (IOException ex) {
            Logger.getLogger(UpdateChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return latest;
    }

    public void installNewVersion() {
        OutputStream outStream = null;
        try {
            File oldFile = new File(APPLICATIEFILE);
            if (oldFile.isFile())
            {
                oldFile.delete();
            }

            URL Url = new URL(APPLICATIELINK);    //  Dit gaat gehost worden op me NAS nadat ik em heb
            outStream = new BufferedOutputStream(new FileOutputStream(APPLICATIEFILE));
            URLConnection uCon = Url.openConnection();
            InputStream is = uCon.getInputStream();
            byte[] buf = new byte[1024];
            int ByteRead;
            while ((ByteRead = is.read(buf)) != -1)
            {
                outStream.write(buf, 0, ByteRead);
            }

            RoosterProgramma.getInstance().showPanel(new Login(0));
        } catch (IOException ex) {
            Logger.getLogger(UpdateChecker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outStream.close();
            } catch (IOException ex) {
                Logger.getLogger(UpdateChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}