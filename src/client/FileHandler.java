package client;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileHandler {

	Properties prop = new Properties();

	FileHandler(){
		try {
			prop.load(new FileInputStream("network.properties"));
			//saveFile();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	//method for saving file, not used in the program. Only used it to create the properties file
	public void saveFile(){
		try {
			//set the properties value
			prop.setProperty("ip", "127.0.0.1");
			prop.setProperty("port", "52000");			
			//save properties to project root folder
			prop.store(new FileOutputStream("network.properties"), null);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String getProperty(String property){
		String p = prop.getProperty(property);
		return p;
	}

}
