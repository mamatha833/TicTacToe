import java.awt.datatransfer.Clipboard;
import java.util.Random;
import java.util.Scanner;


class TicTacToe{

	static char[][] board;

	public TicTacToe() {

		board = new char[3][3];
		init();

	}

	void init() {

		for(int i=0; i<=2; i++) {
			for(int j=0; j<=2; j++) {
				board[i][j] = ' ';
			}
		}
	}

	void dispBoard() {

		for(int i=0; i<=2; i++) {
			System.out.println("-------------");
			System.out.print("| ");
			for(int j=0; j<=2; j++) {
				System.out.print(board[i][j]+" | ");

			}
			System.out.println();
		}
		System.out.println("-------------");
	}

	boolean checkRowWin() {

		for(int i=0; i<=2; i++) {
			if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		return false;

	}

	boolean checkColWin() {

		for(int i=0; i<=2; i++) {
			if(board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				return true;
			}
		}
		return false;

	}

	boolean checkDigonalWin() {

		if(board[0][0] != ' ' && board[0][0] == board[1][1]
				&& board[1][1] == board[2][2]
						|| board[0][2] !=' ' && board[0][2] == board[1][1]
								&& board[1][1] == board[2][0]) {

			return true;
		}

		return false;

	}

	boolean checkDraw() {

		for(int i=0; i<=2; i++) {
			for(int j=0; j<=2; j++) {
				if(board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;

	}


}


abstract class Player{

	String name;
	char mark;
	int row;
	int col;

	abstract void makeMove();
	boolean isValidMove(int row,int col) {

		if(row>=0 && row<=2 && col>=0 && col<=2) {
			if(TicTacToe.board[row][col] == ' ') {
				return true;
			}
			else {
				System.err.println("Already marked");
				return false;
			}
		}
		else
		System.err.println("Invalid position");
		return false;
	}


}

class HumanPlayer extends Player{

	public HumanPlayer(String name,char mark) {

		this.name=name;
		this.mark=mark;
	}

	@Override
	void makeMove() {

		Scanner scan = new Scanner(System.in);

		do {

			System.out.println("Enter row and column");
			row = scan.nextInt();
			col = scan.nextInt();		
		}while(!isValidMove(row, col));

		TicTacToe.board[row][col] = mark;


	}
}

class Computer extends Player{


	public Computer(String name,char mark) {

		this.name=name;
		this.mark=mark;
	}

	@Override
	void makeMove() {
		do {

			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);		
		}while(!isValidMove(row, col));

		TicTacToe.board[row][col] = mark;


	}

}



public class LaunchGame {

	public static void main(String[] args) {

		TicTacToe t = new TicTacToe();
		HumanPlayer hp = new HumanPlayer("Bob",'X');
		Computer cp = new Computer("Computer",'O');
		Player p;

		p=hp;

		while(true) {

			System.out.println(p.name+" turn");
			p.makeMove();
			t.dispBoard();
			if(t.checkRowWin() || t.checkColWin() || t.checkDigonalWin()) {
				System.out.println(p.name+" has won");
				break;
			}
			else if(t.checkDraw()) {
				System.out.println("Draw Match");
				break;
			}
			else {
				if(p==hp) {
					p=cp;
				}
				else {
					p=hp;
				}
			}

		}

	}

}
