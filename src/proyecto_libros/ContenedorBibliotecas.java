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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejandro
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ContenedorBibliotecas")
public class ContenedorBibliotecas implements Serializable {

    @XmlElement
    ArrayList<Biblioteca> listaBibliotecas = new ArrayList<>();
    
    public ContenedorBibliotecas() {
    }

    public ArrayList<Biblioteca> getListaBibliotecas() {
        return listaBibliotecas;
    }

    public void setListaBibliotecas(ArrayList<Biblioteca> listaBibliotecas) {
        this.listaBibliotecas = listaBibliotecas;
    }

    @Override
    public String toString() {
        return "ContenedorBibliotecas{" + "listaBibliotecas=" + listaBibliotecas + '}';
    }

}
