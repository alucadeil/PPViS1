import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

public class SGroup3 {
	
	public SGroup3(Shell shell) {
		
		Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
		
		group.setLocation(406, 10);
		group.setText("Third Group");
		
		Text text = new Text(group, SWT.NONE);
		text.setText("");
		text.setLocation(32, 32);
		text.pack();
		
		Button chkButton = new Button(group, SWT.PUSH);
		chkButton.setText("PUSH ME");
		chkButton.setLocation(32, 60);
		chkButton.pack();
		
		Button radio1 = new Button(group, SWT.RADIO);
		radio1.setText("First");
		radio1.setLocation(32, 85);
		radio1.pack();
		
		Button radio2 = new Button(group, SWT.RADIO);
		radio2.setText("Second");
		radio2.setLocation(32, 105);
		radio2.pack();
		
		Button radio3 = new Button(group, SWT.RADIO);
		radio3.setText("Third");
		radio3.setLocation(32, 125);
		radio3.pack();
		
		check(text, chkButton, radio1, radio2, radio3);
		
		group.setSize(128, 160);
			
	}
	
	private void check(Text text, Button push, Button radio1, Button radio2, Button radio3)
	{	
		push.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String txt = text.getText();
				if(txt.equals(null)) {
					return;
				}
				if(txt.equals(radio1.getText())) {
					radio1.setSelection(true);
					radio2.setSelection(false);
					radio3.setSelection(false);
				} else if(txt.equals(radio2.getText())) {
					radio1.setSelection(false);
					radio2.setSelection(true);
					radio3.setSelection(false);
				} else if(txt.equals(radio3.getText())) {
					radio1.setSelection(false);
					radio2.setSelection(false);
					radio3.setSelection(true);
				}
					
			}
			});
		
	}

}
