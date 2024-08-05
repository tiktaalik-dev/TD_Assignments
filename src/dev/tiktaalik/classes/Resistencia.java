package dev.tiktaalik.classes;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Resistencia {
    public static void main(String[] args) {

        // Crear un objeto Scanner para leer texto desde la consola
        Scanner console = new Scanner(System.in, StandardCharsets.UTF_8);

        // Definir los mensajes para el usuario
        String title_msg = "\n\nResistencia total de un circuito paralelo\n--------------------------------\n\n";
        String inst_1 = "¡Calculemos la resistencia total de un circuito que incluye 3 resistencias!.\n\n";
        String inst_base = "Ingresa la resistencia %s: ";
        String first = "R1";
        String second = "R2";
        String third = "R3";
        String inst_first = inst_base.formatted(first);
        String inst_second = inst_base.formatted(second);
        String inst_third = inst_base.formatted(third);
        String base_msg = "\n\nLa resistencia total es de %f Ohm.\n\nEl programa ha terminado. Adios.";

        // Imprimir encabezado y primera instrucción
        System.out.printf("%s%s%s", title_msg, inst_1, inst_first);

        // Leer primera resistencia desde la consola
        double value_R1 = Double.parseDouble(console.next());

        // Imprimir segunda instrucción
        System.out.print(inst_second);

        // Leer segunda resistencia desde la consola
        double value_R2 = Double.parseDouble(console.next());

        // Imprimir tercera instrucción
        System.out.print(inst_third);

        // Leer tercera resistencia desde la consola
        double value_R3 = Double.parseDouble(console.next());

        // Calcular la resistencia total
        double value_RT = (1 / (1/value_R1 + 1/value_R2 + 1/value_R3));

        // Imprimir la Resistencia Total calculada y terminar el programa
        System.out.printf(base_msg, value_RT);
    }
}
