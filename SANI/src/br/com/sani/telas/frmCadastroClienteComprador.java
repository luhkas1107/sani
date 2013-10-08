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
import javax.swing.UIManager;

public class frmCadastroClienteComprador extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeClienteComprador;
	private JTextField txtEnderecoClienteComprador;
	private JTextField txtNumeroClienteComprador;
	private JTextField txtComplementoClienteComprador;
	
	private JTextField txtRendaClienteComprador;
	private JTextField txtEmailClienteComprador;
	private JRadioButton rdbtnMasculinoClienteComprador;
	private JFormattedTextField ftCpfClienteComprador;
	
	private String[] estadoCivil = new String[]{"S", "C", "D", "V"};
	private JComboBox comboBoxEstadoCivil;
	private JFormattedTextField ftCepClienteComprador;
	private JFormattedTextField ftTelefoneResidencialClienteComprador;
	private JFormattedTextField ftTelefoneCelularClienteComprador;
	private JFormattedTextField ftRgClienteComprador;
	private JTextField txtProfissao;

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
		setBounds(100, 100, 653, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
				JPanel panelSexo = new JPanel();
		panelSexo.setBounds(468, 11, 161, 92);
		panelSexo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sexo*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panelSexo);
		panelSexo.setLayout(null);
		
		ButtonGroup grupoSexo = new ButtonGroup();
		
		rdbtnMasculinoClienteComprador = new JRadioButton("Masculino");
		rdbtnMasculinoClienteComprador.setBounds(22, 22, 109, 23);
		panelSexo.add(rdbtnMasculinoClienteComprador);
		rdbtnMasculinoClienteComprador.setSelected(true);
		
		JRadioButton rdbtnFemininoClienteComprador = new JRadioButton("Feminino");
		rdbtnFemininoClienteComprador.setBounds(22, 48, 109, 23);
		panelSexo.add(rdbtnFemininoClienteComprador);
		
		grupoSexo.add(rdbtnMasculinoClienteComprador);  
		grupoSexo.add(rdbtnFemininoClienteComprador);
		
		//Cria o Button Group
		
		
		
		JButton btnLimparCamposClienteComprador = new JButton("Limpar Campos");
		btnLimparCamposClienteComprador.setBounds(503, 209, 126, 23);
		btnLimparCamposClienteComprador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaFormulario();
			}
		});
		panel.add(btnLimparCamposClienteComprador);
		
		JButton btnCancelarClienteComprador = new JButton("Cancelar");
		btnCancelarClienteComprador.setBounds(503, 261, 126, 23);
		btnCancelarClienteComprador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		panel.add(btnCancelarClienteComprador);
		
		JButton btnSalvarClienteComprador = new JButton("Cadastrar");
		btnSalvarClienteComprador.setBounds(503, 149, 126, 23);
		btnSalvarClienteComprador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//salvarClienteComprador();
			}
		});
		panel.add(btnSalvarClienteComprador);
		
		JPanel panelEndereco = new JPanel();
		panelEndereco.setBounds(15, 183, 435, 139);
		panelEndereco.setBorder(new TitledBorder(null, "Endere\u00E7o*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panelEndereco);
		panelEndereco.setLayout(null);
		
		txtEnderecoClienteComprador = new JTextField();
		txtEnderecoClienteComprador.setEnabled(false);
		txtEnderecoClienteComprador.setBounds(83, 56, 273, 20);
		panelEndereco.add(txtEnderecoClienteComprador);
		txtEnderecoClienteComprador.setColumns(10);
		
		ftCepClienteComprador = new JFormattedTextField(Mascara.setMaskCepInTf(ftCepClienteComprador));
		ftCepClienteComprador.setBounds(83, 27, 86, 20);
		panelEndereco.add(ftCepClienteComprador);
		ftCepClienteComprador.setColumns(10);
		
		txtComplementoClienteComprador = new JTextField();
		txtComplementoClienteComprador.setBounds(258, 87, 86, 20);
		panelEndereco.add(txtComplementoClienteComprador);
		txtComplementoClienteComprador.setColumns(10);
		
		JLabel lblNumeroClienteComprador = new JLabel("N\u00FAmero:");
		lblNumeroClienteComprador.setBounds(21, 90, 50, 14);
		panelEndereco.add(lblNumeroClienteComprador);
		
		JLabel lblCepClienteComprador = new JLabel("CEP:");
		lblCepClienteComprador.setBounds(21, 30, 32, 14);
		panelEndereco.add(lblCepClienteComprador);
		
		JLabel lblEnderecoClienteComprador = new JLabel("Endere\u00E7o:\r\n");
		lblEnderecoClienteComprador.setBounds(21, 60, 58, 14);
		panelEndereco.add(lblEnderecoClienteComprador);
		
		JLabel lblComplementoClienteComprador = new JLabel("Complemento:");
		lblComplementoClienteComprador.setBounds(179, 90, 69, 14);
		panelEndereco.add(lblComplementoClienteComprador);
		
		txtNumeroClienteComprador = new JTextField();
		txtNumeroClienteComprador.setBounds(83, 87, 86, 20);
		panelEndereco.add(txtNumeroClienteComprador);
		txtNumeroClienteComprador.setColumns(10);
		
		JPanel panelContato = new JPanel();
		panelContato.setBounds(15, 333, 273, 139);
		panelContato.setBorder(new TitledBorder(null, "Contato*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panelContato);
		panelContato.setLayout(null);
		
		ftTelefoneCelularClienteComprador = new JFormattedTextField(Mascara.setMaskCelularInTf(ftTelefoneCelularClienteComprador));
		ftTelefoneCelularClienteComprador.setBounds(136, 29, 86, 20);
		panelContato.add(ftTelefoneCelularClienteComprador);
		ftTelefoneCelularClienteComprador.setColumns(10);
		
		JLabel lblTelefoneResidencialClienteComprador = new JLabel("Telefone Residencial:");
		lblTelefoneResidencialClienteComprador.setBounds(22, 60, 102, 14);
		panelContato.add(lblTelefoneResidencialClienteComprador);
		
		JLabel lblTelefoneCelularClienteComprador = new JLabel("Telefone Celular: *");
		lblTelefoneCelularClienteComprador.setBounds(22, 30, 91, 14);
		panelContato.add(lblTelefoneCelularClienteComprador);
		
		JLabel lblEmailClienteComprador = new JLabel("Email: *");
		lblEmailClienteComprador.setBounds(22, 90, 37, 14);
		panelContato.add(lblEmailClienteComprador);
		
		txtEmailClienteComprador = new JTextField();
		txtEmailClienteComprador.setBounds(74, 87, 148, 20);
		panelContato.add(txtEmailClienteComprador);
		txtEmailClienteComprador.setColumns(10);
		
		ftTelefoneResidencialClienteComprador = new JFormattedTextField(Mascara.setMaskTelefoneInTf(ftTelefoneResidencialClienteComprador));
		ftTelefoneResidencialClienteComprador.setBounds(136, 54, 86, 20);
		panelContato.add(ftTelefoneResidencialClienteComprador);
		ftTelefoneResidencialClienteComprador.setColumns(10);
		
		JPanel ppanelInfo = new JPanel();
		ppanelInfo.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es B\u00E1sicas*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ppanelInfo.setBounds(15, 11, 435, 161);
		panel.add(ppanelInfo);
		ppanelInfo.setLayout(null);
		
		txtRendaClienteComprador = new JTextField();
		txtRendaClienteComprador.setBounds(329, 117, 96, 20);
		ppanelInfo.add(txtRendaClienteComprador);
		txtRendaClienteComprador.setColumns(10);
		
		JLabel lblRendaClienteComprador = new JLabel("Renda R$:");
		lblRendaClienteComprador.setBounds(260, 120, 59, 14);
		ppanelInfo.add(lblRendaClienteComprador);
		
		txtProfissao = new JTextField();
		txtProfissao.setBounds(90, 117, 109, 20);
		ppanelInfo.add(txtProfissao);
		txtProfissao.setColumns(10);
		
		JLabel lblProfisso = new JLabel("Profiss\u00E3o:");
		lblProfisso.setBounds(21, 120, 59, 14);
		ppanelInfo.add(lblProfisso);
		
		JLabel lblEstadoCivilClienteComprador = new JLabel("Estado Civil:");
		lblEstadoCivilClienteComprador.setBounds(21, 90, 59, 14);
		ppanelInfo.add(lblEstadoCivilClienteComprador);
		
		JLabel lblCpfClienteComprador = new JLabel("CPF:");
		lblCpfClienteComprador.setBounds(21, 60, 32, 14);
		ppanelInfo.add(lblCpfClienteComprador);
		
		JLabel lblNomeClienteComprador = new JLabel("Nome:");
		lblNomeClienteComprador.setBounds(21, 30, 40, 14);
		ppanelInfo.add(lblNomeClienteComprador);
		
		comboBoxEstadoCivil = new JComboBox();
		comboBoxEstadoCivil.setBounds(90, 87, 109, 20);
		ppanelInfo.add(comboBoxEstadoCivil);
		comboBoxEstadoCivil.setToolTipText("Solteiro (a)\r\nCasado (a)\r\nDivorciado (a)\r\nVi\u00FAvo (a)");
		comboBoxEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"Solteiro (a)", "Casado (a)", "Divorciado (a)", "Vi\u00FAvo (a)"}));
		
		ftCpfClienteComprador = new JFormattedTextField(Mascara.setMaskCpfInTf(ftCpfClienteComprador));
		ftCpfClienteComprador.setBounds(90, 57, 109, 20);
		ppanelInfo.add(ftCpfClienteComprador);
		ftCpfClienteComprador.setColumns(10);
		
		JLabel lblRgClienteComprador = new JLabel("RG:");
		lblRgClienteComprador.setBounds(286, 60, 18, 14);
		ppanelInfo.add(lblRgClienteComprador);
		
		ftRgClienteComprador = new JFormattedTextField(Mascara.setMaskRgInTf(ftRgClienteComprador));
		ftRgClienteComprador.setBounds(329, 57, 96, 20);
		ppanelInfo.add(ftRgClienteComprador);
		ftRgClienteComprador.setColumns(10);
		
		txtNomeClienteComprador = new JTextField();
		txtNomeClienteComprador.setBounds(90, 27, 327, 20);
		ppanelInfo.add(txtNomeClienteComprador);
		txtNomeClienteComprador.setColumns(10);
		
		setLocationRelativeTo(null);		
	}
	
	public void fechar(){
		this.dispose();
	}
	
	public void limpaFormulario(){
		txtNomeClienteComprador.setText("");
		ftCpfClienteComprador.setText("");
		ftRgClienteComprador.setText("");
		//txtNacionalidadeClienteComprador.setText("");
		txtEnderecoClienteComprador.setText("");
		txtNumeroClienteComprador.setText("");
		txtComplementoClienteComprador.setText("");
		ftCepClienteComprador.setText("");
		ftTelefoneResidencialClienteComprador.setText("");
		ftTelefoneCelularClienteComprador.setText("");
		txtRendaClienteComprador.setText("");
		txtEmailClienteComprador.setText("");
		//txtSiteClienteComprador.setText("");
	}
	
	public ClienteComprador getBean() throws EntradaUsuarioException{
		ClienteComprador CliComp = new ClienteComprador();
		CliComp.setNome(TelaUtil.getCampoObrigatorio(txtNomeClienteComprador, true));
		CliComp.setSexo(TelaUtil.getCharSexo(rdbtnMasculinoClienteComprador));
		CliComp.setCpf(TelaUtil.getCpf(ftCpfClienteComprador, true));
		CliComp.setRg(TelaUtil.getRg(ftRgClienteComprador, true));
		CliComp.setRenda(TelaUtil.getCampoObrigatorio(txtRendaClienteComprador, true));
		CliComp.setEstadoCivil(estadoCivil[comboBoxEstadoCivil.getSelectedIndex()]);
		//CliComp.setNacionalidade(TelaUtil.getCampoObrigatorio(txtNacionalidadeClienteComprador, false));
		CliComp.setEndereco(TelaUtil.getCampoObrigatorio(txtEnderecoClienteComprador, true));
		CliComp.setNumeroEndereco(TelaUtil.getCampoObrigatorio(txtNumeroClienteComprador, true));
		CliComp.setComplementoEndereco(TelaUtil.getCampoObrigatorio(txtComplementoClienteComprador, true));
		CliComp.setCep(TelaUtil.getCep(ftCepClienteComprador, true));
		CliComp.setEmailPessoal(TelaUtil.getEmail(txtEmailClienteComprador));
		//CliComp.setSiteClienteComprador(TelaUtil.getCampoObrigatorio(txtSiteClienteComprador, false));
		CliComp.setTelefoneResidencial(TelaUtil.getTelefone(ftTelefoneResidencialClienteComprador, false));
		CliComp.setTelefoneCelular(TelaUtil.getCelular(ftTelefoneCelularClienteComprador, true));
		
		return CliComp;
	}
}
