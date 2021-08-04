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
public class DoubletStructure <A,B>{
	
    protected DoubletListing<A,B> _elements;


    public DoubletStructure() { this._elements = new DoubletListing<>();}

    public boolean isEmpty() {return this._elements.isEmpty();}

    public long getSize() {return this._elements.getSize();}

    public void pop() { this._elements.popFront();}

    public Doublet<A,B> top() throws NullPointerException{ return this._elements.getFirstDoublet(); }

    public boolean contains(Doublet<A,B> element) {return this._elements.containsDoublet(element);}

    public boolean containsFirstElement(A element) {return this._elements.containsFirstElement(element);}

    public boolean containsSecondElement(B element) {return this._elements.containsSecondElement(element);}

    public boolean containsElements(A firstElement,B secondElement)  {return this._elements.containsElements(firstElement,secondElement);}

    public void remove(Doublet<A,B> element) {this._elements.remove(element);}

    public void remove(A firstElement,B secondElement){this._elements.remove(firstElement,secondElement);}

    public void clear() {this._elements.clear();}

    public DoubletListing<A,B> toDoubletListing(){return this._elements;}

    @Override
    public String toString() { return this._elements.toString();}

    @Override
    public int hashCode() {
        return  this._elements.hashCode();
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        return this._elements.equals(obj);
    }
    
    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    
}
