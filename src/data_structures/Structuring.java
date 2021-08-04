/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

/**
 *
 * @author Brian Lomeli
 */
public class Structuring {
    
    protected Listing<Object> _elements;

    /**Creates a new empty structure.*/
    public Structuring() { this._elements = new Listing<>();}

    public boolean isEmpty() {return this._elements.isEmpty();}

    public long  getSize() {return this._elements.getSize();}

    public void pop() { this._elements.popFront();}

    public Object top() throws NullPointerException{ return this._elements.getFirstElement(); }

    public boolean contains(Object element) {return this._elements.containsElement(element);}

    public void remove(Object element) {this._elements.remove(element);}

    /**Removes all elements into the structure.*/
    public void clear() {this._elements.clear();}

    public Listing<Object> toListing() {return this._elements;}
    
    public Iterating<Object> getFrontIterator(){return this._elements.getFrontIterator();}
    
    public Iterating<Object> getBackIterator(){return this._elements.getBackIterator();}

    //Override methods

    @Override
    public String toString() throws NullPointerException{
        return _elements.toString();
    }

    @Override
    public int hashCode() {
        return _elements.hashCode();
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        return _elements.equals(obj);
    }

    @Override
    @SuppressWarnings({"FinalizeCalledExplicitly", "FinalizeDeclaration"})
    protected void finalize() throws Throwable {
        try {
            _elements.finalize();
        } finally {
            super.finalize();
        }
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    protected Object clone() throws CloneNotSupportedException {
        return _elements.clone();
    }
}
