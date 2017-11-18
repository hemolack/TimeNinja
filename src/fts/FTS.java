/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fts;

import java.awt.KeyEventDispatcher;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author grendel
 */
public class FTS extends JFrame {
	KeyEventDispatcher keyListener;
	
	public FTS() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() 
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setOpacity(0.0f);
        setSize(200, 200);
        //System.setProperty("sun.java2d.noddraw", "true");
        //WindowUtils.setWindowTransparent(this, true);
        //WindowUtils.setWindowAlpha(this, 0.6f);
        //setVisible(true);
        setVisible(false);
		Runnable r = new RT();
		Thread t = new Thread(r);
		t.start();
    }

    public static void main(String[] args) 
    {
        FTS fts = new FTS();
    }
	
}
