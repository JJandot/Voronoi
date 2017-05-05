import geometry.Point;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/** La classe ZoneSaisirPointsAfficherSegments. */
class ZonePoints extends JPanel  {

    private CanvasSaisirPointsAfficherSegments canvas;

    /** Creation de la zone d'affichage. */
    ZonePoints(){
        canvas = new CanvasSaisirPointsAfficherSegments();

        // Panel des boutons
        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        final JTextField textNombrePoint = new JTextField("50");
        textNombrePoint.setColumns(5);

        JButton rand = new JButton("Rand Points");
        rand.addActionListener(e -> doRandomPoints(Integer.parseInt(textNombrePoint.getText())));

        JButton effacer = new JButton("Effacer");
        effacer.addActionListener(evt -> clearScreen());

        panel.add(effacer);
        panel.add(rand);
        panel.add(textNombrePoint);
        panelBoutons.add(panel, BorderLayout.CENTER);

        JPanel panelScroll = new JPanel();

        JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 100);
        scrollBar.setPreferredSize(new Dimension(500, 30));
        scrollBar.addAdjustmentListener(e -> {
            double value = e.getValue() * canvas.getWidth() / 100;
            canvas.sweepLine.setPointValue(value);
            canvas.repaint();
        });

        panelScroll.add(scrollBar);
        panelBoutons.add(panelScroll, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        add(panelBoutons, BorderLayout.SOUTH);
    }

    private void doRandomPoints(int nbPoints){
        canvas.points.removeAllElements();
        //canvas.segments.removeAllElements();
        Random random = new Random();
        for(int i = 0; i < nbPoints; ++i){
            canvas.points.add(new Point(2 + random.nextDouble() * (canvas.getWidth() - 5), 2 + random.nextDouble() * (canvas.getHeight() - 5)));
        }
        canvas.repaint();
    }

    private void clearScreen(){
        canvas.points.removeAllElements();
       // canvas.segments.removeAllElements();
        canvas.repaint();
    }
}