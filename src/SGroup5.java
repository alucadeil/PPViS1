import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

public class SGroup5 {
	public SGroup5(Shell shell) {
		
		Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
		
		group.setText("Fifth group");
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		group.setLayout(rowLayout);
		rowLayout.marginTop = 10;
		rowLayout.marginLeft = 32;
		rowLayout.marginRight = 32;
		rowLayout.marginBottom = 10;
		rowLayout.spacing = 15;
	}
}
