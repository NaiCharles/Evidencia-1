package Ev1;

public class Paciente {
    private String id; // Identificador único
    private String nombreCompleto; // Nombre completo del paciente

    public Paciente(String id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public String getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
}
