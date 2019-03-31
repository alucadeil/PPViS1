import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class SGroup5 {
	public SGroup5(Shell shell) {

		Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
		group.setText("Fifth group");
		group.setToolTipText("Пятое задание");

		GridLayout rowLayout = new GridLayout();
		rowLayout.numColumns = 4;
		group.setLayout(rowLayout);

		Text textLine = new Text(group, SWT.NONE);
		textLine.setText("Write here");
		textLine.setLocation(32, 30);
		textLine.pack();

		RhombButton textToTable = new RhombButton(group, SWT.PUSH);
		textToTable.setText("Push 1");

		RhombButton textToSecondColumn = new RhombButton(group, SWT.PUSH);
		textToSecondColumn.setText("Push 2");

		RhombButton textToFirstColumn = new RhombButton(group, SWT.PUSH);
		textToFirstColumn.setText("Push 3");

		Table table = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn firstTableColumn = new TableColumn(table, SWT.CENTER);
		firstTableColumn.setText("First");
		firstTableColumn.setWidth(80);

		TableColumn secondTableColumn = new TableColumn(table, SWT.CENTER);
		secondTableColumn.setText("Second");
		secondTableColumn.setWidth(80);

		textLineToColumn(textToTable, textLine, table, group, shell);

		selectedTextToSecondColumn(textToSecondColumn, table, textLine);

		selectedTextToFirstColumn(textToFirstColumn, table, textLine);

		group.pack();
	}

	private void textLineToColumn(RhombButton but1, Text text, Table table, Group group, Shell shell) {

		but1.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String txt = text.getText();
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(txt);
				group.pack();
				shell.pack();
			}
		});
	}

	private void selectedTextToSecondColumn(RhombButton but2, Table table, Text text) {

		but2.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int index = table.getSelectionIndex();
				if (index == -1) {
					return;
				}
				TableItem item = table.getItem(index);
				String txt = item.getText();
				if (txt == "") {
					return;
				}
				table.clear(index);
				item.setText(1, txt);
			}
		});
	}

	private void selectedTextToFirstColumn(RhombButton but3, Table table, Text text) {

		but3.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int index = table.getSelectionIndex();
				if (index == -1) {
					return;
				}
				TableItem item = table.getItem(index);
				String txt = item.getText(1);
				if (txt == "") {
					return;
				}
				table.clear(index);
				item.setText(0, txt);
			}
		});
	}
}
