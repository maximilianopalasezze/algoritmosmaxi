package teatromoro;

import java.util.Scanner;

public class Exp2_s5_Maximiliano_Palasezze {
 public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        // definir las variables
        int totalEntradasVendidas = 0;
        double ingresosTotales = 0;
        int entradasEliminadas = 0;
        int contadorEntradas = 1;
        int capacidad = 4;
        int entradasDisponibles = capacidad;
        String totalEntradas = ""; 
        boolean salir = false;
            System.out.println(  "\nBienvenidos al teatro Moro\n");
            System.out.println("cargando el menu principal un momento por favor");
            Thread.sleep(1*2000);
             //detalles del menu         
        while (!salir) {
            System.out.println("\n  MENU PRINCIPAL  ");
            System.out.println("1. Venta de entradas");
            System.out.println("2. Promociones");
            System.out.println("3. Buscar entrada");
            System.out.println("4. Eliminar entrada");
            System.out.println("5. resumen de compras");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine().trim();
            // codicion para cuando se acaban las entradas
        switch (opcion) {
         case "1" -> {
           if (entradasDisponibles <= 0) {
            System.out.println("No hay más entradas disponibles.");
           break;
        }
           // menu de entradas 
        String ubicacion = "";
        double precioBase = 0;
        while (true) {
           System.out.println("\n Seleccione su ubicación:");
           System.out.println("1. VIP ($20000)");
           System.out.println("2. Platea alta ($9000)");
           System.out.println("3. Platea baja ($10000)");
           System.out.println("4. Palcos ($6500)");
           System.out.print("Seleccione la ubicación (1-4): ");
        String ubicacionOpcion = scanner.nextLine().trim();

        switch (ubicacionOpcion) {
         case "1" -> {
            ubicacion = "VIP";
            precioBase = 20000;
            break;
        }
         case "2" -> {
            ubicacion = "Platea alta";
            precioBase = 9000;
            break;
        }
         case "3" -> {
            ubicacion = "Platea baja";
            precioBase = 10000;
            break;
        }
         case "4" -> {
            ubicacion = "Palcos";
            precioBase = 6500;
            break;
        }
         default -> System.out.println("\n ubicacion no valida. Intente nuevamente.\n");
        }
           if (!ubicacion.isEmpty()) break;
        }
         // Determinar tipo de cliente por edad
         int edad = 0;
         while (true) {
         try {
           System.out.print("Ingrese su edad: ");
           edad = Integer.parseInt(scanner.nextLine().trim());
           if (edad <= 0 || edad > 100) {
           System.out.println("\nEdad no valida. Intente nuevamente.\n");
           } else {
             break;
           }
         } catch (NumberFormatException e) {
           System.out.println("\nPor favor ingrese un número válido.\n");
         }
         }

         String tipoCliente;
         double descuento;
            if (edad >= 60) {
            tipoCliente = "Tercera edad";
            descuento = 0.15;
         } else if (edad <= 18) {
            tipoCliente = "Estudiante";
            descuento = 0.10;
         } else {
        String respuesta;
         do {
           System.out.print("¿Es usted estudiante? (si/no): "); // si es estudiante mayor a 18
           respuesta = scanner.nextLine().trim().toLowerCase();

           if (!respuesta.equals("si") && !respuesta.equals("no")) {
           System.out.println("\nRespuesta no válida. Por favor escriba 'si' o 'no'.\n");
                            }
         } while (!respuesta.equals("si") && !respuesta.equals("no"));
           if (respuesta.equals("si")) {
            tipoCliente = "Estudiante";
            descuento = 0.10;
           System.out.print("se solicitara el carnet estudiantil para confirmar entrada "); 
            Thread.sleep(1*2000);
         } else {
            tipoCliente = "General";
            descuento = 0.0;
         }
         }
         double precioFinal = precioBase - (precioBase * descuento);

         ingresosTotales += precioFinal;
         totalEntradasVendidas++;
         entradasDisponibles--;
         String entrada = "Número: " + contadorEntradas + ", Ubicación: " + ubicacion + ", Precio: $" + precioFinal + ", Tipo: " + tipoCliente + "\n";
         totalEntradas += entrada;
         contadorEntradas++;
            System.out.println("\nEntrada vendida con éxito:");
            System.out.println(entrada);
        }
         // menu de promociones 
         case "2" -> {
           System.out.println("\n=== Promociones disponibles ===");
           System.out.println("- 10% de descuento para estudiantes");
           System.out.println("- 15% de descuento para personas de la tercera edad");
           System.out.println("- Si compras 3 entradas o más, se aplica 5% de descuento al total al final");
        }
         // menu de busqueda de entradas
         case "3" -> {
           System.out.print("Ingrese el término a buscar (número, ubicación o tipo): ");
        String valor = scanner.nextLine().trim().toLowerCase();

        String[] lineas = totalEntradas.split("\n");
        boolean encontrada = false;
        for (String linea : lineas) {
           if (linea.toLowerCase().contains(valor)) {
           System.out.println(linea);
           encontrada = true;
        }
        }
           if (!encontrada) {
           System.out.println("No se encontraron entradas con ese criterio.");
        }
        }
         //menu de eliminacion de entradas
         case "4" -> {
           System.out.print("Ingrese el número de la entrada a eliminar: ");
        String numeroEliminar = scanner.nextLine().trim();

        String[] lineas = totalEntradas.split("\n");
        String nuevoHistorial = "";
        boolean eliminada = false;

        for (String linea : lineas) {
           if (linea.contains("Número: " + numeroEliminar + ",")) {
           System.out.println("Entrada eliminada:\n" + linea);
        entradasEliminadas++;
        totalEntradasVendidas--;
        entradasDisponibles++;
        eliminada = true;
        } else {
        nuevoHistorial += linea + "\n";
        }
        }
           if (!eliminada) {
           System.out.println("Entrada no encontrada.");
        }
        totalEntradas = nuevoHistorial;
        }
         //menu de estadisticas 
         case "5" -> {
           System.out.println("\n=== Estadísticas ===");
           System.out.println("Total entradas vendidas: " + totalEntradasVendidas);
           System.out.println("Ingresos totales: $" + ingresosTotales);
           System.out.println("Entradas eliminadas: " + entradasEliminadas);
           System.out.println("Entradas disponibles: " + entradasDisponibles);
        }
         // salir del menu principal
         case "6" -> salir = true;
        default -> System.out.println("Opción no válida. Intente de nuevo.");
        }       
        
        }
            // resumen de compras 
           System.out.println("\nResumen de todas las entradas vendidas:");
           System.out.println(totalEntradas);
           Thread.sleep(1*1000);
           if (totalEntradasVendidas >= 3) {
        double descuentoFinal = ingresosTotales * 0.05;
        double totalConDescuento = ingresosTotales - descuentoFinal;
           System.out.println("\n5% de descuento al total por comprar 3 o más entradas.");
           System.out.println("Total antes del descuento final: $" + ingresosTotales);
           System.out.println("Descuento aplicado: $" + descuentoFinal);
           System.out.println("Total a pagar: $" + totalConDescuento);
        } else {
               Thread.sleep(1*1000);
            System.out.println("Total a pagar por todas las entradas: $" + ingresosTotales);
        }
           Thread.sleep(1*1000);
            System.out.println("Gracias por su compra. ¡disfrute la funcion!");
    }
}