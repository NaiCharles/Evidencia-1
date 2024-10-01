package Ev1;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class Principal {
    public static void main(String[] args) {
        SistemaCitas sistema = new SistemaCitas(); // Crear instancia del sistema

        // Crear un doctor
        Doctor doctor = new Doctor("D001", "Juan Pérez", "Pediatra");
        sistema.agregarDoctor(doctor); // Agregar doctor al sistema

        // Crear un paciente
        Paciente paciente = new Paciente("P001", "María Gómez");
        sistema.agregarPaciente(paciente); // Agregar paciente al sistema

        // Crear una cita
        Cita cita = new Cita();
        sistema.crearCita(cita); // Agregar cita al sistema

    }
}
