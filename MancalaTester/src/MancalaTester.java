import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class MancalaTester {
    private JButton startBtn;
    private JComboBox stoneOptions;
    private JComboBox themeOptions;

    public MancalaTester() {
        JFrame frame = new JFrame("Mancala Game");
        JPanel startPanel = new JPanel();
        JPanel stonePanel = new JPanel();
        JPanel themePanel = new JPanel();
        JPanel optionsBundle = new JPanel();
        JLabel stoneLabel = new JLabel("Select amount of stones to play with");
        JLabel themeLabel = new JLabel("Select a theme to play with");
        this.stoneOptions = new JComboBox();
        this.themeOptions = new JComboBox();
        this.stoneOptions.addItem("Select-");
        this.stoneOptions.addItem(3);
        this.stoneOptions.addItem(4);
        this.themeOptions.addItem("Select-");
        this.themeOptions.addItem("Classic Theme");
        this.themeOptions.addItem("Surprise Theme");
        this.startBtn = new JButton("START GAME");
        stonePanel.add(stoneLabel);
        stonePanel.add(stoneOptions);
        themePanel.add(themeLabel);
        themePanel.add(themeOptions);
        optionsBundle.add(stonePanel);
        optionsBundle.add(themePanel);
        frame.add(optionsBundle, BorderLayout.CENTER);
        startPanel.add(startBtn);
        frame.add(startPanel, BorderLayout.SOUTH);
        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ClassicalPitTheme classicTheme = new ClassicalPitTheme();
                SurprisePitTheme surpriseTheme = new SurprisePitTheme();
                // set classical theme
                if(themeOptions.getSelectedItem().equals("Classic Theme")) {
                    System.out.println(themeOptions.getSelectedItem());
                    BoardC theBoard = new BoardC(classicTheme);
                    BoardV theBoardViewer = new BoardV(theBoard);
                    theBoard.appendChangeListener(theBoardViewer);
                    if(stoneOptions.getSelectedItem().equals(3)) {
                        theBoard.populateBoard(3);
                    }
                    else if(stoneOptions.getSelectedItem().equals(4)){
                        theBoard.populateBoard(4);
                    }
                    else {
                        System.out.println("Please select a valid stone amount");
                    }
                }
                // set surprise theme
                else if(themeOptions.getSelectedItem().equals("Surprise Theme")){
                    System.out.println("Surprise theme");
                    BoardC theBoard = new BoardC(surpriseTheme);
                    BoardV theBoardVC = new BoardV(theBoard);
                    theBoard.appendChangeListener(theBoardVC);
                    if(stoneOptions.getSelectedItem().equals(3)) {
                        theBoard.populateBoard(3);
                    }
                    else if(stoneOptions.getSelectedItem().equals(4)){
                        theBoard.populateBoard(4);
                    }
                    else {
                        System.out.println("Please select a valid stone amount");
                    }
                }
                else {
                    System.out.println("Please select a valid theme");
                }
            }

        });
        //frame.setSize(500, 300);
        frame.setSize(400, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MancalaTester mancalaGame = new MancalaTester();
    }
}