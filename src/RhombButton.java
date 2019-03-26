import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TypedListener;


public class RhombButton extends Canvas {
	
	protected Region region;
	
	protected Listener keyListener;
	protected Image image, backgroundImage;
	protected String text;
	protected Font font;
	protected Color fontColor, hoverFontColor, clickedFontColor, inactiveFontColor, selectedFontColor;
	protected Color borderColor, hoverBorderColor, clickedBorderColor, inactiveBorderColor, selectedBorderColor;
	protected Color currentColor, currentColor2, currentFontColor, currentBorderColor;
	protected Color backgroundColor, backgroundColor2;
	protected Color clickedColor, clickedColor2;
	protected Color hoverColor, hoverColor2;
	protected Color inactiveColor, inactiveColor2;
	protected Color selectedColor, selectedColor2;
	protected int innerMarginWidth = 8;
	protected int innerMarginHeight = 4;
	protected int borderWidth = 1;
	protected int imagePadding = 5;
	protected boolean enabled = true;
	protected boolean roundedCorners = true;
	protected boolean isFocused = false;
	protected boolean selectionBorder = false;
	private int lastWidth, lastHeight;
	
	public static int BG_IMAGE_CROP = 0;
	public static int BG_IMAGE_STRETCH = 1;
	public static int BG_IMAGE_TILE = 2;
	public static int BG_IMAGE_CENTER = 3;
	public static int BG_IMAGE_FIT = 4;
	protected int backgroundImageStyle = 0;
	
