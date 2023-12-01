package principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.*;


public class ProcesarExpresionRegular {public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Ingrese el nombre del archivo: ");
    String archivo = scanner.nextLine();
    
    System.out.print("Ingrese la expresión regular: ");
    String expresionRegular = scanner.nextLine();
    
    System.out.print("¿Desea extraer grupos? (Sí/No): ");
    String opcionGrupos = scanner.nextLine();
    
    if (opcionGrupos.equalsIgnoreCase("Sí")) {
        procesarExpresionRegularConGrupos(archivo, expresionRegular);
    } else {
        procesarExpresionRegularSimple(archivo, expresionRegular);
    }

    
    scanner.close();
}
	

	    private static void procesarExpresionRegularSimple(String archivo, String expresionRegular) {
	        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
	            String linea;
	            int numeroLinea = 1;

	            while ((linea = br.readLine()) != null) {
	                if (linea.matches(expresionRegular)) {
	                    System.out.println(numeroLinea + ": Coincide");
	                } else {
	                    System.out.println(numeroLinea + ": No coincide");
	                }
	                numeroLinea++;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void procesarExpresionRegularConGrupos(String archivo, String expresionRegular) {
	        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
	            String linea;
	            int numeroLinea = 1;

	            Pattern patron = Pattern.compile(expresionRegular);

	            while ((linea = br.readLine()) != null) {
	                Matcher matcher = patron.matcher(linea);
	               
	                if (matcher.matches()) {
	                    System.out.println(numeroLinea + ": Coincide");

	                    // Imprimir grupos extraídos
	                    for (int i = 1; i <= matcher.groupCount(); i++) {
	                        System.out.println("Grupo " + i + ": " + matcher.group(i));
	                    }
	                } else {
	                    System.out.println(numeroLinea + ": No coincide");
	                }
	                numeroLinea++;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	