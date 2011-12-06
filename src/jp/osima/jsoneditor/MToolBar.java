package jp.osima.jsoneditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class MToolBar extends JToolBar implements MEditorPanelListener{
	
	private JButton openButton,saveButton,closeButton;
	private JButton prettyButton;
	
	public MToolBar(){
		super();
		
		openButton=new JButton("Open");
		openButton.setIcon(createImageIcon("document-open.png"));
		openButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent arg0) { for(MToolBarListener l:listenerList){ l.open(); } } });
		
		saveButton=new JButton("Save");
		saveButton.setIcon(createImageIcon("document-save-as.png"));
		saveButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent arg0) { for(MToolBarListener l:listenerList){ l.save(); } } });
		
		closeButton=new JButton("Close");
		closeButton.setIcon(createImageIcon("window-close.png"));
		closeButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent arg0) { for(MToolBarListener l:listenerList){ l.close(); } } });
		
		prettyButton=new JButton("pretty");
		prettyButton.setIcon(createImageIcon("format-justify-left.png"));
		prettyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(MToolBarListener l:listenerList){ l.pretty(); }
			}
		});
		
		doMyLayout();
		
		//
		// init
		//
		JButton[] blist = new JButton[]{ saveButton,closeButton,prettyButton};
		for(JButton b:blist){ b.setEnabled(false); }
		
	}
	
	private ImageIcon createImageIcon(String fileName){
		URL url=getClass().getClassLoader().getResource(fileName);
		ImageIcon icon=new ImageIcon(url);
		return icon;
	}
	
	
	private void doMyLayout(){
		
		add(openButton);
		add(saveButton);
		add(closeButton);
		addSeparator();
		add(prettyButton);
		
	}
	
	@Override
	public void stateChanged(MEditorPanel src) {
		
		JButton[] blist = new JButton[]{ saveButton,closeButton,prettyButton};
		
		if( src.getTabCount()>0 )
			for(JButton b:blist){ b.setEnabled(true); }
		else
			for(JButton b:blist){ b.setEnabled(false); }
		
	}
	
	private List<MToolBarListener> listenerList=new ArrayList<MToolBarListener>();
	public void addListener(MToolBarListener l){
		listenerList.add(l);
	}
	
	

}
