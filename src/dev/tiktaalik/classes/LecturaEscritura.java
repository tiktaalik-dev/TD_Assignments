package dev.tiktaalik.classes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LecturaEscritura {
    // Crear variables de clase
    static String user_directory = System.getProperty("user.dir");
    static String separator = File.separator;
    static String src_directory = "%s%ssrc%s".formatted(user_directory, separator, separator);
    static String new_line = System.lineSeparator();
    static String error_msg;
    static String exc_cause;

    private static String[] crearArchivo(String directorio, String fichero) {
        // Crear variables de trabajo y respuesta
        String folder_path = "%s%s".formatted(src_directory, directorio);
        String file_path = "%s%s%s".formatted(folder_path, separator, fichero);
        File folder = new File(folder_path);
        File file = new File(file_path);
        boolean folder_created = false;
        boolean file_created = false;
        String[] answer = new String[2];

        // Crear mensajes al usuario
        String base_success_msg ="\nEl %s ha sido creado exitosamente.\n";
        String base_error_msg = "\nError al crear el %s.\n";
        String base_exists_error_msg = "\nEl %s ya existe.\n";
        String folder_str = "directorio";
        String file_str = "archivo";
        String folder_success_msg =base_success_msg.formatted(folder_str);
        String folder_error_msg = base_error_msg.formatted(folder_str);
        String folder_exists_error_msg = base_exists_error_msg.formatted(folder_str);
        String file_success_msg =base_success_msg.formatted(file_str);
        String file_error_msg = base_error_msg.formatted(file_str);
        String file_exists_error_msg = base_exists_error_msg.formatted(file_str);

        // Crear valores por defecto para la respuesta
        answer[0] = "true";
        answer[1] = "";

        // Comprobar si ya existe la carpeta. Si existe, saltar este bloque y guardar un mensaje de error para el
        // usuario. Caso contrario, crearlo
        if (!folder.exists()) {
            try {
                folder_created = folder.mkdirs();
            } catch (SecurityException folder_exc) {
                // Incluir en la respuesta si es que hubo un mensaje de error
                exc_cause = String.valueOf(folder_exc.getCause());
                answer[1] = "%s.%s%s".formatted(folder_error_msg, new_line, exc_cause);
            }
        } else answer[1] = folder_exists_error_msg;

        // Comprobar si ya existe el archivo. Si existe, saltar este bloque y guardar un mensaje de error para el
        // usuario. Caso contrario, crearlo
        if (!file.exists()) {
            try {
                    file_created = file.createNewFile();
            } catch (SecurityException | IOException file_exc) {
                // Incluir en la respuesta si es que hubo un mensaje de error
                exc_cause = String.valueOf(file_exc.getCause());
                answer[1] = "%s.%s%s%s%s".formatted(answer[1], new_line, file_error_msg, new_line, exc_cause);
            }
        } else answer[1] = "%s.%s%s".formatted(answer[1], new_line, file_exists_error_msg);

        // Construir la respuesta. Esta debe incluir si la operación fue exitosa en el primer elemento del array.
        // Si hubo algún mensaje de error, este va en el segundo elemento del array
        answer[0] = "%s".formatted((folder_created && file_created));
        answer[1] = answer[0].equals("true") ? "%s%s%s".formatted(folder_success_msg, new_line, file_success_msg) : answer[1];

        // Retornar la respuesta
        return answer;
    }


    private static String[] writeBuffer(String directory, String file_name, ArrayList<String> content) {

        // Definir variables de trabajo y respuesta
        String folder_path = "%s%s".formatted(src_directory, directory);
        String file_path = "%s%s%s".formatted(folder_path, separator, file_name);
        File current_file = new File(file_path);
        FileWriter file_writer = null;
        BufferedWriter file_buffer;
        Iterator<String> content_list = content.iterator();
        String[] answer = new String[2];

        // Crear mensajes para el usuario
        String success_msg = "El archivo fue escrito exitosamente.";
        String write_error_msg = "Error al intentar escribir el archivo.";

        // Crear respuesta por defecto
        answer[0] = "true";
        answer[1] = "";

        // Intentar escribir el archivo
        try {
            // Abrir el archivo para escritura
            file_writer = new FileWriter(current_file, StandardCharsets.UTF_8);
            file_buffer = new BufferedWriter(file_writer);

            // Escribir los datos e insertar un salto de línea
            while (content_list.hasNext()) {
                file_buffer.write(content_list.next());
                file_buffer.newLine();
            }

            // Cerrar el archivo
            file_buffer.close();

        } catch (IOException io_exc) {
            // Si se produce una excepción, preparar un mensaje de error
            answer[0] = "false";
            exc_cause = String.valueOf(io_exc.getCause());

            // Guardar mensaje de error en el segundo elemento de la respuesta
            answer[1] = "%s%s%s".formatted(write_error_msg, new_line, exc_cause);
        }

        // Preparar mensaje de éxito para el usuario
        answer[1] = answer[0].equals("true") ? success_msg : answer[1];

        // Retornar respuesta
        return answer;
    }

    private static String[] readFile(String directory, String file_name) {
        // Definir variables de trabajo y respuesta
        String folder_path = "%s%s".formatted(src_directory, directory);
        String file_path = "%s%s%s".formatted(folder_path, separator, file_name);
        File current_file = new File(file_path);
        FileReader file_reader = null;
        BufferedReader file_buffer = null;
        String new_datum = "";
        String[] answer = new String[3];

        // Crear mensajes para el usuario
        String success_msg = "El archivo fue leído exitosamente.";
        String read_error_msg = "Error al intentar leer el archivo.";

        // Crear respuesta por defecto
        answer[0] = "true";
        answer[1] = "";
        answer[2] = "";

        // Intentar leer el archivo
        try {
            // Abrir el archivo
            file_reader = new FileReader(current_file, StandardCharsets.UTF_8);
            file_buffer = new BufferedReader(file_reader);

            // Leer línea por línea
            do {
                new_datum = file_buffer.readLine();
                answer[2] = "%s%s%s".formatted(answer[2], separator, new_datum);
            } while (new_datum != null);

            // Cerrar el archivo
            file_buffer.close();
            file_reader.close();

        } catch (IOException io_exc) {
            // Si se produce una excepción, preparar un mensaje de error
            answer[0] = "false";
            exc_cause = String.valueOf(io_exc.getCause());
            answer[1] = "%s.%s%s".formatted(read_error_msg, new_line, exc_cause);
        }

        // Preparar mensaje de éxito para el usuario
        answer[1] = answer[0].equals("true") ? success_msg : answer[1];

        // Retornar respuesta
        return answer;
    }

    private static ArrayList<String> stringToArray(String content) {
        // Definir variables de trabajo y respuesta
        ArrayList<String> answer;

        // Separar cada elemento usando el salto de línea y guardarlos en 'answer'
        answer =  new ArrayList<String>(Arrays.asList(content.split(separator)));

        // Retornar la respuesta
        return answer;
    }

    private static String[] buscarTexto(String nombreFichero, String texto) {
        // Crear variables de trabajo y respuesta
        ArrayList<String> database = new ArrayList<>();
        String[] read_data;
        int repetitions;
        String[] answer = new String[3];

        // Crear mensaje de éxito para el usuario
        String success_msg = "\nCantidad de repeticiones del texto: %s.\n";

        // Leer los datos desde el archivo especificado
        read_data = readFile("static", nombreFichero);

        // Comprobar si el archivo pudo ser leído.
        if (read_data[0].equals("true")) {
            // Si la lectura fue exitosa, convertir los datos en un ArrayList
            database = stringToArray(read_data[2]);

            // Contar el número de veces que se repite el texto buscado en la base de datos
            repetitions = Collections.frequency(database, texto);

            // Preparar la respuesta
            answer[0] = "true";
            answer[1] = "";
            answer[2] = success_msg.formatted(repetitions);
        }
        // Si falló, retornar el resultado de la lectura como respuesta
        else answer = read_data;


        // Retornar la respuesta
        return answer;
    }

    public static void main(String[] args) {

        // Definir una variable para contener datos de ejemplo que serán grabados en un archivo
        ArrayList<String> dummy_data = new ArrayList<String>();

        // Crear un objeto Scanner para leer texto desde la consola
        Scanner console = new Scanner(System.in, StandardCharsets.UTF_8);

        // Crear otras variables de trabajo y la de respuesta
        String[] folder_file_creation = new String[2];
        String[] file_write_result = new String[2];
        String[] file_read_result = new String[3];
        boolean valid_choice;
        ArrayList<String> data_array = new ArrayList<>();
        String search_string;
        String[] search_result;

        // Crear los mensajes para el usuario
        String title_msg = "\n\nEscribiendo y Leyendo Archivos.\n" +
                "--------------------------------------------------------\n\n";
        String inst_1 = "\nPrimero, crearemos un archivo llamado 'listado.txt' dentro de la carpeta 'static'." +
                "\nEsta operación tuvo el siguiente resultado:\n";
        String inst_2 = "\nLuego, escribiremos en el archivo las palabras 'Perro', 'Gato', 'Juan', 'Daniel', y 'Camila'." +
                "\nEsta operación tuvo el siguiente resultado:\n";
        String inst_3 = "\nFinalmente, puedes intentar buscar una de esas palabras (u otras) y contar cuántas veces se repite.";
        String inst_4 = "\nIngresa la palabra que quieres buscar: ";
        String inst_5 = "\nEsta operación tuvo el siguiente resultado:\n";
        String input_error_msg = "\nLa opción ingresada no es válida. Por favor, elige un número del menú.";
        String end_msg = "\n\nEl programa ha terminado. Adios.";

        // Imprimir título y primera instrucción
        System.out.printf("%s%s", title_msg, inst_1);

        // Crear directorio 'static' y archivo 'listado.txt'
        folder_file_creation = crearArchivo("static", "listado.txt");

        // Imprimir los mensajes de resultado de la operación
        System.out.println(folder_file_creation[1]);

        // Crear datos de ejemplo para grabar en un archivo
        dummy_data.add("Perro");
        dummy_data.add("Gato");
        dummy_data.add("Juan");
        dummy_data.add("Daniel");
        dummy_data.add("Juan");
        dummy_data.add("Gato");
        dummy_data.add("Perro");
        dummy_data.add("Camila");
        dummy_data.add("Daniel");
        dummy_data.add("Camila");

        // Imprimir segunda instrucción
        System.out.print(inst_2);

        // Grabar datos de ejemplo en un archivo
        file_write_result = writeBuffer("static", "listado.txt", dummy_data);

        // Imprimir los mensajes de resultado de la operación
        System.out.println(file_write_result[1]);

        // Leer datos desde el mismo archivo
        file_read_result = readFile("static", "listado.txt");

        // Comprobar si la lectura fue exitosa
        if (file_read_result[0].equals("true")) {

            // Convertir el resultado de la lectura del archivo en un ArrayList
            data_array = stringToArray(file_read_result[2]);

            // Imprimir tercera y cuarta instrucciones
            System.out.printf("%s%s", inst_3, inst_4);

            // Leer la opción seleccionada desde la consola. Si el usuario ingresa un texto inválido,
            // retornar un mensaje de error y volver a preguntar hasta que ingrese un número válido
            search_string = console.next();
            valid_choice = search_string.matches("^\\p{LD}+$");
            if (!valid_choice) {
                while (!valid_choice) {
                    System.out.printf("%s%s", input_error_msg, inst_2);
                    search_string = console.next();
                    valid_choice = search_string.matches("^\\p{LD}+$");
                }
            }

            // Buscar cuántas veces se repite el nombre 'Camila' en los datos del archivo
            search_result = buscarTexto("listado.txt", search_string);

            // Comprobar el resultado de la operación
            if (search_result[0].equals("true")) {

                // Si la búsqueda tuvo éxito imprimir el resultado
                System.out.println(search_result[2]);
            }
            else {

                // Si hubo un error, imprimir el mensaje de error de la búsqueda
                System.out.println(search_result[1]);
            }
        }
        else {
            // Imprimir los mensajes de error de la lectura
            System.out.println(file_read_result[1]);
        }

        // Imprimir el mensaje final y terminar el programa
        System.out.println(end_msg);
    }
}
