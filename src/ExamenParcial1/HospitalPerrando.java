package ExamenParcial1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalPerrando {
    private ArrayList<Doctor> doctores = new ArrayList<>();
    private ArrayList<Paciente> pacientes = new ArrayList<>();
    private String datosHospital = "";

    public static void main(String[] args) {
        HospitalPerrando hospital = new HospitalPerrando();
        hospital.cargarDoctores();
        hospital.cargarDatosHospital();
        hospital.mostrarMenu();
    }

    private void cargarDoctores() {
        doctores.add(new Doctor("Dr. Facundo Uferer", "12345678", "01/01/1970", "Cardiología"));
        doctores.add(new Doctor("Dra. Dua Lipa", "87654321", "15/03/1980", "Pediatría"));

    }

    private void cargarDatosHospital() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Datos.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                datosHospital += linea + "\n";
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al cargar datos del hospital.");
        }
    }

    private void guardarDatosHospital() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("HistorialPacientes.txt"));
            bw.write(datosHospital);
            bw.close();
        } catch (IOException e) {
            System.out.println("Error al guardar datos del hospital.");
        }
    }

    //==============Mostar Menu====================
    private void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("==========================================================================");
            System.out.println("Hospital Julio C. Perrando - Av. 9 de Julio 1100 · 0362 444-2399");
            System.out.println("Menu:");
            System.out.println("1. Listar Doctores.");
            System.out.println("2. Registrar un nuevo paciente.");
            System.out.println("3. Actualizar informacion personal de un paciente.");
            System.out.println("4. Nuevo historial para un paciente.");
            System.out.println("5. Guardar Historial de pacientes en archivo.");
            System.out.println("6. Cargar Historial de pacientes desde archivo.");
            System.out.println("7. Salir.");
            System.out.print("Elija una opcion: ");
            System.out.println("==========================================================================");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    listarDoctores();
                    break;
                case 2:
                    registrarPaciente();
                    break;
                case 3:
                    actualizarInformacionPaciente();
                    break;
                case 4:
                    agregarHistorialPaciente();
                    break;
                case 5:
                    guardarHistorialPacientes();
                    break;
                case 6:
                    cargarHistorialPacientes();
                    break;
                case 7:
                    guardarDatosHospital();
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor, ingrese una opcion valida.");
            }
        } while (opcion != 7);
        scanner.close();
    }

    private void listarDoctores() {
        System.out.println("Lista de Doctores:");
        for (Doctor doctor : doctores) {
            System.out.println("Nombre: " + doctor.getNombre());
            System.out.println("DNI: " + doctor.getDni());
            System.out.println("Fecha de Nacimiento: " + doctor.getFechaNacimiento());
            System.out.println("Especialidad: " + doctor.getEspecialidad());
        }
    }

    private void registrarPaciente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Registro de nuevo paciente:");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("DNI: ");
        String dni = scanner.nextLine();

        System.out.print("Fecha de Nacimiento (dd/MM/yyyy): ");
        String fechaNacimiento = scanner.nextLine();

        System.out.print("Numero de Telefono: ");
        int numeroTelefono = scanner.nextInt();

        System.out.print("Tipo de Sangre: ");
        int tipoSangre = scanner.nextInt();
        scanner.nextLine();


        Paciente nuevoPaciente = new Paciente(nombre, dni, fechaNacimiento, numeroTelefono, tipoSangre);
        pacientes.add(nuevoPaciente);
        System.out.println("Paciente registrado exitosamente.");
    }

    private void actualizarInformacionPaciente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el DNI del paciente a actualizar: ");
        String dni = scanner.nextLine();

        Paciente pacienteEncontrado = null;
        for (Paciente paciente : pacientes) {
            if (paciente.getDni().equals(dni)) {
                pacienteEncontrado = paciente;
                break;
            }
        }
        if (pacienteEncontrado != null) {
            System.out.println("Paciente encontrado:");
            System.out.println("Nombre: " + pacienteEncontrado.getNombre());
            System.out.println("DNI: " + pacienteEncontrado.getDni());
            System.out.println("Fecha de Nacimiento: " + pacienteEncontrado.getFechaNacimiento());
            System.out.println("Número de Teléfono: " + pacienteEncontrado.getNumeroTelefono());
            System.out.println("Tipo de Sangre: " + pacienteEncontrado.getTipoSangre());

            System.out.println("Ingrese los nuevos datos del paciente:");

            System.out.print("Nombre: ");
            String nuevoNombre = scanner.nextLine();
            pacienteEncontrado.setNombre(nuevoNombre);

            System.out.print("Fecha de Nacimiento (dd/MM/yyyy): ");
            String nuevaFechaNacimiento = scanner.nextLine();
            pacienteEncontrado.setFechaNacimiento(nuevaFechaNacimiento);

            System.out.print("Numero de Telefono: ");
            int nuevoNumeroTelefono = scanner.nextInt();
            pacienteEncontrado.setNumeroTelefono(nuevoNumeroTelefono);

            System.out.print("Tipo de Sangre: ");
            int nuevoTipoSangre = scanner.nextInt();
            pacienteEncontrado.setTipoSangre(nuevoTipoSangre);

            System.out.println("Informacion actualizada exitosamente.");
        } else {
            System.out.println("Paciente no encontrado con el DNI proporcionado.");
        }
    }

    private void agregarHistorialPaciente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el DNI del paciente al que desea agregar un nuevo historial: ");
        String dni = scanner.nextLine();

        Paciente pacienteEncontrado = null;
        for (Paciente paciente : pacientes) {
            if (paciente.getDni().equals(dni)) {
                pacienteEncontrado = paciente;
                break;
            }
        }

        if (pacienteEncontrado != null) {
            System.out.println("Ingrese el nuevo historial médico del paciente:");
            System.out.print("Fecha (dd/MM/yyyy): ");
            String fechaEvento = scanner.nextLine();
            System.out.print("Observaciones: ");
            String observaciones = scanner.nextLine();

            String eventoHistorial = fechaEvento + " - " + observaciones;
            pacienteEncontrado.agregarEventoHistorial(eventoHistorial);

            System.out.println("Historial médico actualizado exitosamente.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    private void guardarHistorialPacientes() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("historial de pacientes"));
            outputStream.writeObject(pacientes);
            outputStream.close();
            System.out.println("Historial de pacientes guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el historial de pacientes." + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarHistorialPacientes() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("historial de pacientes"));
            pacientes = (ArrayList<Paciente>) inputStream.readObject();
            inputStream.close();
            System.out.println("Historial de pacientes cargado exitosamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el historial de pacientes." + e.getMessage());
        }
    }

}
