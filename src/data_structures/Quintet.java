/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

import java.util.Objects;


public class Quintet<A,B,C,D,E> {
    
    private A _firstElement;
    private B _secondElement;
    private C _thidElement;
    private D _fourthElement;
    private E _fifthElement;

    public Quintet(A firstElement, B secondElement, C thidElement, D fourthElement, E fifthElement) {
        this._firstElement = firstElement;
        this._secondElement = secondElement;
        this._thidElement = thidElement;
        this._fourthElement = fourthElement;
        this._fifthElement = fifthElement;
    }
    
    public Quintet(Quintet<A,B,C,D,E>  quintetElement){
        this._firstElement = quintetElement.getFirstElement();
        this._secondElement = quintetElement.getSecondElement();
        this._thidElement = quintetElement.getThidElement();
        this._fourthElement = quintetElement.getFourthElement();
        this._fifthElement = quintetElement.getFifthElement();
    }

    /**
     * @return the _firstElement
     */
    public A getFirstElement() throws NullPointerException{
        return _firstElement;
    }

    /**
     * @param firstElement the _firstElement to set
     */
    public void setFirstElement(A firstElement) {
        this._firstElement = firstElement;
    }

    /**
     * @return the _secondElement
     */
    public B getSecondElement() throws NullPointerException{
        return _secondElement;
    }

    /**
     * @param secondElement the _secondElement to set
     */
    public void setSecondElement(B secondElement) {
        this._secondElement = secondElement;
    }

    /**
     * @return the _thidElement
     */
    public C getThidElement() throws NullPointerException{
        return _thidElement;
    }

    /**
     * @param thidElement the _thidElement to set
     */
    public void setThidElement(C thidElement) {
        this._thidElement = thidElement;
    }

    /**
     * @return the _fourthElement
     */
    public D getFourthElement() throws NullPointerException {
        return _fourthElement;
    }

    /**
     * @param fourthElement the _fourthElement to set
     */
    public void setFourthElement(D fourthElement) {
        this._fourthElement = fourthElement;
    }

    /**
     * @return the _fifthElement
     */
    public E getFifthElement() throws NullPointerException{
        return _fifthElement;
    }

    /**
     * @param fifthElement the _fifthElement to set
     */
    public void setFifthElement(E fifthElement) {
        this._fifthElement = fifthElement;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this._firstElement);
        hash = 83 * hash + Objects.hashCode(this._secondElement);
        hash = 83 * hash + Objects.hashCode(this._thidElement);
        hash = 83 * hash + Objects.hashCode(this._fourthElement);
        hash = 83 * hash + Objects.hashCode(this._fifthElement);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
       if (obj == null) 
            return false;
        
        if (this == obj) 
            return true;

        if (!(obj instanceof Quintet<?,?,?,?,?>)) 
            return false; 
        
         Quintet<A, B,C,D,E> other = (Quintet<A, B, C,D,E>) obj;
        
        return this._firstElement.equals(other.getFirstElement()) 
                && this._secondElement.equals(other.getSecondElement())
                && this._thidElement.equals(other.getThidElement())
                && this._fourthElement.equals(other.getFourthElement())
                && this._fifthElement.equals(other.getFifthElement());
    }

    @Override
    public String toString() throws NullPointerException{
        return this._firstElement + "\n" + this._secondElement + "\n" + this._thidElement + "\n" + this._fourthElement + "\n" + this._fifthElement;
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
