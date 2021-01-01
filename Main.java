package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] matrix = new char[3][3];



        //Filing
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = ' ';
            }
        }


        System.out.println("---------");
        System.out.println("| " + matrix[0][0] + " " + matrix[0][1] + " " + matrix[0][2] + " |");
        System.out.println("| " + matrix[1][0] + " " + matrix[1][1] + " " + matrix[1][2] + " |");
        System.out.println("| " + matrix[2][0] + " " + matrix[2][1] + " " + matrix[2][2] + " |");
        System.out.println("---------");

        int column = 0;
        int row = 0;
        char lastTurn = 'O';
        int[] data;
        String stat = "Game not finished";

        do {
            System.out.print("Enter the coordinates: ");
            if (scanner.hasNextInt()) {
                row = scanner.nextInt();
            } else {
                System.out.println("\nYou should enter numbers!");
            }
            if (scanner.hasNextInt()) {
                column = scanner.nextInt();
            } else {
                System.out.println("\nYou should enter numbers!");
            }
            if (!((1 <= row && row <= 3)&&(1 <= column && column <= 3))) {
                System.out.println("\nCoordinates should be from 1 to 3!");
                continue;
            }
            if (matrix[3 - column][row - 1] == ' ') {
                if (lastTurn == 'X') {
                    matrix[3 - column][row - 1] = 'O';
                    lastTurn = 'O';
                } else {
                    matrix[3 - column][row - 1] = 'X';
                    lastTurn = 'X';
                }
                data = readField(matrix);
                stat = testField(data[0], data[1], data[2], data[3]);
                printField(matrix);
                if (stat != "Game not finished") {
                    System.out.println(stat);
                }

            } else {
                System.out.println("\nThis cell is occupied! Choose another one!");
            }

        } while (scanner.hasNextInt() && stat == "Game not finished");
    }


    public static String testField(int xs, int os, int xWin, int oWin) {
        if(Math.abs(xs-os)>1) {
            return "Impossible";
        } else if(xWin==1 && oWin==0) {
            return "X wins";
        } else if(xWin==0 && oWin==1) {
            return "O wins";
        } else if(xs+os==9) {
            return "Draw";
        } else if(xWin>0 && oWin >0) {
            return "Impossible";
        } else {
            return "Game not finished";
        }
    }

    public static void printField (char[][] matrix) {
        System.out.println("---------");
        System.out.println("| " + matrix[0][0] + " " + matrix[0][1] + " " + matrix[0][2] + " |");
        System.out.println("| " + matrix[1][0] + " " + matrix[1][1] + " " + matrix[1][2] + " |");
        System.out.println("| " + matrix[2][0] + " " + matrix[2][1] + " " + matrix[2][2] + " |");
        System.out.println("---------");
    }

    public static int[] readField (char[][] matrix) {
        int xs = 0;
        int os = 0;
        int sum = 0;
        int xWin = 0;
        int oWin = 0;
        int[] data = new int[4];
        //Line
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == 'X') {
                    xs++;
                    sum += 'X';
                } else if (matrix[i][j] == 'O') {
                    os++;
                    sum += 'O';
                } else if (matrix[i][j] == '_') {
                    matrix[i][j] = ' ';
                }
            }
            if (sum == 264) {
                xWin += 1;
            }
            if (sum == 237) {
                oWin += 1;
            }
            sum = 0;
        }
        //column
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[j][i] == 'X') {
                    sum += 'X';
                } else if (matrix[j][i] == 'O') {
                    sum += 'O';
                }
            }
            if (sum == 264) {
                xWin += 1;
            }
            if (sum == 237) {
                oWin += 1;
            }
            sum = 0;
        }
        //1st diagonal
        for (int j = 0; j < 3; j++) {
            if (matrix[j][j] == 'X') {
                sum += 'X';
            } else if (matrix[j][j] == 'O') {
                sum += 'O';
            }
        }
        if (sum == 264) {
            xWin += 1;
        }
        if (sum == 237) {
            oWin += 1;
        }
        //2nd diagonal
        sum = 0;
        int z = 0;
        for (int j = 2; j >= 0; j--) {
            if (matrix[z][j] == 'X') {
                sum += 'X';
            } else if (matrix[j][j] == 'O') {
                sum += 'O';
            }
            z++;
        }
        if (sum == 264) {
            xWin += 1;
        }
        if (sum == 237) {
            oWin += 1;
        }
        data[0] = xs;
        data[1] = os;
        data[2] = xWin;
        data[3] = oWin;
        return data;
    }



}