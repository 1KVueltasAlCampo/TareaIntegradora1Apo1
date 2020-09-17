package model;
public class Operations{
	private final static int roughConstruction = 1300000;
	private final static int painting = 2600000;
	private final static int finalConstruction = 980000;
	
	/**
	Returns the amount of money that must be paid when the products are bought only in a certain hardware store <br>
	<b> pre: </b> <br>
	<b> post: </b> The amount of money to be paid is calculated from the prices of the materials, the quantity, and the location of the residence <br>
	@param materialPrice Set of prices of a certain hardware store for the materials to be used. materialPrice != null.
	@param materialQuantity Set of quantities for previously determined types of materials. materialQuantity != null.
	@param ubication Value that indicates the place of the dwelling, it is a number between 1 and 3. ubication != null.
	@return total
	*/
	public static double totalToPay(double[] materialPrice, double[] materialQuantity,int ubication){
		double total =0;
		for(int i=0;i<materialPrice.length;i++){
			total += materialPrice[i]*materialQuantity[i];
		}
		total += calculateDeliveryValue(total,ubication);
		total+=(roughConstruction+painting+finalConstruction);
		return total;
	}
	/**
	Returns an array of String with the name of the hardware store with the best unit price for a certain material <br>
	<b> pre: </b> <br>
	<b> post: </b> The name of the hardware store with the best price for each specific material is found from the price arrays of the 3 possible hardware stores <br>
	@param HomecenterMaterialPrice Price set from the HomeCenter hardware store for materials to be used. HomeCenterMaterialPrice != Null.
	@param delCentroMaterialPrice Set of prices of the hardware store of the center for the materials to be used. delCentroMaterialPrice != null.
	@param delBarrioMaterialPrice Set of prices from the neighborhood hardware store for the materials to be used. delBarrioMaterialPrice != null.
	@return storeBestPrice
	*/
	public static String[] storeBestPrice(double[] homecenterMaterialPrice, double[] delCentroMaterialPrice, double[] delBarrioMaterialPrice){
		int size = delCentroMaterialPrice.length;
		String[] storeBestPrice = new String[size];
		for(int i=0;i<size;i++){
			if(homecenterMaterialPrice[i] > delCentroMaterialPrice[i]){
				if(delBarrioMaterialPrice[i]>delCentroMaterialPrice[i]){
					storeBestPrice[i] = "Ferreteria del centro";
				}
				else{
					storeBestPrice[i] = "Ferreteria del barrio";
				}
			}
			else if(homecenterMaterialPrice[i]<delBarrioMaterialPrice[i]){
				storeBestPrice[i] = "Ferreteria HomeCenter";
			}
			else{
				storeBestPrice[i]= "Ferreteria del barrio";
			}
		}	
		return storeBestPrice;
	}
	/**
	Returns an array of double with the best unit price for a given material <br>
	<b> pre: </b> <br>
	<b> post: </b> The best unit price for a certain material is found from the price arrays of the 3 possible hardware stores <br>
	@param HomecenterMaterialPrice Price set from the HomeCenter hardware store for materials to be used. HomeCenterMaterialPrice != Null.
	@param delCentroMaterialPrice Set of prices of the hardware store of the center for the materials to be used. delCentroMaterialPrice != null.
	@param delBarrioMaterialPrice Set of prices from the neighborhood hardware store for the materials to be used. delBarrioMaterialPrice != null.
	@return bestPriceValue
	*/
	public static double[] bestPriceValue(double[] homecenterMaterialPrice, double[] delCentroMaterialPrice, double[] delBarrioMaterialPrice){
		int size = homecenterMaterialPrice.length;
		double[] bestPriceValue = new double[size];
		for(int i=0;i<size;i++){
			if(homecenterMaterialPrice[i]>delCentroMaterialPrice[i]){
				if(delBarrioMaterialPrice[i]>delCentroMaterialPrice[i]){
					bestPriceValue[i] = delCentroMaterialPrice[i];
				}
				else{
					bestPriceValue[i] = delBarrioMaterialPrice[i];
				}
			}
			else if(homecenterMaterialPrice[i]<delBarrioMaterialPrice[i]){
				bestPriceValue[i] = homecenterMaterialPrice[i];
			}
			else{
				bestPriceValue[i]= delBarrioMaterialPrice[i];
			}
		}	
		return bestPriceValue;
	}
	/**
	Returns the value of the delivery <br>
	<b> pre: </b> <br>
	<b> post: </b> The value of the delivery is found from the total value of all the materials that must be buyed and the ubication of the dwelling <br>
	@param total  Total value of all the materials that must be buyed
	@param ubication  Value that indicates the place of the dwelling, it is a number between 1 and 3. ubication != null.
	@return deliveryValue
	*/
	private static double calculateDeliveryValue(double total,int ubication){
		double deliveryValue = 0;
		if(total<80000){
			switch(ubication){
				case 1:
					deliveryValue+=120000;
					break;
				case 2:
					deliveryValue+=50000;
					break;
				case 3:
					deliveryValue+=120000;
					break;
			}
		}
		else if(total<300000){
			switch(ubication){
				case 1:
					deliveryValue+=28000;
					break;
				case 3:
					deliveryValue+=55000;
					break;
			}
		}
		return deliveryValue;
	}
	/**
	Returns the value that must be paid when the best prices for materials are chosen <br>
	<b> pre: </b> <br>
	<b> post: </b> The value is found from the array of best prices, and the ubication of the dwelling <br>
	@param bestPriceValue Set of the best prices for materials that can be chosen. bestPriceValue != null. 
	@param ubication  Value that indicates the place of the dwelling, it is a number between 1 and 3. ubication <= 3 && ubication> = 1.
	param materialQuantity Set of quantities for the materials given. materialQuantity != null.
	@return total
	*/
	public static double bestPriceTotal(double[] bestPriceValue,int ubication,double[] materialQuantity){
		int size = bestPriceValue.length;
		double total=0;
		for(int i=0;i<size;i++){
			total += bestPriceValue[i]*materialQuantity[i];
		}
		total += calculateDeliveryValue(total,ubication);

		return total;
	}
	/**
	Returns the size of the set that stores the materials according to their use in the work <br>
	<b> pre: </b> <br>
	<b> post: </b> The value of the size of the set is found from the array of the names of materials, the array of the utilization of materials and an indicator for the clasification that is going to be used <br>
	@param materialName Array of String that stores the names of the materials. materialName != null. 
	@param materialUtilization  Array of int from 1 to 3 that stores the utilization of the materials. utilization != null
	@param use Value that indicates for what work is this size reservation going to be made, number 1 means rough construction, number 2 means final construction, number 3 means painting. use >= 1 && use <= 3
	@return size
	*/
	private static int sizeOfString(String[] materialName, int[] materialUtilization,int use){
		int size =0;
		for(int i=0; i<materialName.length;i++){
			if(materialUtilization[i]==use){
				size++;
			}
		}
		return size;
	}
	/**
	Returns a set that stores the materials according to their use in the work <br>
	<b> pre: </b> <br>
	<b> post: </b> The set is found from the array of the names of materials, the array of the utilization of materials and an indicator for the clasification that is going to be used <br>
	@param materialName Array of String that stores the names of the materials. materialName != null. 
	@param materialUtilization  Array of int from 1 to 3 that stores the utilization of the materials. utilization != null
	@param use Value that indicates for what work is this size reservation going to be made, number 1 means rough construction, number 2 means final construction, number 3 means painting. use >= 1 && use <= 3
	@return size
	*/
	public static String[] utilization(String[] materialName, int[] materialUtilization, int use){
		int size = sizeOfString(materialName,materialUtilization,use);
		int position =0;
		String[] forWhat = new String[size];
		for(int i=0;i<materialName.length;i++){
			if(materialUtilization[i]==use){
				forWhat[position]= materialName[i];
				position++;
			}
		}
		return forWhat;
	}
}