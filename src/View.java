import Shapes.Shape;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Optional;

public class View extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    Model me = new Model(); // serialisation
    /**
     * Initialisation
     */
    private Scene scene;
    private BorderPane borderPane;
    private GridPane gridPaneRight;
    private GridPane gridPaneLeft;
    private GridPane gridPaneTop;
    private GridPane gridPaneBottom;
    private MenuBar menuBar;
    private Menu menuFile;
    private MenuItem sousMenuHelp;
    private MenuItem sousMenuOpen;
    private MenuItem sousMenuQuit;
    private MenuItem sousMenuSaveAs;
    private ToolBar toolBar;
    private ToggleButton selection;
    private ToggleButton draw;
    private ToggleButton line;
    private ToggleButton rectangle;
    private ToggleButton ellipse;
    private ToggleButton polygon;
    private ToggleGroup toggleGroup;
    private ColorPicker contour;
    private ColorPicker remplissage;
    private Slider epaisseurTraits;
    private ToggleButton remplirShapes;
    private Button pullButton;
    private Button pushButton;
    private Button foregroundButton;
    private Button backgroundButton;
    private Button delete;
    private Canvas canvas;
    private GraphicsContext gc;
    private Shape currentShape;
    private ToggleButton gomme;


    public ToggleButton getRemplirShapes() {
        return remplirShapes;
    }

    public Scene getScene() {
        return scene;
    }

    private MenuItem getSousMenuHelp() {
        return sousMenuHelp;
    }

    private MenuItem getSousMenuOpen() {
        return sousMenuOpen;
    }

    private MenuItem getSousMenuQuit() {
        return sousMenuQuit;
    }

    private MenuItem getSousMenuSaveAs() {
        return sousMenuSaveAs;
    }

    public ToggleButton getDraw() {
        return draw;
    }

    public ToggleButton getLine() {
        return line;
    }

    public ToggleButton getRectangle() {
        return rectangle;
    }

    public ToggleButton getEllipse() {
        return ellipse;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public ColorPicker getContour() {
        return contour;
    }

    public Slider getEpaisseurTraits() {
        return epaisseurTraits;
    }


    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(Shape shape) {
        this.currentShape = shape;
    }

    public ToggleButton getPolygon() {
        return polygon;
    }

    public ColorPicker getRemplissage() {
        return remplissage;
    }

    public Button getDelete() {
        return delete;
    }


    public ToggleButton getSelection() {
        return selection;
    }

    public Button getPullButton() {
        return pullButton;
    }

    public Button getPushButton() {
        return pushButton;
    }

    public Button getForegroundButton() {
        return foregroundButton;
    }

    public Button getBackgroundButton() {
        return backgroundButton;
    }


    public View(){

        borderPane = new BorderPane();
        gridPaneRight = new GridPane();
        scene = new Scene(borderPane);
        gridPaneLeft = new GridPane();
        gridPaneTop = new GridPane();
        gridPaneBottom = new GridPane();

        menuBar = new MenuBar();
        menuFile = new Menu("File");
        sousMenuHelp = new MenuItem("Help");
        sousMenuOpen = new MenuItem("Open");
        sousMenuQuit = new MenuItem("Quit");
        sousMenuSaveAs = new MenuItem("Save as");

        toolBar = new ToolBar();

        selection = new ToggleButton("");
        gomme = new ToggleButton("");
        draw = new ToggleButton("");
        line = new ToggleButton("");
        rectangle = new ToggleButton("");
        ellipse = new ToggleButton("");
        polygon = new ToggleButton("");
        toggleGroup = new ToggleGroup();

        contour = new ColorPicker();
        remplissage = new ColorPicker();
        epaisseurTraits = new Slider();
        remplirShapes = new ToggleButton("");

        delete = new Button("");
        pullButton = new Button("");
        pushButton = new Button("");
        foregroundButton = new Button("");
        backgroundButton = new Button("");

        canvas = new Canvas(800,500);
        gc = canvas.getGraphicsContext2D();
        currentShape = null;
    }

    @Override
    public void start(Stage stage) {

        Model m = new Model();
        Controller c = new Controller(this, m);


        /**
         * ICONS et TOOLTIPS pour les informations sur les boutons
         */
        final Tooltip tooltip0 = new Tooltip();
        tooltip0.setText("Epaisseur des traits");
        epaisseurTraits.setTooltip(tooltip0);

        final Tooltip tooltip1 = new Tooltip();
        tooltip1.setText("Sélection de forme");
        selection.setTooltip(tooltip1);
        selection.setGraphic(new ImageView("Icons/selection.png"));

        final Tooltip tooltip2 = new Tooltip();
        tooltip2.setText("Remplisssage de forme");
        remplirShapes.setTooltip(tooltip2);
        remplirShapes.setGraphic(new ImageView("Icons/remplirshape.png"));


        final Tooltip tooltip3=new Tooltip();
        tooltip3.setText("Vider la fenêtre");
        delete.setTooltip(tooltip3);
        delete.setGraphic(new ImageView("Icons/supprimer.png"));

        sousMenuHelp.setGraphic(new ImageView("Icons/help.png"));
        sousMenuOpen.setGraphic(new ImageView("Icons/open.png"));
        sousMenuSaveAs.setGraphic(new ImageView("Icons/saveas.png"));
        sousMenuQuit.setGraphic(new ImageView("Icons/quit.png"));

        final Tooltip tooltip4=new Tooltip();
        tooltip4.setText("Dessin libre");
        draw.setTooltip(tooltip4);
        draw.setGraphic(new ImageView("Icons/crayon.png"));


        final Tooltip tooltip5=new Tooltip();
        tooltip5.setText("Gomme");
        gomme.setTooltip(tooltip5);
        gomme.setGraphic(new  ImageView("Icons/gomme.png"));
        gomme.setDisable(true);

        final Tooltip tooltip6=new Tooltip();
        tooltip6.setText("Trait droit");
        line.setTooltip(tooltip6);
        line.setGraphic(new ImageView("Icons/line.png"));

        final Tooltip tooltip7=new Tooltip();
        tooltip7.setText("ellipse" + "\n"+"+ Ctrl => cercle");
        ellipse.setTooltip(tooltip7);
        ellipse.setGraphic(new ImageView("Icons/ellipse.png"));

        final Tooltip tooltip8=new Tooltip();
        tooltip8.setText("rectangle"+ "\n"+"+ Ctrl => carré");
        rectangle.setTooltip(tooltip8);
        rectangle.setGraphic(new ImageView("Icons/rectangle.png"));

        final Tooltip tooltip9=new Tooltip();
        tooltip9.setText("polygone");
        polygon.setTooltip(tooltip9);
        polygon.setGraphic(new ImageView("Icons/polygon.png"));
        polygon.setDisable(true);

        final Tooltip tooltip10=new Tooltip();
        tooltip10.setText("Mettre au premier plan");
        pullButton.setTooltip(tooltip10);
        pullButton.setGraphic(new ImageView("Icons/foreground.png"));

        final Tooltip tooltip11=new Tooltip();
        tooltip11.setText("Mettre au dernier plan");
        pushButton.setTooltip(tooltip11);
        pushButton.setGraphic(new ImageView("Icons/background.png"));

        final Tooltip tooltip12=new Tooltip();
        tooltip12.setText("Mettre en avant");
        foregroundButton.setTooltip(tooltip12);
        foregroundButton.setGraphic(new ImageView("Icons/pull.png"));

        final Tooltip tooltip13=new Tooltip();
        tooltip13.setText("Mettre en arrière");
        backgroundButton.setTooltip(tooltip13);
        backgroundButton.setGraphic(new ImageView("Icons/push.png"));



        getSousMenuHelp().setOnAction((event)->
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help");
            alert.setContentText("Toolbar :\n" +
                    "Pour le remplissage des formes il faut cliquer sur le pot de peinture afin de\n" +
                    "preciser que l'on veut une forme pleine.\n" +
                    "\n" +
                    "==> pour l'epaisseur des traits, j'ai prefere un slider; il modifie aussi l'epaisseur\n" +
                    "des contours de formes quand elle ne sont pas pleine.\n" +
                    "==> Ellipse --> trace une ellipse : peut changer de couleur, d'epaisseur\n" +
                    "etre deplace, supprime, etre pleine. --> + Ctrl = Cercle.\n" +
                    "==> Rectangle --> trace un rectangle :peut changer de couleur, d'epaisseur\n" +
                    "etre deplace, supprime, etre plein. --> + Ctrl = Carre.\n" +
                    "==> Selection --> cliquer sur la fleche et puis sur une forme pour la deplacer\n" +
                    "la supprimer, mettre en avant, en arriere, au premier et dernier plan.\n" +
                    "==> boutons mettre en avant, en arriere, au premier et dernier plan --> fonctionnent.\n" +
                    "Selectionner la forme au prealable.\n" +
                    "==> Pour supprimer une forme : selectionner la forme et appuyer sur suppr ensuite\n" +
                    "une boite de dialogue demandera une confirmation.\n" +
                    "==> si l'on place la souris sur un bouton un tooltip apparait avec des informations.\n" +
                    "==> bouton poubelle --> efface toutes les formes apres confirmation.");

            Optional<ButtonType> result = alert.showAndWait();
        });
        /**
         * Quitter Application
         */
        getSousMenuQuit().setOnAction((e) ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Voulez-vous vraiment quitter l'application ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                stage.close();
            } else {

            } });
        /**
         * Open file
         */
        getSousMenuOpen().setOnAction((event)->{
            FileChooser openFile = new FileChooser();
            openFile.setTitle("Open File");
            File file = openFile.showOpenDialog(stage);
            if (file != null) {
                try {
                    InputStream io = new FileInputStream(file);
                    Image img = new Image(io);
                    getGc().drawImage(img, 0, 0);
                } catch (IOException ex) {
                    System.out.println("Error!");
                }
            }
        });


        /**
        * Save as
        */
        getSousMenuSaveAs().setOnAction((event)->{

           FileChooser savefile = new FileChooser();
                savefile.setTitle("Save File");
                FileChooser.ExtensionFilter extensionFilter =
                        new FileChooser.ExtensionFilter("png files(*.png)", "*.png");
                savefile.getExtensionFilters().add(extensionFilter);

                File file = savefile.showSaveDialog(stage);
                if (file != null) {
                    try {
                        WritableImage writableImage = new WritableImage(1080, 790);
                        getCanvas().snapshot(null, writableImage);
                        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                        ImageIO.write(renderedImage, "png", file);
                    } catch (IOException ex) {
                        System.out.println("Error!");
                    }
                }



                });


        /**
        * ENREGISTRER LE TRAVAIL SI PAS FAIT AVANT FERMETURE
        */
        stage.setOnCloseRequest((e) ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Avez-vous enregistrer votre travail ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                stage.close();
            } else {

                    FileChooser savefile = new FileChooser();
                    savefile.setTitle("Save File");
                    FileChooser.ExtensionFilter extensionFilter =
                            new FileChooser.ExtensionFilter("png files(*.png)", "*.png");
                    savefile.getExtensionFilters().add(extensionFilter);

                    File file = savefile.showSaveDialog(stage);
                    if (file != null) {
                        try {
                            WritableImage writableImage = new WritableImage(1080, 790);
                            getCanvas().snapshot(null, writableImage);
                            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                            ImageIO.write(renderedImage, "png", file);
                        } catch (IOException ex) {
                            System.out.println("Error!");
                        }
                }
            }
        });


        contour.setStyle("-fx-color-label-visible: false;");
        remplissage.setStyle("-fx-color-label-visible: false;");
        contour.setValue(Color.BLACK);
        remplissage.setValue(Color.BLACK);

        epaisseurTraits.setMin(1);
        epaisseurTraits.setMax(100);
        epaisseurTraits.setShowTickMarks(true);

        selection.setToggleGroup(toggleGroup);
        gomme.setToggleGroup(toggleGroup);
        draw.setToggleGroup(toggleGroup);
        line.setToggleGroup(toggleGroup);
        rectangle.setToggleGroup(toggleGroup);
        ellipse.setToggleGroup(toggleGroup);
        polygon.setToggleGroup(toggleGroup);


        menuFile.getItems().addAll(sousMenuHelp, sousMenuOpen, sousMenuSaveAs, sousMenuQuit);
        menuBar.getMenus().add(menuFile);
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        toolBar.getItems().addAll(contour,remplissage,remplirShapes,new Separator(),selection,gomme,
                epaisseurTraits,draw,line, polygon,rectangle, ellipse,new Separator(),pullButton,pushButton,foregroundButton,
                backgroundButton,delete);


        borderPane.setRight(gridPaneRight);
        borderPane.setLeft(gridPaneLeft);
        borderPane.setBottom(gridPaneBottom);
        borderPane.setTop(gridPaneTop);
        borderPane.setCenter(canvas);
        gridPaneTop.add(menuBar,0,0);
        gridPaneTop.add(toolBar, 0, 1);


        final String FILE_NAME = "model.txt";


            // sérialisation
            try {
                FileOutputStream fs = new FileOutputStream(FILE_NAME);
                ObjectOutputStream os = new ObjectOutputStream(fs);
                os.writeObject(me);
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


            // desérialisation
            try {
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
                me = (Model) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }






        /**redimensionne la zone de dessin en fonction de la taille de la fenêtre
         *
         */
        canvas.heightProperty().bind(stage.heightProperty());
        canvas.widthProperty().bind(stage.widthProperty());


      ChangeListener<Number> stageSizeListener = new ChangeListener<Number>() {
          /** empêche la coupure du dessin lors de la redimenssion de la fenêtre appèle la super méthode ChangeListener**/
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                c.refreshCanvas();
            }
        };


        stage.setMinWidth(500);
        stage.setMinHeight(400);
        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);
        stage.sizeToScene();
        stage.setTitle("Paint Application");
        stage.setScene(scene);
        stage.show();
    }


    public void initGraphicContext(){

        gc.setStroke(contour.getValue());
        gc.setFill(remplissage.getValue());
        gc.setLineWidth(epaisseurTraits.getValue());
        gc.setLineCap(StrokeLineCap.SQUARE);
        gc.setLineJoin(StrokeLineJoin.BEVEL);
    }

    public void initGraphicContext(Shape shape){

        gc.setStroke(shape.getContour());
        gc.setFill(shape.getRemplissage());
        gc.setLineWidth(shape.getEpaisseur());
        gc.setLineCap(StrokeLineCap.SQUARE);
        gc.setLineJoin(StrokeLineJoin.BEVEL);
        gc.setEffect(null);
    }
    

}

