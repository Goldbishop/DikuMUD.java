/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mud.diku.objects;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author goldbishop
 */
public enum ESex {
    UNKNOWN (-1),
    ASEXUAL (0),
    MALE (1),
    FEMALE (2);
    
    private ESex(int value){
        this.value = value;
    }
    
    private static final Map<Integer, ESex> MapLabel = new HashMap<>();
    public final int value;
    
    static {
        for(ESex e: values()){
            MapLabel.put(e.value, e);
        }
    }
    
    public static ESex getByValue(int value){
        return MapLabel.get(value);
    }
}
