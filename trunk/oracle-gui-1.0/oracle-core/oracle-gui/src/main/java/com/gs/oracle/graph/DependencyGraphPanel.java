/**
 * 
 */
package com.gs.oracle.graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.command.GuiCommandConstants;
import com.gs.oracle.model.Column;
import com.gs.oracle.model.ImportedTableRelation;
import com.gs.oracle.model.Table;
import com.gs.oracle.model.TableDependency;
import com.gs.oracle.util.DrawingUtil;

/**
 * @author sabuj.das
 *
 */
public class DependencyGraphPanel extends JPanel {
	
	public static final java.awt.Font DEFAULT_TEXT_FONT =
        new java.awt.Font(java.awt.Font.MONOSPACED,
            java.awt.Font.BOLD, 12);

	private Font bitstreamFont;
	
	private TableDependency dependency;
	private int X_Zero;
	private int Y_Zero;
	private int X_Width;
	private int Y_Height;
	private int scale;

	public DependencyGraphPanel(TableDependency dependency) {
		this.dependency = dependency;
		try {
			bitstreamFont = Font.createFont(Font.TRUETYPE_FONT, 
					getClass().getResourceAsStream("/fonts/VeraMono.ttf"));
			bitstreamFont = new Font(bitstreamFont.getFontName(),
            java.awt.Font.BOLD, 12);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBackground(new Color(255, 255, 255));
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(BorderFactory
						.createLineBorder(new Color(0, 0, 0)), BorderFactory
						.createMatteBorder(1, 25, 1, 25, new Color(207, 218,
								231))), new LineBorder(
						new Color(153, 153, 255), 1, true)));
		setLayout(new BorderLayout());
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphics = (Graphics2D) g;
		Insets i = getBorder().getBorderInsets(this);
		g.setColor(Color.BLACK);
		X_Zero = i.left;
		Y_Zero = i.top;
		X_Width = getWidth() - i.right;
		Y_Height = getHeight() - i.bottom;
		graphics.setFont(
				(null == bitstreamFont) ? DEFAULT_TEXT_FONT : bitstreamFont
			);
		
		int tableWidth = DrawingUtil.calculateTableWidth(graphics, dependency.getCurrentTable());
		int tableHeight = DrawingUtil.calculateTableHeight(graphics, dependency.getCurrentTable());
		int panelWidth = getWidth();
		int panelHeight = getHeight();
		if(panelWidth < tableWidth){
			panelWidth = tableWidth * 2;
		}
		if(panelHeight < tableHeight){
			panelHeight = tableHeight * 2;
		}
		int topMargin = 5;
		setMinimumSize(new Dimension(panelWidth, panelHeight));
		Point currentTableLocation = new Point(
				( panelWidth / 2 ) - (tableWidth / 2), Y_Zero + topMargin);
		Dimension currentTableSize = new Dimension(tableWidth, tableHeight);
		drawTable(graphics, currentTableLocation, currentTableSize, dependency.getCurrentTable());
		
		List<ImportedTableRelation> importedRelations = dependency.getImportedRelations();
		if(null != importedRelations && !importedRelations.isEmpty()){
			int imp_X = X_Zero + topMargin ;
			int imp_Y = Y_Zero + topMargin + currentTableSize.height + topMargin;
			for (ImportedTableRelation r : importedRelations) {
				Table t = r.getImportedTable();
				
			}
		}
		
	}
	
	public void drawTable(Graphics graphics, Point location, Dimension size, Table table){
		// draw the border
		graphics.setColor(OracleGuiConstants.TABLE_BORDER_COLOR);
		graphics.drawRect(location.x, location.y, size.width, size.height);
		graphics.setColor(OracleGuiConstants.COLUMN_NAMES_BG_COLOR);
		graphics.fillRect(location.x+1, location.y+1, size.width-1, size.height-1);
		// draw the header
		graphics.setColor(OracleGuiConstants.TABLE_BORDER_COLOR);
		graphics.drawRect(location.x, location.y, size.width, DrawingUtil.calculateCellHeight(graphics));
		graphics.setColor(OracleGuiConstants.TABLE_HEADER_BG_COLOR);
		graphics.fillRect(location.x+1, location.y+1, size.width-1, DrawingUtil.calculateCellHeight(graphics)-1);
		graphics.setColor(OracleGuiConstants.TABLE_HEADER_FG_COLOR);
		graphics.drawString(table.getModelName(), location.x+2, 
				location.y+DrawingUtil.calculateCellHeight(graphics)-2);
		// draw the left margin
		graphics.setColor(OracleGuiConstants.TABLE_BORDER_COLOR);
		graphics.drawRect(location.x, location.y + DrawingUtil.calculateCellHeight(graphics),
				OracleGuiConstants.TABLE_LEFT_MARGIN_WIDTH, size.height-DrawingUtil.calculateCellHeight(graphics));
		graphics.setColor(OracleGuiConstants.TABLE_LEFT_MARGIN_BG_COLOR);
		graphics.fillRect(location.x+1, location.y + DrawingUtil.calculateCellHeight(graphics)+1,
				OracleGuiConstants.TABLE_LEFT_MARGIN_WIDTH-1, size.height-1-DrawingUtil.calculateCellHeight(graphics));
		
		// draw columns
		int colStart_X = location.x + OracleGuiConstants.TABLE_LEFT_MARGIN_WIDTH + 5;
		int colStart_Y = location.y + DrawingUtil.calculateCellHeight(graphics) + 2;
		int cellHeight = DrawingUtil.calculateCellHeight(graphics);
		List<Column> columnList = table.getColumnlist();
		for(int i = 0; i<columnList.size(); i++){
			Column c = columnList.get(i);
			graphics.setColor(OracleGuiConstants.COLUMN_NAMES_FG_COLOR);
			graphics.drawString(c.getModelName(), 
					colStart_X + 2, colStart_Y + cellHeight);
			// if the column is not the last column
			if(i != columnList.size()-1){
				graphics.setColor(OracleGuiConstants.TABLE_BORDER_COLOR);
				graphics.drawLine(colStart_X+1, colStart_Y + cellHeight +2, location.x+size.width ,colStart_Y + cellHeight +2);
			}
			colStart_Y += cellHeight;
		}
	}
	
}
