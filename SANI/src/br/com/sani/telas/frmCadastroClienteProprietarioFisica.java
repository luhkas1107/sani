package br.com.sani.telas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.sani.bean.ClienteProprietario;
import br.com.sani.bean.ClienteProprietarioFisica;
import br.com.sani.bean.Endereco;
import br.com.sani.dao.ClienteProprietarioFisicaDAO;
import br.com.sani.dao.EnderecoDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.exception.EntradaUsuarioException;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;
import br.com.sani.util.TelaUtil;

import com.toedter.calendar.JDateChooser;

public class frmCadastroClienteProprietarioFisica extends JFrame {

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
	private JComboBox cbEstadoCivilClienteProprietarioFisica;
	
	private int modo = 0;
	private ClienteProprietarioFisica cadastro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCadastroClienteProprietarioFisica frame = new frmCadastroClienteProprietarioFisica();
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
	public frmCadastroClienteProprietarioFisica() throws Throwable {
		SwingUtil.lookWindows(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmCadastroClienteProprietarioFisica.class.getResource("/br/com/images/cliente.png")));
		setTitle("Cadastro Cliente Propriet\u00E1rio");
		setResizable(false);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Cria o Button Group
		
		ButtonGroup grupoSexo = new ButtonGroup();  
		
		JPanel panelInformacoesPessoais = new JPanel();
		panelInformacoesPessoais.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informa\u00E7\u00F5es B\u00E1sicas*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInformacoesPessoais.setBounds(10, 11, 677, 278);
		contentPane.add(panelInformacoesPessoais);
		panelInformacoesPessoais.setLayout(null);
		
		JLabel lblNomeCadastroClienteProprietario = new JLabel("Nome:*");
		lblNomeCadastroClienteProprietario.setBounds(68, 30, 46, 14);
		panelInformacoesPessoais.add(lblNomeCadastroClienteProprietario);
		
		txtNomeClienteProprietario = new JTextField();
		txtNomeClienteProprietario.setBounds(113, 24, 299, 20);
		panelInformacoesPessoais.add(txtNomeClienteProprietario);
		txtNomeClienteProprietario.setColumns(10);
		
		JLabel lblCpfCadastroClienteProprietario = new JLabel("CPF: *");
		lblCpfCadastroClienteProprietario.setBounds(74, 90, 40, 14);
		panelInformacoesPessoais.add(lblCpfCadastroClienteProprietario);
		
		ftCpfClienteProprietario = new JFormattedTextField(Mascara.setMaskCpfInTf(ftCpfClienteProprietario));
		ftCpfClienteProprietario.setBounds(113, 87, 106, 20);
		panelInformacoesPessoais.add(ftCpfClienteProprietario);
		ftCpfClienteProprietario.setColumns(10);
		
		ftRgClienteProprietario = new JFormattedTextField(Mascara.setMaskRgInTf(ftRgClienteProprietario));
		ftRgClienteProprietario.setBounds(303, 87, 109, 20);
		panelInformacoesPessoais.add(ftRgClienteProprietario);
		ftRgClienteProprietario.setColumns(10);
		
		JLabel lblRgCadastroClienteProprietario = new JLabel("RG:");
		lblRgCadastroClienteProprietario.setBounds(270, 90, 23, 14);
		panelInformacoesPessoais.add(lblRgCadastroClienteProprietario);
		
		JLabel lblEstadoCivilCadastroClienteProprietario = new JLabel("Estado Civil:");
		lblEstadoCivilCadastroClienteProprietario.setBounds(442, 27, 70, 14);
		panelInformacoesPessoais.add(lblEstadoCivilCadastroClienteProprietario);
		
		cbEstadoCivilClienteProprietarioFisica = new JComboBox();
		cbEstadoCivilClienteProprietarioFisica.setBounds(512, 24, 137, 20);
		panelInformacoesPessoais.add(cbEstadoCivilClienteProprietarioFisica);
		cbEstadoCivilClienteProprietarioFisica.setModel(new DefaultComboBoxModel(new String[] {"Solteiro (a)", "Casado (a)", "Divorciado (a)", "Vi\u00FAvo (a)"}));
		cbEstadoCivilClienteProprietarioFisica.setToolTipText("Solteiro (a)\r\nCasado (a)\r\nDivorciado (a)\r\nVi\u00FAvo (a)");
		