	public static int IMAGE_LEFT = 0;
	public static int IMAGE_RIGHT = 1;
	protected int imageStyle = 0;
	
	
	public RhombButton(Composite parent, int style) {
		super(parent, style | SWT.NO_BACKGROUND);
		this.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		setDefaultColors();
		addListeners();
	}
	
	
	protected void widgetDisposed(DisposeEvent e) {
		// TODO clean up here (listeners?)
	}
	
	
	protected void addListeners() {
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				RhombButton.this.widgetDisposed(e);
			}
		});
		
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				RhombButton.this.paintControl(e);
			}
		});
		
		// MOUSE EVENTS
		this.addListener(SWT.MouseEnter, new Listener() {
			public void handleEvent(Event e) {
				RhombButton.this.setHoverColor(e);
			}
		});
		this.addListener(SWT.MouseExit, new Listener() {
			public void handleEvent(Event e) {
				if (isFocused)
					RhombButton.this.setSelectedColor(e);
				else
					RhombButton.this.setNormalColor(e);
			}
		});
		this.addListener(SWT.MouseUp, new Listener() {
			public void handleEvent(Event e) {
				if (e.button == 1) {
					RhombButton.this.setHoverColor(e);
					if ((e.count == 1) && enabled && (getClientArea().contains(e.x, e.y))) {
						doButtonClicked();
					}
				}
			}
		});
		this.addListener(SWT.MouseHover, new Listener() {
			public void handleEvent(Event e) {
				RhombButton.this.setHoverColor(e);
			}
		});
		this.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event e) {
				if (e.button == 1) {
					RhombButton.this.setClickedColor(e);
				}
			}
		});
		
		this.addListener (SWT.Traverse, new Listener () {
			public void handleEvent (Event e) {
				switch (e.detail) {
					case SWT.TRAVERSE_ESCAPE:
					case SWT.TRAVERSE_RETURN:
					case SWT.TRAVERSE_TAB_NEXT:	
					case SWT.TRAVERSE_TAB_PREVIOUS:
					case SWT.TRAVERSE_PAGE_NEXT:	
					case SWT.TRAVERSE_PAGE_PREVIOUS:
						e.doit = true;
						break;
				}
			}
		});
		this.addListener (SWT.FocusIn, new Listener () {
			public void handleEvent (Event e) {
				isFocused = true;
				RhombButton.this.setSelectedColor(e);
				redraw();
			}
		});
		this.addListener (SWT.FocusOut, new Listener () {
			public void handleEvent (Event e) {
				isFocused = false;
				RhombButton.this.setNormalColor(e);
				redraw();
			}
		});
		
		this.addListener (SWT.KeyUp, new Listener () {
			public void handleEvent (Event e) {
				isFocused = true;
				RhombButton.this.setSelectedColor(e);
				redraw();
			}
		});
		keyListener = new Listener () {
			public void handleEvent (Event e) {
				// required for tab traversal to work
				switch (e.character) {
					case ' ':
					case '\r':
					case '\n':
						RhombButton.this.setClickedColor(e);
						redraw();
						doButtonClicked();
						break;
				}
			}
		};
		setTraversable(true);
	}
	
	public void addSelectionListener (SelectionListener listener) {
		addListener(SWT.Selection, new TypedListener(listener));
	}
	public void removeSelectionListener (SelectionListener listener) {
		removeListener(SWT.Selection, listener);
	}
	
	
	protected void setTraversable (boolean canTraverse) {
		try {
			if (canTraverse)
				this.addListener (SWT.KeyDown, keyListener);
			else if (!canTraverse)
				this.removeListener(SWT.KeyDown, keyListener);
		} catch (Exception e) {}
	}
	
	
	protected void doButtonClicked () {
		Event e = new Event();
		e.item = this;
		e.widget = this;
		e.type = SWT.Selection;
		notifyListeners(SWT.Selection, e);
	}
	
	protected void setDefaultColors () {
		fontColor = getSavedColor(0, 0, 0);
		hoverFontColor = getSavedColor(0, 0, 0);
		clickedFontColor = getSavedColor(0, 0, 0);
		inactiveFontColor = getSavedColor(187, 187, 187);
		selectedFontColor = getSavedColor(0, 0, 0);
		borderColor = getSavedColor(0, 120, 215);
		hoverBorderColor = getSavedColor(147, 147, 147);
		clickedBorderColor = getSavedColor(147, 147, 147);
		inactiveBorderColor = getSavedColor(200, 200, 200);
		selectedBorderColor = getSavedColor(160, 107, 38);
		backgroundColor = getSavedColor(173,173,173);
		backgroundColor2 = getSavedColor(225,225,225);
		clickedColor = getSavedColor(120, 120, 120);
		clickedColor2 = getSavedColor(204,228,247);
		hoverColor = getSavedColor(0, 120, 215);
		hoverColor2 = getSavedColor(229,241,251);
		inactiveColor = getSavedColor(248, 248, 248);
		inactiveColor2 = getSavedColor(228, 228, 228);
		selectedColor = getSavedColor(0,120,215);
		selectedColor2 = getSavedColor(173,173,173);
	}
	
	
	protected Color getSavedColor (int r, int g, int b) {
		String colorString = "SB_DEFAULT:" + r + "-" + g + "-" + b;
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		if (!colorRegistry.hasValueFor(colorString)) {
			colorRegistry.put(colorString, new RGB(r, g, b));
		}
		return colorRegistry.get(colorString);
	}
	
	
	protected void setNormalColor (Event e) {
		setMouseEventColor(backgroundColor, backgroundColor2, borderColor, fontColor);
	}
	protected void setHoverColor (Event e) {
		setMouseEventColor(hoverColor, hoverColor2, hoverBorderColor, hoverFontColor);
	}
	protected void setClickedColor (Event e) {
		setMouseEventColor(clickedColor, clickedColor2, clickedBorderColor, clickedFontColor);
	}
	protected void setInactiveColor (Event e) {
		setMouseEventColor(inactiveColor, inactiveColor2, inactiveBorderColor, inactiveFontColor);
	}
	protected void setSelectedColor (Event e) {
		setMouseEventColor(selectedColor, selectedColor2, selectedBorderColor, selectedFontColor);
	}
	protected void setMouseEventColor (Color bgColor1, Color bgColor2, Color bdrColor, Color fntColor) {
		if (!this.enabled)
			return;
		
		if (currentColor == null) {
			currentColor = backgroundColor;
			currentColor2 = backgroundColor2;
			currentBorderColor = borderColor;
			currentFontColor = fontColor;
		}
		
		boolean redrawFlag = false;
		if ((bgColor1 != null) && (!currentColor.equals(bgColor1))) {
			currentColor = currentColor2 = bgColor1;
			if (bgColor2 != null) {
				currentColor2 = bgColor2;
			}
			redrawFlag = true;
		}
		if ((bdrColor != null) && (!currentBorderColor.equals(bdrColor))) {
			currentBorderColor = bdrColor;
			redrawFlag = true;
		}
		if ((fntColor != null) && (!currentFontColor.equals(fntColor))) {
			currentFontColor = fntColor;
			redrawFlag = true;
		}
		if (redrawFlag) { redraw(); }
	}
	
	
	private void paintControl(PaintEvent e) {
		if (currentColor == null) {
			currentColor = backgroundColor;
			currentColor2 = backgroundColor2;
			currentBorderColor = borderColor;
			currentFontColor = fontColor;
		}

		int x = this.innerMarginWidth + 1;
		int y = this.innerMarginHeight;
		
		Point p = this.computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
		// with certain layouts, the width is sometimes 1 pixel too wide
		if (p.x > getClientArea().width) { p.x = getClientArea().width; }
		
		Rectangle rect = new Rectangle(0, 0, p.x, p.y);
		
		GC gc = e.gc;
		gc.setAntialias(SWT.ON);
		gc.setAdvanced(true);
		
		
		// add transparency by making the canvas background the same as 
		// the parent background (only needed for rounded corners)
		if (roundedCorners) {
			gc.setBackground(getParent().getBackground());
			gc.fillRectangle(rect);
		}
		
		
		// draw the background color of the inside of the button. There's no such
		// thing as a rounded gradient rectangle in SWT, so we need to draw a filled
		// rectangle that's just the right size to fit inside a rounded rectangle
		// without spilling out at the corners
		gc.setForeground(this.currentColor);
		gc.setBackground(this.currentColor2);
		gc.setLineStyle(SWT.LINE_SOLID);
		
		Rectangle fill = new Rectangle(rect.x + 1, rect.y + 1, rect.width - 1, rect.height - 1);
		if (roundedCorners) {
			fill = new Rectangle(rect.x + 1, rect.y + 1, rect.width - 2, rect.height - 3);
		}
		//gc.fillGradientRectangle(fill.x, fill.y, fill.width, fill.height, true);
		
		gc.setLineWidth(2);

		region = new Region();
		region.add(new int[] {-3, rect.height/2, rect.width/2, -3, rect.width+3, rect.height/2, rect.width/2, rect.height+1});
		this.setRegion(region);
		
		gc.fillPolygon(new int[] {0, rect.height/2, rect.width/2, 0, rect.width, rect.height/2, rect.width/2, rect.height-2});
		
		gc.drawLine(rect.width/2, 1, rect.width, rect.height/2);
		gc.drawLine(rect.width, rect.height/2, rect.width/2, rect.height-2);
		gc.drawLine(rect.width/2, rect.height-2, 0, rect.height/2);
		gc.drawLine(0, rect.height/2, rect.width/2, 1);
		
		
		gc.setForeground(this.currentColor);
		gc.setBackground(this.currentColor2);
		
		if (imageStyle == IMAGE_RIGHT) {
			drawText(gc, x, y);
			if (image != null) {
				x = rect.width - x - image.getBounds().width + imagePadding;
				drawImage(gc, x, y);
			}
		} else {
			x = drawImage(gc, x, y);
			drawText(gc, x+10, y+10);
		}
		

	}
	
	private void drawText (GC gc, int x, int y) {
		gc.setFont(font);
		gc.setForeground(currentFontColor);
		gc.drawText(text, x, y, SWT.DRAW_TRANSPARENT);
	}
	
	
	private int drawImage (GC gc, int x, int y) {
		if (image == null)
			return x;
		gc.drawImage(image, x, y);
		return x + image.getBounds().width + imagePadding;
	}
	
	
	private void drawBackgroundImage (GC gc, Rectangle rect) {
		if (backgroundImage == null)
			return;
		
		Rectangle imgBounds = backgroundImage.getBounds();
		
		if (backgroundImageStyle == BG_IMAGE_STRETCH) {
			gc.drawImage(backgroundImage, 0, 0, imgBounds.width, imgBounds.height, rect.x, rect.y, rect.width, rect.height);
			
		} else if (backgroundImageStyle == BG_IMAGE_CENTER) {
			int x = (imgBounds.width - rect.width) / 2;
			int y = (imgBounds.height - rect.height) / 2;
			Rectangle centerRect = new Rectangle(rect.x, rect.y, rect.width, rect.height);
			if (x < 0) {
				centerRect.x -= x;
				x = 0;
			}
			if (y < 0) {
				centerRect.y -= y;
				y = 0;
			}
			drawClippedImage(gc, backgroundImage, x, y, centerRect);
			
		} else if (backgroundImageStyle == BG_IMAGE_TILE) {
			for (int y = 0; y < rect.height; y += imgBounds.height) {
				Rectangle tileRect = new Rectangle(rect.x, y + rect.y, rect.width, rect.height-y);
				
				for (int x = 0; x < rect.width; x += imgBounds.width) {
					tileRect.x += drawClippedImage(gc, backgroundImage, 0, 0, tileRect);
					tileRect.width -= x;
				}
			}
			
		} else {
			drawClippedImage(gc, backgroundImage, 0, 0, rect);
		}
	}
	
	private int drawClippedImage (GC gc, Image image, int x, int y, Rectangle rect) {
		if (image != null) {
			Rectangle imgBounds = image.getBounds();
			int width = Math.min(imgBounds.width-x, rect.width);
			int height = Math.min(imgBounds.height-y, rect.height);
			gc.drawImage(image, x, y, width, height, rect.x, rect.y, width, height);
			return width;
		}
		return 0;
	}
	
	
	public Point computeSize(int wHint, int hHint, boolean changed) {
		if ((wHint == SWT.DEFAULT) && (hHint == SWT.DEFAULT) && !changed && 
				(lastWidth > 0) && (lastHeight > 0)) {
			return new Point(lastWidth, lastHeight);
		}
		
		int width = 0, height = 0;
		if (image != null) {
			Rectangle bounds = image.getBounds();
			width = bounds.width + imagePadding;
			height = bounds.height + (this.innerMarginHeight*2);
		}
		if (text != null) {
			GC gc = new GC(this);
			gc.setFont(font);
			Point extent = gc.textExtent(text);
			gc.dispose();
			
			width += extent.x + (this.innerMarginWidth*2);
			height = Math.max(height, extent.y + (this.innerMarginHeight*2));
		}
		
		if (wHint != SWT.DEFAULT) width = wHint;
		if (hHint != SWT.DEFAULT) height = hHint;
		
		if ((backgroundImage != null) && (backgroundImageStyle == BG_IMAGE_FIT)) {
			width = backgroundImage.getBounds().width;
			height = backgroundImage.getBounds().height;
		}
		
		lastWidth = width + 20;
		lastHeight = height + 20;
		return new Point(lastWidth, lastHeight);
	}
	
	
	public void setImage(Image image) {
		this.image = image;
		redraw();
	}
	public Image getImage() {
		return image;
	}
	
	public void setImageStyle(int imageStyle) {
		this.imageStyle = imageStyle;
	}
	public int getImageStyle() {
		return imageStyle;
	}
	
	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
		redraw();
	}
	public Image getBackgroundImage() {
		return backgroundImage;
	}
	
	public void setBackgroundImageStyle(int backgroundImageStyle) {
		this.backgroundImageStyle = backgroundImageStyle;
	}
	public int getBackgroundImageStyle() {
		return backgroundImageStyle;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
		redraw();
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		if (font != null)
			this.font = font;
	}
	
	public void setEnabled(boolean enabled) {
		boolean oldSetting = this.enabled;
		this.enabled = enabled;
		if (oldSetting != enabled) {
			if (!enabled) {
				this.enabled = true;
				this.setInactiveColor(null);
				this.setTraversable(false);
				this.enabled = false;
			} else {
				this.setNormalColor(null);
				this.setTraversable(true);
			}
		}
	}
	
	public boolean getEnabled() {
		return enabled;
	}
	public boolean isEnabled() {
		return enabled;
	}

	public void setInnerMarginWidth(int innerMarginWidth) {
		if (innerMarginWidth >= 0)
			this.innerMarginWidth = innerMarginWidth;
	}
	public int getInnerMarginWidth() {
		return innerMarginWidth;
	}
	
	public void setInnerMarginHeight(int innerMarginHeight) {
		if (innerMarginHeight >= 0)
			this.innerMarginHeight = innerMarginHeight;
	}
	public int getInnerMarginHeight() {
		return innerMarginHeight;
	}
	
	public void setRoundedCorners(boolean roundedCorners) {
		this.roundedCorners = roundedCorners;
	}
	public boolean hasRoundedCorners() {
		return roundedCorners;
	}
	
	public void setSelectionBorder(boolean selectionBorder) {
		this.selectionBorder = selectionBorder;
	}
	public boolean hasSelectionBorder() {
		return selectionBorder;
	}
	
	/**
	 * Set the width of the button border, in pixels (default is 1).
	 * 
	 * @param borderWidth
	 */
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}
	public int getBorderWidth() {
		return borderWidth;
	}
	
}