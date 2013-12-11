import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class TheOnlyHandleOfTrueFiles {

	private String user;

	BufferedReader input;
	BufferedWriter output;
	
	//Gives me permission to read from files and use them.
	
	public TheOnlyHandleOfTrueFiles(String filename) throws FileNotFoundException{
		FileReader fileStream = new FileReader(filename);
		input = new BufferedReader(fileStream);
	}
	
	public String readFromFile() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}
	
	//Were the program starts to "read" from the txt file
	
	public String readWholeFile(){
		String text = "";
		String currentLine = "";
		
		currentLine = readFromFile();
		
		while( currentLine != null){
			text += currentLine + System.lineSeparator();
			currentLine = readFromFile();
		}
		
		return text;
	}
	
	//Here I will do the write to file 
	
	public void writeToSystem(String filename) {
		try {
			FileWriter fw;
			fw = new FileWriter(filename);
			output = new BufferedWriter(fw);
			output.write(user);
			output.flush();
			output.close();
		}catch (IOException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
		}
	}
	
	public void setUsername(String t) {
	user = t;	
	writeToSystem("username.txt");
	}

}
