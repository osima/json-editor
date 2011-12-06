package jp.osima.jsoneditor.util

import groovy.ui.*

import java.awt.*
import javax.swing.*


public class MyConsoleTextEditor extends JPanel {

	private def edt
	public ConsoleTextEditor getConsoleTextEditor(){ return edt }

	def MyConsoleTextEditor(){
		edt = new ConsoleTextEditor()
		doMyLayout();
	}
	private void doMyLayout(){
		setLayout(new BorderLayout())
		add( edt,BorderLayout.CENTER )
	}
	void setText(String text){
		edt.textEditor.text = text
	}
	String getText(){
		edt.textEditor.text
	}
}

