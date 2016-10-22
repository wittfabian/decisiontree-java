package algorithm;

import structure.Component;
import java.util.ArrayList;

/**
 * Class with the main method to execute the program.
 * @author Steven Brandt and Fabian Witt
 */
public class LearnDecTree {
    
    private static final String C_SEPERATOR = ",";
    private static ArrayList<String[]> m_data;
    private static Component m_tree;
    
    /**
     * Main method to load the training data, learn the decision tree
     * and print the finally tree.
     * @param args Expects two arguments (infile outfile)
     */
    public static void main(String[] args) {

        if(args.length==2){
            String infile = args[0];
            String outfile = args[1];

            //get the training data from the input file
            Load m_loader = new Load(infile, C_SEPERATOR);
            m_data = m_loader.getData();

            //learn the tree and get it as ArrayList
            Learn m_learner = new Learn(m_data);
            m_tree = m_learner.getTree();

            //print the tree in different ways
            Print m_printer = new Print(m_tree);
            //m_printer.printConsole();
            m_printer.printXML(outfile);
        }
        else{
            System.out.println("[ERROR] Wrong parameters set. (infile outfile)");
        }
    }
    
}
