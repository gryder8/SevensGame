package pkg;

public class GameRules {

    public String getGameRules() {
        String gameRules = "In each round player starts with rolling all 6 dice.\n" +
                "After the roll, the game automatically removes all the combinations that add to 7.\n"+
                "The First Player has the option to roll remaining dice again if he/she wants to increase the score.\n"+
                "This has a risk of decreasing the score if a combination of 7 is rolled again.\n"+
                "The first player can stop after first roll or can continue to roll for one or two more times. Player is not allowed to roll more than 3 times in all.\n"+
                "Subsequent players can only roll as many times as the first player. They can always roll fewer times than the first player.\n"+
                "Player’s score for each round is sum of all dice values remaining in the player’s hand.\n"+
                "The round ends after all players have played.";

        return gameRules;
    }
}
