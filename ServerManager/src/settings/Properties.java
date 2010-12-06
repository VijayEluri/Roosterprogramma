package settings;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Properties extends java.util.Properties {

	/**
	 *
	 */
	private static final  long serialVersionUID = 1938249952507849679L;
	private static FileInputStream input = null;
	private static FileWriter output = null;

	public Properties () {
        try {
            input = new FileInputStream("conf/settings.property");
            super.load(input);
            output = new FileWriter("conf/settings.property", true);
        } catch (IOException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }
	}


        @Override
	public Object setProperty(String key, String value) {
            Object result = super.setProperty(key, value);
            this.store("Update");
            return result;
	}


	public void store (String comments) {
            try {
                super.store(output, comments);
            } catch (IOException ex) {
                Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}