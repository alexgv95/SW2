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
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
            OutputStream os = new FileOutputStream("xml/bibliotecas.xml");
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
            File file = new File("xml/bibliotecas.xml");
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
            System.out.println("¿Qúe título quiere importar?");
            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
            String titulo = consola.readLine();
            String fichero = "xml/" + titulo + ".xml";
            File file = new File(fichero);
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
        } catch (IOException ex) {
            Logger.getLogger(GestionBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
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
            //OutputStream os = new FileOutputStream("libroParticular.xml");
            File libroFile = new File("xml/" + lib.getTitulo() + ".xml");
            //marshallObj.marshal(bib1, System.out);
            //marshallObj.marshal(lib, os);
            marshallObj.marshal(lib, libroFile);
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

        try {
            System.out.println(listaBibliotecas);
            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Introduzca ID de la biblioteca: ");
            int id = Integer.parseInt(consola.readLine());
            Biblioteca bib = buscarBiblioteca(id);
            ArrayList<Libro> listaLibros = bib.getListaLibros();
            listaLibros.add(lib);
            bib.setListaLibros(listaLibros);
        } catch (IOException ex) {
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

    public static void validarDTD() {
        String respuesta;
        File xmlFile = null;
        try {
            System.out.println("Introduzca el nombre del fichero a validar");
            System.out.println("Consejo: Hay un fichero ya creado llamdo pruebaDTD");
            //SE VALIDA CONTRA bibliotecaDTD.dtd    
            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
            String fichero = consola.readLine();
            xmlFile = new File("xml/" + fichero + ".xml");

            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
            dBF.setValidating(true);
            DocumentBuilder builder = dBF.newDocumentBuilder();
            CustomErrorHandler customErrorHandler = new CustomErrorHandler();
            builder.setErrorHandler(customErrorHandler);
            Document doc = builder.parse(xmlFile);
            if (customErrorHandler.isValid()) {
                respuesta = (xmlFile + " es válido");
            } else {
                respuesta = (xmlFile + " no es válido");
            }
        } catch (IOException ex) {
            //Logger.getLogger(GestionBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = (xmlFile + " no ha podido ser accedido");
        } catch (ParserConfigurationException ex) {
            //Logger.getLogger(GestionBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = (xmlFile + " error en el parseado");
        } catch (SAXException ex) {
            //Logger.getLogger(GestionBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = (xmlFile + " no esta bien formado");
        }
        System.out.println(respuesta);
    }

    static void validarXSD() {
        File ficheroXml = null;
        File ficheroXsd = null;
        String respuesta = null;
        try {
            System.out.println("Introduzca el nombre del fichero a validar");
            System.out.println("Consejo: Hay un fichero ya creado llamdo pruebaXSD");
            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
            String fichero = consola.readLine();
            System.out.println("Introduzca el nombre del fichero XSD contra el que comprobarlo");
            System.out.println("Consejo: Hay un fichero ya creado llamdo biblioteca");
            String fichero2 = consola.readLine();
            ficheroXml = new File("xml/" + fichero + ".xml");
            ficheroXsd = new File("xml/" + fichero2 + ".xsd");
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(ficheroXsd);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(ficheroXml));
            respuesta = (ficheroXml + " es válido frente a " + ficheroXsd);
        } catch (IOException ex) {
            respuesta = (ficheroXml + "NO ha podido ser encontrado");
            //Logger.getLogger(GestionBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            respuesta = (ficheroXml + " NO es válido frente a " + ficheroXsd);
            //Logger.getLogger(GestionBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(respuesta);
    }

    static void sentenciaXPath() {
        System.out.println("1.Listar biblitoecas por nombre de facultad");
        System.out.println("2.Contar los libros en todas las biblitoecas");
        System.out.println("3.Listar libros de la biblioteca de Madrid");
        System.out.println("4.Listar los autores BAJO_PEDIDO (mas demandados)");
        System.out.println("5.Escribe sentencia Xpath");
        int opcion;
        String fichero;
        XPath xP = new XPath();
        try {
            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
            opcion = Integer.parseInt(consola.readLine());
            switch (opcion) {
                case 1:
                    System.out.println("Introduzca fichero con las bibliotecas a mostrar");
                    System.out.println("Consejo: Estan en el fichero bibliotecas");
                    fichero = consola.readLine();
                    System.out.println(xP.mostrarBibliotecas(fichero));
                    break;
                case 2:
                    System.out.println("Introduzca fichero con los libros a contar");
                    System.out.println("Consejo: Estan en el fichero bibliotecas");
                    fichero = consola.readLine();
                    System.out.println(xP.contarLibros(fichero));
                    break;
                case 3:
                    System.out.println("Introduzca fichero con los libros a contar");
                    System.out.println("Consejo: Estan en el fichero bibliotecas");
                    fichero = consola.readLine();
                    System.out.println(xP.listarLibros(fichero));
                    break;
                case 4:
                    System.out.println("Introduzca fichero con los libros a contar");
                    System.out.println("Consejo: Estan en el fichero bibliotecas");
                    fichero = consola.readLine();
                    System.out.println(xP.listarLibrosBajoPedido(fichero));
                    break;
                case 5:
                    System.out.println("Introduzca fichero con los libros a contar");
                    System.out.println("Consejo: Estan en el fichero bibliotecas");
                    fichero = consola.readLine();
                    System.out.println("Introduzca la sentencia XPath");
                    String sentencia = consola.readLine();
                    System.out.println(xP.sentenciaUsuario(fichero, sentencia));
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void sentenciaXQuery() {
        System.out.println("1. Mostrar bibliotecas por orden de facultad");
        System.out.println("2. Mostrar libros cortitos (menores a 450 páginas)");
        System.out.println("3. Ejemplo sentencia predefinida XPath sobre un fichero XML");
        //XQueryOwn xQ = new XQueryOwn();
        int opcion;
        try {
            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
            opcion = Integer.parseInt(consola.readLine());
            switch (opcion) {
                case 1:
                    XQueryOwn.bibliotecasFacultad();
                    break;
                case 2:
                    XQueryOwn.librosCortos();
                    break;
                case 3:
                    XQueryOwn.predefinidaQuery();
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
