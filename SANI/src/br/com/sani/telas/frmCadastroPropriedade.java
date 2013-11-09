package br.com.sani.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.sani.bean.ClienteProprietario;
import br.com.sani.bean.Endereco;
import br.com.sani.bean.ImagemImovel;
import br.com.sani.bean.Propriedade;
import br.com.sani.dao.EnderecoDAO;
import br.com.sani.dao.PropriedadeDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.exception.EntradaUsuarioException;
import br.com.sani.util.ImagePanel;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;
import br.com.sani.util.TelaUtil;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;

public class frmCadastroPropriedade extends JFrame {

	private JPanel contentPane;
	private JTextField txtEndereco;
	private JFormattedTextField txtCepPropriedade;
	private JTextField txtNumero;
	private JTextField txtComplementoPropriedade;
	private static JTextField txtNomeProprietario;
	private static JTextField txtCpfProprietario;
	private static JTextField txtEmailProprietario;
	private JTextField txtMetragem;
	private JFormattedTextField ftCepPropriedade;
	
	private static int proprietario = 0;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;
	
	//IMAGENS DO IMOVEL
	
	private ImagePanel imagem1;
	private ImagePanel imagem2;
	private ImagePanel imagem3;
	private ImagePanel imagem4;
	private ImagePanel imagem5;
	private ImagePanel imagem6;
	
	//Byte IMAGENS
	
