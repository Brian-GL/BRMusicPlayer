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
public class Lining extends Structuring {
     /**Creates a new empty Lining*/
    public Lining() { super(); }

    public Lining(Listing<Object> list) { 
            super();
            this._elements.joinToBack(list);
    }

    public Lining(Listed list) { 
            super();
            this._elements.joinToBack(list.toListing());
    }
    
    /*
    public Lining(Graph<T> graph) { 
            super();
            this._elements.JoinToBack(graph);
    }

    public Lining(Matrix<T>.Row row){
            super();
            Matrix<T>.Column auxiliar = row.Front();
            while(auxiliar != null) {
                    this.Enqueue(auxiliar.Value());
                    auxiliar = auxiliar.Next();
            }
    }

    */
    public Lining(Structure<Object> structure) {
            super();
            this._elements.joinToBack(structure.toListing());
    }
    
    public Lining(Structuring structuring) {
            super();
            this._elements.joinToBack(structuring.toListing());
    }

    //Public methods

    public void enqueue(Object element){ this._elements.pushBack(element);}

    public void lineUp(Object element){ this._elements.addToBack(element);}

    public Object dequeue() throws NullPointerException{
            return this._elements.dequeue();
    }
}
