import java.util.Random;
import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static Random random = new Random();

	static String tossWinner = "";
	static String winner = "";
	static boolean firstInningOver = false;
	static int playerScore = 0;
	static int computerScore = 0;

	public static boolean checkNumbers(int userNum, int lowerLimit, int upperLimit) {
		boolean isBetween = true;

		if (!((userNum >= 1) && (userNum <= 10))) {
			isBetween = false;
		}
		return isBetween;
	}

	public static String toss() {

		System.out.println("\nEnter 1 for odd");
		System.out.println("Enter 2 for even");
		System.out.print("Enter your choice : ");

		int choice = scanner.nextInt();

		if (!((choice == 1) || (choice == 2))) {
			System.out.println("You should have entered 1 or 2");

			return "";
		}

		System.out.print("\nEnter Your number(1-10) : ");
		int userNum = scanner.nextInt();
		int compNum = random.nextInt(10) + 1;
		int sum = userNum + compNum;

		System.out.println("\nuser = " + userNum);
		System.out.println("\ncomputer = " + compNum);

		System.out.println("Sum = " + sum);
		// check whether the no. is between 1 and 10
		boolean isBetween = ((userNum >= 1) && (userNum <= 10)) ? true : false;
		if (!isBetween) {
			System.out.println("You should have enter between 1 and 10");
			return "";
		}

		// check who wins the toss
		if (((sum % 2 == 0) && (choice == 2))
				|| (sum % 2 != 0) && (choice == 1)) {
			tossWinner = "user";
		} else {
			tossWinner = "comp";
		}

		System.out.println(tossWinner + " wins the toss");

		// returns the toss winner
		return tossWinner;
	}

	public static void playerBatting() {
		// Batting phase
		for (int ball = 1;; ball++) {
			System.out.print("\nBall " + ball + ": Enter your choice (1-10): ");
			int playerChoice = scanner.nextInt();

			int computerChoice = random.nextInt(6) + 1;
			System.out.println("Computer's choice: " + computerChoice);

			// check if the numbers are between 1 & 10
			boolean isBetween = ((playerChoice >= 1) && (playerChoice <= 10)) ? true : false;
			// if number is not between 1 & 10
			if (!isBetween) {
				return;
			}

			// check for out
			if (playerChoice == computerChoice) {
				System.out.println("Out!");

				// if user is out
				// if user's score is less the comp score
				// computer wins
				if (playerScore < computerScore) {
					winner = "computer";
				}

				// break the loop if user is out
				break;
			} else {
				// increase user score if not out
				playerScore += playerChoice;
			}

			System.out.println("\nYour score: " + playerScore);

			// if first inning is over
			if (firstInningOver) {
				// if player score exceeds computer score during its batting
				if (playerScore > computerScore) {
					winner = "player";
					break;
				}
			}
		}

		// print the total score of the user
		System.out.println("\nYour total score: " + playerScore);
	}

	public static void compBatting() {

		System.out.println("\nNow it's the computer's turn to bat.");
		for (int ball = 1;; ball++) {
			int computerChoice = random.nextInt(6) + 1;
			System.out.print("\nBall " + ball + ": Enter your choice (1-10): ");
			int playerChoice = scanner.nextInt();
			System.out.println("Ball " + ball + ": " + playerChoice + " vs " + computerChoice);

			boolean isBetween = ((playerChoice >= 1) && (playerChoice <= 10)) ? true : false;
			if (!isBetween) {
				return;
				// System.exit();
			}

			// check for out
			if (playerChoice == computerChoice) {
				// if computer is out
				System.out.println("Out!");
				if (playerScore > computerScore) {
					winner = "player ";
				}
				break;
			} else {
				computerScore += computerChoice;
			}

			System.out.println("\nComputer's score: " + computerScore);

			if (firstInningOver) {
				if (playerScore < computerScore) {
					winner = "computer";
					break;
				}
			}
		}

		// print the total score of the user
		System.out.println("\nComputer's total score: " + computerScore);
	}

	public static void main(String[] args) {

		System.out.println("Welcome to Hand Cricket!");
		// System.out.println("Let's play one over. You are the batsman.");

		// toss
		toss();

		// if toss winner is user
		if (tossWinner.compareTo("user") == 0) {
			// ask user to choose from bat or bowl

			System.out.print("\n\nEnter 1 for batting \nEnter 2 for bowling \n\nEnter your choice : ");
			int choice = scanner.nextInt();

			if (choice == 1) {
				playerBatting();// player batting phase

				firstInningOver = true;

				compBatting(); // computer's second batting
			} else {

				compBatting();// Computer's batting phase
				firstInningOver = true;
				playerBatting();// user's second batting
			}
		} else {

			System.out.print("\nComputer choose bowling");

			// is toss winner is comp
			playerBatting();// player batting first
			firstInningOver = true;
			compBatting();// computer will chase the score

		}

		System.out.println("Game over\nwinner = "
				+ winner);

		scanner.close();
	}
}
