package resources;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Toolkit;

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

public class visual extends JFrame {
  public String path="";
  public String called="";
  JPanel contentPane;
  JLabel name = new JLabel();
  JButton buttonOpen = new JButton();
  JButton buttonProcess = new JButton();
  JTextField textField = new JTextField();
  JTextField textField2 = new JTextField();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JComboBox comboBox = new JComboBox();
  JFileChooser jFileChooser1 = new JFileChooser();
  JLabel wait = new JLabel();

  //Construct the frame
  public visual() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    name.setIcon(new ImageIcon(new java.net.URL("file:resources/images/logo.jpg")));
    name.setText("");
    name.setBounds(new Rectangle(60, 10, 120, 120));
    contentPane.setLayout(null);
    //this.getContentPane().setBackground(Color.black);
    this.setResizable(false);
    this.setSize(new Dimension(477, 297));
    this.setTitle("One-Time Image");
    buttonOpen.setBackground(Color.black);
    buttonOpen.setBounds(new Rectangle(230, 6, 231, 124));
    buttonOpen.setToolTipText("click to load a picture");
    buttonOpen.setBorderPainted(false);
    buttonOpen.setText("Open");
    //buttonOpen.setIcon(new ImageIcon(new java.net.URL("file:resources/images/open1.jpg")));
    buttonOpen.setRolloverEnabled(true);
    buttonOpen.setRolloverIcon(new ImageIcon(new java.net.URL("file:resources/images/open2.jpg")));
    buttonOpen.addMouseListener(new visual_buttonOpen_mouseAdapter(this));
    buttonProcess.setBackground(Color.black);
    buttonProcess.setBounds(new Rectangle(230, 135, 229, 124));
    buttonProcess.setToolTipText("Click to create a one-time image pair");
    buttonProcess.setBorderPainted(false);
    buttonProcess.setText("Process");
    //buttonProcess.setIcon(new ImageIcon(new java.net.URL("file:resources/images/process1.jpg")));
    buttonProcess.setRolloverIcon(new ImageIcon(new java.net.URL("file:resources/images/process2.jpg")));
    buttonProcess.addMouseListener(new visual_buttonProcess_mouseAdapter(this));
    textField.setSelectionStart(0);
    textField.setText("");
    textField.setBounds(new Rectangle(6, 221, 128, 34));
    textField2.setText("5");
    textField2.setBounds(new Rectangle(6, 160, 128, 34));
    //jLabel1.setForeground(Color.white);
    jLabel1.setText("Save One-Time Image pair as");
    jLabel1.setBounds(new Rectangle(45, 198, 145, 20));
    //jLabel2.setForeground(Color.white);
    jLabel2.setText("Pixel Size");
    jLabel2.setBounds(new Rectangle(45, 138, 145, 20));
    comboBox.setBounds(new Rectangle(149, 222, 74, 34));
    //contentPane.setBackground(Color.black);
    //wait.setForeground(Color.white);
    wait.setBounds(new Rectangle(6, 180, 162, 18));
    contentPane.add(name, null);
    contentPane.add(textField2,null);
    contentPane.add(textField, null);
    contentPane.add(comboBox, null);
    contentPane.add(jLabel1, null);
    contentPane.add(jLabel2, null);
    contentPane.add(buttonOpen, null);
    contentPane.add(buttonProcess, null);
    contentPane.add(wait, null);

    comboBox.insertItemAt(".png",0);
    comboBox.insertItemAt(".gif",1);
    comboBox.setSelectedIndex(0);

  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  void buttonOpen_mouseClicked(MouseEvent e) {

    //open file
    if (JFileChooser.APPROVE_OPTION == jFileChooser1.showOpenDialog(this)) {

      path = jFileChooser1.getSelectedFile().getPath();
      called = control.getName(path);
      textField.setText(called);
    }

  }

  void buttonProcess_mouseClicked(MouseEvent e) {
    //process images
    wait.setText("Processing - Please Wait");
    if(!jLabel2.getText().equals("")){
        control.pixelsize = textField2.getText();
    }
    boolean success = control.runProg(path, called, comboBox.getSelectedIndex());
    if (success==true ) {wait.setText("");Toolkit.getDefaultToolkit().beep();}
    else wait.setText("Processing Failed");
  }
}

class visual_buttonOpen_mouseAdapter extends java.awt.event.MouseAdapter {
  visual adaptee;

  visual_buttonOpen_mouseAdapter(visual adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.buttonOpen_mouseClicked(e);
  }
}

class visual_buttonProcess_mouseAdapter extends java.awt.event.MouseAdapter {
  visual adaptee;

  visual_buttonProcess_mouseAdapter(visual adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.buttonProcess_mouseClicked(e);
  }
}