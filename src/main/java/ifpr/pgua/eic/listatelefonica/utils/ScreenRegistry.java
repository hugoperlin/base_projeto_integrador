package ifpr.pgua.eic.listatelefonica.utils;


import java.net.URL;

import javafx.util.Callback;

public class ScreenRegistry {
    
    private final Class classe;
    private final String fxml;
    private final Callback controler;
    
    public ScreenRegistry(Class classe, String fxml, Callback controler) {
        this.classe = classe;
        this.fxml = fxml;
        this.controler = controler;
    }

    public URL getFxmlResource() {
        return classe.getResource(fxml);
    }

    public Callback getControler() {
        return controler;
    }

    


}
