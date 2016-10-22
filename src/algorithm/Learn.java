package algorithm;

import structure.Leaf;
import structure.Node;
import structure.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class will learn the decision tree from the training data.
 * @author Steven Brandt and Fabian Witt
 */
public class Learn {
    
    private final ArrayList<String[]> examples;
    double m_tree_entropy;
    private final Component m_tree;
    HashMap<String,Double> m_rootmap;
    
    /**
     * The constructor sets the training data and automatically learns
     * the decision tree.
     * @param data ArrayList of Strings with the training data.
     */
    public Learn(ArrayList<String[]> data) {
        examples = data;
        ArrayList<Integer> usedatts = new ArrayList<>();
        int classcolumn = examples.get(0).length-1;
        m_rootmap = countAttAnz(examples, classcolumn);
        m_tree_entropy = calculateEntropy(m_rootmap);
        m_tree = learnTree(examples,classcolumn,usedatts,0,"");
        m_tree.setIsHead(true);
    }
    
    /**
     * Getter for the ArrayList of the learned tree.
     * @return Returns the learned tree.
     */
    public Component getTree(){
        return m_tree;
    }
    
    /**
     * Method to learn the tree from the training data with the ID3
     * algorithm (recursive). In the package structure all used classes are implemented for storing
     * the tree.
     * @param examples ArrayList with the training examples.
     * @param classcolumn Integer value with the position of the class cloumn.
     * @param usedattributes ArrayList with the attributes, which are already
     * used in the learning process.
     * @param lastatt The last attribute which was used.
     * @param lastvalue The value of the branch which was used.
     * @return Returns the learned tree.
     */
    private Component learnTree(ArrayList<String[]> examples, Integer classcolumn, ArrayList<Integer> usedattributes, int lastatt, String lastvalue){
        
        HashMap<String,Double> classanz = countAttAnz(examples, classcolumn);
        
        //If all Examples have the same class, Return the single-node tree Root, with the label of this class 
        Iterator iteranz = classanz.entrySet().iterator();
        double anz = 0;
        while(iteranz.hasNext()){
            Map.Entry pairs = (Map.Entry)iteranz.next();
            anz = anz+(double)pairs.getValue();
        }
        Iterator iterclass1 = classanz.entrySet().iterator();
        while(iterclass1.hasNext()){
            Map.Entry pairs = (Map.Entry)iterclass1.next();
            if( (anz/(double)pairs.getValue()) == 1 ){
                return new Leaf(calculateEntropy(classanz), (String)pairs.getKey(), examples.get(0)[lastatt], lastatt, classanz);
            }
        }
        
        //If no attributes left, Return the single-node tree Root, with the most common class
        if(usedattributes.size() == classcolumn){
            Iterator iterclass2 = classanz.entrySet().iterator();
            String mostclass = "";
            double anzmostclass = 0;
            while(iterclass2.hasNext()){
                Map.Entry pairs = (Map.Entry)iterclass2.next();
                if((double)pairs.getValue() >= anzmostclass){
                    mostclass = (String)pairs.getKey();
                    anzmostclass = (double)pairs.getValue();
                }
            }
            return new Leaf(calculateEntropy(classanz), mostclass, lastvalue, lastatt, classanz);
        }
        
        //Otherwise select the best attribute        
        double bestgain = -1;
        int bestpos = -1;
       
        for(int i = 0;i<classcolumn;i++){
            if(!usedattributes.contains(i)){
                double gain = calculateGain(examples,i);
                if(bestgain<gain){
                    bestgain = gain;
                    bestpos = i;
                }
            }
        }
        usedattributes.add(bestpos);
        Node node = new Node(calculateEntropy(classanz), "", -1, classanz);
        HashMap<String,Double> values = countAttAnz(examples,bestpos);
        Iterator iter3 = values.entrySet().iterator();
        while(iter3.hasNext()){
            Map.Entry pairs = (Map.Entry)iter3.next();
            ArrayList<String[]> branch = getBranch(examples, bestpos, (String)pairs.getKey());
            if(branch == null){
                Iterator iterclass4 = classanz.entrySet().iterator();
                String mostclass = "";
                int anzmostclass = 0;
                while(iterclass4.hasNext()){
                    Map.Entry pairs4 = (Map.Entry)iterclass4.next();
                    if((int)pairs4.getValue() >= anzmostclass){
                        mostclass = (String)pairs.getKey();
                        anzmostclass = (int)pairs.getValue();
                    }
                }
                node.add(new Leaf(calculateEntropy(values), mostclass, (String)pairs.getKey(), bestpos, values));
                node.setAttrPos(bestpos);
                node.setAttrValue(mostclass);
            }
            else{
                ArrayList<Integer> newatts = new ArrayList<>();
                newatts = (ArrayList<Integer>)usedattributes.clone();
                node.add(learnTree(branch,classcolumn,newatts,bestpos,(String)pairs.getKey()));
                node.setAttrPos(lastatt);
                node.setAttrValue(lastvalue);
            }
        }
        return node;
    }
    
