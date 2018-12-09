package pkg;

import java.util.Scanner;

public class Game {

    private AllDice gameDice = new AllDice();
    private Statistics soloStats = new Statistics("Player");
    private Statistics p1Stats = new Statistics("Player 1");
    private Statistics p2Stats = new Statistics("Player 2");
    private Statistics aiStats = new Statistics("The AI");
    private Scoreboard player1Scoreboard = new Scoreboard();
    private Scoreboard player2Scoreboard = new Scoreboard();
    private Scoreboard aiScoreboard = new Scoreboard();
    private Statistics activeStats;
    private static GameRules gameRules = new GameRules();
    private boolean isInitial;
    private int rollsAllowed;

    private Game() {
        this.isInitial = true;
    }

    private boolean getYesNoFromUserAsChar() {
        boolean done = false;
        int userChoice = 0;
        while (!done) {
            userChoice = getCharFromUser();
            if (userChoice != 'y' && userChoice != 'n') {
                PrintWithColor.brightRed("Invalid! Please try again.");
            } else {
                done = true;
            }
        }
        return userChoice == 'y';
    }

    private char getCharFromUser() { //forces user to input a valid char
        char userChar = ' ';
        Scanner userCharInput = new Scanner(System.in);
        while (!userCharInput.hasNextLine()) {
            PrintWithColor.brightRed("Invalid input! Please try again.");
            userCharInput.nextLine();
            userCharInput.reset();
        }
        userChar = userCharInput.nextLine().toLowerCase().charAt(0);
        return userChar;
    }

    private static int getIntFromUser() { //only 1 copy
        int userNum;
        Scanner userNumberInput = new Scanner(System.in);
        while (!userNumberInput.hasNextInt()) {
            PrintWithColor.brightRed("Invalid input! Try again.");
            userNumberInput.nextLine();
            userNumberInput.reset();
        }
        userNum = userNumberInput.nextInt();
        return userNum;
    }

    private boolean checkIfUserWantsRules() {
        PrintWithColor.brightWhite("Would you like to see the rules? (y/n)");
        return getYesNoFromUserAsChar();
    }

    private static int getGameModeFromUser() { //only 1 copy
        int gameMode = 0;
        boolean done = false;
        PrintWithColor.cyan("Choose your game mode. Enter 1 for Single Player, 2 for Two Player, and 3 to play against the AI.");
        while (!done) {
            gameMode = getIntFromUser();
            if (gameMode < 1 || gameMode > 3) {
                PrintWithColor.brightRed("Please enter a valid choice.");
            } else {
                done = true;
            }
        }
        return gameMode;
    }

    private static int getNumRoundsFromUser() {
        boolean done = false;
        int userInput = 0;
        PrintWithColor.blue("How many rounds would you like to play?");
        while (!done) {
            userInput = getIntFromUser();
            if (userInput < 1) { //||numRounds > 100
                PrintWithColor.brightRed("Please input a valid number of rounds!");
            } else {
                done = true;
            }
        }
        return userInput;
    }

    private boolean askToShowStats(){
        PrintWithColor.brightYellow("Would you like to see the game stats? (y/n)");
        return getYesNoFromUserAsChar();
    }

    private void pause(long millis) { //halts execution for the amount of time passed in
        try { //this has to be a try catch. Exception must be handled
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Interruption error!");
        }
    }

    private String outputCurrentDiceValues() {
        if (gameDice.toString().length() > 0) {
            return "Current dice values are: " + gameDice.toString().substring(0, gameDice.toString().length() - 1); //return dice values without end comma
        } else {
            return "You have no dice remaining!";
        }
    }

    private void releaseAllDice() { //when this is called, all Dice become rollable
        for (int i = 0; i < gameDice.diceInContainer(); i++) {
            gameDice.rollDiceNumber(i);
        }
    }

    private boolean aiKeepRolling() {
        if (gameDice.diceInContainer() < 1) {
            return false;
        }
        return aiScoreboard.generateScore(gameDice) < 15;
    }

    private boolean keepRolling() { //get a valid user input and return it [validation mostly handled by getCharFromUser()]
        PrintWithColor.blue("Would you like to roll again (y/n)?");
        return getYesNoFromUserAsChar();
    }

