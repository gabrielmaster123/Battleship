import java.awt.Point;
import java.util.*;
public class Playground{
	boolean senk;
	int symbol;  
    int cage;                           
	int startX;
	int startY;
	private  int[][] field;
	int shipCount = 2;
	Random rand = new Random();
	boolean sinked;
	boolean unfinished;
	int WASSER = 0;
	int SCHIFF = 1;
	int TREFFER = 2; 
	int WASSERSCHUSS = 4;
	int WDH_SCHUSS = 5;

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


	public void print(){//druckt das feld in die konsole
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
		
	public int getElementAt(int x, int y){//zeigt den wert einer position des feldes
		return field[y][x];
	}

	public int getPublicElementAt(int x, int y){	
		switch (getElementAt(x,y)) {
			case 0:
			case 1:
			case 2:
			case 4:
			case 6:
			case 8:
			case 10:	
				return 1; //unhit water
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:
				return 2; //known ship
			case 12:
				return 3; //hit water
			case 14:
			    return 4; //sunken ship
			default:	
			    return 5; //error
                
		}
	}
	public void reset() {//setzt das feld zurueck
		for(int i = 0; i<field.length;i++){
			for(int o = 0; o<field[0].length;o++){
				field[i][o] = 0;
			}
		}
	}

	public boolean check(int startX, int startY, int length, boolean senk) {//checks whether you can place the ship
		
		boolean unobstructed = true;

		if(senk){
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
	public int schuss (Point p){
		return shoot(p.x, p.y);
	}
	public int shoot(int y, int x ) {//shoots and then returns result
		int result = 3;// 0 = miss 1 = hit 2 = target already shot at 3 = error
		if(field[x][y]==2||
		field[x][y]==4||
		field[x][y]==6||
		field[x][y]==8||
		field[x][y]==10){//successful hit
			field[x][y]++;//marking as hit
			result = 2; 
		}else if(field[x][y]==1||field[x][y]==0){//miss
			field[x][y]=13;
			result = 4;
		}else if(field[x][y]==13){//target already shot at before
			result = 5;
		}
		sink();
		if(field[x][y] == 12){
			result = 3;
		}
		return result;
	}

	public void sink(){
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

	public void resetShipCount(){
		shipCount = 2;
	}
	public void ship(int length) { //places random ship with defined lenght 
		symbol = shipCount;   
		
		// Draw the line of symbols and the sides of the rectangle
		do{              
				senk = rand.nextBoolean();         
				startX = rand.nextInt(field[0].length - 2) + 1;
				startY = rand.nextInt(field[0].length - 2) + 1;
		}while(!check(startX,startY,length, senk));

			if (senk) {
				for (int o = 0; o < length; o++) {
					field[startX+o][startY] = symbol;
				}
			} else {
				for (int o = 0; o < length; o++) {
					field[startX][startY+o] = symbol;
				} 
		}
		shipCount = shipCount + 2;
	}
}