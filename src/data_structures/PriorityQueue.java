/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

import java.util.Comparator;

/**Comparator class useful for element's comparison in Priority Queue*/
class DefaultComparator<T> implements Comparator<T>{
    
    @SuppressWarnings("unchecked")
    @Override
    public int compare(T o1, T o2){
        return ((Comparable<T>) o1).compareTo(o2);
    }
}

public class PriorityQueue<T> extends Structure<T>{
	
    /**Private attributes*/

    /**The mode to insert into the Priority Queue*/
    final private Mode _mode; 

    /**Comparator instance useful for elements comparison.*/
    final private Comparator<T> _comparator = new DefaultComparator<>();

    //Public constructors

    public PriorityQueue(Mode mode) { super(); this._mode = mode; }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public PriorityQueue(Listing<T> list, Mode mode) { 
            super();
            this._mode = mode; 
            Nodet<T> auxiliar = list.getFrontNodet();

            while(auxiliar != null) {
                    enqueue(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }
   
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public PriorityQueue(Structure<T> structure, Mode mode) { 
            super(); 
            this._mode = mode; 
            Nodet<T> auxiliar = structure.toListing().getFrontNodet();

            while(auxiliar != null) {
                    enqueue(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    public Mode mode() {return this._mode;}

    public void enqueue(T element) {

        if(this._elements.isEmpty())
                this._elements.pushFront(element);
        else {

                switch(this._mode) {
                        case ASCENDING -> {	
                                T middleElement = this._elements.getMediumElement();

                                Integer comparator = this._comparator.compare(element,middleElement);

                                //Are the same value
                                if(comparator == 0) 
                                        this._elements.insertNextTo(this._elements.getMiddleNodet(), element);

                                //It means the element if bigger than the middle value.
                                else if(comparator > 0) {

                                        boolean broke = false;
                                        Nodet<T> auxiliar = this._elements.getMiddleNodet();

                                        while(auxiliar.getNext() != null) {

                                                Integer previousComparator = this._comparator.compare(auxiliar.getValue(),element);
                                                Integer nextComparator = this._comparator.compare(auxiliar.getNext().getValue(),element);

                                                if(previousComparator <= 0 && nextComparator >= 0) {
                                                        broke = true;
                                                        this._elements.insertNextTo(auxiliar, element);
                                                        break;
                                                }

                                                auxiliar = auxiliar.getNext();
                                        }

                                        if(!broke)
                                                this._elements.insertNextTo(auxiliar,element);

                                }
                                //It means the element is smaller than the middle value.
                                else {
                                        boolean broke = false;
                                        Nodet<T> auxiliar = this._elements.getMiddleNodet();

                                        while(auxiliar.getPrevious() != null) {

                                                Integer nextComparator = this._comparator.compare(auxiliar.getValue(),element);
                                                Integer previousComparator = this._comparator.compare(auxiliar.getPrevious().getValue(),element);

                                                if(previousComparator <= 0 && nextComparator >= 0) {
                                                        broke = true;
                                                        this._elements.insertPreviousTo(auxiliar, element);
                                                        break;
                                                }

                                                auxiliar = auxiliar.getPrevious();
                                        }

                                        if(!broke)
                                                this._elements.insertPreviousTo(auxiliar,element);
                                }
                        }
                        case DESCENDING -> {
                                T middleElement = this._elements.getMediumElement();

                                Integer comparator = this._comparator.compare(element,middleElement);

                                //Are the same value
                                if(comparator == 0) 
                                        this._elements.insertNextTo(this._elements.getMiddleNodet(), element);

                                //It means the element if bigger than the middle value.
                                else if(comparator > 0) {

                                        boolean broke = false;
                                        Nodet<T> auxiliar = this._elements.getMiddleNodet();

                                        while(auxiliar.getPrevious() != null){

                                                Integer previousComparator = this._comparator.compare(auxiliar.getValue(),element);
                                                Integer nextComparator = this._comparator.compare(auxiliar.getPrevious().getValue(),element);

                                                if(previousComparator <= 0 && nextComparator >= 0) {
                                                        broke = true;
                                                        this._elements.insertPreviousTo(auxiliar, element);
                                                        break;
                                                }

                                                auxiliar = auxiliar.getPrevious();
                                        }

                                        if(!broke)
                                                this._elements.insertPreviousTo(auxiliar,element);

                                }
                                //It means the element is small than the middle value.
                                else {
                                        boolean broke = false;
                                        Nodet<T> auxiliar = this._elements.getMiddleNodet();

                                        while(auxiliar.getNext() != null) {

                                                Integer  previousComparator = this._comparator.compare(auxiliar.getValue(),element);
                                                Integer nextComparator = this._comparator.compare(auxiliar.getNext().getValue(),element);

                                                if(previousComparator >= 0 && nextComparator <= 0) {
                                                        broke = true;
                                                        this._elements.insertNextTo(auxiliar, element);
                                                        break;
                                                }

                                                auxiliar = auxiliar.getNext();
                                        }

                                        if(!broke)
                                                this._elements.insertNextTo(auxiliar,element);
                                }
                        }
                }
        }
    }

    public void queueUp(T element) {
        if(!this._elements.containsElement(element))
            enqueue(element);
    }

    public T dequeue() throws NullPointerException{
           return _elements.dequeue();
    }
	
}
