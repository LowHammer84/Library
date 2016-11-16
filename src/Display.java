import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Display {

    private static boolean      created = false;
    private static JFrame       window;
    private static JList        jList;
    private static JPanel       mainPanel;
    static ArrayList<Score>     scores;

    public static void create(String title){

        if (created)
            return;

        window = new JFrame(title);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jList = new JList<>();

        Score score1 = new Score();
        Score score2 = new Score();
        score1.setId(1);
        score1.setTitle("BlaBla");
        score2.setId(2);
        score2.setTitle("eeeee");
        scores.add(score1);
        scores.add(score2);
        jList.add(scores);

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(800,600));
        mainPanel.add(jList);
        window.getContentPane().add(mainPanel);
        window.pack();
        window.setLocationRelativeTo(null);

        window.setVisible(true);
    }



}
