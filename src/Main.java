import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class Main {

	public Main(Display display) {
		Shell shell = new Shell(display);
		shell.setText("LabWork");

        RowLayout rowLayout= new RowLayout(SWT.HORIZONTAL);
        shell.setLayout(rowLayout);
		rowLayout.marginTop = 10;
		rowLayout.marginLeft = 32;
		rowLayout.marginRight = 32;
		rowLayout.marginBottom = 10;
        rowLayout.spacing = 50;
		
		SGroup group1 = new SGroup(shell);
		SGroup2 group2 = new SGroup2(shell);
		SGroup3 group3 = new SGroup3(shell);
		SGroup4 group4  = new SGroup4(shell);
		SGroup5 group5 = new SGroup5(shell);
		
		shell.pack();
		CenteredWindow(shell);
		shell.open();
		
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private void CenteredWindow(Shell shell) {
		Rectangle body = shell.getDisplay().getBounds();
		
		Point p = shell.getSize();
		int mLeft = (body.width - p.x) /2;
		int mTop = (body.height - p.y) /2;
		
		shell.setBounds(mLeft, mTop, p.x, p.y);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Display display = new Display();
		Main workSpace = new Main(display);
		display.dispose();
	}

}
