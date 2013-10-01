package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import br.com.sani.util.SwingUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmConsultaFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldNomeConsultaFuncionario;
	private JTextField txtFieldEnderecoConsultaFuncionario;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmConsultaFuncionario frame = new frmConsultaFuncionario();
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
	public frmConsultaFuncionario() {
		SwingUtil.lookWindows(this);
		setTitle("Consulta Funcion\u00E1rio");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmConsultaFuncionario.class.getResource("/br/com/images/home_badge.png")));
		setBounds(100, 100, 690, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 86, 645, 2);
		panel.add(separator);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setBounds(10, 11, 46, 14);
		panel.add(lblFiltro);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 36, 68, 14);
		panel.add(lblNome);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(10, 61, 78, 14);
		panel.add(lblEndereco);
		
		txtFieldNomeConsultaFuncionario = new JTextField();
		txtFieldNomeConsultaFuncionario.setColumns(10);
		txtFieldNomeConsultaFuncionario.setBounds(75, 33, 269, 20);
		panel.add(txtFieldNomeConsultaFuncionario);
		
		txtFieldEnderecoConsultaFuncionario = new JTextField();
		txtFieldEnderecoConsultaFuncionario.setColumns(10);
		txtFieldEnderecoConsultaFuncionario.setBounds(75, 58, 269, 20);
		panel.add(txtFieldEnderecoConsultaFuncionario);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(354, 36, 46, 14);
		panel.add(lblEstado);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBox.setBounds(401, 33, 45, 20);
		panel.add(comboBox);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnCancelar.setBounds(10, 368, 89, 23);
		panel.add(btnCancelar);
		
		JLabel lblFuncao = new JLabel("Fun\u00E7\u00E3o:");
		lblFuncao.setBounds(354, 61, 46, 14);
		panel.add(lblFuncao);
		
		JComboBox comboBoxFuncao = new JComboBox();
		comboBoxFuncao.setModel(new DefaultComboBoxModel(new String[] {"Corretor (a) de Im\u00F3veis", "Secret\u00E1ria", "Gerente", "Consultor (a)"}));
		comboBoxFuncao.setBounds(401, 58, 155, 20);
		panel.add(comboBoxFuncao);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 111, 644, 241);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "Endere\u00E7o", "Estado", "Cargo", "Sal\u00E1rio"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(48);
		table.getColumnModel().getColumn(1).setPreferredWidth(183);
		table.getColumnModel().getColumn(2).setPreferredWidth(182);
		table.getColumnModel().getColumn(3).setPreferredWidth(48);
		table.getColumnModel().getColumn(4).setPreferredWidth(87);
		scrollPane.setViewportView(table);
		
		JLabel lblEdit = new JLabel("");
		lblEdit.setToolTipText("Editar Funcionario");
		lblEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Edita o Funcionario Selecionado
			}
		});
		lblEdit.setIcon(new ImageIcon(frmConsultaFuncionario.class.getResource("/br/com/images/edit-.png")));
		lblEdit.setBounds(630, 366, 25, 25);
		panel.add(lblEdit);
		
		JLabel lblRemove = new JLabel("");
		lblRemove.setToolTipText("Apagar Funcionario");
		lblRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Remove o Funcionario Selecionado
			}
		});
		lblRemove.setIcon(new ImageIcon(frmConsultaFuncionario.class.getResource("/br/com/images/delete-.png")));
		lblRemove.setBounds(595, 366, 25, 25);
		panel.add(lblRemove);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setToolTipText("Pesquisar Funcionarios");
		lblSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Faz a busca no BD
			}
		});
		lblSearch.setIcon(new ImageIcon(frmConsultaFuncionario.class.getResource("/br/com/images/search-ico.png")));
		lblSearch.setBounds(629, 50, 25, 25);
		panel.add(lblSearch);
		
		setLocationRelativeTo(null);
	}

	public void fechar() {
		this.dispose();		
	}
}
