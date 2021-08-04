/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

public class DoubletLine<A,B> extends DoubletStructure<A,B>{
	
    public DoubletLine() { super(); }

    public DoubletLine(DoubletListing<A,B> doubleList) { 
            super();
            this._elements.joinToBack(doubleList);
    }

    public DoubletLine(DoubletStructure<A,B> doubleStructure) {
            super();
            this._elements.joinToBack(doubleStructure.toDoubletListing());
    }
    
    public void enqueue(Doublet<A,B> element){ this._elements.pushBack(element);}

    public void enqueue(A firstElement, B secondElement) { this._elements.pushBack(firstElement,secondElement);}

    public void lineUp(Doublet<A,B> element){ this._elements.addToBack(element);}

    public void lineUp(A firstElement, B secondElement) { this._elements.addToBack(firstElement,secondElement);}

    public Doublet<A,B> dequeue() {
            return this._elements.dequeue();
    }
}
