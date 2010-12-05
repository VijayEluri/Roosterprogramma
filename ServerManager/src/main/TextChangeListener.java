/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.PlayerCharacter;

/**
 *
 * @author Dark
 */
public class TextChangeListener extends Thread{
    private String PrevText = "";
    private JTextField SearchBar;
    private JComboBox ResultList;

    public TextChangeListener(JTextField SearchBar, JComboBox ResultList)
    {
        this.SearchBar = SearchBar;
        this.ResultList = ResultList;
    }

    @Override
    public void run()
    {
        while(true)
        {
            if (SearchBar.getText().equals(PrevText))
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TextChangeListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (SearchBar.getText().equals("") || SearchBar.getText().isEmpty() == true)
            {
                if (ResultList.getItemCount() != 0)
                    ResultList.removeAllItems();
            }
            else
            {
                PrevText = SearchBar.getText();
                if (ResultList.getItemCount() != 0)
                    ResultList.removeAllItems();
                List<PlayerCharacter> Characters = ServerManager.getQueryManager().getCharacters(SearchBar.getText());
                for (PlayerCharacter character : Characters)
                {
                    if (!character.getCharname().equals(""))
                        ResultList.addItem(character.getCharname());
                }
            }
        }
    }
}
