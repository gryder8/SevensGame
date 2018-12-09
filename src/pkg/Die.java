package pkg;

/**
 * TEMPLATE FOR DIE OBJECTS
 */
 class Die {
    private int numSides;

     int getNumSides() {
        return numSides;
    }

     void setNumSides(int numSides) {
        this.numSides = numSides;
    }

    private int currentValue;
    private boolean held = false;

     Die() { //each Die object is rolled when rollAll() is called
        numSides = 6; // default constructor
    }

     Die(int sides) { //each Die is rolled when rollAll() is called
        numSides = sides;  // can set the number of sides
    }

     Die (int sides, int initialValue) {
        numSides = sides;
        currentValue = initialValue;
    }

     void roll() {
        if (!this.isHeld()) {
            this.setCurrentValue((int) (Math.random() * numSides + 1)); //randomize each Die
        }
         //this.setCurrentValue(roll);
    }

     public String toString() {
        return currentValue + "";
    }

    private void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

     int getCurrentValue() {
        return currentValue;
    }

    private boolean isHeld() {
        return held;
    }


     void release() { //setter
        this.held = false;
    } //set the local instance boolean held to false

}