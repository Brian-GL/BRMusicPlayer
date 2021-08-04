/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

public class FourSome {

    private Quartet<Object,Object,Object,Object> _quartet;
    
    public FourSome(Object firstObject, Object secondObject, Object thirdObject,Object fourthObject) {
        this._quartet = new Quartet<>(firstObject,secondObject,thirdObject,fourthObject);
    }
    
     public FourSome(FourSome fourSomeElement){
        this._quartet  = new Quartet<>(fourSomeElement.getFirstObject(),fourSomeElement.getSecondObject(),fourSomeElement.getThirdObject(),fourSomeElement.getFourthObject());
    }
     
     public FourSome(Quartet<Object,Object,Object,Object> quartet){
        this._quartet = new Quartet<>(quartet);
    }

    /**
     * @return the _firstObject
     */
    public Object getFirstObject() throws NullPointerException{
        return _quartet.getFirstElement();
    }

    /**
     * @param firstObject the _firstObject to set
     */
    public void setFirstObject(Object firstObject) {
        this._quartet.setFirstElement(firstObject);
    }

    /**
     * @return the _secondObject
     */
    public Object getSecondObject() throws NullPointerException {
        return _quartet.getSecondElement();
    }

    /**
     * @param secondObject the _secondObject to set
     */
    public void setSecondObject(Object secondObject) {
        this._quartet.setSecondElement(secondObject);
    }

    /**
     * @return the _thirdObject
     */
    public Object getThirdObject() throws NullPointerException{
        return _quartet.getThidElement();
    }

    /**
     * @param thirdObject the _thirdObject to set
     */
    public void setThirdObject(Object thirdObject) {
        this._quartet.setThidElement(thirdObject);
    }

    /**
     * @return the _fourthObject
     */
    public Object getFourthObject() throws NullPointerException{
        return _quartet.getFourthElement();
    }

    /**
     * @param fourthObject the _fourthObject to set
     */
    public void setFourthObject(Object fourthObject) {
        this._quartet.setFourthElement(fourthObject);
    }

    public Quartet<Object,Object,Object,Object> toQuartet() throws NullPointerException{
        return this._quartet;
    }

    @Override
    public String toString() throws NullPointerException {
        return _quartet.toString();
    }

    @Override
    public int hashCode() {
        return _quartet.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return _quartet.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            _quartet.finalize();
        } finally {
            super.finalize();
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return _quartet.clone();
    }

   
    
    
}
