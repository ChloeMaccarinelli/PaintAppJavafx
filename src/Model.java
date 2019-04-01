import Shapes.Shape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Model implements Serializable {
    List<Shape> shapes;


    public Model()
    {
        shapes = new ArrayList<>();

    }

    /**
     * Mettre une forme en avant
     * @param shape
     */

    public void moveShapeForward(Shape shape)
    {
        if (this.shapes.size() > 1 && shape != this.shapes.get(this.shapes.size() - 1))
        {
            swapShapes(shape, this.shapes.get(getIndex(shape) + 1));
        }
    }

    /**
     * Mettre une forme en arriere
     * @param shape
     */
    public void moveShapeBackward(Shape shape)
    {
        if (this.shapes.size() > 1 && shape != this.shapes.get(0))
        {
            swapShapes(shape, this.shapes.get(getIndex(shape) - 1));
        }
    }

    /**
     * Mettre une forme au premier plan
     * @param shape
     */
    public void moveShapeFirst(Shape shape)
    {
        if (this.shapes.size() > 1 && shape != this.shapes.get(this.shapes.size() - 1))
        {
            swapShapes(shape, this.shapes.get(this.shapes.size() - 1));
        }
    }

    /**
     * Mettre une forme au dernier plan
     * @param shape
     */
    public void moveShapeLast(Shape shape)
    {
        if (this.shapes.size() > 1 && shape != this.shapes.get(0))
        {
            swapShapes(shape, this.shapes.get(0));
        }
    }

    private void swapShapes(Shape shape1, Shape shape2)
    {
        if (shape1 != shape2)
        {
            Collections.swap(this.shapes, getIndex(shape1), getIndex(shape2));
        }
        updateIndexes();
    }

    public void saveShape(Shape shape)
    {
        this.shapes.add(shape);
        shape.setShapeIndex(getIndex(shape));
    }

    public int getIndex(Shape shape){
        return this.shapes.indexOf(shape);
    }

    public void updateShape(int index, Shape shape){
        this.shapes.set(index, shape);
    }

    public void removeShape(Shape shape)
    {
        this.shapes.remove(shape);
        updateIndexes();
    }

    private void updateIndexes()
    {
        for (int i = 0; i < this.shapes.size(); i++){
            shapes.get(i).setShapeIndex(i);
        }
    }

}

