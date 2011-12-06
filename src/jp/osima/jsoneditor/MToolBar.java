package jp.osima.jsoneditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class MToolBar extends JToolBar{
	
	private JButton openButton,saveButton,closeButton;
	private JButton prettyButton;
	
	public MToolBar(){
		super();
		
		openButton=new JButton("Open");
		openButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent arg0) { for(MToolBarListener l:listenerList){ l.open(); } } });
		saveButton=new JButton("Save");
		saveButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent arg0) { for(MToolBarListener l:listenerList){ l.save(); } } });
		closeButton=new JButton("Close");
		closeButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent arg0) { for(MToolBarListener l:listenerList){ l.close(); } } });
		
		prettyButton=new JButton("pretty");
		prettyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(MToolBarListener l:listenerList){ l.pretty(); }
			}
		});
		
		doMyLayout();
	}
	private void doMyLayout(){
		
		add(openButton);
		add(saveButton);
		add(closeButton);
		addSeparator();
		add(prettyButton);
		
	}
	
	private List<MToolBarListener> listenerList=new ArrayList<MToolBarListener>();
	public void addListener(MToolBarListener l){
		listenerList.add(l);
	}
	
	

}
