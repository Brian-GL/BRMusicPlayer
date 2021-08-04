/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

import java.util.Objects;


public class Triplet<A,B,C> {
    
    private A _firstElement;
    private B _secondElement;
    private C _thidElement;
    
    public Triplet(A firstElement, B secondElement, C thirdElement){
       this._firstElement = firstElement;
       this._secondElement = secondElement;
       this._thidElement = thirdElement;
    }
    
     public Triplet(Triplet<A,B,C> tripletElement){
       this._firstElement = tripletElement.getFirstElement();
       this._secondElement = tripletElement.getSecondElement();
       this._thidElement = tripletElement.getThidElement();
    }

     /**
     * @return the firstElement
     */
    public A getFirstElement() throws NullPointerException{
        return _firstElement;
    }

    /**
     * @param firstElement the firstElement to set
     */
    public void setFirstElement(A firstElement) {
        this._firstElement = firstElement;
    }

    /**
     * @return the secondElement
     */
    public B getSecondElement() throws NullPointerException{
        return _secondElement;
    }

    /**
     * @param secondElement the secondElement to set
     */
    public void setSecondElement(B secondElement) {
        this._secondElement = secondElement;
    }

    /**
     * @return the thidElement
     */
    public C getThidElement() throws NullPointerException{
        return _thidElement;
    }

    /**
     * @param thidElement the thidElement to set
     */
    public void setThidElement(C thidElement) {
        this._thidElement = thidElement;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this._firstElement);
        hash = 79 * hash + Objects.hashCode(this._secondElement);
        hash = 79 * hash + Objects.hashCode(this._thidElement);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
       if (obj == null) 
            return false;
        
        if (this == obj) 
            return true;

        if (!(obj instanceof Triplet<?,?,?>)) 
            return false; 
        
         Triplet<A, B,C> other = (Triplet<A, B, C>) obj;
        
        return this._firstElement.equals(other.getFirstElement()) 
                && this._secondElement.equals(other.getSecondElement())
                && this._thidElement.equals(other.getThidElement());
    }

    @Override
    public String toString() throws NullPointerException{
        return this._firstElement + "\n" + this._secondElement + "\n" + this._thidElement;
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
