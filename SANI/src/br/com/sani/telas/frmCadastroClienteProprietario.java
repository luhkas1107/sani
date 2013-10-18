package br.com.sani.telas;

import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import br.com.sani.bean.ClienteProprietario;
import br.com.sani.bean.ClienteProprietarioFisica;
import br.com.sani.dao.ClienteProprietarioFisicaDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.exception.EntradaUsuarioException;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;
import br.com.sani.util.TelaUtil;

public class frmCadastroClienteProprietario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeClienteProprietario;
	
	private JFormattedTextField ftCpfClienteProprietario;
	private JFormattedTextField ftRgClienteProprietario;
	private JTextField txtNacionalidadeClienteProprietario;
	private JTextField txtEnderecoClienteProprietario;
	private JTextField txtNumeroClienteProprietario;
	private JTextField txtComplementoClienteProprietario;
	private JFormattedTextField ftCepClienteProprietario;
	private JFormattedTextField ftTelefoneResidencialClienteProprietario;
	private JFormattedTextField ftTelefoneCelularClienteProprietario;
	private JFormattedTextField ftEmailClienteProprietario;
	private JTextField txtSiteClienteProprietario;
	
	private JRadioButton rdbtnMasculinoCadastroClienteProprietario;
	private int modo = 0;
	private ClienteProprietarioFisica cadastro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCadastroClienteProprietario frame = new frmCadastroClienteProprietario();
					frame.setVisible(true);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Throwable 
	 */
	public frmCadastroClienteProprietario() throws Throwable {
		SwingUtil.lookWindows(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmCadastroClienteProprietario.class.getResource("/br/com/images/home_badge.png")));
		setTitle("Cadastro Cliente Propriet\u00E1rio");
		setResizable(false);
		setBounds(100, 100, 530, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 524, 521);
		contentPane.add(panel);
		
		JLabel lblNomeCadastroClienteProprietario = new JLabel("Nome: *");
		lblNomeCadastroClienteProprietario.setBounds(10, 11, 46, 14);
		panel.add(lblNomeCadastroClienteProprietario);
		
		JLabel lblSexoCadastroClienteProprietario = new JLabel("Sexo: * ");
		lblSexoCadastroClienteProprietario.setBounds(10, 36, 46, 14);
		panel.add(lblSexoCadastroClienteProprietario);
		
		JRadioButton rdbtnMasculinoCadastroClienteProprietario = new JRadioButton("Masculino");
		rdbtnMasculinoCadastroClienteProprietario.setBounds(56, 32, 109, 23);
		panel.add(rdbtnMasculinoCadastroClienteProprietario);
		
		JRadioButton rdbtnFemininoCadastroClienteProprietario = new JRadioButton("Feminino");
		rdbtnFemininoCadastroClienteProprietario.setBounds(172, 32, 109, 23);
		panel.add(rdbtnFemininoCadastroClienteProprietario);
		
		//Cria o Button Group
		
		ButtonGroup grupoSexo = new ButtonGroup();  
        grupoSexo.add(rdbtnMasculinoCadastroClienteProprietario);  
        grupoSexo.add(rdbtnFemininoCadastroClienteProprietario);
		
		txtNomeClienteProprietario = new JTextField();
		txtNomeClienteProprietario.setColumns(10);
		txtNomeClienteProprietario.setBounds(56, 8, 327, 20);
		panel.add(txtNomeClienteProprietario);
		
		JLabel lblCpfCadastroClienteProprietario = new JLabel("CPF: * ");
		lblCpfCadastroClienteProprietario.setBounds(10, 61, 46, 14);
		panel.add(lblCpfCadastroClienteProprietario);
		
		ftCpfClienteProprietario = new JFormattedTextField(Mascara.setMaskCpfInTf(ftCpfClienteProprietario));
		ftCpfClienteProprietario.setColumns(10);
		ftCpfClienteProprietario.setBounds(57, 58, 109, 20);
		panel.add(ftCpfClienteProprietario);
		
		JLabel lblEstadoCivilCadastroClienteProprietario = new JLabel("Estado Civil:");
		lblEstadoCivilCadastroClienteProprietario.setBounds(10, 86, 81, 14);
		panel.add(lblEstadoCivilCadastroClienteProprietario);
		
		JComboBox comboBoxEstadoCivilCadastroClienteProprietario = new JComboBox();
		comboBoxEstadoCivilCadastroClienteProprietario.setModel(new DefaultComboBoxModel(new String[] {"Solteiro (a)", "Casado (a)", "Divorciado (a)", "Vi\u00FAvo (a)"}));
		comboBoxEstadoCivilCadastroClienteProprietario.setToolTipText("Solteiro (a)\r\nCasado (a)\r\nDivorciado (a)\r\nVi\u00FAvo (a)");
		comboBoxEstadoCivilCadastroClienteProprietario.setBounds(80, 83, 137, 20);
		panel.add(comboBoxEstadoCivilCadastroClienteProprietario);
		
		JLabel lblRgCadastroClienteProprietario = new JLabel("RG:");
		lblRgCadastroClienteProprietario.setBounds(182, 61, 46, 14);
		panel.add(lblRgCadastroClienteProprietario);
		
		ftRgClienteProprietario = new JFormattedTextField(Mascara.setMaskRgInTf(ftRgClienteProprietario));
		ftRgClienteProprietario.setColumns(10);
		ftRgClienteProprietario.setBounds(215, 58, 109, 20);
		panel.add(ftRgClienteProprietario);
		
		JLabel lblNacionalidadeCadastroClienteProprietario = new JLabel("Nacionalidade:");
		lblNacionalidadeCadastroClienteProprietario.setBounds(240, 86, 98, 14);
		panel.add(lblNacionalidadeCadastroClienteProprietario);
		
		txtNacionalidadeClienteProprietario = new JTextField();
		txtNacionalidadeClienteProprietario.setColumns(10);
		txtNacionalidadeClienteProprietario.setBounds(335, 83, 116, 20);
		panel.add(txtNacionalidadeClienteProprietario);
		
		JLabel lblEnderecoCadastroClienteProprietario = new JLabel("Endere\u00E7o: * ");
		lblEnderecoCadastroClienteProprietario.setBounds(10, 111, 63, 14);
		panel.add(lblEnderecoCadastroClienteProprietario);
		
		txtEnderecoClienteProprietario = new JTextField();
		txtEnderecoClienteProprietario.setColumns(10);
		txtEnderecoClienteProprietario.setBounds(80, 108, 244, 20);
		panel.add(txtEnderecoClienteProprietario);
		
		JLabel lblNumeroCadastroClienteProprietario = new JLabel("N\u00FAmero: * ");
		lblNumeroCadastroClienteProprietario.setBounds(345, 111, 70, 14);
		panel.add(lblNumeroCadastroClienteProprietario);
		
		txtNumeroClienteProprietario = new JTextField();
		txtNumeroClienteProprietario.setColumns(10);
		txtNumeroClienteProprietario.setBounds(412, 108, 63, 20);
		panel.add(txtNumeroClienteProprietario);
		
		JLabel lblComplementoCadastroClienteProprietario = new JLabel("Complemento:");
		lblComplementoCadastroClienteProprietario.setBounds(10, 136, 96, 14);
		panel.add(lblComplementoCadastroClienteProprietario);
		
		txtComplementoClienteProprietario = new JTextField();
		txtComplementoClienteProprietario.setColumns(10);
		txtComplementoClienteProprietario.setBounds(101, 133, 102, 20);
		panel.add(txtComplementoClienteProprietario);
		
		JLabel lblCepCadastroClienteProprietario = new JLabel("CEP: *");
		lblCepCadastroClienteProprietario.setBounds(225, 136, 46, 14);
		panel.add(lblCepCadastroClienteProprietario);
		
		ftCepClienteProprietario = new JFormattedTextField(Mascara.setMaskCepInTf(ftCepClienteProprietario));
		ftCepClienteProprietario.setColumns(10);
		ftCepClienteProprietario.setBounds(265, 133, 86, 20);
		panel.add(ftCepClienteProprietario);
		
		JLabel lblTelefoneResidencialCadastroClienteProprietario = new JLabel("Telefone Residencial:");
		lblTelefoneResidencialCadastroClienteProprietario.setBounds(10, 244, 132, 14);
		panel.add(lblTelefoneResidencialCadastroClienteProprietario);
		
		ftTelefoneResidencialClienteProprietario = new JFormattedTextField(Mascara.setMaskTelefoneInTf(ftTelefoneResidencialClienteProprietario));
		ftTelefoneResidencialClienteProprietario.setColumns(10);
		ftTelefoneResidencialClienteProprietario.setBounds(138, 241, 106, 20);
		panel.add(ftTelefoneResidencialClienteProprietario);
		
		JLabel lblTelefoneCelularCadastroClienteProprietario = new JLabel("Telefone Celular: *");
		lblTelefoneCelularCadastroClienteProprietario.setBounds(265, 244, 118, 14);
		panel.add(lblTelefoneCelularCadastroClienteProprietario);
		
		ftTelefoneCelularClienteProprietario = new JFormattedTextField(Mascara.setMaskCelularInTf(ftTelefoneCelularClienteProprietario));
		ftTelefoneCelularClienteProprietario.setColumns(10);
		ftTelefoneCelularClienteProprietario.setBounds(369, 241, 106, 20);
		panel.add(ftTelefoneCelularClienteProprietario);
		
		JLabel lblImagemCadastroClienteProprietario = new JLabel("Imagem:");
		lblImagemCadastroClienteProprietario.setBounds(10, 282, 54, 14);
		panel.add(lblImagemCadastroClienteProprietario);
		
		JButton btnProcurarImagemCadastroClienteProprietario = new JButton("Procurar...");
		btnProcurarImagemCadastroClienteProprietario.setBounds(172, 321, 134, 23);
		panel.add(btnProcurarImagemCadastroClienteProprietario);
		
		JButton btnDefinirImagemCadastroClienteProprietario = new JButton("Definir Imagem");
		btnDefinirImagemCadastroClienteProprietario.setBounds(172, 355, 134, 23);
		panel.add(btnDefinirImagemCadastroClienteProprietario);
		
		JButton btnExcluirImagemCadastroClienteProprietario = new JButton("Excluir Imagem");
		btnExcluirImagemCadastroClienteProprietario.setBounds(172, 389, 134, 23);
		panel.add(btnExcluirImagemCadastroClienteProprietario);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 174, 500, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 269, 500, 2);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 449, 500, 2);
		panel.add(separator_2);
		
		JButton btnLimparCamposCadastroClienteProprietario = new JButton("Limpar Campos");
		btnLimparCamposCadastroClienteProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaFormulario();
			}
		});
		btnLimparCamposCadastroClienteProprietario.setBounds(39, 480, 126, 23);
		panel.add(btnLimparCamposCadastroClienteProprietario);
		
		JButton btnCancelarCadastroClienteProprietario = new JButton("Cancelar");
		btnCancelarCadastroClienteProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnCancelarCadastroClienteProprietario.setBounds(235, 480, 89, 23);
		panel.add(btnCancelarCadastroClienteProprietario);
		
		JButton btnSalvarCadastroClienteProprietario = new JButton("Cadastrar");
		btnSalvarCadastroClienteProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					salvarClienteProprietario();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSalvarCadastroClienteProprietario.setBounds(388, 480, 101, 23);
		panel.add(btnSalvarCadastroClienteProprietario);
		
		JLabel lblContatoCadastroClienteProprietario = new JLabel("Contato:");
		lblContatoCadastroClienteProprietario.setBounds(10, 187, 63, 14);
		panel.add(lblContatoCadastroClienteProprietario);
		
		JLabel lblEmailCadastroClienteProprietario = new JLabel("Email: *");
		lblEmailCadastroClienteProprietario.setBounds(10, 216, 46, 14);
		panel.add(lblEmailCadastroClienteProprietario);
		
		ftEmailClienteProprietario = new JFormattedTextField();
		ftEmailClienteProprietario.setColumns(10);
		ftEmailClienteProprietario.setBounds(90, 213, 154, 20);
		panel.add(ftEmailClienteProprietario);
		
		JLabel lblSiteCadastroClienteProprietario = new JLabel("Site:");
		lblSiteCadastroClienteProprietario.setBounds(265, 216, 46, 14);
		panel.add(lblSiteCadastroClienteProprietario);
		
		txtSiteClienteProprietario = new JTextField();
		txtSiteClienteProprietario.setColumns(10);
		txtSiteClienteProprietario.setBounds(321, 213, 154, 20);
		panel.add(txtSiteClienteProprietario);
		
		ScrollPane scrollPaneImagemCadastroClienteProprietario = new ScrollPane();
		scrollPaneImagemCadastroClienteProprietario.setBounds(10, 300, 115, 143);
		panel.add(scrollPaneImagemCadastroClienteProprietario);
		
		Panel panelPropriedade = new Panel();
		panelPropriedade.setBounds(322, 282, 182, 161);
		panel.add(panelPropriedade);
		panelPropriedade.setLayout(null);
		
		JLabel lblTipoDePropriedade = new JLabel("Tipo de Propriedade: *");
		lblTipoDePropriedade.setBounds(0, 11, 125, 14);
		panelPropriedade.add(lblTipoDePropriedade);
		
		JCheckBox chckbxPropriedadesComerciais = new JCheckBox("Propriedades Comerciais");
		chckbxPropriedadesComerciais.setBounds(6, 47, 180, 23);
		panelPropriedade.add(chckbxPropriedadesComerciais);
		
		JCheckBox chckbxPropriedadesResidenciais = new JCheckBox("Propriedades Residenciais");
		chckbxPropriedadesResidenciais.setBounds(6, 79, 180, 23);
		panelPropriedade.add(chckbxPropriedadesResidenciais);
		
		JLabel lblSave = new JLabel("");
		lblSave.setIcon(new ImageIcon(frmCadastroClienteProprietario.class.getResource("/br/com/images/save.png")));
		lblSave.setBounds(358, 480, 25, 25);
		panel.add(lblSave);
		
		JLabel lblCancelar = new JLabel("");
		lblCancelar.setIcon(new ImageIcon(frmCadastroClienteProprietario.class.getResource("/br/com/images/delete-.png")));
		lblCancelar.setBounds(203, 480, 25, 25);
		panel.add(lblCancelar);
		
		JLabel lblClear = new JLabel("");
		lblClear.setIcon(new ImageIcon(frmCadastroClienteProprietario.class.getResource("/br/com/images/clear.png")));
		lblClear.setBounds(10, 480, 25, 25);
		panel.add(lblClear);
		
		JLabel lblExcluirImagem = new JLabel("");
		lblExcluirImagem.setIcon(new ImageIcon(frmCadastroClienteProprietario.class.getResource("/br/com/images/delete-.png")));
		lblExcluirImagem.setBounds(140, 389, 25, 25);
		panel.add(lblExcluirImagem);
		
		JLabel lblApply = new JLabel("");
		lblApply.setIcon(new ImageIcon(frmCadastroClienteProprietario.class.getResource("/br/com/images/apply-.png")));
		lblApply.setBounds(140, 355, 25, 25);
		panel.add(lblApply);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(frmCadastroClienteProprietario.class.getResource("/br/com/images/search-ico.png")));
		lblSearch.setBounds(138, 321, 25, 25);
		panel.add(lblSearch);
		
		setLocationRelativeTo(null);		
	}
	
	public void fechar(){
		this.dispose();
	}
	
	public void limpaFormulario(){
		txtNomeClienteProprietario.setText("");
		ftCpfClienteProprietario.setText("");
		ftRgClienteProprietario.setText("");
		txtNacionalidadeClienteProprietario.setText("");
		txtEnderecoClienteProprietario.setText("");
		txtNumeroClienteProprietario.setText("");
		txtComplementoClienteProprietario.setText("");
		ftCepClienteProprietario.setText("");
		ftTelefoneResidencialClienteProprietario.setText("");
		ftTelefoneCelularClienteProprietario.setText("");
		ftEmailClienteProprietario.setText("");
		txtSiteClienteProprietario.setText("");
	}
	
