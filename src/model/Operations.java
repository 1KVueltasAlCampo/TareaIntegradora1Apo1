package model;
public class Operations{
	private final static int blackConstructionJob = 1300000;
	private final static int painting = 2600000;
	private final static int whiteConstructionJob = 980000;
	public static double totalToPay(double[] materialPrice, double[] materialQuantity,int ubication){
		double total =0;
		for(int i=0;i<materialPrice.length;i++){
			total += materialPrice[i]*materialQuantity[i];
		}
		if(total<80000){
			switch(ubication){
				case 1:
				total+=120000;
				break;
				case 2:
				total+=50000;
				break;
				case 3:
				total+=120000;
				break;
			}
		}
		else if(total<300000){
			switch(ubication){
				case 1:
				total+=28000;
				break;
				case 3:
				total+=55000;
				break;
			}
		}
		total+=(blackConstructionJob+painting+whiteConstructionJob);
		return total;
	}
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
	public static double bestPriceTotal(double[] bestPriceValue, String[] storeBestPrice,int ubication){
		int size = bestPriceValue.length;
		double homecenterPrice=0;
		double delBarrioPrice=0;
		double delCentroPrice=0;
		double total=0;
		for(int i=0;i<size;i++){
			switch(storeBestPrice[i]){
				case "Ferreteria del barrio":
				delBarrioPrice += bestPriceValue[i];
				break;
				case "Ferreteria HomeCenter":
				homecenterPrice += bestPriceValue[i];
				break;
				case "Ferreteria del centro":
				delCentroPrice += bestPriceValue[i];
				break;
			}
		}
		double[] prices = new double[3];
		prices[0] = homecenterPrice;
		prices[1] = delBarrioPrice;
		prices[2]= delCentroPrice;
		for(int i=0;i<3;i++){
			if(prices[i] != 0){
				if(prices[i]<80000){
					switch(ubication){
						case 1:
						prices[i]+=120000;
						break;
						case 2:
						prices[i]+=50000;
						break;
						case 3:
						prices[i]+=120000;
						break;
					}
				}
				else if(total<300000){
					switch(ubication){
						case 1:
						prices[i]+=28000;
						break;
						case 3:
						prices[i]+=55000;
						break;
					}
				}
			}
		}
		total=prices[0]+prices[1]+prices[2];
		total+= blackConstructionJob+whiteConstructionJob+painting;
		return total;
	}
	private static int sizeOfString(String[] materialName, int[] materialUtilization,int use){
		int size =0;
		for(int i=0; i<materialName.length;i++){
			if(materialUtilization[i]==use){
				size++;
			}
		}
		return size;
	}
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