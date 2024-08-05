package dev.tiktaalik.classes;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Patrones {
    public static void main(String[] args) {

        // Crear un objeto Scanner para leer texto desde la consola
        Scanner console = new Scanner(System.in, StandardCharsets.UTF_8);

        // Definir los mensajes para el usuario
        String title_msg = "\n\nImprimir Diseños de Patrones de Texto.\n" +
                "--------------------------------------\n\n";
        String choice_1 = "*.";
        String choice_2 = "1234";
        String choice_3 = "||*";
        String inst_1 = "Los siguientes patrones de texto están disponibles:\n\n1. %s\n2. %s\n3. %s\n\n".
                formatted(choice_1, choice_2, choice_3);
        String inst_2 = "Ingresa el número de tu opción preferida: ";
        String inst_3 = "Ingresa el número de caracteres que deseas: ";
        String error_msg_1 = "La opción ingresada no es válida. Por favor, elige un número del menú.\n";
        String error_msg_2 = "La cantidad de caracteres ingresada no es válida. " +
                "Por favor, elige un número mayor a cero (0).\n";
        String header_msg = "\n\nAquí está tu patrón seleccionado, con la cantidad de %s caracteres que pediste:\n\n";
        String end_msg = "\n\nEl programa ha terminado. Adios.";

        // Imprimir título y primeras dos instrucciones
        System.out.printf("%s%s%s", title_msg, inst_1, inst_2);

        // Leer la opción seleccionada del menú desde la consola. Si el usuario ingresa un número no disponible,
        // retornar un mensaje de error y volver a preguntar hasta que ingrese un número válido
        String choice_num = console.next();
        boolean valid_choice = choice_num.matches("^[123]$");
        if (!valid_choice) {
            while (!valid_choice) {
                System.out.printf("%s%s", error_msg_1, inst_2);
                choice_num = console.next();
                valid_choice = choice_num.matches("^[123]$");
            }
        }

        // Imprimir tercera instrucción
        System.out.printf(inst_3);

        // Leer la cantidad de caracteres deseados desde la consola. Si el usuario ingresa un múmero menor que 1,
        // retornar un mensaje de error y volver a preguntar hasta que ingrese un número válido
        String char_num = console.next();
        boolean valid_chars = Integer.parseInt(char_num) > 0;
        if (!valid_chars) {
            while (!valid_chars) {
                System.out.printf("%s%s", error_msg_2, inst_3);
                choice_num = console.next();
                valid_chars = Integer.parseInt(char_num) > 0;
            }
        }

        // Imprimir encabezado de la lista
        System.out.printf(header_msg, char_num);

        // Definir el patrón seleccionado y separar los caracteres en elementos de un array
        char[] pattern = new char[0];
        switch (choice_num) {
            case "1" -> pattern = choice_1.toCharArray();
            case "2" -> pattern = choice_2.toCharArray();
            case "3" -> pattern = choice_3.toCharArray();
        }

        // Iterar y construir el patrón de respuesta
        StringBuilder answer = new StringBuilder();
        int max_iter = Integer.parseInt(char_num);
        for (int i = 0; i < max_iter; i++) {
            // Usar el cálculo del módulo entre la iteración y el largo del array del patrón para sincronizar el loop
            // con los elementos del patrón
            answer.append(pattern[i % pattern.length]);
        }
        // Imprimir el patrón en una sola operación
        System.out.println(answer.toString());

        // Imprimir el mensaje final y terminar el programa
        System.out.println(end_msg);
    }
}
