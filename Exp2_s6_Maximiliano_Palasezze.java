package teatromoro20;

import java.util.Scanner;

public class Exp2_s6_Maximiliano_Palasezze {

    static String[] vip = {"[ ]","[ ]","[ ]","[ ]","[ ]"};
    static String[] plateaAlta = {"[ ]","[ ]","[ ]","[ ]","[ ]"};
    static String[] plateaBaja = {"[ ]","[ ]","[ ]","[ ]","[ ]"};
    static String[] general = {"[ ]","[ ]","[ ]","[ ]","[ ]"};

    static String ultimaUbicacion = "";
    static int ultimaCantidad = 0;
    static int ultimoTotal = 0;

    public static void main(String[] args) 
     throws InterruptedException {
            Scanner scanner = new Scanner(System.in);

            String nombreTeatro = "Teatro Moro";
     int opcion = 0;
            System.out.println("bienvenidos al teatro moro?\n");
            Thread.sleep(1*1000);
            System.out.println("cargando menu un momento por favor");
            Thread.sleep(1*1000);
     do {
            System.out.println("\n MENÚ TEATRO MORO ");
            System.out.println("1. Ver asientos del teatro");
            System.out.println("2. Reservar asientos");
            System.out.println("3. Comprar asientos");
            System.out.println("4. Imprimir boleta");
            System.out.println("5. Modificar reserva/compra");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
     if (!scanner.hasNextInt()) {
            System.out.println("Opción inválida. Intente nuevamente.");
            scanner.nextLine();
     continue;
   }
            opcion = scanner.nextInt();

     switch (opcion) {
      case 1 -> mostrarPlano();
      case 2 -> gestionarReserva(scanner);
      case 3 -> {
            scanner.nextLine();
            String respuesta;
     do {
            System.out.print("¿Desea comprar una reserva ya hecha? (si/no): ");
            respuesta = scanner.nextLine().trim().toLowerCase();
      if (!respuesta.equals("si") && !respuesta.equals("no")) {
            System.out.println("Respuesta inválida. Escriba 'si' o 'no'.");
   }
   } while (!respuesta.equals("si") && !respuesta.equals("no"));

      if (respuesta.equals("si")) {
     boolean hayReservas = false;
      for (int tipo = 1; tipo <= 4; tipo++) {
            String[] fila = obtenerFila(tipo);
      for (int i = 0; i < fila.length; i++) {
      if (fila[i].equals("[R]")) {
       // punto de debuggin 1: Transición de la compra de reserva
            fila[i] = "[X]";
            hayReservas = true;
   }
   }
   }
      if (hayReservas && ultimoTotal > 0) {
            System.out.println("Reserva comprada con éxito. Total pagado (con descuentos aplicados en la reserva): $" + ultimoTotal);
   }  else {
            System.out.println("No hay reservas para comprar.");
   }
   }  else {
            gestionarCompra(scanner, false);
   }
   }
      case 4 -> imprimirBoleta(nombreTeatro);
      case 5 -> {
           scanner.nextLine();
           System.out.print("¿Desea modificar una reserva o una compra? (reserva/compra): ");
    String tipoMod = scanner.nextLine().trim().toLowerCase();
     if (tipoMod.equals("reserva")) {
        modificarReserva(scanner);
   } else if (tipoMod.equals("compra")) {
        modificarCompra(scanner);
   } else {
           System.out.println("Tipo inválido. Debe escribir 'reserva' o 'compra'.");
   }
   }
   
    default -> {
   }
   }
   } while (opcion != 6);
           Thread.sleep(1*1000);
           System.out.println("Gracias por su compra disfrute la funcion");
   }
    public static void mostrarPlano() {
           System.out.println("\n===== PLANO DE ASIENTOS =====");
           System.out.print("VIP:           ");
    for (String asiento : vip) System.out.print(asiento + " ");
           System.out.print("\nPlatea Alta:   ");
    for (String asiento : plateaAlta) System.out.print(asiento + " ");
           System.out.print("\nPlatea Baja:   ");
    for (String asiento : plateaBaja) System.out.print(asiento + " ");
           System.out.print("\nGeneral:       ");
    for (String asiento : general) System.out.print(asiento + " ");
           System.out.println("\n\n[ ] = Disponible | [R] = Reservado | [X] = Comprado");
   }
    public static void gestionarReserva(Scanner scanner) 
    throws InterruptedException {
    int tipo = seleccionarTipo(scanner);
    String[] fila = obtenerFila(tipo);
    int precio = obtenerPrecio(tipo);
    int cantidad = 0;
    do {
            System.out.print("¿Cuántos asientos desea reservar? (1-5): ");
    try {
            cantidad = scanner.nextInt();
     if (cantidad < 1 || cantidad > 5) {
            System.out.println("Cantidad no disponible. Intente nuevamente.");
            }
   } catch (Exception e) {
                System.out.println("Entrada inválida. Ingrese un número válido.");
                scanner.nextLine();
                int i = 0;
                i--;
   }
   
   } while (cantidad < 1 || cantidad > 5);
    int total = 0;
    for (int i = 0; i < cantidad; i++) {
            System.out.print("Seleccione número de asiento (1-5): ");
    int nro = scanner.nextInt();
        // punto de debuggin 2: Validación de número de asientos
     if (nro < 1 || nro > 5 || fila[nro - 1].equals("[X]") || fila[nro - 1].equals("[R]")) {
            System.out.println("Asiento no disponible o fuera de rango. Intente nuevamente.");
            i--;
   } else {
    double descuento = calcularDescuento(scanner);
            fila[nro - 1] = "[R]";
    int precioFinal = (int) (precio * (1 - descuento));
            total += precioFinal;
            System.out.println("Asiento " + nro + " reservado con exito. Precio con descuento: $" + precioFinal);
   }
   }
        // punto de debuggin 3: Confirmar datos de reserva
            System.out.println("\nTotal a pagar por la reserva: $" + total);
    ultimaUbicacion = obtenerNombreUbicacion(tipo);
    ultimaCantidad = cantidad;
    ultimoTotal = total;
   }
    public static void gestionarCompra(Scanner scanner, boolean desdeReserva) 
    throws InterruptedException {
    int tipo = seleccionarTipo(scanner);
            String[] fila = obtenerFila(tipo);
    int precio = obtenerPrecio(tipo);
    int cantidad;
    do {
            System.out.print("¿Cuántos asientos desea comprar? (1-5): ");
            cantidad = scanner.nextInt();
   } while (cantidad < 1 || cantidad > 5);
    int total = 0;
    for (int i = 0; i < cantidad; i++) {
            System.out.print("Seleccione número de asiento (1-5): ");
    int nro = scanner.nextInt();
     if (nro < 1 || nro > 5 || fila[nro - 1].equals("[X]") || (!desdeReserva && fila[nro - 1].equals("[R]"))) {
            System.out.println("Asiento no disponible o fuera de rango. Intente nuevamente.");
            i--;
   } else {
    double descuento = calcularDescuento(scanner);
    int precioFinal = (int) (precio * (1 - descuento));
     if (desdeReserva && fila[nro - 1].equals("[R]")) {
            System.out.println("Reserva comprada con éxito.");
   } else {
            System.out.println("Asiento " + nro + " comprado.");
   }
            fila[nro - 1] = "[X]";
            System.out.println("Precio final: $" + precioFinal);
            total += precioFinal;
   }
   }
    ultimaUbicacion = obtenerNombreUbicacion(tipo);
    ultimaCantidad = cantidad;
    ultimoTotal = total;
            System.out.println("\nTotal a pagar: $" + total);
   }
    public static void imprimirBoleta(String teatro) {
     if (ultimaCantidad > 0) {
            System.out.println("\n===== BOLETA =====");
            System.out.println("Teatro: " + teatro);
            System.out.println("Ubicación: " + ultimaUbicacion);
            System.out.println("Cantidad: " + ultimaCantidad);
            System.out.println("Total: $" + ultimoTotal);
     if (ultimoTotal < ultimaCantidad * 20000) { // Se asume que VIP es el precio máximo sin descuento
            System.out.println("Se aplicó un descuento especial (estudiante o tercera edad).");
   }
   } else {
          // punto de debuggin 4: Validar impresión boleta
            System.out.println("\nNo hay compras registradas para imprimir boleta.");
   }
   }
    public static void modificarReserva(Scanner scanner) {
    int tipo = seleccionarTipo(scanner);
            String[] fila = obtenerFila(tipo);
            System.out.print("Seleccione número de asiento reservado a modificar (1-5): ");
    int nro = scanner.nextInt();
     if (nro < 1 || nro > 5 || !fila[nro - 1].equals("[R]")) {
            System.out.println("Ese asiento no está reservado. No se puede modificar.");
   } else {
            fila[nro - 1] = "[ ]";
            System.out.println("Asiento " + nro + " eliminado.");
   }
   }
    public static void modificarCompra(Scanner scanner) {
    int tipo = seleccionarTipo(scanner);
    String[] fila = obtenerFila(tipo);
            System.out.print("Seleccione número de asiento comprado a modificar (1-5): ");
    int nro = scanner.nextInt();
     if (nro < 1 || nro > 5 || !fila[nro - 1].equals("[X]")) {
            System.out.println("Ese asiento no está comprado. No se puede modificar.");
   } else {
            fila[nro - 1] = "[ ]";
            System.out.println("Asiento " + nro + " ha sido liberado y puede comprarse de nuevo.");
   }
   }
    public static int seleccionarTipo(Scanner scanner) {
    int tipo = 0;
    while (tipo < 1 || tipo > 4) {
            System.out.println("1. VIP ($20000)");
            System.out.println("2. Platea Alta ($9000)");
            System.out.println("3. Platea Baja ($10000)");
            System.out.println("4. General ($6500)");
            System.out.print("Ingrese una opción válida (1-4): ");
            tipo = scanner.nextInt();
     if (tipo < 1 || tipo > 4) {
            System.out.println("Opción incorrecta. Intente nuevamente.");
   }
   }
    return tipo;
   }
    public static String[] obtenerFila(int tipo) {
     if (tipo == 1) return vip;
     if (tipo == 2) return plateaAlta;
     if (tipo == 3) return plateaBaja;
    return general;
   }

