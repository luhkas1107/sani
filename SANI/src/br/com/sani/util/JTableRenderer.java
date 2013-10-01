package br.com.sani.util;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

//
public class JTableRenderer implements TableCellRenderer {
	JLabel label;
	
	@Override
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		label = new JLabel();
		
		int val = Integer.parseInt(String.valueOf((Object)table.getValueAt(row, column)));
		
		if (val == 1){
			label.setIcon(new ImageIcon(getClass().getResource("/br/com/mecklen/odontopro/image/bullet-red.png")));
		} else if(val == 2) {
			label.setIcon(new ImageIcon(getClass().getResource("/br/com/mecklen/odontopro/image/bullet-yellow.png")));
		} else if(val == 3){
			label.setIcon(new ImageIcon(getClass().getResource("/br/com/mecklen/odontopro/image/bullet-green.png")));
		} else if(val == 4){
			label.setIcon(new ImageIcon(getClass().getResource("/br/com/mecklen/odontopro/image/bullet-blue.png")));
		}
		
		label.setHorizontalAlignment(JLabel.CENTER);
		return label;
	}
}