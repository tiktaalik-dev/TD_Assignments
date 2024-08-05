package dev.tiktaalik.classes;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class PatronesAnidados {

    private static String buildPattern(String choice, String num_chars) {

        // Definir la variable de respuesta y la del tipo de caracter requerido
        String answer = "";
        char letter;

        // Si el usuario pidió solo 1 ó 2 caracteres, el patrón es simple
        if (Integer.parseInt(num_chars) <= 2) {
            // Si el patrón deseado es el Nº3, la letra debe ser una 'X'. Caso contrario siempre es '*'
            letter = Objects.equals(choice, "3") ? 'X' : '*';

            // Llamar al método para patrones simples
            answer = simplePattern(num_chars, letter, choice);
        }
        // Para más de 3 caracteres, el patrón es más complejo
        else {
            // Construir el patrón seleccionado, delegando la tarea al método específico
            switch (choice) {
                case "1" -> answer = pattern1(num_chars);
                case "2" -> answer = pattern2(num_chars);
                case "3" -> answer = pattern3(num_chars);
                case "4" -> answer = pattern4(num_chars);
            }
        }

        // Retornar el patrón completamente formado
        return answer;
    }

    private static String simplePattern(String num_chars, char letter, String choice) {

        // Definir la variable de respuesta
        StringBuilder answer = new StringBuilder();

        // El primer caracter es común para 1 ó 2 líneas
        answer.append(letter);

        // Para dos líneas agregar más caracteres
        if (Objects.equals(num_chars, "2")) {

            // Crear una plantilla
            String base_pattern = "%s\n%s%s\n";

            // El patrón Nª4 es un caso especial
            if (Objects.equals(choice, "4")) {
                answer.append(base_pattern.formatted(" ", " ", letter));
            }
            else {
                answer.append(base_pattern.formatted(letter, letter, letter));
            }
        }

        return answer.toString();
    }

    private static String complexPattern(String num_chars, String choice) {

        // Definir variables de respuesta y trabajo
        StringBuilder answer = new StringBuilder();
        char letter;
        char blank = ' ';
        char current_char;
        StringBuilder first_line = new StringBuilder();
        StringBuilder last_line = new StringBuilder();
        StringBuilder inner_lines = new StringBuilder();
        int max_chars = Integer.parseInt(num_chars);

        // Crear primera línea. Si el patrón deseado es el Nº3, la letra debe ser una 'X'.
        // Caso contrario siempre es '*'
        letter = Objects.equals(choice, "3") ? 'X' : '*';

        // Crear la primera línea. Los patrones 3 y 4 son casos especiales
        if (Objects.equals(choice, "3")) {
            first_line.append(letter);
            first_line.repeat(' ', max_chars - 2);
            first_line.append(letter);
        }
        else if (Objects.equals(choice, "4")) {
            first_line.repeat(letter, max_chars - 1);
        }
        else {
            first_line.repeat(letter, max_chars);
        }
        // Agregar un salto de línea
        first_line.append('\n');

        // Agregar la primera línea a la respuesta
        answer.append(first_line.toString());

        // Crear las líneas interiores del patrón
        int max_iter = max_chars - 1;

        for (int i = 1; i < max_iter; i++) {
            // Definir el caracter a insertar. Por defecto es igual a 'blank'
            current_char = blank;

            // Cambiar el valor de 'current_char' según sea necesario para cada patrón
            // El patrón 3 es un caso especial que requiere otro rango de iteración
            if (Objects.equals(choice, "3")) {
                int max_j_iter = max_iter - 1;
                for (int j = 0; j <= max_j_iter; j++) {
                    // Agregar el caracter que corresponde al patrón solo cuando corresponde.
                    // Caso contrario, agregar un espacio en blanco
                    if (j == i || j == max_iter - i) {
                        current_char = letter;
                    }

                    // Agregar el caracter a la respuesta
                    inner_lines.append(current_char);

                    // Devolver el valor por defecto de 'current_char'
                    current_char = blank;
                }
            }
            else {
                // Los otros patrones usan el rango habitual
                for (int j = 0; j <= max_iter; j++) {
                    // El patrón 1 solo imprime el caracter cuando es el primer o el último de la fila
                    if (Objects.equals(choice, "1") && (j == 0 || j == max_iter)) {
                        current_char = letter;
                    }
                    // El patrón 2 solo imprime el caracter en una posición, que va disminuyendo cada vez
                    else if (Objects.equals(choice, "2") && j == max_iter - i) {
                        current_char = letter;
                    }
                    // El patrón 4 imprime el caracter siempre que no sea en la primera posición
                    else if (Objects.equals(choice, "4") && (j != 0 && j != max_iter)) {
                        current_char = letter;
                    }
                    // Agregar el caracter a la respuesta
                    inner_lines.append(current_char);

                    // Devolver el valor por defecto de 'current_char'
                    current_char = blank;
                }
            }
            // Agregar un salto de línea antes de la siguiente iteración
            inner_lines.append('\n');
        }

        // Crear la última línea. El patrón 4 es un caso especial
        if (Objects.equals(choice, "4")) {
            last_line.append(' ');
            last_line.repeat(letter, max_chars - 1);
        }
        else {
            last_line = first_line;
        }

        // Construir la respuesta
        answer.append(inner_lines.toString());
        answer.append(last_line.toString());
        answer.append("\n");

        // Retornar la respuesta
        return answer.toString();
    }

    private static String pattern1(String num_chars) {

        // Definir la variable de respuesta
        String answer;

        // Seleccionar la respuesta adecuada según el número de caracteres solicitados
        if (Integer.parseInt(num_chars) <= 2) {
            answer = simplePattern(num_chars, '*', "1");
        }
        else {
            answer = complexPattern(num_chars, "1");
        }

        // Retornar respuesta
        return answer;
    }


    private static String pattern2(String num_chars) {
        // Definir la variable de respuesta
        String answer;

        // Seleccionar la respuesta adecuada según el número de caracteres solicitados
        if (Integer.parseInt(num_chars) <= 2) {
            answer = simplePattern(num_chars, '*', "2");
        }
        else {
            answer = complexPattern(num_chars, "2");
        }

        // Retornar respuesta
        return answer;
    }

    private static String pattern3(String num_chars) {
        // Definir la variable de respuesta
        String answer;

        // Seleccionar la respuesta adecuada según el número de caracteres solicitados
        if (Integer.parseInt(num_chars) <= 2) {
            answer = simplePattern(num_chars, 'X', "3");
        }
        else {
            answer = complexPattern(num_chars, "3");
        }

        // Retornar respuesta
        return answer;
    }

    private static String pattern4(String num_chars) {
        // Definir la variable de respuesta
        String answer;

        // Seleccionar la respuesta adecuada según el número de caracteres solicitados
        if (Integer.parseInt(num_chars) <= 2) {
            answer = simplePattern(num_chars, '*', "4");
        }
        else {
            answer = complexPattern(num_chars, "4");
        }

        // Retornar respuesta
        return answer;
    }

    public static void main(String[] args) {

        // Crear un objeto Scanner para leer texto desde la consola
        Scanner console = new Scanner(System.in, StandardCharsets.UTF_8);

        // Definir los mensajes para el usuario
        String title_msg = "\n\nImprimir Diseños Complejos de Patrones de Texto.\n" +
                "--------------------------------------\n\n";
        String choice_1 = "Cajas";
        String choice_2 = "Z";
        String choice_3 = "X";
        String choice_4 = "l";
        String inst_1 = "Los siguientes patrones de texto están disponibles:\n\n1. %s\n2. %s\n3. %s\n4. %s\n\n".
                formatted(choice_1, choice_2, choice_3, choice_4);
        String inst_2 = "Ingresa el número de tu opción preferida: ";
        String inst_3 = "Ingresa el número de caracteres que deseas: ";
        String error_msg_1 = "La opción ingresada no es válida. Por favor, elige un número del menú.\n";
        String error_msg_2 = "La cantidad de caracteres ingresada no es válida. " +
                "Por favor, elige un número mayor a cero (0).\n";
        String header_msg = "\n\nAquí está tu patrón seleccionado, con la cantidad de %s caracteres y %s líneas que pediste:\n\n";
        String end_msg = "\n\nEl programa ha terminado. Adios.";

        // Imprimir título y primeras dos instrucciones
        System.out.printf("%s%s%s", title_msg, inst_1, inst_2);

        // Leer la opción seleccionada del menú desde la consola. Si el usuario ingresa un número no disponible,
        // retornar un mensaje de error y volver a preguntar hasta que ingrese un número válido
        String choice_num = console.next();
        boolean valid_choice = choice_num.matches("^[1234]$");
        if (!valid_choice) {
            while (!valid_choice) {
                System.out.printf("%s%s", error_msg_1, inst_2);
                choice_num = console.next();
                valid_choice = choice_num.matches("^[1234]$");
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
        System.out.printf(header_msg, char_num, char_num);

        // Construir la respuesta
        String answer = buildPattern(choice_num, char_num);

        // Imprimir el patrón en una sola operación
        System.out.println(answer);

        // Imprimir el mensaje final y terminar el programa
        System.out.println(end_msg);
    }
}
