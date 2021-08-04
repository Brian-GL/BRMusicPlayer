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
public class Piling  extends Structuring{
    
    /**Creates a new empty pile.*/
    public Piling() { super(); }

    public Piling(Listing<Object> list) { 
            super();
            this._elements.joinToFront(list);
    }
    
    public Piling(Listed list) { 
            super();
            this._elements.joinToFront(list.toListing());
    }

    /*
    public Piling(Graph<T> graph) { 
            super();
            this._elements.JoinToFront(graph);
    }
    
    public Piling(Matrix<T>.Row row){
            super();
            Matrix<T>.Column auxiliar = row.Front();

            while(auxiliar != null) {
                    this.Stack(auxiliar.Value());
                    auxiliar = auxiliar.Next();
            }
    }
    */

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Piling(Structure<Object> structure) {
            super();
            Nodet<Object> auxiliar = structure.toListing().getFrontNodet();

            while(auxiliar != null) {
                    stack(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Piling(Structuring structuring) {
            super();
            Nodet<Object> auxiliar = structuring.toListing().getFrontNodet();

            while(auxiliar != null) {
                    stack(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    //Public methods

    public void stack(Object element){ this._elements.pushFront(element);}

    public void pileUp(Object element){ this._elements.addToFront(element);}

    public Object unstack() throws NullPointerException{
            return this._elements.unstack();
    }
    
}
