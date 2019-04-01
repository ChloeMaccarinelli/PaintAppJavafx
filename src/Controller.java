import Shapes.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Controller {

    private View view;
    private Model model;
    public Controller(View v, Model m){
        this.view = v;
        this.model = m;
        this.initevent();
    }

    private void initevent() {

        /**
         * Effacer une forme --> boutton suppr et confirmation requise
         */
        view.getScene().setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if (view.getCurrentShape() != null && view.getCurrentShape().isSelected() && view.getSelection().isSelected())
                {
                    if (event.getCode() == KeyCode.DELETE){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setContentText("Voulez-vous vraiment supprimer cette forme ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            model.removeShape(view.getCurrentShape());
                            if (view.getScene().getCursor() == Cursor.MOVE){
                                view.getScene().setCursor(Cursor.DEFAULT);
                            }
                            view.setCurrentShape(null);
                        } else {

                        }
                    }
                    refreshCanvas();
                }
            }
        });

        /**
         * mise en arriere
         * mise en avant
         * mise au dernier plan
         * mise au premier plan
         */
        view.getPullButton().setOnAction((event)->{
            model.moveShapeFirst(view.getCurrentShape());
            refreshCanvas();
        });
        view.getPushButton().setOnAction((event)->{
            model.moveShapeLast(view.getCurrentShape());
            refreshCanvas();
        });
        view.getBackgroundButton().setOnAction((event)->{
            model.moveShapeBackward(view.getCurrentShape());
            refreshCanvas();
        });
        view.getForegroundButton().setOnAction((event)->{
            model.moveShapeForward(view.getCurrentShape());
            refreshCanvas();
        });

    /**
    * Vider la fenêtre
    */
        view.getDelete().setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Voulez-vous vraiment supprimer la fenêtre ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    view.getGc().clearRect(0, 0, view.getCanvas().getWidth(), view.getCanvas().getHeight());
                    model.shapes.clear();
                } else {
                }
            }
        });


        view.getCanvas().setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {

                if (event.isPrimaryButtonDown()){
                    if (view.getToggleGroup().getSelectedToggle() == null){
                        view.setCurrentShape(null);
                    }
                    if (view.getCurrentShape() != null){
                        view.getCurrentShape().setSelected(false);
                        refreshCanvas();
                    }
                    view.initGraphicContext();

                    if (view.getDraw().isSelected()){
                        view.setCurrentShape(new Crayon(view.getContour().getValue(),view.getRemplissage().getValue(),view.getEpaisseurTraits().getValue(),
                                false,event.getX(),event.getY(),event.getX(),event.getY()
                        ));
                    }
                    else if (view.getLine().isSelected()){
                        view.setCurrentShape(new Line( view.getContour().getValue(), view.getRemplissage().getValue(),view.getEpaisseurTraits().getValue(),
                                false,event.getX(),event.getY(),event.getX(),event.getY()
                        ));
                    } else if (view.getRectangle().isSelected()){
                        view.setCurrentShape(new Rectangle(view.getContour().getValue(),view.getRemplissage().getValue(), view.getEpaisseurTraits().getValue(),
                                view.getRemplirShapes().isSelected(),event.getX(), event.getY(),0,0
                        ));
                    }
                    else if (view.getEllipse().isSelected()){
                        view.setCurrentShape(new Ellipse(view.getContour().getValue(),view.getRemplissage().getValue(), view.getEpaisseurTraits().getValue(),
                                view.getRemplirShapes().isSelected(),event.getX(), event.getY(),0,0
                        ));
                    }
                    else if (view.getSelection().isSelected()){
                        view.setCurrentShape(selectShape(event.getX(), event.getY()));
                        if (view.getCurrentShape() != null){
                            view.getCurrentShape().setShapeIndex(model.getIndex(view.getCurrentShape()));
                            view.getCurrentShape().setDragelmt(event.getX(), event.getY());
                            view.getScene().setCursor(Cursor.MOVE);
                        }
                    }
                    /** GOMME
                    else if(view.getGomme().isSelected()){
                        double lineWidth = view.getGc().getLineWidth();
                        view.getGc().clearRect(event.getX() - lineWidth / 2, event.getY() - lineWidth / 2, lineWidth, lineWidth);
                    }**/
                }

                if (view.getCurrentShape() != null){
                    if (
                            view.getSelection().isSelected()
                                    && view.getCurrentShape().isSelected()
                            ){
                        view.getCurrentShape().drawZone(view.getGc());
                        view.initGraphicContext(view.getCurrentShape());
                    }
                    else if (!view.getSelection().isSelected()
                            && !view.getPolygon().isSelected()){
                        model.saveShape(view.getCurrentShape());
                    }
                    else if (!view.getPolygon().isSelected()){
                        refreshCanvas();
                    }

                }
            }
        });

        view.getCanvas().setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()){
                    if (!event.isControlDown()){
                        if (view.getDraw().isSelected()){
                            ((Crayon)view.getCurrentShape()).addPoint(event.getX(), event.getY());
                        }
                        else if (view.getLine().isSelected()){
                            ((Line)view.getCurrentShape()).setFin(event.getX(), event.getY());
                        }
                        else if (view.getRectangle().isSelected()) {
                            ((Rectangle)view.getCurrentShape()).setWidth(event.getX());
                            ((Rectangle)view.getCurrentShape()).setHeight(event.getY());
                        }
                        else if (view.getEllipse().isSelected()) {
                            ((Ellipse)view.getCurrentShape()).setWidth(event.getX());
                            ((Ellipse)view.getCurrentShape()).setHeight(event.getY());
                        }
                      /** GOMME
                        else if(view.getGomme().isSelected()){
                            double lineWidth = view.getGc().getLineWidth();
                            view.getGc().clearRect(event.getX() - lineWidth / 2, event.getY() - lineWidth / 2, lineWidth, lineWidth);
                        }**/
                        else if (view.getSelection().isSelected()& view.getCurrentShape() != null&& view.getCurrentShape().isSelected()){
                            view.getCurrentShape().translateShape(event.getX(), event.getY());
                            view.getCurrentShape().setDragelmt(event.getX(), event.getY());
                            model.updateShape(view.getCurrentShape().getShapeIndex(), view.getCurrentShape());
                            view.initGraphicContext(view.getCurrentShape());
                        }

                    }
                    else {
                        if (view.getDraw().isSelected()){
                            ((Crayon)view.getCurrentShape()).addPoint(event.getX(), event.getY());
                        }
                        else if (view.getLine().isSelected()){
                            ((Line)view.getCurrentShape()).setFin(event.getX(), event.getY());
                        }
                        else if (view.getRectangle().isSelected()) {
                            ((Rectangle)view.getCurrentShape()).setWidth(event.getX());
                            ((Rectangle)view.getCurrentShape()).setHeightEqualToWidth();
                        }

                        else if (view.getEllipse().isSelected()) {
                            ((Ellipse)view.getCurrentShape()).setWidth(event.getX());
                            ((Ellipse)view.getCurrentShape()).setHeightEqualToWidth();
                        }
                    }
                    if (view.getCurrentShape() != null){
                        model.updateShape(view.getCurrentShape().getShapeIndex(), view.getCurrentShape());
                        refreshCanvas();


                    }

                }
            }
        });

        /** Fonctionne pas bien
         *
         */
    /*    view.getCanvas().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (view.getPolygon().isSelected()) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        if (currentPolygon == null) {
                            currentPolygon = new Polygon();
                            currentPolygon.setStroke(Color.BLACK);
                            currentPolygon.setFill(Color.WHITE);
                            view.getBorderPane().getChildren().add(currentPolygon);
                        }
                        currentPolygon.getPoints().addAll(event.getX(), event.getY());
                    } else {
                        currentPolygon = null;
                    }
                }
            }
        });*/


        view.getCanvas().setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if (!view.getPolygon().isSelected()&& view.getCurrentShape() != null && !view.getCurrentShape().isSelected()){

                    if (!event.isControlDown()){
                        if (view.getRectangle().isSelected()){
                            ((Rectangle)view.getCurrentShape()).setWidth(event.getX());
                            ((Rectangle)view.getCurrentShape()).setHeight(event.getY());
                        }
                        else if (view.getEllipse().isSelected()) {
                            ((Ellipse)view.getCurrentShape()).setWidth(event.getX());
                            ((Ellipse)view.getCurrentShape()).setHeight(event.getY());
                        }
                        else if (view.getLine().isSelected()){
                            ((Line)view.getCurrentShape()).setFin(event.getX(), event.getY());
                        }
                        /** GOMME
                        else if(view.getGomme().isSelected()) {
                            double lineWidth = view.getGc().getLineWidth();
                            view.getGc().clearRect(event.getX() - lineWidth /2, event.getY() - lineWidth/2, lineWidth, lineWidth);
                        }**/
                    }
                    else{
                        if (view.getDraw().isSelected()){
                            ((Crayon)view.getCurrentShape()).addPoint(event.getX(), event.getY());
                        }
                        else if (view.getLine().isSelected()){
                            ((Line)view.getCurrentShape()).setFin(event.getX(), event.getY());
                        }
                        else if (view.getEllipse().isSelected()) {
                            ((Ellipse)view.getCurrentShape()).setWidth(event.getX());
                            ((Ellipse)view.getCurrentShape()).setHeightEqualToWidth();
                        }
                        else if (view.getRectangle().isSelected()) {
                            ((Rectangle)view.getCurrentShape()).setWidth(event.getX());
                            ((Rectangle)view.getCurrentShape()).setHeightEqualToWidth();
                        }

                }
                    if (view.getCurrentShape() != null){
                        refreshCanvas();
                    }

                }
            }
        });

        view.getCanvas().setOnMouseMoved(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {

                 if (view.getSelection().isSelected()&& view.getCurrentShape() != null&& view.getCurrentShape().isSelected()
                                && view.getCurrentShape().contains(event.getX(), event.getY())){
                    view.getScene().setCursor(Cursor.MOVE);
                }
                else if (view.getSelection().isSelected() && view.getCurrentShape() != null&& view.getCurrentShape().isSelected()
                                && !view.getCurrentShape().contains(event.getX(), event.getY())){
                    view.getScene().setCursor(Cursor.DEFAULT);
                }
            }
        });
    }

    private Shape selectShape(double x, double y){
        List<Shape> listeShapes = new ArrayList<>();

        for (Shape shape: model.shapes){
            if (shape.contains(x,y)){
                listeShapes.add(shape);
            }
        }

        if (listeShapes.size() > 0){
            Shape selectedShape = listeShapes.get(listeShapes.size() - 1);
            selectedShape.setSelected(true);
            return selectedShape;
        }
        else{
            return null;
        }
    }
    private void drawModel(){
        Shape selectedShape = null;
        for (Shape shape : model.shapes){
            view.initGraphicContext(shape);
            shape.drawShape(view.getGc());
            if (shape.isSelected()){
                selectedShape = shape;
            }
        }
        if (selectedShape != null){
            selectedShape.drawZone(view.getGc());
        }
        view.initGraphicContext();
    }
    public void refreshCanvas(){
        view.getGc().clearRect(0,0,view.getCanvas().getWidth(),view.getCanvas().getHeight());
        drawModel();
    }
}
