package ui;
import model.*;
import java.util.Scanner;
public class Main {
	public static int askForUbication(Scanner sc){
		int ubication;
		System.out.println("Ingrese la ubicacion del inmueble ( 1 para Norte, 2 para Centro, 3 para Sur) ");
		ubication = sc.nextInt();
		sc.nextLine();
		return ubication;
	}
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
	public static double[] askForQuantity(Scanner sc,String[] materialName){
		int listQuantity = materialName.length;
		double[] materialQuantity = new double[listQuantity];
		for(int i=0;i<listQuantity;i++){
			System.out.println("Ingrese la cantidad de "+materialName[i]);
			materialQuantity[i]=sc.nextDouble();
		}
		return materialQuantity;
	}
	public static int[] askForUtilization(Scanner sc,String[] materialName){
		int listQuantity = materialName.length;
		int[] materialUtilization = new int[listQuantity];
		for(int i=0;i<listQuantity;i++){
			System.out.println("Ingrese a que obra esta destinado el producto "+materialName[i]+"(Escriba 1 para obra negra, 2 para obra blanca, 3 para pintura");
			materialUtilization[i]=sc.nextInt();
		}
		return materialUtilization;
	}
	public static double[] askForPrice(Scanner sc,String[] materialName){
		int listQuantity=materialName.length;
		double[] materialPrice = new double[listQuantity];
		for(int i=0;i<listQuantity;i++){
			System.out.println("Ingrese el precio de "+materialName[i]+" (En precio unitario)");
			materialPrice[i] = sc.nextDouble();
		}
		return materialPrice;
	}
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
		System.out.println("El total a pagar para HomeCenter es: "+Operations.totalToPay(homeCenterMaterialPrice,materialQuantity,ubication));
		System.out.println("El total a pagar para HomeCenter es: "+Operations.totalToPay(delCentroMaterialPrice,materialQuantity,ubication));
		System.out.println("El total a pagar para HomeCenter es: "+Operations.totalToPay(delBarrioMaterialPrice,materialQuantity,ubication));
		for(int i=0;i<materialName.length;i++){
			System.out.println("El mejor lugar para comprar "+materialName[i]+" es "+storeBestPrice[i]+" con un valor de "+bestPriceValue[i]);
		}
		System.out.println("Si compra con los mejores precios, debera pagar "+Operations.bestPriceTotal(bestPriceValue,storeBestPrice,ubication));
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