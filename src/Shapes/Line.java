package Shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Line extends Shape{

    private Point2D depart;
    private Point2D fin;

    public Line(Color contour, Color remplissage, double width, boolean isFilled, double x, double y, double x1, double y1){
        super(contour, remplissage, width, isFilled);
        this.depart = new Point2D(x,y);
        this.fin = new Point2D(x1,y1);
    }

    @Override
    public void drawShape(GraphicsContext GC)
    {
        GC.strokeLine(this.depart.getX(),this.depart.getY(), this.fin.getX(), this.fin.getY());
    }

    @Override
    public Point2D middlePoint()
    {
        return depart.midpoint(fin);
    }

    @Override
    public void translateShape(double x, double y)
    {
        double xTranslation = x - this.getDragelmt().getX();
        double yTranslation = y - this.getDragelmt().getY();
        double newX = this.depart.getX() + xTranslation;
        double newY = this.depart.getY() + yTranslation;
        this.depart = new Point2D(newX, newY);
        newX = this.fin.getX() + xTranslation;
        newY = this.fin.getY() + yTranslation;
        this.fin = new Point2D(newX, newY);
    }

    @Override
    public double getMinX()
    {
        return min(depart.getX(), fin.getX());
    }

    @Override
    public double getMinY()
    {
        return min(depart.getY(), fin.getY());
    }

    @Override
    public double getMaxX()
    {
        return max(depart.getX(), fin.getX());
    }

    @Override
    public double getMaxY()
    {
        return max(depart.getY(), fin.getY());
    }

    public Point2D getDepart()
    {
        return depart;
    }

    public void setDepart(double x, double y)
    {
        this.depart = new Point2D(x,y);
    }

    public Point2D getFin()
    {
        return fin;
    }

    public void setFin(double x, double y)
    {
        this.fin = new Point2D(x,y);
    }
}
