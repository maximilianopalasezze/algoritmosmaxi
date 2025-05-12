package EFT_s9_Maximiliano_Palasezze;

import java.util.*;
// Clase que representa una entrada de teatro
class Entrada {
  static int contador = 1; // contador para generar ID de cada entrada vendida
   int id; 
   String nombre;
   int edad;
   String genero; 
   boolean estudiante; 
   String tipoAsiento; 
   int numeroAsiento; 
   double precioBase; // precio base sin descuentos
   ArrayList<String> descuentosAplicados = new ArrayList<>(); // lista para aplicar descuentos
   double precioFinal; // precio con descuentos aplicados
 //prueba de optimizacion para verificar datos que llegan al crear una entrada
public Entrada(String nombre, int edad, String genero, boolean estudiante, String tipoAsiento, int numeroAsiento) {
  this.id = contador++; // se asigna el ID automáticamente
  this.nombre = nombre;
  this.edad = edad;
  this.genero = genero;
  this.estudiante = estudiante;
  this.tipoAsiento = tipoAsiento;
  this.numeroAsiento = numeroAsiento;
  this.precioBase = obtenerPrecio(tipoAsiento); 
  this.precioFinal = calcularPrecioFinal();
}
private double obtenerPrecio(String tipo) {
  return switch (tipo.toLowerCase()) {
   case "vip" -> 30000;
   case "platea baja" -> 15000;
   case "platea alta" -> 18000;
   case "palco" -> 13000;
   case "galeria" -> 10000;
   default -> 0;
};
}
// prueba de optimizacion para validar qué descuento se aplica
private double calcularPrecioFinal() {
  double total = precioBase;
   if (edad <= 12) {
    descuentosAplicados.add("Niño (10%)");
    total -= precioBase * 0.10;
}  else if (edad >= 65) {
    descuentosAplicados.add("Tercera Edad (25%)");
    total -= precioBase * 0.25;
}  else if (genero.equals("2")) {
    descuentosAplicados.add("Mujer (20%)");
    total -= precioBase * 0.20;
}  else if (estudiante) {
    descuentosAplicados.add("Estudiante (15%)");
    total -= precioBase * 0.15;
}
   return total;
}
// se imprime la boleta con el detalle de la entrada comprada
public void imprimirBoleta() {
        System.out.println("---- Boleta ----");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Género: " + (genero.equals("2") ? "Mujer" : "Hombre"));
        System.out.println("Tipo de asiento: " + tipoAsiento);
        System.out.println("Número de asiento: " + numeroAsiento);
        System.out.printf("Precio base: $%.0f\n", precioBase);
   if (!descuentosAplicados.isEmpty()) {
        System.out.println("Descuento aplicado: " + String.join(", ", descuentosAplicados));
}  else {
        System.out.println("Descuento aplicado: Ninguno");
        }
        System.out.printf("Precio final: $%.0f\n", precioFinal);
        System.out.println("-----------------------------------------");
        System.out.println("Gracias por su compra. Disfrute la función.");
        System.out.println("-----------------------------------------\n");
}
}
public class EFT_s9_Maximiliano_Palasezze {
  static LinkedList<Entrada> entradas = new LinkedList<>();
  static Scanner scanner = new Scanner(System.in);
  static boolean[] vip = new boolean[10];
  static boolean[] plateaBaja = new boolean[10];
  static boolean[] plateaAlta = new boolean[10];
  static boolean[] palco = new boolean[10];
  static boolean[] galeria = new boolean[10];

public static void main(String[] args) {
  int opcion = 0;
        System.out.println("Bienvenido al teatro moro");
  while (true) {
        System.out.println("\n  MENÚ DE OPCIONES");
        System.out.println("1. Venta de entradas");
        System.out.println("2. Ver entradas vendidas");
        System.out.println("3. Ver asientos disponibles");
        System.out.println("4. Eliminar entrada por ID");
        System.out.println("5. Mostrar resumen total de ventas");
        System.out.println("6. Salir");
        System.out.print("Elige una opción: ");
  try {
        opcion = Integer.parseInt(scanner.nextLine());
} catch (NumberFormatException e) {
        System.out.println("Opción no válida. Intenta nuevamente.");
  continue;
}
  switch (opcion) {
   case 1 -> venderEntrada();
   case 2 -> verEntradasVendidas();
   case 3 -> mostrarAsientosDisponibles();
   case 4 -> eliminarEntradaPorId();
   case 5 -> mostrarResumen();
   case 6 -> {
        System.out.println("Gracias por su compra.\nDisfrute la funcion");
   return;
}
   default -> System.out.println("Opción no válida. Intenta nuevamente.");
}
}
}
// prueba de optimizacion para revisar flujo de validaciones y construcción de entrada
public static void venderEntrada() {
  String nombre;
  do {
        System.out.print("Ingrese su nombre: ");
        nombre = scanner.nextLine().trim();
  if (nombre.isEmpty()) {
        System.out.println("debe ingresar un nombre para continuar.");
} else if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
        System.out.println("Nombre no válido.Intente nuevamente");
        nombre = "";
}
} while (nombre.isEmpty());
  int edad = -1;
  while (true) {
  try {
        System.out.print("Ingrese edad: ");
        edad = Integer.parseInt(scanner.nextLine());
  if (edad >= 0 && edad <= 100) break;
  else  System.out.println("Edad no válida. Intente nuevamente.");
} catch (NumberFormatException e) {
        System.out.println("Dato no válido. Debe ingresar su edad en número.");
}
}
  String genero = "";
  while (true) {
  try {
        System.out.print("Es usted: \n(1)Hombre \n(2)Mujer\nIngrese opción: ");
        genero = scanner.nextLine();
  if (genero.equals("1") || genero.equals("2")) break;
  else  System.out.println("Opción no válida. Intente nuevamente.");
} catch (Exception e) {
        System.out.println("Entrada no válida. Intente nuevamente.");
}
}
  boolean estudiante = false;
  if (edad > 12 && edad < 65) {
        String respuesta;
  while (true) {
        System.out.print("¿Es estudiante? (si/no): ");
        respuesta = scanner.nextLine().toLowerCase();
  if (respuesta.equals("si") || respuesta.equals("no")) {
        estudiante = respuesta.equals("si");
  break;
} else {
        System.out.println("Entrada inválida. Escriba 'si' o 'no'.");
}
}
}