		JLabel lblNacionalidadeCadastroClienteProprietario = new JLabel("Nacionalidade:");
		lblNacionalidadeCadastroClienteProprietario.setBounds(432, 60, 74, 14);
		panelInformacoesPessoais.add(lblNacionalidadeCadastroClienteProprietario);
		
		txtNacionalidadeClienteProprietario = new JTextField();
		txtNacionalidadeClienteProprietario.setBounds(512, 57, 137, 20);
		panelInformacoesPessoais.add(txtNacionalidadeClienteProprietario);
		txtNacionalidadeClienteProprietario.setColumns(10);
		
		txtNumeroClienteProprietario = new JTextField();
		txtNumeroClienteProprietario.setBounds(303, 118, 109, 20);
		panelInformacoesPessoais.add(txtNumeroClienteProprietario);
		txtNumeroClienteProprietario.setColumns(10);
		
		JLabel lblNumeroCadastroClienteProprietario = new JLabel("N\u00FAmero: ");
		lblNumeroCadastroClienteProprietario.setBounds(247, 120, 55, 14);
		panelInformacoesPessoais.add(lblNumeroCadastroClienteProprietario);
		
		txtEnderecoClienteProprietario = new JTextField();
		txtEnderecoClienteProprietario.setEditable(false);
		txtEnderecoClienteProprietario.setBounds(113, 147, 299, 20);
		panelInformacoesPessoais.add(txtEnderecoClienteProprietario);
		txtEnderecoClienteProprietario.setColumns(10);
		
		JLabel lblEnderecoCadastroClienteProprietario = new JLabel("Endere\u00E7o:");
		lblEnderecoCadastroClienteProprietario.setBounds(51, 150, 63, 14);
		panelInformacoesPessoais.add(lblEnderecoCadastroClienteProprietario);
		
		JLabel lblComplementoCadastroClienteProprietario = new JLabel("Complemento:");
		lblComplementoCadastroClienteProprietario.setBounds(33, 180, 70, 14);
		panelInformacoesPessoais.add(lblComplementoCadastroClienteProprietario);
		
		txtComplementoClienteProprietario = new JTextField();
		txtComplementoClienteProprietario.setBounds(113, 177, 299, 20);
		panelInformacoesPessoais.add(txtComplementoClienteProprietario);
		txtComplementoClienteProprietario.setColumns(10);
		
		JLabel lblCepCadastroClienteProprietario = new JLabel("CEP: *");
		lblCepCadastroClienteProprietario.setBounds(74, 120, 40, 14);
		panelInformacoesPessoais.add(lblCepCadastroClienteProprietario);
		
