package connectivity;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
                    importRequiredSQLFiles();
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

    private void importRequiredSQLFiles() {
        List<String> importedFiles = RoosterProgramma.getQueryManager().getImportedFiles();
        for (String sqlFile : getSQLFiles())
        {
            if (!importedFiles.contains(sqlFile))
            {
                importSQLFile(sqlFile);
            }
        }
    }

    private void importSQLFile(String sqlFile) {
        URL fileUrl = null;
        try {
            fileUrl = new URL("https://github.com/Darkrulerz/Netbeans/raw/master/RoosterProgramma/sql/" + sqlFile);
        } catch (MalformedURLException ex) {
            Logger.getLogger(UpdateChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(fileUrl.openStream()));
            String pasteLine = "";
            boolean read = true;
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                inputLine = inputLine.trim();
                if (inputLine.contains("/*") && !inputLine.contains("*/"))
                {
                    read = false;
                }
                else if (inputLine.contains("*/"))
                {
                    read = true;
                }

                if (read)
                {
                    if (inputLine.contains("/*") || inputLine.contains("*/") || inputLine.contains("***") || inputLine.isEmpty())
                    {
                        continue;
                    }
                    else if(!inputLine.substring(inputLine.length()-1, inputLine.length()).equals(";"))
                    {
                        pasteLine = pasteLine + " " + inputLine;
                    }
                    else
                    {
                        if (!pasteLine.isEmpty())
                        {
                            inputLine = pasteLine + " " + inputLine;
                            pasteLine = "";
                        }
                        RoosterProgramma.getQueryManager().doQuery(inputLine, sqlFile);
                    }
                }
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(UpdateChecker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(UpdateChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private List<String> getSQLFiles() {
        List<String> SQLFiles = new ArrayList<String>();
        try {
            URL github = new URL("https://github.com/Darkrulerz/Netbeans/tree/master/RoosterProgramma/sql/");
            URLConnection url = github.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(url.getInputStream()));
            boolean read = false;
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                if (inputLine.contains("<table"))
                {
                    read = true;
                }
                else if (inputLine.contains("</table>"))
                {
                    break;
                }

                if (read)
                {
                    if (inputLine.contains(".sql"))
                    {
                        inputLine = inputLine.split("/RoosterProgramma/sql/")[1];
                        inputLine = inputLine.split("\" class=\"js-slide-to\"")[0];
                        SQLFiles.add(inputLine);
                    }
                }
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SQLFiles;
    }
}