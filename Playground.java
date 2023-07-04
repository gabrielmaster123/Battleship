import java.util.*;
public class Playground{
	int senk;
	static int symbol;  
    static int cage;                           
	int startX;
	int startY;
	private static int[][] field;
	static int shipCount = 2;
	Random rand = new Random();
	static boolean sinked;
	static boolean unfinished;

	public Playground(int x,int y){
		field = new int [x][y];
	}

	public int[][] getPlayground(){
		return field;
	}

	public int getSizeX(){// getter für die länge des feldes
		return field.length;
	}
	
	public int getSizeY(){// getter für die breite des feldes 
		return field[0].length;
	}
	public void fillNumber(int number){//füllt das ganze feld mit einer bestimmten zahl
		for(int i = 0; i<field.length;i++){
			for(int o = 0; o<field[0].length;o++){
				field[i][o] = number;
			}
		}
	}


	public static void print(){//druckt das feld in die konsole
		System.out.print("========================================");
		System.out.println();
		for(int i=0;i<field.length;i++){
			for(int o = 0; o<field[0].length;o++){
				System.out.print(field[i][o]+" ");
			}
			System.out.println();
		}
		System.out.println("========================================");
	}
		
	public static int getElementAt(int y, int x){//zeigt den wert einer position des feldes
		return field[x][y];
	}
	
	public void reset() {//setzt das feld zurueck
		for(int i = 0; i<field.length;i++){
			for(int o = 0; o<field[0].length;o++){
				field[i][o] = 0;
			}
		}
	}

	public boolean check(int startX, int startY, int length, int senk) {//checks whether you can place the ship
		
		boolean unobstructed = true;

		if(senk == 1){
			if(startX==0 ||  
			startY==0) {
				return unobstructed = false;
			}
			if (startX+length>11 ||
			startY+length>11) {
				return unobstructed = false;
			}
			for (int i = startX - 1; i <= startX+length; i++) {
				if (field[i][startY - 1] != 0 || 
				field[i][startY+1] != 0) {
					return unobstructed = false;
				}
			}
			if (field[startX - 1][startY] != 0 || 
			field[startX+length][startY] != 0) {
				return unobstructed = false;
			}
			for (int o = 0; o < length; o++, startX++) {
				if (field[startX][startY] != 0 || 
				field[startX][startY - 1] != 0 || 
				field[startX][startY+1] != 0) {
					return unobstructed = false;
				}
			}
			
		}else{
			if(startX==0 || 
			startY==0) {
				return unobstructed = false;
			}
			if (startX+length>11 ||
			startY+length>11) {
				return unobstructed = false;
			}
			for (int i = startY - 1; i <= startY+length; i++) {
				if (field[startX - 1][i] != 0 || 
				field[startX + 1][i] != 0) {
					return unobstructed = false;
				}
			}
			if (field[startX][startY - 1] != 0 || 
			field[startX][startY+length] != 0) {
				return unobstructed = false;
			}
			for (int o = 0; o < length; o++, startX++) {
				if (field[startX][startY] != 0 || 
				field[startX - 1][startY] != 0 || 
				field[startX + 1][startY] != 0) {
					return unobstructed = false;
				}
			}			
		}
		return unobstructed;
	}

	public static int shoot(int y, int x ) {//shoots and then returns result
		int result = 3;// 0 = miss 1 = hit 2 = target already shot at 3 = error
		if(field[x][y]==2||
		field[x][y]==4||
		field[x][y]==6||
		field[x][y]==8||
		field[x][y]==10){//successful hit
			field[x][y]++;//marking as hit
			result = 1; 
		}else if(field[x][y]==1||field[x][y]==0){//miss
			field[x][y]=13;
			result = 0;
		}else if(field[x][y]==4){//target already shot at before
			result = 2;
		}
		sink();
		return result;
	}