    public static String obtenerNombreUbicacion(int tipo) {
     if (tipo == 1) return "VIP";
     if (tipo == 2) return "Platea Alta";
     if (tipo == 3) return "Platea Baja";
    return "General";
   }
    public static int obtenerPrecio(int tipo) {
     if (tipo == 1) return 20000;
     if (tipo == 2) return 9000;
     if (tipo == 3) return 10000;
    return 6500;
   }
    public static double calcularDescuento(Scanner scanner) 
    throws InterruptedException {
    int edad = 0;
    do {
            System.out.print("Ingrese su edad : ");
            edad = scanner.nextInt();
    if (edad < 1 || edad > 100) {
            System.out.println("Edad no válida. Intente nuevamente.");
   }
   } while (edad < 1 || edad > 100);
            scanner.nextLine(); // limpiar buffer
     if (edad >= 60) {
            System.out.println("Descuento del 15% aplicado (Tercera edad).");
    return 0.15;
   } else if (edad <= 18) {
            System.out.println("Descuento del 10% aplicado (Estudiante menor de edad).");
    return 0.10;
   } else {
            String respuesta = "";
    do {
            System.out.print("¿Es usted estudiante? (si/no): ");
            respuesta = scanner.nextLine().trim().toLowerCase();
     if (!respuesta.equals("si") && !respuesta.equals("no")) {
            System.out.println("Respuesta no válida. Escriba 'si' o 'no'.");
   }
   } while (!respuesta.equals("si") && !respuesta.equals("no"));
     if (respuesta.equals("si")) {
            System.out.println("Descuento del 10% aplicado (Estudiante adulto).");
            Thread.sleep(1*1000);
            System.out.println("se solicitara su carnet estudiantil para validar su entrada");
            Thread.sleep(1*1000);
    return 0.10;
   }
   }
    return 0.0;
   }
}
