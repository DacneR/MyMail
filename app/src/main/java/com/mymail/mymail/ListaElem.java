package com.mymail.mymail;

import android.widget.ImageView;

public class ListaElem {

    public ListaElem(String color, String titulo, String descr, String comen) {
        this.color = color;
        this.titulo = titulo;
        this.descr = descr;
        this.comen = comen;

    }

    private   String color, titulo, descr, comen;






    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getComen() {
        return comen;
    }

    public void setComen(String comen) {
        this.comen = comen;
    }
}
