/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

import java.util.Objects;


public class Quartet<A,B,C,D> {
    
    private A _firstElement;
    private B _secondElement;
    private C _thidElement;
    private D _fourthElement;
    
    public Quartet(A firstElement, B secondElement, C thirdElement,D fourthElement){
       this._firstElement = firstElement;
       this._secondElement = secondElement;
       this._thidElement = thirdElement;
       this._fourthElement = fourthElement;
    }
    
     public Quartet(Quartet<A,B,C,D> quartetElement){
       this._firstElement = quartetElement.getFirstElement();
       this._secondElement = quartetElement.getSecondElement();
       this._thidElement = quartetElement.getThidElement();
       this._fourthElement = quartetElement.getFourthElement();
    }

     /**
     * @return the firstElement
     */
    public A getFirstElement()throws NullPointerException {
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

    /**
     * @return the fourthElement
     */
    public D getFourthElement() throws NullPointerException{
        return this._fourthElement;
    }

    /**
     * @param fourthElement the fourthElement to set
     */
    public void setFourthElement(D fourthElement) {
        this._fourthElement = fourthElement;
    }

    @Override
    public String toString() throws NullPointerException{
        return this._firstElement + "\n" + this._secondElement + "\n" + this._thidElement + "\n" + this._fourthElement;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this._firstElement);
        hash = 13 * hash + Objects.hashCode(this._secondElement);
        hash = 13 * hash + Objects.hashCode(this._thidElement);
        hash = 13 * hash + Objects.hashCode(this._fourthElement);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
       if (obj == null) 
            return false;
        
        if (this == obj) 
            return true;

        if (!(obj instanceof Quartet<?,?,?,?>)) 
            return false; 
        
        Quartet<A, B,C,D> other = (Quartet<A, B, C,D>) obj;
        
        return this._firstElement.equals(other.getFirstElement()) 
                && this._secondElement.equals(other.getSecondElement())
                && this._thidElement.equals(other.getThidElement())
                && this._fourthElement.equals(other.getFourthElement());
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
