/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

public  class Iterating<E>{
    
    private Nodet<E> _nodet;

    public Iterating() { }
    
    protected void setNodet(Nodet<E> nodet){
        this._nodet = nodet;
    }
    
    public E  value() throws NullPointerException{
        return _nodet.getValue();
    }
    
    public boolean hasNextOrPrevious(){
        return _nodet != null;
    }
    
    public void next(){
        if(hasNextOrPrevious())
            _nodet = _nodet.getNext();
    }
    
    public void previous(){
        if(hasNextOrPrevious())
            _nodet = _nodet.getPrevious();
    }

    @Override
    public String toString() throws NullPointerException{
        return _nodet.toString();
    }

    @Override
    public int hashCode() {
       return _nodet.hashCode();
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        return _nodet.equals(obj);
    }

    @Override
    @SuppressWarnings({"FinalizeDeclaration", "FinalizeCalledExplicitly", "FinalizeDoesntCallSuperFinalize"})
    protected void finalize() throws Throwable {
        _nodet.finalize();
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    protected Object clone() throws CloneNotSupportedException {
        return _nodet.clone();
    }

}

