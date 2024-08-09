package dev.tiktaalik.classes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LecturaEscritura {
    private static String[] crearArchivo(String directorio, String fichero) throws IOException, SecurityException {
        // Crear variables de trabajo y respuesta
        String separator = File.separator;
        String folder_path = "src%s%s".formatted(separator, directorio);
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


        // Comprobar si ya existe la carpeta. Si existe, saltar este bloque. Caso contrario, crearlo
        String userDirectory = System.getProperty("user.dir");
        System.out.printf("Current user.dir is: %s%n", userDirectory);
        if (!folder.exists()) {
            try {
                folder_created = folder.mkdirs();
            } catch (SecurityException folder_exc) {
                // Incluir en la respuesta si es que hubo un mensaje de error
                answer[1] = String.valueOf(folder_exc.getCause());
                throw new RuntimeException(folder_exc);
            }
        } else answer[1] = folder_exists_error_msg;

        // Comprobar si ya existe el archivo. Si existe, saltar este bloque. Caso contrario, crearlo
        if (!file.exists()) {
            try {
                    file_created = file.createNewFile();
            } catch (SecurityException | IOException file_exc) {
                // Incluir en la respuesta si es que hubo un mensaje de error
                answer[1] = "%s.\n%s".formatted(answer[1], String.valueOf(file_exc.getCause()));
                throw new RuntimeException(file_exc);
            }
        } else answer[1] = "%s%s".formatted(answer[1], file_exists_error_msg);

        // Construir la respuesta. Esta debe incluir si la operación fue exitosa y si hubo algún mensaje de error
        answer[0] = "%s".formatted((folder_created && file_created) ? folder_success_msg : folder_error_msg);

        // Retornar la respuesta
        return answer;
    }


    private static String[] writeBuffer(String directory, String file_name, ArrayList<String> content) throws IOException {

        // Definir variables de trabajo y respuesta
        String separator = File.separator;
        String folder_path = "src%s%s".formatted(separator, directory);
        String file_path = "%s%s%s".formatted(folder_path, separator, file_name);
        File current_file = new File(file_path);
        FileWriter file_writer = null;
        BufferedWriter file_buffer;
        Iterator<String> content_list = content.iterator();
        String[] answer = new String[2];

        // Crear respuesta por defecto
        answer[0] = "true";
        answer[1] = "";

        // Abrir archivo para escribir
        try {
            file_writer = new FileWriter(current_file, StandardCharsets.UTF_8);
        } catch (IOException io_exc) {
            answer[0] = "false";
            answer[1] = String.valueOf(io_exc.getCause());
            throw new RuntimeException(io_exc);
        } finally {
            if (answer[0].equals("true")) {
                file_buffer = new BufferedWriter(file_writer);
                while (content_list.hasNext()) {
                    file_buffer.write(content_list.next());
                    file_buffer.newLine();
                }
                file_buffer.close();
            }
        }

        // Retornar respuesta
        return answer;
    }

    private static String[] readFile(String directory, String file_name) throws IOException {
        // Definir variables de trabajo y respuesta
        String separator = File.separator;
        String folder_path = "src%s%s".formatted(separator, directory);
        String file_path = "%s%s%s".formatted(folder_path, separator, file_name);
        File current_file = new File(file_path);
        FileReader file_reader = null;
        BufferedReader file_buffer = null;
        String line_read = "";
        String[] answer = new String[3];

        // Crear respuesta por defecto
        answer[0] = "true";
        answer[1] = "";
        answer[2] = "";

        // Abrir archivo para leerlo
        try {
            file_reader = new FileReader(current_file, StandardCharsets.UTF_8);
        } catch (IOException io_exc) {
            answer[0] = "false";
            answer[2] = String.valueOf(io_exc.getCause());
            throw new RuntimeException(io_exc);
        }
        if (answer[0].equals("true")) {
            file_buffer = new BufferedReader(file_reader);
            try {
                line_read = file_buffer.readLine();
            } catch (IOException io_exc) {
                answer[0] = "false";
                answer[2] = String.valueOf(io_exc.getCause());
                throw new RuntimeException(io_exc);
            }
        }
        if (answer[0].equals("true")) {
            while (line_read != null) {
                answer[1] = "%s%s%s".formatted(answer[1], System.lineSeparator(), line_read);
                try {
                    line_read = file_buffer.readLine();
                } catch (IOException io_exc) {
                answer[0] = "false";
                answer[2] = String.valueOf(io_exc.getCause());
                throw new RuntimeException(io_exc);
                }
            }
            file_buffer.close();
        }
        file_reader.close();

        // Retornar respuesta
        return answer;
    }

    private static ArrayList<String> stringToArray(String content) {
        // Definir variables de trabajo y respuesta
        ArrayList<String> answer;

        // Separar cada elemento usando el salto de línea y guardarlos en 'answer'
        answer =  new ArrayList<String>(Arrays.asList(content.split(System.lineSeparator())));

        // Retornar la respuesta
        return answer;
    }

    private static Integer buscarTexto(String nombreFichero, String texto) throws IOException {
        // Crear variables de trabajo y respuesta
        ArrayList<String> database = new ArrayList<>();
        String[] read_data;
        Integer answer;

        // Leer los datos desde el archivo especificado
        read_data = readFile("static", nombreFichero);

        // Comprobar si el archivo pudo ser leído. Si falló, imprimir el mensaje de error y retornar
        if (read_data[0].equals("false")) {
            System.out.println(read_data[2]);
            return -1;
        }

        // Caso contrario, convertir los datos en un ArrayList
        database = stringToArray(read_data[1]);

        // Contar el número de veces que se repite el texto buscado en la base de datos
        answer = Collections.frequency(database, texto);

        // Retornar la respuesta
        return answer;
    }

    public static void main(String[] args) throws IOException {

        // Definir una variable para contener datos de ejemplo que serán grabados en un archivo
        ArrayList<String> dummy_data = new ArrayList<String>();

        // Crear variables de trabajo y de respuesta
        String answer;
        String[] folder_file_creation = new String[2];
        String[] file_write_result = new String[2];
        String[] file_read_result = new String[3];
        ArrayList<String> data_array = new ArrayList<>();

        // Definir los mensajes al usuario
        String title_msg = "\n\nCalculando la suma y el promedio de una lista de números.\n" +
                "--------------------------------------------------------\n\n";
        String sum_msg_base = "Para la entrada anterior, el resultado de la suma es %d.";
        String avg_msg_base = "Para la entrada anterior, el resultado del promedio es %.2f.";
        String answer_msg = "\n\n%s\n%s\n\n";
        String end_msg = "\n\nEl programa ha terminado. Adios.";
        String output_msgs = "%s%s%s";
        String read_success_msg ="\nEl archivo ha sido leído exitosamente.\n";
        String read_error_msg = "\nError al intentar leer el archivo.\n";
        String write_success_msg ="\nEl archivo ha sido escrito exitosamente.\n";
        String write_error_msg = "\nError al intentar escribir en el archivo.\n";

        // Crear directorio 'static' y archivo 'listado.txt'
        try {
            folder_file_creation = crearArchivo("static", "listado.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("folder_file_creation = %s\n%s%n", folder_file_creation[0], folder_file_creation[1]);

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

        // Grabar datos de ejemplo en un archivo
        file_write_result = writeBuffer("static", "listado.txt", dummy_data);
        System.out.printf("file_write_result = %s\n%s\n", file_write_result[0], file_write_result[1]);

        // Leer datos desde un archivo
        file_read_result = readFile("static", "listado.txt");
        System.out.printf("file_read_result = %s\n%s\n%s\n", file_read_result[0], file_read_result[1], file_read_result[2]);

        // Convertir el resultado de la lectura del archivo en un ArrayList
        data_array = stringToArray(file_read_result[1]);
        System.out.printf("data array = %s\n\n", data_array);

        // Buscar cuántas veces se repite el nombre 'Camila' en los datos del archivo
        Integer repetitions = buscarTexto("listado.txt", "Camila");
        System.out.printf("El nombre 'Camila' se repite: %d veces en el archivo.\n", repetitions);
    }
}
