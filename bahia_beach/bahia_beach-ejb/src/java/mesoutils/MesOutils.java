/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesoutils;

import java.util.Random;

/**
 *
 * @author cdi415
 */
public class MesOutils {

    public MesOutils() {
    }
    public String gerenrerCommandeCode(){
        Random rand = new Random();
        String s="CMD";
        s+=rand.nextInt(999999999);
        return s;
    }
     public String genererCode(int length){

	    String chars = "1234567890"; 
	    String code = "";
	    for(int x=0;x<length;x++)
	    {
	       int i = (int)Math.floor(Math.random() * 10); 
	       code += chars.charAt(i);
	    }
	    return code;
    }

}
