/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_libros;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Alex
 * @author Javivi
 */
public class XPath {

    public String mostrarBibliotecas(String fichero) {
        String respuesta = "";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("xml/" + fichero + ".xml");
            XPathFactory xPathfactory = XPathFactory.newInstance();
            javax.xml.xpath.XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//listaBibliotecas/@facultad");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodes.getLength(); i++) {
                //System.out.println(nodes.item(i).getNodeValue());
                respuesta += i + ": " + (nodes.item(i).getNodeValue()) + "\n";
            }
        } catch (SAXException | ParserConfigurationException ex) {
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en el parseado del fichero");
        } catch (XPathExpressionException ex) {
            System.out.println("Error en la expresion Xpath");
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error al acceder al fichero");
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public String contarLibros(String fichero) {
        String respuesta = "";
        Number result = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("xml/" + fichero + ".xml");
            XPathFactory xPathfactory = XPathFactory.newInstance();
            javax.xml.xpath.XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("count(//listaBibliotecas/listaLibros/@titulo)");
            result = (Number) expr.evaluate(doc, XPathConstants.NUMBER);

            respuesta = result.toString();

        } catch (SAXException | ParserConfigurationException ex) {
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en el parseado del fichero");
        } catch (XPathExpressionException ex) {
            System.out.println("Error en la expresion Xpath");
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error al acceder al fichero");
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public String listarLibros(String fichero) {
        String respuesta = "";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("xml/" + fichero + ".xml");
            XPathFactory xPathfactory = XPathFactory.newInstance();
            javax.xml.xpath.XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//listaBibliotecas[@ciudad='Madrid']/listaLibros/@titulo");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodes.getLength(); i++) {
                //System.out.println(nodes.item(i).getNodeValue());
                respuesta += i + ": " + (nodes.item(i).getNodeValue()) + "\n";
            }
        } catch (SAXException | ParserConfigurationException ex) {
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en el parseado del fichero");
        } catch (XPathExpressionException ex) {
            System.out.println("Error en la expresion Xpath");
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error al acceder al fichero");
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public String listarLibrosBajoPedido(String fichero) {
        String respuesta = "";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("xml/" + fichero + ".xml");
            XPathFactory xPathfactory = XPathFactory.newInstance();
            javax.xml.xpath.XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//listaBibliotecas/listaLibros[@disponibilidad='BAJO_PEDIDO']/@autor");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodes.getLength(); i++) {
                //System.out.println(nodes.item(i).getNodeValue());
                respuesta += i + ": " + (nodes.item(i).getNodeValue()) + "\n";
            }
        } catch (SAXException | ParserConfigurationException ex) {
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en el parseado del fichero");
        } catch (XPathExpressionException ex) {
            System.out.println("Error en la expresion Xpath");
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error al acceder al fichero");
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    String sentenciaUsuario(String fichero, String sentencia) {
        String respuesta = "";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("xml/" + fichero + ".xml");
            XPathFactory xPathfactory = XPathFactory.newInstance();
            javax.xml.xpath.XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile(sentencia);
            respuesta = (String) expr.evaluate(doc, XPathConstants.STRING);
        } catch (SAXException ex) {
            System.out.println("Error de los otros");
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error al acceder al archivo");
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            System.out.println("Error en la expresion introducida, quiza es mejor que uses las predefinidas");
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            System.out.println("Error del parser");
            //Logger.getLogger(XPath.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
}
