/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo1;

/**
 *
 * @author moham
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.input.KeyCode;

/**
 *
 * @author Nadeen Nadim
 */
public class KeyManager {
    
    private boolean [] keystate;
    
    public KeyManager()
    {
        keystate = new boolean[4];
    }
    
    private int turnKeycodeIndx(KeyCode k)
    {
        switch(k)
        {
            case UP:
               return 0;
            case DOWN:
                return 1;
            case S:
                return 2;
            case W:
                return 3;
            default:
                return -1;
                    
        }
    }
    
    
    
    public void setKeyState(KeyCode k, boolean state)
    {
        int i = turnKeycodeIndx(k);
        keystate[i]= state;
    }
    
     public boolean getKeyState(KeyCode k)
    {
        int i = turnKeycodeIndx(k);
         return keystate[i];
    }
    
}
