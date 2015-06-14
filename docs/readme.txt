*** ONE-TIME IMAGE ***

Created by Robert Hansen
www.r-hansen.com

1) Overview
2) Instructions
3) How does it work?
4) Uses
5) Output Formats
6) Source
7) Increasing Security
8) Terms of Use


1) Overview

One-Time Image is a program written in Java to encrypt images using the principle of the one-time pad to create a pair of black and white images. Each appears as a 'snow' of black and white pixels, and no information can be extracted from either image on its own - unlike most cryptography, it is not merely 'very difficult' to extract information: an implementation of the one-time image principle with true random numbers is *perfectly* secure and unbreakable (see the section entitled 'Increasing Security' for why this is not true of this program, and how to make it so).

However, despite the fact that no information can be retrieved from either image on its own, if both are printed onto transparencies and one is laid directly over the other, the original image will immediately and clearly appear.


2) Instructions

First, click on 'Open', and select the image you wish to encrypt. The program can load BMP, GIF, PNG or JPEG files. Then select the filename you wish to be used for the image pair (the output filename will be filename_1.ext and filename_2.ext, where filename is the name you enter and .ext is the extension of the filetype chosen). Choose whether to save them as .png or .gif files, and then click 'Process' to create and save the image pair.

Note that the final images will be in black and white, and each will have dimensions twice those of the source image. They will be saved into the directory from which you run the program.


3) How does it work?

The principle of the one-time pad was developed during World War One, though it was 25 years before a mathematician proved it was perfectly secure, as opposed to merely prohibitously difficult to break. It is a very simple substitution cypher (a type of cypher where A becomes C, B becomes D etc, for example), but with the twist that the key is the same length as the message. As a result each letter has its own unique (and random) rotation, making the encoded message proof against any sort of analysis without the key. The final result is a pair of random-looking message (one the encrypted message, one the key used to encrypt it) of the same length, either of which cannot be broken or analysed in any way on its own, but which can easily be decoded once the two are brought together.

One-Time Image extends the principle to that of images. First, the source image is converted to black and white (not greyscale, true black and white with pixels of only these two different colours). 

Secondly a key image is generated, of the same dimensions, where each pixel is randomly set to white or black. Third, the original image is encrypted using this key - if the pixel in the key is white then the corresponding pixel in the original image is used in the encrypted image, whereas if the key pixel is black then the corresponding pixel in the original image is flipped (black to white, white to black) for the encrypted image. The result is two images of apparently random black and white pixels.

Finally, each image is then doubled in size - each pixel becomes a 2x2 square of pixels. Black pixels have black pixels in the top-left and bottom-right corners while the other two pixels are while, while a white pixel in the original image produces the opposite 2x2 square. These enlarged images are the final ones.

The trick is that, when printed onto transparencies and one is laid over the other (the order is irrelevant), the original image is suddenly revealed! This is because a black pixel in the original image produced pixels of different colour in the key and encrypted images (one black, one white). Since these black and white pixels became 2x2 squares with two black and two white pixels, when overlaid all four pixels in the square become black. However, a white pixel in the original produced pixels of matching colour in the key and encrypted images (both black, or both white). Hence the 2x2 squares in the final images are identical, and when overlaid halfd the pixels remained white. Hence, when you look at the image from anything but very short range, these 2x2 squares look grey while other look black.

If you don't have a printer and transparencies to hand, you can demonstrate it for yourself using Photoshop or some other image-manipulation program - take one of the output images and import/paste the other on top of it as a new layer and you'll see your original image.


4) Uses

In the real world? None really - in practise it makes a lot more sense to use the same principles to produce a pair of USB keys rather than messing around with transparent sheets of printed static. And even then the one-time pad is mostly a curiosity: its lack of authentication, need to transport the message securely, need to dispose of the keys securely after use and other problems means its usage is limited.

It is, however, a useful tool for demonstrating the principle behind the one-time pad, though, since the decoding is automatic, instantaneous and very visible. The fact that transparencies are involved also makes it perfectly suited for use with overhead projectors if being formally taught.


5) Output Formats

One-Time Image can output the image pairs in .png or .gif format. Both are very suited to this as they cope well with limited colours and compress images losslessly. JPEG was not used because its lossy compression ruins the accurate positioning of the pixels that is so important.


6) Source

The program was written in Java to make use of its built-in image handling capabilities; I make no claims to the beauty of the code (I'm primarily a C/C++ developer), but believe it gets the job done. A makefile is provided to compile the .java files into .class files. There are few comments, but it's a relatively small, simple program, and I've tried to include them when I do anything non-obvious.


7) Increasing Security

While a one-time image pair generated using random numbers is secure, it should be noted that image pairs generated by this program in its current state are *not* perfectly secure. This is because, as a proof of concept and a fun program rather than a serious cryptographic tool, the program uses Java's built-in random number generator, which generates pseudorandom numbers using the computer's internal clock for a seed. As a result, a determined decoder could retrieve information from one of the images in isolation.

If you wish to improve the security of the program, you can provide an alternate source of random numbers by editing createRandom function in the alter.java file. A bolded comment is included to direct you to precisely where you should replace the default line.

Note that image_1 is the key, while image_2 is the encoded image. Hence if being used without a source of true random numbers, security should focus on protecting image_2, since the original image can be recovered from it given a competant analyst and a relatively trivial amount of computation, while image_1 on its own will only provide the exact procedural random numbers.


8) Terms of Use

Copyright (c) 2007 Robert Hansen, www.r-hansen.com

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
