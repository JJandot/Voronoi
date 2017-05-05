import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

/** La classe CanvasSaisirPointsAfficherSegments. */
class CanvasSaisirPointsAfficherSegments extends JPanel implements MouseListener, MouseMotionListener {
    /** La liste des points affiches. */
    Vector<geometry.Point> points;

    /** La liste des segments affiches. */
    //Vector<Segment> segments;

    /** Le numero du point selectionne. */
    private int numSelectedPoint;

    /** La couleur d'un point a l'ecran. */
    private final Color pointColor = Color.GRAY;

    /** La couleur d'un segment a l'ecran. */
    private final Color segmentColor = Color.BLUE;

    /** La couleur d'un point selectionne a l'ecran. */
    private final Color selectedPointColor = Color.RED;

    /** La taille d'un point a l'ecran. */
    private final int POINT_SIZE = 2;

    /** Creation de la zone d'affichage. */
    CanvasSaisirPointsAfficherSegments()
    {
        // Creation du vecteur de points
        points = new Vector<>();

        // Creation du vecteur de segments
        //segments = new Vector<>();

        // Initialisation du point selectionne
        numSelectedPoint = -1;

        // Initialisation de la couleur de fond
        setBackground(Color.WHITE);

        // Ajout de la gestion des actions souris
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /** Dessin de la zone d'affichage. */
    public void paint(Graphics g) {
        // Efface le fond
        g.clearRect(0,0,getWidth(),getHeight());

        // Dessin des segments
        //drawSegments(g);

        // Dessin des points
        drawPoints(g);
    }

    /** Affichage des points. */
    private void drawPoints(Graphics g) {
        for (int n = 0; n < points.size(); n++) {
            geometry.Point p = points.elementAt(n);

            if ( n == numSelectedPoint )
                g.setColor(selectedPointColor);
            else
                g.setColor(pointColor);

            g.fillOval((int)(p.x - POINT_SIZE), (int)(p.y - POINT_SIZE), 2 * POINT_SIZE + 1, 2 * POINT_SIZE + 1);
            g.drawOval((int)(p.x - 2 * POINT_SIZE), (int)(p.y - 2 * POINT_SIZE), 2 * 2 * POINT_SIZE,	2 * 2 * POINT_SIZE);
            //g.drawString(Double.toString(p.x), (int)p.x - 50, (int)p.y);
        }
    }

    /** Affichage des segments.
     */
    /*private void drawSegments(Graphics g) {
        for (int n = 0; n < segments.size(); n++) {
            Segment segment = segments.elementAt(n);
            g.setColor(segmentColor);
            g.drawLine((int)segment.a.x,(int)segment.a.y,(int)segment.b.x,(int)segment.b.y);
        }
    }*/

    /** Retourne le numero du point situe en (x,y). */
    private int getNumSelectedPoint(int x, int y) {
        for(int n = 0; n < points.size(); n++)
        {
            geometry.Point p = points.elementAt(n);
            if
                    (
                    p.x > x - 2 * POINT_SIZE &&
                            p.x < x + 2 * POINT_SIZE &&
                            p.y > y - 2 * POINT_SIZE &&
                            p.y < y + 2 * POINT_SIZE
                    )
                return n;
        }
        return -1;
    }

    /** Un point est ajoute si on presse le bouton de gauche
     * 	et si aucun point n'est selectionne.
     * 	Un point est suuprime si on presse un autre bouton
     * 	et si un point est selectionne.
     */
    public void mousePressed(MouseEvent evt) {
        if ( evt.getButton() == 1 )
        {
            if (numSelectedPoint == -1)
            {
                numSelectedPoint = points.size();
                points.addElement(new geometry.Point(evt.getX(), evt.getY()));
                repaint();
            }
        }
        else
        {
            if (numSelectedPoint != -1)
            {
                points.removeElementAt(numSelectedPoint);
                numSelectedPoint = getNumSelectedPoint(evt.getX(), evt.getY());
                repaint();
            }
        }
    }

    /** Le x et y du point numSelectedPoint est modifie si
     * 	la souris change de position avec un bouton enfonce.
     */
    public void mouseDragged(MouseEvent evt) {
        if (numSelectedPoint != -1)
        {
            points.elementAt(numSelectedPoint).x = evt.getX();
            points.elementAt(numSelectedPoint).y = evt.getY();
            //long initTime = System.nanoTime();
            //segments = Algorithms.grahamScan(points, segments);
            //System.out.println("Travail effectué en : " + (System.nanoTime() - initTime) + " nanosecondes");
            repaint();
        }
    }

    /** Le numSelectedPoint est calcule si
     * 	la souris change de position sans bouton enfonce.
     */
    public void mouseMoved(MouseEvent evt) {
        numSelectedPoint = getNumSelectedPoint(evt.getX(), evt.getY());
        repaint();
    }

    /** Lance l'algorithme sur l'ensemble de points. */

    public void mouseReleased(MouseEvent evt) {}
    public void mouseEntered(MouseEvent evt) {}
    public void mouseExited(MouseEvent evt) {}
    public void mouseClicked(MouseEvent evt) {}
}
