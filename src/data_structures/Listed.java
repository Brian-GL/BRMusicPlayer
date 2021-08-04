/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

public class Listed {
    
    private Listing<Object> _list;
    
    public Listed(){
        _list = new Listing<>();
    }

    public boolean isEmpty() {
        return _list.isEmpty();
    }

    public long getSize() {
        return _list.getSize();
    }

    public Object getFirstElement() throws NullPointerException {
        return _list.getFirstElement();
    }

    public Object getLastElement() throws NullPointerException {
        return _list.getLastElement();
    }

    public Object getMediumElement() throws NullPointerException {
        return _list.getMediumElement();
    }

    public Nodet<Object> getFrontNodet() throws NullPointerException {
        return _list.getFrontNodet();
    }

    public Nodet<Object> getBackNodet() throws NullPointerException {
        return _list.getBackNodet();
    }

    public Nodet<Object> getMiddleNodet() throws NullPointerException {
        return _list.getMiddleNodet();
    }

    public Object dequeue() throws NullPointerException {
        return _list.dequeue();
    }

    public Object unstack() throws NullPointerException {
        return _list.unstack();
    }

    public Iterating<Object> getFrontIterator() throws NullPointerException {
        return _list.getFrontIterator();
    }

    public Iterating<Object> getBackIterator() throws NullPointerException {
        return _list.getBackIterator();
    }

    public void pushBack(Object element) {
        _list.pushBack(element);
    }
    
    public Listing<Object> toListing(){
        return this._list;
    }

    public void pushFront(Object element) {
        _list.pushFront(element);
    }

    public boolean containsElement(Object element) {
        return _list.containsElement(element);
    }

    public void addToFront(Object element) {
        _list.addToFront(element);
    }

    public void addToBack(Object element) {
        _list.addToBack(element);
    }

    public Object getElement(long index) throws NullPointerException {
        return _list.getElement(index);
    }

    public void replace(Object elementToReplace, Object replacement) {
        _list.replace(elementToReplace, replacement);
    }

    public void replacing(Object elementToReplace, Object replacement) {
        _list.replacing(elementToReplace, replacement);
    }

    public void replace(long position, Object replacement) {
        _list.replace(position, replacement);
    }

    public void superSede(Object elementToReplace, Object replacement) {
        _list.superSede(elementToReplace, replacement);
    }

    public void superSede(long position, Object replacement) {
        _list.superSede(position, replacement);
    }

    public void swap(long firstPosition, long secondPosition) {
        _list.swap(firstPosition, secondPosition);
    }

    public void swap(Object firstElement, Object secondElement) {
        _list.swap(firstElement, secondElement);
    }

    public long getPositionAt(Object element) {
        return _list.getPositionAt(element);
    }

    public void popFront() {
        _list.popFront();
    }

    public void popBack() {
        _list.popBack();
    }

    public void joinToBack(Listing<Object> list) {
        _list.joinToBack(list);
    }

    public void joinToFront(Listing<Object> list) {
        _list.joinToFront(list);
    }

    public void concatenateToFront(Listing<Object> list) {
        _list.concatenateToFront(list);
    }

    public void concatenateToBack(Listing<Object> list) {
        _list.concatenateToBack(list);
    }

    public void reverse() {
        _list.reverse();
    }

    public void clear() {
        _list.clear();
    }

    public void insertNextTo(Nodet<Object> node, Object element) {
        _list.insertNextTo(node, element);
    }

    public void insertPreviousTo(Nodet<Object> node, Object element) {
        _list.insertPreviousTo(node, element);
    }

    public void insertAt(long position, Object element) {
        _list.insertAt(position, element);
    }

    public void introduceAt(long index, Object element) {
        _list.introduceAt(index, element);
    }

    public void eraseAt(long position) {
        _list.eraseAt(position);
    }

    public void remove(Object element) {
        _list.remove(element);
    }

    @Override
    public String toString() {
        return _list.toString();
    }

    @Override
    public int hashCode() {
        return _list.hashCode();
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        return _list.equals(obj);
    }

    @Override
    @SuppressWarnings({"FinalizeDeclaration", "FinalizeCalledExplicitly"})
    protected void finalize() throws Throwable {
        try {
            _list.finalize();
        } finally {
            super.finalize();
        }
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    protected Object clone() throws CloneNotSupportedException {
        return _list.clone();
    }
    
}
