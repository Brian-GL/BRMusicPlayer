/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;


public class DoubletPile<A,B> extends DoubletStructure<A,B>{

    public DoubletPile() { super(); }

    public DoubletPile(DoubletListing<A,B> doubleList) { 
            super();
            this._elements.joinToFront(doubleList);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public DoubletPile(DoubletStructure<A,B> doubleStructure) {
            super();
            Nodet<Doublet<A,B>> auxiliar = doubleStructure.toDoubletListing().getFrontNodet();

            while(auxiliar != null) {
                    this.stack(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    public void stack(Doublet<A,B> element){ this._elements.pushFront(element);}

    public void stack(A firstElement, B secondElement) { this._elements.pushFront(firstElement,secondElement);}

    public void pileUp(Doublet<A,B> element){ this._elements.addToFront(element);}

    public void pileUp(A firstElement, B secondElement) throws Throwable{ this._elements.addToFront(firstElement,secondElement);}

    public Doublet<A,B> unstack() throws  NullPointerException{
           return this._elements.unstack();
    }
}
