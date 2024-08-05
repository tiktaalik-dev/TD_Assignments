package dev.tiktaalik.classes;

import java.util.ArrayList;

public class Visitas {
    private static float promedio(Integer[] visitas) {
        // Definir variable acumuladora y de respuesta
        int sum = 0;
        float answer;

        // Iterar en 'visitas' y acumular la sumatoria
        for (Integer visita : visitas) {
            sum += visita;
        }

        // Calcular el promedio
        answer = (float) sum / visitas.length;

        // Retornar la respuesta
        return answer;
    }


    public static void main(String[] args) {
        // Crear variable para almacenar en una lista los argumentos de la línea de comandos
        ArrayList<Integer> visits = new ArrayList<Integer>(args.length);

        // Crear una variable de trabajo y otra de respuesta
        float average;
        String answer;

        // Definir los mensajes al usuario
        String title_msg = "\n\nCalculando el promedio de visitas diarias a un sitio web.\n" +
                "---------------------------------------------------------\n\n";
        String answer_msg = "Para la entrada anterior, el resultado es %.2f.";
        String end_msg = "\n\nEl programa ha terminado. Adios.";

        // Convertir argumentos de la línea de comandos en 'Integer'
        for (int i = 0; i < args.length; i++) {
            visits.add(Integer.parseInt(args[i]));
        }

        // Calcular el promedio
        average = promedio(visits.toArray(new Integer[args.length]));

        // Construir la respuesta
        answer = answer_msg.formatted(average);

        // Imprimir mensajes del título, respuesta, y fin del programa
        System.out.printf("%s%s%s", title_msg, answer, end_msg);
    }
}
