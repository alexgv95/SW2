/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_libros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Alejandro
 */
public class GestionBiblioteca {

    static ArrayList<Biblioteca> listaBibliotecas = new ArrayList<>();
    static ArrayList<Libro> listaLibrosGeneral = new ArrayList<>();
    static Biblioteca bib;

    /**
     *
     */
    public static void crearBiblioteca() {
        int id = 0;
        String ciudad = "";
        String facultad = "";
        try {
            System.out.println("-Creación de Biblioteca-");
            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Introducir ID: ");
            id = Integer.parseInt(consola.readLine());
            System.out.println("Introducir CIUDAD: ");
            ciudad = consola.readLine();
            System.out.println("Introducir FACULTAD: ");
            facultad = consola.readLine();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        Biblioteca bib = new Biblioteca();
        bib.setId(id);
        bib.setCiudad(ciudad);
        bib.setFacultad(facultad);
        listaBibliotecas.add(bib);
        System.out.println("La biblioteca de " + facultad + "ha sido creada"
                + "satisfactoriamente");
        System.out.println(listaBibliotecas);
    }

    public static Biblioteca buscarBiblioteca(int id) {
        for (Biblioteca bib : listaBibliotecas) {
            System.out.println(listaBibliotecas);
            if (bib.getId() == id) {
                return bib;
            }
        }
        return null;
    }

    public static void anadirLibro() {
        String isbn = "";
        String autor = "";
        String titulo = "";
        Integer numPag;
        String disponibilidad = "";

        try {
            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("-Inserción de libro-");
            System.out.println("Introduzca ID de la biblioteca: ");
            int id = Integer.parseInt(consola.readLine());
            Biblioteca bib = buscarBiblioteca(id);
            System.out.println("Introducir isbn: ");
            isbn = consola.readLine();
            System.out.println("Introducir autor: ");
            autor = consola.readLine();
            System.out.println("Introducir título: ");
            titulo = consola.readLine();
            System.out.println("Introducir numPag: ");
            numPag = Integer.parseInt(consola.readLine());
            System.out.println("Introducir disponibilidad: DESCATALOGADO/DISPONIBLE/BAJO_PEDIDO");
            disponibilidad = consola.readLine();

            Libro lib = new Libro();
            lib.setIsbn(isbn);
            lib.setAutor(autor);
            lib.setNumPag(numPag);
            lib.setDisponibilidad(disponibilidad);
            lib.setTitulo(titulo);

            ArrayList<Libro> listaLibros = bib.getListaLibros();
            listaLibros.add(lib);
            bib.setListaLibros(listaLibros);

            listaLibrosGeneral.add(lib);

        } catch (IOException ex) {
            Logger.getLogger(GestionBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void marshallingBiblioteca() {

        try {
            listarBibliotecas();
            ContenedorBibliotecas contenedor = new ContenedorBibliotecas();
            contenedor.setListaBibliotecas(listaBibliotecas);
            //creating the JAXB context
            JAXBContext jContext = JAXBContext.newInstance(ContenedorBibliotecas.class);
            //creating the marshaller object
            Marshaller marshallObj = jContext.createMarshaller();
            //setting the property to show xml format output
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //setting the values in POJO class
            //calling the marshall method
            OutputStream os = new FileOutputStream("bibliotecas.xml");
            //marshallObj.marshal(bib1, System.out);
            marshallObj.marshal(contenedor, os);
            System.out.println("Biblioteca exportada con exito\n\n");
        } catch (JAXBException e) {
            System.out.println(e);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Proyecto_Libros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void unmarshallingBiblioteca() {
        try {
            ContenedorBibliotecas contenedor = new ContenedorBibliotecas();
            //getting the xml file to read
            File file = new File("bibliotecas.xml");
            //creating the JAXB context
            JAXBContext jContext = JAXBContext.newInstance(ContenedorBibliotecas.class);
            //creating the unmarshall object
            Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
            //calling the unmarshall method
            contenedor = (ContenedorBibliotecas) unmarshallerObj.unmarshal(file);
            System.out.println(contenedor);
            listaBibliotecas = contenedor.getListaBibliotecas();
            cargarListaLibrosGeneral();
        } catch (JAXBException e) {
            System.out.println(e);
        }
    }

    public static void listarBibliotecas() {
        System.out.println(listaBibliotecas);
    }

    public static void listarLibrosGeneral() {
        //cargarListaLibrosGeneral();
        System.out.println("[Cargando la lista general ...]");
        System.out.println(listaLibrosGeneral);
    }

    public static void unmarshallingLibro() {
        try {

            //ContenedorBibliotecas contenedor = new ContenedorBibliotecas();
            //getting the xml file to read
            File file = new File("libroParticular.xml");
            //creating the JAXB context
            JAXBContext jContext = JAXBContext.newInstance(Libro.class);
            //creating the unmarshall object
            Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
            //calling the unmarshall method
            //contenedor = (ContenedorBibliotecas) unmarshallerObj.unmarshal(file);
            //System.out.println(contenedor);
            Libro lib = new Libro();
            lib = (Libro) unmarshallerObj.unmarshal(file);
            anadirLibro(lib);
            System.out.println("Libro importado con exito");
        } catch (JAXBException e) {
            System.out.println(e);
        }
    }

    public static void marshallingLibro() {

        try {
            listarLibrosGeneral();
            System.out.println("Seleccionar el ISBN del libro a exportar: ");
            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
            String isbn = consola.readLine();
            Libro lib = buscarLibro(isbn);
            //creating the JAXB context
            JAXBContext jContext = JAXBContext.newInstance(Libro.class);
            //creating the marshaller object
            Marshaller marshallObj = jContext.createMarshaller();
            //setting the property to show xml format output
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //setting the values in POJO class
            //calling the marshall method
            OutputStream os = new FileOutputStream("libroParticular.xml");
            //marshallObj.marshal(bib1, System.out);
            marshallObj.marshal(lib, os);
            System.out.println("Libro: " + lib.getTitulo() + " exportado con exito\n\n");
        } catch (JAXBException e) {
            System.out.println(e);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Proyecto_Libros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Libro buscarLibro(String isbn) {
        for (Libro lib : listaLibrosGeneral) {
            if (lib.getIsbn().equals(isbn)) {
                return lib;
            }
        }
        return null;
    }

    private static void anadirLibro(Libro lib) {
        
        try{
            System.out.println(listaBibliotecas);
            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Introduzca ID de la biblioteca: ");
            int id = Integer.parseInt(consola.readLine());
            Biblioteca bib = buscarBiblioteca(id);
            ArrayList<Libro> listaLibros = bib.getListaLibros();
            listaLibros.add(lib);
            bib.setListaLibros(listaLibros);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    private static void cargarListaLibrosGeneral() {
        for (int i = 0; i < listaBibliotecas.size(); i++) {
            //System.out.println(listaBibliotecas.size());
            Biblioteca bibI = listaBibliotecas.get(i);
            System.out.println(bibI);
            for (int x = 0; x < bibI.getListaLibros().size(); x++) {
                System.out.println(x);
                System.out.println(bibI.getListaLibros().size());
                Libro libX = bibI.listaLibros.get(x);
                System.out.println(libX);
                listaLibrosGeneral.add(libX);
            }
        }
    }
}