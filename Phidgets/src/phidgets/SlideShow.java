/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phidgets;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Dark
 */
public class SlideShow
{
    int SlideNr = 0;
    String IconName;
    String IconDescription = null;
    public void MainSlide()
    {
        this.SlideNr = 1;
    }
    public void NextSlide()
    {
        this.SlideNr += 1;
    }
    public void PrevSlide()
    {
        this.SlideNr -= 1;
    }
    public void HandleSelect()
    {
        switch (this.SlideNr)       // Dit zorgt ervoor dat de deuren naar de juiste dia gaan
        {
            case 1:
                this.SlideNr = 4;
                break;
            case 2:
                this.SlideNr = 6;
                break;
            case 3:
                this.SlideNr = 8;
                break;
            default:
                return;
        }
    }
    public String HandlePrevSlide()     // Dit zorgt ervoor dat we achteruit gaan indien mogelijk
    {
        switch (this.SlideNr)
        {
            case 0:
                return "Dia: "+(this.SlideNr-1)+" bestaat niet.";
            case 1:
                this.SlideNr = 3;       // als we op deur 1 zitten gaan we naar deur 3 (een loop)
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                PrevSlide();        // Vorige dia
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            default:
                MainSlide();        // Deur 1
                break;
        }
        return "Dia: "+this.SlideNr;
    }
    public String HandleNextSlide() throws IOException
    {
        switch (this.SlideNr)       // Zorg ervoor dat we niet content van andere dia's zien dan wat we gekozen hebben
        {
            case 0:
            case 1:
            case 2:
                NextSlide();
                break;
            case 3:
                MainSlide();
                break;
            case 4:
                NextSlide();
                break;
            case 5:
                MainSlide();
                break;
            case 6:
                NextSlide();
                break;
            case 7:
                MainSlide();
                break;
            case 8:
                NextSlide();
                break;
            case 9:
            default:
                MainSlide();
                break;
        }
        return "Dia: "+this.SlideNr;
    }
    public void HandleContent(JLabel ImageLabel, JTextArea TextArea) throws IOException
    {
        switch (this.SlideNr)       // Regel de tekst en de images per dia
        {
            case 0:
                ImageLabel.setSize(750, 400);
                ImageLabel.setIcon(createImageIcon(Integer.toString(this.SlideNr), IconDescription, 750, 400));
                break;
            case 1:
            case 2:
            case 3:
                TextArea.setText("Voor U ziet U 3 deuren. Achter elk van deze deuren schuilt een probleem\n" +
                        "dat betrekking heeft tot de sector handel, en dan specifiek de Webwinkel branche.\n" +
                        "Achter deur 1 zit: Slordige magazijnen\n" +
                        "Achter deur 2 zit: Lelijke websites\n" +
                        "Achter deur 3 zit: Rommelige servers\n" +
                        "Maak Uw keuze met behulp van de touch knoppen.");
                ImageLabel.setIcon(createImageIcon(Integer.toString(this.SlideNr), IconDescription, 850, 600));
                break;
            case 4:
                TextArea.setText("Op deze slide ziet U een onhandig magazijn.\n" +
                        "Wij van ITopia zijn van mening dat dit veel gemakkelijker kan.\n" +
                        "Met de creativiteit van de ITopia medewerkers\n" +
                        "komen zij vast met goede oplossingen.\n" +
                        "Zij zouden met unieke ideeën kunnen komen,\n" +
                        "zoals een soort robotarm die de producten uit\n" +
                        "het magazijn kunnen halen, volledig geautomatiseerd.");
                ImageLabel.setSize(850, 600);
                ImageLabel.setIcon(createImageIcon(Integer.toString(this.SlideNr), IconDescription, 850, 600));
                break;
            case 5:
                TextArea.setText("Dit zou een van de innovatieve oplossingen kunnen zijn\n" +
                        "waar de medewerkers mee zouden komen.\n" +
                        "Zij bouwen vervolgens een prototype en als uw keuze hier op valt,\n" +
                        "kunnen zij het voor u waar maken.\n" +
                        "Zij zullen alle benodigde software schrijven en U uitleggen hoe U het in gebruik moet nemen.\n" +
                        "Op deze manier heeft U een mooie oplossing voor Uw probleem.");
                ImageLabel.setSize(850, 600);
                ImageLabel.setIcon(createImageIcon(Integer.toString(this.SlideNr), IconDescription, 850, 600));
                break;
            case 6:
                TextArea.setText("Achter deze deur ziet u een webwinkel, met een wat slordige lay-out.\n" +
                        "Wij van ITopia kunnen goede oplossingen bedenken, zoals de kleuren, menu, etc.\n" +
                        "Met een slordige webwinkel zult u weinig klanten trekken. \n" +
                        "Op deze slide ziet U een slordige webwinkel.");
                ImageLabel.setSize(850, 600);
                ImageLabel.setIcon(createImageIcon(Integer.toString(this.SlideNr), IconDescription, 850, 600));
                break;
            case 7:
                TextArea.setText("ITopia heeft met haar gemotiveerde werknemers daar een goede oplossing voor.\n" +
                        "Dit is wat ITopia kan doen voor de webwinkels.");
                ImageLabel.setSize(850, 600);
                ImageLabel.setIcon(createImageIcon(Integer.toString(this.SlideNr), IconDescription, 850, 600));
                break;
            case 8:
                TextArea.setText("Achter deze deur ziet U een serverkast, zoals U in menig bedrijf tegenkomt.\n" +
                        "Deze wordt vaak over het hoofd gezien, en is dus meestal behoorlijk rommelig en nog belangrijker: slecht onderhouden.\n" +
                        "Het is belangrijk om de snoeren goed te ordenen en nummeren, om irritatie en ongemak te voorkomen in geval van problemen.\n" +
                        "Zoals U aan de kast op de volgende slide ziet, moet er een hoop gebeuren.");
                ImageLabel.setSize(500, 650);
                ImageLabel.setIcon(createImageIcon(Integer.toString(this.SlideNr), IconDescription, 500, 650));
                break;
            case 9:
                TextArea.setText("Wilt U het laten ordenen door professionals, dan kunt U dit met een gerust hart overlaten aan ITopia." +
                        "Het resultaat nadat de experts van ITopia bezig zijn geweest is hierboven te zien.");
                ImageLabel.setSize(450, 750);
                ImageLabel.setIcon(createImageIcon(Integer.toString(this.SlideNr), IconDescription, 450, 750));
                break;
            default:
                MainSlide();
                return;
        }
    }
    public ImageIcon createImageIcon(String path, String description, int width, int height) throws IOException
    {
        if (path != null)
        {
            if (description == null)
                description = "Nothing to see here, move along";
            if (getClass().getResource(path+".jpg") == null)        // Als we "dianummer".jpg niet kunnen vinden, probeer het dan met .png
                path = path+".png";
            else
                path = path+".jpg";
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null)     // Als het plaatje bestaat
            {
                if(imgURL.openConnection().getContentLength() > 0)      // En het is inderdaad een object met een grootte
                {
                    ImageIcon raw = new ImageIcon(imgURL, description);         // Neem het ruwe plaatje
                    Image img = raw.getImage();
                    BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);     // Buffer het plaatje, maak het leesbaar per deeltje
                    Graphics g = bi.createGraphics();
                    g.drawImage(img, 0, 0, width, height, null);        // maak het plaatje opniew vanuit de buffer...maar nu met de nieuwe grootte
                    return new ImageIcon(bi);       // stuur het plaatje terug naar de content handler
                }
                else
                    return null;
            }
            else
                return null;
        }
        else
            return null;
    }
}