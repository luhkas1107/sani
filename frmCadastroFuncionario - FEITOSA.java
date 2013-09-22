package br.com.sani.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.InputMismatchException;

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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.sani.bean.Funcionario;
import br.com.sani.dao.FuncionarioDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;

public class frmCadastroFuncionario extends JFrame {
	JComboBox comboBoxEstadoCivilFuncionario = new JComboBox();
	private JPanel contentPane;
	private JTextField txtNomeFuncionario;
	//private JTextField txtCpfFuncionario;
	//private JTextField txtRgFuncionario;
	private JTextField txtEnderecoFuncionario;
	private JTextField txtNumeroEnderecoFuncionario;
	private JTextField txtComplementoFuncionario;
	//private JTextField txtCepFuncionario;
	//private JTextField txtTelefoneResidencialFuncionario;
	//private JTextField txtTelefoneCelularFuncionario;
	private JTextField txtSiteFuncionario;
	private JTextField txtEmailFuncionario;
	private JTextField txtNacionalidadeFuncionario;
	private JTextField txtLoginFuncionario;
	private JPasswordField passwordFieldSenhaFuncionario;
	private JPasswordField passwordFieldConfSenhaFuncionario;
	private JTextField txtRegistroFuncionario;
	
	private JFormattedTextField ftCepFuncionario;
	private JFormattedTextField ftCpfFuncionario;
	private JFormattedTextField ftRgFuncionario;
	private JFormattedTextField ftTelefoneResidencialFuncionario;
	private JFormattedTextField ftTelefoneCelularFuncionario;
	JRadioButton rdbtnMasculinoFuncionario = new JRadioButton("Masculino");
	JRadioButton rdbtnFemininoFuncionario = new JRadioButton("Feminino");
	String sexo;
	JFormattedTextField formattedDataNascimento = new JFormattedTextField();
	String funcao2;
	JRadioButton rdbtnCargoCorretorFuncionario = new JRadioButton("Corretor (a) de Im\u00F3veis");
	JRadioButton rdbtnCargoSecretariaFuncionario = new JRadioButton("Secret\u00E1ria ");
	JRadioButton rdbtnCargoGerenteFuncionario = new JRadioButton("Gerente");
	JRadioButton rdbtnCargoConsultorFuncionario = new JRadioButton("Consultor (a)");
	boolean resultadoetapa1;
	boolean resultadoetapa2;
	boolean etapa1=false;
	boolean etapa2=false;
	String senha;
	String c_senha;
	
	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCadastroFuncionario frame = new frmCadastroFuncionario();
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

void verificacao1 (){
	SwingUtil.lookWindows(this);

	if (rdbtnMasculinoFuncionario.isSelected()){
	sexo = "M";
	}else{
	sexo = "F";
	}

	if (rdbtnCargoCorretorFuncionario.isSelected()){
		funcao2="Corretor";
	}else if (rdbtnCargoConsultorFuncionario.isSelected()){
		funcao2 = "Consultor";
	}else if (rdbtnCargoGerenteFuncionario.isSelected()){
		funcao2 = "Gerente";
	}else if (rdbtnCargoSecretariaFuncionario.isSelected()){
		funcao2="Secretaria";
	}
	
	


		String nome2 = txtNomeFuncionario.getText(); // ok
		String sexo2 = sexo; // ok
		String cpf2 = ftRgFuncionario.getText().toString();//ok
		String rg2 = ftRgFuncionario.getText().toString(); //ok
		String estadocivil2 = comboBoxEstadoCivilFuncionario.getSelectedItem().toString(); // ok
		String nacionalidade2 = txtNacionalidadeFuncionario.getText(); //ok
		String endereco2 = txtEnderecoFuncionario.getText(); //ok
		String numeroendereco2 = txtNumeroEnderecoFuncionario.getText(); //ok
		String complemento2 = txtComplementoFuncionario.getText();//ok
		String cep2 = ftCepFuncionario.getText().toString();//ok
		String telefone2 = ftTelefoneResidencialFuncionario.getText().toString(); //ok
		String celular2 = ftTelefoneCelularFuncionario.getText().toString(); //ok
		String email2 = txtEmailFuncionario.getText(); //ok
		String datanascimento2 = formattedDataNascimento.getText().toString(); //ok
		String funcao3 = funcao2;  
		String login2 = txtLoginFuncionario.getText();
		String senha2 = passwordFieldSenhaFuncionario.getText().toString();
		senha = new String(passwordFieldSenhaFuncionario.getPassword());
		c_senha = new String(passwordFieldConfSenhaFuncionario.getPassword());
		
	if ( !nome2.equals("") && !endereco2.equals("") && !numeroendereco2.equals("") && !sexo2.equals("")
			&& !cpf2.equals("") && !rg2.equals("") && !estadocivil2.equals("") && !nacionalidade2.equals("") && 
			 !cep2 .equals("") && !email2.equals("") && !datanascimento2.equals("")
			 && !funcao3.equals("") && !login2.equals("") && !senha.equals("") && !c_senha.equals("")){
		etapa1 = true;
			
	}				
		else {
		etapa1 = false;
		
	}
	resultadoetapa1=etapa1;
	JOptionPane.showMessageDialog(null, "Resultado:" +resultadoetapa1);
	
		
		
		
		
}



void verificacao2 (){
	SwingUtil.lookWindows(this);
	if (senha.equals(c_senha) && !senha.equals("")){
		etapa2 = true;
	}
	else {
		etapa2 = false;
	}
	resultadoetapa2 = etapa2;
	JOptionPane.showMessageDialog(null, "Resultado:" +resultadoetapa2);
	}


String retiraMascara(String mascarado){
	mascarado = mascarado.replace(".", "");
	mascarado = mascarado.replace("-", "");  
	  
	  return mascarado;
	  
  }
  


boolean validacpf(String cpf){
	SwingUtil.lookWindows(this);
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		    if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
		        cpf.equals("22222222222") || cpf.equals("33333333333") ||
		        cpf.equals("44444444444") || cpf.equals("55555555555") ||
		        cpf.equals("66666666666") || cpf.equals("77777777777") ||
		        cpf.equals("88888888888") || cpf.equals("99999999999") ||
		       (cpf.length() != 11))
		       return(false);

