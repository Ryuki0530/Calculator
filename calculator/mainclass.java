package calculator;
import gui.*;


import javax.swing.JFrame ;

public class mainclass  {
    
    private mainclass(){/*インスタンスの生成を禁止。*/}
    
    public static void main(String args[]){

                
      
                JFrame Window = new JFrame("貯金電卓") ;
                Window.setSize(500, 300) ;
                Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                calculatorGUI c = new calculatorGUI();
                c.guiInit();
                Window.add(c);
                Window.setLocationByPlatform( true ) ;
                Window.setVisible( true ) ;
    }
}
