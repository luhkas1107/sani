package br.com.sani.telas;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;
import java.text.ParseException;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import br.com.sani.util.SwingUtil;
import br.com.sani.util.Wallpaper;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmMenuPrincipalAdmin extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmSair;
	private JMenuItem mntmFazerBackup;
	private JMenuItem mntmRestaurarBackup;
	private JMenu mnCadastro;
	private JMenu mnClientes;
	private JMenuItem mntmClienteComprador;
	private JMenuItem mntmClienteProprietario;
	private JMenuItem mntmFuncionarios;
	private JMenuItem mntmMetas;
	private JMenu mnVenda;
	private JMenuItem mntmIniciarVenda;
	private JMenu mnConsulta;
	private JMenuItem mntmPropriedadesConsulta;
	private JMenuItem mntmClientes;
	private JMenuItem mntmFuncionariosConsulta;
	private JMenuItem mntmMetasConsulta;
	private JMenu mnAjuda;
	private JMenuItem mntmTutorialDoSistema;
	private JMenuItem mntmDesenvolvedores;
	private JMenuItem mntmSobre;
	private JMenuItem mnConfiguracoes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMenuPrincipalAdmin frame = new frmMenuPrincipalAdmin();
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
	public frmMenuPrincipalAdmin() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		SwingUtil.lookWindows(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmMenuPrincipalAdmin.class.getResource("/br/com/images/home_badge.png")));
		setTitle("ADMINISTRADOR - Sistema Administrador de Negócios Imobiliários - v0.3.4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		contentPane = new Wallpaper();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(false);
		
		JMenuBar menuBarArquivo = new JMenuBar();
		setJMenuBar(menuBarArquivo);
		
		JMenu mnConfiuguracoes = new JMenu("Configuracoes");
		mnConfiuguracoes.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/settings2.png")));
		menuBarArquivo.add(mnConfiuguracoes);

		JMenu mnBackup = new JMenu("Backup ...");
		mnBackup.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/backup.png")));
		mnConfiuguracoes.add(mnBackup);
		
		mntmFazerBackup = new JMenuItem("Fazer Backup");
		mntmFazerBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mntmFazerBackup.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/backup.png")));
		mnBackup.add(mntmFazerBackup);
		
		mntmRestaurarBackup = new JMenuItem("Restaurar Backup");
		mntmRestaurarBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mntmRestaurarBackup.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/restore.png")));
		mntmRestaurarBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmBackupRestaurar restaura = new frmBackupRestaurar();
				restaura.setVisible(true);
			}
		});
		mnBackup.add(mntmRestaurarBackup);
		mntmFazerBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmBackupCriar backup = new frmBackupCriar();
				backup.setVisible(true);
			}
		});
		
		JSeparator separator = new JSeparator();
		mnConfiuguracoes.add(separator);
		
		mntmSair = new JMenuItem("Sair");
		mntmSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmSair.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/exit.png")));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int resposta = 0; 
				resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente Sair?", "ATENÇÃO!!!", resposta, JOptionPane.WARNING_MESSAGE);
				
				if (resposta == JOptionPane.YES_OPTION) {
					// verifica se o usuário clicou no botão YES
					System.exit(0);				 
				}
			}
		});
		mnConfiuguracoes.add(mntmSair);
		
		mnCadastro = new JMenu("Cadastro");
		mnCadastro.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/cadastro.png")));
		menuBarArquivo.add(mnCadastro);
		
		mntmFuncionarios = new JMenuItem("Funcion\u00E1rios");
		mntmFuncionarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmFuncionarios.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/func.png")));
		mntmFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmCadastroFuncionario frameCadFun = null;
			try {
				frameCadFun = new frmCadastroFuncionario();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frameCadFun.setVisible(true);
			}
		});
		
		JMenuItem mntmPropriedades = new JMenuItem("Propriedades");
		mntmPropriedades.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmPropriedades.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/house.png")));
		mntmPropriedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmCadastroPropriedade frameCadProp;
			try {
				frameCadProp = new frmCadastroPropriedade();
				frameCadProp.setVisible(true);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		});
		mnCadastro.add(mntmPropriedades);
		
		mnClientes = new JMenu("Clientes");
		mnClientes.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/cliente.png")));
		mnCadastro.add(mnClientes);
		
		mntmClienteComprador = new JMenuItem("Cliente Comprador");
		mntmClienteComprador.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmClienteComprador.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/cliComp.png")));
		mntmClienteComprador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmCadastroClienteCompradorFis frameCCC = new frmCadastroClienteCompradorFis(null);
			frameCCC.setVisible(true);
			}
		});
		mnClientes.add(mntmClienteComprador);
		
		mntmClienteProprietario = new JMenuItem("Cliente Propriet\u00E1rio");
		mntmClienteProprietario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmClienteProprietario.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/cliProp.png")));
		mntmClienteProprietario.setHorizontalAlignment(SwingConstants.LEFT);
		mntmClienteProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCadastroClienteProprietarioFisica frameCadCliProp;
				try {
					frameCadCliProp = new frmCadastroClienteProprietarioFisica();
					frameCadCliProp.setVisible(true);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//frameCadCliProp.setVisible(true);
				
			}
		});
		mnClientes.add(mntmClienteProprietario);

		mnCadastro.add(mntmFuncionarios);
				
		mntmMetas = new JMenuItem("Metas");
		mntmMetas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmMetas.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/meta.png")));
		mntmMetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmCadastroMetas frameCadMetas = new frmCadastroMetas();
			frameCadMetas.setVisible(true);
			}
		});
		mnCadastro.add(mntmMetas);
		
		mnVenda = new JMenu("Venda");
		mnVenda.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/money.png")));
		menuBarArquivo.add(mnVenda);
		
		mntmIniciarVenda = new JMenuItem("Iniciar Venda");
		mntmIniciarVenda.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/start.png")));
		mntmIniciarVenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK));
		mntmIniciarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frmVenda venda = new frmVenda();
					venda.setVisible(true);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnVenda.add(mntmIniciarVenda);
		
		mnConsulta = new JMenu("Consulta");
		mnConsulta.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/search.png")));
		menuBarArquivo.add(mnConsulta);
		
		mntmPropriedadesConsulta = new JMenuItem("Propriedades");
		mntmPropriedadesConsulta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
		mntmPropriedadesConsulta.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/house.png")));
		mntmPropriedadesConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmConsultaPropriedade frameConsProp = new frmConsultaPropriedade();
			frameConsProp.setVisible(true);
			}
		});
		mnConsulta.add(mntmPropriedadesConsulta);
		
		mntmFuncionariosConsulta = new JMenuItem("Funcion\u00E1rios");
		mntmFuncionariosConsulta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
		mntmFuncionariosConsulta.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/func.png")));
		mntmFuncionariosConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmConsultaFuncionario frameConsFun = new frmConsultaFuncionario();
				frameConsFun.setVisible(true);
			}
		});
		
		mntmClientes = new JMenuItem("Clientes");
		mntmClientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mntmClientes.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/cliente.png")));
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmConsultaClientes frameConsCli = new frmConsultaClientes();
				frameConsCli.setVisible(true);
			}
		});
		mnConsulta.add(mntmClientes);
		mnConsulta.add(mntmFuncionariosConsulta);
		
		mntmMetasConsulta = new JMenuItem("Metas");
		mntmMetasConsulta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_MASK));
		mntmMetasConsulta.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/meta.png")));
		mntmMetasConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmConsultaMetas frameConsMetas;
			try {
				frameConsMetas = new frmConsultaMetas();
				frameConsMetas.setVisible(true);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		mnConsulta.add(mntmMetasConsulta);
		
		JMenu mnRelatorio = new JMenu("Relat\u00F3rio");
		mnRelatorio.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/report1.png")));
		menuBarArquivo.add(mnRelatorio);
		
		JMenuItem mntmEmitirRelatorio = new JMenuItem("Emitir Relat\u00F3rio...");
		mntmEmitirRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Função não liberada\n" +
				"Função estará disponível em breve!");
			}
		});
		mnRelatorio.add(mntmEmitirRelatorio);
		
		mnAjuda = new JMenu("Ajuda");
		mnAjuda.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/help.png")));
		menuBarArquivo.add(mnAjuda);
		
		mntmTutorialDoSistema = new JMenuItem("Tutorial do Sistema");
		mntmTutorialDoSistema.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmTutorialDoSistema.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/tuto.png")));
		mntmTutorialDoSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Abre o Manual
				pdf();
			}
		});
		mnAjuda.add(mntmTutorialDoSistema);
		
		mntmDesenvolvedores = new JMenuItem("Desenvolvedores");
		mntmDesenvolvedores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mntmDesenvolvedores.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/dev.png")));
		mntmDesenvolvedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmDesenvolvedores frameDev = new frmDesenvolvedores();
			frameDev.setVisible(true);
			
			}
		});
		mnAjuda.add(mntmDesenvolvedores);
		
		JSeparator separator_1 = new JSeparator();
		mnAjuda.add(separator_1);
		
		mntmSobre = new JMenuItem("Sobre...");
		mntmSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mntmSobre.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/info2.png")));
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmAbout frameSobre = new frmAbout();
			frameSobre.setVisible(true);
			}
		});
		mnAjuda.add(mntmSobre);
		
	}
	
	public void pdf(){
		File pdf = new File("src/br/com/sani/doc/help.pdf");
		try {  
		  Desktop.getDesktop().open(pdf);  
		} catch(Exception ex) {  
		  ex.printStackTrace();  
		  JOptionPane.showMessageDialog(null, "Erro: " + ex);  
		}
	}
	
	public void usuarioLogado(){
		
	}
}