	public static void sink(){
		for(int i = 2; i <= 10; i+=2){
			sinked = true;
			for (int j = 0; j < field.length; j++) {
				for (int k = 0; k < field[0].length; k++) {
					if(field[j][k] == i) {
						sinked = false;
					}
				}
			}
			if (sinked) {
				for (int j = 0; j < field.length; j++) {
					for (int k = 0; k < field[0].length; k++) {
						if (field[j][k] == i + 1) {
							field[j][k] = 12;
						}
					}
				}
			}
		}
		unfinished = false;
		for (int j = 0; j < field.length; j++) {
			for (int k = 0; k < field[0].length; k++) {
				if (
				field[j][k] == 2 ||
				field[j][k] == 4 ||
				field[j][k] == 6 ||
				field[j][k] == 8 ||
				field[j][k] == 10){
					unfinished = true;
				}
			}
		}
		if(!unfinished){
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field[0].length; j++) {
					field[i][j] = 14;
				}
			}
		}

	}

	public static void resetShipCount(){
		shipCount = 2;
	}

	public static void shipManual(int startX, int startY, int length, int senk) { //places random ship
		symbol = shipCount;    
		cage = 1;            
		// Draw the line of symbols and the sides of the rectangle
				if (senk == 0) {
					// Draw top and bottom sides of the rectangle
					for (int i = startX - 1; i <= startX+length; i++) {
						field[i][startY - 1] = cage; 
						field[i][startY+1] = cage;
					}
					// Draw left and right sides of the rectangle
					for (int i = startY - 1+1; i < startY+1; i++) {
						field[startX - 1][i] = cage;
						field[startX+length][i] = cage;
					}
				for (int o = 0; o < length; o++, startX++) {
					field[startX][startY] = symbol;
					field[startX][startY - 1] = cage;
					field[startX][startY+1] = cage;
				}
			} else {
				// Draw left and right sidesof cage
					for (int i = startX - 1; i <= startX+1; i++) {
						field[i][startY - 1] = cage;//left
						field[i][startY+length] = cage;
					}
				//Draw top and bottom of cage and ship
				for (int o = 0; o < length; o++, startY++) {
					field[startX][startY] = symbol;//draw ship
					field[startX - 1][startY] = cage;//draw top line
					field[startX+1][startY] = cage;
				}  
			}
			
	}

	public void ship(int length) { //places random ship with defined lenght 
		symbol = shipCount;    
		cage = 1;              
		senk = rand.nextInt(2);
		startX = rand.nextInt(field[0].length - 2 - length) + 1;
		startY = rand.nextInt(field[0].length - 2 - length) + 1;
		int rr = 0;
		
		// Draw the line of symbols and the sides of the rectangle
		while(!check(startX,startY,length, senk)){              
					senk = rand.nextInt(2);// 0 horizontal 1 vertical         
					startX = rand.nextInt(field[0].length - 2 - length) + 1;
					startY = rand.nextInt(field[0].length - 2) + 1;
					if(rr<10000){
						System.out.println("reroll" + rr);
						rr++;
					}else{
						System.out.println("Impossible ship placement, please restart program");
					}
		}
			if (senk == 1) {
				// Draw top and bottom sides of the rectangle
				for (int i = startX - 1; i <= startX+length; i++) {
					field[i][startY - 1] = cage; 
					field[i][startY+1] = cage;
				}
				// Draw left and right sides of the rectangle
				for (int i = startY - 1+1; i < startY+1; i++) {
					field[startX - 1][i] = cage;
					field[startX+length][i] = cage;
				}
				for (int o = 0; o < length; o++, startX++) {
					field[startX][startY] = symbol;
					field[startX][startY - 1] = cage;
					field[startX][startY+1] = cage;
				}
			} else {
				if (senk == 0) {
				// Draw left and right sidesof cage
					for (int i = startX - 1; i <= startX+1; i++) {
						field[i][startY - 1] = cage;//left
						field[i][startY+length] = cage;
					}
				//Draw top and bottom of cage and ship
				for (int o = 0; o < length; o++, startY++) {
					field[startX][startY] = symbol;//draw ship
					field[startX - 1][startY] = cage;//draw top line
					field[startX+1][startY] = cage;
				}  
			}
		}
		shipCount = shipCount + 2;
	}
}