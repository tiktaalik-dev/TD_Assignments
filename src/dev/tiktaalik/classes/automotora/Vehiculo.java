package dev.tiktaalik.classes.automotora;

public class Vehiculo {

    // Crear variables de clase
    protected String color;
    protected String patente;

    public Vehiculo(String color, String patente) {

        // Asignar los valores iniciales
        this.color = color;
        this.patente = patente;
    }

    public String getColor() {

        // Retornar el color actual
        return color;
    }

    public boolean setColor(String nuevoColor) {

        // Intentar asignar el nuevo color
        try {
            this.color = nuevoColor;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }

    public String getPatente() {

        // Retornar la patente actual
        return patente;
    }

    public boolean setPatente(String nuevaPatente) {

        // Intentar asignar la nueva patente del vehículo
        try {
            this.patente = nuevaPatente;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }
}
