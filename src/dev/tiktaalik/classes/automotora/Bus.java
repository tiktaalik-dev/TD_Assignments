package dev.tiktaalik.classes.automotora;

public class Bus extends Vehiculo {

    // Crear variables de clase
    private int cantidadAsientos;
    private int cantidadAsientosOcupados;

    public Bus(String color, String patente, int cantidadAsientos) {

        // Asignar los valores iniciales
        super(color, patente);
        this.cantidadAsientos = cantidadAsientos;
    }

    public int getCantidadAsientos() {

        // Retornar la cantidad de asientos en total que tiene el bus
        return cantidadAsientos;
    }

    public boolean setCantidadAsientos(int cantidadAsientos) {

        // Intentar asignar la nueva cantidad de asientos en total
        try {
            this.cantidadAsientos = cantidadAsientos;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }

    public int getCantidadAsientosOcupados() {

        // Retornar la cantidad de asientos ocupados actualmente
        return cantidadAsientosOcupados;
    }

    public boolean setCantidadAsientosOcupados(int cantidadAsientosOcupados) {

        // Intentar asignar la nueva cantidad de asientos ocupados
        try {
            this.cantidadAsientosOcupados = cantidadAsientosOcupados;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }

    public int asientosDisponibles() {

        // Retornar cuántos asientos hay disponibles
        return getCantidadAsientos() - getCantidadAsientosOcupados();
    }
}
