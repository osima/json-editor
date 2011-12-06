package jp.osima.jsoneditor;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	
	private List<MEditorPanelListener> listenerList=new ArrayList<MEditorPanelListener>();
	void addListener(MEditorPanelListener l){
		listenerList.add(l);
	}
	
	MyConsoleTextEditor addEditor(File file){
		
		MTextEditor editor=new MTextEditor(file);
		tab.addTab(file.getName(),editor);
		
		tab.setSelectedComponent(editor);
		
		for(MEditorPanelListener l:listenerList){ l.stateChanged(this); }
		
		return editor;
	}
	void closeActiveEditor(){
		tab.remove( getActiveEditor() );
		//revalidate();
		
		for(MEditorPanelListener l:listenerList){ l.stateChanged(this); }
	}
	
	int getTabCount(){
		return tab.getTabCount();
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
	}
	
	
}
