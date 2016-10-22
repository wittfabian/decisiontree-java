package structure;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class for tree leafs.
 * @author Steven Brandt and Fabian Witt
 */
public class Leaf extends Component {

    private HashMap<String, Double> classAnz;
    private double entropy;
    private String className;
    private String attrValue;
    private int attrPos;
    private boolean isHead;

    /**
     * Constructor with all parameters.
     * @param entropy entropy of the leaf
     * @param className name of the class
     * @param attrValue value of the attribute
     * @param attrPos position of the attribute (under the higher-level node)
     * @param classAnz HashMap with classes and number of classes
     */
    public Leaf(double entropy, String className, String attrValue, int attrPos, HashMap<String,Double> classAnz) {
        this.entropy = entropy;
        this.classAnz = classAnz;
        this.className = className;
        this.attrValue = attrValue;
        this.attrPos = attrPos;
        this.isHead = false;
    }
    
    /**
     * Default constructor.
     */
    public Leaf(){
        this.isHead = false;
    }

    /**
     * Set parameter classAnz.
     * @param classAnz HashMap with classes and number of classes
     */
    @Override
    public void setClassAnz(HashMap<String, Double> classAnz) {
        this.classAnz = classAnz;
    }

    /**
     * Set parameter entropy.
     * @param entropy entropy of the leaf
     */
    @Override
    public void setEntropy(double entropy) {
        this.entropy = entropy;
    }

    /**
     * Set parameter className.
     * @param className name of the class
     */
    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Set parameter attrValue.
     * @param attrValue value of the attribute
     */
    @Override
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    /**
     * Set parameter attrPos.
     * @param attrPos position of the attribute (under the higher-level node)
     */
    @Override
    public void setAttrPos(int attrPos) {
        this.attrPos = attrPos;
    }
     
    /**
     * Set parameter isHead.
     * @param isHead if the leaf is the head of the tree: isHead = true
     */
    @Override
    public void setIsHead(boolean isHead) {
        this.isHead = isHead;
    }

    /**
     * Print the leaf with classes, entropy, attribute and className.
     * (if isHead = true: use tree-tag instead of node and hide attr)
     */
    @Override
    public void print() {
        System.out.print("<" + ((isHead) ? "tree" : "node" ) +  " classes=\"");
        
        Iterator iter = classAnz.entrySet().iterator();
        
        while (iter.hasNext()) {
            Map.Entry aktIter = (Map.Entry) iter.next();
            System.out.print(aktIter.getKey() + ":" + aktIter.getValue());
            if(iter.hasNext()){
                System.out.print(",");
            }
        }

        System.out.print("\" entropy=\"" + Math.round(entropy * Math.pow(10, 3)) / Math.pow(10, 3) + "\"");
        if(attrPos >= 0 && !isHead){
            System.out.print(" attr" + (attrPos + 1) + "=\"" + attrValue + "\"");
        }
        System.out.print(">" + className + "</" + ((isHead) ? "tree" : "node" ) + ">");
        System.out.println();
    }
   
    /**
     * Write the leaf with classes, entropy, attribute and className in a file.
     * (if isHead = true: use tree-tag instead of node and hide attr)
     * @param writer PrintWriter-Object
     */
    @Override
    public void write(PrintWriter writer){
        writer.print("<" + ((isHead) ? "tree" : "node" ) +  " classes=\"");
        
        Iterator iter = classAnz.entrySet().iterator();
        
        while (iter.hasNext()) {
            Map.Entry aktIter = (Map.Entry) iter.next();
            writer.print(aktIter.getKey() + ":" + aktIter.getValue());
            if(iter.hasNext()){
                writer.print(",");
            }
        }

        writer.print("\" entropy=\"" + Math.round(entropy * Math.pow(10, 3)) / Math.pow(10, 3) + "\"");
        if(attrPos >= 0 && !isHead){
            writer.print(" attr" + (attrPos + 1) + "=\"" + attrValue + "\"");
        }
        writer.print(">" + className + "</" + ((isHead) ? "tree" : "node" ) + ">");
        writer.println();
    }
}