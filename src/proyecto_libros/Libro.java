/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_libros;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejandro
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Libro")
public class Libro implements Serializable {

    @XmlAttribute
    private String isbn;
    @XmlAttribute
    private String autor;
    @XmlAttribute
    private String titulo;
    @XmlAttribute
    private Integer numPag;
    @XmlAttribute
    private String disponibilidad;

    public Libro() {
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNumPag(Integer numPag) {
        this.numPag = numPag;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNumPag() {
        return numPag;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    @Override
    public String toString() {
        return "\n\t\tLibro{" + "\n\t\tisbn=" + isbn + "\n\t\tautor=" + autor + "\n\t\ttitulo=" + titulo + "\n\t\tnumPag=" + numPag + "\n\t\tdisponibilidad=" + disponibilidad + '}';
    }

}
