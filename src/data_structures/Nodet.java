/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

import java.util.Objects;


public class Nodet<E> {
    
    protected Nodet<E> _previous;
    protected Nodet<E> _next;
    protected E _value;

    public Nodet(Nodet<E> previous, E value, Nodet<E> next) {
        this._previous = previous;
        this._next = next;
        this._value = value;
    }

    public Nodet(E value) {
        this._value = value;
    }

    /**
     * @return the _previous
     */
    public Nodet<E> getPrevious() throws NullPointerException{
        return _previous;
    }

    /**
     * @param previous the _previous to set
     */
    public void setPrevious(Nodet<E> previous) {
        this._previous = previous;
    }

    /**
     * @return the _next
     */
    public Nodet<E> getNext() throws NullPointerException{
        return _next;
    }

    /**
     * @param next the _next to set
     */
    public void setNext(Nodet<E> next) {
        this._next = next;
    }

    /**
     * @return the _value
     */
    public E getValue() throws NullPointerException{
        return _value;
    }

    /**
     * @param value the _value to set
     */
    public void setValue(E value) {
        this._value = value;
    }

    @Override
    public String toString() throws NullPointerException{
       String toString = new String();
        if(this._previous != null)
            toString = toString + this._previous._value + "\n";
        toString = toString + this._value + "\n";
        if(this._next != null)
            toString = toString + this._next._value + "\n";
      
        return toString;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        if(this._previous != null)
            hash = 97 * hash + Objects.hashCode(this._previous._value);
        if(this._next != null)
            hash = 97 * hash + Objects.hashCode(this._next._value);
        hash = 97 * hash + Objects.hashCode(this._value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
       if (obj == null) 
            return false;
        
        if (this == obj) 
            return true;

        if (!(obj instanceof Nodet<?>)) 
            return false; 
        
         Nodet<E> other = (Nodet<E>) obj;
         
         if(this._next != null && this._previous != null
                 && other._next != null && other._previous != null){
             
             return this._next._value.equals(other._next._value)
                     && this._previous._value.equals(other._previous._value)
                     && this._value.equals(other._value);
             
         }
         else if(this._next == null && this._previous != null
                 && other._next == null && other._previous != null){
             return this._previous._value.equals(other._previous._value)
                     && this._value.equals(other._value);
         }
         else if(this._next != null && this._previous == null
                 && other._next != null && other._previous == null){
             return this._next._value.equals(other._next._value)
                     && this._value.equals(other._value);
         }
         else if(this._next == null && this._previous == null
                 && other._next == null && other._previous == null){
             return this._value.equals(other._value);
         }
      
         return false;
         
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
