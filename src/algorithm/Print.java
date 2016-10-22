package algorithm;

import structure.Component;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * This class will print the learned tree in different ways.
 * @author Steven Brandt and Fabian Witt
 */
public class Print {
    
    private final Component m_tree;

    /**
     * This constructor will create a print object.
     * @param tree A component object of the tree.
     */
    public Print(Component tree) {
        this.m_tree = tree;
    }
    
    /**
     * This method will print the tree to the console.
     */
    public void printConsole(){
        
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        
        m_tree.print();
    }
    
    /**
     * This method will print the tree in a XML file to a certain path.
     * @param outfile The path were the file will be printed.
     */
    public void printXML(String outfile){
        
        try (PrintWriter writer = new PrintWriter(outfile, "UTF-8")) {
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            m_tree.write(writer);
        } catch (FileNotFoundException e){
            System.out.println("FileNotFoundException");
        } catch(UnsupportedEncodingException e){
            System.out.println("UnsupportedEncodingException");
        }
    }
}
