package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import br.com.sani.util.SwingUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class frmConsultaClienteComprador extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldConsultaNomeClienteComprador;
	private JTextField txtFieldConsultaEnderecoClienteComprador;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmConsultaClienteComprador frame = new frmConsultaClienteComprador();
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
	public frmConsultaClienteComprador() {
		SwingUtil.lookWindows(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmConsultaClienteComprador.class.getResource("/br/com/images/home_badge.png")));
		setTitle("Consulta de Cliente Comprador");
		setBounds(100, 100, 690, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(9, 89, 645, 2);
		panel.add(separator);
		
		JLabel label = new JLabel("Filtro:");
		label.setBounds(10, 11, 46, 14);
		panel.add(label);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 36, 68, 14);
		panel.add(lblNome);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(325, 36, 78, 14);
		panel.add(lblEndereo);
		
		txtFieldConsultaNomeClienteComprador = new JTextField();
		txtFieldConsultaNomeClienteComprador.setBounds(57, 33, 259, 20);
		txtFieldConsultaNomeClienteComprador.setColumns(10);
		panel.add(txtFieldConsultaNomeClienteComprador);
		
		txtFieldConsultaEnderecoClienteComprador = new JTextField();
		txtFieldConsultaEnderecoClienteComprador.setBounds(395, 33, 259, 20);
		txtFieldConsultaEnderecoClienteComprador.setColumns(10);
		panel.add(txtFieldConsultaEnderecoClienteComprador);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 61, 46, 14);
		panel.add(lblEstado);
		
		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBoxEstado.setBounds(57, 58, 45, 20);
		panel.add(comboBoxEstado);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnCancelar.setBounds(10, 362, 89, 23);
		panel.add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 102, 623, 254);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "Endere\u00E7o", "Contato", "Tipo de Cliente"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		table.getColumnModel().getColumn(1).setPreferredWidth(197);
		table.getColumnModel().getColumn(2).setPreferredWidth(192);
		table.getColumnModel().getColumn(3).setPreferredWidth(86);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		scrollPane.setViewportView(table);
		
		JLabel lblRemove = new JLabel("");
		lblRemove.setToolTipText("Apagar Cliente");
		lblRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Remove o Usuario Selecionado
			}
		});
		lblRemove.setIcon(new ImageIcon(frmConsultaClienteComprador.class.getResource("/br/com/images/delete-.png")));
		lblRemove.setBounds(583, 361, 25, 25);
		panel.add(lblRemove);
		
		JLabel lblEdit = new JLabel("");
		lblEdit.setToolTipText("Editar Cliente");
		lblEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Edita o Cliente Selecionado
				JOptionPane.showMessageDialog(null, "Teste");
			}
		});
		lblEdit.setIcon(new ImageIcon(frmConsultaClienteComprador.class.getResource("/br/com/images/edit-.png")));
		lblEdit.setBounds(618, 361, 25, 25);
		panel.add(lblEdit);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setToolTipText("Pesquisar Clientes");
		lblSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Faz a Busca no BD
			}
		});
		lblSearch.setIcon(new ImageIcon(frmConsultaClienteComprador.class.getResource("/br/com/images/search-ico.png")));
		lblSearch.setBounds(618, 56, 25, 25);
		panel.add(lblSearch);
		
		setLocationRelativeTo(null);		
	}
	
	public void fechar(){
		this.dispose();
	}
}
