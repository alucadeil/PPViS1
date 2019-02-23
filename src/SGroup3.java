import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;

public class SGroup3 {
	
	public SGroup3(Shell shell) {
		
		Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
		group.setText("Third Group");
		
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		group.setLayout(rowLayout);
		rowLayout.marginTop = 10;
		rowLayout.marginLeft = 32;
		rowLayout.marginRight = 32;
		rowLayout.marginBottom = 10;
		rowLayout.spacing = 10;
		
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
		radio1.setLocation(32, 95);
		radio1.pack();
		
		Button radio2 = new Button(group, SWT.RADIO);
		radio2.setText("Second");
		radio2.setLocation(32, 115);
		radio2.pack();
		
		Button radio3 = new Button(group, SWT.RADIO);
		radio3.setText("Third");
		radio3.setLocation(32, 135);
		radio3.pack();
		
		check(text, chkButton, radio1, radio2, radio3);
		
			
	}
	
	private void check(Text text, Button push, Button radio1, Button radio2, Button radio3) {	
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
