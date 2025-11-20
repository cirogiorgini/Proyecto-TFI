package tfi.empleadolegajo.app;

import tfi.empleadolegajo.entities.Empleado;
import tfi.empleadolegajo.entities.Legajo;
import tfi.empleadolegajo.service.EmpleadoService;
import tfi.empleadolegajo.service.LegajoService;
import tfi.empleadolegajo.service.impl.EmpleadoServiceImpl;
import tfi.empleadolegajo.service.impl.LegajoServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AppMenu {

    private final Scanner sc = new Scanner(System.in);
    private final EmpleadoService empleadoService = new EmpleadoServiceImpl();
    private final LegajoService legajoService = new LegajoServiceImpl();

    public static void main(String[] args) {
        new AppMenu().start();
    }

    public void start() {
        String opcion;
        do {
            mostrarMenu();
            System.out.print("Opcion: ");
            opcion = sc.nextLine().trim().toUpperCase();

            try {
                switch (opcion) {
                    case "1" -> crearEmpleado();
                    case "2" -> crearEmpleadoConLegajo();
                    case "3" -> listarEmpleados();
                    case "4" -> listarLegajos();
                    case "5" -> buscarEmpleadoPorId();
                    case "6" -> buscarEmpleadoPorDni();
                    case "7" -> actualizarEmpleado();
                    case "8" -> eliminarEmpleadoYLegajo();
                    case "9" -> eliminarLegajoSolo();
                    case "0" -> System.out.println("Saliendo...");
                    default  -> System.out.println("Opcion invalida.");
                }
            } catch (Exception e) {
                System.err.println("ERROR: " + e.getMessage());
            }

        } while (!"0".equals(opcion));
    }

    private void mostrarMenu() {
        System.out.println("""
                ==========================================
                       MENU EMPLEADO - LEGAJO (TFI)
                ==========================================
                [1] Crear empleado (sin legajo)
                [2] Crear empleado + legajo (TRANSACCION)
                [3] Listar empleados
                [4] Listar legajos
                [5] Buscar empleado por ID
                [6] Buscar empleado por DNI
                [7] Actualizar empleado
                [8] Eliminar empleado + legajo (baja logica)
                [9] Eliminar solo legajo (baja logica)
                [0] Salir
                """);
    }

    // ================= EMPLEADO =================

    private void crearEmpleado() {
        System.out.println("=== Alta de empleado ===");
        Empleado e = leerDatosEmpleado(new Empleado());
        empleadoService.insertar(e);
        System.out.println("Empleado creado. ID generado: " + e.getId());
    }

    private void crearEmpleadoConLegajo() {
        System.out.println("=== Alta de empleado + legajo (TRANSACCION) ===");
        Empleado e = leerDatosEmpleado(new Empleado());
        Legajo l = leerDatosLegajo(new Legajo());

        empleadoService.crearEmpleadoConLegajo(e, l);

        System.out.println("Empleado y legajo creados correctamente.");
        System.out.println("ID empleado: " + e.getId());
        System.out.println("Nro legajo: " + l.getNroLegajo());
    }

    private void listarEmpleados() {
        System.out.println("=== Listado de empleados (no eliminados) ===");
        List<Empleado> lista = empleadoService.getAll();
        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay empleados para mostrar.");
            return;
        }
        for (Empleado e : lista) {
            System.out.println(e);
        }
    }

    private void buscarEmpleadoPorId() {
        long id = leerLong("ID de empleado: ");
        Empleado e = empleadoService.getById(id);
        if (e == null) {
            System.out.println("No se encontro empleado con ese ID.");
        } else {
            System.out.println("Empleado encontrado:\n" + e);
        }
    }

    private void buscarEmpleadoPorDni() {
        System.out.print("DNI a buscar: ");
        String dni = sc.nextLine().trim();
        if (dni.isBlank()) {
            System.out.println("DNI vacio.");
            return;
        }

        List<Empleado> lista = empleadoService.getAll();
        Empleado encontrado = null;
        if (lista != null) {
            for (Empleado e : lista) {
                if (dni.equals(e.getDni())) {
                    encontrado = e;
                    break;
                }
            }
        }

        if (encontrado == null) {
            System.out.println("No se encontro empleado con ese DNI.");
        } else {
            System.out.println("Empleado encontrado:\n" + encontrado);
        }
    }

    private void actualizarEmpleado() {
        long id = leerLong("ID del empleado a actualizar: ");
        Empleado actual = empleadoService.getById(id);
        if (actual == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        System.out.println("Deja vacio para mantener el valor actual.");

        System.out.print("DNI (" + actual.getDni() + "): ");
        String dni = sc.nextLine().trim();
        if (!dni.isBlank()) actual.setDni(dni);

        System.out.print("Nombre (" + actual.getNombre() + "): ");
        String nombre = sc.nextLine().trim();
        if (!nombre.isBlank()) actual.setNombre(nombre);

        System.out.print("Apellido (" + actual.getApellido() + "): ");
        String apellido = sc.nextLine().trim();
        if (!apellido.isBlank()) actual.setApellido(apellido);

        System.out.print("Email (" + actual.getEmail() + "): ");
        String email = sc.nextLine().trim();
        if (!email.isBlank()) actual.setEmail(email);

        System.out.print("Area (" + actual.getArea() + "): ");
        String area = sc.nextLine().trim();
        if (!area.isBlank()) actual.setArea(area);

        empleadoService.actualizar(actual);
        System.out.println("Empleado actualizado correctamente.");
    }

    private void eliminarEmpleadoYLegajo() {
        long id = leerLong("ID del empleado a eliminar (baja logica): ");

        empleadoService.eliminarEmpleadoYLegajo(id);

        System.out.println("Empleado y legajo eliminados logicamente (si existian).");
    }

    // ================= LEGAJO =================

    private void listarLegajos() {
        System.out.println("=== Listado de legajos (no eliminados) ===");
        List<Legajo> lista = legajoService.getAll();
        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay legajos para mostrar.");
            return;
        }
        for (Legajo l : lista) {
            System.out.println(l);
        }
    }

    private void eliminarLegajoSolo() {
        long id = leerLong("ID del legajo a eliminar (baja logica): ");
        legajoService.eliminar(id);
        System.out.println("Legajo eliminado logicamente (si existia).");
    }

    // ============ Lectura / construccion de objetos ============

    private Empleado leerDatosEmpleado(Empleado e) {
        System.out.print("DNI: ");
        e.setDni(sc.nextLine().trim());

        System.out.print("Nombre: ");
        e.setNombre(sc.nextLine().trim());

        System.out.print("Apellido: ");
        e.setApellido(sc.nextLine().trim());

        System.out.print("Email: ");
        e.setEmail(sc.nextLine().trim());

        System.out.print("Area: ");
        e.setArea(sc.nextLine().trim());

        e.setFechaIngreso(LocalDate.now());
        e.setEliminado(false);
        return e;
    }

    private Legajo leerDatosLegajo(Legajo l) {
        System.out.print("Numero de legajo: ");
        l.setNroLegajo(sc.nextLine().trim());

        System.out.print("Categoria: ");
        l.setCategoria(sc.nextLine().trim());

        System.out.print("Estado: ");
        l.setEstado(sc.nextLine().trim());

        System.out.print("Observaciones: ");
        l.setObservaciones(sc.nextLine().trim());

        l.setFechaAlta(LocalDate.now());
        l.setEliminado(false);
        return l;
    }

    private long leerLong(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String txt = sc.nextLine().trim();
            try {
                return Long.parseLong(txt);
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un numero entero. Intenta otra vez.");
            }
        }
    }
}
