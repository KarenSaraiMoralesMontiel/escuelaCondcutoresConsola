package gov.peru.escuela.conducir.consola;

import gov.peru.escuela.conducir.bean.Escuela;
import gov.peru.escuela.conducir.exception.EscuelaException;
import gov.peru.escuela.conducir.persistencia.EscuelaDBImpl;
import gov.peru.escuela.conducir.util.EscuelaTabla;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EscuelaDBImpl escuelaDB = new EscuelaDBImpl();

        // General
        String welcome = "\n¡Bienvenido a la aplicación de consola para el manejo de escuelas de manejo!";
        String final_msg = "¡Que tenga un bonito día!";
        String prompt = "Seleccione una opción del menú: ";
        String menu = """
        \nMENÚ
        1. Mostrar las Escuelas de Manejo
        2. Insertar una Escuela de Manejo
        3. Salir""";

        String select_valid = "Por favor, seleccione una opción válida";
        String prompt_continue = "Presione Enter para continuar...";

        String option;
        Scanner s = new Scanner(System.in);

        System.out.println(welcome);
        do {
            System.out.println(menu);
            System.out.print(prompt);
            option = s.nextLine();
            switch (option) {
                case "1": {
                    try {
                        // If your findAll method accepts limit parameter
                        List<Escuela> escuelas = escuelaDB.findAll();
                        EscuelaTabla tabla = new EscuelaTabla();
                        String CompleteTabla = tabla.escuelaTabla(escuelas);
                        System.out.println(CompleteTabla);
                        System.out.println(prompt_continue);
                        s.nextLine();
                    } catch (EscuelaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "2": {
                    try {
                        System.out.print("Ingrese el no ruc (tiene que ser 11 caracteres): ");
                        String no_ruc = s.nextLine();

                        System.out.print("Ingrese la razon social: ");
                        String nombre_centro = s.nextLine();

                        System.out.print("Ingrese la direccion: ");
                        String direccion_centro = s.nextLine();

                        System.out.print("Ingrese el email: ");
                        String email = s.nextLine();

                        System.out.print("Ingrese el numero: ");
                        String numero = s.nextLine();

                        System.out.print("Ingrese el id_distrito (debe ser el id_ubigeo del distrito: ");
                        String id_distrito = s.nextLine();

                        Escuela newEscuela = new Escuela(no_ruc, nombre_centro, direccion_centro, true, email, numero, id_distrito);
                        boolean result = escuelaDB.insertEscuela(newEscuela);
                        System.out.println(result ?  "Insercción exitosa" :
                        "Ha habido un error, por favor intentelo de nuevo");

                    } catch (Exception e) {
                        System.out.println("Error al insertar escuela: " + e.getMessage());
                    }
                }
                    break;
                case "3":
                    break;
                default:
                    System.out.println(select_valid);
                    System.out.println(prompt_continue);
                    s.nextLine();
                    break;
            }
        } while (!option.equals("3"));
        System.out.println(final_msg);
    }
}