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
        switch (this.SlideNr)
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
    public String HandlePrevSlide()
    {
        switch (this.SlideNr)
        {
            case 0:
                return "Dia: "+(this.SlideNr-1)+" bestaat niet.";
            case 1:
                this.SlideNr = 3;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                PrevSlide();
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            default:
                MainSlide();
                break;
        }
        return "Dia: "+this.SlideNr;
    }
    public String HandleNextSlide() throws IOException
    {
        switch (this.SlideNr)
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
        switch (this.SlideNr)
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
            case 5:
            case 6:
            case 7:
                ImageLabel.setSize(850, 600);
                ImageLabel.setIcon(createImageIcon(Integer.toString(this.SlideNr), IconDescription, 850, 600));
                break;
            case 8:
                ImageLabel.setSize(500, 650);
                ImageLabel.setIcon(createImageIcon(Integer.toString(this.SlideNr), IconDescription, 500, 650));
                break;
            case 9:
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
            if (getClass().getResource(path+".jpg") == null)
                path = path+".png";
            else
                path = path+".jpg";
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null)
            {
                if(imgURL.openConnection().getContentLength() > 0)
                {
                    ImageIcon raw = new ImageIcon(imgURL, description);
                    Image img = raw.getImage();
                    BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                    Graphics g = bi.createGraphics();
                    g.drawImage(img, 0, 0, width, height, null);
                    return new ImageIcon(bi);
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