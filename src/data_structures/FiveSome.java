/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

public class FiveSome {
    
    private Quintet<Object,Object,Object,Object,Object> _quintet;
    
    public FiveSome(Object firstObject, Object secondObject, Object thirdObject,Object fourthObject,Object fifthObject){
       _quintet = new Quintet<>(fifthObject,secondObject,thirdObject,fourthObject,fifthObject);
       
    }
    
     public FiveSome(FiveSome fiveSomeObject){
       _quintet = new Quintet<>(fiveSomeObject.getFirstObject(),fiveSomeObject.getSecondObject(),fiveSomeObject.getThidObject(),
               fiveSomeObject.getFourthObject(),fiveSomeObject.getFifthObject());
    }
     
     public FiveSome(Quintet<Object,Object,Object,Object,Object> quintet){
        this._quintet = new Quintet<>(quintet);
    }

    public Quintet<Object,Object,Object,Object,Object> toQuintet(){return this._quintet;}

    public Object getFirstObject() throws NullPointerException {
        return _quintet.getFirstElement();
    }

    public void setFirstObject(Object firstObject) {
        _quintet.setFirstElement(firstObject);
    }

    public Object getSecondObject() throws NullPointerException {
        return _quintet.getSecondElement();
    }

    public void setSecondObject(Object secondObject) {
        _quintet.setSecondElement(secondObject);
    }

    public Object getThidObject() throws NullPointerException {
        return _quintet.getThidElement();
    }

    public void setThidObject(Object thidObject) {
        _quintet.setThidElement(thidObject);
    }

    public Object getFourthObject() throws NullPointerException {
        return _quintet.getFourthElement();
    }

    public void setFourthObject(Object fourthObject) {
        _quintet.setFourthElement(fourthObject);
    }

    public Object getFifthObject() throws NullPointerException {
        return _quintet.getFifthElement();
    }

    public void setFifthObject(Object fifthObject) {
        _quintet.setFifthElement(
                fifthObject);
    }

    @Override
    public int hashCode() {
        return _quintet.hashCode();
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        return _quintet.equals(obj);
    }

    @Override
    public String toString() throws NullPointerException {
        return _quintet.toString();
    }

    @SuppressWarnings({"FinalizeCalledExplicitly", "FinalizeDeclaration"})
    @Override
    protected void finalize() throws Throwable {
        try {
            _quintet.finalize();
        } finally {
            super.finalize();
        }
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    protected Object clone() throws CloneNotSupportedException {
        return _quintet.clone();
    }

}
