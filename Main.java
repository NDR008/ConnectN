class Main {
    public static void main(String[] args) {
        Game game; //Object name

        /* Game can change x-y size of board AND connectN */
        if (args.length == 3) {
            if (isNumeric(args[0]) && isNumeric(args[1]) && isNumeric(args[2])) {
                int arg0 = Integer.parseInt(args[0]);
                int arg1 = Integer.parseInt(args[1]);
                int arg2 = Integer.parseInt(args[2]);
                if (checkBoardSize(arg0) && checkBoardSize(arg1) && checkRange(arg2)) {
                    game = new Game(arg0, arg1, arg2);
                } else {
                    System.out.println("Board axis must be less than 10 and larger than 5.");
                    System.out.println("ConnectN - where N is less than 7 and larger than 2.");
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
            Integer.parseInt(input);
            return true;
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

    public static boolean checkBoardSize(int value) {
        if ( (value < 10) && (value > 5) ){
            return true;
        }
        return false;
    }

    public static void expectedArg() {
        System.out.println("It is expected that you call the program with 3 arguments:");
        System.out.println("Argument 0 - number of columns >5 & <10");
        System.out.println("Argument 1 - number of rows >5 & <10");
        System.out.println("Argument 2 - number of connects to win >2 & <7");
    }

}