private ClienteProprietarioFisica getBean() throws EntradaUsuarioException, ParseException{
		
		//primeiro eu instancio a classe pai que deve ser inserida primeiro
		ClienteProprietario cliComprador = new ClienteProprietario();
		
		//preencho com os dados necessarios fazendo a valida��o de campos obrigat�rios
		cliComprador.setCep(TelaUtil.getCep(ftCep, true));
		cliComprador.setNumeroEndereco(TelaUtil.getCampoObrigatorio(txtNumero, true));
		cliComprador.setComplementoEndereco(TelaUtil.getCampoObrigatorio(txtComplemento, false)); //false quando nao � obrig.
		cliComprador.setTelefone(TelaUtil.getTelefone(ftTelefone, false));
		cliComprador.setCelular(TelaUtil.getCelular(ftCelular, true));
		
		//verifica se est� em modo edi��o, se estiver ele seta o id do cadastro que deseja alterar
		if(this.modo  == 1){
			cliComprador.setCodCliComprador(cadastro.getClienteComprador().getCodCliComprador());
		}
		
		//agora eu vou instancia a classe filha que depende de um ID que ja foi inserido para ser inserida
		ClienteProprietarioFisica cliFi = new ClienteProprietarioFisica();
		
		//preenche com os dados
		cliFi.setNome(TelaUtil.getCampoObrigatorio(txtNome, true));
		cliFi.setCpf(TelaUtil.getCpf(ftCpf, true));
		cliFi.setRg(TelaUtil.getCampoObrigatorio(txtRg, true));
		cliFi.setDataNascimento(TelaUtil.getDateFromDatePicker(dtNascimento));
		cliFi.setEstadoCivil(cbEstadoCivil.getSelectedItem().toString().substring(0, 1));
		cliFi.setProfissao(TelaUtil.getCampoObrigatorio(txtProfissao, true));
		cliFi.setSexo(TelaUtil.getCharSexo(rbMasculino));
		cliFi.setRenda(TelaUtil.getCampoObrigatorioDouble(txtRenda));
		cliFi.setEmail(TelaUtil.getEmail(txtEmail));
		cliFi.setClienteComprador(cliComprador); //agora seto dentro da filha a classe pai.
		
		
		return cliFi;
	}
	
	private void salvarClienteProprietario() throws SQLException, ParseException{
		try{
			ClienteProprietarioFisica CliProp = getBean();
			new ClienteProprietarioFisicaDAO().inserirClienteProprietarioFisica(CliProp);
			limpaFormulario();
			JOptionPane.showMessageDialog(this, "Transa��o efetuada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
		}catch(DAOException e){
			e.printStackTrace();
		} catch (EntradaUsuarioException e) {
			e.printStackTrace();
		}
	}
}