		    char dig10, dig11;
		    int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		    try {
		// Calculo do 1o. Digito Verificador
		      sm = 0;
		      peso = 10;
		      for (i=0; i<9; i++) {              
		// converte o i-esimo caractere do CPF em um numero:
		// por exemplo, transforma o caractere '0' no inteiro 0         
		// (48 eh a posicao de '0' na tabela ASCII)         
		        num = (int)(cpf.charAt(i) - 48); 
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }

		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig10 = '0';
		      else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

		// Calculo do 2o. Digito Verificador
		      sm = 0;
		      peso = 11;
		      for(i=0; i<10; i++) {
		        num = (int)(cpf.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }

		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig11 = '0';
		      else dig11 = (char)(r + 48);

		// Verifica se os digitos calculados conferem com os digitos informados.
		      if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
		         return(true);
		      else return(false);
		    } catch (InputMismatchException erro) {
		        return(false);
		    }
		  }

		  public static String imprimeCPF(String CPF) {
		    return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
		      CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
		  }



	
void pegar (){
	//LoginFuncionario novologin = new LoginFuncionario();
	//login = novologin.login;	
	//senha = novologin.senha;
	SwingUtil.lookWindows(this);
		if (rdbtnMasculinoFuncionario.isSelected()){
			sexo = "M";
		}else{
			sexo = "F";
		}
		if (rdbtnCargoCorretorFuncionario.isSelected()){
			funcao2="Corretor";
		}else if (rdbtnCargoConsultorFuncionario.isSelected()){
			funcao2 = "Consultor";
		}else if (rdbtnCargoGerenteFuncionario.isSelected()){
			funcao2 = "Gerente";
		}else if (rdbtnCargoSecretariaFuncionario.isSelected()){
			funcao2="Secretaria";
		}
		JOptionPane.showMessageDialog(null, "funcao :" +funcao2);
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome(txtNomeFuncionario.getText());
		funcionario.setCpf(ftRgFuncionario.getText().toString());
		funcionario.setEstadoCivil(comboBoxEstadoCivilFuncionario.getSelectedItem().toString());
		funcionario.setNacionalidade(txtNacionalidadeFuncionario.getText());
		funcionario.setEndereco(txtEnderecoFuncionario.getText());
		funcionario.setNumeroEndereco(txtEnderecoFuncionario.getText());	
		funcionario.setComplementoEndereco(txtComplementoFuncionario.getText());
		funcionario.setCep(ftCepFuncionario.getText().toString());
		funcionario.setTelefoneResidencial(ftTelefoneResidencialFuncionario.getText().toString());
		funcionario.setTelefoneCelular(ftTelefoneCelularFuncionario.getText().toString());
		funcionario.setEmailPessoal(txtEmailFuncionario.getText());
		funcionario.setDataNascimento(formattedDataNascimento.getText().toString());
		funcionario.setCargoFuncionario(funcao2);
		funcionario.setLoginFuncionario(txtLoginFuncionario.getText());
		funcionario.setSenhaFuncionario(passwordFieldSenhaFuncionario.getText().toString());
		funcionario.setSiteFuncionario(txtSiteFuncionario.getText());
		funcionario.setSexo(sexo);
		funcionario.setRg(ftRgFuncionario.getText().toString());
		
		try {
			FuncionarioDAO funcDao = new FuncionarioDAO();
			funcDao.inserirFuncionario(funcionario);
			
			JOptionPane.showMessageDialog(null, "Cadastrado com Suscesso!");
			limpaFormulario();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JOptionPane.showMessageDialog(null, "ID:"); // Aqui iremos puxar o id gerado pelo banco!
		
		}


	public frmCadastroFuncionario() throws Throwable {
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
		
		rdbtnMasculinoFuncionario.setBounds(56, 32, 109, 23);
		panel.add(rdbtnMasculinoFuncionario);
		
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
		
		ftCpfFuncionario = new JFormattedTextField(Mascara.setMaskCpfInTf(ftCpfFuncionario));
		ftCpfFuncionario.setColumns(10);
		ftCpfFuncionario.setBounds(57, 58, 109, 20);
		panel.add(ftCpfFuncionario);
	
		MaskFormatter data= new MaskFormatter();
		data.setMask("##/##/####");
				
		
		JLabel lblEstadoCivilFuncionario = new JLabel("Estado Civil:");
		lblEstadoCivilFuncionario.setBounds(10, 86, 81, 14);
		panel.add(lblEstadoCivilFuncionario);
		
		comboBoxEstadoCivilFuncionario.setModel(new DefaultComboBoxModel(new String[] {"Solteiro (a)", "Casado (a)", "Divorciado (a)", "Vi\u00FAvo (a)"}));
		comboBoxEstadoCivilFuncionario.setBounds(80, 83, 137, 20);
		panel.add(comboBoxEstadoCivilFuncionario);
		
		JLabel lblRgFuncionario = new JLabel("RG:");
		lblRgFuncionario.setBounds(182, 61, 46, 14);
		panel.add(lblRgFuncionario);
		
		ftRgFuncionario = new JFormattedTextField();
		ftRgFuncionario.setColumns(10);
		ftRgFuncionario.setBounds(215, 58, 109, 20);
		panel.add(ftRgFuncionario);
		
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
		
		ftCepFuncionario = new JFormattedTextField(Mascara.setMaskCepInTf(ftCepFuncionario));
		ftCepFuncionario.setColumns(10);
		ftCepFuncionario.setBounds(265, 133, 86, 20);
		panel.add(ftCepFuncionario);
		
		JLabel lblTelefoneResidencialFuncionario = new JLabel("Telefone Residencial:");
		lblTelefoneResidencialFuncionario.setBounds(10, 161, 132, 14);
		panel.add(lblTelefoneResidencialFuncionario);
		
		ftTelefoneResidencialFuncionario = new JFormattedTextField(Mascara.setMaskTelefoneInTf(ftTelefoneResidencialFuncionario));
		ftTelefoneResidencialFuncionario.setColumns(10);
		ftTelefoneResidencialFuncionario.setBounds(136, 158, 106, 20);
		panel.add(ftTelefoneResidencialFuncionario);
		
		JLabel lblTelefoneCelularFuncionario = new JLabel("Telefone Celular: *");
		lblTelefoneCelularFuncionario.setBounds(265, 161, 118, 14);
		panel.add(lblTelefoneCelularFuncionario);
		
		ftTelefoneCelularFuncionario = new JFormattedTextField(Mascara.setMaskCelularInTf(ftTelefoneCelularFuncionario));
		ftTelefoneCelularFuncionario.setColumns(10);
		ftTelefoneCelularFuncionario.setBounds(372, 158, 106, 20);
		panel.add(ftTelefoneCelularFuncionario);
		
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
		btnSalvarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				
				String cpf = ftCpfFuncionario.getText().toString();
				
				
				verificacao1();

				verificacao2();
				
				if (resultadoetapa1 == true && resultadoetapa2 == true){
				if(validacpf(retiraMascara(cpf))){
						
						JOptionPane.showMessageDialog(null, "cpf sem mascara: " +cpf);
						pegar();
						}else{
							JOptionPane.showMessageDialog(null, "Cpf inválido!");
							
						}
					
				}
				
				if (resultadoetapa1 == false){
					JOptionPane.showMessageDialog(null, "Favor preencher todos os campos importantes!");
					
				}
				if (resultadoetapa2 == false){
					JOptionPane.showMessageDialog(null, "Verifique sua senha pois pode estar vazio ou diferente.");
				}
				

				
				
			
			}
		});
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
		
		//4321
		rdbtnCargoCorretorFuncionario.setBounds(343, 225, 166, 23);
		panel.add(rdbtnCargoCorretorFuncionario);
		
		rdbtnCargoSecretariaFuncionario.setBounds(343, 251, 109, 23);
		panel.add(rdbtnCargoSecretariaFuncionario);
		
		rdbtnCargoGerenteFuncionario.setBounds(343, 277, 109, 23);
		panel.add(rdbtnCargoGerenteFuncionario);
		
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
		
		
		formattedDataNascimento= new JFormattedTextField(data);
		formattedDataNascimento.setBounds(408, 33, 81, 20);
		panel.add(formattedDataNascimento);
		
		JLabel lblDataNascimento = new JLabel("Data nascimento");
		lblDataNascimento.setBounds(298, 36, 100, 14);
		panel.add(lblDataNascimento);
		
		setLocationRelativeTo(null);		
	}
	
	public void fechar(){
		this.dispose();
	}
	
	public void limpaFormulario(){
		txtNomeFuncionario.setText("");
		ftCpfFuncionario.setText("");
		ftRgFuncionario.setText("");
		txtNacionalidadeFuncionario.setText("");
		txtEnderecoFuncionario.setText("");
		txtNumeroEnderecoFuncionario.setText("");
		txtComplementoFuncionario.setText("");
		ftCepFuncionario.setText("");
		ftTelefoneResidencialFuncionario.setText("");
		ftTelefoneCelularFuncionario.setText("");
		txtLoginFuncionario.setText("");
		txtSiteFuncionario.setText("");
		txtEmailFuncionario.setText("");
		txtLoginFuncionario.setText("");
		passwordFieldSenhaFuncionario.setText("");
		passwordFieldConfSenhaFuncionario.setText("");
	}
}
