package br.com.sani.telas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.sani.bean.ClienteProprietario;
import br.com.sani.bean.ClienteProprietarioFisica;
import br.com.sani.bean.Endereco;
import br.com.sani.dao.ClienteProprietarioDAO;
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
	private JTextField txtNome;
	
	private JFormattedTextField txtCpf;
	private JTextField txtRg;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JFormattedTextField txtCep;
	private JFormattedTextField txtTelefone;
	private JFormattedTextField txtCelular;
	private JFormattedTextField txtEmail;
	
	private JRadioButton rdbtnMasculinoCadastroClienteProprietario;
	private JComboBox cbEstadoCivilClienteProprietarioFisica;
	
	private int modo = 0;
	private ClienteProprietarioFisica cadastro;
	private JDateChooser dtNascimento;
	private JButton btnCadastrar;
	private JButton btnLimpar;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new frmCadastroClienteProprietarioFisica(null);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public frmCadastroClienteProprietarioFisica(ClienteProprietarioFisica cliente){
		try {
			montarComponentes();
			if(cliente != null){
				modoEdicao(cliente);
			}
			
			setVisible(true);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @throws Throwable 
	 */
	public void montarComponentes() throws Throwable {
		SwingUtil.lookWindows(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmCadastroClienteProprietarioFisica.class.getResource("/br/com/images/cliente.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Cadastro Cliente Propriet\u00E1rio - Pessoa Fisica");
		setResizable(false);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Cria o Button Group
		
		ButtonGroup grupoSexo = new ButtonGroup();  
		
		JPanel panelInformacoesPessoais = new JPanel();
		panelInformacoesPessoais.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informa\u00E7\u00F5es B\u00E1sicas*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInformacoesPessoais.setBounds(10, 11, 677, 404);
		contentPane.add(panelInformacoesPessoais);
		panelInformacoesPessoais.setLayout(null);
		
		JLabel lblNomeCadastroClienteProprietario = new JLabel("Nome:*");
		lblNomeCadastroClienteProprietario.setBounds(68, 30, 46, 14);
		panelInformacoesPessoais.add(lblNomeCadastroClienteProprietario);
		
		txtNome = new JTextField();
		txtNome.setBounds(113, 24, 299, 20);
		panelInformacoesPessoais.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblCpfCadastroClienteProprietario = new JLabel("CPF: *");
		lblCpfCadastroClienteProprietario.setBounds(74, 90, 40, 14);
		panelInformacoesPessoais.add(lblCpfCadastroClienteProprietario);
		
		txtCpf = new JFormattedTextField(Mascara.setMaskCpfInTf(txtCpf));
		txtCpf.setBounds(113, 87, 106, 20);
		panelInformacoesPessoais.add(txtCpf);
		txtCpf.setColumns(10);
		
		txtRg = new JTextField();
		txtRg.setBounds(303, 87, 109, 20);
		panelInformacoesPessoais.add(txtRg);
		txtRg.setColumns(10);
		
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
		
		txtNumero = new JTextField();
		txtNumero.setBounds(303, 118, 109, 20);
		panelInformacoesPessoais.add(txtNumero);
		txtNumero.setColumns(10);
		
		JLabel lblNumeroCadastroClienteProprietario = new JLabel("N\u00FAmero: ");
		lblNumeroCadastroClienteProprietario.setBounds(247, 120, 55, 14);
		panelInformacoesPessoais.add(lblNumeroCadastroClienteProprietario);
		
		txtEndereco = new JTextField();
		txtEndereco.setEditable(false);
		txtEndereco.setBounds(113, 147, 299, 20);
		panelInformacoesPessoais.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblEnderecoCadastroClienteProprietario = new JLabel("Endere\u00E7o:");
		lblEnderecoCadastroClienteProprietario.setBounds(51, 150, 63, 14);
		panelInformacoesPessoais.add(lblEnderecoCadastroClienteProprietario);
		
		JLabel lblComplementoCadastroClienteProprietario = new JLabel("Complemento:");
		lblComplementoCadastroClienteProprietario.setBounds(33, 180, 70, 14);
		panelInformacoesPessoais.add(lblComplementoCadastroClienteProprietario);
		
		txtComplemento = new JTextField();
		txtComplemento.setBounds(113, 177, 299, 20);
		panelInformacoesPessoais.add(txtComplemento);
		txtComplemento.setColumns(10);
		
		JLabel lblCepCadastroClienteProprietario = new JLabel("CEP: *");
		lblCepCadastroClienteProprietario.setBounds(74, 120, 40, 14);
		panelInformacoesPessoais.add(lblCepCadastroClienteProprietario);
		
		txtCep = new JFormattedTextField(Mascara.setMaskCepInTf(txtCep));
		txtCep.setBounds(113, 118, 106, 20);
		panelInformacoesPessoais.add(txtCep);
		txtCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				buscarEndereco();
				txtNumero.requestFocus();
			}
		});
		txtCep.setColumns(10);
		
		JPanel panelSexo = new JPanel();
		panelSexo.setBorder(new TitledBorder(null, "Sexo*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSexo.setBounds(442, 87, 137, 74);
		panelInformacoesPessoais.add(panelSexo);
		panelSexo.setLayout(null);
		
		JRadioButton rdbtnMasculinoCadastroClienteProprietario_1 = new JRadioButton("Masculino");
		rdbtnMasculinoCadastroClienteProprietario_1.setSelected(true);
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
		
		txtTelefone = new JFormattedTextField(Mascara.setMaskTelefoneInTf(txtTelefone));
		txtTelefone.setBounds(113, 55, 106, 20);
		panelInformacoesPessoais.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblTelefoneCelularCadastroClienteProprietario = new JLabel("Telefone Cel: *");
		lblTelefoneCelularCadastroClienteProprietario.setBounds(225, 60, 81, 14);
		panelInformacoesPessoais.add(lblTelefoneCelularCadastroClienteProprietario);
		
		txtCelular = new JFormattedTextField(Mascara.setMaskCelularInTf(txtCelular));
		txtCelular.setBounds(306, 55, 106, 20);
		panelInformacoesPessoais.add(txtCelular);
		txtCelular.setColumns(10);
		
		JLabel lblDataNasc = new JLabel("Data Nasc.:");
		lblDataNasc.setBounds(442, 57, 63, 14);
		panelInformacoesPessoais.add(lblDataNasc);
		
		dtNascimento = new JDateChooser();
		dtNascimento.setName("Data de Nascimento");
		dtNascimento.setBounds(511, 54, 89, 20);
		panelInformacoesPessoais.add(dtNascimento);
		
		txtEmail = new JFormattedTextField();
		txtEmail.setBounds(113, 208, 299, 20);
		panelInformacoesPessoais.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblEmailCadastroClienteProprietario = new JLabel("Email: *");
		lblEmailCadastroClienteProprietario.setBounds(65, 210, 49, 14);
		panelInformacoesPessoais.add(lblEmailCadastroClienteProprietario);
		
		btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setBounds(351, 352, 145, 30);
		panelInformacoesPessoais.add(btnLimpar);
		btnLimpar.setIcon(new ImageIcon(frmCadastroClienteProprietarioFisica.class.getResource("/br/com/images/clear.png")));
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(506, 352, 145, 30);
		panelInformacoesPessoais.add(btnCancelar);
		btnCancelar.setIcon(new ImageIcon(frmCadastroClienteProprietarioFisica.class.getResource("/br/com/images/delete-.png")));
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(196, 352, 145, 30);
		panelInformacoesPessoais.add(btnCadastrar);
		btnCadastrar.setIcon(new ImageIcon(frmCadastroClienteProprietarioFisica.class.getResource("/br/com/images/save.png")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(modo == 0){
						salvarClienteProprietario();
					}else if(modo == 1){
						atualizar();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnLimpar.addActionListener(new ActionListener() {
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
		txtNome.setText("");
		txtCpf.setText("");
		txtRg.setText("");
		txtEndereco.setText("");
		txtNumero.setText("");
		txtComplemento.setText("");
		txtCep.setText("");
		txtTelefone.setText("");
		txtCelular.setText("");
		txtEmail.setText("");
	}
	
	public ClienteProprietarioFisica getBean() throws EntradaUsuarioException, ParseException{
		
		ClienteProprietario CliProp = new ClienteProprietario();
		
		CliProp.setNumeroEndereco(TelaUtil.getCampoObrigatorio(txtNumero, true));
		CliProp.setComplementoEndereco(TelaUtil.getCampoObrigatorio(txtComplemento, true));
		CliProp.setCep(TelaUtil.getCep(txtCep, true));
		CliProp.setTelefone(TelaUtil.getTelefone(txtTelefone, false));
		CliProp.setCelular(TelaUtil.getCelular(txtCelular, true));
		
		if(this.modo == 1){
			CliProp.setCodCliProprietario(cadastro.getClienteProprietarioFisica().getCodCliProprietario());
		}
		
		ClienteProprietarioFisica CliPropFisica = new ClienteProprietarioFisica();
		
		CliPropFisica.setNome(TelaUtil.getCampoObrigatorio(txtNome, true));
		CliPropFisica.setSexo(TelaUtil.getCharSexo(rdbtnMasculinoCadastroClienteProprietario));
		CliPropFisica.setCpf(TelaUtil.getCpf(txtCpf, true));
		CliPropFisica.setRg(TelaUtil.getCampoObrigatorio(txtRg, true));
		CliPropFisica.setEstadoCivil(cbEstadoCivilClienteProprietarioFisica.getSelectedItem().toString().substring(0, 1));
		CliPropFisica.setEmail(TelaUtil.getEmail(txtEmail));
		CliPropFisica.setDataNascimento(TelaUtil.getDateFromDateChooser(dtNascimento, true));
		
		CliPropFisica.setClienteProprietario(CliProp);
		
		return CliPropFisica;		
		
		
	}
	
	/**
	 * Este método atualiza os dados no banco
	 */
	private void atualizar(){
		try{
			ClienteProprietarioFisica bean = getBean(); //mando ele coletar os dados da tela e validadar
			ClienteProprietarioFisicaDAO dao = new ClienteProprietarioFisicaDAO();
			
			dao.atualizarClienteProprietarioFisica(bean);
			
			//se não der erro ele exibe essa mensagem
			JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			
			//volta para modo de cadastro
			modoCadastro();
		}catch(DAOException e){
			JOptionPane.showMessageDialog(this, "Erro ao tentar Atualizar os Dados!", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (EntradaUsuarioException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void buscarEndereco(){
		try{
			Endereco endereco = new EnderecoDAO().buscarPorCep(TelaUtil.getCep(txtCep, true));
			if(endereco != null){
				txtEndereco.setText(endereco.getEndereco());
				//txtCidade.setText(endereco.getCidade());
				//txtBairro.setText(endereco.getBairro());
				//txtEstado.setText(endereco.getEstado());
			}else{
				JOptionPane.showMessageDialog(null, "O cep digitado não existe!");
				txtCep.setText("");
				txtCep.requestFocus();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void modoEdicao(ClienteProprietarioFisica cadastro){
		setTitle("EDIÇÃO de Cadastro Cliente Proprietario / Pessoa Física");
		btnCadastrar.setText("Salvar");
		this.modo = 1;
		
		txtNome.setText(cadastro.getNome());
		txtComplemento.setText(cadastro.getCodCliProprietario().getComplementoEndereco());
		txtEmail.setText(cadastro.getEmail());
		txtNumero.setText(cadastro.getCodCliProprietario().getNumeroEndereco());
		txtRg.setText(cadastro.getRg());
		txtCelular.setText(cadastro.getCodCliProprietario().getCelular());
		txtCep.setText(cadastro.getCodCliProprietario().getCep());
		txtCpf.setText(cadastro.getCpf());
		txtTelefone.setText(cadastro.getCodCliProprietario().getTelefone());
		dtNascimento.setDate(cadastro.getDataNascimento());
		
		this.cadastro = cadastro;
		
		buscarEndereco();
	}
	
	private void modoCadastro(){
		limpaFormulario();
		setTitle("Cadastro Cliente Proprietario / Pessoa Física");
		btnCadastrar.setText("Cadastrar");
		this.modo = 0;
		
		this.cadastro = null;
	}
}
