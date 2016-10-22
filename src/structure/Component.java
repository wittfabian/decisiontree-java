package structure;

import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Abstract class for the storing structure.
 * @author Steven Brandt and Fabian Witt
 */
public abstract class Component {
    
    /**
     * Abstract method implemented in subclasses.
     * @param classAnz abstract method, see impl. in the subclasses
     */
    public void setClassAnz(HashMap<String, Double> classAnz){
        throw new UnsupportedOperationException();
    }

    /**
     * Abstract method implemented in subclasses.
     * @param entropy abstract method, see impl. in the subclasses
     */
    public void setEntropy(double entropy) {
        throw new UnsupportedOperationException();
    }

    /**
     * Abstract method implemented in subclasses.
     * @param className abstract method, see impl. in the subclasses
     */
    public void setClassName(String className) {
        throw new UnsupportedOperationException();
    }

    /**
     * Abstract method implemented in subclasses.
     * @param attrValue abstract method, see impl. in the subclasses
     */
    public void setAttrValue(String attrValue) {
        throw new UnsupportedOperationException();
    }  

    /**
     * Abstract method implemented in subclasses.
     * @param attrPos abstract method, see impl. in the subclasses
     */
    public void setAttrPos(int attrPos) {
        throw new UnsupportedOperationException();
    }

    /**
     * Abstract method implemented in subclasses.
     * @param isHead abstract method, see impl. in the subclasses
     */
    public void setIsHead(boolean isHead) {
        throw new UnsupportedOperationException();
    }

    /**
     * Abstract method implemented in subclasses.
     * @param component abstract method, see impl. in the subclasses
     */
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    /**
     * Abstract method implemented in subclasses.
     * @param component abstract method, see impl. in the subclasses
     */
    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }

    /**
     * Abstract method implemented in subclasses.
     * @param i abstract method, see impl. in the subclasses
     * @return Component
     */
    public Component getChild(int i) {
	throw new UnsupportedOperationException();
    }

    /**
     * Abstract method implemented in subclasses.
     */
    public void print() {
        throw new UnsupportedOperationException();
    }

    /**
     * Abstract method implemented in subclasses.
     * @param writer abstract method, see impl. in the subclasses
     */
    public void write(PrintWriter writer){
        throw new UnsupportedOperationException();
    }
}
