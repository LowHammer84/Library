import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Library {

    private static boolean created = false;

    private static JFrame     frame;
    private static JButton    addButton;
    private static JButton    saveButton;
    private static JList      jList;
    private static JTextField idJtf;
    private static JTextField titleJtf;
    private static JTextField authorJtf;
    private static JTextField genreJtf;
    private static JCheckBox  inLibrary;
    private static DefaultListModel  listModel;


    public static void main(String[] args) {
        try {
            Utils.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        create("Score Library");
    }

    public static void create(String title){
        if (created)
            return;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);

        // JList create
        listModel = new DefaultListModel();
        for (Score s: Utils.scores) {
            listModel.add(0,s);
        }
        jList = new JList(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setSelectedIndex(0);
        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {

                    int i = jList.getSelectedIndex();
                    Score temp = (Score)jList.getModel().getElementAt(i);
                    authorJtf.setText(temp.getAuthor());
                    titleJtf.setText(temp.getTitle());
                    genreJtf.setText(temp.getGenre());
                    idJtf.setText(temp.getId()+"");
                    inLibrary.setSelected(temp.isInLibrary());

                    if (jList.getSelectedIndex() == -1) {

                    } else {

                    }
                }
            }
        });
        JScrollPane listScrollPane = new JScrollPane(jList);
        listScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        listScrollPane.setLocation(10,10);
        listScrollPane.setSize(400,200);
        frame.getContentPane().add(listScrollPane);

        // Id field
        JLabel idLabel = new JLabel("№");
        idLabel.setLocation(430, 10);
        idLabel.setSize(90,20);
        idJtf = new JTextField("");
        idJtf.setLocation(500, 10);
        idJtf.setSize(50,20);
        frame.getContentPane().add(idJtf);
        frame.getContentPane().add(idLabel);

        // Title field
        JLabel titleLabel = new JLabel("Название");
        titleLabel.setLocation(430, 40);
        titleLabel.setSize(90,20);
        titleJtf = new JTextField();
        titleJtf.setLocation(500,40);
        titleJtf.setSize(200,20);
        frame.getContentPane().add(titleLabel);
        frame.getContentPane().add(titleJtf);

        // Author field
        JLabel authLabel = new JLabel("Автор");
        authLabel.setLocation(430, 70);
        authLabel.setSize(90,20);
        authorJtf = new JTextField();
        authorJtf.setLocation(500, 70);
        authorJtf.setSize(200,20);
        frame.getContentPane().add(authLabel);
        frame.getContentPane().add(authorJtf);

        // Genre field
        JLabel genreLabel = new JLabel("Жанр");
        genreLabel.setLocation(430, 100);
        genreLabel.setSize(90,20);
        genreJtf = new JTextField();
        genreJtf.setLocation(500, 100);
        genreJtf.setSize(200,20);
        frame.getContentPane().add(genreLabel);
        frame.getContentPane().add(genreJtf);

        // CheckBox
        inLibrary = new JCheckBox("В библиотеке");
        inLibrary.setLocation(496, 130);
        inLibrary.setSize(200,20);
        frame.getContentPane().add(inLibrary);

        // Add button
        addButton = new JButton("Добавить");
        addButton.setLocation(10, 230);
        addButton.setSize(100, 30);
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder stringBuilder = new StringBuilder("");
                stringBuilder.append(titleJtf.getText() + " ");
                stringBuilder.append(authorJtf.getText() + " ");
                stringBuilder.append(genreJtf.getText() + " ");
                stringBuilder.append(idJtf.getText() + " ");
                listModel.addElement(stringBuilder);
                jList.repaint();
                Utils.add(titleJtf.getText(),authorJtf.getText(),genreJtf.getText(),inLibrary.isSelected());
            }
        });

        // Save button
        saveButton = new JButton("Сохранить");
        saveButton.setLocation(120, 230);
        saveButton.setSize(100,30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Utils.saveToFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        frame.getContentPane().add(saveButton);

        frame.getContentPane().add(addButton);

        frame.setVisible(true);
    }

}
