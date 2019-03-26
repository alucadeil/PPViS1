import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SGroup4 {
	public SGroup4(Shell shell) {
		
		Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
		group.setText("Fourth Group");
		group.setToolTipText("Четвертое задание");
		
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		group.setLayout(rowLayout);
		rowLayout.marginTop = 10;
		rowLayout.marginLeft = 32;
		rowLayout.marginRight = 32;
		rowLayout.marginBottom = 10;
		rowLayout.spacing = 15;
		
		Text textLine = new Text(group, SWT.NONE);
		textLine.setText("");
		textLine.setLocation(32, 30);
		textLine.pack();
		
		RhombButton checkButton = new RhombButton(group, SWT.PUSH);
		checkButton.setText("PUSH ME");
		checkButton.setLocation(32, 60);
		checkButton.pack();
		
		Button firstCheckBox = new Button(group, SWT.CHECK);
		firstCheckBox.setText("One");
		firstCheckBox.setLocation(32, 95);
		firstCheckBox.pack();
		
		Button secondCheckBox = new Button(group, SWT.CHECK);
		secondCheckBox.setText("Two");
		secondCheckBox.setLocation(32, 115);
		secondCheckBox.pack();
		
		Button thirdCheckBox = new Button(group, SWT.CHECK);
		thirdCheckBox.setText("Three");
		thirdCheckBox.setLocation(32, 135);
		thirdCheckBox.pack();
		
		checkTextLine(textLine, checkButton, firstCheckBox, secondCheckBox, thirdCheckBox, shell);
		
		
	}
	
	private void checkTextLine (Text text, RhombButton push, Button checkBox1, Button checkBox2, Button checkBox3, Shell shell) {
		
		push.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String txt = text.getText();
				
				if(txt == null) {
					return;
				}
					if(!txt.equals(checkBox1.getText()) 
							&& !txt.equals(checkBox2.getText())
							&& !txt.equals(checkBox3.getText())) {
						message(shell, txt);
					}  
					
					if(txt.equals(checkBox1.getText())) {
						if(checkBox1.getSelection() == true) {
							checkBox1.setSelection(false);
							return;
						}
						checkBox1.setSelection(true);
					}
					
					if(txt.equals(checkBox2.getText())) {
						if(checkBox2.getSelection() == true) {
							checkBox2.setSelection(false);
							return;
						}
						checkBox2.setSelection(true);
					}
					
					if(txt.equals(checkBox3.getText())) {
						if(checkBox3.getSelection() == true) {
							checkBox3.setSelection(false);
							return;
						}
						checkBox3.setSelection(true);
					}
			}
		});
	}
	
	private void message(Shell shell, String string) {
		MessageBox message = new MessageBox(shell);
		message.setText("ERROR");
		message.setMessage(string + ": Введите существующее название");
		message.open();
		return;
	}
}
