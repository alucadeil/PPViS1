import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SGroup3 {
	
	public SGroup3(Shell shell) {
		
		Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
		group.setText("Third Group");
		group.setToolTipText("Третье задание");
		
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		group.setLayout(rowLayout);
		rowLayout.marginTop = 10;
		rowLayout.marginLeft = 32;
		rowLayout.marginRight = 32;
		rowLayout.marginBottom = 10;
		rowLayout.spacing = 10;
		
		Text textLine = new Text(group, SWT.NONE);
		textLine.setText("");
		textLine.setLocation(32, 32);
		textLine.pack();
		
		RhombButton buttonCheckSelected = new RhombButton(group, SWT.PUSH);
		buttonCheckSelected.setText("PUSH ME");
		buttonCheckSelected.setLocation(32, 60);
		buttonCheckSelected.pack();
		
		Button firstRadioButton = new Button(group, SWT.RADIO);
		firstRadioButton.setText("First");
		firstRadioButton.setLocation(32, 95);
		firstRadioButton.pack();
		
		Button secondRadioButton = new Button(group, SWT.RADIO);
		secondRadioButton.setText("Second");
		secondRadioButton.setLocation(32, 115);
		secondRadioButton.pack();
		
		Button thirdRadioButton = new Button(group, SWT.RADIO);
		thirdRadioButton.setText("Third");
		thirdRadioButton.setLocation(32, 135);
		thirdRadioButton.pack();
		
		checkSelected(textLine, buttonCheckSelected, firstRadioButton, secondRadioButton, thirdRadioButton);
		
			
	}
	
	private void checkSelected(Text text, RhombButton push, Button radio1, Button radio2, Button radio3) {	
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
