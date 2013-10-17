package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.SQLException;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;

import br.com.sani.bean.ClienteComprador;
import br.com.sani.bean.ClienteCompradorFisica;
import br.com.sani.bean.Endereco;
import br.com.sani.dao.ClienteCompradorFisicaDAO;
import br.com.sani.dao.EnderecoDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.exception.EntradaUsuarioException;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;
import br.com.sani.util.TelaUtil;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmCadastroClienteCompradorFis extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	
	private JTextField txtRenda;
	private JTextField txtEmail;
	private JRadioButton rbMasculino;
	private JFormattedTextField ftCpf;
	
	private String[] estadoCivil = new String[]{"S", "C", "D", "V"};
	private JComboBox cbEstadoCivil;
	private JFormattedTextField ftTelefone;
	private JFormattedTextField ftCelular;
	private JTextField txtRg;
	private JTextField txtProfissao;
	private JTextField txtEndereco;
	private JTextField txtComplemento;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JButton btnCancelar;
	private JButton btnCadastrar;
	private JButton btLimpar;
	private JXDatePicker dtNascimento;
	private JRadioButton rbFeminino;
	private JFormattedTextField ftCep;
	private JButton btnPesquisar;
	private JPanel pnEndereco;
	private JPanel panel;
	
	private int modo = 0; //essa variaval armazena se a tela esta em modo edicao ou "cadastração" kkkkkkkk
	private ClienteCompradorFisica cadastro; //essa variavel armazena um item que esta sendo editado
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new frmCadastroClienteCompradorFis(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public frmCadastroClienteCompradorFis(ClienteCompradorFisica cadastro){
		try {
			montarComponentes();
			
			if(cadastro != null){
				modoEdicao(cadastro);
			}
			
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmCadastroClienteCompradorFis.class.getResource("/br/com/images/home_badge.png")));
		setTitle("Cadastro Cliente Comprador / Pessoa Física");
		setBounds(100, 100, 725, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		ButtonGroup grupoSexo = new ButtonGroup();
		
		//Cria o Button Group
		
		
		
		btLimpar = new JButton("Limpar Campos");
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}
		});
		btLimpar.setIcon(new ImageIcon(frmCadastroClienteCompradorFis.class.getResource("/br/com/images/clear.png")));
		btLimpar.setBounds(409, 420, 140, 28);
		panel.add(btLimpar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//verifica em qual modo a tela esta
				if(modo == 0){
					dispose();
				}else{
					exluir();
				}
			}
		});
		btnCancelar.setIcon(new ImageIcon(frmCadastroClienteCompradorFis.class.getResource("/br/com/images/delete-.png")));
		btnCancelar.setBounds(261, 420, 140, 28);
		panel.add(btnCancelar);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//verifica em qual modo a tela esta
				if(modo == 0){
					salvar();
				}else{
					atualizar();
				}
			}
		});
		btnCadastrar.setIcon(new ImageIcon(frmCadastroClienteCompradorFis.class.getResource("/br/com/images/apply-.png")));
		btnCadastrar.setBounds(559, 420, 140, 28);
		panel.add(btnCadastrar);
		
		JPanel panelContato = new JPanel();
		panelContato.setBounds(460, 206, 239, 193);
		panelContato.setBorder(new TitledBorder(null, "Contato*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panelContato);
		panelContato.setLayout(null);
		
		ftCelular = new JFormattedTextField(Mascara.setMaskCelularInTf(ftCelular));
		ftCelular.setName("Telefone Celular");
		ftCelular.setBounds(131, 29, 91, 20);
		panelContato.add(ftCelular);
		ftCelular.setColumns(10);
		
		JLabel lblTelefoneResidencialClienteComprador = new JLabel("Telefone Residencial:");
		lblTelefoneResidencialClienteComprador.setBounds(22, 60, 102, 14);
		panelContato.add(lblTelefoneResidencialClienteComprador);
		
		JLabel lblTelefoneCelularClienteComprador = new JLabel("Telefone Celular: *");
		lblTelefoneCelularClienteComprador.setBounds(22, 30, 91, 14);
		panelContato.add(lblTelefoneCelularClienteComprador);
		
		JLabel lblEmailClienteComprador = new JLabel("Email: *");
		lblEmailClienteComprador.setBounds(22, 90, 37, 14);
		panelContato.add(lblEmailClienteComprador);
		
		txtEmail = new JTextField();
		txtEmail.setName("Email");
		txtEmail.setBounds(74, 87, 148, 20);
		panelContato.add(txtEmail);
		txtEmail.setColumns(10);
		
		ftTelefone = new JFormattedTextField(Mascara.setMaskTelefoneInTf(ftTelefone));
		ftTelefone.setName("Telefone Residencial");
		ftTelefone.setBounds(131, 54, 91, 20);
		panelContato.add(ftTelefone);
		ftTelefone.setColumns(10);
		
		JPanel ppanelInfo = new JPanel();
		ppanelInfo.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es B\u00E1sicas*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ppanelInfo.setBounds(15, 11, 684, 172);
		panel.add(ppanelInfo);
		ppanelInfo.setLayout(null);
		
		txtRenda = new JTextField();
		txtRenda.setName("Renda");
		txtRenda.setBounds(329, 117, 112, 20);
		ppanelInfo.add(txtRenda);
		txtRenda.setColumns(10);
		
		JLabel lblRendaClienteComprador = new JLabel("Renda R$:");
		lblRendaClienteComprador.setBounds(268, 120, 51, 14);
		ppanelInfo.add(lblRendaClienteComprador);
		
		txtProfissao = new JTextField();
		txtProfissao.setName("Profiss\u00E3o");
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
		
		cbEstadoCivil = new JComboBox();
		cbEstadoCivil.setName("Estado Civil");
		cbEstadoCivil.setBounds(90, 87, 109, 20);
		ppanelInfo.add(cbEstadoCivil);
		cbEstadoCivil.setToolTipText("Solteiro (a)\r\nCasado (a)\r\nDivorciado (a)\r\nVi\u00FAvo (a)");
		cbEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"Solteiro (a)", "Casado (a)", "Divorciado (a)", "Vi\u00FAvo (a)"}));
		
		ftCpf = new JFormattedTextField(Mascara.setMaskCpfInTf(ftCpf));
		ftCpf.setName("CPF");
		ftCpf.setBounds(90, 57, 109, 20);
		ppanelInfo.add(ftCpf);
		ftCpf.setColumns(10);
		
		JLabel lblRgClienteComprador = new JLabel("RG:");
		lblRgClienteComprador.setBounds(301, 60, 18, 14);
		ppanelInfo.add(lblRgClienteComprador);
		
		txtRg = new JTextField();
		txtRg.setName("RG");
		txtRg.setBounds(329, 57, 112, 20);
		ppanelInfo.add(txtRg);
		txtRg.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setName("Nome");
		txtNome.setBounds(90, 27, 351, 20);
		ppanelInfo.add(txtNome);
		txtNome.setColumns(10);
		
				JPanel panelSexo = new JPanel();
				panelSexo.setBounds(475, 22, 157, 92);
				ppanelInfo.add(panelSexo);
				panelSexo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sexo*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelSexo.setLayout(null);
				
				rbMasculino = new JRadioButton("Masculino");
				rbMasculino.setBounds(22, 22, 109, 23);
				panelSexo.add(rbMasculino);
				rbMasculino.setSelected(true);
				
				rbFeminino = new JRadioButton("Feminino");
				rbFeminino.setBounds(22, 50, 109, 23);
				panelSexo.add(rbFeminino);
				
				grupoSexo.add(rbMasculino);  
				grupoSexo.add(rbFeminino);
				
				JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
				lblDataDeNascimento.setBounds(219, 90, 100, 14);
				ppanelInfo.add(lblDataDeNascimento);
				
				dtNascimento = new JXDatePicker();
				dtNascimento.setFormats(new String[] {"dd/MM/yyyy"});
				dtNascimento.setName("Data de Nascimento");
				dtNascimento.setBounds(329, 87, 112, 20);
				ppanelInfo.add(dtNascimento);
		
		pnEndereco = new JPanel();
		pnEndereco.setLayout(null);
		pnEndereco.setBorder(new TitledBorder(null, "Endere\u00E7o*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnEndereco.setBounds(15, 206, 435, 193);
		panel.add(pnEndereco);
		
		txtEndereco = new JTextField();
		txtEndereco.setEditable(false);
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(94, 56, 318, 20);
		pnEndereco.add(txtEndereco);
		
		ftCep = new JFormattedTextField(Mascara.setMaskCepInTf(ftCep));
		ftCep.setName("CEP");
		ftCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				buscarEndereco();
				txtNumero.requestFocus();
			}
		});
		ftCep.setColumns(10);
		ftCep.setBounds(94, 27, 86, 20);
		pnEndereco.add(ftCep);
		
		txtComplemento = new JTextField();
		txtComplemento.setName("Complemento");
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(94, 150, 318, 20);
		pnEndereco.add(txtComplemento);
		
		JLabel label = new JLabel("N\u00FAmero:");
		label.setBounds(264, 90, 50, 14);
		pnEndereco.add(label);
		
		JLabel label_1 = new JLabel("CEP:");
		label_1.setBounds(21, 30, 32, 14);
		pnEndereco.add(label_1);
		
		JLabel label_2 = new JLabel("Endere\u00E7o:\r\n");
		label_2.setBounds(21, 60, 58, 14);
		pnEndereco.add(label_2);
		
		JLabel label_3 = new JLabel("Complemento:");
		label_3.setBounds(21, 153, 69, 14);
		pnEndereco.add(label_3);
		
		txtNumero = new JTextField();
		txtNumero.setName("N\u00FAmero Residencial");
		txtNumero.setColumns(10);
		txtNumero.setBounds(326, 87, 86, 20);
		pnEndereco.add(txtNumero);
		
		JLabel label_4 = new JLabel("Bairro:");
		label_4.setBounds(21, 90, 46, 14);
		pnEndereco.add(label_4);
		
		txtBairro = new JTextField();
		txtBairro.setEditable(false);
		txtBairro.setColumns(10);
		txtBairro.setBounds(94, 87, 154, 20);
		pnEndereco.add(txtBairro);
		
		JLabel label_5 = new JLabel("Cidade:");
		label_5.setBounds(21, 122, 46, 14);
		pnEndereco.add(label_5);
		
		txtCidade = new JTextField();
		txtCidade.setEditable(false);
		txtCidade.setColumns(10);
		txtCidade.setBounds(94, 119, 154, 20);
		pnEndereco.add(txtCidade);
		
		JLabel label_6 = new JLabel("Estado:");
		label_6.setBounds(264, 122, 50, 14);
		pnEndereco.add(label_6);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		txtEstado.setBounds(326, 119, 86, 20);
		pnEndereco.add(txtEstado);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPesquisar.setBounds(321, 27, 91, 20);
		pnEndereco.add(btnPesquisar);
		
		setLocationRelativeTo(null);		
	}
	
	/**
	 * Esse método serve para pegar os dados da tela e armazenar num Bean para poder salvar no banco de dados
	 * ele faz validação de campos obrigatórios.
	 * @return retorna um bean com os dados necessarios para inserir no banco de dados
	 * @throws EntradaUsuarioException lança essa excessao se o usuario deixar campo obr. em branco
	 * @throws ParseException erro caso haja uma conversao de dados invalida
	 */
	private ClienteCompradorFisica getBean() throws EntradaUsuarioException, ParseException{
		
		//primeiro eu instancio a classe pai que deve ser inserida primeiro
		ClienteComprador cliComprador = new ClienteComprador();
		
		//preencho com os dados necessarios fazendo a validação de campos obrigatórios
		cliComprador.setCep(TelaUtil.getCep(ftCep, true));
		cliComprador.setNumeroEndereco(TelaUtil.getCampoObrigatorio(txtNumero, true));
		cliComprador.setComplementoEndereco(TelaUtil.getCampoObrigatorio(txtComplemento, false)); //false quando nao é obrig.
		cliComprador.setTelefone(TelaUtil.getTelefone(ftTelefone, false));
		cliComprador.setCelular(TelaUtil.getCelular(ftCelular, true));
		
		//verifica se está em modo edição, se estiver ele seta o id do cadastro que deseja alterar
		if(this.modo == 1){
			cliComprador.setCodCliComprador(cadastro.getClienteComprador().getCodCliComprador());
		}
		
		//agora eu vou instancia a classe filha que depende de um ID que ja foi inserido para ser inserida
		ClienteCompradorFisica cliFi = new ClienteCompradorFisica();
		
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
	
	
	/**
	 * Esse método salva os dados no banco
	 */
	private void salvar(){
		try{
			ClienteCompradorFisica bean = getBean(); //mando ele coletar os dados da tela e validadar
			ClienteCompradorFisicaDAO dao = new ClienteCompradorFisicaDAO(); //intancio a dao
			
			dao.inserirClienteCompradorFisica(bean);//chamo o metodo inserir e passo o bean como parametro
			
			//se não der erro ele exibe essa mensagem
			JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			
			//limpa o formulario
			limparFormulario();
		}catch(DAOException e){
			JOptionPane.showMessageDialog(this, "Erro ao tentar Salvar os Dados!", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (EntradaUsuarioException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Este método atualiza os dados no banco
	 */
	private void atualizar(){
		try{
			ClienteCompradorFisica bean = getBean(); //mando ele coletar os dados da tela e validadar
			ClienteCompradorFisicaDAO dao = new ClienteCompradorFisicaDAO(); //intancio a dao
			
			dao.atualizarClienteCompradorFisica(bean);//chamo o metodo atualizar e passo o bean como parametro
			
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
	
	/**
	 * 
	 */
	private void exluir(){
		//verifica se o usuário que mesmo exluir
		int question = JOptionPane.showConfirmDialog(this, "Deseja realmente exluir este cadastro?", "Atenção!!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(question == 0){
			try{
				ClienteCompradorFisicaDAO dao = new ClienteCompradorFisicaDAO(); //intancio a dao
				
				int codigoCliente = this.cadastro.getClienteComprador().getCodCliComprador(); //pega o id do cadastro que esta sendo editado
				
				dao.exluirClienteCompradorFisica(codigoCliente);//chamo o metodo atualizar e passo o codigo como parametro
				
				//se não der erro ele exibe essa mensagem
				JOptionPane.showMessageDialog(this, "Dados exluídos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				
				//volta para modo de cadastro
				modoCadastro();
			}catch(DAOException e){
				JOptionPane.showMessageDialog(this, "Erro ao tentar exluir os Dados!", "Erro", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Este método tem como função buscar o endereçoo de acordo com o cep digitado pelo usuário. Esse método é chamado quando
	 * o campo cep perde o foco!
	 */
	private void buscarEndereco(){
		try{
			Endereco endereco = new EnderecoDAO().buscarPorCep(TelaUtil.getCep(ftCep, true));
			if(endereco != null){
				txtEndereco.setText(endereco.getEndereco());
				txtCidade.setText(endereco.getCidade());
				txtBairro.setText(endereco.getBairro());
				txtEstado.setText(endereco.getEstado());
			}else{
				JOptionPane.showMessageDialog(null, "O cep digitado não existe!");
				ftCep.setText("");
				ftCep.requestFocus();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private void limparFormulario(){
		txtNome.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		txtComplemento.setText("");
		txtEmail.setText("");
		txtEndereco.setText("");
		txtEstado.setText("");
		txtNumero.setText("");
		txtProfissao.setText("");
		txtRenda.setText("");
		txtRg.setText("");
		ftCelular.setText("");
		ftCep.setText("");
		ftCpf.setText("");
		ftTelefone.setText("");
		dtNascimento.setDate(null);
	}
	
	/**
	 * Este método seta na tela os dados atuais de um cadastro para o usuario poder alterar, ele altera a variavel modo para o valor 1 que representa
	 * edição de cadastro. Ele altera o titulo da tela e o texto do botão salvar para ficar facil a interpretação do que o usuario esta fazendo!
	 * @param cadastro
	 */
	private void modoEdicao(ClienteCompradorFisica cadastro){
		setTitle("EDIÇÃO de Cadastro Cliente Comprador / Pessoa Física");
		btnCadastrar.setText("Salvar");
		btnCancelar.setText("Deletar");
		this.modo = 1;
		
		txtNome.setText(cadastro.getNome());
		txtComplemento.setText(cadastro.getClienteComprador().getComplementoEndereco());
		txtEmail.setText(cadastro.getEmail());
		txtNumero.setText(cadastro.getClienteComprador().getNumeroEndereco());
		txtProfissao.setText(cadastro.getProfissao());
		txtRenda.setText(TelaUtil.formataDinheiro(cadastro.getRenda()));
		txtRg.setText(cadastro.getRg());
		ftCelular.setText(cadastro.getClienteComprador().getCelular());
		ftCep.setText(cadastro.getClienteComprador().getCep());
		ftCpf.setText(cadastro.getCpf());
		ftTelefone.setText(cadastro.getClienteComprador().getTelefone());
		dtNascimento.setDate(cadastro.getDataNascimento());
		
		this.cadastro = cadastro;
		
		buscarEndereco();
	}
	
	private void modoCadastro(){
		limparFormulario();
		setTitle("Cadastro Cliente Comprador / Pessoa Física");
		btnCadastrar.setText("Cadastrar");
		btnCancelar.setText("Cancelar");
		this.modo = 0;
		
		this.cadastro = null;
	}
	
}
