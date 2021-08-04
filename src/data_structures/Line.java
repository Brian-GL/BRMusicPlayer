/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;


public class Line<T> extends Structure<T>{
	
    /**Creates a new empty Line*/
    public Line() { super(); }

    public Line(Listing<T> list) { 
            super();
            this._elements.joinToBack(list);
    }

    /*
    public Line(Graph<T> graph) { 
            super();
            this._elements.JoinToBack(graph);
    }

    public Line(Matrix<T>.Row row){
            super();
            Matrix<T>.Column auxiliar = row.Front();
            while(auxiliar != null) {
                    this.Enqueue(auxiliar.Value());
                    auxiliar = auxiliar.Next();
            }
    }

    */
    public Line(Structure<T> structure) {
            super();
            this._elements.joinToBack(structure.toListing());
    }

    //Public methods

    public void enqueue(T element){ this._elements.pushBack(element);}

    public void lineUp(T element){ this._elements.addToBack(element);}

    public T dequeue() throws NullPointerException{
            return this._elements.dequeue();
    }
}
