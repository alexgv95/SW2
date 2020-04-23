/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_libros;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author aleja
 */
public class CustomErrorHandler implements ErrorHandler {

    Boolean errorProduced = Boolean.FALSE;

    public Boolean isValid() {
        return !this.errorProduced;
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exception);
        errorProduced = Boolean.TRUE;
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exception);
        errorProduced = Boolean.TRUE;
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exception);
        errorProduced = Boolean.TRUE;
    }
}

