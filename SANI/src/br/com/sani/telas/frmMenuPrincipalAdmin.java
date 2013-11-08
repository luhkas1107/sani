package br.com.sani.telas;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import br.com.sani.util.SwingUtil;
import br.com.sani.util.Wallpaper;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class frmMenuPrincipalAdmin extends JFrame {

	private JPanel contentPane;

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
		
		JMenuItem mntmFazerBackup = new JMenuItem("Fazer Backup");
		mntmFazerBackup.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/backup.png")));
		mnBackup.add(mntmFazerBackup);
		
		JMenuItem mntmRestaurarBackup = new JMenuItem("Restaurar Backup");
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
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/exit.png")));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			}
		});
		mnConfiuguracoes.add(mntmSair);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		mnCadastro.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/cadastro.png")));
		menuBarArquivo.add(mnCadastro);
		
		JMenuItem mntmFuncionarios = new JMenuItem("Funcion\u00E1rios");
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
		
		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/cliente.png")));
		mnCadastro.add(mnClientes);
		
		JMenuItem mntmClienteComprador = new JMenuItem("Cliente Comprador");
		mntmClienteComprador.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/cliComp.png")));
		mntmClienteComprador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmCadastroClienteCompradorFis frameCCC = new frmCadastroClienteCompradorFis(null);
			frameCCC.setVisible(true);
			}
		});
		mnClientes.add(mntmClienteComprador);
		
		JMenuItem mntmClienteProprietario = new JMenuItem("Cliente Propriet\u00E1rio");
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
				
		JMenuItem mntmMetas = new JMenuItem("Metas");
		mntmMetas.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/meta.png")));
		mntmMetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmCadastroMetas frameCadMetas = new frmCadastroMetas();
			frameCadMetas.setVisible(true);
			}
		});
		mnCadastro.add(mntmMetas);
		
		JMenu mnVenda = new JMenu("Venda");
		mnVenda.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/money.png")));
		menuBarArquivo.add(mnVenda);
		
		JMenuItem mntmIniciarVenda = new JMenuItem("Iniciar Venda");
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
		
		JMenu mnConsulta = new JMenu("Consulta");
		mnConsulta.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/search.png")));
		menuBarArquivo.add(mnConsulta);
		
		JMenuItem mntmPropriedadesConsulta = new JMenuItem("Propriedades");
		mntmPropriedadesConsulta.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/house.png")));
		mntmPropriedadesConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmConsultaPropriedade frameConsProp = new frmConsultaPropriedade();
			frameConsProp.setVisible(true);
			}
		});
		mnConsulta.add(mntmPropriedadesConsulta);
		
		JMenuItem mntmFuncionariosConsulta = new JMenuItem("Funcion\u00E1rios");
		mntmFuncionariosConsulta.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/func.png")));
		mntmFuncionariosConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmConsultaFuncionario frameConsFun = new frmConsultaFuncionario();
				frameConsFun.setVisible(true);
			}
		});
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/cliente.png")));
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmConsultaClienteComprador frameConsCli = new frmConsultaClienteComprador();
				frameConsCli.setVisible(true);
			}
		});
		mnConsulta.add(mntmClientes);
		mnConsulta.add(mntmFuncionariosConsulta);
		
		JMenuItem mntmMetasConsulta = new JMenuItem("Metas");
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
		
		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/help.png")));
		menuBarArquivo.add(mnAjuda);
		
		JMenuItem mntmTutorialDoSistema = new JMenuItem("Tutorial do Sistema");
		mntmTutorialDoSistema.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/tuto.png")));
		mntmTutorialDoSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**JOptionPane.showMessageDialog(null, "Função não liberada\n" +
				"Função estará disponível em breve!");**/
				
				//Abre o Manual
				
				File pdf = new File("src/br/com/sani/doc/help.pdf");
				try {  
				  Desktop.getDesktop().open(pdf);  
				} catch(Exception ex) {  
				  ex.printStackTrace();  
				  JOptionPane.showMessageDialog(null, "Erro: " + ex);  
				}
				
				
			}
		});
		mnAjuda.add(mntmTutorialDoSistema);
		
		JMenuItem mntmDesenvolvedores = new JMenuItem("Desenvolvedores");
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
		
		JMenuItem mntmSobre = new JMenuItem("Sobre...");
		mntmSobre.setIcon(new ImageIcon(frmMenuPrincipalAdmin.class.getResource("/br/com/images/info2.png")));
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmAbout frameSobre = new frmAbout();
			frameSobre.setVisible(true);
			}
		});
		mnAjuda.add(mntmSobre);
		
	}
}
