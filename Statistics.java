package pkg;


import java.text.DecimalFormat;

class Statistics { //track total rolls, average of rolls, most commonly rolled dice, times pairs were removed

    private DecimalFormat df = new DecimalFormat("0.0");

    void increaseTotalRolls() {
        this.totalRolls ++;
    }

    void increaseTotalScore(double increaseScore) {
        this.totalScore += increaseScore;
    }

    void increaseRemovals() {
        this.removalCount++;
    }

    private int getRemovalCount(){
        return this.removalCount;
    }

    private String playerName;
    private int totalRolls;
    private double totalScore;
    private int removalCount;
    private int[] diceTallies = new int[6];

    private int threeFourTally;
    private int oneSixTally;
    private int twoFiveTally;

    Statistics (String playerNameInput){
        this.playerName = playerNameInput;
    }

    private String calculateAverageScore() {
        double average = totalScore / (double) totalRolls;
        return df.format(average);
    }

    void tallyDice(AllDice gameDice) {
        for (int i = 0; i < gameDice.diceInContainer(); i++) {
            diceTallies[gameDice.getSpecificValueOfDice(i)-1]++; //increment that space by 1
        }
    }

    private String returnMostRolledDiceValue(){
        StringBuilder output = new StringBuilder();
        int highestIndex = 0;
        for (int i = 0; i<diceTallies.length; i++){
            if (diceTallies[i] > diceTallies[highestIndex]){
                highestIndex = i;
            }
        }
        output.append(highestIndex+1); //initialize the StringBuilder as the highest index and compensate for 0 based
        for (int i = 0; i<diceTallies.length; i++){
            if (diceTallies[i] == diceTallies[highestIndex] && i != highestIndex){
                output.append(" & "+(i+1));
            }
        }
        return output.toString();
    }

    void increasePairsTally (int firstNum) {
        switch (firstNum) {
            case 1:
                oneSixTally++;
                break;
            case 2:
                twoFiveTally++;
                break;
            case 3:
                threeFourTally++;
                break;
            case 4:
                threeFourTally++;
                break;
            case 5:
                twoFiveTally++;
                break;
            default:
                oneSixTally++;
                break;
        }
    }

    private String returnMostCommonRemovedPair(){ //Your most common removed pair was

        if ((oneSixTally == twoFiveTally) && (threeFourTally == oneSixTally)){
            return "no specific pair, since all were removed "+oneSixTally+" times";
        }

        int firstMax = Math.max(oneSixTally, twoFiveTally);
        int finalMax = Math.max(firstMax, threeFourTally);

        if (finalMax == oneSixTally && finalMax ==twoFiveTally) {
            return "1 & 6 as well as 2 & 5";
        } else if (finalMax == oneSixTally && finalMax == threeFourTally){
            return "1 & 6 as well as 3 & 4";
        } else if (finalMax == twoFiveTally && finalMax == threeFourTally){
            return "3 & 4 as well as 2 & 5";
        } else if (finalMax == oneSixTally){
            return "1 & 6";
        } else if (finalMax == twoFiveTally){
            return "2 & 5";
        } else {
            return "3 & 4";
        }
    }

    void printFinalStats(){
        System.out.println(); //blank
        PrintWithColor.blue(playerName+"'s average score for a round was "+calculateAverageScore());
        PrintWithColor.green(playerName+"'s most common dice roll was "+returnMostRolledDiceValue());
        PrintWithColor.yellow(getRemovalCount()+" dice pairs were removed during "+playerName+"'s turn(s)");
        PrintWithColor.purple(playerName+"'s most common removed pair was "+returnMostCommonRemovedPair());
    }
}
