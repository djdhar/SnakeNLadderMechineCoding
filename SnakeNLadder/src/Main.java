import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

        Map<Integer, Integer> items = Map.of(
                4, 20,
                7, 55,
                9, 72,
                20, 40,
                38, 12,
                89, 7,
                70, 50,
                26, 8,
                99, 3
        );
        int i=0;
        int players = 5;
        int[] pos = new int[players];
        Arrays.fill(pos, 1);

        while (true) {
                int playerNo = i%players+1;
                System.out.print("Player " + playerNo + " turns : ");
                Scanner sc= new Scanner(System.in);
                sc.nextLine();
                int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
                System.out.println("Your random dice is : "+ randomNum);
                pos[playerNo-1]+=randomNum;
                if(items.containsKey(pos[playerNo-1])) {
                    printConsequence(pos[playerNo-1], items.get(pos[playerNo-1]));
                    pos[playerNo-1]=items.get(pos[playerNo-1]);
                }
                if(pos[playerNo-1]>=100) {System.out.println("Player 1 WINS"); break;}
                BoardPrinter.printBoard(items, makeMap(pos));
                i++;
            }
    }

    private static Map<String, Integer> makeMap(int[] pos) {
        HashMap<String, Integer> map = new HashMap<>();
        for(int x=0; x<pos.length;x++) {
            map.put("P"+(x+1), pos[x]);
        }
        return map;
    }

    private static void printConsequence(int key, int val) {
        if(key<val) {
            System.out.println("GREAT! YOU HAVE RIDDED A LADDER ! (" + key + ") to (" + val+")");
        } else {
            System.out.println("OPPS! YOU HAVE TAKEN BY A SNAKE ! (" + key + ") to (" + val+")");
        }
    }
}