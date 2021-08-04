/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

import java.util.Objects;


public class Doublet<A,B>{
    
    private A _firstElement;
    private B _secondElement;
    
    public Doublet(A firstElement, B secondElement){
        this._firstElement = firstElement;
        this._secondElement = secondElement;
    }
    
    public Doublet(Doublet<A,B> doubletElement){
        this._firstElement = doubletElement.getFirstElement();
        this._secondElement = doubletElement.getSecondElement();
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

    @Override
    public String toString() throws NullPointerException{
        return this._firstElement + "\n" + this._secondElement;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this._firstElement);
        hash = 41 * hash + Objects.hashCode(this._secondElement);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) 
            return false;
        
        if (this == obj) 
            return true;

        if (!(obj instanceof Doublet<?,?>)) 
            return false; 
        
        Doublet<A, B> other = (Doublet<A, B>) obj;
        
        return this._firstElement.equals(other.getFirstElement()) 
                && this._secondElement.equals(other.getSecondElement());
            
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