	private byte[] foto1;
	private byte[] foto2;
	private byte[] foto3;
	private byte[] foto4;
	private byte[] foto5;
	private byte[] foto6;
	private JButton btnExcluir;
	private JRadioButton rbTerreno;
	private JRadioButton rbGalpao;
	private JRadioButton rbSala;
	private JRadioButton rbImovelRenda;
	private JRadioButton rbCasa;
	private JRadioButton rbApartamento;
	private JRadioButton rbSobrado;
	private JRadioButton rbPredio;
	private JRadioButton rbVenda;
	private JRadioButton rbVendida;
	private JRadioButton rbAluguel;
	private JRadioButton rbAlugado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCadastroPropriedade frame = new frmCadastroPropriedade();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public frmCadastroPropriedade() throws ParseException {
		SwingUtil.lookWindows(this);
		setResizable(false);
		setTitle("Cadastro de Propriedade");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmCadastroPropriedade.class.getResource("/br/com/images/house.png")));
		setBounds(100, 100, 709, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTipoPropriedade = new JPanel();
		panelTipoPropriedade.setBorder(new TitledBorder(null, "Tipo Propriedade*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTipoPropriedade.setBounds(10, 11, 457, 139);
		contentPane.add(panelTipoPropriedade);
		panelTipoPropriedade.setLayout(null);
		
		//Cria o ButtonGroup para Residencial e Comercial
		
		ButtonGroup grupoPrincipal = new ButtonGroup();
		
		//RadioButton Comercial
		
		rbTerreno = new JRadioButton("Terreno");
		rbTerreno.setBounds(67, 32, 109, 23);
		panelTipoPropriedade.add(rbTerreno);
		
		rbGalpao = new JRadioButton("Galp\u00E3o");
		rbGalpao.setBounds(67, 52, 109, 23);
		panelTipoPropriedade.add(rbGalpao);
		
		rbSala = new JRadioButton("Sala");
		rbSala.setBounds(67, 72, 109, 23);
		panelTipoPropriedade.add(rbSala);
		
		rbImovelRenda = new JRadioButton("Im\u00F3vel para Renda");
		rbImovelRenda.setBounds(67, 92, 147, 23);
		panelTipoPropriedade.add(rbImovelRenda);
		
		rbPredio = new JRadioButton("Pr\u00E9dio");
		rbPredio.setBounds(267, 92, 109, 23);
		panelTipoPropriedade.add(rbPredio);
		
		rbCasa = new JRadioButton("Casa");
		rbCasa.setSelected(true);
		rbCasa.setBounds(267, 32, 109, 23);
		panelTipoPropriedade.add(rbCasa);
		
		rbApartamento = new JRadioButton("Apartamento");
		rbApartamento.setBounds(267, 52, 109, 23);
		panelTipoPropriedade.add(rbApartamento);
		
		rbSobrado = new JRadioButton("Sobrado");
		rbSobrado.setBounds(267, 72, 109, 23);
		panelTipoPropriedade.add(rbSobrado);
		
		//Cria ButtonGroup
		
		ButtonGroup group = new ButtonGroup();  
		group.add(rbTerreno);  
		group.add(rbGalpao);
		group.add(rbSala);
		group.add(rbImovelRenda);
		group.add(rbCasa);
		group.add(rbApartamento);
		group.add(rbSobrado);
		group.add(rbPredio);
		
		//Cria ButtonGroup Estado do Imovel
		
		ButtonGroup grupoEstadoImovel= new ButtonGroup();  
		
		JPanel panelInfoProprietario = new JPanel();
		panelInfoProprietario.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es Propriet\u00E1rio*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfoProprietario.setBounds(10, 161, 457, 151);
		contentPane.add(panelInfoProprietario);
		panelInfoProprietario.setLayout(null);
		
		JLabel lblProprietario = new JLabel("Propriet\u00E1rio:");
		lblProprietario.setBounds(45, 30, 60, 14);
		panelInfoProprietario.add(lblProprietario);
		
		JButton btnSelecionarProprietrio = new JButton("Selecionar Propriet\u00E1rio");
		btnSelecionarProprietrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new frmConsultaClienteProprietario(1);
			}
		});
		btnSelecionarProprietrio.setBounds(110, 26, 232, 23);
		panelInfoProprietario.add(btnSelecionarProprietrio);
		
		JLabel lblNome = new JLabel("Nome/Raz\u00E3o Social:");
		lblNome.setBounds(10, 60, 95, 14);
		panelInfoProprietario.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF/CNPJ:");
		lblCpf.setBounds(53, 90, 52, 14);
		panelInfoProprietario.add(lblCpf);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(77, 120, 28, 14);
		panelInfoProprietario.add(lblEmail);
		
		txtNomeProprietario = new JTextField();
		txtNomeProprietario.setName("Proprietario");
		txtNomeProprietario.setEditable(false);
		txtNomeProprietario.setBounds(110, 57, 232, 20);
		panelInfoProprietario.add(txtNomeProprietario);
		txtNomeProprietario.setColumns(10);
		
		txtCpfProprietario = new JTextField();
		txtCpfProprietario.setEditable(false);
		txtCpfProprietario.setColumns(10);
		txtCpfProprietario.setBounds(110, 87, 125, 20);
		panelInfoProprietario.add(txtCpfProprietario);
		
		txtEmailProprietario = new JTextField();
		txtEmailProprietario.setEditable(false);
		txtEmailProprietario.setColumns(10);
		txtEmailProprietario.setBounds(110, 117, 232, 20);
		panelInfoProprietario.add(txtEmailProprietario);
		
		JPanel panelInfoPropriedade = new JPanel();
		panelInfoPropriedade.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es Propriedade*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfoPropriedade.setBounds(10, 323, 457, 179);
		contentPane.add(panelInfoPropriedade);
		panelInfoPropriedade.setLayout(null);
		
		JLabel lblEnderecoPropriedade = new JLabel("Endere\u00E7o:");
		lblEnderecoPropriedade.setBounds(30, 60, 66, 14);
		panelInfoPropriedade.add(lblEnderecoPropriedade);
		
		txtEndereco = new JTextField();
		txtEndereco.setEditable(false);
		txtEndereco.setBounds(96, 55, 312, 20);
		panelInfoPropriedade.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblCepPropriedade = new JLabel("CEP:");
		lblCepPropriedade.setBounds(54, 28, 24, 14);
		panelInfoPropriedade.add(lblCepPropriedade);
		
		ftCepPropriedade = new JFormattedTextField(Mascara.setMaskCepInTf(ftCepPropriedade));
		ftCepPropriedade.setName("Cep");
		ftCepPropriedade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				buscarEndereco();
				//txtNumero.requestFocus();
			}
		});
		ftCepPropriedade.setBounds(96, 25, 66, 20);
		ftCepPropriedade.setColumns(10);
		panelInfoPropriedade.add(ftCepPropriedade);
		
		JLabel lblNumeroPropriedade = new JLabel("N\u00BA:");
		lblNumeroPropriedade.setBounds(308, 86, 16, 14);
		panelInfoPropriedade.add(lblNumeroPropriedade);
		
		txtNumero = new JTextField();
		txtNumero.setName("N\u00FAmero");
		txtNumero.setBounds(342, 86, 66, 20);
		txtNumero.setColumns(10);
		panelInfoPropriedade.add(txtNumero);
		
		JLabel lblComplementoPropriedade = new JLabel("Complemento:");
		lblComplementoPropriedade.setBounds(10, 148, 75, 14);
		panelInfoPropriedade.add(lblComplementoPropriedade);
		
		txtComplementoPropriedade = new JTextField();
		txtComplementoPropriedade.setName("Complemento");
		txtComplementoPropriedade.setBounds(96, 145, 150, 20);
		txtComplementoPropriedade.setColumns(10);
		panelInfoPropriedade.add(txtComplementoPropriedade);
		
		JLabel lblMetragem = new JLabel("Metragem m\u00B2:");
		lblMetragem.setBounds(256, 148, 75, 14);
		panelInfoPropriedade.add(lblMetragem);
		
		txtMetragem = new JTextField();
		txtMetragem.setName("Metragem");
		txtMetragem.setBounds(342, 145, 66, 20);
		panelInfoPropriedade.add(txtMetragem);
		txtMetragem.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(46, 89, 32, 14);
		panelInfoPropriedade.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setEditable(false);
		txtBairro.setBounds(96, 85, 150, 20);
		panelInfoPropriedade.add(txtBairro);
		txtBairro.setColumns(10);
		
		txtCidade = new JTextField();
		txtCidade.setEditable(false);
		txtCidade.setColumns(10);
		txtCidade.setBounds(96, 115, 150, 20);
		panelInfoPropriedade.add(txtCidade);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(40, 118, 37, 14);
		panelInfoPropriedade.add(lblCidade);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(287, 118, 37, 14);
		panelInfoPropriedade.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		txtEstado.setBounds(342, 115, 66, 20);
		panelInfoPropriedade.add(txtEstado);
		
		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/clear.png")));
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaFormulario();
			}
		});
		btnLimparCampos.setBounds(280, 531, 145, 30);
		contentPane.add(btnLimparCampos);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/delete-.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnCancelar.setBounds(85, 531, 145, 30);
		contentPane.add(btnCancelar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inserirPropriedade();
			}
		});
		btnCadastrar.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/save.png")));
		btnCadastrar.setBounds(475, 531, 145, 30);
		contentPane.add(btnCadastrar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Estado do Im\u00F3vel*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(481, 11, 210, 139);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//Buttons Estado Imovel
		
		rbVenda = new JRadioButton("\u00C0 Venda");
		rbVenda.setBounds(20, 30, 109, 23);
		panel.add(rbVenda);
		grupoEstadoImovel.add(rbVenda);
		
		rbVendida = new JRadioButton("Vendida");
		rbVendida.setBounds(20, 50, 109, 23);
		panel.add(rbVendida);
		grupoEstadoImovel.add(rbVendida);
		
		rbAluguel = new JRadioButton("Aluguel");
		rbAluguel.setSelected(true);
		rbAluguel.setBounds(20, 70, 109, 23);
		panel.add(rbAluguel);
		grupoEstadoImovel.add(rbAluguel);
		
		rbAlugado = new JRadioButton("Alugado");
		rbAlugado.setBounds(20, 90, 109, 23);
		panel.add(rbAlugado);
		rbAlugado.setHorizontalAlignment(SwingConstants.LEFT);
		grupoEstadoImovel.add(rbAlugado);
		
		JPanel panelImagem = new JPanel();
		panelImagem.setBorder(new TitledBorder(null, "Selecionar Imagens", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelImagem.setBounds(477, 161, 214, 341);
		contentPane.add(panelImagem);
		panelImagem.setLayout(null);
		
		//Imagens
		
		imagem1 = new ImagePanel(null);
		imagem1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				try {
					if(ev.getClickCount() > 1)
						escolherFoto1();
				} catch (EntradaUsuarioException e) {
					e.printStackTrace();
				}
			}
		});
		imagem1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imagem1.setBounds(10, 26, 96, 85);
		panelImagem.add(imagem1);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(imagem1, popupMenu);
		
		JMenuItem mntmLimpar = new JMenuItem("Limpar");
		mntmLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				foto1 = null;
				imagem1.setImagem(null);
				imagem1.repaint();
				imagem1.revalidate();
				
			}
		});
		popupMenu.add(mntmLimpar);
		imagem1.setLayout(null);
		
		imagem2 = new ImagePanel(null);
		imagem2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				try {
					if(ev.getClickCount() > 1)	
						escolherFoto2();
				} catch (EntradaUsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		imagem2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imagem2.setBounds(112, 26, 92, 85);
		panelImagem.add(imagem2);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(imagem2, popupMenu_1);
		
		JMenuItem menuItem = new JMenuItem("Limpar");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foto2 = null;
				imagem2.setImagem(null);
				imagem2.repaint();
				imagem2.revalidate();
			}
		});
		popupMenu_1.add(menuItem);
		imagem2.setLayout(null);
		
		imagem3 = new ImagePanel(null);
		imagem3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				try {
					if(ev.getClickCount() > 1)
						escolherFoto3();
				} catch (EntradaUsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		imagem3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imagem3.setBounds(10, 117, 96, 85);
		panelImagem.add(imagem3);
		
		JPopupMenu popupMenu_2 = new JPopupMenu();
		addPopup(imagem3, popupMenu_2);
		
		JMenuItem menuItem_1 = new JMenuItem("Limpar");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foto3 = null;
				imagem3.setImagem(null);
				imagem3.repaint();
				imagem3.revalidate();
			}
		});
		popupMenu_2.add(menuItem_1);
		imagem3.setLayout(null);
		
		imagem4 = new ImagePanel(null);
		imagem4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				try {
					if(ev.getClickCount() > 1)
						escolherFoto4();
				} catch (EntradaUsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		imagem4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imagem4.setBounds(112, 117, 92, 85);
		panelImagem.add(imagem4);
		
		JPopupMenu popupMenu_3 = new JPopupMenu();
		addPopup(imagem4, popupMenu_3);
		
		JMenuItem menuItem_2 = new JMenuItem("Limpar");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foto4 = null;
				imagem4.setImagem(null);
				imagem4.repaint();
				imagem4.revalidate();
			}
		});
		popupMenu_3.add(menuItem_2);
		imagem4.setLayout(null);
		
		imagem5 = new ImagePanel(null);
		imagem5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				try {
					if(ev.getClickCount() > 1)
						escolherFoto5();
				} catch (EntradaUsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		imagem5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imagem5.setBounds(10, 208, 96, 85);
		panelImagem.add(imagem5);
		
		JPopupMenu popupMenu_4 = new JPopupMenu();
		addPopup(imagem5, popupMenu_4);
		
		JMenuItem menuItem_3 = new JMenuItem("Limpar");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foto5 = null;
				imagem6.setImagem(null);
				imagem5.repaint();
				imagem5.revalidate();
			}
		});
		popupMenu_4.add(menuItem_3);
		imagem5.setLayout(null);
		
		imagem6 = new ImagePanel(null);
		imagem6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				try {
					if(ev.getClickCount() > 1)
						escolherFoto6();
				} catch (EntradaUsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		imagem6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imagem6.setBounds(112, 206, 92, 87);
		panelImagem.add(imagem6);
		
		JPopupMenu popupMenu_5 = new JPopupMenu();
		addPopup(imagem6, popupMenu_5);
		
		JMenuItem menuItem_4 = new JMenuItem("Limpar");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foto6 = null;
				imagem6.setImagem(null);
				imagem6.repaint();
				imagem6.revalidate();
			}
		});
		popupMenu_5.add(menuItem_4);
		imagem6.setLayout(null);
		
		btnExcluir = new JButton("Limpar tudo");
		btnExcluir.setBounds(112, 307, 92, 23);
		panelImagem.add(btnExcluir);
		
		JLabel lblImagem1 = new JLabel("");
		lblImagem1.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/imagem.png")));
		lblImagem1.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem1.setForeground(Color.DARK_GRAY);
		lblImagem1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblImagem1.setLabelFor(this);
		lblImagem1.setBounds(10, 26, 96, 85);
		panelImagem.add(lblImagem1);
		
		JLabel lblImagem2 = new JLabel("");
		lblImagem2.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/imagem.png")));
		lblImagem2.setBounds(112, 26, 92, 85);
		panelImagem.add(lblImagem2);
		lblImagem2.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem2.setForeground(Color.DARK_GRAY);
		lblImagem2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblImagem3 = new JLabel("");
		lblImagem3.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/imagem.png")));
		lblImagem3.setBounds(10, 117, 96, 85);
		panelImagem.add(lblImagem3);
		lblImagem3.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem3.setForeground(Color.DARK_GRAY);
		lblImagem3.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblImagem4 = new JLabel("");
		lblImagem4.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/imagem.png")));
		lblImagem4.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem4.setForeground(Color.DARK_GRAY);
		lblImagem4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblImagem4.setBounds(112, 117, 92, 85);
		panelImagem.add(lblImagem4);
		
		JLabel lblImagem5 = new JLabel("");
		lblImagem5.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/imagem.png")));
		lblImagem5.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem5.setForeground(Color.DARK_GRAY);
		lblImagem5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblImagem5.setBounds(10, 208, 96, 85);
		panelImagem.add(lblImagem5);
		
		JLabel lblImagem6 = new JLabel("");
		lblImagem6.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/imagem.png")));
		lblImagem6.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem6.setForeground(Color.DARK_GRAY);
		lblImagem6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblImagem6.setBounds(112, 206, 92, 85);
		panelImagem.add(lblImagem6);
		
		setLocationRelativeTo(null);		
	}
	
	private void buscarEndereco(){
		try{
			Endereco endereco = new EnderecoDAO().buscarPorCep(TelaUtil.getCep(ftCepPropriedade, true));
			if(endereco != null){
				txtEndereco.setText(endereco.getEndereco());
				txtCidade.setText(endereco.getCidade());
				txtBairro.setText(endereco.getBairro());
				txtEstado.setText(endereco.getEstado());
			}else{
				JOptionPane.showMessageDialog(null, "O cep digitado não existe!");
				ftCepPropriedade.setText("");
				ftCepPropriedade.requestFocus();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void fechar(){
		this.dispose();
	}
	
	public static void setProprietario(Object[] prop){
		txtNomeProprietario.setText((String) prop[1]);
		txtEmailProprietario.setText((String) prop[3]);
		txtCpfProprietario.setText((String) prop[2]);
		
		proprietario = (Integer) prop[0];
	}
	
	public void limpaFormulario(){
		txtEndereco.setText("");
		txtNumero.setText("");
		txtComplementoPropriedade.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		txtCpfProprietario.setText("");
		txtEmailProprietario.setText("");
		txtEstado.setText("");
		txtMetragem.setText("");
		txtNomeProprietario.setText("");
		proprietario = 0;
		
		foto1 = null;
		imagem1.setImagem(null);
		imagem1.repaint();
		imagem1.revalidate();
		
		foto2 = null;
		imagem2.setImagem(null);
		imagem2.repaint();
		imagem2.revalidate();
		
		foto3 = null;
		imagem3.setImagem(null);
		imagem3.repaint();
		imagem3.revalidate();
		
		foto4 = null;
		imagem4.setImagem(null);
		imagem4.repaint();
		imagem4.revalidate();
		
		foto5 = null;
		imagem5.setImagem(null);
		imagem5.repaint();
		imagem5.revalidate();
		
		foto6 = null;
		imagem6.setImagem(null);
		imagem6.repaint();
		imagem6.revalidate();
		
		ftCepPropriedade.setText("");
	}
	
	private void escolherFoto1() throws EntradaUsuarioException {
		JTextField foto = new JTextField();
		
		this.foto1 = TelaUtil.showTelaEscolheImage(new File("C:\\Users\\Public\\Pictures"), this.imagem1, foto, this, ".jpg", ".gif", ".png");
	}
	
	private void escolherFoto2() throws EntradaUsuarioException {
		JTextField foto = new JTextField();
		
		this.foto2 = TelaUtil.showTelaEscolheImage(new File("C:\\Users\\Public\\Pictures"), this.imagem2, foto, this, ".jpg", ".gif", ".png");
	}
	
	private void escolherFoto3() throws EntradaUsuarioException {
		JTextField foto = new JTextField();
		
		this.foto3 = TelaUtil.showTelaEscolheImage(new File("C:\\Users\\Public\\Pictures"), this.imagem3, foto, this, ".jpg", ".gif", ".png");
	}
	
	private void escolherFoto4() throws EntradaUsuarioException {
		JTextField foto = new JTextField();
		
		this.foto4 = TelaUtil.showTelaEscolheImage(new File("C:\\Users\\Public\\Pictures"), this.imagem4, foto, this, ".jpg", ".gif", ".png");
	}
	
	private void escolherFoto5() throws EntradaUsuarioException {
		JTextField foto = new JTextField();
		
		this.foto5 = TelaUtil.showTelaEscolheImage(new File("C:\\Users\\Public\\Pictures"), this.imagem5, foto, this, ".jpg", ".gif", ".png");
	}
	
	private void escolherFoto6() throws EntradaUsuarioException {
		JTextField foto = new JTextField();
		
		this.foto6 = TelaUtil.showTelaEscolheImage(new File("C:\\Users\\Public\\Pictures"), this.imagem6, foto, this, ".jpg", ".gif", ".png");
	}
	
	private Propriedade getBean() throws EntradaUsuarioException{
		Propriedade p = new Propriedade();
		ClienteProprietario cli = new ClienteProprietario();
		
		if(proprietario != 0){
			cli.setCodCliProprietario(proprietario);
		}else{
			JOptionPane.showMessageDialog(this, "Selecione um proprietario", "Atenção", JOptionPane.WARNING_MESSAGE);
			throw new EntradaUsuarioException(txtNomeProprietario);
		}
		
		p.setClienteProprietario(cli);
		p.setCep(TelaUtil.getCep(ftCepPropriedade, true));
		p.setComplementoEndereco(TelaUtil.getCampoObrigatorio(txtComplementoPropriedade, false));
		p.setMetragem(TelaUtil.getCampoObrigatorioFloat(txtMetragem));
		p.setNumeroEndereco(Integer.parseInt(TelaUtil.getCampoObrigatorio(txtNumero, true)));
		
		if(rbCasa.isSelected()){
			p.setTipoPropriedade("Casa");
		}else if(rbApartamento.isSelected()){
			p.setTipoPropriedade("Apto");
		}else if (rbGalpao.isSelected()) {
			p.setTipoPropriedade("Galpao");
		}else if (rbImovelRenda.isSelected()) {
			p.setTipoPropriedade("IR");
		}else if (rbPredio.isSelected()) {
			p.setTipoPropriedade("Predio");
		}else if (rbSala.isSelected()) {
			p.setTipoPropriedade("Sala");
		}else if (rbSobrado.isSelected()) {
			p.setTipoPropriedade("Sobr");
		}else if (rbTerreno.isSelected()) {
			p.setTipoPropriedade("Terr");
		}
		
		if (rbAlugado.isSelected()) {
			p.setSituacaoPropriedade("Alugado");
		}else if (rbAluguel.isSelected()) {
			p.setSituacaoPropriedade("DispAluguel");
		}else if (rbVenda.isSelected()) {
			p.setSituacaoPropriedade("DispVenda");
		}else if (rbVendida.isSelected()) {
			p.setSituacaoPropriedade("Vendida");
		}
		
		return p;
	}
	
	private List<byte[]> getImagens(){
		List<byte[]> listaImagens = new ArrayList<byte[]>();
		
		if(foto1 != null){
			listaImagens.add(foto1);
		}
		if (foto2 != null) {
			listaImagens.add(foto2);
		}
		if (foto3 != null) {
			listaImagens.add(foto3);
		}
		if (foto4 != null) {
			listaImagens.add(foto4);
		}
		if (foto5 != null) {
			listaImagens.add(foto5);
		}
		if (foto6 != null) {
			listaImagens.add(foto6);
		}
		
		return listaImagens;
	}
	
	private void inserirPropriedade(){
		try{
			PropriedadeDAO dao = new PropriedadeDAO();
			
			int codigo = dao.inserirPropriedade(getBean());
			
			ImagemImovel img = new ImagemImovel();
			img.setDataImagem(new Date());
			img.setPropriedade(codigo);

			List<byte[]> imgs = getImagens();
			for(byte[] foto : imgs){
				img.setImagem(foto);
				dao.inserirImagensPropriedade(img);
			}
			
			JOptionPane.showMessageDialog(this, "Propriedade salva com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
			limpaFormulario();
		}catch(DAOException e){
			e.printStackTrace();
		} catch (EntradaUsuarioException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
