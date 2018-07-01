/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisBeans;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ana Arribas
 */
public class Pedido implements Serializable, PropertyChangeListener {

    private int numeropedido;
    private int idproducto;
    private Date fecha;
    private int cantidad;
    private boolean pedir;//se usa para controlar si hay que hacer pedido

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        System.out.println("Stock anterior: " + pce.getOldValue());
        System.out.println("Stock actual: " + pce.getNewValue());
        setPedir(true); //hay que hacer pedido
       

    }

    public Pedido() {
    }

    public Pedido(int numeropedido, int idproducto, java.sql.Date fecha, int cantidad) {
        this.numeropedido = numeropedido;
        this.idproducto = idproducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    /**
     * @return the numeropedido
     */
    public int getNumeropedido() {
        return numeropedido;
    }

    /**
     * @param numeropedido the numeropedido to set
     */
    public void setNumeropedido(int numeropedido) {
        this.numeropedido = numeropedido;
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
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the pedir
     */
    public boolean isPedir() {
        return pedir;
    }

    /**
     * @param pedir the pedir to set
     */
    public void setPedir(boolean pedir) {
        this.pedir = pedir;
    }

}
