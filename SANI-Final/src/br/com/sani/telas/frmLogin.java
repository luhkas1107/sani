package br.com.sani.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.sani.util.SwingUtil;
import com.toedter.calendar.JDateChooser;
import org.jdesktop.swingx.JXDatePicker;

public class frmLogin extends JFrame implements KeyListener {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		/**GRAÇAS AO IGOR**/
		
		SplashScreen teste = new SplashScreen();
		teste.initSplash();
		
        for (int i = 0; i < 500000; i++){  
           System.out.println(i);  
           teste.setProgresso(i);
           
        }				
		
        frmLogin frame = new frmLogin ();
		frame.setVisible(true);
		teste.fechaSplash();
		
		/**Run**/
		
		/**EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin frame = new frmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});**/
	}

	/**
	 * Create the frame.
	 */
	public frmLogin() {
		SwingUtil.lookWindows(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmLogin.class.getResource("/br/com/images/user3.png")));
		setTitle("Login - SANI v0.3.4");
		setBounds(100, 100, 335, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(47, 57, 46, 14);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(47, 85, 46, 14);
		contentPane.add(lblSenha);
		
		JLabel lblLogin_1 = new JLabel("LOGIN");
		lblLogin_1.setFont(new Font("Verdana", Font.BOLD, 14));
		lblLogin_1.setBounds(138, 11, 74, 32);
		contentPane.add(lblLogin_1);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(103, 54, 134, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(this);
		passwordField.setBounds(103, 82, 134, 20);
		contentPane.add(passwordField);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			}
		});
		btnCancelar.setBounds(10, 125, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				login();
			}
		});
		
		btnLogar.setBounds(230, 125, 89, 23);
		contentPane.add(btnLogar);
		
		setLocationRelativeTo(null);		
	}
	
	public void login(){
		String senhaAdm = new String(passwordField.getPassword()); 
		String  userAdm = txtLogin.getText();
		
		String senhaFunc = new String(passwordField.getPassword());
		String userFunc = txtLogin.getText();
		
		//COLOCAR userAdm.equals(getUser) && senhaAdm.equals(getPassword)){
		
		if(userAdm.equals("admin")&& senhaAdm.equals("admin")){
			frmMenuPrincipalAdmin frameMPA = new frmMenuPrincipalAdmin();
			frameMPA.setVisible(true);
			fechaLogin();
		}else if(userFunc.equals("teste") && senhaFunc.equals("teste")){
			frmMenuPrincipalFuncionario frameMPF = new frmMenuPrincipalFuncionario();
			frameMPF.setVisible(true);
			fechaLogin();
		}else{
			JOptionPane.showMessageDialog(null, "Login e/ou Senha Incorretos!");
		}
	}
	
	public void fechaLogin(){
		this.dispose();
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
