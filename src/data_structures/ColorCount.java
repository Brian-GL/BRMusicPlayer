/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

import javafx.scene.paint.Color;

/**
 *
 * @author LENOVO
 */
public class ColorCount implements Comparable<ColorCount>{
    
    private Doublet<Integer,Color> doublet;

    public Integer getFirstElement() throws NullPointerException {
        return doublet.getFirstElement();
    }

    public void setFirstElement(Integer firstElement) {
        doublet.setFirstElement(firstElement);
    }

    public Color getSecondElement() throws NullPointerException {
        return doublet.getSecondElement();
    }

    public void setSecondElement(Color secondElement) {
        doublet.setSecondElement(secondElement);
    }
    
    public ColorCount(Doublet<Integer,Color> aDoublet){
        doublet = aDoublet;
    }

    @Override
    public int compareTo(ColorCount o) {
        return this.getFirstElement().compareTo(o.getFirstElement());
    }
    
    
    
}
