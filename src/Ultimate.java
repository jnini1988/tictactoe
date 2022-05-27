public class Ultimate extends Board{
	Board[][] ult=new Board[3][3];
	String[][] sumWinner={{"  ","  ","  "},{"  ","  ","  "},{"  ","  ","  "}}; //aid checkwin
	
	public Ultimate() {
		ult=new Board[3][3];
		for(int r=0;r<3;r++) {
			for(int c=0;c<3;c++) {
				ult[r][c]=new Board();
			}
		}
	}
	//Print each individual board 
	public void print(){
		 for(int uRow=0;uRow<3;uRow++){
	         for(int row=0;row<3;row++){
	        	System.out.print("   ");
	            for(int uCol=0;uCol<3;uCol++){
	               for(int col=0;col<3;col++){
	                  String[][] temp=ult[uRow][uCol].getGame();
	                  System.out.print(temp[row][col]);
	               }
	               if(uCol<2) {
	            	   System.out.print(" | ");
	               }
	            }
	            System.out.println();
	         }
	         if(uRow<2) {
	        	 System.out.print("   ");
	        	 System.out.println("-------------------------");
	         }
	     }
	}
	
	//update the sumWinner with winner for a board
	public void updateWinner(int uRow, int uCol) {
		sumWinner[uRow][uCol]=ult[uRow][uCol].getWinner();
	}
	
	//get sumWinner array
	//use when calling checkWin in test class, in order to determine if game should end
	//use when calling checkIfOccupied
	public String[][] getSumWinner(){
		return sumWinner;
	}
	
	//used with uRow and uCol to get a board
	public Board[][] getUlt(){
		return ult;
	}
	
	public boolean allBoardFilled() {
		for(Board[] r:ult) {
			for(Board b:r) {
				for(String[] str:b.getGame()) {
					for(String s:str) {
						if(s.equals("  ")) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}