		ftCepClienteProprietario = new JFormattedTextField(Mascara.setMaskCepInTf(ftCepClienteProprietario));
		ftCepClienteProprietario.setBounds(113, 118, 106, 20);
		panelInformacoesPessoais.add(ftCepClienteProprietario);
		ftCepClienteProprietario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				buscarEndereco();
				txtNumeroClienteProprietario.requestFocus();
			}
		});
		ftCepClienteProprietario.setColumns(10);
		
		JPanel panelSexo = new JPanel();
		panelSexo.setBorder(new TitledBorder(null, "Sexo*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSexo.setBounds(512, 121, 137, 74);
		panelInformacoesPessoais.add(panelSexo);
		panelSexo.setLayout(null);
		
		JRadioButton rdbtnMasculinoCadastroClienteProprietario_1 = new JRadioButton("Masculino");
		rdbtnMasculinoCadastroClienteProprietario_1.setBounds(18, 20, 109, 23);
		panelSexo.add(rdbtnMasculinoCadastroClienteProprietario_1);
		grupoSexo.add(rdbtnMasculinoCadastroClienteProprietario_1);  
		
		JRadioButton rdbtnFemininoCadastroClienteProprietario = new JRadioButton("Feminino");
		rdbtnFemininoCadastroClienteProprietario.setBounds(18, 46, 109, 23);
		panelSexo.add(rdbtnFemininoCadastroClienteProprietario);
		grupoSexo.add(rdbtnFemininoCadastroClienteProprietario);
		
		JLabel lblTelefoneResidencialCadastroClienteProprietario = new JLabel("Telefone Res.:*");
		lblTelefoneResidencialCadastroClienteProprietario.setBounds(28, 60, 86, 14);
		panelInformacoesPessoais.add(lblTelefoneResidencialCadastroClienteProprietario);
		
		ftTelefoneResidencialClienteProprietario = new JFormattedTextField(Mascara.setMaskTelefoneInTf(ftTelefoneResidencialClienteProprietario));
		ftTelefoneResidencialClienteProprietario.setBounds(113, 55, 106, 20);
		panelInformacoesPessoais.add(ftTelefoneResidencialClienteProprietario);
		ftTelefoneResidencialClienteProprietario.setColumns(10);
		
		JLabel lblTelefoneCelularCadastroClienteProprietario = new JLabel("Telefone Cel: *");
		lblTelefoneCelularCadastroClienteProprietario.setBounds(225, 60, 81, 14);
		panelInformacoesPessoais.add(lblTelefoneCelularCadastroClienteProprietario);
		
		ftTelefoneCelularClienteProprietario = new JFormattedTextField(Mascara.setMaskCelularInTf(ftTelefoneCelularClienteProprietario));
		ftTelefoneCelularClienteProprietario.setBounds(306, 55, 106, 20);
		panelInformacoesPessoais.add(ftTelefoneCelularClienteProprietario);
		ftTelefoneCelularClienteProprietario.setColumns(10);
		
		JLabel lblDataNasc = new JLabel("Data Nasc.:");
		lblDataNasc.setBounds(443, 90, 63, 14);
		panelInformacoesPessoais.add(lblDataNasc);
		
		JDateChooser dtNascimento = new JDateChooser();
		dtNascimento.setName("Data de Nascimento");
		dtNascimento.setBounds(512, 87, 89, 20);
		panelInformacoesPessoais.add(dtNascimento);
		
		txtSiteClienteProprietario = new JTextField();
		txtSiteClienteProprietario.setBounds(113, 239, 299, 20);
		panelInformacoesPessoais.add(txtSiteClienteProprietario);
		txtSiteClienteProprietario.setColumns(10);
		
		ftEmailClienteProprietario = new JFormattedTextField();
		ftEmailClienteProprietario.setBounds(113, 208, 299, 20);
		panelInformacoesPessoais.add(ftEmailClienteProprietario);
		ftEmailClienteProprietario.setColumns(10);
		
		JLabel lblSiteCadastroClienteProprietario = new JLabel("Site:");
		lblSiteCadastroClienteProprietario.setBounds(68, 240, 46, 14);
		panelInformacoesPessoais.add(lblSiteCadastroClienteProprietario);
		
		JLabel lblEmailCadastroClienteProprietario = new JLabel("Email: *");
		lblEmailCadastroClienteProprietario.setBounds(65, 210, 49, 14);
		panelInformacoesPessoais.add(lblEmailCadastroClienteProprietario);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo Propriedade*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfo.setBounds(10, 300, 677, 168);
		contentPane.add(panelInfo);
		panelInfo.setLayout(null);
		
		JCheckBox chckbxTerreno = new JCheckBox("Terreno");
		chckbxTerreno.setBounds(75, 26, 97, 23);
		panelInfo.add(chckbxTerreno);
		
		JCheckBox chckbxGalpao = new JCheckBox("Galp\u00E3o");
		chckbxGalpao.setBounds(75, 56, 97, 23);
		panelInfo.add(chckbxGalpao);
		
		JCheckBox chckbxSala = new JCheckBox("Sala");
		chckbxSala.setBounds(75, 86, 97, 23);
		panelInfo.add(chckbxSala);
		
		JCheckBox chckbxImovelRenda = new JCheckBox("Im\u00F3vel para Renda");
		chckbxImovelRenda.setBounds(75, 116, 117, 23);
		panelInfo.add(chckbxImovelRenda);
		
		JCheckBox chckbxCasa = new JCheckBox("Casa");
		chckbxCasa.setBounds(242, 26, 97, 23);
		panelInfo.add(chckbxCasa);
		
		JCheckBox chckbxApartamento = new JCheckBox("Apartamento");
		chckbxApartamento.setBounds(242, 56, 97, 23);
		panelInfo.add(chckbxApartamento);
		
		JCheckBox chckbxSobrado = new JCheckBox("Sobrado");
		chckbxSobrado.setBounds(242, 86, 97, 23);
		panelInfo.add(chckbxSobrado);
		
		JCheckBox chckbxPredio = new JCheckBox("Pr\u00E9dio");
		chckbxPredio.setBounds(242, 116, 97, 23);
		panelInfo.add(chckbxPredio);
		
		JButton btnLimparCamposCadastroClienteProprietario = new JButton("Limpar Campos");
		btnLimparCamposCadastroClienteProprietario.setIcon(new ImageIcon(frmCadastroClienteProprietarioFisica.class.getResource("/br/com/images/clear.png")));
		btnLimparCamposCadastroClienteProprietario.setBounds(472, 27, 145, 30);
		panelInfo.add(btnLimparCamposCadastroClienteProprietario);
		
		JButton btnCancelarCadastroClienteProprietario = new JButton("Cancelar");
		btnCancelarCadastroClienteProprietario.setIcon(new ImageIcon(frmCadastroClienteProprietarioFisica.class.getResource("/br/com/images/delete-.png")));
		btnCancelarCadastroClienteProprietario.setBounds(472, 68, 145, 30);
		panelInfo.add(btnCancelarCadastroClienteProprietario);
		
		JButton btnSalvarCadastroClienteProprietario = new JButton("Cadastrar");
		btnSalvarCadastroClienteProprietario.setIcon(new ImageIcon(frmCadastroClienteProprietarioFisica.class.getResource("/br/com/images/save.png")));
		btnSalvarCadastroClienteProprietario.setBounds(472, 109, 145, 30);
		panelInfo.add(btnSalvarCadastroClienteProprietario);
		btnSalvarCadastroClienteProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					salvarClienteProprietario();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCancelarCadastroClienteProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnLimparCamposCadastroClienteProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaFormulario();
			}
		});
		
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
	
	public ClienteProprietarioFisica getBean() throws EntradaUsuarioException{
		
		ClienteProprietario CliProp = new ClienteProprietario();
		
		CliProp.setNumeroEndereco(TelaUtil.getCampoObrigatorio(txtNumeroClienteProprietario, true));
		CliProp.setComplementoEndereco(TelaUtil.getCampoObrigatorio(txtComplementoClienteProprietario, true));
		CliProp.setCep(TelaUtil.getCep(ftCepClienteProprietario, true));
		CliProp.setTelefone(TelaUtil.getTelefone(ftTelefoneResidencialClienteProprietario, false));
		CliProp.setCelular(TelaUtil.getCelular(ftTelefoneCelularClienteProprietario, true));
		
		if(this.modo == 1){
			CliProp.setCodCliProprietario(cadastro.getClienteProprietarioFisica().getCodCliProprietario());
		}
		
		ClienteProprietarioFisica CliPropFisica = new ClienteProprietarioFisica();
		
		CliPropFisica.setNome(TelaUtil.getCampoObrigatorio(txtNomeClienteProprietario, true));
		CliPropFisica.setSexo(TelaUtil.getCharSexo(rdbtnMasculinoCadastroClienteProprietario));
		CliPropFisica.setCpf(TelaUtil.getCpf(ftCpfClienteProprietario, true));
		CliPropFisica.setRg(TelaUtil.getRg(ftRgClienteProprietario, true));
		//CliPropFisica.setRenda(TelaUtil.getCampoObrigatorio(txtNacionalidadeClienteProprietario, false));
		CliPropFisica.setEstadoCivil(cbEstadoCivilClienteProprietarioFisica.getSelectedItem().toString().substring(0, 1));
		CliPropFisica.setEmail(TelaUtil.getEmail(ftEmailClienteProprietario));
		
		return CliPropFisica;		
		
		
	}
	
	private void salvarClienteProprietario() throws SQLException{
		try{
			ClienteProprietarioFisica CliProp = getBean();
			new ClienteProprietarioFisicaDAO().inserirClienteProprietarioFisica(CliProp);
			limpaFormulario();
			JOptionPane.showMessageDialog(this, "Transação efetuada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
		}catch(DAOException e){
			e.printStackTrace();
		} catch (EntradaUsuarioException e) {
			e.printStackTrace();
		}
	}
	
	private void buscarEndereco(){
		try{
			Endereco endereco = new EnderecoDAO().buscarPorCep(TelaUtil.getCep(ftCepClienteProprietario, true));
			if(endereco != null){
				txtEnderecoClienteProprietario.setText(endereco.getEndereco());
				//txtCidade.setText(endereco.getCidade());
				//txtBairro.setText(endereco.getBairro());
				//txtEstado.setText(endereco.getEstado());
			}else{
				JOptionPane.showMessageDialog(null, "O cep digitado não existe!");
				ftCepClienteProprietario.setText("");
				ftCepClienteProprietario.requestFocus();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
