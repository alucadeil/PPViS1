import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

public class SGroup4 {
	public SGroup4(Shell shell) {
		Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
		group.setText("Fourth Group");
		group.setLocation(584, 10);
		
		Text text = new Text(group, SWT.NONE);
		text.setText("");
		text.setLocation(32, 30);
		text.pack();
		
		Button chkButton = new Button(group, SWT.PUSH);
		chkButton.setText("PUSH ME");
		chkButton.setLocation(32, 60);
		chkButton.pack();
		
		Button checkBox1 = new Button(group, SWT.CHECK);
		checkBox1.setText("Check one");
		checkBox1.setLocation(32, 95);
		checkBox1.pack();
		
		Button checkBox2 = new Button(group, SWT.CHECK);
		checkBox2.setText("Check two");
		checkBox2.setLocation(32, 115);
		checkBox2.pack();
		
		Button checkBox3 = new Button(group, SWT.CHECK);
		checkBox3.setText("Check three");
		checkBox3.setLocation(32, 135);
		checkBox3.pack();
		
		check(text, chkButton, checkBox1, checkBox2, checkBox3, shell);
		
		group.setSize(128, 160);
		
	}
	
	private void check (Text text, Button push, Button checkBox1, Button checkBox2, Button checkBox3, Shell shell) {
		
		push.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String txt = text.getText();
				if(txt.equals(null)) {
					return;
				}
				if((!txt.equals(checkBox1)) && (!txt.equals(checkBox1)) && (!txt.equals(checkBox1))) {
					message(shell);
				}
				
				if(txt.equals(checkBox1.getText())) {
					checkBox1.setSelection(true);
				} else if(txt.equals(checkBox2.getText())) {
					checkBox2.setSelection(true);
				} else if(txt.equals(checkBox3.getText())) {
					checkBox3.setSelection(true);
				}
					
			}
			});
		
	}
	
	private void message(Shell shell) {
		MessageBox message = new MessageBox(shell);
		message.setText("ERROR");
		message.setMessage("ббедхре ясыеярбсчыее хлъ");
		message.open();
		return;
	}
}
