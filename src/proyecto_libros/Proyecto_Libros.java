/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_libros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Alejandro <agutierrezvivancos@gmail.com>
 * @author Javier <javier@sancerni.es>
 */
public class Proyecto_Libros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion = 0;
        Biblioteca bib;
        GestionBiblioteca.unmarshallingBiblioteca();
        System.out.println("[Cargando bibliotecas de la base de datos ...]\n\n");
        do {
            System.out.println("--MENÚ PRINCIPAL--");
            System.out.println("1. Crear biblioteca");
            System.out.println("2. Añadir libro a una biblioteca");
            System.out.println("3. Listar bibliotecas existentes");
            System.out.println("4. Exportar biblioteca a archivo XML");
            System.out.println("5. Importar biblioteca desde archivo XML");
            System.out.println("6. Exportar un libro de la biblioteca a XML");
            System.out.println("7. Importar un libro de XML a biblioteca");
            System.out.println("8. Validar un archivo XML con la DTD");

            System.out.println("\n\tIntroduzca la opción: ");

            try {
                BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
                opcion = Integer.parseInt(consola.readLine());
            } catch (IOException ex) {
                System.out.println(ex);
            }
            switch (opcion) {
                case 1:
                    GestionBiblioteca.crearBiblioteca();
                    break;
                case 2:
                    GestionBiblioteca.listarBibliotecas();
                    GestionBiblioteca.anadirLibro();
                    break;
                case 3:
                    GestionBiblioteca.listarBibliotecas();
                    break;
                case 4:
                    GestionBiblioteca.marshallingBiblioteca();
                    break;
                case 5:
                    GestionBiblioteca.unmarshallingBiblioteca();
                    break;
                case 6:
                    GestionBiblioteca.marshallingLibro();
                    break;
                case 7:
                    GestionBiblioteca.unmarshallingLibro();
                    break;
                case 8:
                    GestionBiblioteca.validarDTD();
                    break;
                default:
                    System.out.println("Opcion errónea");
                    break;
            }
        } while (opcion > 0);

    }

}
