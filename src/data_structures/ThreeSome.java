/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

public class ThreeSome {
    
    private Triplet<Object,Object,Object> _triplet;

    public ThreeSome(Object firstObject, Object secondObject, Object thirdObject) {
        this._triplet = new Triplet<>(firstObject,secondObject,thirdObject);
    }
    
    public ThreeSome(ThreeSome threeSomeElement) {
       this. _triplet = new Triplet<>(threeSomeElement.getFirstObject(),threeSomeElement.getSecondObject(),threeSomeElement.getThirdObject());
    }
    
    public ThreeSome(Triplet<Object,Object,Object> triplet){
        this._triplet = new Triplet<>(triplet);
    }
            

    /**
     * @return the _firstObject
     */
    public Object getFirstObject() throws NullPointerException{
        return _triplet.getFirstElement();
    }

    /**
     * @param firstObject the _firstObject to set
     */
    public void setFirstObject(Object firstObject) {
        this._triplet.setFirstElement(firstObject);
    }

    /**
     * @return the _secondObject
     */
    public Object getSecondObject() throws NullPointerException{
        return _triplet.getSecondElement();
    }

    /**
     * @param secondObject the _secondObject to set
     */
    public void setSecondObject(Object secondObject) {;
         this._triplet.setSecondElement(secondObject);
    }

    /**
     * @return the _thirdObject
     */
    public Object getThirdObject() throws NullPointerException{
        return _triplet.getThidElement();
    }

    /**
     * @param thirdObject the _thirdObject to set
     */
    public void setThirdObject(Object thirdObject) {
         this._triplet.setThidElement(thirdObject);
    }

    /**
     * @return the _triplet
     */
    public Triplet<Object,Object,Object> toTriplet() throws NullPointerException{
        return _triplet;
    }

    @Override
    public int hashCode() {
        return _triplet.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return _triplet.equals(obj);
    }

    @Override
    public String toString() throws NullPointerException {
        return _triplet.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            _triplet.finalize();
        } finally {
            super.finalize();
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return _triplet.clone();
    }

    
    
}
