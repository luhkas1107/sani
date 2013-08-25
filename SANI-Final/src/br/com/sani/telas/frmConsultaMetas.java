package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import br.com.sani.util.SwingUtil;

public class frmConsultaMetas extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldConsultaDataInicioMetas;
	private JTextField txtFieldConsultaDataFinalMetas;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmConsultaMetas frame = new frmConsultaMetas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmConsultaMetas() {
		SwingUtil.lookWindows(this);
		setTitle("Consulta Metas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmConsultaMetas.class.getResource("/br/com/images/home_badge.png")));
		setBounds(100, 100, 690, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 75, 645, 2);
		panel.add(separator);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setBounds(10, 11, 46, 14);
		panel.add(lblFiltro);
		
		JLabel lblDataInicioMetas = new JLabel("Data de In\u00EDcio:");
		lblDataInicioMetas.setBounds(10, 36, 89, 14);
		panel.add(lblDataInicioMetas);
		
		JLabel lblDataFinalMetas = new JLabel("Data Final:");
		lblDataFinalMetas.setBounds(208, 36, 78, 14);
		panel.add(lblDataFinalMetas);
		
		JButton button_1 = new JButton("Cancelar");
		button_1.setBounds(10, 368, 89, 23);
		panel.add(button_1);
		
		txtFieldConsultaDataInicioMetas = new JTextField();
		txtFieldConsultaDataInicioMetas.setBounds(99, 33, 86, 20);
		panel.add(txtFieldConsultaDataInicioMetas);
		txtFieldConsultaDataInicioMetas.setColumns(10);
		
		txtFieldConsultaDataFinalMetas = new JTextField();
		txtFieldConsultaDataFinalMetas.setColumns(10);
		txtFieldConsultaDataFinalMetas.setBounds(275, 33, 86, 20);
		panel.add(txtFieldConsultaDataFinalMetas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 644, 242);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data de In\u00EDcio", "Data Final", "Descri\u00E7\u00E3o"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(79);
		table.getColumnModel().getColumn(1).setPreferredWidth(81);
		table.getColumnModel().getColumn(2).setPreferredWidth(460);
		scrollPane.setViewportView(table);
		
		JLabel lblEdit = new JLabel("");
		lblEdit.setIcon(new ImageIcon(frmConsultaMetas.class.getResource("/br/com/images/edit-.png")));
		lblEdit.setBounds(630, 366, 25, 25);
		panel.add(lblEdit);
		
		JLabel lblDelete = new JLabel("");
		lblDelete.setIcon(new ImageIcon(frmConsultaMetas.class.getResource("/br/com/images/delete-.png")));
		lblDelete.setBounds(592, 366, 25, 25);
		panel.add(lblDelete);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(frmConsultaMetas.class.getResource("/br/com/images/search-ico.png")));
		lblSearch.setBounds(610, 33, 25, 25);
		panel.add(lblSearch);
		
		setLocationRelativeTo(null);
	}
}
