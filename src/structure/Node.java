package structure;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class for tree nodes.
 * @author Steven Brandt and Fabian Witt
 */
public class Node extends Component {

    private final ArrayList<Component> components = new ArrayList<>();

    private HashMap<String,Double> classAnz;
    private double entropy;
    private String attrValue;
    private int attrPos;
    private boolean isHead;

    /**
     * Constructor with all parameters
     * @param entropy entropy of the node
     * @param attrValue value of the attribute
     * @param attrPos position of the attribute (under the higher-level node)
     * @param classAnz HashMap with classes and number of classes
     */
    public Node(double entropy, String attrValue, int attrPos, HashMap<String,Double> classAnz) {
        this.entropy = entropy;
        this.classAnz = classAnz;
        this.attrValue = attrValue;
        this.attrPos = attrPos;
        this.isHead = false;
    }
    
    /**
     * Default constructor.
     * Set parameter isHead = false
     */
    public Node(){
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
     * @param entropy entropy of the node
     */
    @Override
    public void setEntropy(double entropy) {
        this.entropy = entropy;
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
     * Set parameter.
     * @param isHead if the leaf is the head of the tree: isHead = true
     */
    @Override
    public void setIsHead(boolean isHead) {
        this.isHead = isHead;
    }

    /**
     * Add a component to the components-list.
     * @param component element for the components-list
     */
    @Override
    public void add(Component component) {
        components.add(component);
    }

    /**
     * Removes the element component from the components-list.
     * @param component element of the components-list
     */
    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    /**
     * Returns Component (with index i) from the components-list.
     * @param i index of the components-list
     * @return Component-Object
     */
    @Override
    public Component getChild(int i) {
        return components.get(i);
    }

    /**
     * 1. print the leaf with classes, entropy, attribute and className
     * 2. iterate through all components in the list
     * (if isHead = true: use tree-tag instead of node and hide attr)
     *
     */
    @Override
    public void print() {
            
        System.out.print("<" + ((isHead) ? "tree" : "node" ) +  " classes=\"");

        Iterator classIter = classAnz.entrySet().iterator();

        while(classIter.hasNext()) {
            Map.Entry aktsecIter = (Map.Entry) classIter.next();
            System.out.print(aktsecIter.getKey() + ":" + aktsecIter.getValue());
            if(classIter.hasNext()){
                System.out.print(",");
            }
        }

        System.out.print("\" entropy=\"" + Math.round(entropy * Math.pow(10, 3)) / Math.pow(10, 3) + "\"");
        if(attrPos >= 0 && !isHead){
            System.out.print(" attr" + (attrPos + 1) + "=\"" + attrValue + "\"");
        }
        System.out.print(">");
        System.out.println();
            
        Iterator<Component> compIter = components.iterator();
        
        while(compIter.hasNext()){
            
            Component aktCompIter = compIter.next();

            aktCompIter.print();
        }
        
        System.out.println("</" + ((isHead) ? "tree" : "node" ) + ">");
    }
    
    
    /**
     * 1. write the node with classes, entropy and attribute in a file
     * 2. iterate through all components in the list
     * (if isHead = true: use tree-tag instead of node and hide attr)
     *
     * @param writer PrintWriter-Object
     */
    @Override
    public void write(PrintWriter writer){
        writer.print("<" + ((isHead) ? "tree" : "node" ) +  " classes=\"");

        Iterator classIter = classAnz.entrySet().iterator();

        while(classIter.hasNext()) {
            Map.Entry aktsecIter = (Map.Entry) classIter.next();
            writer.print(aktsecIter.getKey() + ":" + aktsecIter.getValue());
            if(classIter.hasNext()){
                writer.print(",");
            }
        }

        writer.print("\" entropy=\"" + Math.round(entropy * Math.pow(10, 3)) / Math.pow(10, 3) + "\"");
        if(attrPos >= 0 && !isHead){
            writer.print(" attr" + (attrPos + 1) + "=\"" + attrValue + "\"");
        }
        writer.print(">");
        writer.println();
            
        Iterator<Component> compIter = components.iterator();
        
        while(compIter.hasNext()){
            
            Component aktCompIter = compIter.next();

            aktCompIter.write(writer);
        }
        
        writer.println("</" + ((isHead) ? "tree" : "node" ) + ">");
    }
}