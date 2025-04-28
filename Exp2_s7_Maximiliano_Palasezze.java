package Exp2_s7_Maximiliano_Palasezze;

import java.util.ArrayList;
import java.util.Scanner;

 class Venta {
  String ubicacion;
  double precioBase;
  double descuentoPorcentaje;
  double precioFinal;

 public Venta(String ubicacion, double precioBase, double descuentoPorcentaje) {
  this.ubicacion = ubicacion;
  this.precioBase = precioBase;
  this.descuentoPorcentaje = descuentoPorcentaje;
  this.precioFinal = precioBase * (1 - descuentoPorcentaje);
}
}
 public class Exp2_s7_Maximiliano_Palasezze {
  static int totalEntradasVendidas = 0;
  static double ingresosTotales = 0;
  static ArrayList<Venta> resumenVentas = new ArrayList<>();
  static String[] ubicaciones = {"VIP", "Platea Alta", "Platea Baja", "General"};
  static double[] precios = {20000, 9000, 10000, 6500};
  
 public static void main(String[] args) 
  throws InterruptedException {
      Scanner scanner = new Scanner(System.in);
  int opcion = 0;
       System.out.println("bienvenido al teatro moro");
       Thread.sleep(1*1000);
   do {
       System.out.println("\n=====  Menú de Opciones =====");
       System.out.println("1. Venta de entradas");
       System.out.println("2. Resumen de ventas");
       System.out.println("3. Imprimir boleta");
       System.out.println("4. Ingresos totales");
       System.out.println("5. Salir");
       System.out.print("Seleccione una opción: ");

   if (scanner.hasNextInt()) {
       opcion = scanner.nextInt();
}  else {
       System.out.println("Ingrese un número valido.");
       scanner.next();
   continue;
}
   switch (opcion) {
    case 1 -> venderEntrada(scanner);
    case 2 -> mostrarResumen();
    case 3 -> generarBoleta();
    case 4 -> System.out.println("Ingresos totales: $" + ingresosTotales);
    case 5 -> System.out.println("Gracias por su compra. Disfrute la funcion.");
    default -> System.out.println("Opción no valida. Intente nuevamente.");
}
}  while (opcion != 5);
}
 
 public static void venderEntrada(Scanner scanner) {
  boolean seguirComprando = true;
  while (seguirComprando) {
  int ubicacionIndex = -1;
  while (true) {
       System.out.println("\nSeleccione ubicación:");
  for (int i = 0; i < ubicaciones.length; i++) {
       System.out.println((i + 1) + ". " + ubicaciones[i] + " ($" + precios[i] + ")");
}
       System.out.print("Ingrese opción: ");
  if (scanner.hasNextInt()) {
  int opcionUbicacion = scanner.nextInt();
  if (opcionUbicacion >= 1 && opcionUbicacion <= ubicaciones.length) {
       ubicacionIndex = opcionUbicacion - 1;
       break;
} else {
       System.out.println("Opción no valida. Intente nuevamente.");
}
} else {
       System.out.println("caracter no valido. Debe ingresar un número.");
       scanner.next();
}
}
  double precioBase = precios[ubicacionIndex];
  double descuento = 0;
  while (true) {
       System.out.print("¿Es estudiante? (si/no): ");
       String estudiante = scanner.next().toLowerCase();
  if (estudiante.equals("si")) {
       descuento = 0.10;
       System.out.println("¡no olvides traer tu carnet para validar tu descuento!");
       System.out.println("si es mayor a 18 se solicitara carnet estudiantil");
       break;
} else if (estudiante.equals("no")) {
  while (true) {
       System.out.print("¿Es persona de la tercera edad? (si/no): ");
       String terceraEdad = scanner.next().toLowerCase();
  if (terceraEdad.equals("si")) {
      System.out.println("¡no olvides traer tu carnet para validar tu descuento!");
       descuento = 0.15;
       break;
} else if (terceraEdad.equals("no")) {
       break;
} else {
       System.out.println("respuesta no valida. Responda con 'si' o 'no'.");
}
}
       break;
} else {
       System.out.println("respuesta no valida. Responda con 'si' o 'no'.");
}
}
       Venta venta = new Venta(ubicaciones[ubicacionIndex], precioBase, descuento);
       resumenVentas.add(venta);
       ingresosTotales += venta.precioFinal;
       totalEntradasVendidas++;
       System.out.println("Entrada vendida con éxito.");
  while (true) {
       System.out.print("¿Desea comprar otra entrada? (si/no): ");
       String respuesta = scanner.next().toLowerCase();
  if (respuesta.equals("no")) {
       seguirComprando = false;
       break;
} else if (respuesta.equals("si")) {
       break;
} else {
       System.out.println("Respuesta no valida. Escriba 'si' o 'no'.");
}
}
}
}
 
 public static void mostrarResumen() {
       System.out.println("\n===== Resumen de Ventas =====");
  if (resumenVentas.isEmpty()) {
       System.out.println("No hay ventas registradas.");
} else {
  for (Venta venta : resumenVentas) {
       System.out.println("Ubicación: " + venta.ubicacion +" | Precio final: $" + venta.precioFinal +" | Descuento: " + (int)(venta.descuentoPorcentaje * 100) + "%");              
}
}
}
 
 public static void generarBoleta() {
       System.out.println("\n========== BOLETA ==========");
  if (resumenVentas.isEmpty()) {
       System.out.println("No hay ventas registradas.");
} else {
  double totalAPagar = 0;
  for (Venta venta : resumenVentas) {
       System.out.println("\nUbicación: " + venta.ubicacion);
       System.out.println("Precio base: $" + venta.precioBase);
  if (venta.descuentoPorcentaje > 0) {
       System.out.println("Descuento aplicado: " + (int)(venta.descuentoPorcentaje * 100) + "%");
} else {
       System.out.println("Sin descuento");
}
       System.out.println("Precio final: $" + venta.precioFinal);
       totalAPagar += venta.precioFinal;
}
       System.out.println("\nTOTAL A PAGAR: $" + totalAPagar);
       System.out.println("============================================");
       System.out.println("¡Gracias por su compra. Disfrute la función!");
       System.out.println("============================================");
}
}
}
