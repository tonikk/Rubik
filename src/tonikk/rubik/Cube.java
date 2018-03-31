package tonikk.rubik;
import java.util.*;
import java.util.function.*;

import static java.util.stream.Collectors.*;

import static tonikk.rubik.Element.of;

/*
 * It's simple version of 2x2x2 cube with only 3 colors to start with
 * TODO: Maybe unite pos and slots in one class
 * TODO: Implement moves
 * TODO: Write a parser to give initial state as a string/number
 */
public class Cube {
    private int[] stickers = {
            0, 0, 0, 0,   // U
            1, 1, 1, 1,   // F
            2, 2, 2, 2,   // R
            0, 0, 0, 0,   // D
            1, 1, 1, 1,   // B
            2, 2, 2, 2    // L
    };
    private int[][] pos = {
            {0,20,17},
            {15,11,18},
            {3,8,5},
            {12,23,6},
            {14,19,22},
            {1,16,9},
            {13,7,10},
            {2,4,21}
    };

    private Element[] slots = {
            of(0), of(5), of(5), of(0),     // ULB DRB URF DLF
            of(1), of(2), of(2), of(1)};    // DBL UBR DFR UFL

    private static Map<Integer, Integer> perm2pos = new TreeMap<>();  // (94);
    private static List<Integer> pos2perm = new ArrayList<>(70);
    static {
        for (int i = 0, n = 0; i < 256; i++) {
            if (Integer.bitCount(i) == 4) {
                pos2perm.add(i); perm2pos.put(i, n++);
            }
        }
    }

    public void stickers2slots() {
        for (int i = 0; i < slots.length; i++) {
            slots[i].type(stickers[pos[i][0]], stickers[pos[i][1]]);
        }
    }
    public void slots2stickers() {
        Element e;
        for (int i = 0; i < slots.length; i++) {
            e = slots[i];
            for (int j = 0; j < 3; j++) {
                stickers[pos[i][j]] = e.color(j);
            }
        }
    }

    public int[] stickers() {
        return stickers;
    }

    public void parseStickers(String s) {
        int[] tmp = s.chars()
                .filter(c -> (c >= '0') && (c <= '2'))
                .map(c -> c - '0')
                .limit(24)
                .toArray();
        Map<Integer,Long> map = Arrays.stream(tmp)
                .boxed()
                .collect(groupingBy(Function.identity(),counting()));

        if (tmp.length != 24 || map.get(0) != 8 || map.get(1) != 8 || map.get(2) != 8) {
            throw new IllegalArgumentException("Wrong number of stickers");
        }
        stickers = tmp;
    }

    public static void main(String[] args) {
        System.out.println(perm2pos);
        System.out.println(pos2perm);
        Cube c = new Cube();
        System.out.println(Arrays.deepToString(c.slots));
        c.stickers2slots();
        System.out.println(Arrays.deepToString(c.slots));
        c.slots2stickers();
        c.parseStickers("0101_1010_2222_0101_1010_2222");
        System.out.println(Arrays.toString(c.stickers));


    }
}
