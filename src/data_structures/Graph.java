/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

import java.util.Objects;


public class Graph<T>{
	
    /**Vertex class.*/
    public class Vertex{

            //Private Vertex's attributes.

            /**Front and first Edge pointer of the vertex.*/
            private Edge _front; 
            /**Back and last Edge pointer of the vertex.*/
            private Edge _back; 
            /**The edge pointer who is in the middle of the vertex.*/
            private Edge _middle; 
            /**The number of Edges into the vertex.*/
            private long _size; 
            /**The 'T' value to save.*/
            private T _value;
            /**Vertex previous pointer.*/
            private Vertex _previous;
            /**Vertex next pointer.*/
            private Vertex _next;
            /**Listing of all edges pointers.*/
            private Listing<Edge> _edges;
            /**The total weight of every edge pointers into the vertex.*/
            private double _totalWeight;

            //Private methods

            /**Sets the initial values of all attributes, except for the 'T' value.*/
            private void _init() {
                    this._previous = this._next = null;
                    this._front = this._back = this._middle = null;
                    this._size = 0;
                    this._edges = new Listing<>();
                    this._totalWeight = 0.0;
            }

            private void pushBack(Edge edge) {
                    if(edge != null) {
                            if(this.isEmpty()){
                                    //It does not have edges
                                    this._front = this._back = this._middle = edge;
                                    this._edges.pushBack(edge);
                                    _allEdges.pushBack(edge);
                                    this._size++;
                                    this._totalWeight += edge.getWeight();
                                    _allWeight += edge.getWeight();
                            }
                            else {
                                    this._back.setNext(edge);
                                    edge.setPrevious(this._back);
                                    this._back  = edge;
                                    this._edges.pushBack(edge);
                                    _allEdges.pushBack(edge);
                                    this._size++;
                                    this._totalWeight += edge.getWeight();
                                    _allWeight += edge.getWeight();
                                    if(this._size % 2 != 0)
                                            this._middle = this._middle.getNext();
                            }
                    }
            }

            /**Adds a Edge into the Vertex from the front side.*/
            private void pushFront(Edge edge) {
                    if(edge != null) {

                            if(this.isEmpty()){
                                    //It does not have edges
                                    this._front = this._back = this._middle = edge;
                                    this._edges.pushBack(edge);
                                    _allEdges.pushFront(edge);
                                    this._size++;
                                    this._totalWeight += edge.getWeight();
                                    _allWeight += edge.getWeight();
                            }
                            else {
                                    this._front.setPrevious(edge);
                                    edge.setNext(this._front);
                                    this._front  = edge;
                                    this._edges.pushFront(edge);
                                    _allEdges.pushFront(edge);
                                    this._size++;
                                    this._totalWeight += edge.getWeight();
                                    _allWeight += edge.getWeight();
                                    if(this._size % 2 == 0)
                                            this._middle = this._middle.getPrevious();
                            }
                    }
            }

            //Public constructors

            public Vertex(T value) { 
                    this._init();
                    this._value = value;
            }

            public Vertex(T value, Vertex previous, Vertex next){
                    this._value = value;
                    this._previous = previous;
                    this._next = next;
                    this._front = this._back = this._middle = null;
                    this._size = 0;
                    this._edges = new Listing<>();
                    this._totalWeight = 0.0;
            }

            @SuppressWarnings("OverridableMethodCallInConstructor")
            public Vertex(Vertex vertex){

                    this._init();
                    this._value = vertex.getValue();

                    Edge middle = vertex.getMiddleEdge();

                    if(middle != null) {

                            this.pushBack(middle.getWeight(),middle.getDestination());
                            Edge previous = middle.getPrevious();
                            Edge next = middle.getNext();

                            while(previous != null || next != null) {

                                    if(previous != null) {
                                            this.pushFront(previous.getWeight(),previous.getDestination());
                                            previous = previous.getPrevious();
                                    }
                                    if(next != null) {
                                            this.pushBack(next.getWeight(),next.getDestination());
                                            next = next.getNext();
                                    }
                            }
                    }
            }

            @SuppressWarnings("OverridableMethodCallInConstructor")
            public Vertex(Vertex vertex, Vertex previous, Vertex next){

                    this._previous = previous;
                    this._next = next;
                    this._front = this._back = this._middle = null;
                    this._size = 0;
                    this._edges = new Listing<>();
                    this._totalWeight = 0.0;
                    this._value = vertex.getValue();

                    Edge middle = vertex.getMiddleEdge();

                    if(middle != null) {

                            this.pushBack(middle.getWeight(),middle.getDestination());
                            Edge previousEdge = middle.getPrevious();
                            Edge nextEdge = middle.getNext();

                            while(previous != null || next != null) {

                                    if(previous != null) {
                                            this.pushFront(previousEdge.getWeight(),previousEdge.getDestination());
                                            previous = previous.getPrevious();
                                    }
                                    if(next != null) {
                                            this.pushBack(nextEdge.getWeight(),nextEdge.getDestination());
                                            next = next.getNext();
                                    }
                            }
                    }
            }

            //Public Methods

            public boolean isEmpty() {return this._front == null;}

            public long getSize() {return this._size;}

            public void setValue(T value) {this._value = value;}

            public T getValue() {return this._value;}

            public Listing<Edge> getEdges(){return this._edges;}

            public double getTotalWeight() {return this._totalWeight;}

            public Edge getFrontEdge() { return this._front; }

            public Edge getLastEdge() { return this._back; }

            public Edge getMiddleEdge() { return this._middle; }

            public void setPrevious(Vertex previous) {this._previous = previous;}

            public Vertex getPrevious() {return this._previous;}

            public void setNext(Vertex next) {this._next = next;}

            public Vertex getNext() {return this._next;}

            public Edge dequeue()  throws  NullPointerException{
                    Edge auxiliar = this._front;
                    popFront();
                    return auxiliar;
            }

            public Edge unstack() throws NullPointerException{
                Edge auxiliar = this._back;
                popBack();
                return auxiliar;
            }

            public void pushBack(double weight, Vertex destination)  {
                    Edge edge = new Edge(weight,this,destination);
                    if(!containsEdge(edge))
                            this.pushBack(edge);
                    else
                            edge = null;
            }

            public void pushFront(double weight, Vertex destination)  {
                    Edge edge = new Edge(weight,this,destination);
                    if(!containsEdge(edge))
                            this.pushFront(edge);
                    else
                            edge = null;
            }

