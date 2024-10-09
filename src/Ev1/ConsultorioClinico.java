package Ev1;

import java.io.*;
import java.util.*;

class Doctor {
    private String id;
    private String nombre;
    private String especialidad;

    public Doctor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }

    @Override
    public String toString() {
        return id + "," + nombre + "," + especialidad;
    }
}

class Paciente {
    private String id;
    private String nombre;

    public Paciente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return id + "," + nombre;
    }
}

class Cita {
    private String id;
    private String fecha;
    private String hora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String id, String fecha, String hora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return id + "," + fecha + "," + hora + "," + motivo + "," + doctor.getId() + "," + paciente.getId();
    }
}

public class ConsultorioClinico {
    private static Map<String, Doctor> doctores = new HashMap<>();
    private static Map<String, Paciente> pacientes = new HashMap<>();
    private static List<Cita> citas = new ArrayList<>();
    private static Set<String> administradores = new HashSet<>();

    public static void main(String[] args) {
        cargarDatos();
        Scanner scanner = new Scanner(System.in);

        if (!validarAcceso(scanner)) {
            System.out.println("Acceso denegado. Finalizando programa.");
            return;
        }

        int opcion;
        do {
            System.out.println("\n=== Sistema de Administración de Citas ===");
            System.out.println("1. Dar de alta doctor");
            System.out.println("2. Dar de alta paciente");
            System.out.println("3. Crear cita");
            System.out.println("4. Listar doctores");
            System.out.println("5. Listar pacientes");
            System.out.println("6. Listar citas");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> darAltaDoctor(scanner);
                case 2 -> darAltaPaciente(scanner);
                case 3 -> crearCita(scanner);
                case 4 -> listarDoctores();
                case 5 -> listarPacientes();
                case 6 -> listarCitas();
                case 7 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 7);

        guardarDatos();
        scanner.close();
    }

    private static boolean validarAcceso(Scanner scanner) {
        System.out.print("Ingrese su identificador de administrador: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();
        return administradores.contains(id + ":" + contrasena);
    }

    private static void darAltaDoctor(Scanner scanner) {
        System.out.print("Ingrese el ID del doctor: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el nombre del doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la especialidad del doctor: ");
        String especialidad = scanner.nextLine();

        Doctor doctor = new Doctor(id, nombre, especialidad);
        doctores.put(id, doctor);
        System.out.println("Doctor registrado exitosamente.");
    }

    private static void darAltaPaciente(Scanner scanner) {
        System.out.print("Ingrese el ID del paciente: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();

        Paciente paciente = new Paciente(id, nombre);
        pacientes.put(id, paciente);
        System.out.println("Paciente registrado exitosamente.");
    }

    private static void crearCita(Scanner scanner) {
        System.out.print("Ingrese el ID de la cita: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese la fecha de la cita (DD/MM/AAAA): ");
        String fecha = scanner.nextLine();
        System.out.print("Ingrese la hora de la cita (HH:MM): ");
        String hora = scanner.nextLine();
        System.out.print("Ingrese el motivo de la cita: ");
        String motivo = scanner.nextLine();

        System.out.print("Ingrese el ID del doctor: ");
        String doctorId = scanner.nextLine();
        Doctor doctor = doctores.get(doctorId);
        if (doctor == null) {
            System.out.println("Doctor no encontrado.");
            return;
        }

        System.out.print("Ingrese el ID del paciente: ");
        String pacienteId = scanner.nextLine();
        Paciente paciente = pacientes.get(pacienteId);
        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        Cita cita = new Cita(id, fecha, hora, motivo, doctor, paciente);
        citas.add(cita);
        System.out.println("Cita creada exitosamente.");
    }

    private static void listarDoctores() {
        System.out.println("\n=== Lista de Doctores ===");
        doctores.values().forEach(System.out::println);
    }

    private static void listarPacientes() {
        System.out.println("\n=== Lista de Pacientes ===");
        pacientes.values().forEach(System.out::println);
    }

    private static void listarCitas() {
        System.out.println("\n=== Lista de Citas ===");
        citas.forEach(System.out::println);
    }

    private static void cargarDatos() {
        cargarAdministradores();
        cargarDoctores();
        cargarPacientes();
        cargarCitas();
    }

    private static void cargarAdministradores() {
        // Simulación de datos de acceso. En producción, esto debería venir de un archivo seguro.
        administradores.add("admin:admin123");
    }

    private static void cargarDoctores() {
        try (BufferedReader reader = new BufferedReader(new FileReader("db/doctores.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                Doctor doctor = new Doctor(datos[0], datos[1], datos[2]);
                doctores.put(datos[0], doctor);
            }
        } catch (IOException e) {
            System.out.println("No se encontraron datos de doctores.");
        }
    }

    private static void cargarPacientes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("db/pacientes.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                Paciente paciente = new Paciente(datos[0], datos[1]);
                pacientes.put(datos[0], paciente);
            }
        } catch (IOException e) {
            System.out.println("No se encontraron datos de pacientes.");
        }
    }

    private static void cargarCitas() {
        try (BufferedReader reader = new BufferedReader(new FileReader("db/citas.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                Doctor doctor = doctores.get(datos[4]);
                Paciente paciente = pacientes.get(datos[5]);
                if (doctor != null && paciente != null) {
                    Cita cita = new Cita(datos[0], datos[1], datos[2], datos[3], doctor, paciente);
                    citas.add(cita);
                }
            }
        } catch (IOException e) {
            System.out.println("No se encontraron datos de citas.");
        }
    }

    private static void guardarDatos() {
        guardarDoctores();
        guardarPacientes();
        guardarCitas();
    }

    private static void guardarDoctores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("db/doctores.csv"))) {
            for (Doctor doctor : doctores.values()) {
                writer.write(doctor.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar doctores.");
        }
    }

    private static void guardarPacientes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("db/pacientes.csv"))) {
            for (Paciente paciente : pacientes.values()) {
                writer.write(paciente.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar pacientes.");
        }
    }

    private static void guardarCitas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("db/citas.csv"))) {
            for (Cita cita : citas) {
                writer.write(cita.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar citas.");
        }
    }
}
