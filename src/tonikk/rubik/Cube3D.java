package tonikk.rubik;

import javax.swing.*;
import java.awt.*;

public class Cube3D extends JPanel {

    public Cube3D (Cube c) {
        cube = c;
        for (int i = 0; i < c.stickers().length; i++) {
            pols[i] = calc(i);
        }
    }

    private static int xscale = 24;
    private static int yscale = 12;
    private Cube cube;

    private Polygon[] pols = new Polygon[24];
    private static int[] pars = {
            67,99,95,127,
            139,171,199,231,
            173,145,233,205,
            335,367,307,339,
            59,27,119,87,
            17,45,77,105
    };
    static Color colors[] = {
            new Color(255,255,204),
            new Color(0, 204, 204),
            new Color(255, 102, 51)};

    private Polygon calc(int i) {
        int[] x = new int[4];
        int[] y = new int[4];
        int x0 = pars[i] % 15;
        int y0 = pars[i] / 15;
        if (y0 % 2 == 0) {      // --
            x[0] = xscale + x0 * xscale / 2 - xscale + 1;
            x[1] = xscale + x0 * xscale / 2;
            x[2] = xscale + x0 * xscale / 2 + xscale - 1;
            x[3] = xscale + x0 * xscale / 2;
            y[0] = 2 * yscale + y0 * yscale / 2;
            y[1] = 2 * yscale + y0 * yscale / 2 - yscale + 1;
            y[2] = 2 * yscale + y0 * yscale / 2;
            y[3] = 2 * yscale + y0 * yscale / 2 + yscale - 1;
        } else {
            y[0] = 2 * yscale + y0 * yscale / 2 - yscale / 2 - yscale + 1;
            y[1] = 2 * yscale + y0 * yscale / 2 + yscale / 2 - yscale;
            y[2] = 2 * yscale + y0 * yscale / 2 + yscale / 2 + yscale - 1;
            y[3] = 2 * yscale + y0 * yscale / 2 - yscale / 2 + yscale;
            if ((x0 + y0) % 4 < 2) { // \
                x[0] = xscale + x0 * xscale / 2 - xscale / 2 + 1;
                x[1] = xscale + x0 * xscale / 2 + xscale / 2 - 1;
                x[2] = xscale + x0 * xscale / 2 + xscale / 2 - 1;
                x[3] = xscale + x0 * xscale / 2 - xscale / 2 + 1;
            } else {                // /
                x[0] = xscale + x0 * xscale / 2 + xscale / 2 - 1;
                x[1] = xscale + x0 * xscale / 2 - xscale / 2 + 1;
                x[2] = xscale + x0 * xscale / 2 - xscale / 2 + 1;
                x[3] = xscale + x0 * xscale / 2 + xscale / 2 - 1;
            }
        }
        return new Polygon(x, y, 4);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(xscale * 9, yscale * 16);
    }

    @Override    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < pols.length; i++) {
            g.setColor(colors[cube.stickers()[i]]);
            g.fillPolygon(pols[i]);
            g.setColor(Color.BLACK);
            g.drawPolygon(pols[i]);
        }


    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("Cube 2x2x2 / 3 color");
        Cube c = new Cube();
//        c.slots2stickers();
        Cube3D c3d = new Cube3D(c);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(c3d, BorderLayout.CENTER);
        jf.pack();
        jf.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        c.slots2stickers();
        c3d.repaint();
    }

}