  if (genero.equals("2") || estudiante) {
        System.out.println("No olvides de traer tu carnet para validar tus descuentos.");
}
  String tipoAsiento;
 int numeroAsiento;

  while (true) {
        tipoAsiento = elegirTipoAsiento(); // nueva función
        numeroAsiento = seleccionarAsiento(tipoAsiento);
  if    (numeroAsiento == -1) {
        System.out.println("Sección sin asientos disponibles. Por favor, elija otra sección.");
} else {
  break;
}
}
// prueba de optimizacion para verificar valores antes de crear la entrada
        Entrada entrada = new Entrada(nombre, edad, genero, estudiante, tipoAsiento, numeroAsiento);
        entradas.add(entrada);
        System.out.println("El ID de esta entrada es: " + entrada.id);
   if (!entrada.descuentosAplicados.isEmpty()) {
        System.out.println("No olvides de traer tu carnet para validar tus descuentos.");
}
        entrada.imprimirBoleta();
}
public static String elegirTipoAsiento() {
    String tipoAsiento = "";
   while (true) {
   try {
        System.out.println("Seleccione tipo de asiento:");
        System.out.println("1. VIP ($30000)");
        System.out.println("2. Platea Baja ($15000)");
        System.out.println("3. Platea Alta ($18000)");
        System.out.println("4. Palco ($13000)");
        System.out.println("5. Galería ($10000)");
        System.out.print("Ingrese número: ");
        String opcion = scanner.nextLine();    
  switch (opcion) {
    case "1" -> tipoAsiento = "vip";
    case "2" -> tipoAsiento = "platea baja";
    case "3" -> tipoAsiento = "platea alta";
    case "4" -> tipoAsiento = "palco";
    case "5" -> tipoAsiento = "galeria";
    default -> {
        System.out.println("Opción no válida. Intente nuevamente.");
    continue;
}
}
  break;
} catch (Exception e) {
        System.out.println("Entrada no válida. Intente nuevamente.");
}
}
  return tipoAsiento;
}
// prueba de optimizacion para validar la lógica de asignación de asientos disponibles
public static int seleccionarAsiento(String tipo) {
   boolean[] fila = switch (tipo) {
    case "vip" -> vip;
    case "platea baja" -> plateaBaja;
    case "platea alta" -> plateaAlta;
    case "palco" -> palco;
    case "galeria" -> galeria;
   default -> null;
};
   if (fila == null) return -1;
   while (true) {
        System.out.println("Asientos disponibles:");
   for (int i = 0; i < fila.length; i++) {
        System.out.print(fila[i] ? "[X] " : "[" + (i + 1) + "] ");
}
        System.out.print("\nSeleccione un número de asiento (1 a 10):");
   int asiento;
   try {
        asiento = Integer.parseInt(scanner.nextLine());
}  catch (Exception e) {
        System.out.println("Entrada inválida.");
   continue;
}
   if (asiento < 1 || asiento > 10) System.out.println("Fuera de rango.");
   else if (fila[asiento - 1]) System.out.println("Asiento ocupado.");
   else {
        fila[asiento - 1] = true;
   return asiento;
}
}
}
public static void verEntradasVendidas() {
   if (entradas.isEmpty()) System.out.println("No se han vendido entradas.");
   else for (Entrada e : entradas) e.imprimirBoleta();
}
public static void mostrarAsientosDisponibles() {
        mostrarFila("VIP", vip);
        mostrarFila("Platea Baja", plateaBaja);
        mostrarFila("Platea Alta", plateaAlta);
        mostrarFila("Palco", palco);
        mostrarFila("Galería", galeria);
}
public static void mostrarFila(String nombre, boolean[] fila) {
        System.out.print(nombre + ": ");
   for (int i = 0; i < fila.length; i++) {
        System.out.print(fila[i] ? "[X] " : "[" + (i + 1) + "] ");
}
        System.out.println();
}
// prueba de optimizacion para comprobar el comportamiento cuando se ingresa un ID válido o inválido
public static void eliminarEntradaPorId() {
        System.out.print("Ingrese el ID de la entrada a eliminar: ");
   int idEliminar;
   try {
       idEliminar = Integer.parseInt(scanner.nextLine());
}  catch (Exception e) {
        System.out.println("ID no válido.");
   return;
}
   boolean eliminado = false;
        Iterator<Entrada> it = entradas.iterator();
   while (it.hasNext()) {
        Entrada e = it.next();
    if (e.id == idEliminar) {
        it.remove();
        eliminado = true;
        System.out.println("Entrada eliminada correctamente.");
    break;
}
}
    if (!eliminado) {
        System.out.println("No se encontró una entrada con ese ID.");
}
}
// Muestra resumen total recaudado por la venta de entradas
public static void mostrarResumen() {
    int total = entradas.size();
    double montoTotal = 0;
     // prueba de optimizacion para verificar suma del total recaudado
    for (Entrada e : entradas) {
        montoTotal += e.precioFinal;
}
        System.out.println("Total de entradas vendidas: " + total);
        System.out.println("Monto total recaudado: $" + montoTotal);
}
}