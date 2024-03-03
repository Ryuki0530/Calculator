

import java.util.Date;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

/** Java と JavaScript の連携 */
public class JavaScriTest {

	public static void main(String[] args) throws Exception {
		System.out.println("java.version: " + System.getProperty("java.version"));

        // 対応している ScriptEngine の情報を出力
		ScriptEngineManager manager = new ScriptEngineManager();
		List<ScriptEngineFactory> engineFactories = manager.getEngineFactories();
		for (ScriptEngineFactory factory : engineFactories) {
			System.out.println("Engine: " + factory.getEngineName()
					+ ", Version: " + factory.getEngineVersion());
			System.out.println("Language: " + factory.getLanguageName()
					+ ", Version: " + factory.getLanguageVersion());
			System.out.println("Extensions: " + factory.getExtensions());
			System.out.println("MimeTypes: " + factory.getMimeTypes());
			System.out.println("Names: " + factory.getNames());
			System.out.println();
		}
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		// JavaScript の実行
		engine.eval("var message = 'Hello, I am JavaScript.'");
		engine.eval("print('#1: ' + message)");

		// Java のオブジェクトを JavaScript に渡してみる
		engine.put("message2", "Hello, I am Java8.");
		engine.put("today", new Date());
		engine.eval("print('#2: ' + message2 + ' now : ' + today.toString())");

		// JavaScript から Java のメソッドを使用
		engine.eval("var obj = Java.type('test.javascript.JavaScriptTest');"
				+ "print('#3: ' + obj.getMessage());");
		engine.eval("obj.printMessage('Good afternoon.');");

		// Java から JavaScript の値を取得
		engine.eval("var message3 = 'Good evening.';");
		Object message3 = engine.get("message3");
		System.out.println("#5: " + message3);

		// Java から JavaScript の関数を呼び出し
		if (engine instanceof Invocable) {
			Invocable invocable = (Invocable) engine;
			engine.eval("var func = function(arg) { print('#6: ' + arg);}");
			invocable.invokeFunction("func", "Good night.");
		}
	}

	/** メッセージを返す */
	public static String getMessage() {
		return "Good morning.";
	}

	/** メッセージを出力 */
	public static void printMessage(String message) {
		System.out.println("#4: " + message);
	}
}
