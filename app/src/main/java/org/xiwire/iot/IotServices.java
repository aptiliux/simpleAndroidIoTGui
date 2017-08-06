package org.xiwire.iot;

/**
 * Created by aptiliux on 14/04/17.
 */

public class IotServices {
    private final String host;

    public IotServices(String host){
        this.host=host;
    }

    public boolean isLampOn(){
        return true;
    }
}
