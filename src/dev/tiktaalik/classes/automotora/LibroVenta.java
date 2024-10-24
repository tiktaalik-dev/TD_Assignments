package dev.tiktaalik.classes.automotora;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LibroVenta {

    // Crear variables de clase
    String nombreVenta;
    String fechaVenta;
    static String user_directory = System.getProperty("user.dir");
    static String separator = File.separator;
    static String src_directory = "%s%sficheros%s".formatted(user_directory, separator, separator);
    static String new_line = System.lineSeparator();
    static String error_msg;
    static String exc_cause;


    public LibroVenta(String nombreVenta, String fechaVenta) {
        this.nombreVenta = nombreVenta;
        this.fechaVenta = fechaVenta;
    }

    public String getNombreVenta() {
        return nombreVenta;
    }

    public void setNombreVenta(String nombreVenta) {
        this.nombreVenta = nombreVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    private static String[] crearArchivo(String fichero) {
        // Crear variables de trabajo y respuesta
        String file_path = "%s%s%s".formatted(src_directory, separator, fichero);
        File folder = new File(src_directory);
        File file = new File(file_path);
        boolean folder_created = false;
        boolean file_created = false;
        String[] answer = new String[2];

        // Crear mensajes al usuario
        String base_success_msg = "\nEl %s ha sido creado exitosamente.\n";
        String base_error_msg = "\nError al crear el %s.\n";
        String base_exists_error_msg = "\nEl %s ya existe.\n";
        String folder_str = "directorio";
        String file_str = "archivo";
        String folder_success_msg = base_success_msg.formatted(folder_str);
        String folder_error_msg = base_error_msg.formatted(folder_str);
        String folder_exists_error_msg = base_exists_error_msg.formatted(folder_str);
        String file_success_msg = base_success_msg.formatted(file_str);
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


    private static String[] writeBuffer(String file_name, ArrayList<String> content) {

        // Definir variables de trabajo y respuesta
        String file_path = "%s%s%s".formatted(src_directory, separator, file_name);
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

    public boolean guardarVenta(Cliente comprador, Vehiculo producto) {

        // Crear variables de trabajo
        boolean result = false;
        String[] file_written = new String[2];

        // Extraer información del comprador y el vehículo
        String patente = producto.getPatente();
        String rut = comprador.getRut();
        String nombre_comprador = comprador.getNombre();
        int edad = comprador.getEdad();

        // Componer el contenido del archivo
        String linefeed = System.lineSeparator();
        String header = "Patente;rut;Nombre;Edad;" ;
        String datos = "%s;%s;%s;%d;".formatted(patente, rut, nombre_comprador, edad) ;
        String contenido = "%s%s%s".formatted(header, linefeed, datos);
        String nombre_fichero = "%s.txt".formatted(getNombreVenta());

        // Crear el archivo de registro
        String[] file_created = crearArchivo(nombre_fichero);

        // Escribir el contenido del archivo
        if (Objects.equals(file_created[0], "true")) {
            file_written = writeBuffer(nombre_fichero, new ArrayList<>(List.of(contenido)));
        }

        // Determinar el resultado de la operación
        if (Objects.equals(file_created[0], "true") && Objects.equals(file_written[0], "true")) {
            result = true;
        }
        return result;
    }
}
