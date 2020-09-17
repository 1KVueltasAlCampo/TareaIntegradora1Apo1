package ui;
import model.*;
import java.util.Scanner;
public class Main {
	/**
	Read the ubication of the dwelling <br>
	<b> pre: </b> The user must enter an int number from 1 to 3, 1 means North, 2 means center, 3 means south <br>
	<b> post: </b> The method returns the value that the user entered
	@param sc A scanner with the function of reading what the user enters. The scanner must be initialized
	@return ubication
	*/
	public static int askForUbication(Scanner sc){
		int ubication;
		System.out.println("Ingrese la ubicacion del inmueble ( 1 para Norte, 2 para Centro, 3 para Sur) ");
		ubication = sc.nextInt();
		sc.nextLine();
		return ubication;
	}
	/**
	Read the names of the materials <br>
	<b> pre: </b> The user must enter strings with the names of the materials to be used <br>
	<b> post: </b> The method returns the values that the user entered
	@param sc A scanner with the function of reading what the user enters. The scanner must be initialized
	@return materialName
	*/
	public static String[] askForName(Scanner sc){
		System.out.println("Ingrese cuantos tipos de materiales necesita para la construccion ");
		int listQuantity= sc.nextInt();
		sc.nextLine();
		String[] materialName = new String[listQuantity];
		for(int i=0;i<listQuantity;i++){
		System.out.println("Ingrese el nombre del material que necesita: ");
		materialName[i] = sc.nextLine();
		}
		return materialName;
	}
	/**
	Read the quantities for each type of the materials <br>
	<b> pre: </b> The user must enter positive double numbers for each type of the materials <br>
	<b> post: </b> The method returns the values that the user entered
	@param sc A scanner with the function of reading what the user enters. The scanner must be initialized
	@param materialName The set of the type of materials that are going to be used. materialName != null.
	@return materialQuantity
	*/
	public static double[] askForQuantity(Scanner sc,String[] materialName){
		int listQuantity = materialName.length;
		double[] materialQuantity = new double[listQuantity];
		for(int i=0;i<listQuantity;i++){
			System.out.println("Ingrese la cantidad de "+materialName[i]);
			materialQuantity[i]=sc.nextDouble();
		}
		return materialQuantity;
	}
	/**
	Read for which work will each type of the materials be used <br>
	<b> pre: </b> The user must enter int numbers from 1 to 3 for each type of the materials. 1 means rough construction, 2 means final construction, 3 means painting  <br>
	<b> post: </b> The method returns the values that the user entered
	@param sc A scanner with the function of reading what the user enters. The scanner must be initialized
	@param materialName The set of the type of materials that are going to be used. materialName != null.
	@return materialUtilization
	*/
	public static int[] askForUtilization(Scanner sc,String[] materialName){
		int listQuantity = materialName.length;
		int[] materialUtilization = new int[listQuantity];
		for(int i=0;i<listQuantity;i++){
			System.out.println("Ingrese a que obra esta destinado el producto "+materialName[i]+"(Escriba 1 para obra negra, 2 para obra blanca, 3 para pintura");
			materialUtilization[i]=sc.nextInt();
		}
		return materialUtilization;
	}
	/**
	Read the prices for the materials of a certain hardware store <br>
	<b> pre: </b> The user must enter positive double numbers that indicate the amount of materials that will be used for each type of these.<br>
	<b> post: </b> The method returns the values that the user entered
	@param sc A scanner with the function of reading what the user enters. The scanner must be initialized
	@param materialName The set of the type of materials that are going to be used. materialName != null.
	@return materialPrice
	*/
	public static double[] askForPrice(Scanner sc,String[] materialName){
		int listQuantity=materialName.length;
		double[] materialPrice = new double[listQuantity];
		for(int i=0;i<listQuantity;i++){
			System.out.println("Ingrese el precio de "+materialName[i]+" (En precio unitario)");
			materialPrice[i] = sc.nextDouble();
		}
		return materialPrice;
	}
	/**
	Shows the solution to all the problems that arise in the integrative task <br>
	<b> pre: </b> All methods must work correctly <br>
	<b> post: </b> The method shows the solution to all the problems that arise in the integrative task
	@param sc A scanner with the function of reading what the user enters. The scanner must be initialized
	@return materialPrice
	*/
	public static void showData(Scanner sc){
		int ubication = askForUbication(sc);
		String[] materialName= askForName(sc);
		double[] materialQuantity = askForQuantity(sc,materialName);
		int[] materialUtilization = askForUtilization(sc,materialName);
		System.out.println("Para HomeCenter: ");
		double[] homeCenterMaterialPrice = askForPrice(sc,materialName);
		System.out.println("Para Ferreteria del centro: ");
		double[] delCentroMaterialPrice = askForPrice(sc,materialName);
		System.out.println("Para Ferreteria del barrio: ");
		double[] delBarrioMaterialPrice = askForPrice(sc,materialName);
		String[] storeBestPrice = Operations.storeBestPrice(homeCenterMaterialPrice,delCentroMaterialPrice,delBarrioMaterialPrice);
		double[] bestPriceValue = Operations.bestPriceValue(homeCenterMaterialPrice,delCentroMaterialPrice,delBarrioMaterialPrice);
		String[] blackJobConstructionUtilization = Operations.utilization(materialName,materialUtilization,1);
		String[] whiteJobConstructionUtilization = Operations.utilization(materialName,materialUtilization,2);
		String[] paintingUtilization = Operations.utilization(materialName,materialUtilization,3);
		System.out.println(" ");
		System.out.println("El total a pagar para HomeCenter es: "+Operations.totalToPay(homeCenterMaterialPrice,materialQuantity,ubication));
		System.out.println("El total a pagar para Ferreteria del centro es: "+Operations.totalToPay(delCentroMaterialPrice,materialQuantity,ubication));
		System.out.println("El total a pagar para Ferreteria del barrio es: "+Operations.totalToPay(delBarrioMaterialPrice,materialQuantity,ubication));
		for(int i=0;i<materialName.length;i++){
			System.out.println("El mejor lugar para comprar "+materialName[i]+" es "+storeBestPrice[i]+" con un valor de "+bestPriceValue[i]);
		}
		System.out.println("Si compra con los mejores precios, debera pagar "+Operations.bestPriceTotal(bestPriceValue,ubication,materialQuantity));
		System.out.println("Los productos utilizados para la obra negra son: ");
		for(int i=0;i<blackJobConstructionUtilization.length;i++){
			System.out.println(blackJobConstructionUtilization[i]);	
		}
		System.out.println("Los productos utilizados para la obra blanca son: ");
		for(int i=0;i<whiteJobConstructionUtilization.length;i++){
			System.out.println(whiteJobConstructionUtilization[i]);	
		}
		System.out.println("Los productos utilizados para la pintura son: ");
		for(int i=0;i<paintingUtilization.length;i++){
			System.out.println(paintingUtilization[i]);	
		}
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		showData(sc);
	}
}