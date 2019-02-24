import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

public class SGroup5 {
	public SGroup5(Shell shell) {
		
		Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
		group.setText("Fifth group");
		
		GridLayout rowLayout = new GridLayout();
		rowLayout.numColumns = 4;
		group.setLayout(rowLayout);
		
		Text text = new Text(group, SWT.NONE);
		text.setText("Write here");
		text.setLocation(32, 30);
		text.pack();

		Button button1 = new Button(group, SWT.PUSH);
		button1.setText("Push 1");
		
		Button button2 = new Button(group, SWT.PUSH);
		button2.setText("Push 2");
	
		Button button3 = new Button(group, SWT.PUSH);
		button3.setText("Push 3");	

		Table table = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tc1 = new TableColumn(table, SWT.CENTER);
		tc1.setText("First");
		tc1.setWidth(80);
	    
	    TableColumn tc2 = new TableColumn(table, SWT.CENTER);
	    tc2.setText("Second");
	    tc2.setWidth(80);
	    
	    FirstButton(button1, text, table, group, shell);
	    
	    SecondButton(button2, table, text);
	    
	    ThirdButton(button3, table, text);
	    
	    group.pack();
	}
	
	private void FirstButton (Button but1, Text text, Table table, Group group, Shell shell) {
		
		but1.addSelectionListener(new SelectionAdapter() {
	    	
	    	@Override
	    	public void widgetSelected(SelectionEvent arg0) {
	    		String txt = text.getText();
	    		TableItem item = new TableItem(table, SWT.NONE);
	    		item.setText(txt);
	    		group.pack();
	    		shell.pack();
	    	}
	    });
	}
	
	private void SecondButton (Button but2, Table table, Text text) {
		
		but2.addSelectionListener(new SelectionAdapter() {
	 
	    	@Override
	    	public void widgetSelected(SelectionEvent arg0) {
	    		int index = table.getSelectionIndex();
	    		if(index == -1) {
	    			return;
	    		}
	    		TableItem item = table.getItem(index);
	    		String txt = item.getText();
		    	if(txt == "") {
		    		return;
		    	} 
	    		table.clear(index);
	    		item.setText(1, txt);
	    	}
	    });
	}
	    
	private void ThirdButton (Button but3, Table table, Text text) {
			
		but3.addSelectionListener(new SelectionAdapter() {
		 
		   	@Override
		   	public void widgetSelected(SelectionEvent arg0) {
		   		int index = table.getSelectionIndex();
		   		if(index == -1) {
		   			return;
		   		}
		    	TableItem item = table.getItem(index);
		    	String txt = item.getText(1);
		    	if(txt == "") {
		    		return;
		    	} 
		    	table.clear(index);
		    	item.setText(0, txt);
		    	}
		    });
	}
}