    private String outputRoll() {
        if (gameDice.toString().length() > 0) {
            return "Roll is: " + gameDice.toString().substring(0, gameDice.toString().length() - 1); //return dice values without end comma
        } else {
            return "You have no dice to roll!";
        }
    }

    private void autoRemoveSevens() {
        final int seven = 7;
        for (int i = 0; i < gameDice.diceInContainer(); i++) {
            int storedValue = gameDice.getSpecificValueOfDice(i);
            for (int k = 0; k < gameDice.diceInContainer(); k++) {
                if (storedValue + gameDice.getSpecificValueOfDice(k) == seven) { //TODO: Test removal thoroughly!!
                    if (gameDice.getSpecificValueOfDice(i) + gameDice.getSpecificValueOfDice(k) == seven) {
                        removeDiceInOrder(i, k);
                    }
                    i = 0;
                    activeStats.increasePairsTally(storedValue);
                    activeStats.increaseRemovals();
                }
            }
        }
    }

    private void removeDiceInOrder(int firstChoiceIndex, int secondChoiceIndex) {
        if (firstChoiceIndex > secondChoiceIndex && gameDice.diceInContainer() > 0) {
            PrintWithColor.yellow("Removed a " + gameDice.getSpecificValueOfDice(secondChoiceIndex));
            PrintWithColor.yellow("Removed a " + gameDice.getSpecificValueOfDice(firstChoiceIndex));
            gameDice.removeDiceNumber(firstChoiceIndex);
            gameDice.removeDiceNumber(secondChoiceIndex);
            PrintWithColor.grey("--------------------");
            PrintWithColor.brightGreen(outputCurrentDiceValues());
        } else if (gameDice.diceInContainer() > 0) {
            PrintWithColor.yellow("Removed a " + gameDice.getSpecificValueOfDice(firstChoiceIndex));
            PrintWithColor.yellow("Removed a " + gameDice.getSpecificValueOfDice(secondChoiceIndex));
            PrintWithColor.grey("--------------------");
            gameDice.removeDiceNumber(secondChoiceIndex);
            gameDice.removeDiceNumber(firstChoiceIndex);
            PrintWithColor.brightGreen(outputCurrentDiceValues());
        }
    }

    private void printWinner(String player1Name, String player2Name, Scoreboard firstScoreboard, Scoreboard secondScoreboard) {
        int firstScore = firstScoreboard.getTotalScore();
        int secondScore = secondScoreboard.getTotalScore();
        if (firstScore > secondScore) {
            PrintWithColor.brightYellow(player1Name + " won!");
        } else if (secondScore > firstScore) {
            PrintWithColor.brightYellow(player2Name + " won!");
        } else {
            PrintWithColor.brightYellow("It's a tie!");
        }
    }

    private void playerRound(Scoreboard scoreboard, int currentRound, String playerName, String turnDialogue, int rounds, Statistics currentStats) {
        activeStats = currentStats;
        releaseAllDice();
        PrintWithColor.cyan(turnDialogue);
        PrintWithColor.brightMagenta("***ROUND " + (currentRound + 1) + "***");
        int rollsRemaining;
        if (isInitial) {
            rollsRemaining = 3;
        } else {
            rollsRemaining = rollsAllowed;
        }
        boolean rollAgain = true;
        gameDice.restore();
        while (rollsRemaining > 0 && rollAgain) {
            gameDice.rollAll();
            activeStats.increaseTotalRolls();
            activeStats.tallyDice(gameDice);
            rollsRemaining--;
            PrintWithColor.green(outputRoll());
            if (rollsRemaining > 0) {
                System.out.println("(" + rollsRemaining + " rolls remaining)");
                autoRemoveSevens();
                rollAgain = keepRolling();
                if (rollAgain) {
                    if (isInitial) {
                        rollsAllowed++;
                    }
                    autoRemoveSevens();
                }
            }
            if (rollsRemaining == 0) {
                autoRemoveSevens();
            }
        }
        PrintWithColor.brightBlue(scoreboard.printfinalScore(gameDice, playerName));//end of while loop
        activeStats.increaseTotalScore(scoreboard.getTempScore());
        if (isInitial) {
            rollsAllowed++;
            isInitial = false;
            if (rollsAllowed == 1 && rounds > 1) {
                PrintWithColor.brightWhite("The first player rolled only once.");
                PrintWithColor.brightYellow("The rest of the game will now be simulated in 5 seconds as the initial role is the only roll you get!");
                pause(5000);
            } else if (rounds > 1) {
                PrintWithColor.brightYellow("***The game will be played with a maximum of " + rollsAllowed + " rolls.***");
            }
        }
    }

