package pkg;

class Scoreboard {

    /**
     * In each round player starts with rolling all 6 dice. After the roll player must remove all dice combinations which add up to 7.
     * The first Player has the option to roll remaining dice again if he/she wants to increase the score.
     * This has a risk of decreasing the score if a combination of 7 is rolled again.
     * The first player can stop after first roll or can continue to roll for one or two more times.
     * Player is not allowed to roll more than 3 times in all.
     * Subsequent players can only roll as many times as the first player. They are allowed to go for fewer rolls than the first player.
     * Player’s score for each round is sum of all dice values remaining in the player’s hand.
     * Dice combinations of 7 removed from the game are not added to the score.
     * The round ends after all players have played.
     * New round is started by the player next to first player (clockwise) in the previous round.
     * Game can be played for predetermined number of rounds and player with maximum score after completion of all rounds is the overall winner.
     *
     * http://www.netexl.com/howtoplay/sevens/
     */

    private int totalScore;
    private int tempScore;

    Scoreboard (){
        totalScore = 0;
        tempScore = 0;
    }

    /**
     * getters and setters
     * @return value
     */
    int getTotalScore() {
        return totalScore;
    }

    int getTempScore() {
        return tempScore;
    }

    int generateScore(AllDice gameDice){
        int tempScore = 0;
        for (int i = 0; i<gameDice.diceInContainer(); i++){
            tempScore += gameDice.getSpecificValueOfDice(i);
        }
        return tempScore;
    }

    String printfinalScore(AllDice gameDice, String playerName){
        tempScore = generateScore(gameDice);
        totalScore += generateScore(gameDice);
        return "Round over! " + playerName +" scored "+ tempScore + " points. Total score is "
                + totalScore+ " points.";
    }

    void gameOver(String playerName){
        PrintWithColor.purple(playerName + " scored "+totalScore+" points!");
    }
}
