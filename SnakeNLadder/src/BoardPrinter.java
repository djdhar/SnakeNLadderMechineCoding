import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class BoardPrinter {

    public static void printBoard(Map<Integer,Integer> items, Map<String, Integer> pos) {
        Vector<String> board = new Vector<>(100);
        board.setSize(100);

        //board.set(pos-1, "(OO)");
        AtomicInteger S= new AtomicInteger(1);
        AtomicInteger L= new AtomicInteger(1);

        int value = 9;
        HashMap<Integer, Integer> mapper = new HashMap<>();
        for(int i=0;i<=9;i++) {
            mapper.put(i, value-i);
        }

        items.forEach((k,v)-> {
            if(k>v) {
                //Snake
                board.set(v-1, "S"+S);
                board.set(k-1, "S"+S);
                S.set(S.get() + 1);
            } else {
                //Ladder
                board.set(v-1, "L"+L);

                board.set(k-1, "L"+L);
                L.set(L.get() + 1);
            }
        });
        int n=100;
        for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                int val = n;
                if(((n-1)/10)%2==0) {
                    val = 10*((n-1)/10) + mapper.get((n-1)%10);
                    val+=1;
                }
                if(pos.containsValue(val)) {
                    System.out.print("("+getPlayer(pos, val)+") ");
                } else {
                    if(board.get(val-1)!=null) {
                        System.out.print("(" + board.get(val-1) + ") ");
                    } else {
                        System.out.print("(" + changeN(val) + ") ");
                    }
                }
                n--;
            }
            System.out.println();
        }
    }

    private static String changeN(int n) {
        if(n>=1 && n<=9) return n+" ";
        if(n==100) return "FF";
        else return String.valueOf(n);
    }

    public static String getPlayer(Map<String, Integer> map, Integer desiredValue) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(desiredValue)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
