/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;


public class DoubletListed {
    
    private Listing<TwoSome> _twoSomeListing;
    
    public DoubletListed() { this._twoSomeListing = new Listing<>();}

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public DoubletListed(DoubletListing<Object,Object> doubletListing) {
            this._twoSomeListing = new Listing<>();

            Nodet<Doublet<Object,Object>> middle = doubletListing.getMiddleNodet();

            if(middle != null) {

                    pushBack(middle.getValue());
                   Nodet<Doublet<Object,Object>> previous = middle.getPrevious();
                    Nodet<Doublet<Object,Object>> next = middle.getNext();

                    while(previous != null || next != null) {

                            if(previous != null) {
                                    pushFront(previous.getValue());
                                    previous = previous.getPrevious();
                            }
                            if(next != null) {
                                    pushBack(next.getValue());
                                    next = next.getNext();
                            }
                    }
            }
    }
    
    public DoubletListed(Listing<TwoSome> listing) {
            this._twoSomeListing = new Listing<>();

            Nodet<TwoSome> middle = listing.getMiddleNodet();

            if(middle != null) {

                    pushBack(middle.getValue());
                   Nodet<TwoSome> previous = middle.getPrevious();
                    Nodet<TwoSome> next = middle.getNext();

                    while(previous != null || next != null) {

                            if(previous != null) {
                                    pushFront(previous.getValue());
                                    previous = previous.getPrevious();
                            }
                            if(next != null) {
                                    pushBack(next.getValue());
                                    next = next.getNext();
                            }
                    }
            }
    }
    
    public DoubletListed(DoubletListed listing) {
            this._twoSomeListing = new Listing<>();

            Nodet<TwoSome> middle = listing.getMiddleNodet();

            if(middle != null) {

                    pushBack(middle.getValue());
                   Nodet<TwoSome> previous = middle.getPrevious();
                    Nodet<TwoSome> next = middle.getNext();

                    while(previous != null || next != null) {

                            if(previous != null) {
                                    pushFront(previous.getValue());
                                    previous = previous.getPrevious();
                            }
                            if(next != null) {
                                    pushBack(next.getValue());
                                    next = next.getNext();
                            }
                    }
            }
    }

    public boolean isEmpty() {
        return _twoSomeListing.isEmpty();
    }

    public long getSize() {
        return _twoSomeListing.getSize();
    }

    public TwoSome getFirstElement() throws NullPointerException {
        return _twoSomeListing.getFirstElement();
    }

    public TwoSome getLastElement() throws NullPointerException {
        return _twoSomeListing.getLastElement();
    }

    public TwoSome getMediumElement() throws NullPointerException {
        return _twoSomeListing.getMediumElement();
    }

    public Nodet<TwoSome> getFrontNodet() throws NullPointerException {
        return _twoSomeListing.getFrontNodet();
    }

    public Nodet<TwoSome> getBackNodet() throws NullPointerException {
        return _twoSomeListing.getBackNodet();
    }

    public Nodet<TwoSome> getMiddleNodet() throws NullPointerException {
        return _twoSomeListing.getMiddleNodet();
    }

    public TwoSome dequeue() throws NullPointerException {
        return _twoSomeListing.dequeue();
    }

    public TwoSome unstack() throws NullPointerException {
        return _twoSomeListing.unstack();
    }

    public Iterating<TwoSome> getFrontIterator() throws NullPointerException {
        return _twoSomeListing.getFrontIterator();
    }

    public Iterating<TwoSome> getBackIterator() throws NullPointerException {
        return _twoSomeListing.getBackIterator();
    }

    public void pushBack(TwoSome element) { _twoSomeListing.pushBack(element);}
    
     public void pushBack(Doublet<Object,Object> element) { this._twoSomeListing.pushBack(new TwoSome(element));}

    public void pushBack(Object firstElement, Object secondElement) {  this._twoSomeListing.pushBack(new TwoSome(firstElement,secondElement));}

    public void pushFront(TwoSome element) {  _twoSomeListing.pushFront(element);}
    
    public void pushFront(Doublet<Object,Object> element) { this._twoSomeListing.pushFront(new TwoSome(element));}

    public void pushFront(Object firstElement, Object secondElement) {  this._twoSomeListing.pushFront(new TwoSome(firstElement,secondElement));}

    public boolean containsTwoSome(TwoSome element) {  return _twoSomeListing.containsElement(element);}
    
    public boolean containsFirstObject(Object element) {
            
        if(this._twoSomeListing.getSize() == 0)
                return false;
        else if (this._twoSomeListing.getSize() == 1)
                return this._twoSomeListing.getFirstElement().getFirstObject().equals(element);
         else if (this._twoSomeListing.getSize() == 2)
                return this._twoSomeListing.getFirstElement().getFirstObject().equals(element) || this._twoSomeListing.getLastElement().getFirstObject().equals(element);
        else{

                Nodet<TwoSome> first = this._twoSomeListing.getFrontNodet();
                Nodet<TwoSome> last = this._twoSomeListing.getBackNodet();

                double middleIndex = ((double)this._twoSomeListing.getSize())/2;

                for(double i = 0; i < middleIndex;i++) {

                        if(first.getValue().getFirstObject().equals(element))
                                return true;

                        if(last.getValue().getFirstObject().equals(element))
                                return true;

                        first = first.getNext();
                        last = last.getPrevious();
                }

                return false;
        }
            
    }

