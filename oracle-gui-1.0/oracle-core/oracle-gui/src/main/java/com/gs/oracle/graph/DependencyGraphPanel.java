/**
 * 
 */
package com.gs.oracle.graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.gs.oracle.model.TableDependency;

/**
 * @author sabuj.das
 *
 */
public class DependencyGraphPanel extends JPanel {
	
	private TableDependency dependency;

	public DependencyGraphPanel(TableDependency dependency) {
		this.dependency = dependency;
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
		int X_Zero = i.left;
		int Y_Zero = i.top;
		int 
		g.drawLine(0, 0, 100, 100);
		
	}
}
