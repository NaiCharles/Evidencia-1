package Ev1;

public class Doctor {
    private String id; // Identificador único
    private String nombreCompleto; // Nombre completo del doctor
    private String especialidad; // Especialidad del doctor

    public Doctor(String id, String nombreCompleto, String especialidad) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
    }

    public String getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}
