package dev.tiktaalik.classes.automotora;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Crear variable de trabajo
        String linefeed = System.lineSeparator();
        String msg = "%s%s".formatted(linefeed, linefeed);

        // Crear mensajes de éxito y error
        String success_msg = "El archivo ha sido creado exitosamente dentro del directorio 'ficheros'.";
        String error_msg = "El archivo no pudo ser creado. Intente nuevamente.";

        // Crear una instancia de cada vehículo
        Bus autobus = new Bus("Rojo","UB-43.56", 42);
        MiniBus liebre = new MiniBus("Verde","ND-93.20", 26, "Express");
        Taxi colectivo = new Taxi("Negro/Amarillo", "TS-61.75",350);
        ArrayList<Vehiculo> productos = new ArrayList<>(Arrays.asList(autobus, liebre, colectivo));

        // Crear instancias para la tienda, vendedor, y cliente
        Cliente comprador = new Cliente("11.235.912-5", "Alan Brito", 46);
        Vendedor super_vendedor = new Vendedor("10.785.877-6", "Telov Endo","Sucursal El Bosque");
        Tienda sucursal_EB = new Tienda(super_vendedor, comprador,"Sucursal El Bosque", 15, productos);

        // Crear un Libro de Ventas
        LibroVenta ledger = new LibroVenta("Venta_retail", "2024-10-24");

        // Guardar registro de la venta
        boolean resultado = ledger.guardarVenta(comprador, liebre);

        // Componer el mensaje indicando al usuario el resultado de la operación
        if (resultado) {
            msg = "%s%s".formatted(msg, success_msg);
        } else {
            msg = "%s%s".formatted(msg, error_msg);
        }

        // Imprimir mensaje
        System.out.println(msg);
    }
}
