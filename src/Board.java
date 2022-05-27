public class Board {
	String[][] game=new String[3][3];;
	String winner=" ";
	
	public Board() {
		for(int r=0;r<3;r++) {
			for(int c=0;c<3;c++) {
				game[r][c]="  ";
			}
		}
	}
	
	//Precondition: index is not occupied and uBoard index is not occupied
	//Place a mark in an array index / game tile 
	public void placeMove(String mark, int row, int col) {
		game[row][col]=mark;
	}
	
	//Check if there is a winner in this board. Return true if there is, else return false. 
	public boolean checkWin(String[][] g,boolean board) {
		for(int r=0;r<3;r++) {
			if(g[r][0].equals(g[r][1]) && g[r][0].equals(g[r][2]) && checkIfOccupied(r, 0,g)&&!g[r][0].equals("*")) {
				if (board) winner=g[r][0];
				return true;
			}
		}
		for(int c=0;c<3;c++) {
			if(g[0][c].equals(g[1][c])&&g[0][c].equals(g[2][c])&&checkIfOccupied(0,c,g)&&!g[0][c].equals("*")) {
				if (board) winner=g[0][c];
				return true;
			}
		}
		if(checkIfOccupied(1, 1,g)&&!game[1][1].equals("*")) {
			if(g[0][0].equals(g[1][1])&&g[0][0].equals(g[2][2])) {
				if (board) winner=g[1][1];
				return true;
			}
			if(g[0][2].equals(g[1][1])&&g[0][2].equals(g[2][0])) {
				if (board) winner=g[1][1];
				return true;
			}
		}
		return false;
	}
	
	//Return true if array index is occupied, else return false
	public boolean checkIfOccupied(int r, int c,String[][] arr) {
		if(arr[r][c].equals("  ")) {
			return false;
		}
		return true;
	}
	
	//Return the winner of this board, aid updateWinner
	public String getWinner() {
		return winner;
	}
	
	//Return game array to aid checkwin method for this board
	public String[][] getGame(){
		return game;
	}
	
	//Update the game array with * if there is a winner 
	public void updateGame() {
		for(int r=0;r<3;r++) {
			for(int c=0;c<3;c++) {
				game[r][c]="* ";
			}
		}
		game[1][1]=winner;
	}
}
