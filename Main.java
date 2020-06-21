class Main {
    public static void main(String[] args) {
        // Do something with args later
        Game game;
        if (args.length == 3) {
            if (isNumeric(args[0]) && isNumeric(args[1]) && isNumeric(args[2])) {
                int arg0 = Integer.parseInt(args[0]);
                int arg1 = Integer.parseInt(args[1]);
                int arg2 = Integer.parseInt(args[2]);
                if (checkRange(arg0) && checkRange(arg1) && checkRange(arg2)) {
                    game = new Game(arg0, arg1, arg2);
                } else {
                    System.out.println("All Arguments should be 2 < n < 7");
                    return;
                }
            } else {
                expectedArg();
                return;
            }
        } else if (args.length>0) {
            expectedArg();
            return;
        }else{
            System.out.println("Loading default game");
            game = new Game();
        }
        game.playGame();
    }

    public static boolean isNumeric(String input) {
        try {
            {
                Integer.parseInt(input);
                return true;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean checkRange(int value) {
        if ( (value < 7) && (value > 2) ){
            return true;
        }
        return false;
    }

    public static void expectedArg() {
        System.out.println("It is expected that you call the program with 3 arguments:");
        System.out.println("Argument 0 - number of columns");
        System.out.println("Argument 1 - number of rows");
        System.out.println("Argument 2 - number of connects to win ");
    }

}