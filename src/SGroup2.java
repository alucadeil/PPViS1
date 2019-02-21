import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;


public class SGroup2 {
		public SGroup2 (Shell shell) {
			
			Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
			
			group.setLocation(228, 10);
			group.setText("Second Group");
			
			Text text = new Text(group, SWT.NONE);
			text.setText("Write here");
			text.setLocation(32, 32);
			text.pack();

			Button button1 = new Button(group, SWT.PUSH);
			button1.setLocation(32, 64);
			button1.setText("Text one");
			button1.pack();
			
			Button button2 = new Button(group, SWT.PUSH);
			button2.setLocation(32, 96);
			button2.setText("Text");
			button2.pack();
		
			button1.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					String txt = text.getText();
					if(txt == null) {
						return;
					}
					button2.setText(txt);
					button2.pack();
				}
				});
			
			button2.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					String txt = button1.getText();
					if(txt == null) {
						return;
					}
					button1.setText(button2.getText());
					button1.pack();
					button2.setText(txt);
					button2.pack();
				}
				});
			group.setSize(128, 128);
		
		}
		
}
