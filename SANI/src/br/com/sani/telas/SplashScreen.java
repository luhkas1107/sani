package br.com.sani.telas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class SplashScreen extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar progressBar = new JProgressBar();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public SplashScreen() {
        int width = 450;
        int height =300;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setBackground(new Color(51, 153, 225));
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.LIGHT_GRAY);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel.add(progressBar);
		
		JLabel lblSani = new JLabel("SANI");
		lblSani.setForeground(new Color(0, 0, 0));
		lblSani.setFont(new Font("Verdana", Font.BOLD, 22));
		lblSani.setBounds(270, 34, 80, 53);
		panel.add(lblSani);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setToolTipText("");
		lblLogo.setBounds(10, 11, 173, 188);
		lblLogo.setIcon(new ImageIcon(SplashScreen.class.getResource("/br/com/images/HOME.png")));
		panel.add(lblLogo);
		setContentPane(contentPane);
				
		JLabel lblSistemaAdministrador = new JLabel("Sistema Administrador de");
		lblSistemaAdministrador.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblSistemaAdministrador.setForeground(new Color(0, 0, 0));
		lblSistemaAdministrador.setBounds(201, 88, 233, 29);
		panel.add(lblSistemaAdministrador);
		
		JLabel lblNegciosImobilirios = new JLabel("Neg\u00F3cios Imobili\u00E1rios");
		lblNegciosImobilirios.setForeground(new Color(0, 0, 0));
		lblNegciosImobilirios.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNegciosImobilirios.setBounds(221, 114, 203, 29);
		panel.add(lblNegciosImobilirios);
		
		JLabel lblVersion = new JLabel("Vers\u00E3o 0.3.4");
		lblVersion.setForeground(SystemColor.activeCaptionBorder);
		lblVersion.setFont(new Font("Verdana", Font.ITALIC, 13));
		lblVersion.setBounds(10, 233, 101, 23);
		panel.add(lblVersion);
		
		JLabel lblTodosOsDireitos = new JLabel("Todos os Direitos Reservados \u00A9 2011-2013 \u00A9");
		lblTodosOsDireitos.setForeground(SystemColor.activeCaptionBorder);
		lblTodosOsDireitos.setFont(new Font("Verdana", Font.ITALIC, 13));
		lblTodosOsDireitos.setBounds(132, 233, 323, 23);
		panel.add(lblTodosOsDireitos);
		
		JLabel lblClose = new JLabel("");
		lblClose.setToolTipText("Fechar");
		lblClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblClose.setIcon(new ImageIcon(SplashScreen.class.getResource("/br/com/images/close.png")));
		lblClose.setBackground(new Color(240, 240, 240));
		lblClose.setBounds(415, 11, 25, 25);
		panel.add(lblClose);
		
		
	}

	public void initSplash(){
		this.setVisible(true);
	}
	
	public void setProgresso(int i) {
		progressBar.setValue(i);
		progressBar.setBorderPainted(false);
		progressBar.setString("Carregando...");
		progressBar.setMinimum(0);
		progressBar.setMaximum(500000);
		progressBar.setIndeterminate(false);
		progressBar.setBackground(Color.white);
		progressBar.setForeground(Color.gray);
		progressBar.setFont(new Font("Verdana", Font.PLAIN, 11));
		progressBar.setStringPainted(true);
		progressBar.setBounds(0, 264, 444, 29);		
	}
	
	public void  fechaSplash() {
		this.dispose();
	}
}