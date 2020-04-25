/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_libros;

import com.saxonica.xqj.SaxonXQDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

/**
 *
 * @author aleja
 */
public class XQueryOwn {

    public static void bibliotecasFacultad() {
        String respuesta = "";
        try {
            //Try with different Querys Query_1, Query_2, ...
            File queryFile = new File("xml/Query_1.xqy"); //

            XQDataSource xqjd = new SaxonXQDataSource();
            XQConnection xqjc = xqjd.getConnection();
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(queryFile);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(XQueryOwn.class.getName()).log(Level.SEVERE, null, ex);
            }
            XQPreparedExpression exp = xqjc.prepareExpression(inputStream);
            XQResultSequence result = exp.executeQuery();
            while (result.next()) {
                System.out.println(result.getItemAsString(null));
            }
            result.close();
            exp.close();
            xqjc.close();

        } catch (XQException ex) {
            Logger.getLogger(XQueryOwn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void librosCortos() {
        String respuesta = "";
        try {
            //Try with different Querys Query_1, Query_2, ...
            File queryFile = new File("xml/Query_2.xqy"); //

            XQDataSource xqjd = new SaxonXQDataSource();
            XQConnection xqjc = xqjd.getConnection();
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(queryFile);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(XQueryOwn.class.getName()).log(Level.SEVERE, null, ex);
            }
            XQPreparedExpression exp = xqjc.prepareExpression(inputStream);
            XQResultSequence result = exp.executeQuery();
            while (result.next()) {
                System.out.println(result.getItemAsString(null));
            }
            result.close();
            exp.close();
            xqjc.close();

        } catch (XQException ex) {
            Logger.getLogger(XQueryOwn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void predefinidaQuery() {
        String respuesta = "";
        try {
            //File XML
            File xmlFile = new File("bibliotecas.xml");
            //Create Query
            String query = "doc(\"xml/" + xmlFile + "\")/ContenedorBibliotecas/listaBibliotecas/listaLibros[@autor='Pedro Sanchez']";
            //System.out.println(query);
            XQDataSource xqjd = new SaxonXQDataSource();
            XQConnection xqjc = xqjd.getConnection();
            XQExpression expr = xqjc.createExpression();

            XQResultSequence result = expr.executeQuery(query);
            while (result.next()) {
                respuesta += (result.getItemAsString(null));
            }
            result.close();
            expr.close();
            xqjc.close();
        } catch (XQException ex) {
            System.out.println("Ha habido un error");
        }
        System.out.println(respuesta);
    }

}
