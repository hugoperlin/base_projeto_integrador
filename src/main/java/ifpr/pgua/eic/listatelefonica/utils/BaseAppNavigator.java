package ifpr.pgua.eic.listatelefonica.utils;


import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Classe responsável por criar uma aplicação que permite a 
 * troca do conteúdo da janela. Deve ser extendida por outra classe.
 * Para cada janela da aplicação deve ser criado um atributo final
 * do tipo Parent ou ScreenRegistry. Exemplo:
 * 
 * public static final ScreenRegistry TELA_PRINCIPAL = new ScreenRegistry("fxml/principal.fxml",o->new Principal());
 * 
 */


public abstract class BaseAppNavigator extends Application{




    private static StackPane root;
    private static Scene cena;
    private Stage palco;

    @Override
    public final void start(Stage primaryStage) throws Exception {

        root = new StackPane();
<<<<<<< HEAD
        
        String homeRegistry = getHome();
=======

        ScreenRegistry homeRegistry = getHomeFXML();
>>>>>>> parent of 8692235 (corrigindo utils)
        if(homeRegistry != null){
            pushScreen(homeRegistry);
        }else{
            Parent home = getHomeParent();
            if(home != null){
                pushScreen(home);
            }else{
                throw new RuntimeException("Um dos métodos getHome deve retornar algo diferente de null!");
            }
        }

        cena = new Scene(root,Region.USE_PREF_SIZE,Region.USE_PREF_SIZE);
        this.palco = primaryStage;

        atualizaEstilo();

        this.palco.setScene(cena);
        this.palco.setTitle(getAppTitle());
        this.palco.show();
    }

    /**
     * Método responsável por trocar todo o conteúdo da janela por uma
     * nova cena. A nova cena será empilhada sobre a cena anterior,
     * e a cena anterior ficará invisível.     * 
     * 
     * @param screen Objeto do tipo ScreenRegistry que indica qual o fxml
     * e seu controlador.
     */

    public final static void pushScreen(ScreenRegistry screen){
        if(root.getChildren().size() > 0){
            root.getChildren().get(root.getChildren().size()-1).setVisible(false);
        }

        Parent tela = loadFxml(screen.getFxmlResource(),screen.getControler());
        root.getChildren().add(tela);
    }

    /**
     * Método responsável por trocar todo o conteúdo da janela por uma
     * nova cena. A nova cena será empilhada sobre a cena anterior,
     * e a cena anterior ficará invisível. Pode ser utilizado como o popScreen. 
     * 
     * @param screen Objeto do tipo Parent que indica qual a classe que representa a tela.
     * 
     */

    public final static void pushScreen(Parent screen){
        if(root.getChildren().size() > 0){
            root.getChildren().get(root.getChildren().size()-1).setVisible(false);
        }

        root.getChildren().add(screen);
    }


        
    /***
     * Método responsável por desempilhar a tela que está no topo e mostrar
     * a tela anterior. Deve ser utilizado em conjunto com o pushScreen.
     */

    public final static void popScreen(){
        if(root.getChildren().size() < 2){
            throw new RuntimeException("NO SCREENS TO POP!!");
        }
        root.getChildren().remove(root.getChildren().size()-1);
        root.getChildren().get(root.getChildren().size()-1).setVisible(true);
    }

    /**
     * Método que permite substituir somente uma região da tela. Importante que
     * para o correto funcionamento, a tela que está sendo mostrada deve ter
     * como elemento root um objeto do tipo BorderPane.
     * 
     * @param screenPiece objeto que indica o fxml e seu controlador que será inseridos em 
     * uma parte da janela.
     * @param regiao localização onde a parte será inserida.
     */

    public final static void changeScreenRegion(ScreenRegistry screenPiece, BorderPaneRegion regiao){
        try{
            BorderPane borderPane = (BorderPane) root.getChildren().get(root.getChildren().size()-1);
            Parent root = loadFxml(screenPiece.getFxmlResource(), screenPiece.getControler());
            
            switch(regiao){
                case LEFT: 
                    borderPane.setLeft(root);
                break;
                case RIGHT: 
                    borderPane.setRight(root);
                break;
                case TOP: 
                    borderPane.setTop(root);
                break;
                case BOTTOM: 
                    borderPane.setBottom(root);
                break;
                case CENTER:
                    borderPane.setCenter(root);
                break;
                
            }
        }catch(Exception e){
            System.out.println("PROBLEMA AO CARREGAR O FXML!!! O root da cena é um BorderPane?");
            e.printStackTrace();
            Platform.exit();
        }
    }

    /**
     * Método que permite carregar um fxml.
     * 
     * @param fxml Localização do arquivo fxml. Geralmente está dentro da pasta resources do projeto.
     * @param controlerMaker Função anônima para criar um controlador da janela.
     * @return
     */
    private static Parent loadFxml(URL fxml, Callback controlerMaker){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fxml);
            loader.setControllerFactory(controlerMaker);

            Parent root = loader.load();

            return root;
        }catch(IOException e){
            System.out.println("PROBLEMA AO CARREGAR O FXML!!! O arquivo "+fxml+" existe?");
            e.printStackTrace();
            Platform.exit();
        }
        return null;
    }



    /**
     * Método responsável por indicar quel é a tela inicial da aplicação.
     * 
     * @return o ScreenRegistry da tela inicial.
     */
    public abstract ScreenRegistry getHomeFXML();

    /**
     * Método responsável por indicar quel é a tela inicial da aplicação, 
     * utilizando uma classe
     * 
     * @return o Parent da tela inicial.
     */
    
    public abstract Parent getHomeParent();

    /**
     * Método que retorna o título da aplicação.
     * 
     * @return
     */
    public abstract String getAppTitle();

<<<<<<< HEAD
    public void registraTela(String nome, ScreenRegistry tela){
        if(nome.isBlank() || nome.isEmpty() || nome == null){
            throw new RuntimeException("[REGISTRA TELA] Nome inválido!");
        }

        if(tela == null){
            throw new RuntimeException("[REGISTRA TELA] Tela inválida!");
        }

        if(registroTelas.containsKey(nome)){
            throw new RuntimeException("[REGISTRA TELA] Tela já registrada!");
        }

        registroTelas.put(nome, tela);

    }

    private static ScreenRegistry pegaTela(String nome){
        if(!registroTelas.containsKey(nome)){
            throw new RuntimeException("[PEGA TELA] Tela não registrada!");
        }

        return registroTelas.get(nome);
    }

    public void adicionarArquivoEstilo(String arquivo){
        root.getStylesheets().add(arquivo);
    }

    public void atualizaEstilo(){}

    

    public abstract void registrarTelas();
=======
>>>>>>> parent of 8692235 (corrigindo utils)

}
