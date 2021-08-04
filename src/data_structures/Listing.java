/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

public class Listing<T>{
    
    private Nodet<T> _front;
    private Nodet<T> _back;
    private Nodet<T> _middle;
    private long _size;
    @SuppressWarnings("FieldMayBeFinal")
    private Iterating<T> _frontIterator;
    @SuppressWarnings("FieldMayBeFinal")
    private Iterating<T> _backIterator;

    //Private methods
    
    private Nodet<T> getNode(T element) throws NullPointerException{
         
            if(_size == 0)
                    return null;
            else if(_size == 1)
                    return (this._front.getValue().equals(element))? this._front : null;
            else if(_size == 2){

                    if(this._front.getValue().equals(element))
                            return this._front;
                    if(this._back.getValue().equals(element))
                            return this._back;

                    return null;
            }
           else{
                    Nodet<T> first = this._front;
                    Nodet<T> last = this._back;
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

    private Doublet<Nodet<T>,Nodet<T>> getNodes(T firstElement, T secondElement){
            Nodet<T> firstNode = null;
            Nodet<T> secondNode = null;
            if(this._size == 1) {
                    if(this._front.getValue().equals(firstElement))
                            firstNode = this._front;
                    else if(this._front.getValue().equals(secondElement))
                            secondNode = this._front;
            }
            else if(this._size == 2) {
                    if(this._front.getValue().equals(firstElement))
                            firstNode = this._front;
                    if(this._front.getValue().equals(secondElement))
                            secondNode = this._front;
                    if(this._back.getValue().equals(firstElement))
                            firstNode = this._back;
                    if(this._back.getValue().equals(secondElement))
                            secondNode = this._back;
            }
            else{
                    Nodet<T> first = this._front;
                    Nodet<T> last = this._back;
                    double middleIndex = ((double)this._size)/2;
                    for(double i = 0; i < middleIndex;i++) {
                            if(first.getValue().equals(firstElement))
                                    firstNode = first;
                            else if(first.getValue().equals(secondElement))
                                    secondNode = first;
                            if(last.getValue().equals(firstElement))
                                    firstNode = last;
                            else if(last.getValue().equals(secondElement))
                                    secondNode = last;
                            first = first.getNext();
                            last = last.getPrevious();
                    }
                    return null;
            }
            return new Doublet<>(firstNode, secondNode);
    }

    private Nodet<T> getNode(long index) throws NullPointerException{
            if(!isEmpty()){
                    long realIndex = index % _size;
                    if(realIndex == 0) 
                            return this._front;
                    else if(realIndex == _size-1) 
                            return this._back;
                    else { 
                            long middleIndex = _size/2;
                            if(this._size % 2 != 0) { 
                                    long differenceBetweenFront = Math.abs(realIndex-1);
                                    long differenceBetweenBack = Math.abs(realIndex-(_size-2));
                                    long differenceBetweenMiddle = Math.abs(realIndex-middleIndex);

                                    if( (differenceBetweenFront < differenceBetweenBack 
                                                    && differenceBetweenFront < differenceBetweenMiddle) 
                                                    || differenceBetweenFront == differenceBetweenMiddle) {
                                            Nodet<T> auxiliar = this._front.getNext();
                                            for(long i = 1L; i < realIndex;i++)
                                                    auxiliar = auxiliar.getNext();
                                            return auxiliar;
                                    }

                                    if(differenceBetweenMiddle < differenceBetweenBack 
                                                    && differenceBetweenMiddle < differenceBetweenFront) {	
                                            if(realIndex > middleIndex) {
                                                    Nodet<T> auxiliar = this._middle.getNext();
                                                    for(long i = middleIndex+1; i < realIndex;i++)
                                                            auxiliar = auxiliar.getNext();
                                                    return auxiliar;
                                            }
                                            else if(realIndex ==  middleIndex)
                                                    return this._middle;
                                            else {
                                                    Nodet<T> auxiliar = this._middle.getPrevious();
                                                    for(long i = middleIndex-1; i > realIndex;i--)
                                                            auxiliar = auxiliar.getPrevious();
                                                    return auxiliar;
                                            }
                                    }

                                    if((differenceBetweenBack < differenceBetweenMiddle 
                                                    && differenceBetweenBack < differenceBetweenFront) 
                                                    || differenceBetweenBack == differenceBetweenMiddle) {

                                            Nodet<T> auxiliar = this._back.getPrevious();

                                            for(long i =_size-2; i > realIndex;i--)
                                                    auxiliar = auxiliar.getPrevious();

                                            return auxiliar;
                                    }

                            }
                            else { 
                                    long differenceBetweenFront = Math.abs(realIndex-1);
                                    long differenceBetweenBack = Math.abs(realIndex-(_size-2));
                                    long differenceBetweenMiddle = Math.abs(realIndex-(middleIndex-1));

                                    if( (differenceBetweenFront < differenceBetweenBack 
                                                    && differenceBetweenFront < differenceBetweenMiddle ) 
                                                    ||differenceBetweenFront == differenceBetweenMiddle) {

                                            Nodet<T> auxiliar = this._front.getNext();

                                            for(long i = 1L; i < realIndex;i++)
                                                    auxiliar = auxiliar.getNext();

                                            return auxiliar;
                                    }

                                    if(differenceBetweenMiddle < differenceBetweenBack 
                                                    && differenceBetweenMiddle < differenceBetweenFront) {

                                            if(realIndex > (middleIndex-1)) {

                                                    Nodet<T> auxiliar = this._middle.getNext();

                                                    for(long i = middleIndex; i < realIndex;i++)
                                                            auxiliar = auxiliar.getNext();

                                                    return auxiliar;
                                            }
                                            else if(realIndex == (middleIndex-1))
                                                    return this._middle;
                                            else {
                                                    Nodet<T> auxiliar = this._middle.getPrevious();

                                                    for(long i = middleIndex-2; i > realIndex;i--)
                                                            auxiliar = auxiliar.getPrevious();

                                                    return auxiliar;
                                            }
                                    }

                                    if((differenceBetweenBack < differenceBetweenMiddle 
                                                    && differenceBetweenBack < differenceBetweenFront) 
                                                    ||differenceBetweenBack == differenceBetweenMiddle ) {

                                            Nodet<T> auxiliar = this._back.getPrevious();

                                            for(long i = _size-2; i > realIndex;i--)
                                                    auxiliar = auxiliar.getPrevious();

                                            return auxiliar;
                                    }
                            }
                    }
            }

            return null;
    }
    
    private void updateFrontIterator(){
        this._frontIterator.setNodet(_front);
    }
    
    private void updateBackIterator(){
        this._backIterator.setNodet(_back);
    }

    //Public constructors

    public Listing() { 
        _size = 0;
        this._frontIterator = new Iterating<>();
        this._backIterator = new Iterating<>();
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Listing(T[] array) {
       _size = 0;
        this._frontIterator = new Iterating<>();
        this._backIterator = new Iterating<>();
        for(T value:array)
                this.pushBack(value);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Listing(Structure<T> structure) {
            _size = 0;
            this._frontIterator = new Iterating<>();
            this._backIterator = new Iterating<>();
            Nodet<T> middle = structure.toListing().getMiddleNodet();

            if(middle != null) {

                    pushBack(middle.getValue());
                    Nodet<T> previous = middle.getPrevious();
                    Nodet<T> next = middle.getNext();

                    while(previous != null || next != null) {

                            if(previous != null) {
                                    pushFront(previous.getValue());
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
    public Listing(Listing<T> list) {
        _size = 0;
        this._frontIterator = new Iterating<>();
        this._backIterator = new Iterating<>();

            Nodet<T> middle = list.getMiddleNodet();

            if(middle != null) {

                    this.pushBack(middle.getValue());
                    Nodet<T> previous = middle.getPrevious();
                    Nodet<T> next = middle.getNext();

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

    /*
  
    public List(Matrix<T>.Row row) {

           _size = 0;
            this._frontIterator = new Iterating<>();
            this._backIterator = new Iterating<>();

            Matrix<T>.Column middle = row.Middle();

            if(middle != null) {

                    this.pushBack(middle.getValue());
                    Matrix<T>.Column previous = middle.getPrevious();
                    Matrix<T>.Column next = middle.getNext();

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
    */

    /*
    public List(Graph<T> graph) {

            _size = 0;
            this._frontIterator = new Iterating<>();
            this._backIterator = new Iterating<>();

            Graph<T>.Vertex middle = graph.Middle();

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
    
    */

    //Public Methods

    public boolean isEmpty() {return this._front == null;}

    public long getSize() {return this._size;}

    public T getFirstElement() throws NullPointerException { return this._front.getValue(); }

    public T getLastElement() throws NullPointerException {     return this._back.getValue(); }

    public T getMediumElement() throws NullPointerException {   return this._middle.getValue();  }

    public Nodet<T> getFrontNodet() throws NullPointerException{ return this._front; }

    public Nodet<T> getBackNodet() throws NullPointerException { return this._back; }

    public Nodet<T> getMiddleNodet() throws NullPointerException { return this._middle; }

    public T dequeue() throws NullPointerException{
            if(!isEmpty()) {
                    T auxiliar = this._front.getValue();
                    popFront();
                    return auxiliar;
            }
           return null;
    }

    public T unstack() throws NullPointerException{
            if(!isEmpty()) {
                    T auxiliar = this._back.getValue();
                    popBack();
                    return auxiliar;
            }
            return null;
    }
    
    public Iterating<T> getFrontIterator() throws NullPointerException{ return this._frontIterator; }
    
    public Iterating<T> getBackIterator() throws NullPointerException { return this._backIterator; }

    public void pushBack(T element) {
            ++this._size;
            if(isEmpty()) {
                    Nodet<T> node = new Nodet<>(element);
                    this._front = this._back = this._middle = node;
            }
            else {
                    Nodet<T> node = new Nodet<>(this._back,element,null);
                    this._back.setNext(node);
                    this._back = node;
                    if(this._size % 2 != 0)
                            this._middle = this._middle.getNext();
            }
            updateFrontIterator();
            updateBackIterator();
    }

    public void pushFront(T element) {
            ++this._size;
            if(isEmpty()) {
                    Nodet<T> node = new Nodet<>(element);
                    this._front = this._back = this._middle = node;
            }
            else {
                    Nodet<T> node = new Nodet<>(null,element,this._front);
                    this._front.setPrevious(node);
                    this._front = node;
                    if(this._size % 2 == 0)
                            this._middle = this._middle.getPrevious();
            }
             updateFrontIterator();
            updateBackIterator();
    }

    public boolean containsElement(T element) {

        if(_size == 0)
                return false;
        if(_size == 1)
                        return this._front.getValue().equals(element);
       if(_size == 2)
                        return this._front.getValue().equals(element) || this._back.getValue().equals(element);
        else{

                Nodet<T> first = this._front;
                Nodet<T> last = this._back;

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
    

    public void addToFront(T element) {
            if(!containsElement(element))
                   pushFront(element);
    }

    public void addToBack(T element) {
            if(!containsElement(element))
                   pushBack(element);
    }

    public T getElement(long index) throws NullPointerException {

            if(!isEmpty()){

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

                                            Nodet<T> auxiliar = this._front.getNext();

                                            for(long i = 1L; i < realIndex;i++)
                                                    auxiliar = auxiliar.getNext();

                                            return auxiliar.getValue();

                                    }
                                    else if(differenceBetweenMiddle < differenceBetweenBack 
                                                    && differenceBetweenMiddle < differenceBetweenFront) {

                                            if(realIndex > middleIndex) {
                                                    Nodet<T> auxiliar = this._middle.getNext();

                                                    for(long i = middleIndex+1; i < realIndex;i++)
                                                            auxiliar = auxiliar.getNext();

                                                    return auxiliar.getValue();
                                            }
                                            else if(realIndex == middleIndex)
                                                    return this._middle.getValue();

                                            else {
                                                    Nodet<T> auxiliar = this._middle.getPrevious();

                                                    for(long i = middleIndex-1; i > realIndex;i--)
                                                            auxiliar = auxiliar.getPrevious();

                                                    return auxiliar.getValue();
                                            }
                                    }
                                    else{

                                            Nodet<T> auxiliar = this._back.getPrevious();

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

                                            Nodet<T> auxiliar = this._front.getNext();

                                            for(long i = 1L; i < realIndex;i++)
                                                    auxiliar = auxiliar.getNext();

                                            return auxiliar.getValue();

                                    }

                                    else if(differenceBetweenMiddle < differenceBetweenBack 
                                                    && differenceBetweenMiddle < differenceBetweenFront) {

                                            if(realIndex > (middleIndex-1)) {

                                                    Nodet<T> auxiliar = this._middle.getNext();

                                                    for(long i = middleIndex; i < realIndex;i++)
                                                            auxiliar = auxiliar.getNext();

                                                    return auxiliar.getValue();
                                            }
                                            else if(realIndex == (middleIndex-1))
                                                    return this._middle.getValue();

                                            else {
                                                    Nodet<T> auxiliar = this._middle.getPrevious();

                                                    for(long i = middleIndex-2; i > realIndex;i--)
                                                            auxiliar = auxiliar.getPrevious();

                                                    return auxiliar.getValue();
                                            }

                                    }
                                    else{

                                            Nodet<T> auxiliar = this._back.getPrevious();

                                            for(long i = this._size-2; i > realIndex;i--)
                                                    auxiliar = auxiliar.getPrevious();

                                            return auxiliar.getValue();
                                    }
                            }

                    }
            }

            return null;
    }

    public void replace(T elementToReplace,T replacement){

            if(!isEmpty()) {
                    Nodet<T> node = getNode(elementToReplace);

                    if(node != null) 
                            node.setValue(replacement);
            }
    }

    public void replacing(T elementToReplace, T replacement) {
            if(!isEmpty()) {
                    Nodet<T> node = this._front;

                    while(node != null) {

                            if(node.getValue().equals(elementToReplace))
                                    node.setValue(replacement);

                            node = node.getNext();
                    }
            }
            
    }

    public  void replace(long position,T replacement){

            if(!isEmpty()) {
                    Nodet<T> node = getNode(position);

                    if(node != null) 
                            node.setValue(replacement);
            }
    }

    public  void superSede(T elementToReplace, T replacement) {
            if(!isEmpty()) {

                    if(!containsElement(replacement)) 
                           replace(elementToReplace,replacement);
            }
            
    }

    public void  superSede(long position,T replacement) {

            if(!isEmpty()) {

                    if(!containsElement(replacement)) 
                            replace(position,replacement);
            }
    }

    public  void swap(long firstPosition, long secondPosition){
            if(!isEmpty()){

                    if(firstPosition != secondPosition){

                            Nodet<T> first = getNode(firstPosition);
                            Nodet<T> second = getNode(secondPosition);

                            T auxiliar = second.getValue();
                            second.setValue(first.getValue());
                            first.setValue(auxiliar);
                    }
            }
    }

    public void swap(T firstElement, T secondElement){
            if(!isEmpty()){

                    Doublet<Nodet<T>,Nodet<T>> nodes = getNodes(firstElement, secondElement);

                    if(nodes.getFirstElement() != null 
                                    && nodes.getSecondElement() != null) {

                            T auxiliar = nodes.getFirstElement().getValue();
                            nodes.getFirstElement().setValue(nodes.getSecondElement().getValue());
                            nodes.getSecondElement().setValue(auxiliar);
                    }
                    
                    nodes = null;
            }
    }

    public long getPositionAt(T element){
            long frontCounter = 0;
            long backCounter = this._size-1;
            Nodet<T> first = this._front;
            Nodet<T> last = this._back;

            double middleIndex = ((double)this._size)/2;

            for(double i = 0; i < middleIndex;i++) {

                    if(first.getValue().equals(element))
                            return frontCounter;

                    if(last.getValue().equals(element))
                            return backCounter;

                    frontCounter++;
                    backCounter--;

                    first = first.getNext();
                    last = last.getPrevious();
            }
            return -1;
    }

    public void  popFront(){
            if(!isEmpty()){
                    Nodet<T> erasedNode = this._front;
                    if(this._size % 2 == 0) 
                            this._middle = this._middle.getNext();
                    this._front = this._front.getNext();

                if(this._front != null)
                            this._front.setPrevious(null);

                    --this._size;

                    erasedNode = null;
                    
                    updateFrontIterator();
                    updateBackIterator();
                    
            }
    }

    public void popBack(){
            if(!isEmpty()){
                    Nodet<T> erasedNode = this._back;
                    if(this._size % 2 != 0) 
                            this._middle = this._middle.getPrevious();

                    this._back = this._back.getPrevious();

                if(this._back != null)
                            this._back.setNext(null);

                    --this._size;
                    erasedNode = null;
                    
                    updateBackIterator();
                    updateFrontIterator();
            }
    }

    public void joinToBack(Listing<T> list){
        if(!list.isEmpty()){
            Nodet<T> auxiliar = list.getFrontNodet();

            while(auxiliar != null) {
                    this.pushBack(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
            
            updateBackIterator();
            updateFrontIterator();
        }
    }

    public void joinToFront(Listing<T> list){
        if(!list.isEmpty()){
            Nodet<T> auxiliar = list.getFrontNodet();

            while(auxiliar != null) {
                    this.pushFront(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
            
            updateBackIterator();
            updateFrontIterator();
        }
    }

    /*

            Matrix<T>.Column auxiliar = row.Front();

            while(auxiliar != null) {
                    this.pushBack(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

   
    public void JoinToFront(Matrix<T>.Row row){

            Matrix<T>.Column auxiliar = row.Front();

            while(auxiliar != null) {
                    this.pushFront(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    
    public void JoinToBack(Structure<T> structure){

            List<T>.Nodet<T> auxiliar = structure.ToList().Front();

            while(auxiliar != null) {
                    this.pushBack(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    
    public void JoinToFront(Structure<T> structure){

            List<T>.Nodet<T> auxiliar = structure.ToList().Front();

            while(auxiliar != null) {
                    this.pushFront(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    
    public void JoinToBack(Graph<T> graph){

            Graph<T>.Vertex auxiliar = graph.Front();

            while(auxiliar != null) {
                    this.pushBack(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

   
    public void JoinToFront(Graph<T> graph){

            Graph<T>.Vertex auxiliar = graph.Front();

            while(auxiliar != null) {
                    this.pushFront(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    */

    public void concatenateToFront(Listing<T> list){
        if(!list.isEmpty()){
            Nodet<T> auxiliar = list.getFrontNodet();

                while(auxiliar != null) {
                        this.addToFront(auxiliar.getValue());
                        auxiliar = auxiliar.getNext();
                }

                updateBackIterator();
                updateFrontIterator();
        }
    }

    public void concatenateToBack(Listing<T> list){
            if(!list.isEmpty()){
                Nodet<T> auxiliar = list.getFrontNodet();

                while(auxiliar != null) {
                        this.addToBack(auxiliar.getValue());
                        auxiliar = auxiliar.getNext();
                }

                updateBackIterator();
                updateFrontIterator();
            }
    }

    /*
    public void ConcatenateToFront(Matrix<T>.Row row){
            Matrix<T>.Column auxiliar = row.Front();

            while(auxiliar != null) {
                    this.InsertToFront(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

   
    public void ConcatenateToBack(Matrix<T>.Row row){
            Matrix<T>.Column auxiliar = row.Front();

            while(auxiliar != null) {
                    this.InsertToBack(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    
    public void ConcatenateToBack(Structure<T> structure){
            List<T>.Nodet<T> auxiliar = structure.ToList().Front();

            while(auxiliar != null) {
                    this.InsertToBack(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    
    public void ConcatenateToFront(Structure<T> structure){
            List<T>.Nodet<T> auxiliar = structure.ToList().Front();

            while(auxiliar != null) {
                    this.InsertToFront(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    
    public void ConcatenateToBack(Graph<T> graph){
            Graph<T>.Vertex auxiliar = graph.Front();

            while(auxiliar != null) {
                    this.InsertToBack(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }

    
    public void ConcatenateToFront(Graph<T> graph){
            Graph<T>.Vertex auxiliar = graph.Front();

            while(auxiliar != null) {
                    this.InsertToFront(auxiliar.getValue());
                    auxiliar = auxiliar.getNext();
            }
    }
    */

    public void reverse() {

            if(!isEmpty()) {

                    Nodet<T> first = this._front;
                    Nodet<T> last = this._back;

                    double middleIndex = ((double)this._size)/2;

                    for(double i = 0; i < middleIndex;i++) {

                            T firstvalue = first.getValue();
                            first.setValue(last.getValue());
                            last.setValue(firstvalue);

                            first = first.getNext();
                            last = last.getPrevious();
                    }
            }
    }

    public void clear(){
            if(!isEmpty()){
                    while(!isEmpty()) {
                            popFront();
                    }
                    this._size = 0;
                    this._back = this._middle  = this._front = null;
                    updateBackIterator();
                    updateFrontIterator();
            }
    }

    public void insertNextTo(Nodet<T> node, T element) {
            if(!isEmpty()){

                    if(node != null) {
                            if(node.getValue().equals(this._back.getValue())) 
                                    pushBack(element);
                            else{

                                    Nodet<T> previousNode = node;
                                    Nodet<T> nextNode = previousNode.getNext();
                                    Nodet<T> newNode = new Nodet<>(previousNode,element,nextNode);

                                    previousNode.setNext(newNode);
                                    nextNode.setPrevious(newNode);

                                    if(this._size % 2 == 0)
                                            this._middle = this._middle.getNext();

                                    this._size++; 
                            }
                            
                            updateBackIterator();
                            updateFrontIterator();
                    }
            }
    }

    public void insertPreviousTo(Nodet<T> node, T element) {
            if(!isEmpty()){

                    if(node != null) {
                            if(node.getValue().equals(this._front.getValue())) 
                                    pushFront(element);
                            else {
                                    Nodet<T> previousNode = node.getPrevious();
                                    Nodet<T> nextNode = node;
                                    Nodet<T> newNode = new Nodet<>(previousNode,element,nextNode);

                                    previousNode.setNext(newNode);
                                    nextNode.setPrevious(newNode);

                                    if(this._size % 2 != 0)
                                            this._middle = this._middle.getPrevious();

                                    this._size++;
                            }
                            
                            updateBackIterator();
                            updateFrontIterator();
                    }
            }
    }

    public void insertAt(long position, T element){
            if(!isEmpty()){
                    position = position % this._size;
                    if(position == 0) pushFront(element);
                    else if(position == this._size-1) pushBack(element);
                    else{

                            long middleIndex = (this._size+1)/2;

                            if(position != middleIndex) {
                                    Nodet<T> previousNode = getNode(position-1);
                                    Nodet<T> nextNode = previousNode.getNext();
                                    Nodet<T> newNode = new Nodet<>(previousNode,element,nextNode);

                                    previousNode.setNext(newNode);
                                    nextNode.setPrevious(newNode);

                                    if(this._size % 2 != 0)
                                            this._middle = this._middle.getPrevious();
                            }
                            else {
                                    Nodet<T> previousNode = getNode(position-1);
                                    Nodet<T> nextNode = previousNode.getNext();
                                    Nodet<T> newNode = new Nodet<>(previousNode,element,nextNode);

                                    previousNode.setNext(newNode);
                                    nextNode.setPrevious(newNode);

                                    this._middle = newNode;
                            }

                            this._size++;
                    }
            }
    }

    public void introduceAt(long index, T element){

            if(!isEmpty()) {

                    if(!containsElement(element))
                            insertAt(index,element);
            }
    }

    public void eraseAt(long position){
            if(!isEmpty()){

                    long realPosition = position % this._size;

                    if(realPosition == 0) 
                            popFront();

                    else if(realPosition == (this._size -1) )
                            popBack();

                    else{

                            Nodet<T> previousNode = getNode(position-1);
                            Nodet<T> erasedNode = previousNode.getNext();
                            boolean isMiddle = erasedNode.getValue().equals(this._middle.getValue());
                            Nodet<T> nextNode = erasedNode.getNext();

                            previousNode.setNext(nextNode);
                            nextNode.setPrevious(previousNode);

                            erasedNode = null;

                    this._size--;

                    if(!isMiddle) {
                            if(this._size % 2 != 0)
                                        this._middle = this._middle.getNext();
                    }
                    else 
                            this._middle = (this._size % 2 == 0) ? previousNode : nextNode;
                    }
            }
    }

    public void remove(T element) {
            if(!isEmpty()) {

                    if(this._front.getValue().equals(element))
                            popFront();

                    else if(this._back.getValue().equals(element))
                            popBack();

                    else {

                            Nodet<T> erasedNode = getNode(element);

                            if(erasedNode != null) {
                                    Nodet<T> previousNode = erasedNode.getPrevious();
                                    boolean isMiddle = erasedNode.getValue().equals(this._middle.getValue());
                                    Nodet<T> nextNode = erasedNode.getNext();

                                    previousNode.setNext(nextNode);
                                    nextNode.setPrevious(previousNode);
                                    erasedNode = null;

                            this._size--;

                            if(!isMiddle) {
                                    if(this._size % 2 != 0)
                                                    this._middle = this._middle.getNext();
                            }
                            else 
                                    this._middle = (this._size % 2 == 0) ? previousNode : nextNode;

                            }
                    }
            }
    }

    //Override methods

    @Override
    public String toString() throws NullPointerException{

            String newString = new String();

            Nodet<T> auxiliar = this._front;

            while(auxiliar != null){
                    newString += "["+auxiliar.getValue()+"]\n";
                    auxiliar = auxiliar.getNext();
            }
            return newString;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        Nodet<T> aux = this._front;
        while(aux != null){
            hash = 53 * hash + aux.getValue().hashCode();
            aux = aux.getNext();
        }
        
        hash = 53 * hash + (int) (this._size ^ (this._size >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
         if (obj == null) 
            return false;
        
        if (this == obj) 
            return true;

        if (!(obj instanceof Listing<?>)) 
            return false; 
        
         Listing<T> other = (Listing<T>) obj;
         
         if(other.getSize() == this._size){
             Nodet<T> thisAux = this._front;
            Nodet<T> otherAux = other.getFrontNodet();
            
            while(thisAux != null){
                
                if(!thisAux.getValue().equals(otherAux.getValue()))
                    return false;
                
                thisAux = thisAux.getNext();
                otherAux = otherAux.getNext();
             
            }
            
            return true;
         }
         
         return false;
         
    }

    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

	
}
    

