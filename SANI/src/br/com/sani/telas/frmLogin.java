package br.com.sani.telas;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.sani.exception.DAOException;
import br.com.sani.login.Login;
import br.com.sani.login.LoginDAO;
import br.com.sani.util.SwingUtil;

public class frmLogin extends JFrame implements KeyListener {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField passwordField;

	//Launch the application
	
	public static void main(String[] args) {
		
		//GRAÇAS AO IGOR
		
		SplashScreen teste = new SplashScreen();
		teste.initSplash();
		
        for (int i = 0; i < 100000; i++){  
           System.out.println(i);  
           teste.setProgresso(i);
           
        }				
		
        frmLogin frame = new frmLogin ();
		frame.setVisible(true);
		teste.fechaSplash();
		
	}

	//Create the frame
	public frmLogin() {
		SwingUtil.lookWindows(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmLogin.class.getResource("/br/com/images/user3.png")));
		setTitle("Login - SANI BETA VERSION 0.9.4");
		setBounds(100, 100, 488, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Nome de usu\u00E1rio:");
		lblLogin.setBounds(31, 82, 84, 14);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(81, 119, 34, 14);
		contentPane.add(lblSenha);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(139, 79, 134, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(this);
		passwordField.setBounds(139, 116, 134, 20);
		contentPane.add(passwordField);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(frmLogin.class.getResource("/br/com/images/delete-.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(71, 185, 103, 32);
		contentPane.add(btnCancelar);
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.setIcon(new ImageIcon(frmLogin.class.getResource("/br/com/images/start.png")));
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				login();
			}
		});
		
		btnLogar.setBounds(184, 185, 89, 32);
		contentPane.add(btnLogar);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(frmLogin.class.getResource("/br/com/images/HOME.png")));
		lblLogo.setBounds(299, 38, 173, 179);
		contentPane.add(lblLogo);
		
		JLabel lblIniciarAcesso = new JLabel("Iniciar Acesso");
		lblIniciarAcesso.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIniciarAcesso.setBounds(139, 31, 134, 20);
		contentPane.add(lblIniciarAcesso);
		
		setLocationRelativeTo(null);		
	}
	
	public void login(){
		String senha = String.copyValueOf(passwordField.getPassword());
		String user = txtLogin.getText().trim();
		
		try {
			Login login = new LoginDAO().fazerLogin(user, senha);
			
			if(login != null){
				new frmMenuPrincipal();
				dispose();
			}else{
				JOptionPane.showMessageDialog(this, "Acesso Negado, login ou senha incorreto!", "Falha no acesso!", JOptionPane.WARNING_MESSAGE);
			}
			
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getSource() == passwordField){
			if(event.getKeyCode() == 10){
				login();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
