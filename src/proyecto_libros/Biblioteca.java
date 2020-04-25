/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_libros;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejandro
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Biblioteca")
public class Biblioteca implements Serializable {

    @XmlAttribute
    private int id;
    @XmlAttribute
    private String facultad;
    @XmlAttribute
    private String ciudad;
    @XmlElement
    ArrayList<Libro> listaLibros = new ArrayList<>();

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Biblioteca() {
    }

    public String getFacultad() {
        return facultad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public ArrayList<Libro> getListaLibros() {
        return listaLibros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setListaLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @Override
    public String toString() {
        return "\nBiblioteca{" + "\n\tid=" + id + "\n\tfacultad=" + facultad + "\n\tciudad=" + ciudad + "\n\tlistaLibros=" + listaLibros + '}';
    }

}
