import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BoardV implements ChangeListener{
    public BoardV(BoardC board) {
        boardc = board;
        houses = boardc.data();
        JFrame frame = new JFrame();
        JButton undo = new JButton();
        JPanel mancalagrid = new JPanel(new GridLayout(0, 8));
        frame.setTitle("Mancala Game");
        frame.setBackground(Color.GREEN);
        frame.setSize(1000, 600);
        frame.add(undo, BorderLayout.SOUTH);
        frame.add(mancalagrid, BorderLayout.CENTER);
        undo.setText("Undo Turn");
        undo.setBackground(Color.RED);
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boardc.isUndo();
            }
        });
        mancalagrid.add(board.getScore(Gamer.SECOND));
        for ( int i = 0; i <= 5; i++ ) {
            JPanel housesGrid = new JPanel(new GridLayout(2, 0));
            PitContainer gamer1 = houses.get(i);
            PitContainer gamer2 = houses.get(12 - i);
            housesGrid.add(gamer1);
            gamer1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    boardc.selectPitContainer(gamer1);
                }
            });
            housesGrid.add(gamer2);
            gamer2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    boardc.selectPitContainer(gamer2);
                }
            });
            mancalagrid.add(housesGrid);
        }
        mancalagrid.add(board.getScore(Gamer.FIRST));
        turn = new JTextField(boardc.getGamer().toString());
        turn.setHorizontalAlignment(JTextField.CENTER);
        frame.add(turn, BorderLayout.NORTH);
        //mancalagrid.add(housesGrid);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void stateChanged(ChangeEvent event) {
        houses = boardc.data();
        if (boardc.finalScore()) {
            String score1 = "Player 1: " + houses.get(6).getStones();
            String score2 = "Player 2: " + houses.get(13).getStones();
            if (houses.get(6).getStones() > houses.get(13).getStones())
                turn.setText("Marble Count-> " + score1 + score2 + "Winner: Player 1");
            else if (houses.get(6).getStones() < houses.get(13).getStones())
                turn.setText("Marble Count-> " + score1 + score2 + "Winner: Player 2");
            else
                turn.setText("Draw!");
        }
        for ( PitContainer pits : houses ) {
            pits.repaint();
        }
    }
    private BoardC boardc;
    ArrayList<PitContainer> houses;
    JTextField turn;
}
