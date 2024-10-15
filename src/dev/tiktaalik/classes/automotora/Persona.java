package dev.tiktaalik.classes.automotora;

public class Persona {

    // Crear variables de clase
    protected String rut;
    protected String nombre;

    public Persona(String rut, String nombre) {

        // Asignar los valores iniciales
        this.rut = rut;
        this.nombre = nombre;
    }

    public String getRut() {

        // Retornar el R.U.T. de la persona
        return rut;
    }

    public boolean setRut(String rut) {

        // Intentar asignar el R.U.T. de la persona
        try {
            this.rut = rut;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }

    public String getNombre() {

        // Retornar el nombre de la persona
        return nombre;
    }

    public boolean setNombre(String nombre) {

        // Intentar asignar el nombre de la persona
        try {
            this.nombre = nombre;
        } catch (Exception e) {

            // Si falla retornar falso
            return false;
        }

        // Por defecto retornar éxito
        return true;
    }
}
