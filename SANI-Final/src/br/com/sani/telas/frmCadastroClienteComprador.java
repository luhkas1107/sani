package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.sani.bean.ClienteComprador;
import br.com.sani.dao.ClienteCompradorDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.exception.EntradaUsuarioException;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;
import br.com.sani.util.TelaUtil;
import javax.swing.border.TitledBorder;

public class frmCadastroClienteComprador extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeClienteComprador;
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
	private JFormattedTextField ftRgClienteComprador;
	private JTextField textField;
	private JTextField textField_1;

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
		setBounds(100, 100, 655, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel lblNomeClienteComprador = new JLabel("Nome: *");
		lblNomeClienteComprador.setBounds(45, 39, 40, 14);
		panel.add(lblNomeClienteComprador);
		
		//Cria o Button Group
		
		ButtonGroup grupoSexo = new ButtonGroup();  
		
		txtNomeClienteComprador = new JTextField();
		txtNomeClienteComprador.setColumns(10);
		txtNomeClienteComprador.setBounds(95, 36, 327, 20);
		panel.add(txtNomeClienteComprador);
		
		JLabel lblCpfClienteComprador = new JLabel("CPF: *");
		lblCpfClienteComprador.setBounds(53, 64, 32, 14);
		panel.add(lblCpfClienteComprador);
		
		ftCpfClienteComprador = new JFormattedTextField(Mascara.setMaskCpfInTf(ftCpfClienteComprador));
		ftCpfClienteComprador.setColumns(10);
		ftCpfClienteComprador.setBounds(95, 61, 109, 20);
		panel.add(ftCpfClienteComprador);
		
		JLabel lblEstadoCivilClienteComprador = new JLabel("Estado Civil:");
		lblEstadoCivilClienteComprador.setBounds(26, 89, 59, 14);
		panel.add(lblEstadoCivilClienteComprador);
		
		comboBoxEstadoCivil = new JComboBox();
		comboBoxEstadoCivil.setToolTipText("Solteiro (a)\r\nCasado (a)\r\nDivorciado (a)\r\nVi\u00FAvo (a)");
		comboBoxEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"Solteiro (a)", "Casado (a)", "Divorciado (a)", "Vi\u00FAvo (a)"}));
		comboBoxEstadoCivil.setBounds(95, 86, 109, 20);
		panel.add(comboBoxEstadoCivil);
		
		JLabel lblRgClienteComprador = new JLabel("RG:");
		lblRgClienteComprador.setBounds(298, 64, 18, 14);
		panel.add(lblRgClienteComprador);
		
		ftRgClienteComprador = new JFormattedTextField(Mascara.setMaskRgInTf(ftRgClienteComprador));
		ftRgClienteComprador.setColumns(10);
		ftRgClienteComprador.setBounds(326, 61, 96, 20);
		panel.add(ftRgClienteComprador);
		
		JLabel lblNacionalidadeClienteComprador = new JLabel("Nacionalidade:");
		lblNacionalidadeClienteComprador.setBounds(246, 89, 70, 14);
		panel.add(lblNacionalidadeClienteComprador);
		
		txtNacionalidadeClienteComprador = new JTextField();
		txtNacionalidadeClienteComprador.setColumns(10);
		txtNacionalidadeClienteComprador.setBounds(326, 86, 96, 20);
		panel.add(txtNacionalidadeClienteComprador);
		
		JLabel lblEnderecoClienteComprador = new JLabel("Endere\u00E7o: *");
		lblEnderecoClienteComprador.setBounds(26, 218, 59, 14);
		panel.add(lblEnderecoClienteComprador);
		
		txtEnderecoClienteComprador = new JTextField();
		txtEnderecoClienteComprador.setColumns(10);
		txtEnderecoClienteComprador.setBounds(95, 265, 96, 20);
		panel.add(txtEnderecoClienteComprador);
		
		JLabel lblNumeroClienteComprador = new JLabel("N\u00FAmero: *");
		lblNumeroClienteComprador.setBounds(257, 193, 56, 14);
		panel.add(lblNumeroClienteComprador);
		
		txtNumeroClienteComprador = new JTextField();
		txtNumeroClienteComprador.setColumns(10);
		txtNumeroClienteComprador.setBounds(326, 190, 46, 20);
		panel.add(txtNumeroClienteComprador);
		
		JLabel lblComplementoClienteComprador = new JLabel("Complemento:");
		lblComplementoClienteComprador.setBounds(15, 243, 70, 14);
		panel.add(lblComplementoClienteComprador);
		
		txtComplementoClienteComprador = new JTextField();
		txtComplementoClienteComprador.setColumns(10);
		txtComplementoClienteComprador.setBounds(95, 215, 229, 20);
		panel.add(txtComplementoClienteComprador);
		
		JLabel lblCepClienteComprador = new JLabel("CEP: *");
		lblCepClienteComprador.setBounds(53, 193, 32, 14);
		panel.add(lblCepClienteComprador);
		
		ftCepClienteComprador = new JFormattedTextField(Mascara.setMaskCepInTf(ftCepClienteComprador));
		ftCepClienteComprador.setColumns(10);
		ftCepClienteComprador.setBounds(95, 190, 86, 20);
		panel.add(ftCepClienteComprador);
		
		JLabel lblTelefoneResidencialClienteComprador = new JLabel("Telefone Residencial:");
		lblTelefoneResidencialClienteComprador.setBounds(207, 268, 106, 14);
		panel.add(lblTelefoneResidencialClienteComprador);
		
		ftTelefoneResidencialClienteComprador = new JFormattedTextField(Mascara.setMaskTelefoneInTf(ftTelefoneResidencialClienteComprador));
		ftTelefoneResidencialClienteComprador.setColumns(10);
		ftTelefoneResidencialClienteComprador.setBounds(326, 265, 96, 20);
		panel.add(ftTelefoneResidencialClienteComprador);
		
		JLabel lblTelefoneCelularClienteComprador = new JLabel("Telefone Celular: *");
		lblTelefoneCelularClienteComprador.setBounds(15, 296, 118, 14);
		panel.add(lblTelefoneCelularClienteComprador);
		
		ftTelefoneCelularClienteComprador = new JFormattedTextField(Mascara.setMaskCelularInTf(ftTelefoneCelularClienteComprador));
		ftTelefoneCelularClienteComprador.setColumns(10);
		ftTelefoneCelularClienteComprador.setBounds(503, 351, 106, 20);
		panel.add(ftTelefoneCelularClienteComprador);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 171, 583, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(26, 407, 479, 2);
		panel.add(separator_1);
		
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
		btnSalvarClienteComprador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvarClienteComprador();
			}
		});
		btnSalvarClienteComprador.setBounds(388, 480, 101, 23);
		panel.add(btnSalvarClienteComprador);
		
		JLabel lblRendaClienteComprador = new JLabel("Renda R$:*");
		lblRendaClienteComprador.setBounds(257, 133, 59, 14);
		panel.add(lblRendaClienteComprador);
		
		txtRendaClienteComprador = new JTextField();
		txtRendaClienteComprador.setBounds(326, 130, 96, 20);
		panel.add(txtRendaClienteComprador);
		txtRendaClienteComprador.setColumns(10);
		
		JLabel lblContatoClienteComprador = new JLabel("Contato:");
		lblContatoClienteComprador.setBounds(26, 268, 46, 14);
		panel.add(lblContatoClienteComprador);
		
		JLabel lblEmailClienteComprador = new JLabel("Email: *");
		lblEmailClienteComprador.setBounds(45, 354, 40, 14);
		panel.add(lblEmailClienteComprador);
		
		txtEmailClienteComprador = new JTextField();
		txtEmailClienteComprador.setBounds(288, 351, 154, 20);
		panel.add(txtEmailClienteComprador);
		txtEmailClienteComprador.setColumns(10);
		
		JLabel lblSiteClienteComprador = new JLabel("Site:");
		lblSiteClienteComprador.setBounds(53, 332, 32, 14);
		panel.add(lblSiteClienteComprador);
		
		txtSiteClienteComprador = new JTextField();
		txtSiteClienteComprador.setBounds(95, 329, 154, 20);
		panel.add(txtSiteClienteComprador);
		txtSiteClienteComprador.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Sexo*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(448, 36, 161, 78);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton rdbtnFemininoClienteComprador = new JRadioButton("Feminino");
		rdbtnFemininoClienteComprador.setBounds(20, 44, 109, 23);
		panel_1.add(rdbtnFemininoClienteComprador);
		grupoSexo.add(rdbtnFemininoClienteComprador);
		
		rdbtnMasculinoClienteComprador = new JRadioButton("Masculino");
		rdbtnMasculinoClienteComprador.setBounds(20, 18, 109, 23);
		panel_1.add(rdbtnMasculinoClienteComprador);
		rdbtnMasculinoClienteComprador.setSelected(true);
		grupoSexo.add(rdbtnMasculinoClienteComprador);  
		
		JLabel lblProfisso = new JLabel("Profiss\u00E3o:*");
		lblProfisso.setBounds(26, 133, 59, 14);
		panel.add(lblProfisso);
		
		textField = new JTextField();
		textField.setBounds(95, 130, 109, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(95, 240, 65, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		setLocationRelativeTo(null);		
	}
	
	public void fechar(){
		this.dispose();
	}
	
	public void limpaFormulario(){
		txtNomeClienteComprador.setText("");
		ftCpfClienteComprador.setText("");
		ftRgClienteComprador.setText("");
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
		CliComp.setRg(TelaUtil.getRg(ftRgClienteComprador, true));
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
	
	private void salvarClienteComprador(){
		try{
			ClienteComprador CliComp = getBean();
			new ClienteCompradorDAO().inserirClienteComprador(CliComp);
			limpaFormulario();
			JOptionPane.showMessageDialog(this, "Cadastro efetuado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
		}catch(DAOException e){
			e.printStackTrace();
		} catch (EntradaUsuarioException e) {
			e.printStackTrace();
		}
	}
}
