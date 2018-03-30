package tonikk.rubik;

/*
  Element in 2x2x2 cube is a corner element.

  TODO: Check whether the same class can be later used for edges

  1. Type is integer now, it represents (for this 2x2x2 cube) one of 2 types of corners.
  Colors are the same, the difference is only in color order.
  2. Type can also represent orientation. But it has positive and negative impacts.
  Positive is we have only one cube parameter instead of two
  Negative is we have more complicated move tables.


 */

public class Element {
    private int type;
    private int orientation;

    private int[][] types = {
            {-1,1,0},
            {2,-1,5},
            {3,4,-1}
    };

    public Element(int t, int o) {
        type = t;
        orientation = o;
    }
    public Element(int n) {
        this(n % 2, n / 2 % 3);
    }

    public static Element of(int t) {
        return new Element(t);
    }

    public static Element of(int t, int o) {
        return new Element(t, o);
    }
    public void set(int c0, int c1, int c2) {
        if (types[c0][c1] < 0) { throw new IllegalStateException("Wrong element type"); }
        type = types[c0][c1] % 2;
        orientation = types[c0][c1] / 2 % 3;
    }


    public void type(int c0, int c1) {
        if (types[c0][c1] < 0) { throw new IllegalStateException("Wrong element type"); }
        type = types[c0][c1] % 2;
        orientation = types[c0][c1] / 2 % 3;
    }
    public int type() {
        return type;
    }
    public int orientation() {
        return orientation;
    }
    public void twist(int t) {
        orientation += t;
        orientation %= 3;
    }
    public String toString() {
        return orientation * 2 + type + "";
    }
    public int color(int x) {
        return (6 + (2 * type - 1) * (x - orientation)) % 3;
    }

}
