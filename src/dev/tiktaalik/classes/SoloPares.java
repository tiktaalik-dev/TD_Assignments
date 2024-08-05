package dev.tiktaalik.classes;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SoloPares {
    public static void main(String[] args) {

        // Crear un objeto Scanner para leer texto desde la consola
        Scanner console = new Scanner(System.in, StandardCharsets.UTF_8);

        // Definir los mensajes para el usuario
        String title_msg = "\n\nImprimir Números Pares Desde el Cero\n" +
                "------------------------------------\n\n";
        String inst_1 = "Imprimamos los primeros números pares, incluyendo el cero, según lo que solicite el usuario.\n\n";
        String inst_2 = "Ingresa la cantidad de números pares que deseas ver: ";
        String header_msg = "\n\nEstos son los %s números pares que solicitaste:\n" +
                "-----------------------------------------------\n";
        String end_msg = "\n\nEl programa ha terminado. Adios.";

        // Imprimir encabezado y primera instrucción
        System.out.printf("%s%s%s", title_msg, inst_1, inst_2);

        // Leer la cantidad de números desde la consola
        String num_amount = console.next();

        // Imprimir encabezado de la lista
        System.out.printf(header_msg, num_amount);

        // Iterar e imprimir los números
        int max_iter = Integer.parseInt(num_amount) * 2;
        for (int i = 0; i < max_iter; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }

        // Imprimir el mensaje final y terminar el programa
        System.out.println(end_msg);
    }
}
