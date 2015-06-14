package resources;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

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

public class inout {
//no class constructors needed

  static BufferedImage loadImage(String filename){
    BufferedImage in;
    try {
      File filein = new File(filename);
      in = ImageIO.read(filein);
    }
    catch (Exception ex) {
      in = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }
    return in;
  }

  static boolean validateImage(BufferedImage pic){
    boolean valid;
    int x=pic.getWidth();
    if (x>0) {
      x=pic.getHeight();
      if (x>0) valid=true;
        else valid=false;
    }
    else valid=false;
    return valid;
  }

  static boolean saveImage(BufferedImage pic, String filename, int format) {
    boolean valid=false;
    String type="png";
    if (format==1) type="gif";
    filename.trim();
    filename=filename+"."+type;
    try {
      File fileout = new File(filename);
      ImageIO.write(pic, type, fileout);
      valid=true;
    }
    catch (Exception ex) {
      valid = false;
    }
    return valid;

  }

}