package Exp1_S1_Maximiliano_Palasezze_Grupo12;

import java.util.Scanner;

public class Exp1_S1_Maximiliano_Palasezze_Grupo12 {
    
public static void main(String[] args) {
  try (Scanner scanner = new Scanner(System.in)) {
    Cliente cliente = null;
  int opcion;
      System.out.println("Bienvenido a BankBoston");
  do {
      System.out.println("\n--- Menú Principal ---");
      System.out.println("1. Registrar cliente");
      System.out.println("2. Ver datos de cliente");
      System.out.println("3. Depositar");
      System.out.println("4. Girar");
      System.out.println("5. Consultar saldo");
      System.out.println("6. Salir");
      System.out.print("Seleccione una opción: ");
  while (!scanner.hasNextInt()) {
      System.out.println("Opcion no válida. Intente nuevamente.");
      System.out.print("Seleccione una opción: ");
    scanner.next();
}
    opcion = scanner.nextInt();
    scanner.nextLine();
  switch (opcion) {
   case 1 -> {
   if (cliente != null) {
      System.out.println("Ya hay un cliente registrado.");
  break;
}
    String rut;
  do {
      System.out.print("Ingrese su rut con putnos y digito verificador: ");
     rut = scanner.nextLine().toLowerCase();
   if (!(rut.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9kK]") && (rut.length() == 12 || rut.length() == 11))) {
      System.out.println("RUT no válido. Intente nuevamente.");
     rut = null;
}
}  while (rut == null);
     String nombre;
   do {
      System.out.print("Nombre: ");
     nombre = scanner.nextLine();                
   if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
      System.out.println("Nombre no válido. Intente nuevamente.");
     nombre = null;
}
}  while (nombre == null);
     String apPat;
   do {
      System.out.print("Apellido Paterno: ");
     apPat = scanner.nextLine();
   if (!apPat.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
      System.out.println("Apellido no válido. Intente nuevamente.");
     apPat = null;
}
}  while (apPat == null);
     String apMat;
   do {
      System.out.print("Apellido Materno: ");
     apMat = scanner.nextLine();
   if (!apMat.isBlank() && !apMat.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
      System.out.println("Apellido no válido. Intente nuevamente.");
     apMat = null;
}
}  while (apMat == null);
     String domicilio;
   do {
      System.out.print("Domicilio o calle: ");
     domicilio = scanner.nextLine();
   if (!domicilio.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
      System.out.println("Domicilio o calle no válido/a. Intente nuevamente.");
     domicilio = null;                           }
}  while (domicilio == null);
     String numeroCasa;
   do {
      System.out.print("Número: ");
     numeroCasa = scanner.nextLine();
   if (!numeroCasa.matches("[0-9]+")) {
      System.out.println("Número no válido. Intente nuevamente.");
     numeroCasa = null;
}
}  while (numeroCasa == null);
     String comuna;
   do {
      System.out.print("Comuna: ");
     comuna = scanner.nextLine();
   if (!comuna.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
      System.out.println("Comuna no válida. intente nuevamente.");
     comuna = null;
}
}  while (comuna == null);
     String telefono;
   do {
      System.out.print("Teléfono: +56 ");
     telefono = scanner.nextLine();
   if (!telefono.matches("\\d{9}")) {
      System.out.println("Teléfono no válido. Intente nuevamente.");
     telefono = null;
}
}  while (telefono == null);
   int numCuenta = -1;
   do {
      System.out.print("Número de cuenta: ");
   if (scanner.hasNextInt()) {
     numCuenta = scanner.nextInt();
     scanner.nextLine();
}  else {
      System.out.println("Número no válido. Intente nuevamente.");
     scanner.next();
}
}  while (numCuenta <= 0);
   try {
     cliente = new Cliente(rut, nombre, apPat, apMat, domicilio + " #" + numeroCasa, comuna, telefono, numCuenta);
      System.out.println("Cliente registrado exitosamente.");
}  catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
}
}
   case 2 -> {
   if (cliente != null) {
     cliente.verDatosCliente();
}  else {
      System.out.println("No hay aun clientes registrados.");
}
}
   case 3 -> {
   if (cliente != null) {
   int monto = -1;
  do {
      System.out.print("Ingrese monto a depositar: ");
   if (scanner.hasNextInt()) {
     monto = scanner.nextInt();
     scanner.nextLine();
}  else {
      System.out.println("Monto no válido. Intente nuevamente.");
     scanner.next();
}
}  while (monto <= 0);
     cliente.getCuenta().depositar(monto);
}  else {
      System.out.println("Debe registrarse para realizar un depósito.");
}
}
   case 4 -> {
   if (cliente != null) {
   boolean exito = false;
   while (!exito) {
      System.out.print("Ingrese monto a girar: ");
   while (!scanner.hasNextDouble()) {
      System.out.println("Monto no válido. Intente nuevamente.");
     scanner.next();
}
   double monto = scanner.nextDouble();
     scanner.nextLine();
   if (cliente.getCuenta().getSaldo() >= monto) {
     cliente.getCuenta().girar(monto);
      System.out.println("Giro exitoso.");
     exito = true;
}  else {
      System.out.println("Saldo insuficiente. Intente nuevamente.");
        }
    }
} else {
      System.out.println("Debe registrarse para realizar un giro.");
}
}
  case 5 -> {
  if (cliente != null) {
     cliente.getCuenta().consultarSaldo();
} else {
      System.out.println("No hay clientes registrados para ver saldo.");
}
}
  case 6 ->  System.out.println("Saliendo bank Boston... Que tenga un buen dia ");
  default -> System.out.println("Opción no válida.");
}
} while (opcion != 6);
}
}
}
