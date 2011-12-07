package jp.osima.jsoneditor;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import jp.osima.jsoneditor.util.MyConsoleTextEditor;
import jp.osima.jsoneditor.util.Utils;

public class MFrame extends JFrame {
	
	private MToolBar toolBar;
	
	private MEditorPanel editorPanel;
	//private MyConsoleTextEditor editor;
	
	public MFrame(){
		super();
		
		editorPanel=new MEditorPanel();
		toolBar=new MToolBar();
		editorPanel.addListener(toolBar);
		toolBar.addListener(new MToolBarListener() {
			
			@Override
			public void save() {
				
				MyConsoleTextEditor edt = editorPanel.getActiveEditor();
				if(edt==null)
					return ;
				
				Utils.setText( editorPanel.getActiveFile(), edt.getText() );

				
			}
			
			@Override
			public void pretty() {
				
				MyConsoleTextEditor edt = editorPanel.getActiveEditor();
				if(edt==null)
					return ;
				
				edt.setText( Utils.pretty( edt.getText() ) );
				edt.select(0,0);
				
			}
			
			@Override
			public void open() {
				
				JFileChooser jfc=new JFileChooser();
				jfc.setCurrentDirectory(getCurrentDir());
				jfc.setFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						return "*.json,*.txt";
					}
					
					@Override
					public boolean accept(File arg0) {

						//if(arg0.isFile() && arg0.getName().endsWith("json") || arg0.isDirectory()){

						if(arg0.isDirectory()){
							return true;
						}

						if(arg0.isFile() ){
							String fileName = arg0.getName();
							String[] suffixArray=new String[]{"json","txt"};
							for(String suffix:suffixArray){
								if( fileName.endsWith(suffix) ){
									return true;
								}
							}
						}

						return false;
					}
				});
				
				int r = jfc.showOpenDialog(MFrame.this);
				if( r==JFileChooser.APPROVE_OPTION){
					File f = jfc.getSelectedFile();
					
					MyConsoleTextEditor edt = editorPanel.addEditor(f);
					edt.setText(Utils.getText(f));
					edt.select(0,0);
				}
			}
			
			@Override
			public void close() {
				editorPanel.closeActiveEditor();
				
			}
		});
		
		//editor=new MyConsoleTextEditor();
		doMyLayout();
	}
	private void doMyLayout(){
		
		setLayout(new BorderLayout());
		add(toolBar,BorderLayout.NORTH);
		add(editorPanel,BorderLayout.CENTER);
		
	}
	
	private File currentDir;
	public File getCurrentDir() {
		if(currentDir==null)
			currentDir=new File(".");
		return currentDir;
	}
	public void setCurrentDir(File currentDir) {
		this.currentDir = currentDir;
	}

	
	static public void main(String[] args){
		MFrame f=new MFrame();
		
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		f.setSize(640,480);
		f.setVisible(true);
	}

}
