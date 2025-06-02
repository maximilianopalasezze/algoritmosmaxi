package controlador;

import modelo.Cliente;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.File;

public class Banco {
  // Lista para almacenar varios clientes
 private  ArrayList<Cliente> clientes;
 private double totalDepositado = 0;
 private double totalGirado = 0;
 // Constructor
 public Banco() {
      clientes = new ArrayList<>();
}
 // M�todo para registrar cliente
 public void registrarCliente(Cliente cliente) {
      clientes.add(cliente);
}
 // Verifica si un RUT ya est� registrado
 public boolean rutExiste(String rut) {
    for (Cliente cliente : clientes) {
    if (cliente.getRut().equalsIgnoreCase(rut)) {
    return true;
}
}
   return false;
}

 public ArrayList<Cliente> getClientes() {
    return clientes;
}
 // M�todo para mostrar todos los clientes
 public void mostrarClientes() {
   if (clientes.isEmpty()) {
      System.out.println("No hay clientes registrados.");
   return;
}
   for (Cliente c : clientes) {
       System.out.println(c);
}
}
 // M�todo para depositar dinero
public void depositarEnCuenta(int numeroCuenta, double monto) {
   if (monto <= 0) {
      System.out.println("El monto debe ser mayor que cero.");
   return;
}
   for (Cliente cliente : clientes) {
   if (cliente.getCuenta().getNumeroCuenta() == numeroCuenta) {
      cliente.getCuenta().depositar(monto);
      totalDepositado += monto; // actualiza estad�stica
      System.out.println("Dep�sito realizado con �xito.");
   return;
}
}
      System.out.println("Cuenta no encontrada.Intente nuevamente");
}
 // M�todo para girar dinero
 public void girarDeCuenta(int numeroCuenta, double monto) {
    if (monto <= 0) {
      System.out.println("El monto debe ser mayor que cero.");
   return;
} 
   for (Cliente cliente : clientes) {
   if (cliente.getCuenta().getNumeroCuenta() == numeroCuenta) {
   boolean realizado = cliente.getCuenta().girar(monto);
   if (realizado) {
      totalGirado += monto; // actualiza estad�stica
      System.out.println("Giro realizado con �xito.");
}  else {
      System.out.println("Fondos insuficientes. Intente nuevamente");
}
   return;
}
}
      System.out.println("Cuenta no encontrada. Intente nuevamente");
}
 // M�todo para consultar el saldo
 public void consultarSaldoCuenta(int numeroCuenta) {
   for (Cliente c : clientes) {
   if (c.getCuenta().getNumeroCuenta() == numeroCuenta) {
      System.out.println("Saldo actual: $" + c.getCuenta().getSaldo());
   return;
}
}
      System.out.println("Cuenta no encontrada. Intente nuevamente");
}
 // M�todo para mostrar estad�sticas generales
 public void mostrarEstadisticasGenerales() {
    System.out.println("\n---- Estad�sticas del Banco ----");
    System.out.println("Clientes registrados: " + clientes.size());
    System.out.println("Total depositado: $" + totalDepositado);
    System.out.println("Total girado: $" + totalGirado);
}
 // ==== Guarda los clientes en un archivo .dat ====
public void guardarClientes() {
    try {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("clientes.dat"))) {
            salida.writeObject(clientes); // clientes es el ArrayList<Cliente>
        } // clientes es el ArrayList<Cliente>
        System.out.println("Clientes guardados exitosamente.");
    } catch (IOException e) {
        System.out.println("Error al guardar clientes: " + e.getMessage());
    }
}
    //Carga los clientes desde el archivo .dat
public void cargarClientes() {
    try {
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("clientes.dat"));
        clientes = (ArrayList<Cliente>) entrada.readObject();
        entrada.close();
        System.out.println("Clientes cargados desde archivo.");
    } catch (IOException e) {
        System.out.println("No se encontraron datos previos, se inicia lista vac�a.");
        clientes = new ArrayList<>();
    } catch (ClassNotFoundException e) {
        System.out.println("Error al leer los datos guardados: clase no encontrada.");
        clientes = new ArrayList<>();
    }
}
public void eliminarTodosLosClientes() {
    clientes.clear(); // vac�a la lista en memoria
    File archivo = new File("clientes.dat");
    if (archivo.exists()) {
        archivo.delete(); // borra el archivo del disco
        System.out.println("Todos los clientes fueron eliminados.");
    } else {
        System.out.println("No se encontr� archivo para eliminar.");
    }
}
// Buscar cliente por n�mero de cuenta
public Cliente buscarClientePorCuenta(int numeroCuenta) {
    for (Cliente c : clientes) {
    if (c.getCuenta().getNumeroCuenta() == numeroCuenta) {
    return c;
}
}
    return null;
}
}