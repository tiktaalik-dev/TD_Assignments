package dev.tiktaalik.classes;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {

        // Crear un objeto Scanner para leer texto desde la consola
        Scanner console = new Scanner(System.in, StandardCharsets.UTF_8);

        // Definir los mensajes para el usuario
        String title_msg = "\n\nImprimir Números De La Secuencia De Fibonacci.\n" +
                "----------------------------------------------\n\n";
        String inst_1 = "Imprimamos los números de la secuencia de Fibonacci, según lo que solicite el usuario.\n\n";
        String inst_2 = "Ingresa la cantidad de números de la secuencia que deseas ver: ";
        String header_msg = "\n\nEstos son los %s números de la secuencia que solicitaste:\n" +
                "---------------------------------------------------------\n";
        String end_msg = "\n\nEl programa ha terminado. Adios.";

        // Imprimir encabezado y primera instrucción
        System.out.printf("%s%s%s", title_msg, inst_1, inst_2);

        // Leer la cantidad de números desde la consola
        String num_amount = console.next();

        // Imprimir encabezado de la lista
        int max_iter = Integer.parseInt(num_amount);
        System.out.printf(header_msg, max_iter + 1);

        // Iterar y calcular los números
        ArrayList <Integer> sequence = new ArrayList<Integer>();
        for (int i = 0; i <= max_iter; i++) {
            int seq_num;
            if (i > 1){
                seq_num = sequence.getLast() + sequence.get(sequence.size() - 2);
            } else if (i == 1) {
                seq_num = 1;
            } else {
                seq_num = 0;
            }
            sequence.add(seq_num);

            // Imprimir el número calculado de la secuencia
            System.out.println(seq_num);
        }

        // Imprimir el mensaje final y terminar el programa
        System.out.println(end_msg);
    }
}
