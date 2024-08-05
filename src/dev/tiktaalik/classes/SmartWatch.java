package dev.tiktaalik.classes;

import java.util.ArrayList;

public class SmartWatch {
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

    private static Integer[] clearSteps(Integer[] steps) {
        // Declarar las variables de trabajo y respuesta
        ArrayList<Integer> answer = new ArrayList<>();

        // Iterar la lista de pasos y agregar a la respuesta solo los registros entre 200 y 100.000
        for (Integer step: steps) {
            if (step > 200 && step < 100000) {
                answer.add(step);
            }
        }

        // Retornar la respuesta
        return answer.toArray(new Integer[answer.size()]);
    }


    public static void main(String[] args) {
        // Crear variable para almacenar en una lista los argumentos de la línea de comandos
        ArrayList<Integer> daily_steps = new ArrayList<Integer>(args.length);

        // Crear una variable de trabajo y otra de respuesta
        float average;
        Integer[] filtered_steps = new Integer[0];
        String answer;

        // Definir los mensajes al usuario
        String title_msg = "\n\nCalculando el promedio de pasos de un smartwatch.\n" +
                "----------------------------------------------------\n\n";
        String answer_msg = "Para la entrada anterior, el resultado es %.2f.";
        String end_msg = "\n\nEl programa ha terminado. Adios.";

        // Convertir argumentos de la línea de comandos en 'Integer'
        for (int i = 0; i < args.length; i++) {
            daily_steps.add(Integer.parseInt(args[i]));
        }

        // Filtrar los pasos fuera de rango
        filtered_steps = clearSteps(daily_steps.toArray(new Integer[daily_steps.size()]));

        // Calcular el promedio
        average = promedio(filtered_steps);

        // Construir la respuesta
        answer = answer_msg.formatted(average);

        // Imprimir mensajes del título, respuesta, y fin del programa
        System.out.printf("%s%s%s", title_msg, answer, end_msg);
    }
}
