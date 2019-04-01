package Shapes;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

import static java.lang.Math.abs;

public class Rectangle extends Shape
{

    private Point2D depart;
    private double width;
    private double height;

    public Rectangle(Color contour, Color remplissage, double width, boolean isFilled, double x, double y, double w, double h)
    {
        super(contour, remplissage, width, isFilled);
        this.depart = new Point2D(x,y);
        this.width = w;
        this.height = h;
    }


    public void setHeightEqualToWidth(){
        this.height = this.width;
    }


    /**
     * Implémentation des méthodes ABSTRAITES de SHAPE
     *
     * @param GC
     */
    @Override
    public void drawShape(GraphicsContext GC)
    {
        double translatedX = this.depart.getX();
        double translatedY = this.depart.getY();

        if (this.width < 0){ translatedX += this.width; }

        if (this.height < 0){ translatedY += this.height; }

        if (!this.isFilled()){ GC.strokeRect( translatedX,translatedY,abs(this.width),abs(this.height)); }

        else if (this.isFilled() && !(GC.getLineCap() == StrokeLineCap.ROUND)){ GC.fillRect(translatedX,translatedY,abs(this.width),abs(this.height));}
    }


    @Override
    public Point2D middlePoint()
    {
        return new Point2D(this.width/2, this.height/2);
    }

    /**
     * Duplication du code avec translateShape() dans Ellipse.java
     * @param x
     * @param y
     */

    @Override
    public void translateShape(double x, double y)
    {
        double xTranslation = x - this.getDragelmt().getX();
        double yTranslation = y - this.getDragelmt().getY();
        double newX = this.depart.getX() + xTranslation;
        double newY = this.depart.getY() + yTranslation;
        this.depart = new Point2D(newX, newY);
    }

    @Override
    public double getMinX()
    {
        if (this.width < 0)
        {
            return this.depart.getX() + this.width;
        }
        else
        {
            return this.depart.getX();
        }
    }

    @Override
    public double getMinY()
    {
        if (this.height < 0)
        {
            return this.depart.getY() + this.height;
        }
        else
        {
            return this.depart.getY();
        }
    }

    @Override
    public double getMaxX()
    {
        if (this.width < 0)
        {
            return this.depart.getX();
        }
        else
        {
            return this.depart.getX() + this.width;
        }
    }

    @Override
    public double getMaxY()
    {
        if (this.height < 0)
        {
            return this.depart.getY();
        }
        else
        {
            return this.depart.getY() + this.height;
        }
    }


    /**
     * ACCESSEURS
     *
     * @return
     */
    public Point2D getDepart() {
        return depart;
    }

    public void setDepart(Point2D depart) {
        this.depart = depart;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width - this.depart.getX();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height - this.depart.getY();
    }
}

