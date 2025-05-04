package Exp3_s8_Maximiliano_Palasezze;

import java.util.LinkedList;
import java.util.Scanner;

class Reserva {
  int idReserva;
  String nombreCliente;
  int edad;
  String tipoAsiento;
  int numAsiento;
  double precioFinal;
  double descuentoAplicado;

public Reserva(int id, String nombre, int edad, String tipo, int num, double precio, double descuento) {
  this.idReserva = id;
  this.nombreCliente = nombre;
  this.edad = edad;
  this.tipoAsiento = tipo;
  this.numAsiento = num;
  this.precioFinal = precio;
  this.descuentoAplicado = descuento;
}
public class Exp3_s8_Maximiliano_Palasezze {
 static final int FILAS = 4;
 static final int COLUMNAS = 10;
 static String[][] asientos = new String[FILAS][COLUMNAS];
 static String[][] clientes = new String[FILAS][COLUMNAS];
 static String[][] ventas = new String[FILAS][COLUMNAS];
 static LinkedList<Reserva> listaReservas = new LinkedList<>();
 static Scanner scanner = new Scanner(System.in);
 static String[] tipos = {"VIP", "Platea Alta", "Platea Baja", "General"};
 static int[] precios = {20000, 9000, 10000, 6000};
 static int numeroOrden = 1;
 static String[] nombresOrden = new String[100];

public static void main(String[] args) {
        inicializarAsientos();
        System.out.println("Bienvenido al Teatro Moro");
 int opcion;
 do {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Venta de entrada");
        System.out.println("2. Mostrar asientos");
        System.out.println("3. Modificar entradas");
        System.out.println("4. Imprimir boleta");
        System.out.println("5. Salir");
        opcion = leerEntero("Elige una opción: ");
 switch (opcion) {
  case 1 -> venderEntrada();
  case 2 -> mostrarAsientos();
  case 3 -> modificarPorID();
  case 4 -> imprimirBoleta();
  case 5 -> System.out.println("Gracias por su compra.\nDisfrute la función");
  default -> System.out.println("Opción no válida.");
}
} while (opcion != 5);
}

static void inicializarAsientos() {
 for (int f = 0; f < FILAS; f++) {
 for (int c = 0; c < COLUMNAS; c++) {
        asientos[f][c] = "Libre";
}
}
}
static void mostrarAsientos() {
 for (int f = 0; f < FILAS; f++) {
        System.out.println("\n" + tipos[f] + ":");
 for (int c = 0; c < COLUMNAS; c++) {
        System.out.print((c + 1) + ": [" + asientos[f][c] + "] ");    }
        System.out.println();
}
}
static void mostrarTiposDeAsiento() {
 for (int j = 0; j < tipos.length; j++) {
        System.out.println((j + 1) + ". " + tipos[j] + " ($" + precios[j] + ")");
}
}
static String generarBoleta(String tipo, int asiento, double precio, double descuento) {
 return "Ubicación: " + tipo +
        "\nAsiento: " + asiento +
        "\nPrecio base: $" + precio +
        "\nDescuento aplicado: " + (int)(descuento * 100) + "%" +
        "\nPrecio final: $" + (precio - (precio * descuento));
}
static int leerEntero(String mensaje) {
 int valor;
 while (true) {
        System.out.print(mensaje);
 if (scanner.hasNextInt()) {
        valor = scanner.nextInt();
                scanner.nextLine();
 break;
} else {
        System.out.println("Error: Ingrese un número válido.");
                scanner.next();
}
}
 return valor;
}
static void venderEntrada() {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
 while (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
        System.out.println("Nombre no válido. Intente nuevamente.");
        System.out.print("Ingrese su nombre: ");
        nombre = scanner.nextLine();
}
 int cantidad = 1;
        System.out.print("Tiene acompañantes? (si/no): ");
        String tieneAcomp = scanner.nextLine();
 if (tieneAcomp.equalsIgnoreCase("si")) {
 int acomp = leerEntero("Cuántos acompañantes?: ");
 if (acomp >= 0) cantidad += acomp;
}

 int ordenActual = numeroOrden++;
        nombresOrden[ordenActual] = nombre;
        System.out.println("Su número de orden es: " + ordenActual);
 for (int i = 0; i < cantidad; i++) {
 int edad = leerEntero((i == 0 ? "Tu edad: " : "Edad acompañante " + i + ": "));
 while (edad < 1 || edad > 100) {
        System.out.println("Edad no válida. Intente nuevamente.");
        edad = leerEntero((i == 0 ? "Tu edad: " : "Edad acompañante " + i + ": "));
}
 int tipo;
 do {
        System.out.println("Seleccione tipo de asiento:");
        mostrarTiposDeAsiento();
        tipo = leerEntero("");
} while (tipo < 1 || tipo > tipos.length);
        tipo--;
 int asiento;
 do {
        mostrarAsientos();
        asiento = leerEntero("Número de asiento (1-10): ");
} while (asiento < 1 || asiento > COLUMNAS || !asientos[tipo][asiento - 1].equals("Libre"));
  double precio = precios[tipo];
  double descuento = 0;
  if (edad <= 18) descuento = 0.10;
  else if (edad >= 65) descuento = 0.15;
  else {
        System.out.print("Es estudiante? (si/no): ");
        String estudiante = scanner.nextLine();
  if (estudiante.equalsIgnoreCase("si")) {
        descuento = 0.10;
}
}
  double total = precio - (precio * descuento);
  if (edad > 19 && edad <= 64) {
        System.out.println("Se le solicitará el carnet estudiantil en la entrada");
}
        asientos[tipo][asiento - 1] = "Comprado";
        clientes[tipo][asiento - 1] = String.valueOf(ordenActual);
        ventas[tipo][asiento - 1] = generarBoleta(tipos[tipo], asiento, precio, descuento);
        listaReservas.add(new Reserva(ordenActual, nombre, edad, tipos[tipo], asiento, total, descuento));
        System.out.println("Compra realizada con éxito.");
}
}
static void modificarPorID() {
        System.out.print("Ingrese el número de ID: ");
        String orden = scanner.nextLine();
 boolean encontrado = false;
 for (int f = 0; f < FILAS; f++) {
 for (int c = 0; c < COLUMNAS; c++) {
 if (clientes[f][c] != null && clientes[f][c].equals(orden)) {
        System.out.println("\nAsiento " + tipos[f] + " - " + (c + 1));
        System.out.println(ventas[f][c]);
 int opcion = leerEntero("¿Qué desea hacer con el asiento?\n1. Modificarlo\n2. Eliminarlo\n3. Dejarlo igual\nElija: ");
 if (opcion == 1) {
        asientos[f][c] = "Libre";
        clientes[f][c] = null;
        ventas[f][c] = null;
 int tipoNuevo;
 do {
        System.out.println("Seleccione nuevo tipo de asiento:");
        mostrarTiposDeAsiento();
        tipoNuevo = leerEntero("") - 1;
} while (tipoNuevo < 0 || tipoNuevo >= FILAS);
  int numNuevo;
  do {
        mostrarAsientos();
        numNuevo = leerEntero("Número de asiento (1-10): ") - 1;
} while (numNuevo < 0 || numNuevo >= COLUMNAS || !asientos[tipoNuevo][numNuevo].equals("Libre"));
  int nuevaEdad = leerEntero("Edad para este nuevo asiento: ");
  while (nuevaEdad < 1 || nuevaEdad > 100) {
        System.out.println("Edad no válida. Intente nuevamente.");
        nuevaEdad = leerEntero("Edad para este nuevo asiento: ");
}
  double precio = precios[tipoNuevo];
  double desc = 0;
  if (nuevaEdad <= 18) desc = 0.10;
  else if (nuevaEdad >= 65) desc = 0.15;
  else {
        System.out.print("Es estudiante? (si/no): ");
        String respuesta = scanner.nextLine();
  if (respuesta.equalsIgnoreCase("si")) {
        desc = 0.10;
}
}
  double total = precio - (precio * desc);
  if (nuevaEdad > 19 && nuevaEdad <= 64) {
        System.out.println("Se le solicitará el carnet estudiantil en la entrada");
}
        asientos[tipoNuevo][numNuevo] = "Comprado";
        clientes[tipoNuevo][numNuevo] = orden;
        ventas[tipoNuevo][numNuevo] = generarBoleta(tipos[tipoNuevo], (numNuevo + 1), precio, desc);
  try {
  int id = Integer.parseInt(orden);
  if (id >= 0 && id < nombresOrden.length && nombresOrden[id] != null) {
        listaReservas.add(new Reserva(id, nombresOrden[id], nuevaEdad, tipos[tipoNuevo], (numNuevo + 1), total, desc));
} else {
        System.out.println("ID inválido o no registrado.");
}
} catch (NumberFormatException e) {
        System.out.println("ID ingresado no es un número válido.");
}
        System.out.println("Asiento modificado correctamente.");
} else if (opcion == 2) {
        asientos[f][c] = "Libre";
        clientes[f][c] = null;
        ventas[f][c] = null;
  try {
  int idEliminar = Integer.parseInt(orden);
  final String tipoEliminar = tipos[f];
  final int numEliminar = c + 1;
        listaReservas.removeIf(r -> r.idReserva == idEliminar && r.tipoAsiento.equals(tipoEliminar) && r.numAsiento == numEliminar);
} catch (NumberFormatException e) {
        System.out.println("ID inválido al intentar eliminar reserva.");
}
        System.out.println("Asiento eliminado correctamente.");
}
        encontrado = true;
}
}
}
  if (!encontrado) {
        System.out.println("No se encontraron asientos con ese número de ID.");
}
}
static void imprimirBoleta() {
        System.out.print("Ingrese el número de ID: ");
        String id = scanner.nextLine();
  boolean encontrado = false;
  double totalFinal = 0;
        System.out.println("\n========= BOLETA ==========");
  try {
  int orden = Integer.parseInt(id);
        System.out.println("Cliente: " + nombresOrden[orden]);
  for (Reserva r : listaReservas) {
  if (r.idReserva == orden) {
        System.out.println("Ubicación: " + r.tipoAsiento);
        System.out.println("Asiento: " + r.numAsiento);
        System.out.println("Precio base: $" + (r.precioFinal / (1 - r.descuentoAplicado)));
        System.out.println("Descuento aplicado: " + (int)(r.descuentoAplicado * 100) + "%");
        System.out.println("Precio final: $" + r.precioFinal);
        System.out.println("---------------------------");
        totalFinal += r.precioFinal;
        encontrado = true;
}
}
  if (encontrado) {
        System.out.println("TOTAL A PAGAR: $" + totalFinal);
        System.out.println("============================================");
        System.out.println("¡Gracias por su compra. Disfrute la función!");
        System.out.println("============================================");
} else {
        System.out.println("No se encontró una venta con ese número de ID.");
}
} catch (NumberFormatException e) {
        System.out.println("ID inválido.");
}
}
}
}