            public Edge getEdge(long index) {

                    if(!this.isEmpty()){
                            long realIndex = index % this._size;

                            if(realIndex == 0) 
                                    return this._front;
                            else if(realIndex == this._size-1) 
                                    return this._back;

                            else { 

                                    long middleIndex = this._size/2;

                                    if(this._size % 2 != 0) { 
                                            long differenceBetweenFront = Math.abs(realIndex-1);
                                            long differenceBetweenBack = Math.abs(realIndex-(this._size-2));
                                            long differenceBetweenMiddle = Math.abs(realIndex-middleIndex);

                                            if( (differenceBetweenFront < differenceBetweenBack 
                                                            && differenceBetweenFront < differenceBetweenMiddle ) 
                                                            || differenceBetweenFront == differenceBetweenMiddle) {

                                                    Edge auxiliar = this._front.getNext();

                                                    for(long i = 1; i < realIndex;i++)
                                                            auxiliar = auxiliar.getNext();

                                                    return auxiliar;

                                            }

                                            if(differenceBetweenMiddle < differenceBetweenBack 
                                                            && differenceBetweenMiddle < differenceBetweenFront) {

                                                    if(realIndex > middleIndex) {
                                                            Edge auxiliar = this._middle.getNext();

                                                            for(long i = middleIndex+1; i < realIndex;i++)
                                                                    auxiliar = auxiliar.getNext();

                                                            return auxiliar;
                                                    }
                                                    else if(realIndex == middleIndex)
                                                            return this._middle;

                                                    else {
                                                            Edge auxiliar = this._middle.getPrevious();

                                                            for(long i = middleIndex-1; i > realIndex;i--)
                                                                    auxiliar = auxiliar.getPrevious();

                                                            return auxiliar;
                                                    }

                                            }
                                            if((differenceBetweenBack < differenceBetweenMiddle 
                                                            && differenceBetweenBack < differenceBetweenFront) 
                                                            || differenceBetweenBack == differenceBetweenMiddle ) {

                                                    Edge auxiliar = this._back.getPrevious();

                                                    for(long i = this._size-2; i > realIndex;i--)
                                                            auxiliar = auxiliar.getPrevious();

                                                    return auxiliar;
                                            }

                                    }
                                    else { 
                                            long differenceBetweenFront = Math.abs(realIndex-1);
                                            long differenceBetweenBack = Math.abs(realIndex-(this._size-2));
                                            long differenceBetweenMiddle = Math.abs(realIndex-(middleIndex-1));

                                            if( (differenceBetweenFront < differenceBetweenBack 
                                                            && differenceBetweenFront < differenceBetweenMiddle ) 
                                                            || differenceBetweenFront == differenceBetweenMiddle) {

                                                    Edge auxiliar = this._front.getNext();

                                                    for(long i = 1; i < realIndex;i++)
                                                            auxiliar = auxiliar.getNext();

                                                    return auxiliar;
                                            }
                                            if(differenceBetweenMiddle < differenceBetweenBack 
                                                            && differenceBetweenMiddle < differenceBetweenFront) {

                                                    if(realIndex > (middleIndex-1)) {

                                                            Edge auxiliar = this._middle.getNext();

                                                            for(long i = middleIndex; i < realIndex;i++)
                                                                    auxiliar = auxiliar.getNext();

                                                            return auxiliar;
                                                    }
                                                    else if(realIndex == (middleIndex-1))
                                                            return this._middle;
                                                    else {
                                                            Edge auxiliar = this._middle.getPrevious();

                                                            for(long i = middleIndex-2; i > realIndex;i--)
                                                                    auxiliar = auxiliar.getPrevious();

                                                            return auxiliar;
                                                    }
                                            }

                                            if((differenceBetweenBack < differenceBetweenMiddle 
                                                            && differenceBetweenBack < differenceBetweenFront) 
                                                            || differenceBetweenBack == differenceBetweenMiddle ) {

                                                    Edge auxiliar = this._back.getPrevious();

                                                    for(long i = this._size-2; i > realIndex;i--)
                                                            auxiliar = auxiliar.getPrevious();

                                                    return auxiliar;
                                            }
                                    }
                            }
                    }
                    return null;
            }

            public boolean containsEdge(Edge element) {
                    if(this._size == 0)
                            return false;
                    else if(this._size == 1)
                            return this._front.equals(element);
                    else if(this._size == 2)
                                    return this._front.equals(element) || this._back.equals(element);
                    else{

                            Edge first = this._front;
                            Edge last = this._back;

                            double middleIndex = ((double)this._size)/2;

                            for(double i = 0; i < middleIndex;i++) {

                                    if(first.equals(element))
                                            return true;

                                    if(last.equals(element))
                                            return true;

                                    first = first.getNext();
                                    last = last.getPrevious();
                            }

                            return false;
                    }
                    
            }

            public boolean containsElement(T destination) {
                    Edge first = this._front;
                    Edge last = this._back;

                    double middleIndex = ((double)this._size)/2;

                    for(double i = 0; i < middleIndex;i++) {

                            if(first.getDestination().getValue().equals(destination))
                                    return true;

                            if(last.getDestination().getValue().equals(destination))
                                    return true;

                            first = first.getNext();
                            last = last.getPrevious();
                    }

                    return false;
            }

            public long getPositionAt(Edge element){
                    long frontCounter = 0;
                    long backCounter = this._size-1;
                    Edge first = this._front;
                    Edge last = this._back;

                    double middleIndex = ((double)this._size)/2;

                    for(double i = 0; i < middleIndex;i++) {

                            if(first.equals(element))
                                    return frontCounter;

                            if(last.equals(element))
                                    return backCounter;

                            frontCounter++;
                            backCounter--;

                            first = first.getNext();
                            last = last.getPrevious();
                    }
                    return -1;
            }

            public void popFront() {
                    if(!this.isEmpty()){

                            Edge erasedEdge = this._front;
                            _allWeight -= erasedEdge.getWeight();
                            this._totalWeight -= erasedEdge.getWeight();
                            _allEdges.remove(erasedEdge);

                            if(this._size % 2 == 0) 
                                    this._middle = this._middle.getNext();

                            this._front = this._front.getNext();

                        if(this._front != null)
                                    this._front.setPrevious(null);

                            --this._size;

                            erasedEdge = null;

                            this._edges.popFront();
                    }
            }

            public void popBack() {
                    if(!this.isEmpty()){
                            Edge erasedEdge = this._back;
                            _allWeight -= erasedEdge.getWeight();
                            this._totalWeight -= erasedEdge.getWeight();
                            _allEdges.remove(erasedEdge);
                            if(this._size % 2 != 0) 
                                    this._middle = this._middle.getPrevious();

                            this._back = this._back.getPrevious();

                        if(this._back != null)
                                    this._back.setNext(null);

                            --this._size;
                            erasedEdge = null;

                            this._edges.popBack();
                    }

            }

            public void clear() {
                    if(!this.isEmpty()){
                            while(!this.isEmpty()) {
                                    popFront();
                                    popBack();
                            }
                            this._size = 0;
                            this._back = this._middle  = this._front = null;
                    }
            }
            
            public void eraseAt(long position) {
                    if(!this.isEmpty()){

                            long realPosition = position % this._size;

                            if(realPosition == 0) 
                                    popFront();

                            else if(realPosition == (this._size -1) )
                                    popBack();

                            else{

                                    Edge previousEdge = getEdge(position-1);
                                    Edge erasedEdge = previousEdge.getNext();
                                    this._totalWeight -= erasedEdge.getWeight();
                                    _allWeight -= erasedEdge.getWeight();
                                    _allEdges.remove(erasedEdge);
                                    boolean isMiddle = erasedEdge.equals(this._middle);
                                    Edge nextEdge = erasedEdge.getNext();

                                    previousEdge.setNext(nextEdge);
                                    nextEdge.setPrevious(previousEdge);
                                    erasedEdge = null;

                            this._size--;

                            if(!isMiddle) {
                                    if(this._size % 2 != 0)
                                                    this._middle = this._middle.getNext();
                            }
                            else 
                                    this._middle = (this._size % 2 == 0) ? previousEdge : nextEdge;

                            this._edges.eraseAt(position);

                            }
                    }

            }

            public void remove(T destination)  {
                    Edge first = this._front;
                    Edge last = this._back;

                    double middleIndex = ((double)this._size)/2;

                    for(double i = 0; i < middleIndex;i++) {

                            if(first.getDestination().getValue().equals(destination)) {
                                    remove(first);
                                    return;
                            }


                            if(last.getDestination().getValue().equals(destination)) {
                                    remove(last);
                                    return;
                            }

                            first = first.getNext();
                            last = last.getPrevious();
                    }

            }

