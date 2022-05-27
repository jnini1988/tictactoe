import java.util.Scanner;
public class Game {
	static int uRow=-1,uCol=-1,row=-1,col=-1;
	
	public static void main(String[] args) {
		System.out.println("Welcome to Ultimate tictactoe");
		System.out.println();
		setUpGame();
	}
	
	public static void setUpGame() {
		Scanner sc=new Scanner(System.in);
		Ultimate ult=new Ultimate();
		int turn=0;
		String mark="X ";
		
		//allow user to choose mode for game
		int bot=-1;
		while(!(bot==1||bot==2)) {
			System.out.println("Please enter 1 - Play against another human player, or 2 - Play against the computer.");
			while(!sc.hasNextInt()) 
			{
			   System.out.println("Please enter 1 or 2");
			   sc.next();
			}
			bot=sc.nextInt();
		}
		
		
		//if no final winner or spaces still open, continue the game
		while(!ult.checkWin(ult.getSumWinner(),false)) {
			ult.print();
			//switch player for each turn
			if(turn%2==1) {
				mark="O "; 
			}
			else {
				mark="X ";
			}
			System.out.println("It is "+mark+"'s turn");
			boolean botTurn= bot==2&&mark.equals("O ");
			
			//ask user to set uRow and uCol for the first turn
			if(uRow==-1&&uCol==-1) {
				System.out.println("Please enter row and column index for the ultimate board: ");
				setValidIndex("ultimate",botTurn);
			}
			//set next player's uRow and uCol to correspond to the index that this player choose
			//but if the uRow and uCol is occupied, the next player can set their own uRow and uCol
			else {	
				uRow=row; 
				uCol=col;
				boolean passOccupyCheck=false;
				while(!passOccupyCheck) {
					if(ult.checkIfOccupied(uRow,uCol,ult.getSumWinner())) {
						if(!botTurn) {
							System.out.println("Please choose a new row and column index for the ultimate board: ");
						}
						setValidIndex("ultimate",botTurn);
					}
					else {
						passOccupyCheck=true;
					}
				}
			}
			
			//ask user to set local board index that are in range and not occupied
			if(!botTurn) {
				System.out.println("Please enter row and column for this board at row "+uRow+",column "+uCol);
			}
			setValidIndex("board",botTurn);
			boolean passOccupyCheck=false;
			while(!passOccupyCheck) {
				if(ult.getUlt()[uRow][uCol].checkIfOccupied(row,col,ult.getUlt()[uRow][uCol].getGame())) {
					if(!botTurn) {
						System.out.println("This index is already occupied, please try again.");
					}
					setValidIndex("board",botTurn);
				}
				else {
					passOccupyCheck=true;
				}
			}
			
			//place mark and update sumWinner if there is a winner in this board
			ult.getUlt()[uRow][uCol].placeMove(mark,row,col);
			if(ult.getUlt()[uRow][uCol].checkWin((ult.getUlt()[uRow][uCol].getGame()),true)) {
				ult.getUlt()[uRow][uCol].updateGame();
				ult.updateWinner(uRow,uCol);
			}
			
			System.out.println("___________________________________________");
			System.out.println();
			turn++;
			
			if(ult.allBoardFilled()) {
				break;
			}
		}
		
		ult.print();
		//Print result and ask user if they want to play again
		System.out.println("END OF GAME");
		if(ult.allBoardFilled()&&!ult.checkWin(ult.getSumWinner(),false)) {
			System.out.println("Tie, no player win");
		}
		else {
			System.out.println(mark+" won! Congrats!");
		}
		System.out.println("Enter 1 to play again, any other key to end game");
		String next=sc.next();
		if(next.equals("1")) {
			uRow=-1;uCol=-1;row=-1;col=-1;
			setUpGame();
		}
	}
	

	//Ask user to enter row and col, repeat if the index is not in range
	public static void setValidIndex(String status,boolean bot) {
		int tempR=-1;
		int tempC=-1;
		if(!bot) {
			Scanner sk=new Scanner(System.in);
			System.out.print("Row: ");
			while(!sk.hasNextInt()) 
			{
			   System.out.println("Please enter a number from 0-2");
			   sk.next();
			}
			tempR=sk.nextInt();
			System.out.print("Col: ");
			while(!sk.hasNextInt()) 
			{
			   System.out.println("Please enter a number from 0-2");
			   sk.next();
			}
			tempC=sk.nextInt();
		}
		else {
			tempR=(int)(Math.random()*3);
			tempC=(int)(Math.random()*3);
		}
		if(!inRange(tempR,tempC)) {
			System.out.println();
			System.out.println("Row and Index are not in range. Please try again. Reminder: row and col index range from 0-2.");
			setValidIndex(status,bot);
		}
		else {
			if(status.equals("ultimate")) {
				uRow=tempR;
				uCol=tempC;
			}
			else {
				row=tempR;
				col=tempC;
			}
		}
	}
	
	//Return true if row and col is in range, false otherwise 
	public static boolean inRange(int r,int c) {
		if(r>2||r<0||c>2||c<0) {
			return false;
		}
		return true;
	}
}