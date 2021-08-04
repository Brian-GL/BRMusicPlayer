/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;


public class DoubletListing <A,B>{

    private final Listing<Doublet<A,B>> _doubletListing;

    //Public constructors

    public DoubletListing() { this._doubletListing = new Listing<>();}

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public DoubletListing(DoubletListing<A,B> doubletListing) {
            this._doubletListing = new Listing<>();

            Nodet<Doublet<A,B>> middle = doubletListing.getMiddleNodet();

            if(middle != null) {

                    pushBack(middle.getValue());
                   Nodet<Doublet<A,B>> previous = middle.getPrevious();
                    Nodet<Doublet<A,B>> next = middle.getNext();

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
    
    //Public Methods

    public Doublet<A,B> get(long index){
        return _doubletListing.getElement(index);
    }
    
    public boolean isEmpty() {return this._doubletListing.isEmpty();}

    public long getSize() {return this._doubletListing.getSize();}

    public Doublet<A,B> getFirstDoublet() {  return this._doubletListing.getFirstElement();}

    public Doublet<A,B> getLastDoublet() {  return this._doubletListing.getLastElement();}

    public Doublet<A,B> getMediumDoublet() { return this._doubletListing.getMediumElement();}

    public Nodet<Doublet<A,B>> getFrontNodet() { return this._doubletListing.getFrontNodet(); }

    public Nodet<Doublet<A,B>> getBackNodet() { return this._doubletListing.getBackNodet(); }

    public Nodet<Doublet<A,B>> getMiddleNodet() { return this._doubletListing.getMiddleNodet(); }
   
    public Doublet<A,B> dequeue() throws  NullPointerException{  return _doubletListing.dequeue();}

    public Doublet<A,B> unstack() { return this._doubletListing.unstack(); }

    public void pushBack(Doublet<A,B> element) { this._doubletListing.pushBack(element);}

    public void pushBack(A firstElement, B secondElement) {  this._doubletListing.pushBack(new Doublet<>(firstElement,secondElement));}

    public void pushFront(Doublet<A,B> element) { this._doubletListing.pushFront(element);}

    public void pushFront(A firstElement, B secondElement) {  this._doubletListing.pushFront(new Doublet<>(firstElement,secondElement));}

    public boolean containsDoublet(Doublet<A,B> element) { return this._doubletListing.containsElement(element);}

    public boolean containsFirstElement(A element) {
            
        if(this._doubletListing.getSize() == 0)
                return false;
        else if (this._doubletListing.getSize() == 1)
                return this._doubletListing.getFirstElement().getFirstElement().equals(element);
         else if (this._doubletListing.getSize() == 2)
                return this._doubletListing.getFirstElement().getFirstElement().equals(element) || this._doubletListing.getLastElement().getFirstElement().equals(element);
        else{

                Nodet<Doublet<A,B>> first = this._doubletListing.getFrontNodet();
                Nodet<Doublet<A,B>> last = this._doubletListing.getBackNodet();

                double middleIndex = ((double)this._doubletListing.getSize())/2;

                for(double i = 0; i < middleIndex;i++) {

                        if(first.getValue().getFirstElement().equals(element))
                                return true;

                        if(last.getValue().getFirstElement().equals(element))
                                return true;

                        first = first.getNext();
                        last = last.getPrevious();
                }

                return false;
        }
            
    }

    public boolean containsSecondElement(B element) {
        if(this._doubletListing.getSize() == 0)
                return false;
        else if (this._doubletListing.getSize() == 1)
                return this._doubletListing.getFirstElement().getSecondElement().equals(element);
         else if (this._doubletListing.getSize() == 2)
                return this._doubletListing.getFirstElement().getSecondElement().equals(element) || this._doubletListing.getLastElement().getSecondElement().equals(element);
        else{

            Nodet<Doublet<A,B>> first = this._doubletListing.getFrontNodet();
            Nodet<Doublet<A,B>> last = this._doubletListing.getBackNodet();

            double middleIndex = ((double)this._doubletListing.getSize())/2;

            for(double i = 0; i < middleIndex;i++) {

                    if(first.getValue().getSecondElement().equals(element))
                            return true;

                    if(last.getValue().getSecondElement().equals(element))
                            return true;

                    first = first.getNext();
                    last = last.getPrevious();
            }

            return false;
        }
    }
    
    public Doublet<A,B> getDoubletForSecondElement(B element) {
        if(this._doubletListing.getSize() == 0)
                return null;
        else{

            Nodet<Doublet<A,B>> first = this._doubletListing.getFrontNodet();
            Nodet<Doublet<A,B>> last = this._doubletListing.getBackNodet();

            double middleIndex = ((double)this._doubletListing.getSize())/2;

            for(double i = 0; i < middleIndex;i++) {

                if(first.getValue().getSecondElement().equals(element))
                        return first.getValue();

                if(last.getValue().getSecondElement().equals(element))
                        return last.getValue();

                first = first.getNext();
                last = last.getPrevious();
            }

            return null;
        }
    }

    public void removeDoubletForSecondElement(B element) {
        
        if(!this.isEmpty()){

            Nodet<Doublet<A,B>> first = this._doubletListing.getFrontNodet();
            Nodet<Doublet<A,B>> last = this._doubletListing.getBackNodet();

            double middleIndex = ((double)this._doubletListing.getSize())/2;

            for(double i = 0; i < middleIndex;i++) {

                if(first.getValue().getSecondElement().equals(element)){
                        remove(first.getValue());
                        return;
                }

                if(last.getValue().getSecondElement().equals(element)){
                    remove(last.getValue());
                    return;
                }

                first = first.getNext();
                last = last.getPrevious();
            }
        }
    }
    
    public Doublet<A,B> getDoubletForFirstElement(A element) {
        if(this._doubletListing.getSize() == 0)
                return null;
        else{

            Nodet<Doublet<A,B>> first = this._doubletListing.getFrontNodet();
            Nodet<Doublet<A,B>> last = this._doubletListing.getBackNodet();

            double middleIndex = ((double)this._doubletListing.getSize())/2;

            for(double i = 0; i < middleIndex;i++) {

                if(first.getValue().getFirstElement().equals(element))
                        return first.getValue();

                if(last.getValue().getFirstElement().equals(element))
                        return last.getValue();

                first = first.getNext();
                last = last.getPrevious();
            }

            return null;
        }
    }
    
    public B getSecondElementByKey(A element) {
        if(this._doubletListing.getSize() == 0)
                return null;
        else{

            Nodet<Doublet<A,B>> first = this._doubletListing.getFrontNodet();
            Nodet<Doublet<A,B>> last = this._doubletListing.getBackNodet();

            double middleIndex = ((double)this._doubletListing.getSize())/2;

            for(double i = 0; i < middleIndex;i++) {

                if(first.getValue().getFirstElement().equals(element))
                        return first.getValue().getSecondElement();

                if(last.getValue().getFirstElement().equals(element))
                        return last.getValue().getSecondElement();

                first = first.getNext();
                last = last.getPrevious();
            }

            return null;
        }
    }
    
    public boolean containsElements(A firstElement, B secondElement) { 
            Doublet<A,B> auxiliar = new Doublet<>(firstElement,secondElement);
            boolean contains = this._doubletListing.containsElement(auxiliar);
            auxiliar = null;
            return contains;
    }

   
    public void addToFront(Doublet<A,B> element) { this._doubletListing.addToFront(element);}

    public void addToFront(A firstElement, B secondElement)  {  this._doubletListing.addToFront( new Doublet<>(firstElement,secondElement));}

    public void addToBack(Doublet<A,B> element) { this._doubletListing.addToBack(element);}

    public void addToBack(A firstElement, B secondElement) { this._doubletListing.addToBack(new Doublet<>(firstElement,secondElement));}

    public void replace(Doublet<A,B> elementToReplace,Doublet<A,B> replacement){ this._doubletListing.replace(elementToReplace,replacement);}

    public void replace(A firstElementToReplace, B secondElementToReplace,Doublet<A,B> replacement) { 
            Doublet<A,B> auxiliar = new Doublet<>(firstElementToReplace,secondElementToReplace);
            this._doubletListing.replace(auxiliar,replacement);
            auxiliar = null;
    }

    public void replace(A firstElementToReplace, B secondElementToReplace,A firstReplacement, B secondReplacement) { 
            Doublet<A,B> firstAuxiliar = new Doublet<>(firstElementToReplace,secondElementToReplace);
            Doublet<A,B> secondAuxiliar = new Doublet<>(firstReplacement,secondReplacement);
            this._doubletListing.replace(firstAuxiliar,secondAuxiliar);
            firstAuxiliar = null;
            secondAuxiliar = null;
    }

    public void replace(Doublet<A,B> elementToReplace, A firstReplacement, B secondReplacement) { 
            Doublet<A,B> auxiliar = new Doublet<>(firstReplacement,secondReplacement);
            this._doubletListing.replace(elementToReplace,auxiliar);
            auxiliar = null;
    }

    public void replacing(Doublet<A,B> elementToReplace, Doublet<A,B> replacement) { this._doubletListing.replacing(elementToReplace,replacement);}

    public void replacing(Doublet<A,B> elementToReplace, A firstReplacement, B secondReplacement)  { 
            Doublet<A,B> auxiliar = new Doublet<>(firstReplacement,secondReplacement);
            this._doubletListing.replacing(elementToReplace,auxiliar);
            auxiliar = null;
    }

    public void replacing(A firstElementToReplace, B secondElementToReplace, A firstReplacement, B secondReplacement)  { 
            Doublet<A,B> firstAuxiliar = new Doublet<>(firstElementToReplace,secondElementToReplace);
            Doublet<A,B> secondAuxiliar = new Doublet<>(firstReplacement,secondReplacement);
            this._doubletListing.replacing(firstAuxiliar,secondAuxiliar);
            firstAuxiliar = null;
            secondAuxiliar = null;
    }

    public void replacing(A firstElementToReplace, B secondElementToReplace, Doublet<A,B> replacement)  { 
            Doublet<A,B> auxiliar = new Doublet<>(firstElementToReplace,secondElementToReplace);
            this._doubletListing.replacing(auxiliar,replacement);
            auxiliar = null;
    }

    public void replace(long position,Doublet<A,B> replacement){ this._doubletListing.replace(position, replacement); }

    public void replace(long position,A firstReplacement, B secondReplacement) { 
            Doublet<A,B> auxiliar = new Doublet<>(firstReplacement,secondReplacement);
            this._doubletListing.replace(position, auxiliar);
            auxiliar = null;
    }

    public void superSede(Doublet<A,B> elementToReplace, Doublet<A,B> replacement) { this._doubletListing.superSede(elementToReplace, replacement);}

    public void superSede(A firstElementToReplace, B secondElementToReplace,Doublet<A,B> replacement) { 
            Doublet<A,B> auxiliar = new Doublet<>(firstElementToReplace,secondElementToReplace);
            this._doubletListing.superSede(auxiliar,replacement);
            auxiliar = null;
    }

    public void superSede(A firstElementToReplace, B secondElementToReplace,A firstReplacement, B secondReplacement) { 
            Doublet<A,B> firstAuxiliar = new Doublet<>(firstElementToReplace,secondElementToReplace);
            Doublet<A,B> secondAuxiliar = new Doublet<>(firstReplacement,secondReplacement);
            this._doubletListing.superSede(firstAuxiliar,secondAuxiliar);
            firstAuxiliar = null;
            secondAuxiliar = null;
    }

    public void superSede(Doublet<A,B> elementToReplace, A firstReplacement, B secondReplacement) { 
            Doublet<A,B> auxiliar = new Doublet<>(firstReplacement,secondReplacement);
            this._doubletListing.superSede(elementToReplace,auxiliar);
            auxiliar = null;
    }

    public void superSede(long position, Doublet<A,B> replacement) { this._doubletListing.superSede(position, replacement);}

    public void superSede(long position,A firstReplacement, B secondReplacement) { 
            Doublet<A,B> auxiliar = new Doublet<>(firstReplacement,secondReplacement);
            this._doubletListing.superSede(position, auxiliar);
            auxiliar = null;
    }

    public void swap(long firstPosition, long secondPosition){this._doubletListing.swap(firstPosition, secondPosition);}

    public void swap(Doublet<A,B> firstElement, Doublet<A,B> secondElement) { this._doubletListing.swap(firstElement, secondElement);}

    public void swap(Doublet<A,B> pairElement, A firstElement, B secondElement) { 
            Doublet<A,B> auxiliar = new Doublet<>(firstElement,secondElement);
            this._doubletListing.swap(pairElement, auxiliar);
            auxiliar = null;
    }

    public void swap(A firstElement, B secondElement, Doublet<A,B> pairElement) {
            Doublet<A,B> auxiliar = new Doublet<>(firstElement,secondElement);
            this._doubletListing.swap(auxiliar, pairElement);
            auxiliar = null;
    }

    public void swap(A firstElement, B secondElement, A thirdElement, B fourthElement) { 
            Doublet<A,B> firstAuxiliar = new Doublet<>(firstElement,secondElement);
            Doublet<A,B> secondAuxiliar = new Doublet<>(thirdElement,fourthElement);
            this._doubletListing.swap(firstAuxiliar, secondAuxiliar);
            firstAuxiliar = null;
            secondAuxiliar = null;
    }

    public long getPositionAt(Doublet<A,B> element){ return this._doubletListing.getPositionAt(element);}

    public long getPositionAt(A firstElement, B secondElement) { 
            Doublet<A,B> auxiliar = new Doublet<>(firstElement,secondElement);
            long position = this._doubletListing.getPositionAt(auxiliar);
            auxiliar = null;
            return position;
    }

    public void popFront() { this._doubletListing.popFront();}

    public void popBack() { this._doubletListing.popBack();}

    public void joinToBack(DoubletListing<A,B> doubletListing){
            Nodet<Doublet<A,B>> auxiliar = doubletListing.getFrontNodet();
            while(auxiliar != null) {
                    this.pushBack(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    public void joinToFront(DoubletListing<A,B> doubletListing){
            Nodet<Doublet<A,B>> auxiliar = doubletListing.getFrontNodet();
            while(auxiliar != null) {
                    this.pushFront(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    public void concatenateToFront(DoubletListing<A,B> doubletListing){
            Nodet<Doublet<A,B>> auxiliar = doubletListing.getFrontNodet();
            while(auxiliar != null) {
                    this.addToFront(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    public void concatenateToBack(DoubletListing<A,B> doubletListing){
            Nodet<Doublet<A,B>> auxiliar = doubletListing.getFrontNodet();
            while(auxiliar != null) {
                    this.addToBack(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    public void reverse() {

            if(!this._doubletListing.isEmpty()) {

                    Nodet<Doublet<A,B>>  first = this._doubletListing.getFrontNodet();
                    Nodet<Doublet<A,B>> last = this._doubletListing.getBackNodet();

                    double middleIndex = ((double)this._doubletListing.getSize())/2;

                    for(double i = 0; i < middleIndex;i++) {

                            Doublet<A,B> firstvalue = first.getValue();
                            first.setValue(last.getValue());
                            last.setValue(firstvalue);

                            first = first.getNext();
                            last = last.getPrevious();
                    }
            }
    }

    public void clear() { this._doubletListing.clear();}

    public void insertNextTo(Nodet<Doublet<A,B>> node, Doublet<A,B> element) { this._doubletListing.insertNextTo(node, element);}

    public void insertNextTo(Nodet<Doublet<A,B>> node, A firstElement, B secondElement)  { this._doubletListing.insertNextTo(node, new Doublet<>(firstElement,secondElement));}

    public void insertPreviousTo(Nodet<Doublet<A,B>> node, Doublet<A,B> element) { this._doubletListing.insertPreviousTo(node, element);}

    public void insertPreviousTo(Nodet<Doublet<A,B>> node, A firstElement, B secondElement)  {  this._doubletListing.insertPreviousTo(node,new Doublet<>(firstElement,secondElement));}

    public void insertAt(long position, Doublet<A,B> element){ this._doubletListing.insertAt(position, element);}

    public void insertAt(long position, A firstElement, B secondElement) {  this._doubletListing.insertAt(position,new Doublet<>(firstElement,secondElement));}

    public void introduceAt(long index, Doublet<A,B> element){ this._doubletListing.introduceAt(index, element);}

    public void introduceAt(long index, A firstElement, B secondElement) { 
            Doublet<A,B> auxiliar = new Doublet<>(firstElement,secondElement);
            this._doubletListing.introduceAt(index, auxiliar);
            auxiliar = null;
    }

    public void eraseAt(long position) { this._doubletListing.eraseAt(position);}

    public void remove(Doublet<A,B> element)  { this._doubletListing.remove(element);}

    public void remove(A firstElement, B secondElement)  { 
            Doublet<A,B> auxiliar = new Doublet<>(firstElement,secondElement);
            this._doubletListing.remove(auxiliar);
            auxiliar = null;
    }

    //Override methods

    @Override
    public String toString() { return _doubletListing.toString();}

    @Override
    public int hashCode() {
        return _doubletListing.hashCode();
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        return _doubletListing.equals(obj);
    }

    @Override
   
    @SuppressWarnings({"FinalizeCalledExplicitly", "FinalizeDeclaration"})
    protected void finalize() throws Throwable {
        try {
            _doubletListing.finalize();
        } finally {
            super.finalize();
        }
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    protected Object clone() throws CloneNotSupportedException {
        return _doubletListing.clone();
    }


}