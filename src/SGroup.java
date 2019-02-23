import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.RowLayout;

public class SGroup {
	
	public SGroup(Shell shell) {
		
		Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
		group.setText("First Group");
		
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		group.setLayout(rowLayout);
		rowLayout.marginTop = 10;
		rowLayout.marginLeft = 32;
		rowLayout.marginRight = 32;
		rowLayout.marginBottom = 10;
		rowLayout.spacing = 10;
		
		Text text = new Text(group, SWT.NONE);
		text.setText("Write here");
		text.setLocation(32, 30);
		text.pack();
		
		Combo combo = new Combo(group, SWT.DROP_DOWN);
		combo.setLocation(32, 96);
		combo.add("Hello!");
		combo.pack();
		
		Button button = new Button(group, SWT.PUSH);
		button.setLocation(32, 64);
		button.setText("PUSH ME");
		button.pack();
		
		button.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String txt = text.getText();
				if(txt == null) {
					return;
				}
				String[] ComboItems = combo.getItems();
				for(String Item : ComboItems) {
					if(Item.equals(txt)) {
						MessageBox message = new MessageBox(shell);
						message.setText("ERROR");
						message.setMessage("Õ≈À‹«ﬂ ≈Ÿ≈ –¿« ƒŒ¡¿¬Àﬂ“‹!");
						message.open();
						return;
					}
				}
				combo.add(txt);
			}
		});

	}
	
}
