package Shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.max;
import static java.util.Collections.min;

public class Crayon extends Shape{
    private List<Line> lines;
    private int nbPoints;

    public Crayon(Color contour, Color remplissage, double epaisseur, boolean isFilled, double x, double y, double x1, double y1)
    {
        super(contour, remplissage, epaisseur, isFilled);
        this.lines = new ArrayList<>();
        this.addPoint(x,y,x1,y1);
    }

    private void addPoint(double x, double y, double x1, double y1)
    {
        this.lines.add(new Line(this.getContour(),this.getRemplissage(),this.getEpaisseur(),this.isFilled(), x, y, x1, y1));
        this.nbPoints++;
    }

    public void addPoint(double x, double y)
    {
        this.lines.add(new Line(this.getContour(),this.getRemplissage(),this.getEpaisseur(),this.isFilled(),this.lines.get(this.nbPoints - 1).getFin().getX(),
                this.lines.get(this.nbPoints - 1).getFin().getY(), x, y));
        this.nbPoints++;
    }

    @Override
    public void setDragelmt(double x, double y)
    {
          super.setDragelmt(x,y);
        for (Line line: this.lines) {
            line.setDragelmt(x,y);
        }
    }

    @Override
    public void drawShape(GraphicsContext GC)
    {
        for (Shape shape: this.lines)
        {
            shape.drawShape(GC);
        }
    }

    @Override
    public Point2D middlePoint()
    {
        Point2D depart = this.lines.get(0).getDepart();
        Point2D fin = this.lines.get(this.nbPoints - 1).getFin();
        return depart.midpoint(fin);
    }

    @Override
    public void translateShape(double x, double y)
    {
        for (Shape line: this.lines)
        {
            line.translateShape(x,y);
        }
    }

    @Override
    public double getMinX()
    {
        List<Double> xDepartArray = new ArrayList<>();
        List<Double> xFinArray = new ArrayList<>();

        for (int i = 0; i < this.nbPoints; i++)
        {
            xDepartArray.add(this.lines.get(i).getDepart().getX());
            xFinArray.add(this.lines.get(i).getFin().getX());
        }
        return Math.min(min(xDepartArray),min(xFinArray));
    }

    @Override
    public double getMinY()
    {
        List<Double> yDepartArray = new ArrayList<>();
        List<Double> yFinArray = new ArrayList<>();

        for (int i = 0; i < this.nbPoints; i++)
        {
            yDepartArray.add(this.lines.get(i).getDepart().getY());
            yFinArray.add(this.lines.get(i).getFin().getY());
        }
        return Math.min(min(yDepartArray),min(yFinArray));
    }

    @Override
    public double getMaxX()
    {
        List<Double> xDepartArray = new ArrayList<>();
        List<Double> xFinArray = new ArrayList<>();

        for (int i = 0; i < this.nbPoints; i++)
        {
            xDepartArray.add(this.lines.get(i).getDepart().getX());
            xFinArray.add(this.lines.get(i).getFin().getX());
        }
        return Math.max(max(xDepartArray),max(xFinArray));
    }

    @Override
    public double getMaxY()
    {
        List<Double> yDepartArray = new ArrayList<>();
        List<Double> yFinArray = new ArrayList<>();

        for (int i = 0; i < this.nbPoints; i++)
        {
            yDepartArray.add(this.lines.get(i).getDepart().getY());
            yFinArray.add(this.lines.get(i).getFin().getY());
        }
        return Math.max(max(yDepartArray),max(yFinArray));
    }

}

