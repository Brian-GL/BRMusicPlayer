/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;


public class TwoSome {
    
  private Doublet<Object,Object> _doublet;

    public TwoSome(Doublet<Object, Object> doublet) {
        this._doublet = new Doublet<>(doublet);
    }
    
    public TwoSome(TwoSome twoSome){
        this._doublet = new Doublet<>(twoSome.getFirstObject(),twoSome.getSecondObject());
    }
    
    public TwoSome(Object firstObject, Object secondObject){
        this._doublet = new Doublet<>(firstObject,secondObject);
    }

    public Object getFirstObject() throws NullPointerException {
        return _doublet.getFirstElement();
    }

    public void setFirstObject(Object firstObject) {
        _doublet.setFirstElement(firstObject);
    }

    public Object getSecondObject() throws NullPointerException {
        return _doublet.getSecondElement();
    }

    public void setSecondObject(Object secondObject) {
        _doublet.setSecondElement(secondObject);
    }
    
    public Doublet<Object,Object> toDoublet(){
        return this._doublet;
    }

  @Override
    public String toString() throws NullPointerException {
        return _doublet.toString();
    }

  @Override
    public int hashCode() {
        return _doublet.hashCode();
    }

  @Override
    public boolean equals(Object obj) {
        return _doublet.equals(obj);
    }

  @Override
    protected void finalize() throws Throwable {
      try {
          _doublet.finalize();
      } finally {
          super.finalize();
      }
    }

  @Override
    protected Object clone() throws CloneNotSupportedException {
        return _doublet.clone();
    }

}
