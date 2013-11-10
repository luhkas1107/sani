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

public class frmMenuPrincipal extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmSair;
	private JMenuItem mntmFazerBackup;
	private JMenuItem mntmRestaurarBackup;
	private JMenu mnCadastro;
	private JMenu mnClientes;
	private JMenuItem mntmMetas;
	private JMenu mnVenda;
	private JMenuItem mntmIniciarVenda;
	private JMenu mnConsulta;
	private JMenuItem mntmPropriedadesConsulta;
	private JMenuItem mntmMetasConsulta;
	private JMenu mnAjuda;
	private JMenuItem mntmTutorialDoSistema;
	private JMenuItem mntmDesenvolvedores;
	private JMenuItem mntmSobre;
	private JMenuItem mnConfiguracoes;
	private JMenu mnClienteComprador;
	private JMenu mnClientes_1;
	private JMenuItem mntmClienteComprador;
	private JMenuItem mntmClienteProprietrio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMenuPrincipal frame = new frmMenuPrincipal();
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
	public frmMenuPrincipal() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		SwingUtil.lookWindows(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmMenuPrincipal.class.getResource("/br/com/images/home_badge.png")));
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
		mnConfiuguracoes.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/settings2.png")));
		menuBarArquivo.add(mnConfiuguracoes);

		JMenu mnBackup = new JMenu("Backup ...");
		mnBackup.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/backup.png")));
		mnConfiuguracoes.add(mnBackup);
		
		mntmFazerBackup = new JMenuItem("Fazer Backup");
		mntmFazerBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mntmFazerBackup.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/backup.png")));
		mnBackup.add(mntmFazerBackup);
		
		mntmRestaurarBackup = new JMenuItem("Restaurar Backup");
		mntmRestaurarBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mntmRestaurarBackup.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/restore.png")));
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
		mntmSair.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/exit.png")));
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
		mnCadastro.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/cadastro.png")));
		menuBarArquivo.add(mnCadastro);
		
		JMenuItem mntmPropriedades = new JMenuItem("Propriedades");
		mntmPropriedades.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmPropriedades.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/house.png")));
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
		mnClientes.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/cliente.png")));
		mnCadastro.add(mnClientes);
		
		mnClienteComprador = new JMenu("Cliente Comprador");
		mnClienteComprador.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/cliComp.png")));
		mnClientes.add(mnClienteComprador);
		
		JMenuItem mntmPessoaFisica = new JMenuItem("Pessoa Fisica");
		mntmPessoaFisica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new frmCadastroClienteCompradorFis(null);
			}
		});
		mnClienteComprador.add(mntmPessoaFisica);
		
		JMenuItem mntmPessoaJuridica = new JMenuItem("Pessoa Juridica");
		mntmPessoaJuridica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new frmCadastroClienteCompradorJur(null);
			}
		});
		mnClienteComprador.add(mntmPessoaJuridica);
		
		JMenu mnClienteProprietrio = new JMenu("Cliente Propriet\u00E1rio");
		mnClienteProprietrio.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/cliProp.png")));
		mnClientes.add(mnClienteProprietrio);
		
		JMenuItem mntmPessoaFisica_1 = new JMenuItem("Pessoa Fisica");
		mntmPessoaFisica_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new frmCadastroClienteProprietarioFisica();
			}
		});
		mnClienteProprietrio.add(mntmPessoaFisica_1);
		
		JMenuItem mntmPessoaJuridica_1 = new JMenuItem("Pessoa Juridica");
		mntmPessoaJuridica_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new frmCadastroClienteProprietarioJuridica(null);
			}
		});
		mnClienteProprietrio.add(mntmPessoaJuridica_1);
				
		mntmMetas = new JMenuItem("Metas");
		mntmMetas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmMetas.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/meta.png")));
		mntmMetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmCadastroMetas frameCadMetas = new frmCadastroMetas();
			frameCadMetas.setVisible(true);
			}
		});
		mnCadastro.add(mntmMetas);
		
		mnVenda = new JMenu("Venda");
		mnVenda.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/money.png")));
		menuBarArquivo.add(mnVenda);
		
		mntmIniciarVenda = new JMenuItem("Fechar Venda/Loca\u00E7\u00E3o");
		mntmIniciarVenda.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/_2Partnership_icon.png")));
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
		
		JMenuItem mntmPropostaDeVenda = new JMenuItem("Proposta de Venda/Loca\u00E7\u00E3o");
		mntmPropostaDeVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new frmGerarProposta();
			}
		});
		mntmPropostaDeVenda.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/_1proposta_icon.png")));
		mnVenda.add(mntmPropostaDeVenda);
		mnVenda.add(mntmIniciarVenda);
		
		mnConsulta = new JMenu("Consulta");
		mnConsulta.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/search.png")));
		menuBarArquivo.add(mnConsulta);
		
		mntmPropriedadesConsulta = new JMenuItem("Propriedades");
		mntmPropriedadesConsulta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
		mntmPropriedadesConsulta.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/house.png")));
		mntmPropriedadesConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new frmConsultaPropriedade(0);
			}
		});
		mnConsulta.add(mntmPropriedadesConsulta);
		
		mnClientes_1 = new JMenu("Clientes");
		mnClientes_1.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/cliente.png")));
		mnConsulta.add(mnClientes_1);
		
		mntmClienteComprador = new JMenuItem("Cliente Comprador");
		mntmClienteComprador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new frmConsultaClientesComprador(0);
			}
		});
		mntmClienteComprador.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/cliComp.png")));
		mnClientes_1.add(mntmClienteComprador);
		
		mntmClienteProprietrio = new JMenuItem("Cliente Propriet\u00E1rio");
		mntmClienteProprietrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new frmConsultaClienteProprietario(0);
			}
		});
		mntmClienteProprietrio.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/cliProp.png")));
		mnClientes_1.add(mntmClienteProprietrio);
		
		mntmMetasConsulta = new JMenuItem("Metas");
		mntmMetasConsulta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_MASK));
		mntmMetasConsulta.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/meta.png")));
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
		mnRelatorio.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/report1.png")));
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
		mnAjuda.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/help.png")));
		menuBarArquivo.add(mnAjuda);
		
		mntmTutorialDoSistema = new JMenuItem("Tutorial do Sistema");
		mntmTutorialDoSistema.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmTutorialDoSistema.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/tuto.png")));
		mntmTutorialDoSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Abre o Manual
				pdf();
			}
		});
		mnAjuda.add(mntmTutorialDoSistema);
		
		mntmDesenvolvedores = new JMenuItem("Desenvolvedores");
		mntmDesenvolvedores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mntmDesenvolvedores.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/dev.png")));
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
		mntmSobre.setIcon(new ImageIcon(frmMenuPrincipal.class.getResource("/br/com/images/info2.png")));
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