    private void aiRound(int currentRound, Statistics currentStats) {
        activeStats = currentStats;
        releaseAllDice();
        PrintWithColor.cyan("--The AI's Turn--");
        PrintWithColor.brightMagenta("***ROUND " + (currentRound + 1) + "***");
        int rollsRemaining = rollsAllowed;
        boolean rollAgain = true;
        gameDice.restore();
        while (rollsRemaining > 0 && rollAgain) {
            gameDice.rollAll();
            activeStats.increaseTotalRolls();
            activeStats.tallyDice(gameDice);
            rollsRemaining--;
            PrintWithColor.green(outputRoll());
            if (rollsRemaining > 0) {
                System.out.println("(" + rollsRemaining + " rolls remaining)");
                autoRemoveSevens();
                rollAgain = aiKeepRolling();
                if (rollAgain) {
                    PrintWithColor.yellow("AI rolled again.");
                    autoRemoveSevens();
                }
            }
            if (rollsRemaining == 0) {
                autoRemoveSevens();
            }
        }
        PrintWithColor.brightBlue(aiScoreboard.printfinalScore(gameDice, "The AI"));
        activeStats.increaseTotalScore(aiScoreboard.getTempScore());//end of while loop
    }

    private void singlePlayerGame(int numRounds, Game newGame) {
        //isInitial = true;
        for (int currentRound = 0; currentRound < numRounds; currentRound++) {
            newGame.playerRound(player1Scoreboard, currentRound, "You", "", numRounds, soloStats);
        }
        PrintWithColor.brightCyan("GAME OVER!");
        player1Scoreboard.gameOver("You");
        if (askToShowStats()) {
            soloStats.printFinalStats();
        }
    }

    private void twoPlayerGame(int numRounds, Game newGame) {
        for (int currentRound = 0; currentRound < numRounds; currentRound++) {
            newGame.playerRound(player1Scoreboard, currentRound, "Player 1", "--Player 1's Turn--", numRounds, p1Stats);
            newGame.playerRound(player2Scoreboard, currentRound, "Player 2", "--Player 2's Turn--", numRounds, p2Stats);
        }
        PrintWithColor.brightCyan("GAME OVER!");
        player1Scoreboard.gameOver("Player 1");
        player2Scoreboard.gameOver("Player 2");
        printWinner("Player 1", "Player 2", player1Scoreboard, player2Scoreboard);
        if (askToShowStats()) {
            p1Stats.printFinalStats();
            p2Stats.printFinalStats();
        }
    }

    private void aiGame(int numRounds, Game newGame) {
        //isInitial = true;
        for (int currentRound = 0; currentRound < numRounds; currentRound++) {
            newGame.playerRound(player1Scoreboard, currentRound, "You", "--Your turn--", numRounds, p1Stats);
            newGame.aiRound(currentRound, aiStats);
        }
        PrintWithColor.brightCyan("GAME OVER!");
        player1Scoreboard.gameOver("You");
        aiScoreboard.gameOver("The AI");
        printWinner("You", "The AI", player1Scoreboard, aiScoreboard);
        if (askToShowStats()) {
            p1Stats.printFinalStats();
            aiStats.printFinalStats();
        }
    }

    public static void main(String[] args) {
        Game newGame = new Game();
        if (newGame.checkIfUserWantsRules()) {
            PrintWithColor.white(gameRules.getGameRules());
        }
        int gameMode = getGameModeFromUser();
        int numRounds = getNumRoundsFromUser();
        switch (gameMode) {
            case 1:
                newGame.singlePlayerGame(numRounds, newGame);
                break;
            case 2:
                newGame.twoPlayerGame(numRounds, newGame);
                break;
            default:
                newGame.aiGame(numRounds, newGame);
                break;
        }
    }
}