    /**
     * This method calculates the gain for a spezific attribute.
     * @param examples ArrayList of the training examples.
     * @param a Integer of the attribute position.
     * @return Returns the information gain of an attribute.
     */
    private double calculateGain(ArrayList<String[]> examples, int a){
        //calc root entropy and total count
        int numberofcolumns = examples.get(0).length;
        HashMap<String,Double> classanz = countAttAnz(examples,numberofcolumns-1);
        double rootEntropy = calculateEntropy(classanz);
        double gain = rootEntropy;

        HashMap<String,Double> attanz = countAttAnz(examples,a);
        double anza = countBranchExamples(attanz);

        //loop over values from the attribute
        Iterator itervalues = attanz.entrySet().iterator();
        while(itervalues.hasNext()){
            Map.Entry pairs = (Map.Entry)itervalues.next();
            ArrayList<String[]> branch = getBranch(examples,a,(String)pairs.getKey());
            HashMap<String,Double> branchmap = countAttAnz(branch,numberofcolumns-1);
            double entv = calculateEntropy(branchmap);
            double anzv = countBranchExamples(branchmap);
            gain = gain - anzv/anza*entv;
        }
        return gain;
    }
    
    /**
     * This method counts the training examples for a spezial branch.
     * @param branch HashMap of a spezial branch.
     * @return Returns the count of training examples at the branch.
     */
    private double countBranchExamples(HashMap<String,Double> branch){
        Iterator iter = branch.entrySet().iterator();
        double anz = 0;
        while(iter.hasNext()){
            Map.Entry pairs = (Map.Entry)iter.next();
            anz = anz + (double)pairs.getValue();
        }
        return anz;
    }
    
    /**
     * This method returns a branch of the tree for an attribute with a
     * special value.
     * @param examples ArrayList with the training examples.
     * @param attribute Integer position of the attribute.
     * @param value String with the value of the attribute.
     * @return Returns ArrayList of the branch.
     */
    private ArrayList<String[]> getBranch(ArrayList<String[]> examples, int attribute, String value){
        ArrayList<String[]> branch = new ArrayList<>();
        Iterator<String[]> iter = examples.iterator();
        while(iter.hasNext()){
            String[] ex = iter.next();
            if(ex[attribute].equals(value)){
                branch.add(ex);
            }
        }
        return branch;
    }
     
    /**
     * This method calculates the entropy for a given set.
     * @param valueanz This HashMap contains the different values
     * of an attribute (or in spezial case the classes) as key and the
     * number of the examples for the value (or class).
     * @return Returns the entropy for a given set.
     */
    private double calculateEntropy(HashMap<String,Double> valueanz){
        //Count the number of different values in the set.
        if(valueanz.size() == 1){
            return 0;
        }
        else{
        Iterator itervalues = valueanz.entrySet().iterator();
        double c = valueanz.size();
        double anzexamples = countBranchExamples(valueanz);
        //Compute the entropy for the given set.
        Iterator iterent = valueanz.entrySet().iterator();
        double entropy = 0;
        while(iterent.hasNext()){
            Map.Entry pairs = (Map.Entry)iterent.next();
            double pi = (double)pairs.getValue() / anzexamples;
            entropy = entropy - pi*(Math.log(pi)/Math.log(c));
        }
        return entropy;
        }
    }
    
    /**
     * This method counts the different values of an attribute, spezified by
     * the position.
     * @param examples ArrayList of the training examples.
     * @param pos Position number of the attribute.
     * @return Returns a HashMap with the distinct attribute values and the
     * count of the examples with this value.
     */
    private HashMap<String,Double> countAttAnz(ArrayList<String[]> examples, int pos){
        ArrayList<String> values = new ArrayList<>();
        HashMap<String,Double> valueanz = new HashMap<>();
        
        
        Iterator<String[]> itervalues = examples.iterator();
        while(itervalues.hasNext()){
            String[] ex_values = itervalues.next();
            if (!values.contains(ex_values[pos])){
                values.add(ex_values[pos]);
            }
        }
        
        Iterator<String> iteranz = values.iterator();
        while(iteranz.hasNext()){
            double tempanz = 0;
            String vl = iteranz.next();
            
            Iterator<String[]> iterex = examples.iterator();
            while(iterex.hasNext()){
                String[] exanz = iterex.next();
                if(exanz[pos].equals(vl)){
                    tempanz++;
                }
            }
            valueanz.put(vl, tempanz);   
        }
        return valueanz;
    }
    
}