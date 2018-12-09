package pkg;

class PrintWithColor {
    /**
     * ANSI Escape Codes to print with color
     */
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final String BRIGHT_BLACK = "\u001b[30;1m";
    private static final String BRIGHT_RED = "\u001b[31;1m";
    private static final String BRIGHT_GREEN = "\u001b[32;1m";
    private static final String BRIGHT_YELLOW = "\u001b[33;1m";
    private static final String BRIGHT_BLUE = "\u001b[34;1m";
    private static final String BRIGHT_MAGENTA = "\u001b[35;1m";
    private static final String BRIGHT_CYAN = "\u001b[36;1m";
    private static final String BRIGHT_WHITE = "\u001b[37;1m";

    /**
     * Methods (passed a string and print out string with declared color)
     * ALL SYSOUTS MUST END WITH ANSI_RESET!!!
     * @param input
     */
    static void black (String input){
        System.out.println(ANSI_BLACK + input+ ANSI_RESET);
    }

    static void red (String input){ //looks like an exception/error
        System.out.println(ANSI_RED + input+ ANSI_RESET);
    }

    static void green (String input){
        System.out.println(ANSI_GREEN + input+ ANSI_RESET);
    }

    static void yellow (String input){
        System.out.println(ANSI_YELLOW + input+ ANSI_RESET);
    }

    static void blue (String input){
        System.out.println(ANSI_BLUE + input+ ANSI_RESET);
    }

    static void purple (String input){
        System.out.println(ANSI_PURPLE + input+ ANSI_RESET);
    }

    static void cyan (String input){
        System.out.println(ANSI_CYAN + input+ ANSI_RESET);
    }

    static void white (String input){ //grey
        System.out.println(ANSI_WHITE + input+ ANSI_RESET);
    }

    static void brightWhite (String input){
        System.out.println(BRIGHT_BLACK + input+ ANSI_RESET);
    }

    /**
     * Basically bold text
     * @param input
     */
    static void brightRed (String input){
        System.out.println(BRIGHT_RED + input+ ANSI_RESET);
    }

    static void brightGreen (String input){
        System.out.println(BRIGHT_GREEN + input+ ANSI_RESET);
    }

    static void brightYellow (String input){
        System.out.println(BRIGHT_YELLOW + input+ ANSI_RESET);
    }

    static void brightBlue (String input){
        System.out.println(BRIGHT_BLUE + input+ ANSI_RESET);
    }

    static void brightMagenta (String input){
        System.out.println(BRIGHT_MAGENTA + input + ANSI_RESET);
    }

    static void brightCyan (String input){
        System.out.println(BRIGHT_CYAN + input+ ANSI_RESET);
    }

    static void grey (String input){
        System.out.println(BRIGHT_WHITE + input+ ANSI_RESET);
    }
}

