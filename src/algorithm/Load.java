package algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class will prepare the data to learn the decision tree.
 * @author Steven Brandt and Fabian Witt
 */
public class Load {
    
    private final ArrayList<String[]> m_data;
    private final String m_path;
    private final String m_seperator;
    
    /**
     * The constructor sets the path of the file and automatically generates
     * the training data.
     * @param path String value of the input file path. 
     * @param seperator String value of Data-Seperator
     */
    public Load(String path, String seperator) {
        this.m_data = new ArrayList<>();
        this.m_path = path;
        this.m_seperator = seperator;
        loadTrainingData();
    }

    /**
     * Getter for the ArrayList with the training data.
     * @return Returns the training data.
     */
    public ArrayList<String[]> getData() {
        return m_data;
    }
    
    /**
     * Method to read the training data from the input file. 
     */
    private void loadTrainingData(){
        BufferedReader br;
        if(m_data != null){
            m_data.clear();
        }
        
        try {
            String currentLine;
            br = new BufferedReader(new FileReader(m_path));
            
            while ((currentLine = br.readLine()) != null) {
                
                m_data.add( currentLine.split(m_seperator) );
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Reading input file failes!");
        }
    }
}