            public void remove(Edge edge)  {
                    if(!this.isEmpty()) {
                            if(this._front.equals(edge))
                                    popFront();

                            else if(this._back.equals(edge))
                                    popBack();

                            else {

                                    if(containsEdge(edge)) {
                                            this._totalWeight -= edge.getWeight();
                                            _allWeight -= edge.getWeight();
                                            _allEdges.remove(edge);
                                            Edge previousEdge = edge.getPrevious();
                                            boolean isMiddle = edge.equals(this._middle);
                                            Edge nextEdge = edge.getNext();

                                            previousEdge.setNext(nextEdge);
                                            nextEdge.setPrevious(previousEdge);
                                            edge = null;

                                    this._size--;

                                    if(!isMiddle) {
                                            if(this._size % 2 != 0)
                                                            this._middle = this._middle.getNext();
                                    }
                                    else 
                                            this._middle = (this._size % 2 == 0) ? previousEdge : nextEdge;

                                    this._edges.remove(edge);

                                    }
                            }
                    }
            }

            //Override methods

            @Override
            public String toString(){

                    String newString = new String();

                    Edge auxiliar = this._front;

                    while(auxiliar != null){
                            newString += "[" +auxiliar+"] ";
                            auxiliar = auxiliar.getNext();
                    }
                    return newString;
            }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 53 * hash + (int) (this._size ^ (this._size >>> 32));
            hash = 53 * hash + Objects.hashCode(this._value);
            hash = 53 * hash + Objects.hashCode(this._edges);
            hash = 53 * hash + (int) (Double.doubleToLongBits(this._totalWeight) ^ (Double.doubleToLongBits(this._totalWeight) >>> 32));
            return hash;
        }

        @Override 
        public boolean equals(Object object) {
                if (object == this) 
                return true; 

            if (!(object instanceof Graph.Vertex))
                return false; 

                    @SuppressWarnings("unchecked")
                    Vertex objectRow = (Vertex) object; 

            if(this._size == objectRow._size) {

                    Edge thisFront = this._front;
                    Edge objectFront = objectRow._front;

                    while(thisFront != null) {

                            if(!thisFront.equals(objectFront))
                                    return false;

                            thisFront = thisFront.getNext();
                            objectFront = objectFront.getNext();
                    }

                    return true;
            }

            return false;
        }

