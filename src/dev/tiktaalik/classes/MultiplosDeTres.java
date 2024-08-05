package dev.tiktaalik.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class MultiplosDeTres {
    private static Integer[] filter_multiples(Integer[] num_array) {
        // Declarar las variables de trabajo y respuesta
        ArrayList<Integer> answer = new ArrayList<>();

        // Iterar la lista de pasos y agregar a la respuesta solo los registros que són múltiplos de 3
        for (Integer number: num_array) {
            if (number %3 == 0) {
                answer.add(number);
            }
        }

        // Retornar la respuesta
        return answer.toArray(new Integer[answer.size()]);
    }

    private static Integer suma(Integer[] num_array) {
        // Definir la variable de respuesta
        Integer sum;

        // Calcular la suma de los elementos del array
        sum = Arrays.stream(num_array).mapToInt(Integer::intValue).sum();

        // Retornar la respuesta
        return sum;
    }

    private static float promedio(Integer[] num_array) {
        // Definir variable acumuladora y de respuesta
        Integer sum;
        float answer;

        // Calcular la 'suma' de los elementos del array
        sum = suma(num_array);

        // Calcular el promedio
        answer = (float) sum / num_array.length;

        // Retornar la respuesta
        return answer;
    }


    public static void main(String[] args) {
        // Crear variable para almacenar en una lista los argumentos de la línea de comandos
        ArrayList<Integer> num_list = new ArrayList<Integer>(args.length);

        // Crear una variable de trabajo y otra de respuesta
        int sum;
        float average;
        Integer[] filtered_list = new Integer[0];
        String sum_msg;
        String avg_msg;
        String answer;

        // Definir los mensajes al usuario
        String title_msg = "\n\nCalculando la suma y el promedio de una lista de números.\n" +
                "--------------------------------------------------------\n\n";
        String sum_msg_base = "Para la entrada anterior, el resultado de la suma es %d.";
        String avg_msg_base = "Para la entrada anterior, el resultado del promedio es %.2f.";
        String answer_msg = "\n\n%s\n%s\n\n";
        String end_msg = "\n\nEl programa ha terminado. Adios.";
        String output_msgs = "%s%s%s";

        // Convertir argumentos de la línea de comandos en 'Integer'
        for (int i = 0; i < args.length; i++) {
            num_list.add(Integer.parseInt(args[i]));
        }

        // Filtrar los pasos fuera de rango
        filtered_list = filter_multiples(num_list.toArray(new Integer[num_list.size()]));

        // Calcular el promedio
        sum = suma(filtered_list);

        // Calcular el promedio
        average = promedio(filtered_list);

        // Construir la respuesta
        sum_msg = sum_msg_base.formatted(sum);
        avg_msg = avg_msg_base.formatted(average);
        answer = answer_msg.formatted(sum_msg, avg_msg);

        // Imprimir mensajes del título, respuesta, y fin del programa
        System.out.printf(output_msgs, title_msg, answer, end_msg);
    }
}
