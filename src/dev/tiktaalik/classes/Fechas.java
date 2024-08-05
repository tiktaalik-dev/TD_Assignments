// Crear Namespace
package dev.tiktaalik.classes;

// Importar librerías requeridas
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// Crear clase principal
public class Fechas {

    // Crear método principal
    public static void main(String[] args) {

        // Crear un objeto Scanner para leer texto desde la consola
        Scanner console = new Scanner(System.in, StandardCharsets.UTF_8);

        // Definir los mensajes para el usuario
        String title_msg = "\n\n¿Quién es el mayor?\n-------------------\n\n";
        String inst_1 = "¡Calculemos quién es el mayor entre dos personas según su fecha de nacimiento!.\n\n";
        String inst_base = "Ingresa la %s fecha de nacimiento (como DD/MM/AAAA): ";
        String first = "primera";
        String second = "segunda";
        String inst_first = inst_base.formatted(first);
        String inst_second = inst_base.formatted(second);
        String base_msg = "\n\nLa persona %s %s que la persona %s.\n\nEl programa ha terminado. Adios.";
        String older = "es mayor";
        String same = "tiene la misma edad";
        String older_1 = base_msg.formatted("1", older, "2");
        String older_2 = base_msg.formatted("2", older, "1");
        String same_age = base_msg.formatted("1", same, "2");

        // Imprimir encabezado y primera instrucción
        System.out.printf("%s%s%s", title_msg, inst_1, inst_first);

        // Leer primera fecha desde la consola
        String date_1 = console.next();

        // Imprimir segunda instrucción
        System.out.print(inst_second);

        // Leer segunda fecha desde la consola
        String date_2 = console.next();

        // Extraer día, mes y año desde las fechas ingresadas por el usuario
        int day_1 = Integer.parseInt(date_1.substring(0, 2));
        int month_1 = Integer.parseInt(date_1.substring(3, 5));
        int year_1 = Integer.parseInt(date_1.substring(6, 10));
        int day_2 = Integer.parseInt(date_2.substring(0, 2));
        int month_2 = Integer.parseInt(date_2.substring(3, 5));
        int year_2 = Integer.parseInt(date_2.substring(6, 10));

        // Crear variables para las comparaciones y el mensaje
        int equal_day = 0;
        int equal_month = 0;
        int equal_year = 0;
        String decision = "";

        // Comparar fechas y decidir
        // Primero comparar años
        if (year_1 > year_2) {
            decision = older_2;
        } else if (year_2 > year_1) {
            decision = older_1;
        } else {
            equal_year = 1;
        }

        // Si el año es el mismo, comparar el mes
        if (equal_year == 1) {
            if (month_1 > month_2) {
                decision = older_2;
            } else if (month_2 > month_1) {
                decision = older_1;
            } else {
                equal_month = 1;
            }
        }

        // Si el mes es el mismo, comparar el día
        if (equal_month == 1) {
            if (day_1 > day_2) {
                decision = older_2;
            } else if (day_2 > day_1) {
                decision = older_1;
            } else {
                equal_day = 1;
            }
        }

        // Si el día también coincide, decidir que tienen la misma edad
        if (equal_day == 1) {
            decision = same_age;
        }

        // Imprimir la decisión y terminar el programa
        System.out.println(decision);
    }
}
