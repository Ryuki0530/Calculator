package gui;
import javax.swing.*;

public abstract class GUIbase extends JPanel{
    //GUIを準備するためのメソッド
    public final void guiInit(){
        GuiInitSub();
        GuiLayout();
        addListeners();
    }
    //Guiの初期化を行うメソッド
    public void GuiInitSub(){}
    //Guiのレイアウトを作成するメソッド
    public abstract void GuiLayout();
    //Guiのリスナーをセットするメソッド
    public abstract void addListeners();
}
