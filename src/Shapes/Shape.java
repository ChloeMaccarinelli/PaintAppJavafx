package Shapes;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

/**
 * On crée la classe abstraite : base pour toutes les formes
 */
public abstract class Shape {

    public String toString()
    {
        return this.getClass().getName();
    }

    private boolean isFilled;
    private Color contour;
    private Color remplissage;
    private double epaisseur;
    private boolean isSelected;
    private Point2D dragelmt;
    private int shapeIndex;


    /**
     * Méthodes abstraites à implémenter pour chaques formes géométriques
     */

    protected abstract double getMinX();
    protected abstract double getMinY();
    protected abstract double getMaxX();
    protected abstract double getMaxY();



    public abstract void drawShape(GraphicsContext GC); /** méthode pour dessiner la forme **/

    protected abstract Point2D middlePoint();

    public abstract void translateShape(double x, double y); /** méthode pour déplacer la forme **/


    /**
     * @param contour
     * @param remplissage
     * @param epaisseur
     * @param isFilled
     */
    Shape(Color contour, Color remplissage, double epaisseur, boolean isFilled)
    {/** constructeur **/

        this.contour = contour;
        this.remplissage = remplissage;
        this.epaisseur = epaisseur;
        this.isFilled = isFilled; /** booléen pour savoir si on dessine une forme pleine ou non **/
        this.dragelmt = new Point2D(0,0);
    }


    private boolean between(double val, double min, double max)
    {
        return (val <= max && val >= min);
    }

    public void drawZone(GraphicsContext GC)
    {
        GC.save();

        GC.setStroke(Color.LIGHTGRAY);
        GC.setFill(Color.LIGHTGRAY);
        GC.setLineWidth(1);
        GC.setLineCap(StrokeLineCap.SQUARE);
        GC.setLineJoin(StrokeLineJoin.ROUND);

        GC.strokeRect(getMinX() - 5, getMinY() - 5, getMaxX() - getMinX() + 10, getMaxY() - getMinY() + 10);

        GC.fillOval(getMinX() - 10, getMinY() - 10, 10,10);
        GC.fillOval(getMinX() - 10, getMaxY(), 10,10);
        GC.fillOval(getMaxX(), getMinY() - 10, 10,10);
        GC.fillOval(getMaxX(), getMaxY(), 10,10);

        GC.restore();
    }

    boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public Color getContour() {
        return contour;
    }

    public void setContour(Color contour) {
        this.contour = contour;
    }

    public Color getRemplissage() {
        return remplissage;
    }

    public void setRemplissage(Color remplissage) {
        this.remplissage = remplissage;
    }

    public double getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(double epaisseur) {
        this.epaisseur = epaisseur;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    Point2D getDragelmt() {
        return dragelmt;
    }

    public void setDragelmt(double x, double y) {
        this.dragelmt= new Point2D(x,y);
    }

    public int getShapeIndex() {
        return shapeIndex;
    }

    public void setShapeIndex(int shapeIndex) {
        this.shapeIndex = shapeIndex;
    }



    public boolean contains(double x, double y)
    {
        return between(x, getMinX(), getMaxX()) && between(y, getMinY(), getMaxY());
    }

}