            @Override
            @SuppressWarnings("FinalizeDeclaration")
            protected void finalize()  throws Throwable{ 
                  super.finalize();
            }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone(); //To change body of generated methods, choose Tools | Templates.
        }

    }

    /**Edge class*/
    public class Edge{

            //Private attributes

            /**The weight value of the edge.*/
            private Double _weight;
            /**The origin vertex pointer.*/
            private Vertex _origin;
            /**The destination vertex pointer.*/
            private Vertex _destination;
            /**The previous edge pointer.*/
            private Edge _previous;
            /**The next edge pointer.*/
            private Edge _next;

            //Private methods

            private byte accessLevel(){

                    //Both Edges next and previous are not null.
                    if(this._next != null && this._previous != null){
                            //Both Vertex are not null. That means nothing is null.
                            if(this._origin != null && this._destination != null)
                                    return 0;
                            //Just destination is null.
                            else if(this._origin != null && this._destination == null)
                                    return 1;
                            //Just origin is null.
                            else if(this._origin == null && this._destination != null)
                                    return 2;
                            //Both Vertex are null.
                            else
                                    return 3;
                    }
                    //Just next Edge is not null.
                    else if(this._next != null && this._previous == null){
                            //Both Vertex are not null. That means nothing is null.
                            if(this._origin != null && this._destination != null)
                                    return 4;
                            //Just destination is null.
                            else if(this._origin != null && this._destination == null)
                                    return 5;
                            //Just origin is null.
                            else if(this._origin == null && this._destination != null)
                                    return 6;
                            //Both Vertex are null.
                            else
                                    return 7;
                    }
                    //Just previous Edge is not null.
                    else if(this._next == null && this._previous != null){
                            //Both Vertex are not null. That means nothing is null.
                            if(this._origin != null && this._destination != null)
                                    return 8;
                            //Just destination is null.
                            else if(this._origin != null && this._destination == null)
                                    return 9;
                            //Just origin is null.
                            else if(this._origin == null && this._destination != null)
                                    return 10;
                            //Both Vertex are null.
                            else
                                    return 11;
                    }
                    //Both Edges are null.
                    else{
                            //Both Vertex are not null. That means nothing is null.
                            if(this._origin != null && this._destination != null)
                                    return 12;
                            //Just destination is null.
                            else if(this._origin != null && this._destination == null)
                                    return 13;
                            //Just origin is null.
                            else if(this._origin == null && this._destination != null)
                                    return 14;
                            //Both Vertex are null.
                            else
                                    return 15;
                    }
            }

            //Public constructors

            public Edge(double weight, Vertex origin){
                    this._weight = weight;
                    this._origin = origin;
                    this._destination = null;
                    this._previous = this._next = null;
            }

            public Edge(double weight, Vertex origin, Edge previous, Edge next){
                    this._weight = weight;
                    this._origin = origin;
                    this._destination = null;
                    this._previous = previous;
                    this._next = next;
            }

            public Edge(double weight, Vertex origin, Vertex destination){
                    this._weight = weight;
                    this._origin = origin;
                    this._destination = destination;
                    this._previous = this._next = null;
            }

            public Edge(double weight, Vertex origin, Vertex destination, Edge previous, Edge next){
                    this._weight = weight;
                    this._origin = origin;
                    this._destination = destination;
                    this._previous = previous;
                    this._next = next;
            }

            //Public methods

            public void setWeight(double value) { this._weight = value;}

            public double getWeight() { return this._weight;}

            public void setPrevious(Edge previous) {this._previous = previous;}

            public Edge getPrevious() {return this._previous;}

            public void setNext(Edge next) {this._next = next;}

            public Edge getNext() {return this._next;}

            public void setOrigin(Vertex origin){this._origin = origin;}

            public Vertex getOrigin(){return this._origin;}

            public void setDestination(Vertex destination){this._destination = destination;}

            public Vertex getDestination(){return this._destination;}

            //Overrides methods

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 37 * hash + (int) (Double.doubleToLongBits(this._weight) ^ (Double.doubleToLongBits(this._weight) >>> 32));
            hash = 37 * hash + Objects.hashCode(this._origin);
            hash = 37 * hash + Objects.hashCode(this._destination);
            return hash;
        }
            
            
            
            @Override
            public boolean equals(Object object) {
                if (object == this) 
                return true; 

            if (!(object instanceof Graph.Edge))
                return false; 

            @SuppressWarnings("unchecked")
            Edge objectEdge = (Edge) object;

            //Both Edges next and previous are not null.
                    if(this._next != null && this._previous != null){
                            //Both Vertex are not null.
                            if(this._origin != null && this._destination != null){
                                    if(objectEdge.accessLevel() == 0)
                                            return Objects.equals(this._weight, objectEdge._weight) && this._origin.getValue().equals(objectEdge.getOrigin().getValue()) 
                                                    && this._destination.getValue().equals(objectEdge.getDestination().getValue()) && this._next._weight == (objectEdge.getNext().getWeight())
                                                    && this._previous._weight == objectEdge.getPrevious().getWeight();
                                    return false;
                            }
                            //Just destination is null.
                            if(this._origin != null && this._destination == null){
                                    if(objectEdge.accessLevel() == 1)
                                            return Objects.equals(this._weight, objectEdge._weight) && this._origin.getValue().equals(objectEdge.getOrigin().getValue()) 
                                                    &&  this._next._weight == objectEdge.getNext().getWeight() && this._previous.getWeight() == (objectEdge.getPrevious().getWeight());
                                    return false;
                            }
                            //Just origin is null.
                            if(this._origin == null && this._destination != null){
                                    if(objectEdge.accessLevel() == 2)
                                            return Objects.equals(this._weight, objectEdge._weight) && this._destination.getValue().equals(objectEdge.getDestination().getValue()) 
                                                    &&  this._next._weight == (objectEdge.getNext().getWeight()) && this._previous.getWeight() == (objectEdge.getPrevious().getWeight());
                                    return false;
                            }
                            //Both Vertex are null.
                            if(objectEdge.accessLevel() == 3)
                                    return Objects.equals(this._weight, objectEdge._weight) &&  this._next._weight == (objectEdge.getNext().getWeight()) && this._previous.getWeight() == (objectEdge.getPrevious().getWeight());
                            return false;

                    }
                    //Just next Edge is not null.
                    else if(this._next != null && this._previous == null){
                            //Both Vertex are not null.
                            if(this._origin != null && this._destination != null){
                                    if(objectEdge.accessLevel() == 4)
                                            return Objects.equals(this._weight, objectEdge._weight) && this._origin.getValue().equals(objectEdge.getOrigin().getValue()) 
                                                    && this._destination.getValue().equals(objectEdge.getDestination().getValue()) && this._next._weight == (objectEdge.getNext().getWeight());
                                    return false;
                            }

                            //Just destination is null.
                            if(this._origin != null && this._destination == null){
                                    if(objectEdge.accessLevel() == 5)
                                            return Objects.equals(this._weight, objectEdge._weight) && this._origin.getValue().equals(objectEdge.getOrigin().getValue()) 
                                                    && this._next._weight == (objectEdge.getNext().getWeight());
                                    return false;
                            }
                            //Just origin is null.
                            if(this._origin == null && this._destination != null){
                                    if(objectEdge.accessLevel() == 6)
                                            return Objects.equals(this._weight, objectEdge._weight) && this._destination.getValue().equals(objectEdge.getDestination().getValue()) 
                                                    && this._next._weight == (objectEdge.getNext().getWeight());
                                    return false;
                            }
                            //Both Vertex are null.
                            if(objectEdge.accessLevel() == 7)
                                    return Objects.equals(this._weight, objectEdge._weight)  && this._next._weight == (objectEdge.getNext().getWeight());
                            return false;	
                    }
                    //Just previous Edge is not null.
                    else if(this._next == null && this._previous != null){
                            //Both Vertex are not null.
                            if(this._origin != null && this._destination != null){
                                    if(objectEdge.accessLevel() == 8)
                                            return Objects.equals(this._weight, objectEdge._weight) && this._origin.getValue().equals(objectEdge.getOrigin().getValue()) 
                                                    && this._destination.getValue().equals(objectEdge.getDestination().getValue()) && this._previous.getWeight() == (objectEdge.getPrevious().getWeight());
                                    return false;
                            }
                            //Just destination is null.
                            if(this._origin != null && this._destination == null){
                                    if(objectEdge.accessLevel() == 9)
                                            return Objects.equals(this._weight, objectEdge._weight) && this._origin.getValue().equals(objectEdge.getOrigin().getValue()) 
                                                    && this._previous.getWeight() == (objectEdge.getPrevious().getWeight());
                                    return false;

                            }
                            //Just origin is null.
                            if(this._origin == null && this._destination != null){
                                    if(objectEdge.accessLevel() == 10)
                                            return Objects.equals(this._weight, objectEdge._weight) && this._destination.getValue().equals(objectEdge.getDestination().getValue()) 
                                                    && this._previous.getWeight() == (objectEdge.getPrevious().getWeight());
                                    return false;
                            }
                            //Both Vertex are null.
                            if(objectEdge.accessLevel() == 11)
                                    return Objects.equals(this._weight, objectEdge._weight) && this._previous.getWeight() == (objectEdge.getPrevious().getWeight());
                            return false;

                    }
                    //Both Edges are null.
                    else{
                            //Both Vertex are not null.
                            if(this._origin != null && this._destination != null){
                                    if(objectEdge.accessLevel() == 12)
                                            return Objects.equals(this._weight, objectEdge._weight) && this._origin.getValue().equals(objectEdge.getOrigin().getValue()) 
                                                    && this._destination.getValue().equals(objectEdge.getDestination().getValue());
                                    return false;
                            }
                            //Just destination is null.
                            else if(this._origin != null && this._destination == null){
                                    if(objectEdge.accessLevel() == 13)
                                            return Objects.equals(this._weight, objectEdge._weight) && this._origin.getValue().equals(objectEdge.getOrigin().getValue());
                                    return false;
                            }
                            //Just origin is null.
                            else if(this._origin == null && this._destination != null){
                                    if(objectEdge.accessLevel() == 14)
                                            return Objects.equals(this._weight, objectEdge._weight) && this._destination.getValue().equals(objectEdge.getDestination().getValue());
                                    return false;
                            }

                            //Both Vertex are null.
                            return (objectEdge.accessLevel() == 15) && Objects.equals(this._weight, objectEdge._weight);
                    }
            }

            @Override
            public String toString(){

                    if(this._origin != null && this._destination != null)
                            return this._origin.getValue() + " - " + this._weight + " - " + this._destination.getValue();
                    else if(this._origin == null && this._destination != null)
                            return " - " + this._weight + " - " + this._destination.getValue();
                    else if(this._origin != null && this._destination == null)
                            return this._origin.getValue() + " - " + this._weight + " - ";
                    else 
                            return this._weight.toString();
            }

            public int compareTo(Edge value){
                    return (value == null)? 1 : this._weight.compareTo(value.getWeight());
            }

            @Override
            @SuppressWarnings("FinalizeDeclaration")
            public void finalize() throws Throwable {
                    super.finalize();
            }
    }

    //Private attributes

    /**The number of vertex into the graph.*/
    private long  _size;
    /**The first-front vertex pointer.*/
    private Vertex _front;
    /**The middle-medium vertex pointer.*/
    private Vertex _middle;
    /**The last-back vertex pointer.*/
    private Vertex _back;
    /**The total weight into the graph.*/
    private double _allWeight;
    /**The all edges list*/
    private Listing<Edge> _allEdges;

    /**Sets the initial values of all attributes.*/
    private void _init() {
            this._front = this._back = this._middle = null;
            this._size = 0;
            this._allWeight = 0.0;
            this._allEdges = new Listing<>();
    }

    /**Gets a pair of the vertexs who have the given elements.*/
    private Doublet<Vertex,Vertex> getVertexs(T firstElement, T secondElement){
            Vertex firstVertex;
            Vertex secondVertex;

            firstVertex = secondVertex = null;

            if(this._size == 1) {
                    if(this._front.getValue().equals(firstElement))
                            firstVertex = this._front;
                    else if(this._front.getValue().equals(secondElement))
                            secondVertex = this._front;
            }
            else if(this._size == 2) {
                    if(this._front.getValue().equals(firstElement))
                            firstVertex = this._front;
                    else if(this._front.getValue().equals(secondElement))
                            secondVertex = this._front;
                    if(this._back.getValue().equals(firstElement))
                            firstVertex = this._back;
                    else if(this._back.getValue().equals(secondElement))
                            secondVertex = this._back;
            }
            else{

                    Vertex first = this._front;
                    Vertex last = this._back;

                    double middleIndex = ((double)this._size)/2;

                    for(double i = 0; i < middleIndex;i++) {

                            if(first.getValue().equals(firstElement))
                                    firstVertex = first;
                            else if(first.getValue().equals(secondElement))
                                    secondVertex = first;

                            if(last.getValue().equals(firstElement))
                                    firstVertex = last;
                            else if(last.getValue().equals(secondElement))
                                    secondVertex = last;

                            first = first.getNext();
                            last = last.getPrevious();
                    }	

            }
            return new Doublet<>(firstVertex, secondVertex);
    }

    //Public constructors

    /**Creates a new empty graph with initial values.*/
    public Graph() { _init();}

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Graph(Graph<T> graph) {

            this._init();

            Graph<T>.Vertex middle = graph.getMiddleVertex();

            if(middle != null) {

                    this.pushBack(middle.getValue());
                    Graph<T>.Vertex previous = middle.getPrevious();
                    Graph<T>.Vertex next = middle.getNext();

                    while(previous != null || next != null) {

                            if(previous != null) {
                                    this.pushFront(previous.getValue());
                                    previous = previous.getPrevious();
                            }
                            if(next != null) {
                                    this.pushBack(next.getValue());
                                    next = next.getNext();
                            }
                    }
            }
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Graph(Listing<T> list) {

            this._init();

           Nodet<T> middle = list.getMiddleNodet();

            if(middle != null) {

                    this.pushBack(middle.getValue());
                   Nodet<T> previous = middle.getPrevious();
                   Nodet<T> next = middle.getNext();

                    while(previous != null || next != null) {

                            if(previous != null) {
                                    this.pushFront(previous.getValue());
                                    previous = previous.getPrevious();
                            }
                            if(next != null) {
                                    this.pushBack(next.getValue());
                                    next = next.getNext();
                            }
                    }
            }
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Graph(Structure<T> structure) {

            this._init();

           Nodet<T> middle = structure.toListing().getMiddleNodet();

            if(middle != null) {

                    this.pushBack(middle.getValue());
                   Nodet<T> previous = middle.getPrevious();
                   Nodet<T> next = middle.getNext();

                    while(previous != null || next != null) {

                            if(previous != null) {
                                    this.pushFront(previous.getValue());
                                    previous = previous.getPrevious();
                            }
                            if(next != null) {
                                    this.pushBack(next.getValue());
                                    next = next.getNext();
                            }
                    }
            }
    }

    public boolean isEmpty() {return this._front == null;}

    public long getSize() {return this._size;}

    public double getTotalWeight() {return this._allWeight;}

    public long getNumberOfEdges() {return this._allEdges.getSize();}

    public Listing<Edge> getEdges(){return _allEdges;}

    public boolean existDestination(Vertex vertex) {

            Nodet<Edge> firstDestinationNode = this._allEdges.getFrontNodet();
            Nodet<Edge> lastDestinationNode = this._allEdges.getBackNodet();
            double middleIndex = ((double)this._size)/2;

            for(double i = 0; i < middleIndex;i++) {

                    if(firstDestinationNode.getValue().getDestination().getValue().equals(vertex.getValue()))
                            return true;

                    if(lastDestinationNode.getValue().getDestination().getValue().equals(vertex.getValue()))
                            return true;

                    firstDestinationNode = firstDestinationNode.getNext();
                    lastDestinationNode = lastDestinationNode.getPrevious();
            }

            return false;
    }

    public boolean xxistOrigin(Vertex vertex) {

            Nodet<Edge> firstOriginNode = this._allEdges.getFrontNodet();
            Nodet<Edge> lastOriginNode = this._allEdges.getBackNodet();
            double middleIndex = ((double)this._size)/2;

            for(double i = 0; i < middleIndex;i++) {

                    if(firstOriginNode.getValue().getOrigin().getValue().equals(vertex.getValue()))
                            return true;

                    if(lastOriginNode.getValue().getOrigin().getValue().equals(vertex.getValue()))
                            return true;

                    firstOriginNode = firstOriginNode.getNext();
                    lastOriginNode = lastOriginNode.getPrevious();
            }

            return false;
    }

    public boolean existEdge(Vertex origin, Vertex destination) { return existEdge(origin.getValue(),destination.getValue());}

    public boolean existEdge(T origin, T destination) {

            Nodet<Edge> firstNode = this._allEdges.getFrontNodet();
            Nodet<Edge> lastNode = this._allEdges.getBackNodet();

            double middleIndex = ((double)this._size)/2;

            for(double i = 0; i < middleIndex;i++) {

                    if(firstNode.getValue().getDestination().getValue().equals(destination)
                                    && firstNode.getValue().getOrigin().getValue().equals(origin))
                            return true;

                    if(lastNode.getValue().getDestination().getValue().equals(destination)
                                    && lastNode.getValue().getOrigin().getValue().equals(origin))
                            return true;

                    firstNode = firstNode.getNext();
                    lastNode = lastNode.getPrevious();
            }

            return false;
    }


    public Edge getEdge(T origin, T destination) {

            Nodet<Edge> firstNode = this._allEdges.getFrontNodet();
            Nodet<Edge> lastNode = this._allEdges.getBackNodet();

            double middleIndex = ((double)this._size)/2;

            for(double i = 0; i < middleIndex;i++) {

                    if(firstNode.getValue().getDestination().getValue().equals(destination)
                                    && firstNode.getValue().getOrigin().getValue().equals(origin))
                            return firstNode.getValue();

                    if(lastNode.getValue().getDestination().getValue().equals(destination)
                                    && lastNode.getValue().getOrigin().getValue().equals(origin))
                            return lastNode.getValue();

                    firstNode = firstNode.getNext();
                    lastNode = lastNode.getPrevious();
            }

            return null;
    }

    public Doublet<Edge,Edge> getEdges(T origin, T destination) {

            Edge firstEdge = null;
            Edge secondEdge = null;

            Nodet<Edge> firstNode = this._allEdges.getFrontNodet();
            Nodet<Edge> lastNode = this._allEdges.getBackNodet();

            double middleIndex = ((double)this._size)/2;

            for(double i = 0; i < middleIndex;i++) {

                    if(firstNode.getValue().getDestination().getValue().equals(destination)
                                    && firstNode.getValue().getOrigin().getValue().equals(origin))
                            firstEdge = firstNode.getValue();

                    if(lastNode.getValue().getDestination().getValue().equals(destination)
                                    && lastNode.getValue().getOrigin().getValue().equals(origin))
                            firstEdge = lastNode.getValue();

                    if(firstNode.getValue().getDestination().getValue().equals(origin)
                                    && firstNode.getValue().getOrigin().getValue().equals(destination))
                            secondEdge = firstNode.getValue();

                    if(lastNode.getValue().getDestination().getValue().equals(origin)
                                    && lastNode.getValue().getOrigin().getValue().equals(destination))
                            secondEdge = lastNode.getValue();

                    firstNode = firstNode.getNext();
                    lastNode = lastNode.getPrevious();
            }

            return new Doublet<>(firstEdge,secondEdge);
    }

    public Doublet<Boolean,Boolean> existEdges(T origin, T destination){

            Boolean existFirstEdge = false;
            Boolean existSecondEdge = false;

            Nodet<Edge> firstNode = this._allEdges.getFrontNodet();
            Nodet<Edge> lastNode = this._allEdges.getBackNodet();

            double middleIndex = ((double)this._size)/2;

            for(double i = 0; i < middleIndex;i++) {


                    if(firstNode.getValue().getDestination().getValue().equals(destination)
                                    && firstNode.getValue().getOrigin().getValue().equals(origin))
                            existFirstEdge = true;


                    if(lastNode.getValue().getDestination().getValue().equals(destination)
                                    && lastNode.getValue().getOrigin().getValue().equals(origin))
                            existFirstEdge = true;

                    if(firstNode.getValue().getDestination().getValue().equals(origin)
                                    && firstNode.getValue().getOrigin().getValue().equals(destination))
                            existSecondEdge = true;


                    if(lastNode.getValue().getDestination().getValue().equals(origin)
                                    && lastNode.getValue().getOrigin().getValue().equals(destination))
                            existSecondEdge = true;

                    if(existFirstEdge && existSecondEdge)
                            break;

                    firstNode = firstNode.getNext();
                    lastNode = lastNode.getPrevious();
            }

            return new Doublet<>(existFirstEdge, existSecondEdge);
    }

    public boolean isAddresed(){

            Nodet<Edge> firstOriginNode = this._allEdges.getFrontNodet();
            Nodet<Edge> lastOriginNode = this._allEdges.getBackNodet();

            double middleIndex = ((double)this._allEdges.getSize())/2;

            for(double i = 0; i < middleIndex;i++) {

                    if(existDestination(firstOriginNode.getValue().getOrigin()))
                            return true;

                    if(existDestination(lastOriginNode.getValue().getOrigin()))
                            return true;

                    firstOriginNode = firstOriginNode.getNext();
                    lastOriginNode = lastOriginNode.getPrevious();
            }
            return false;
    }

    public T getFirstElement() { 
            if(this._front != null)
                    return this._front.getValue(); 
            else
                    throw new NullPointerException("Error: The Value Is Null. Impossible To Return It.");
    }

    public T getLastElement() { 
            if(this._back != null)
                    return this._back.getValue(); 
            else
                    throw new NullPointerException("Error: The Value Is Null. Impossible To Return It.");
    }

    public T getMediumElement() { 
            if(this._middle != null)
                    return this._middle.getValue(); 
            else
                    throw new NullPointerException("Error: The Value Is Null. Impossible To Return It.");
    }

    public Vertex getFrontVertex() { return this._front; }
    
    public Vertex getBackVertex() { return this._back; }
	
    public Vertex getMiddleVertex() {return this._middle;}

    public boolean containsElement(T element) {
            
            if(this._size == 0)
                    return false;
            else if(this._size == 1)
                            return this._front.getValue().equals(element);
            else if(this._size == 2)
                            return this._front.getValue().equals(element) || this._back.getValue().equals(element);
            else{

                    Vertex first = this._front;
                    Vertex last = this._back;

                    double middleIndex = ((double)this._size)/2;

                    for(double i = 0; i < middleIndex;i++) {

                            if(first.getValue().equals(element))
                                    return true;

                            if(last.getValue().equals(element))
                                    return true;

                            first = first.getNext();
                            last = last.getPrevious();
                    }

                    return false;
            }
            
    }

    public void pushBack(T value){
            if(this.isEmpty()){
                    //creates a new Vertex.
                    Vertex newVertex = new Vertex(value);
                    this._front = this._back = this._middle = newVertex;
                    this._size++;
            }
            else{
                    if(!containsElement(value)) {
                            Vertex newVertex = new Vertex(value,this._back,null);
                            this._back.setNext(newVertex);
                            this._back = newVertex;
                            ++_size;
                            if(this._size % 2 != 0)
                                    this._middle = this._middle.getNext();
                    }
            }
    }

    public void pushFront(T value){
            if(this.isEmpty()){
                    //creates a new Vertex.
                    Vertex newVertex = new Vertex(value);
                    this._front = this._back = this._middle = newVertex;
                    this._size++;
            }
            else{
                    if(!containsElement(value)) {
                            Vertex newVertex = new Vertex(value,null,this._front);
                            this._front.setPrevious(newVertex);
                            this._front = newVertex;
                            ++_size;
                            if(this._size % 2 == 0)
                                    this._middle = this._middle.getPrevious();
                    }
            }
    }

    public T getElement(long index) {

            if(!this.isEmpty()){

                    long realIndex = index % this._size;

                    if(realIndex == 0) 
                            return this._front.getValue();

                    else if(realIndex == this._size-1) 
                            return this._back.getValue();

                    else { 
                            long middleIndex = this._size/2;

                            if(this._size % 2 != 0) { 
                                    long differenceBetweenFront = Math.abs(realIndex-1);
                                    long differenceBetweenBack = Math.abs(realIndex-(this._size-2));
                                    long differenceBetweenMiddle = Math.abs(realIndex-middleIndex);

                                    if( (differenceBetweenFront < differenceBetweenBack 
                                                    && differenceBetweenFront < differenceBetweenMiddle ) 
                                                    || differenceBetweenFront == differenceBetweenMiddle) {

                                            Vertex auxiliar = this._front.getNext();

                                            for(long i = 1; i < realIndex;i++)
                                                    auxiliar = auxiliar.getNext();

                                            return auxiliar.getValue();

                                    }
                                    else if(differenceBetweenMiddle < differenceBetweenBack 
                                                    && differenceBetweenMiddle < differenceBetweenFront) {

                                            if(realIndex > middleIndex) {
                                                    Vertex auxiliar = this._middle.getNext();

                                                    for(long i = middleIndex+1; i < realIndex;i++)
                                                            auxiliar = auxiliar.getNext();

                                                    return auxiliar.getValue();
                                            }
                                            else if(realIndex == middleIndex)
                                                    return this._middle.getValue();

                                            else {
                                                    Vertex auxiliar = this._middle.getPrevious();

                                                    for(long i = middleIndex-1; i > realIndex;i--)
                                                            auxiliar = auxiliar.getPrevious();

                                                    return auxiliar.getValue();
                                            }
                                    }
                                    else{

                                            Vertex auxiliar = this._back.getPrevious();

                                            for(long i = this._size-2; i > realIndex;i--)
                                                    auxiliar = auxiliar.getPrevious();

                                            return auxiliar.getValue();
                                    }

                            }
                            else { 
                                    long differenceBetweenFront = Math.abs(realIndex-1);
                                    long differenceBetweenBack = Math.abs(realIndex-(this._size-2));
                                    long differenceBetweenMiddle = Math.abs(realIndex-(middleIndex-1));

                                    if( (differenceBetweenFront < differenceBetweenBack 
                                                    && differenceBetweenFront < differenceBetweenMiddle ) 
                                                    || differenceBetweenFront == differenceBetweenMiddle) {

                                            Vertex auxiliar = this._front.getNext();

                                            for(long i = 1; i < realIndex;i++)
                                                    auxiliar = auxiliar.getNext();

                                            return auxiliar.getValue();

                                    }

                                    else if(differenceBetweenMiddle < differenceBetweenBack 
                                                    && differenceBetweenMiddle < differenceBetweenFront) {

                                            if(realIndex > (middleIndex-1)) {

                                                    Vertex auxiliar = this._middle.getNext();

                                                    for(long i = middleIndex; i < realIndex;i++)
                                                            auxiliar = auxiliar.getNext();

                                                    return auxiliar.getValue();
                                            }
                                            else if(realIndex == (middleIndex-1))
                                                    return this._middle.getValue();

                                            else {
                                                    Vertex auxiliar = this._middle.getPrevious();

                                                    for(long i = middleIndex-2; i > realIndex;i--)
                                                            auxiliar = auxiliar.getPrevious();

                                                    return auxiliar.getValue();
                                            }

                                    }
                                    else{

                                            Vertex auxiliar = this._back.getPrevious();

                                            for(long i = this._size-2; i > realIndex;i--)
                                                    auxiliar = auxiliar.getPrevious();

                                            return auxiliar.getValue();
                                    }
                            }

                    }
            }

            else
                    throw new NullPointerException("Error: The Graph Is isEmpty.");
    }

    public Vertex getVertex(long index) {

            if(!this.isEmpty()){

                    long realIndex = index % this._size;

                    if(realIndex == 0) 
                            return this._front;

                    else if(realIndex == this._size-1) 
                            return this._back;

                    else { 
                            long middleIndex = this._size/2;

                            if(this._size % 2 != 0) { 
                                    long differenceBetweenFront = Math.abs(realIndex-1);
                                    long differenceBetweenBack = Math.abs(realIndex-(this._size-2));
                                    long differenceBetweenMiddle = Math.abs(realIndex-middleIndex);

                                    if( (differenceBetweenFront < differenceBetweenBack 
                                                    && differenceBetweenFront < differenceBetweenMiddle ) 
                                                    || differenceBetweenFront == differenceBetweenMiddle) {

                                            Vertex auxiliar = this._front.getNext();

                                            for(long i = 1; i < realIndex;i++)
                                                    auxiliar = auxiliar.getNext();

                                            return auxiliar;

                                    }
                                    else if(differenceBetweenMiddle < differenceBetweenBack 
                                                    && differenceBetweenMiddle < differenceBetweenFront) {

                                            if(realIndex > middleIndex) {
                                                    Vertex auxiliar = this._middle.getNext();

                                                    for(long i = middleIndex+1; i < realIndex;i++)
                                                            auxiliar = auxiliar.getNext();

                                                    return auxiliar;
                                            }
                                            else if(realIndex == middleIndex)
                                                    return this._middle;

                                            else {
                                                    Vertex auxiliar = this._middle.getPrevious();

                                                    for(long i = middleIndex-1; i > realIndex;i--)
                                                            auxiliar = auxiliar.getPrevious();

                                                    return auxiliar;
                                            }
                                    }
                                    else{

                                            Vertex auxiliar = this._back.getPrevious();

                                            for(long i = this._size-2; i > realIndex;i--)
                                                    auxiliar = auxiliar.getPrevious();

                                            return auxiliar;
                                    }

                            }
                            else { 
                                    long differenceBetweenFront = Math.abs(realIndex-1);
                                    long differenceBetweenBack = Math.abs(realIndex-(this._size-2));
                                    long differenceBetweenMiddle = Math.abs(realIndex-(middleIndex-1));

                                    if( (differenceBetweenFront < differenceBetweenBack 
                                                    && differenceBetweenFront < differenceBetweenMiddle ) 
                                                    || differenceBetweenFront == differenceBetweenMiddle) {

                                            Vertex auxiliar = this._front.getNext();

                                            for(long i = 1; i < realIndex;i++)
                                                    auxiliar = auxiliar.getNext();

                                            return auxiliar;

                                    }

                                    else if(differenceBetweenMiddle < differenceBetweenBack 
                                                    && differenceBetweenMiddle < differenceBetweenFront) {

                                            if(realIndex > (middleIndex-1)) {

                                                    Vertex auxiliar = this._middle.getNext();

                                                    for(long i = middleIndex; i < realIndex;i++)
                                                            auxiliar = auxiliar.getNext();

                                                    return auxiliar;
                                            }
                                            else if(realIndex == (middleIndex-1))
                                                    return this._middle;

                                            else {
                                                    Vertex auxiliar = this._middle.getPrevious();

                                                    for(long i = middleIndex-2; i > realIndex;i--)
                                                            auxiliar = auxiliar.getPrevious();

                                                    return auxiliar;
                                            }

                                    }
                                    else{

                                            Vertex auxiliar = this._back.getPrevious();

                                            for(long i = this._size-2; i > realIndex;i--)
                                                    auxiliar = auxiliar.getPrevious();

                                            return auxiliar;
                                    }
                            }

                    }
            }

            else
                    throw new NullPointerException("Error: The Graph Is isEmpty.");
    }

    
    public long getPositionAt(T element){
            long frontCounter = 0;
            long backCounter = this._size-1;
            Vertex first = this._front;
            Vertex last = this._back;

            double middleIndex = ((double)this._size)/2;

            for(double i = 0; i < middleIndex;i++) {

                    if(first.equals(element))
                            return frontCounter;

                    if(last.equals(element))
                            return backCounter;

                    frontCounter++;
                    backCounter--;

                    first = first.getNext();
                    last = last.getPrevious();
            }
            return -1;
    }

    public long getPositionAt(Vertex vertex) {return getPositionAt(vertex.getValue());}

    public Vertex getVertex(T element) {
            
          if(this._size == 0)
                    return null;
          else if(this._size == 1)
                    return (this._front.getValue().equals(element))? this._front : null;
          else if(this._size == 0){

                    if(this._front.getValue().equals(element))
                            return this._front;
                    if(this._back.getValue().equals(element))
                            return this._back;

                    return null;
          }
          else{
                            Vertex first = this._front;
                            Vertex last = this._back;
                            double middleIndex = ((double)this._size)/2;

                            for(double i = 0; i < middleIndex;i++) {
                                    if(first.getValue().equals(element))
                                            return first;
                                    if(last.getValue().equals(element))
                                            return last;
                                    first = first.getNext();
                                    last = last.getPrevious();
                            }

                            return null;
                }
            
    }

    public void popFront() {
            if(!this.isEmpty()){

                    Vertex erasedVertex = this._front;

                    Vertex first = this._front;
                    Vertex last = this._back;

                    double middleIndex = ((double)this._size)/2;

                    for(double i = 0; i < middleIndex;i++) {

                            first.remove(erasedVertex.getValue());
                            last.remove(erasedVertex.getValue());

                            first = first.getNext();
                            last = last.getPrevious();

                    }

                    if(this._size % 2 == 0) 
                            this._middle = this._middle.getNext();
                    this._front = this._front.getNext();

                if(this._front != null)
                            this._front.setPrevious(null);

                    --this._size;

                    erasedVertex = null;
            }
    }

    public void popBack() {
            if(!this.isEmpty()){

                    Vertex erasedVertex = this._back;

                    Vertex first = this._front;
                    Vertex last = this._back;

                    double middleIndex = ((double)this._size)/2;

                    for(double i = 0; i < middleIndex;i++) {

                            first.remove(erasedVertex.getValue());
                            last.remove(erasedVertex.getValue());

                            first = first.getNext();
                            last = last.getPrevious();

                    }

                    if(this._size % 2 != 0) 
                            this._middle = this._middle.getPrevious();

                    this._back = this._back.getPrevious();

                if(this._back != null)
                            this._back.setNext(null);

                    --this._size;
                    erasedVertex = null;
            }

    }

    public void clear() {
            if(!this.isEmpty()){
                    while(!this.isEmpty()) {
                            popFront();
                            popBack();
                    }
                    this._size = 0;
                    this._back = this._middle  = this._front = null;
            }
    }

    public void eraseAt(long position) {
            if(!this.isEmpty()){

                    long realPosition = position % this._size;

                    if(realPosition == 0) 
                            popFront();

                    else if(realPosition == (this._size -1) )
                            popBack();

                    else{

                            Vertex previousVertex = getVertex(position-1);
                            Vertex erasedVertex = previousVertex.getNext();
                            Vertex first = this._front;
                            Vertex last = this._back;

                            double middleIndex = ((double)this._size)/2;

                            for(double i = 0; i < middleIndex;i++) {

                                    first.remove(erasedVertex.getValue());
                                    last.remove(erasedVertex.getValue());

                                    first = first.getNext();
                                    last = last.getPrevious();

                            }

                            boolean isMiddle = erasedVertex.equals(this._middle);
                            Vertex nextVertex = erasedVertex.getNext();

                            previousVertex.setNext(nextVertex);
                            nextVertex.setPrevious(previousVertex);
                            erasedVertex = null;

                    this._size--;

                    if(!isMiddle) {
                            if(this._size % 2 != 0)
                                            this._middle = this._middle.getNext();
                    }
                    else 
                            this._middle = (this._size % 2 == 0) ? previousVertex : nextVertex;
                    }
            }

    }

    public void remove(T element)  {
            if(!this.isEmpty()) {

                    if(this._front.getValue().equals(element))
                            popFront();

                    else if(this._back.getValue().equals(element))
                            popBack();

                    else {

                            Vertex erasedVertex = getVertex(element);
                            if(erasedVertex != null) {

                                    Vertex first = this._front;
                                    Vertex last = this._back;

                                    double middleIndex = ((double)this._size)/2;

                                    for(double i = 0; i < middleIndex;i++) {

                                            first.remove(erasedVertex.getValue());
                                            last.remove(erasedVertex.getValue());

                                            first = first.getNext();
                                            last = last.getPrevious();

                                    }

                                    Vertex previousVertex = erasedVertex.getPrevious();
                                    boolean isMiddle = erasedVertex.equals(this._middle);
                                    Vertex nextVertex = erasedVertex.getNext();

                                    previousVertex.setNext(nextVertex);
                                    nextVertex.setPrevious(previousVertex);
                                    erasedVertex = null;

                            this._size--;

                            if(!isMiddle) {
                                    if(this._size % 2 != 0)
                                                    this._middle = this._middle.getNext();
                            }
                            else 
                                    this._middle = (this._size % 2 == 0) ? previousVertex : nextVertex;
                            }
                    }
            }
    }

    public void addEdgeToBack(T origin, double weight, T destination) {

            Doublet<Vertex,Vertex> vertexPair = getVertexs(origin,destination);

            if(vertexPair.getFirstElement()!= null && vertexPair.getSecondElement() != null)
                    vertexPair.getFirstElement().pushBack(weight, vertexPair.getSecondElement());

            vertexPair = null;
    }

    public void addEdgeToBack(Vertex origin, double weight, Vertex destination) {

            if(origin != null && destination != null) 
                    origin.pushBack(weight, destination);
    }

    public void addEdgeToFront(T origin, double weight, T destination) {

            Doublet<Vertex,Vertex> vertexPair = getVertexs(origin,destination);

            if(vertexPair.getFirstElement() != null && vertexPair.getSecondElement() != null)
                    vertexPair.getFirstElement().pushFront(weight, vertexPair.getSecondElement());

            vertexPair = null;
    }


    public void addEdgeToFront(Vertex origin, double weight, Vertex destination) {

            if(origin != null && destination != null)
                    origin.pushFront(weight, destination);
    }

    public void addEdgesToBack(T origin, double weight, T destination) {

            Doublet<Vertex,Vertex> vertexPair = getVertexs(origin,destination);

            if(vertexPair.getFirstElement() != null && vertexPair.getSecondElement() != null) {
                    vertexPair.getFirstElement().pushBack(weight,vertexPair.getSecondElement());
                    vertexPair.getSecondElement().pushBack(weight,vertexPair.getFirstElement());
            }

            vertexPair = null; 
    }

    public void addEdgesToBack(Vertex origin, double weight, Vertex destination) {

            if(origin != null && destination != null) {
                    origin.pushBack(weight,destination);
                    destination.pushBack(weight,origin);
            }
    }

    public void addEdgesToFront(T origin, double weight, T destination) {

            Doublet<Vertex,Vertex> vertexPair = getVertexs(origin,destination);

            if(vertexPair.getFirstElement() != null && vertexPair.getSecondElement() != null) {
                    vertexPair.getFirstElement().pushFront(weight,vertexPair.getSecondElement());
                    vertexPair.getSecondElement().pushFront(weight,vertexPair.getFirstElement());
            }
            vertexPair = null;
    }

    public void addEdgesToFront(Vertex origin, double weight, Vertex destination) {

            if(origin != null && destination != null) {
                    origin.pushFront(weight,destination);
                    destination.pushFront(weight,origin);
            }
    }

    public void deleteEdge(T origin, T destination)  {
            Edge edge = getEdge(origin,destination);

            if(edge != null) {
                    Vertex originVertex = edge.getOrigin();
                    originVertex.remove(edge);
            }
    }

    public void deleteEdges(T origin, T destination)  {
            Doublet<Edge,Edge> edges = getEdges(origin,destination);


            if(edges.getFirstElement() != null) {
                    Vertex originVertex = edges.getFirstElement().getOrigin();
                    originVertex.remove(edges.getFirstElement());
            }

            if(edges.getSecondElement()!= null) {
                    Vertex originVertex = edges.getSecondElement().getOrigin();
                    originVertex.remove(edges.getSecondElement());
            }
            edges = null;
    }

    public void updateEdge(T origin, double newWeight ,T destination) {
            Edge edge = getEdge(origin,destination);

            if(edge != null) {
                    edge.getOrigin()._totalWeight -= edge.getWeight();
                    this._allWeight -= edge.getWeight();
                    edge.setWeight(newWeight);
                    edge.getOrigin()._totalWeight += newWeight;
                    this._allWeight += newWeight;
            }
    }

    public void updateEdges(T origin, double newWeight,T destination) {
            Doublet<Edge,Edge> edges = getEdges(origin,destination);

            if(edges.getFirstElement() != null) {
                    edges.getFirstElement().getOrigin()._totalWeight -= edges.getFirstElement().getWeight();
                    this._allWeight -= edges.getFirstElement().getWeight();
                    edges.getFirstElement().setWeight(newWeight);
                    edges.getFirstElement().getOrigin()._totalWeight += newWeight;
                    this._allWeight += newWeight;
            }

            if(edges.getSecondElement()!= null) {
                    edges.getSecondElement().getOrigin()._totalWeight -= edges.getSecondElement().getWeight();
                    this._allWeight -= edges.getSecondElement().getWeight();
                    edges.getSecondElement().setWeight(newWeight);
                    edges.getSecondElement().getOrigin()._totalWeight += newWeight;
                    this._allWeight += newWeight;
            }

            edges = null;
    }


    //Override methods
    @Override
    public String toString(){
            String newString = new String();
            Vertex auxiliar = this._front;
            while(auxiliar != null){
                    newString += "{"+auxiliar.getValue()+"} -> " + auxiliar+"\n";
                    auxiliar = auxiliar.getNext();
            }
            return newString;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (int) (this._size ^ (this._size >>> 32));
        hash = 89 * hash + Objects.hashCode(this._front);
        hash = 89 * hash + Objects.hashCode(this._back);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this._allWeight) ^ (Double.doubleToLongBits(this._allWeight) >>> 32));
        return hash;
    }
    
    

    @Override 
    public boolean equals(Object object) {
            if (object == this) 
            return true; 

        if (!(object instanceof Graph))
            return false; 

                @SuppressWarnings("unchecked")
                Graph<T> objectGraph = (Graph<T>) object; 

        if(this._size == objectGraph._size) {

                Vertex thisFront = this._front;
                Vertex objectFront = objectGraph._front;

                while(thisFront != null) {

                        if(!thisFront.getValue().equals(objectFront.getValue()))
                                return false;

                        thisFront = thisFront.getNext();
                        objectFront = objectFront.getNext();
                }

                return true;
        }

        return false;
    }

    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize() throws Throwable  {
          super.finalize();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
