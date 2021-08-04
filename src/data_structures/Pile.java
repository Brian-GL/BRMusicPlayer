/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;


public class Pile<T> extends Structure<T>{
	
    /**Creates a new empty pile.*/
    public Pile() { super(); }

    public Pile(Listing<T> list) { 
            super();
            this._elements.joinToFront(list);
    }

    /*
    public Pile(Graph<T> graph) { 
            super();
            this._elements.JoinToFront(graph);
    }
    
    public Pile(Matrix<T>.Row row){
            super();
            Matrix<T>.Column auxiliar = row.Front();

            while(auxiliar != null) {
                    this.Stack(auxiliar.Value());
                    auxiliar = auxiliar.Next();
            }
    }
    */

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Pile(Structure<T> structure) {
            super();
            Nodet<T> auxiliar = structure.toListing().getFrontNodet();

            while(auxiliar != null) {
                    stack(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    //Public methods

    public void stack(T element){ this._elements.pushFront(element);}

    public void pileUp(T element){ this._elements.addToFront(element);}

    public T unstack() throws NullPointerException{
            return this._elements.unstack();
    }
}
