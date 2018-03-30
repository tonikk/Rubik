package tonikk.rubik;

import javax.swing.*;
import java.awt.*;

public class JCube {

    Cube cube;

    static Color colors[] = {
            new Color(255,255,204),
            new Color(0, 204, 204),
            new Color(255, 102, 51)};

    static int coord[][] = {{0,2}, {2,2}, {2,4}, {4,2}, {2,6}, {2,0}};


    public static void main(String[] args) {
        JCube jc = new JCube();
        jc.cube = new Cube();
//        jc.cube.slots2stickers();
        JPanel jp = new JPanel();
        JFrame jf = new JFrame("Cube 2x2x2");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().add(jp, BorderLayout.CENTER);
        jp.setLayout(new GridLayout(6, 8, 3,3));
        JLabel[][] labs = new JLabel[6][8];
        int[] stickers = jc.cube.stickers();
        for (int face = 0; face < 6; face++) {
            for (int cube = 0; cube < 4; cube++) {
                int row = cube / 2 + coord[face][0];
                int column = cube % 2 + coord[face][1];
                JLabel lab = new JLabel("");
                lab.setMinimumSize(new Dimension(20,20));
                lab.setPreferredSize(new Dimension(30,30));
                lab.setBackground(colors[stickers[face*4 + cube]]);
                lab.setOpaque(true);
                labs[row][column] = lab;
            }
        }
        for (JLabel[] row : labs) {
            for (JLabel lab : row) {
                if (lab == null) { lab = new JLabel(); }
                jp.add(lab);
            }
        }
        jf.setSize(640, 480);
        jf.pack();

        jf.setVisible(true);
    }


}
