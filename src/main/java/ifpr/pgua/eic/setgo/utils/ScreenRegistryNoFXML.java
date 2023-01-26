package ifpr.pgua.eic.setgo.utils;


import java.net.URL;

import javafx.scene.Parent;
import javafx.util.Callback;

public class ScreenRegistryNoFXML implements ScreenRegistry{
    
    private Callback construtor;
    
    public ScreenRegistryNoFXML(Callback construtor) {
        this.construtor = construtor;
    }

    public Parent getRoot(){
        return (Parent) construtor.call(null);
    }

    


}
