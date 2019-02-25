import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.RowLayout;

public class SGroup4 {
	public SGroup4(Shell shell) {
		
		Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
		group.setText("Fourth Group");
		
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		group.setLayout(rowLayout);
		rowLayout.marginTop = 10;
		rowLayout.marginLeft = 32;
		rowLayout.marginRight = 32;
		rowLayout.marginBottom = 10;
		rowLayout.spacing = 15;
		
		Text text = new Text(group, SWT.NONE);
		text.setText("");
		text.setLocation(32, 30);
		text.pack();
		
		Button chkButton = new Button(group, SWT.PUSH);
		chkButton.setText("PUSH ME");
		chkButton.setLocation(32, 60);
		chkButton.pack();
		
		Button checkBox1 = new Button(group, SWT.CHECK);
		checkBox1.setText("One");
		checkBox1.setLocation(32, 95);
		checkBox1.pack();
		
		Button checkBox2 = new Button(group, SWT.CHECK);
		checkBox2.setText("Two");
		checkBox2.setLocation(32, 115);
		checkBox2.pack();
		
		Button checkBox3 = new Button(group, SWT.CHECK);
		checkBox3.setText("Three");
		checkBox3.setLocation(32, 135);
		checkBox3.pack();
		
		check(text, chkButton, checkBox1, checkBox2, checkBox3, shell);
		
		
	}
	
	private void check (Text text, Button push, Button checkBox1, Button checkBox2, Button checkBox3, Shell shell) {
		
		push.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String txt = text.getText();
				
				String[] subStr;
				String delim = " ";
				if(txt == null) {
					return;
				}
				subStr = txt.split(delim);
				for(int i = 0; i < subStr.length;i++) {
					if(!subStr[i].equals(checkBox1.getText()) 
							&& !subStr[i].equals(checkBox2.getText())
							&& !subStr[i].equals(checkBox3.getText())) {
						message(shell, subStr[i]);
					} else if(subStr[i].equals(checkBox1.getText())) {
						checkBox1.setSelection(true);
					} else if(subStr[i].equals(checkBox2.getText())) {
						checkBox2.setSelection(true);
					} else if(subStr[i].equals(checkBox3.getText())) {
						checkBox3.setSelection(true);
					}
				}
			}
			});
	}
	
	private void message(Shell shell, String string) {
		MessageBox message = new MessageBox(shell);
		message.setText("ERROR");
		message.setMessage(string + ": ¬ведите существующее название");
		message.open();
		return;
	}
}
