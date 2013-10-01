package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import br.com.sani.util.SwingUtil;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmConsultaPropriedade extends JFrame {

	private JPanel contentPane;
	private JTextField txtEndereco;
	private JTextField txtProprietário;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmConsultaPropriedade frame = new frmConsultaPropriedade();
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
	public frmConsultaPropriedade() {
		SwingUtil.lookWindows(this);
		setTitle("Consulta de Propriedades");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmConsultaPropriedade.class.getResource("/br/com/images/home_badge.png")));
		setBounds(100, 100, 690, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 86, 645, 2);
		panel.add(separator);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setBounds(10, 11, 46, 14);
		panel.add(lblFiltro);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(10, 36, 68, 14);
		panel.add(lblEndereco);
		
		JLabel lblProprietario = new JLabel("Propriet\u00E1rio:");
		lblProprietario.setBounds(354, 36, 78, 14);
		panel.add(lblProprietario);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(75, 33, 269, 20);
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		txtProprietário = new JTextField();
		txtProprietário.setBounds(419, 33, 236, 20);
		panel.add(txtProprietário);
		txtProprietário.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 66, 46, 14);
		panel.add(lblEstado);
		
		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBoxEstado.setBounds(66, 61, 46, 20);
		panel.add(comboBoxEstado);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnCancelar.setBounds(10, 362, 89, 23);
		panel.add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 645, 231);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Endere\u00E7o", "Propriet\u00E1rio", "Valor R$"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(291);
		table.getColumnModel().getColumn(2).setPreferredWidth(208);
		scrollPane.setViewportView(table);
		
		JLabel lblEdit = new JLabel("");
		lblEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEdit.setToolTipText("Editar Propriedade");
		lblEdit.setIcon(new ImageIcon(frmConsultaPropriedade.class.getResource("/br/com/images/edit-.png")));
		lblEdit.setBounds(629, 360, 25, 25);
		panel.add(lblEdit);
		
		JLabel lblDelete = new JLabel("");
		lblDelete.setToolTipText("Apagar Propriedade");
		lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDelete.setIcon(new ImageIcon(frmConsultaPropriedade.class.getResource("/br/com/images/delete-.png")));
		lblDelete.setBounds(594, 360, 25, 25);
		panel.add(lblDelete);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setToolTipText("Pesquisar Propriedades");
		lblSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSearch.setIcon(new ImageIcon(frmConsultaPropriedade.class.getResource("/br/com/images/search-ico.png")));
		lblSearch.setBounds(630, 59, 25, 25);
		panel.add(lblSearch);
		
		setLocationRelativeTo(null);
	}

	public void fechar() {
		this.dispose();
	}
}
