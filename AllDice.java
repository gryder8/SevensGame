package pkg;

import java.util.ArrayList;

public class AllDice {

    /**
     * CONSTRUCTORS
     */
    ArrayList<Die> dice = new ArrayList<Die>(6);


    AllDice() {
        for (int i = 0; i<6; i++) {
            dice.add(i, new Die());
        }
    }

    /**
     * INSTANCE METHODS
     */
    int diceInContainer() {
        return dice.size();
    }

    void restore(){
        while (diceInContainer()<6){
            dice.add(new Die());
        }
    }

    void removeDiceNumber(int diceNumber) {
        dice.remove(diceNumber);
    }

    void rollDiceNumber(int diceNumber) {
        dice.get(diceNumber).release();
    }


    void rollAll() { //no return needed
        for (int i = 0; i < dice.size(); i++) {
            dice.get(i).roll();
            //dice.get(i).roll(i+1);
        }
//        dice.get(0).roll(1);
//        dice.get(1).roll(1);
//        dice.get(2).roll(2);
//        dice.get(3).roll(2);
//        dice.get(4).roll(3);
//        dice.get(5).roll(3);
    }

    int getSpecificValueOfDice(int index) {
        return dice.get(index).getCurrentValue();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < dice.size(); i++) {
            output.append(dice.get(i).getCurrentValue() + ","); //value of given object toString()
        }
        return output.toString();
    }
}