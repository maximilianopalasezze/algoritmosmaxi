package vista;

import controlador.Banco;
import modelo.CuentaCredito;
import modelo.CuentaCorriente;
import modelo.CuentaBancaria;
import modelo.CuentaAhorro;
import modelo.Cliente;
import java.util.Scanner;

public class Exp1_S2_Maximiliano_Palasezze_Grupo12 {
 public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      Banco banco = new Banco();
      banco.cargarClientes();
   int opcion;
      System.out.println("Bienvenido a Bank Boston");
  // Menú principal
   do {
      System.out.println("\n--- Menú Principal ---");
      System.out.println("1. Registrar cliente");
      System.out.println("2. Ver datos de clientes");
      System.out.println("3. Depositar");
      System.out.println("4. Girar");
      System.out.println("5. Consultar saldo");
      System.out.println("6. Ver estadisticas del banco");
      System.out.println("7. Eliminar clientes registrados");
      System.out.println("8. Mostrar información de la cuenta");
      System.out.println("9. Salir");
      System.out.print("Seleccione una opción: ");
   while (!scanner.hasNextInt()) {
      System.out.println("Opción no válida. Intente nuevamente");
      scanner.next();
}
      opcion = scanner.nextInt();
      scanner.nextLine();
  switch (opcion) {
   // registrar clientes
   case 1 -> {
      String rut, nombre, apPat, apMat, domicilio, comuna, telefono;
   int numero;
   while (true) {
      System.out.print("Ingrese su RUT (con puntos y guion): ");
      rut = scanner.nextLine();
   if (rut.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]") && rut.length() >= 11 && rut.length() <= 12) {
    // validacion para que no se registre un cliente nuevo con un rut ya existente
   if (banco.rutExiste(rut)) {
      System.out.println("Ya hay un cliente registrado con ese RUT. Intene nuevamente");
   continue; 
   }
   break;
}  else {
      System.out.println("RUT no válido, intente nuevamente.");
}
}
   do {
      System.out.print("Nombre: ");
      nombre = scanner.nextLine();
   if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
      System.out.println("Nombre no válido, intente nuevamente.");
}
}  while (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+"));
   do {
      System.out.print("Apellido paterno: ");
      apPat = scanner.nextLine();
   if (!apPat.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
      System.out.println("Apellido paterno no válido, intente nuevamente.");
}
}  while (!apPat.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+"));
   do{
      System.out.print("Apellido materno: ");
      apMat = scanner.nextLine();
   if (!apMat.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
      System.out.println("Apellido materno no válido, intente nuevamente.");                    
}
}  while (!apMat.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+"));
   do {
      System.out.print("Domicilio (calle): ");
      domicilio = scanner.nextLine();
   if (!domicilio.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
      System.out.println("Domicilio no válido. Intente nuevamente.");
}
}  while (!domicilio.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+"));
      System.out.print("Número del domicilio: ");
   while (!scanner.hasNextInt()) {
      System.out.println("Número no válido. Intente nuevamente.");
      scanner.next();
}
      numero = scanner.nextInt();
      scanner.nextLine();
   do {
      System.out.print("Comuna: ");
      comuna = scanner.nextLine();
   if (!comuna.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
      System.out.println("Comuna no válida. Intente nuevamente.");
}
}  while (!comuna.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+"));
   do {
      System.out.print("Teléfono: +56 ");
      telefono = scanner.nextLine();
   if (!telefono.matches("\\d{9}")) {
      System.out.println("Teléfono no válido. Intente nuevamente.");
}
}  while (!telefono.matches("\\d{9}"));
   int tipo;
   while (true) {
      System.out.println("Seleccione tipo de cuenta:");
      System.out.println("1. Cuenta Corriente");
      System.out.println("2. Cuenta Ahorro");
      System.out.println("3. Cuenta Crédito");
   if (!scanner.hasNextInt()) {
      System.out.println("Opción no válida. Intene nuevamente");
      scanner.next();
   continue;
}
      tipo = scanner.nextInt();
      scanner.nextLine();
   if (tipo >= 1 && tipo <= 3) break;
      System.out.println("Opción fuera de rango. Intente nuevamente.");
                    }
   // Número de cuenta generado a partir del RUT sin puntos ni guion
   int numCuenta = Integer.parseInt(rut.replace(".", "").replace("-", "").replaceAll("[a-zA-Z]", ""));
   double saldo;
   while (true) {
      System.out.print("Ingrese saldo inicial: ");
   if (!scanner.hasNextDouble()) {
      System.out.println("Dato no válido.El saldo debe ser mayor a 0");
      scanner.next();
}  else {
      saldo = scanner.nextDouble();
      scanner.nextLine();
   break;
}
}
    CuentaBancaria cuenta;
    cuenta = 
    switch (tipo) {
    case 1 -> new CuentaCorriente(numCuenta, saldo);
    case 2 -> new CuentaAhorro(numCuenta, saldo);
    default -> new CuentaCredito(numCuenta, saldo);
};
      Cliente cliente = new Cliente(rut, nombre, apPat, apMat, domicilio, numero, comuna, telefono, cuenta);
      banco.registrarCliente(cliente);
      System.out.println("Cliente registrado exitosamente.");
      System.out.println("Su número de cuenta es:" + cuenta.getNumeroCuenta());
}
   // metodo para ver los datos del cliente con el numero de cuenta
    case 2 -> {
    System.out.print("Ingrese el número de cuenta del cliente: ");
    int numero = scanner.nextInt();

    boolean encontrado = false;
    for (Cliente c : banco.getClientes()) {
    if (c.getCuenta().getNumeroCuenta() == numero) {
      c.mostrarDatos();
      encontrado = true;
    break;
}
}
    if (!encontrado) {
      System.out.println("Cliente no encontrado.");
}
}
    // metodo para depositar  
    case 3 -> {
      System.out.print("Ingrese número de cuenta: ");
    int cuentaDeposito = scanner.nextInt();
      System.out.print("Ingrese monto a depositar: ");
    double montoDep = scanner.nextDouble();
      banco.depositarEnCuenta(cuentaDeposito, montoDep);
}
    // metodo para girar
    case 4 -> {
      System.out.print("Ingrese número de cuenta: ");
    int cuentaGiro = scanner.nextInt();
      System.out.print("Ingrese monto a girar: ");
    double montoGiro = scanner.nextDouble();
      banco.girarDeCuenta(cuentaGiro, montoGiro);
}
    // metodo para consultar el saldo
    case 5 -> {
      System.out.print("Ingrese número de cuenta: ");
    int cuentaConsulta = scanner.nextInt();
      banco.consultarSaldoCuenta(cuentaConsulta);
}
    // metodo para ver las estadisticas del banco
    case 6 -> banco.mostrarEstadisticasGenerales();
    // metodo para eliminar clientes registrados
    case 7 -> {
      banco.eliminarTodosLosClientes();
    break;
}
    case 8 -> {
    System.out.print("Ingrese número de cuenta: ");
    int num = scanner.nextInt();
    Cliente c = banco.buscarClientePorCuenta(num); // asumimos que existe este método
    if (c != null && c.getCuenta() instanceof interfaces.OperacionesBancarias) {
        interfaces.OperacionesBancarias op = (interfaces.OperacionesBancarias) c.getCuenta();
        op.mostrarInformacion();
        op.procesarOperacion();
    } else {
        System.out.println("Cuenta no encontrada o no válida.");
    }
    break; 
}
    // metodo para salir del programa
    case 9-> { 
      banco.guardarClientes(); 
      System.out.println("Saliendo del sistema... \n"
            + "¡Gracias por usar Bank Boston!");
    break;
}
    default -> System.out.println("Opción no válida. Intente nuevamente.");
}
}   while (opcion != 9);
      scanner.close();
}
}