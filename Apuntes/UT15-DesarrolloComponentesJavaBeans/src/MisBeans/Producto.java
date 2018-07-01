/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author Ana Arribas
 */
public class Producto implements Serializable {
    
     
    private String description;
    private int idproducto;
    private int stockactual;
    private int stockminimo;
    private double pvp;
    
    private PropertyChangeSupport propertySupport;
    
    public Producto()
    {}
    
    public Producto(int idproducto,String descripcion, int stockactual,int stockminimo, double pvp) {
        propertySupport = new PropertyChangeSupport(this);
        this.idproducto=idproducto;
        this.description=descripcion;
        this.stockactual=stockactual;
        this.stockminimo=stockminimo;
        this.pvp=pvp;
    }
    
   
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the idproducto
     */
    public int getIdproducto() {
        return idproducto;
    }

    /**
     * @param idproducto the idproducto to set
     */
    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    /**
     * @return the stockactual
     */
    public int getStockactual() {
        return stockactual;
    }

    /**
     * @param stockactual the stockactual to set
     */
    public void setStockactual(int valorNuevo) {
        int valorAnterior = stockactual; 
        stockactual = valorNuevo;
        if (stockactual < getStockminimo()) //hay que realizar pedido
            propertySupport.firePropertyChange("stockactual", valorAnterior, stockactual);

    }

    /**
     * @return the stockminimo
     */
    public int getStockminimo() {
        return stockminimo;
    }

    /**
     * @param stockminimo the stockminimo to set
     */
    public void setStockminimo(int stockminimo) {
        this.stockminimo = stockminimo;
    }

    /**
     * @return the pvp
     */
    public double getPvp() {
        return pvp;
    }

    /**
     * @param pvp the pvp to set
     */
    public void setPvp(double pvp) {
        this.pvp = pvp;
    }
    
}
