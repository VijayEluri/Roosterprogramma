package settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *	Handle loading and retrieving of predetermined settings
 *	such as database connection info, server port, etc.
 *
 * 	@author Matthijs
 */
public class Settings {

	public static Hashtable<String, Properties> settings = null;


	/**
	 *	Settings constructor. Attempts to load the main configuration files.
	 *
	 *	If that fails the user should get a message notifying him as to why.
	 */
	public Settings () {
		settings = new Hashtable<String, Properties> ();
		loadSettings("database");
		loadSettings("server");
		loadSettings("mangos");
	}


	/**
	 * 	Obtain the value for the given field in String format
	 * 	as stored in the configuration file loaded. If no
	 * 	configuration file is loaded this will probably not be so
	 * 	pretty :]
	 *
	 * 	@param 	setting		Name of the setting to obtain.
	 * 	@return				The value of the requested setting.
	 */
	public static String get(String type, String setting) {
	    return ((Properties) settings.get(type)).getProperty(setting);
	}


	/**
	 *	Load a given configuration file. This will attempt to
	 *	load the file given by 'name'[.conf] and store its contents
	 *	in the hash table settings under that name.
	 *
	 * 	@param name	The name of the configuration.
	 */
	private void loadSettings (String name) {
            try {
                Properties configuration = new Properties();
                configuration.load(new FileInputStream("conf/" + name + ".conf"));
                settings.put(name, configuration);
            } catch (IOException ex) {
                Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}