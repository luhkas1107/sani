package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
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
import java.text.ParseException;

import javax.swing.ImageIcon;

import br.com.sani.bean.ClienteComprador;
import br.com.sani.exception.EntradaUsuarioException;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;
import br.com.sani.util.TelaUtil;

public class frmCadastroClienteComprador extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeClienteComprador;
	private JTextField txtRgClienteComprador;
	private JTextField txtNacionalidadeClienteComprador;
	private JTextField txtEnderecoClienteComprador;
	private JTextField txtNumeroClienteComprador;
	private JTextField txtComplementoClienteComprador;
	
	private JTextField txtRendaClienteComprador;
	private JTextField txtSiteClienteComprador;
	private JTextField txtEmailClienteComprador;
	private JRadioButton rdbtnMasculinoClienteComprador;
	private JFormattedTextField ftCpfClienteComprador;
	
	private String[] estadoCivil = new String[]{"S", "C", "D", "V"};
	private JComboBox comboBoxEstadoCivil;
	private JFormattedTextField ftCepClienteComprador;
	private JFormattedTextField ftTelefoneResidencialClienteComprador;
	private JFormattedTextField ftTelefoneCelularClienteComprador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new frmCadastroClienteComprador();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public frmCadastroClienteComprador(){
		try {
			montarComponentes();
			
			setLocationRelativeTo(null);
			setVisible(true);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public void montarComponentes() throws ParseException {
		SwingUtil.lookWindows(this);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmCadastroClienteComprador.class.getResource("/br/com/images/home_badge.png")));
		setTitle("Cadastro Cliente Comprador");
		setBounds(100, 100, 530, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel lblNomeClienteComprador = new JLabel("Nome: *");
		lblNomeClienteComprador.setBounds(10, 11, 46, 14);
		panel.add(lblNomeClienteComprador);
		
		JLabel lblSexoClienteComprador = new JLabel("Sexo: *");
		lblSexoClienteComprador.setBounds(10, 36, 46, 14);
		panel.add(lblSexoClienteComprador);
		
		rdbtnMasculinoClienteComprador = new JRadioButton("Masculino");
		rdbtnMasculinoClienteComprador.setSelected(true);
		rdbtnMasculinoClienteComprador.setBounds(56, 32, 109, 23);
		panel.add(rdbtnMasculinoClienteComprador);
		
		JRadioButton rdbtnFemininoClienteComprador = new JRadioButton("Feminino");
		rdbtnFemininoClienteComprador.setBounds(172, 32, 109, 23);
		panel.add(rdbtnFemininoClienteComprador);
		
		//Cria o Button Group
		
		ButtonGroup grupoSexo = new ButtonGroup();  
        grupoSexo.add(rdbtnMasculinoClienteComprador);  
        grupoSexo.add(rdbtnFemininoClienteComprador);
		
		txtNomeClienteComprador = new JTextField();
		txtNomeClienteComprador.setColumns(10);
		txtNomeClienteComprador.setBounds(56, 8, 327, 20);
		panel.add(txtNomeClienteComprador);
		
		JLabel lblCpfClienteComprador = new JLabel("CPF: *");
		lblCpfClienteComprador.setBounds(10, 61, 46, 14);
		panel.add(lblCpfClienteComprador);
		
		ftCpfClienteComprador = new JFormattedTextField(Mascara.setMaskCpfInTf(ftCpfClienteComprador));
		ftCpfClienteComprador.setColumns(10);
		ftCpfClienteComprador.setBounds(57, 58, 109, 20);
		panel.add(ftCpfClienteComprador);
		
		JLabel lblEstadoCivilClienteComprador = new JLabel("Estado Civil:");
		lblEstadoCivilClienteComprador.setBounds(10, 86, 81, 14);
		panel.add(lblEstadoCivilClienteComprador);
		
		comboBoxEstadoCivil = new JComboBox();
		comboBoxEstadoCivil.setToolTipText("Solteiro (a)\r\nCasado (a)\r\nDivorciado (a)\r\nVi\u00FAvo (a)");
		comboBoxEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"Solteiro (a)", "Casado (a)", "Divorciado (a)", "Vi\u00FAvo (a)"}));
		comboBoxEstadoCivil.setBounds(80, 83, 137, 20);
		panel.add(comboBoxEstadoCivil);
		
		JLabel lblRgClienteComprador = new JLabel("RG:");
		lblRgClienteComprador.setBounds(182, 61, 46, 14);
		panel.add(lblRgClienteComprador);
		
		txtRgClienteComprador = new JTextField();
		txtRgClienteComprador.setColumns(10);
		txtRgClienteComprador.setBounds(215, 58, 109, 20);
		panel.add(txtRgClienteComprador);
		
		JLabel lblNacionalidadeClienteComprador = new JLabel("Nacionalidade:");
		lblNacionalidadeClienteComprador.setBounds(240, 86, 98, 14);
		panel.add(lblNacionalidadeClienteComprador);
		
		txtNacionalidadeClienteComprador = new JTextField();
		txtNacionalidadeClienteComprador.setColumns(10);
		txtNacionalidadeClienteComprador.setBounds(335, 83, 116, 20);
		panel.add(txtNacionalidadeClienteComprador);
		
		JLabel lblEnderecoClienteComprador = new JLabel("Endere\u00E7o: *");
		lblEnderecoClienteComprador.setBounds(10, 111, 63, 14);
		panel.add(lblEnderecoClienteComprador);
		
		txtEnderecoClienteComprador = new JTextField();
		txtEnderecoClienteComprador.setColumns(10);
		txtEnderecoClienteComprador.setBounds(80, 108, 244, 20);
		panel.add(txtEnderecoClienteComprador);
		
		JLabel lblNumeroClienteComprador = new JLabel("N\u00FAmero: *");
		lblNumeroClienteComprador.setBounds(345, 111, 64, 14);
		panel.add(lblNumeroClienteComprador);
		
		txtNumeroClienteComprador = new JTextField();
		txtNumeroClienteComprador.setColumns(10);
		txtNumeroClienteComprador.setBounds(412, 108, 63, 20);
		panel.add(txtNumeroClienteComprador);
		
		JLabel lblComplementoClienteComprador = new JLabel("Complemento:");
		lblComplementoClienteComprador.setBounds(10, 136, 96, 14);
		panel.add(lblComplementoClienteComprador);
		
		txtComplementoClienteComprador = new JTextField();
		txtComplementoClienteComprador.setColumns(10);
		txtComplementoClienteComprador.setBounds(101, 133, 102, 20);
		panel.add(txtComplementoClienteComprador);
		
		JLabel lblCepClienteComprador = new JLabel("CEP: *");
		lblCepClienteComprador.setBounds(225, 136, 46, 14);
		panel.add(lblCepClienteComprador);
		
		ftCepClienteComprador = new JFormattedTextField(Mascara.setMaskCepInTf(ftCepClienteComprador));
		ftCepClienteComprador.setColumns(10);
		ftCepClienteComprador.setBounds(265, 133, 86, 20);
		panel.add(ftCepClienteComprador);
		
		JLabel lblTelefoneResidencialClienteComprador = new JLabel("Telefone Residencial:");
		lblTelefoneResidencialClienteComprador.setBounds(10, 244, 132, 14);
		panel.add(lblTelefoneResidencialClienteComprador);
		
		ftTelefoneResidencialClienteComprador = new JFormattedTextField(Mascara.setMaskTelefoneInTf(ftTelefoneResidencialClienteComprador));
		ftTelefoneResidencialClienteComprador.setColumns(10);
		ftTelefoneResidencialClienteComprador.setBounds(138, 241, 106, 20);
		panel.add(ftTelefoneResidencialClienteComprador);
		
		JLabel lblTelefoneCelularClienteComprador = new JLabel("Telefone Celular: *");
		lblTelefoneCelularClienteComprador.setBounds(265, 244, 118, 14);
		panel.add(lblTelefoneCelularClienteComprador);
		
		ftTelefoneCelularClienteComprador = new JFormattedTextField(Mascara.setMaskCelularInTf(ftTelefoneCelularClienteComprador));
		ftTelefoneCelularClienteComprador.setColumns(10);
		ftTelefoneCelularClienteComprador.setBounds(369, 241, 106, 20);
		panel.add(ftTelefoneCelularClienteComprador);
		
		JLabel lblImagemClienteComprador = new JLabel("Imagem:");
		lblImagemClienteComprador.setBounds(10, 282, 54, 14);
		panel.add(lblImagemClienteComprador);
		
		JButton btnProcurarImagemClienteComprador = new JButton("Procurar...");
		btnProcurarImagemClienteComprador.setBounds(225, 320, 134, 23);
		panel.add(btnProcurarImagemClienteComprador);
		
		JButton btnDefinirImagemClienteComprador = new JButton("Definir Imagem");
		btnDefinirImagemClienteComprador.setBounds(225, 355, 134, 23);
		panel.add(btnDefinirImagemClienteComprador);
		
		JButton btnExcluirImagemClienteComprador = new JButton("Excluir Imagem");
		btnExcluirImagemClienteComprador.setBounds(225, 390, 134, 23);
		panel.add(btnExcluirImagemClienteComprador);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 174, 479, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 269, 479, 2);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 449, 479, 2);
		panel.add(separator_2);
		
		JButton btnLimparCamposClienteComprador = new JButton("Limpar Campos");
		btnLimparCamposClienteComprador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaFormulario();
			}
		});
		btnLimparCamposClienteComprador.setBounds(39, 480, 126, 23);
		panel.add(btnLimparCamposClienteComprador);
		
		JButton btnCancelarClienteComprador = new JButton("Cancelar");
		btnCancelarClienteComprador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnCancelarClienteComprador.setBounds(235, 480, 89, 23);
		panel.add(btnCancelarClienteComprador);
		
		JButton btnSalvarClienteComprador = new JButton("Cadastrar");
		btnSalvarClienteComprador.setBounds(388, 480, 101, 23);
		panel.add(btnSalvarClienteComprador);
		
		JLabel lblRendaClienteComprador = new JLabel("Renda     R$: *");
		lblRendaClienteComprador.setBounds(337, 61, 81, 14);
		panel.add(lblRendaClienteComprador);
		
		txtRendaClienteComprador = new JTextField();
		txtRendaClienteComprador.setBounds(418, 58, 86, 20);
		panel.add(txtRendaClienteComprador);
		txtRendaClienteComprador.setColumns(10);
		
		JLabel lblContatoClienteComprador = new JLabel("Contato:");
		lblContatoClienteComprador.setBounds(10, 187, 63, 14);
		panel.add(lblContatoClienteComprador);
		
		JLabel lblEmailClienteComprador = new JLabel("Email: *");
		lblEmailClienteComprador.setBounds(10, 216, 46, 14);
		panel.add(lblEmailClienteComprador);
		
		txtEmailClienteComprador = new JTextField();
		txtEmailClienteComprador.setBounds(90, 213, 154, 20);
		panel.add(txtEmailClienteComprador);
		txtEmailClienteComprador.setColumns(10);
		
		JLabel lblSiteClienteComprador = new JLabel("Site:");
		lblSiteClienteComprador.setBounds(265, 216, 46, 14);
		panel.add(lblSiteClienteComprador);
		
		txtSiteClienteComprador = new JTextField();
		txtSiteClienteComprador.setBounds(321, 213, 154, 20);
		panel.add(txtSiteClienteComprador);
		txtSiteClienteComprador.setColumns(10);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(69, 300, 115, 143);
		panel.add(scrollPane);
		
		JLabel lblSave = new JLabel("");
		lblSave.setIcon(new ImageIcon(frmCadastroClienteComprador.class.getResource("/br/com/images/save.png")));
		lblSave.setBounds(358, 480, 25, 25);
		panel.add(lblSave);
		
		JLabel lblCancelar = new JLabel("");
		lblCancelar.setIcon(new ImageIcon(frmCadastroClienteComprador.class.getResource("/br/com/images/delete-.png")));
		lblCancelar.setBounds(203, 480, 25, 25);
		panel.add(lblCancelar);
		
		JLabel lblApagarImagem = new JLabel("");
		lblApagarImagem.setIcon(new ImageIcon(frmCadastroClienteComprador.class.getResource("/br/com/images/delete-.png")));
		lblApagarImagem.setBounds(190, 390, 25, 25);
		panel.add(lblApagarImagem);
		
		JLabel lblApply = new JLabel("");
		lblApply.setIcon(new ImageIcon(frmCadastroClienteComprador.class.getResource("/br/com/images/apply-.png")));
		lblApply.setBounds(192, 355, 25, 25);
		panel.add(lblApply);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(frmCadastroClienteComprador.class.getResource("/br/com/images/search-ico.png")));
		lblSearch.setBounds(192, 320, 25, 25);
		panel.add(lblSearch);
		
		JLabel lblLimpar = new JLabel("");
		lblLimpar.setIcon(new ImageIcon(frmCadastroClienteComprador.class.getResource("/br/com/images/clear.png")));
		lblLimpar.setBounds(10, 480, 25, 25);
		panel.add(lblLimpar);
		
		setLocationRelativeTo(null);		
	}
	
	public void fechar(){
		this.dispose();
	}
	
	public void limpaFormulario(){
		txtNomeClienteComprador.setText("");
		ftCpfClienteComprador.setText("");
		txtRgClienteComprador.setText("");
		txtNacionalidadeClienteComprador.setText("");
		txtEnderecoClienteComprador.setText("");
		txtNumeroClienteComprador.setText("");
		txtComplementoClienteComprador.setText("");
		ftCepClienteComprador.setText("");
		ftTelefoneResidencialClienteComprador.setText("");
		ftTelefoneCelularClienteComprador.setText("");
		txtRendaClienteComprador.setText("");
		txtEmailClienteComprador.setText("");
		txtSiteClienteComprador.setText("");
	}
	
	public ClienteComprador getBean() throws EntradaUsuarioException{
		ClienteComprador CliComp = new ClienteComprador();
		CliComp.setNome(TelaUtil.getCampoObrigatorio(txtNomeClienteComprador, true));
		CliComp.setSexo(TelaUtil.getCharSexo(rdbtnMasculinoClienteComprador));
		CliComp.setCpf(TelaUtil.getCpf(ftCpfClienteComprador, true));
		CliComp.setRg(TelaUtil.getCampoObrigatorio(txtRgClienteComprador, false));
		CliComp.setRenda(TelaUtil.getCampoObrigatorio(txtRendaClienteComprador, true));
		CliComp.setEstadoCivil(estadoCivil[comboBoxEstadoCivil.getSelectedIndex()]);
		CliComp.setNacionalidade(TelaUtil.getCampoObrigatorio(txtNacionalidadeClienteComprador, false));
		CliComp.setEndereco(TelaUtil.getCampoObrigatorio(txtEnderecoClienteComprador, true));
		CliComp.setNumeroEndereco(TelaUtil.getCampoObrigatorio(txtNumeroClienteComprador, true));
		CliComp.setComplementoEndereco(TelaUtil.getCampoObrigatorio(txtComplementoClienteComprador, true));
		CliComp.setCep(TelaUtil.getCep(ftCepClienteComprador, true));
		CliComp.setEmailPessoal(TelaUtil.getEmail(txtEmailClienteComprador));
		CliComp.setSiteClienteComprador(TelaUtil.getCampoObrigatorio(txtSiteClienteComprador, false));
		CliComp.setTelefoneResidencial(TelaUtil.getTelefone(ftTelefoneResidencialClienteComprador, false));
		CliComp.setTelefoneCelular(TelaUtil.getCelular(ftTelefoneCelularClienteComprador, true));
		
		return CliComp;
	}
}
