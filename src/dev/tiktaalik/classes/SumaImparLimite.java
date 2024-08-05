package dev.tiktaalik.classes;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SumaImparLimite {
    public static void main(String[] args) {

        // Crear un objeto Scanner para leer texto desde la consola
        Scanner console = new Scanner(System.in, StandardCharsets.UTF_8);

        // Definir los mensajes para el usuario
        String title_msg = "\n\nImprimir La Suma De Un Rango De Números Impares\n" +
                "-----------------------------------------------\n\n";
        String inst_1 = "Imprimamos la suma de un rango de números impares, según lo que solicite el usuario.\n\n";
        String inst_2 = "Ingresa el límite inferior del rango: ";
        String inst_3 = "Ingresa el límite superior del rango: ";
        String sum_msg = "\n\nLa suma de los %d números impares que solicitaste es: %d\n\n";
        String end_msg = "\n\nEl programa ha terminado. Adios.";

        // Imprimir encabezado y primera instrucción
        System.out.printf("%s%s%s", title_msg, inst_1, inst_2);

        // Leer la cantidad de números desde la consola
        String lower_limit = console.next();

        // Imprimir tercera instrucción
        System.out.printf(inst_3);

        // Leer la cantidad de números desde la consola
        String upper_limit = console.next();

        // Iterar y acumular los números
        int num_sum = 0;
        int first_iter = Integer.parseInt(lower_limit);
        int max_iter = Integer.parseInt(upper_limit);
        for (int i = first_iter; i <= max_iter; i++) {
            if (i % 2 != 0) {
                num_sum += i;
            }
        }

        // Imprimir encabezado de la lista
        System.out.printf(sum_msg, max_iter / 2, num_sum);

        // Imprimir el mensaje final y terminar el programa
        System.out.println(end_msg);
    }
}
