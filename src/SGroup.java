import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.RowLayout;

public class SGroup {
	
	public SGroup(Shell shell) {
		
		Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
		group.setText("First Group");
		
		group.setToolTipText("œÂ‚ÓÂ Á‡‰‡ÌËÂ");
		
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		group.setLayout(rowLayout);
		rowLayout.marginTop = 10;
		rowLayout.marginLeft = 32;
		rowLayout.marginRight = 32;
		rowLayout.marginBottom = 10;
		rowLayout.spacing = 10;
		
		Text textLine = new Text(group, SWT.NONE);
		textLine.setText("Write here");
		textLine.setLocation(32, 30);
		textLine.pack();
		
		Combo comboBox = new Combo(group, SWT.DROP_DOWN);
		comboBox.setLocation(32, 96);
		comboBox.add("Hello!");
		comboBox.pack();
		
		Button buttonSelectionToCombo = new Button(group, SWT.PUSH);
		buttonSelectionToCombo.setLocation(32, 64);
		buttonSelectionToCombo.setText("PUSH ME");
		buttonSelectionToCombo.pack();
		
		buttonSelectionToCombo.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String txt = textLine.getText();
				if(txt == null) {
					return;
				}
				String[] ComboItems = comboBox.getItems();
				for(String Item : ComboItems) {
					if(Item.equals(txt)) {
						MessageBox message = new MessageBox(shell);
						message.setText("ERROR");
						message.setMessage("Õ≈À‹«ﬂ ≈Ÿ≈ –¿« ƒŒ¡¿¬Àﬂ“‹!");
						message.open();
						return;
					}
				}
				comboBox.add(txt);
			}
		});

	}
	
}