    public boolean containsSecondObject(Object element) {
        if(this._twoSomeListing.getSize() == 0)
                return false;
        else if (this._twoSomeListing.getSize() == 1)
                return this._twoSomeListing.getFirstElement().getSecondObject().equals(element);
         else if (this._twoSomeListing.getSize() == 2)
                return this._twoSomeListing.getFirstElement().getSecondObject().equals(element) || this._twoSomeListing.getLastElement().getSecondObject().equals(element);
        else{

                Nodet<TwoSome> first = this._twoSomeListing.getFrontNodet();
                Nodet<TwoSome> last = this._twoSomeListing.getBackNodet();

                double middleIndex = ((double)this._twoSomeListing.getSize())/2;

                for(double i = 0; i < middleIndex;i++) {

                        if(first.getValue().getSecondObject().equals(element))
                                return true;

                        if(last.getValue().getSecondObject().equals(element))
                                return true;

                        first = first.getNext();
                        last = last.getPrevious();
                }

                return false;
        }
    }

    public void addToFront(TwoSome element) {
        _twoSomeListing.addToFront(element);
    }

    public void addToBack(TwoSome element) {
        _twoSomeListing.addToBack(element);
    }
    
    public void addToFront(Doublet<Object,Object> element) { this._twoSomeListing.addToFront(new TwoSome(element));}

    public void addToFront(Object firstElement, Object secondElement)  {  this._twoSomeListing.addToFront( new TwoSome(firstElement,secondElement));}

    public void addToBack(Doublet<Object,Object> element) { this._twoSomeListing.addToBack(new TwoSome(element));}

    public void addToBack(Object firstElement, Object secondElement) { this._twoSomeListing.addToBack(new TwoSome(firstElement,secondElement));}

    public TwoSome getElement(long index) throws NullPointerException {
        return _twoSomeListing.getElement(index);
    }

    public void replace(TwoSome elementToReplace, TwoSome replacement) {
        _twoSomeListing.replace(elementToReplace, replacement);
    }

    public void replacing(TwoSome elementToReplace, TwoSome replacement) {
        _twoSomeListing.replacing(elementToReplace, replacement);
    }

    public void replace(long position, TwoSome replacement) {
        _twoSomeListing.replace(position, replacement);
    }

    public void superSede(TwoSome elementToReplace, TwoSome replacement) {
        _twoSomeListing.superSede(elementToReplace, replacement);
    }

    public void superSede(long position, TwoSome replacement) {
        _twoSomeListing.superSede(position, replacement);
    }

    public void swap(long firstPosition, long secondPosition) {
        _twoSomeListing.swap(firstPosition, secondPosition);
    }

    public void swap(TwoSome firstElement, TwoSome secondElement) {
        _twoSomeListing.swap(firstElement, secondElement);
    }

    public long getPositionAt(TwoSome element) {
        return _twoSomeListing.getPositionAt(element);
    }
    
    public long getPositionAt(Object firstElement, Object secondElement) { 
            TwoSome auxiliar = new TwoSome(firstElement,secondElement);
            long position = this._twoSomeListing.getPositionAt(auxiliar);
            auxiliar = null;
            return position;
    }

    public void popFront() {
        _twoSomeListing.popFront();
    }

    public void popBack() {
        _twoSomeListing.popBack();
    }

    public void joinToBack(Listing<TwoSome> list) {
        _twoSomeListing.joinToBack(list);
    }

    public void joinToFront(Listing<TwoSome> list) {
        _twoSomeListing.joinToFront(list);
    }

    public void concatenateToFront(Listing<TwoSome> list) {
        _twoSomeListing.concatenateToFront(list);
    }

    public void concatenateToBack(Listing<TwoSome> list) {
        _twoSomeListing.concatenateToBack(list);
    }

    public void reverse() {
        _twoSomeListing.reverse();
    }

    public void clear() {
        _twoSomeListing.clear();
    }

    public void insertNextTo(Nodet<TwoSome> node, TwoSome element) {
        _twoSomeListing.insertNextTo(node, element);
    }

    public void insertPreviousTo(Nodet<TwoSome> node, TwoSome element) {
        _twoSomeListing.insertPreviousTo(node, element);
    }

    public void insertNextTo(Nodet<TwoSome> node, Object firstElement, Object secondElement)  { this._twoSomeListing.insertNextTo(node, new TwoSome(firstElement,secondElement));}
    
    public void insertPreviousTo(Nodet<TwoSome> node, Object firstElement, Object secondElement)  {  this._twoSomeListing.insertPreviousTo(node,new TwoSome(firstElement,secondElement));}

    public void insertAt(long position, TwoSome element) {
        _twoSomeListing.insertAt(position, element);
    }
    
    public void insertAt(long position, Object firstElement, Object secondElement) {  this._twoSomeListing.insertAt(position,new TwoSome(firstElement,secondElement));}

    public void introduceAt(long index, TwoSome element) {
        _twoSomeListing.introduceAt(index, element);
    }
    
    public void introduceAt(long index, Object firstElement, Object secondElement) { 
            TwoSome auxiliar = new TwoSome(firstElement,secondElement);
            this._twoSomeListing.introduceAt(index, auxiliar);
            auxiliar = null;
    }

    public void eraseAt(long position) {
        _twoSomeListing.eraseAt(position);
    }
    
    public void remove(Object firstElement, Object secondElement)  { 
            TwoSome auxiliar = new TwoSome(firstElement,secondElement);
            this._twoSomeListing.remove(auxiliar);
            auxiliar = null;
    }
    
    public void remove(TwoSome element) {
        _twoSomeListing.remove(element);
    }

    @Override
    public String toString() throws NullPointerException {
        return _twoSomeListing.toString();
    }

    @Override
    public int hashCode() {
        return _twoSomeListing.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return _twoSomeListing.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            _twoSomeListing.finalize();
        } finally {
            super.finalize();
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return _twoSomeListing.clone();
    }

    
     
}
