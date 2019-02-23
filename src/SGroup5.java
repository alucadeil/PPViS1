import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
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
		rowLayout.marginLeft = 16;
		rowLayout.marginRight = 16;
		rowLayout.marginBottom = 10;
		
		Table table = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		
		TableColumn tc1 = new TableColumn(table, SWT.CENTER);
		tc1.setText("First");
		tc1.setWidth(80);
	    
	    TableColumn tc2 = new TableColumn(table, SWT.CENTER);
	    tc2.setText("Second");
	    tc2.setWidth(80);
	    


	    
	}
}
