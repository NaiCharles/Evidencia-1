package Ev1;

import java.util.ArrayList;

public class SistemaCitas {
    private ArrayList<Doctor> doctores; // Lista de doctores
    private ArrayList<Paciente> pacientes; // Lista de pacientes
    private ArrayList<Cita> citas; // Lista de citas

    // Constructor
    public SistemaCitas() {
        this.doctores = new ArrayList<>();
        this.pacientes = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    // Método para agregar doctor
    public void agregarDoctor(Doctor doctor) {
        doctores.add(doctor);
    }

    // Método para agregar paciente
    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    // Método para crear cita
    public void crearCita(Cita cita) {
        citas.add(cita);
    }

}
