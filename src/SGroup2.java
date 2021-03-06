import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SGroup2 {
		public SGroup2 (Shell shell) {
			
			Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
			group.setText("Second Group");
			group.setToolTipText("������ �������");
			
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

			RhombButton textToButton = new RhombButton(group, SWT.PUSH);
			textToButton.setLocation(32, 64);
			textToButton.setText("Text one");
			textToButton.pack();
			
			RhombButton buttonTextToAnotherButton = new RhombButton(group, SWT.PUSH);
			buttonTextToAnotherButton.setLocation(32, 96);
			buttonTextToAnotherButton.setText("Text");
			buttonTextToAnotherButton.pack();
		
			textToButton.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					String txt = textLine.getText();
					if(txt == null) {
						return;
					}
					buttonTextToAnotherButton.setText(txt);
					buttonTextToAnotherButton.pack();
					group.pack();
					shell.pack();
				}
			});
			
			buttonTextToAnotherButton.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					String txt = textToButton.getText();
					if(txt == null) {
						return;
					}
					textToButton.setText(buttonTextToAnotherButton.getText());
					textToButton.pack();
					buttonTextToAnotherButton.setText(txt);
					buttonTextToAnotherButton.pack();
					group.pack();
					shell.pack();
				}
			});
		
		}
		
}
