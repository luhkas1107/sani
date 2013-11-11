package br.com.sani.telas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.ParseException;

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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.sani.exception.DAOException;
import br.com.sani.exception.EntradaUsuarioException;
import br.com.sani.login.Login;
import br.com.sani.login.LoginDAO;
import br.com.sani.util.SwingUtil;
import br.com.sani.util.TelaUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmCadastroLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtUser;
	private JPasswordField psfSenha;
	private JPasswordField psfConfSenha;
	private JComboBox comboBox;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCadastroLogin frame = new frmCadastroLogin();
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
	public frmCadastroLogin(){
		initComponets();
	}
	
	public void initComponets() {
		SwingUtil.lookWindows(this);
		setTitle("Novo Usu\u00E1rio");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmCadastroLogin.class.getResource("/br/com/images/user3.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 290, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(59, 14, 31, 14);
		contentPane.add(lblNome);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setBounds(50, 47, 40, 14);
		contentPane.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(55, 80, 35, 14);
		contentPane.add(lblSenha);
		
		JLabel lblConfirmaSenha = new JLabel("Confirma Senha:");
		lblConfirmaSenha.setBounds(10, 111, 80, 14);
		contentPane.add(lblConfirmaSenha);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(62, 146, 28, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPermisso = new JLabel("Permiss\u00E3o:");
		lblPermisso.setBounds(39, 180, 52, 14);
		contentPane.add(lblPermisso);
		
		txtNome = new JTextField();
		txtNome.setBounds(123, 11, 127, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtUser = new JTextField();
		txtUser.setBounds(123, 44, 127, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		psfSenha = new JPasswordField();
		psfSenha.setBounds(123, 77, 127, 20);
		contentPane.add(psfSenha);
		
		psfConfSenha = new JPasswordField();
		psfConfSenha.setBounds(123, 108, 127, 20);
		contentPane.add(psfConfSenha);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Normal"}));
		comboBox.setBounds(123, 177, 127, 20);
		contentPane.add(comboBox);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(frmCadastroLogin.class.getResource("/br/com/images/delete-.png")));
		btnCancelar.setBounds(10, 219, 114, 32);
		contentPane.add(btnCancelar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					inserirLogin();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (EntradaUsuarioException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSalvar.setIcon(new ImageIcon(frmCadastroLogin.class.getResource("/br/com/images/save.png")));
		btnSalvar.setBounds(146, 219, 104, 32);
		contentPane.add(btnSalvar);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(123, 143, 127, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
	}
	
	private Login getBean() throws DAOException, SQLException, EntradaUsuarioException{
				
		Login l = new Login();
		
		l.setNome(TelaUtil.getCampoObrigatorio(txtNome, true));
		l.setUser(TelaUtil.getCampoObrigatorio(txtUser, true));
		l.setSenha(TelaUtil.getSenha(psfSenha, psfConfSenha));
		l.setEmail(TelaUtil.getEmail(txtEmail));
		l.setPermissao(comboBox.getSelectedItem().toString().substring(0, 1));
				
		return l;
	}
	
	private void inserirLogin() throws SQLException, EntradaUsuarioException{
		try {
			Login l = getBean();
			new LoginDAO().inserir(l);
			JOptionPane.showMessageDialog(this, "Login gerada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
			limparFormulario();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	private void limparFormulario(){
		
	}
}
