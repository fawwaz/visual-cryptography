package resources;

import java.awt.image.*;

/*========================================================================

Copyright (c) 2007 Robert Hansen, www.r-hansen.com

This file is part of One-Time Image.

One-Time Image is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

One-Time Image is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with One-Time Image; if not, write to the Free Software
Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

========================================================================*/

public class control {
   static String pixelsize=""; 
    
   static boolean runProg(String load, String name, int type) {

     boolean valid = false;

     BufferedImage input;

     input = inout.loadImage(load);
     valid = inout.validateImage(input);

     if (valid==true) {
       input = alter.monochrome(input);
       
       if(pixelsize.equals("")){ // kalau dibiarin kosong anggap aja 1 pixel
            BufferedImage pad1 = alter.createRandom(input);
            BufferedImage pad2 = alter.meldImages(input, pad1);
            BufferedImage pad3 = alter.decrypted(pad1, pad2);
            pixelsize = "1";
            System.out.println("[START] Writing Image to disk");
            valid = inout.saveImage(pad1, name+"_"+pixelsize+"pixel"+"_1", type);
            valid = inout.saveImage(pad2, name+"_"+pixelsize+"pixel"+"_2", type);
            valid = inout.saveImage(pad3, name+"_"+pixelsize+"pixel"+"_3", type);
            System.out.println("[DONE] Writing Image to disk");
       }else{
           BufferedImage pad1 = alter.createRandom(input,pixelsize);
           BufferedImage pad2 = alter.meldImages(input, pad1, pixelsize);
           BufferedImage pad3 = alter.decrypted(pad1, pad2);
           System.out.println("[START] Writing Image to disk");
           valid = inout.saveImage(pad1, name+"_"+pixelsize+"pixel"+"_1", type);
           valid = inout.saveImage(pad2, name+"_"+pixelsize+"pixel"+"_2", type);
           valid = inout.saveImage(pad3, name+"_"+pixelsize+"pixel"+"_3", type);
           System.out.println("[DONE] Writing Image to disk");
           //alter.makeGrey(pad3);
       }
     }

     return valid;
   }

   static String getName(String path) {
     String named = "";
     String name = "";
     int i,j=0;

     String[] pathall = path.split("\\\\");

     named=pathall[pathall.length-1];

     String[] nameall = named.split("");

     for (i=0;i<nameall.length;i++) if (nameall[i].equals(".")) j=i;

     for (i=0;i<j;i++) name = name + nameall[i];

     return name;
   }




}