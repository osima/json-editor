package jp.osima.jsoneditor;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import jp.osima.jsoneditor.util.MyConsoleTextEditor;

class MEditorPanel extends JPanel {
	
	private JTabbedPane tab;
	MEditorPanel(){
		
		tab=new JTabbedPane();
		
		setLayout(new BorderLayout());
		add(tab,BorderLayout.CENTER);
	}
	
	MyConsoleTextEditor addEditor(File file){
		
		MTextEditor editor=new MTextEditor(file);
		tab.addTab(file.getName(),editor);
		
		tab.setSelectedComponent(editor);
		
		return editor;
	}
	void closeActiveEditor(){
		tab.remove( getActiveEditor() );
		//revalidate();
	}
	
	
	MyConsoleTextEditor getActiveEditor(){
		return (MyConsoleTextEditor)tab.getSelectedComponent();
	}
	File getActiveFile(){
		return ((MTextEditor)getActiveEditor()).getFile();
	}

	private static class MTextEditor extends MyConsoleTextEditor {
		private File file;
		MTextEditor(File file){
			this.file=file;
		}

		public File getFile() {
			return file;
		}

		/*
		public void setFile(File file) {
			this.file = file;
		}
		*/
	}
	
	
}
