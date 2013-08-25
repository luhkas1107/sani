package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.ScrollPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPasswordField;

import br.com.sani.util.SwingUtil;

public class frmCadastroFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeFuncionario;
	private JTextField txtCpfFuncionario;
	private JTextField txtRgFuncionario;
	private JTextField txtEnderecoFuncionario;
	private JTextField txtNumeroEnderecoFuncionario;
	private JTextField txtComplementoFuncionario;
	private JTextField txtCepFuncionario;
	private JTextField txtTelefoneResidencialFuncionario;
	private JTextField txtTelefoneCelularFuncionario;
	private JTextField txtSiteFuncionario;
	private JTextField txtEmailFuncionario;
	private JTextField txtNacionalidadeFuncionario;
	private JTextField txtLoginFuncionario;
	private JPasswordField passwordFieldSenhaFuncionario;
	private JPasswordField passwordFieldConfSenhaFuncionario;
	private JTextField txtRegistroFuncionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCadastroFuncionario frame = new frmCadastroFuncionario();
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
	public frmCadastroFuncionario() {
		SwingUtil.lookWindows(this);
		setResizable(false);
		setTitle("Cadastro Funcionario");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmCadastroFuncionario.class.getResource("/br/com/images/home_badge.png")));
		setBounds(100, 100, 521, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 515, 523);
		contentPane.add(panel);
		
		JLabel lblNomeFuncionario = new JLabel("Nome: *");
		lblNomeFuncionario.setBounds(10, 11, 46, 14);
		panel.add(lblNomeFuncionario);
		
		JLabel lblSexoFuncionario = new JLabel("Sexo: *");
		lblSexoFuncionario.setBounds(10, 36, 46, 14);
		panel.add(lblSexoFuncionario);
		
		JRadioButton rdbtnMasculinoFuncionario = new JRadioButton("Masculino");
		rdbtnMasculinoFuncionario.setBounds(56, 32, 109, 23);
		panel.add(rdbtnMasculinoFuncionario);
		
		JRadioButton rdbtnFemininoFuncionario = new JRadioButton("Feminino");
		rdbtnFemininoFuncionario.setBounds(172, 32, 109, 23);
		panel.add(rdbtnFemininoFuncionario);
		
		//Cria o Button Group
		
		ButtonGroup grupoSexo = new ButtonGroup();  
        grupoSexo.add(rdbtnMasculinoFuncionario);  
        grupoSexo.add(rdbtnFemininoFuncionario);
		
		txtNomeFuncionario = new JTextField();
		txtNomeFuncionario.setColumns(10);
		txtNomeFuncionario.setBounds(56, 8, 327, 20);
		panel.add(txtNomeFuncionario);
		
		JLabel lblCpfFuncionario = new JLabel("CPF: *");
		lblCpfFuncionario.setBounds(10, 61, 46, 14);
		panel.add(lblCpfFuncionario);
		
		txtCpfFuncionario = new JTextField();
		txtCpfFuncionario.setColumns(10);
		txtCpfFuncionario.setBounds(57, 58, 109, 20);
		panel.add(txtCpfFuncionario);
		
		JLabel lblEstadoCivilFuncionario = new JLabel("Estado Civil:");
		lblEstadoCivilFuncionario.setBounds(10, 86, 81, 14);
		panel.add(lblEstadoCivilFuncionario);
		
		JComboBox comboBoxEstadoCivilFuncionario = new JComboBox();
		comboBoxEstadoCivilFuncionario.setModel(new DefaultComboBoxModel(new String[] {"Solteiro (a)", "Casado (a)", "Divorciado (a)", "Vi\u00FAvo (a)"}));
		comboBoxEstadoCivilFuncionario.setBounds(80, 83, 137, 20);
		panel.add(comboBoxEstadoCivilFuncionario);
		
		JLabel lblRgFuncionario = new JLabel("RG:");
		lblRgFuncionario.setBounds(182, 61, 46, 14);
		panel.add(lblRgFuncionario);
		
		txtRgFuncionario = new JTextField();
		txtRgFuncionario.setColumns(10);
		txtRgFuncionario.setBounds(215, 58, 109, 20);
		panel.add(txtRgFuncionario);
		
		JLabel lblNacionalidadeFuncionario = new JLabel("Nacionalidade:");
		lblNacionalidadeFuncionario.setBounds(240, 86, 98, 14);
		panel.add(lblNacionalidadeFuncionario);
		
		txtNacionalidadeFuncionario = new JTextField();
		txtNacionalidadeFuncionario.setColumns(10);
		txtNacionalidadeFuncionario.setBounds(335, 83, 116, 20);
		panel.add(txtNacionalidadeFuncionario);
		
		JLabel lblEnderecoFuncionario = new JLabel("Endere\u00E7o: *");
		lblEnderecoFuncionario.setBounds(10, 111, 63, 14);
		panel.add(lblEnderecoFuncionario);
		
		txtEnderecoFuncionario = new JTextField();
		txtEnderecoFuncionario.setColumns(10);
		txtEnderecoFuncionario.setBounds(80, 108, 244, 20);
		panel.add(txtEnderecoFuncionario);
		
		JLabel lblNumeroFuncionario = new JLabel("N\u00FAmero: *");
		lblNumeroFuncionario.setBounds(329, 111, 54, 14);
		panel.add(lblNumeroFuncionario);
		
		txtNumeroEnderecoFuncionario = new JTextField();
		txtNumeroEnderecoFuncionario.setColumns(10);
		txtNumeroEnderecoFuncionario.setBounds(388, 108, 63, 20);
		panel.add(txtNumeroEnderecoFuncionario);
		
		JLabel lblComplementoFuncionario = new JLabel("Complemento:");
		lblComplementoFuncionario.setBounds(10, 136, 96, 14);
		panel.add(lblComplementoFuncionario);
		
		txtComplementoFuncionario = new JTextField();
		txtComplementoFuncionario.setColumns(10);
		txtComplementoFuncionario.setBounds(101, 133, 102, 20);
		panel.add(txtComplementoFuncionario);
		
		JLabel lblCepFuncionario = new JLabel("CEP: *");
		lblCepFuncionario.setBounds(225, 136, 46, 14);
		panel.add(lblCepFuncionario);
		
		txtCepFuncionario = new JTextField();
		txtCepFuncionario.setColumns(10);
		txtCepFuncionario.setBounds(265, 133, 86, 20);
		panel.add(txtCepFuncionario);
		
		JLabel lblTelefoneResidencialFuncionario = new JLabel("Telefone Residencial:");
		lblTelefoneResidencialFuncionario.setBounds(10, 161, 132, 14);
		panel.add(lblTelefoneResidencialFuncionario);
		
		txtTelefoneResidencialFuncionario = new JTextField();
		txtTelefoneResidencialFuncionario.setColumns(10);
		txtTelefoneResidencialFuncionario.setBounds(136, 158, 106, 20);
		panel.add(txtTelefoneResidencialFuncionario);
		
		JLabel lblTelefoneCelularFuncionario = new JLabel("Telefone Celular: *");
		lblTelefoneCelularFuncionario.setBounds(265, 161, 118, 14);
		panel.add(lblTelefoneCelularFuncionario);
		
		txtTelefoneCelularFuncionario = new JTextField();
		txtTelefoneCelularFuncionario.setColumns(10);
		txtTelefoneCelularFuncionario.setBounds(372, 158, 106, 20);
		panel.add(txtTelefoneCelularFuncionario);
		
		JLabel lblImagemFuncionario = new JLabel("Imagem:");
		lblImagemFuncionario.setBounds(10, 196, 54, 14);
		panel.add(lblImagemFuncionario);
		
		JButton btnProcurarImagemFuncionario = new JButton("Procurar...");
		btnProcurarImagemFuncionario.setBounds(180, 225, 134, 23);
		panel.add(btnProcurarImagemFuncionario);
		
		JButton btnDefinirImagemFuncionario = new JButton("Definir Imagem");
		btnDefinirImagemFuncionario.setBounds(180, 260, 134, 23);
		panel.add(btnDefinirImagemFuncionario);
		
		JButton btnExcluirImagemFuncionario = new JButton("Excluir Imagem");
		btnExcluirImagemFuncionario.setBounds(180, 295, 134, 23);
		panel.add(btnExcluirImagemFuncionario);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 186, 479, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 350, 479, 2);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 462, 479, 2);
		panel.add(separator_2);
		
		JButton btnLimparCamposFuncionario = new JButton("Limpar Campos");
		btnLimparCamposFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaFormulario();
			}
		});
		btnLimparCamposFuncionario.setBounds(39, 475, 126, 23);
		panel.add(btnLimparCamposFuncionario);
		
		JButton btnCancelarFuncionario = new JButton("Cancelar");
		btnCancelarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnCancelarFuncionario.setBounds(225, 475, 89, 23);
		panel.add(btnCancelarFuncionario);
		
		JButton btnSalvarFuncionario = new JButton("Salvar");
		btnSalvarFuncionario.setBounds(400, 475, 89, 23);
		panel.add(btnSalvarFuncionario);
		
		JLabel lblContatoOnlineFuncionario = new JLabel("Contato Online:");
		lblContatoOnlineFuncionario.setBounds(268, 372, 89, 14);
		panel.add(lblContatoOnlineFuncionario);
		
		JLabel lblEmailFuncionario = new JLabel("Email: *");
		lblEmailFuncionario.setBounds(268, 397, 46, 14);
		panel.add(lblEmailFuncionario);
		
		JLabel lblSiteFuncionario = new JLabel("Site:");
		lblSiteFuncionario.setBounds(268, 422, 46, 14);
		panel.add(lblSiteFuncionario);
		
		txtSiteFuncionario = new JTextField();
		txtSiteFuncionario.setColumns(10);
		txtSiteFuncionario.setBounds(324, 419, 154, 20);
		panel.add(txtSiteFuncionario);
		
		txtEmailFuncionario = new JTextField();
		txtEmailFuncionario.setColumns(10);
		txtEmailFuncionario.setBounds(324, 394, 154, 20);
		panel.add(txtEmailFuncionario);
		
		JLabel lblCargo = new JLabel("Cargo na Empresa: *");
		lblCargo.setBounds(343, 201, 116, 14);
		panel.add(lblCargo);
		
		JRadioButton rdbtnCargoCorretorFuncionario = new JRadioButton("Corretor (a) de Im\u00F3veis");
		rdbtnCargoCorretorFuncionario.setBounds(343, 225, 166, 23);
		panel.add(rdbtnCargoCorretorFuncionario);
		
		JRadioButton rdbtnCargoSecretariaFuncionario = new JRadioButton("Secret\u00E1ria ");
		rdbtnCargoSecretariaFuncionario.setBounds(343, 251, 109, 23);
		panel.add(rdbtnCargoSecretariaFuncionario);
		
		JRadioButton rdbtnCargoGerenteFuncionario = new JRadioButton("Gerente");
		rdbtnCargoGerenteFuncionario.setBounds(343, 277, 109, 23);
		panel.add(rdbtnCargoGerenteFuncionario);
		
		JRadioButton rdbtnCargoConsultorFuncionario = new JRadioButton("Consultor (a)");
		rdbtnCargoConsultorFuncionario.setBounds(343, 303, 109, 23);
		panel.add(rdbtnCargoConsultorFuncionario);
		
		//Cria o Button Group
		
		ButtonGroup grupoCargo = new ButtonGroup();  
		grupoCargo.add(rdbtnCargoCorretorFuncionario);  
		grupoCargo.add(rdbtnCargoSecretariaFuncionario);
		grupoCargo.add(rdbtnCargoGerenteFuncionario);  
		grupoCargo.add(rdbtnCargoConsultorFuncionario);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 216, 118, 128);
		panel.add(scrollPane);
		
		JLabel lblSave = new JLabel("");
		lblSave.setIcon(new ImageIcon(frmCadastroFuncionario.class.getResource("/br/com/images/save.png")));
		lblSave.setBounds(365, 475, 25, 25);
		panel.add(lblSave);
		
		JLabel lblCancelar = new JLabel("");
		lblCancelar.setIcon(new ImageIcon(frmCadastroFuncionario.class.getResource("/br/com/images/delete-.png")));
		lblCancelar.setBounds(192, 475, 25, 25);
		panel.add(lblCancelar);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(Color.GRAY);
		panelLogin.setBounds(10, 355, 248, 103);
		panel.add(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel lblLoginFuncionario = new JLabel("Login: *");
		lblLoginFuncionario.setBounds(10, 16, 46, 14);
		panelLogin.add(lblLoginFuncionario);
		
		JLabel lblSenhaFuncionario = new JLabel("Senha: * ");
		lblSenhaFuncionario.setBounds(10, 41, 46, 14);
		panelLogin.add(lblSenhaFuncionario);
		
		txtLoginFuncionario = new JTextField();
		txtLoginFuncionario.setColumns(10);
		txtLoginFuncionario.setBounds(114, 13, 124, 20);
		panelLogin.add(txtLoginFuncionario);
		
		JLabel lblConfSenhaFuncionario = new JLabel("Confirmar Senha: *");
		lblConfSenhaFuncionario.setBounds(10, 66, 102, 14);
		panelLogin.add(lblConfSenhaFuncionario);
		
		passwordFieldSenhaFuncionario = new JPasswordField();
		passwordFieldSenhaFuncionario.setBounds(114, 38, 124, 20);
		panelLogin.add(passwordFieldSenhaFuncionario);
		
		passwordFieldConfSenhaFuncionario = new JPasswordField();
		passwordFieldConfSenhaFuncionario.setBounds(114, 63, 124, 20);
		panelLogin.add(passwordFieldConfSenhaFuncionario);
		
		JLabel lblClear = new JLabel("");
		lblClear.setIcon(new ImageIcon(frmCadastroFuncionario.class.getResource("/br/com/images/clear.png")));
		lblClear.setBounds(10, 475, 25, 25);
		panel.add(lblClear);
		
		JLabel lblExcluirImagem = new JLabel("");
		lblExcluirImagem.setIcon(new ImageIcon(frmCadastroFuncionario.class.getResource("/br/com/images/delete-.png")));
		lblExcluirImagem.setBounds(140, 295, 25, 25);
		panel.add(lblExcluirImagem);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(frmCadastroFuncionario.class.getResource("/br/com/images/apply-.png")));
		label.setBounds(140, 260, 25, 25);
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(frmCadastroFuncionario.class.getResource("/br/com/images/search-ico.png")));
		label_1.setBounds(136, 225, 25, 25);
		panel.add(label_1);
		
		JLabel lblRegistroFuncionario = new JLabel("ID:");
		lblRegistroFuncionario.setBounds(399, 11, 29, 14);
		panel.add(lblRegistroFuncionario);
		
		txtRegistroFuncionario = new JTextField();
		txtRegistroFuncionario.setEditable(false);
		txtRegistroFuncionario.setBounds(426, 8, 63, 20);
		panel.add(txtRegistroFuncionario);
		txtRegistroFuncionario.setColumns(10);
		
		setLocationRelativeTo(null);		
	}
	
	public void fechar(){
		this.dispose();
	}
	
	public void limpaFormulario(){
		txtNomeFuncionario.setText("");
		txtCpfFuncionario.setText("");
		txtRgFuncionario.setText("");
		txtNacionalidadeFuncionario.setText("");
		txtEnderecoFuncionario.setText("");
		txtNumeroEnderecoFuncionario.setText("");
		txtComplementoFuncionario.setText("");
		txtCepFuncionario.setText("");
		txtTelefoneResidencialFuncionario.setText("");
		txtTelefoneCelularFuncionario.setText("");
		txtLoginFuncionario.setText("");
		txtSiteFuncionario.setText("");
		txtEmailFuncionario.setText("");
		txtLoginFuncionario.setText("");
		passwordFieldSenhaFuncionario.setText("");
		passwordFieldConfSenhaFuncionario.setText("");
	}
}
