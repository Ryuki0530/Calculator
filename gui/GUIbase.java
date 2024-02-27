package gui;

public abstract class GUIbase {
    //GUIを準備するためのメソッド
    public final void guiSet(){
        GuiInit()
        GuiLayout()
        GuiListener()
    }
    //Guiの初期化を行うメソッド
    GuiInit();
    //Guiのレイアウトを作成するメソッド
    GuiLayout();
    //Guiのリスナーをセットするメソッド
    GuiListener();
}
