package br.com.sani.telas;

import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.sani.bean.ClienteProprietario;
import br.com.sani.bean.ClienteProprietarioFisica;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;

public class frmCadastroPropriedade extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnderecoPropriedade;
	private JTextField txtCepPropriedade;
	private JTextField txtNumeroPropriedade;
	private JTextField txtComplementoPropriedade;
	private static JTextField txtNomeProprietario;
	private static JTextField txtCpfProprietario;
	private static JTextField txtEmailProprietario;
	private JTextField txtMetragem;
	private JFormattedTextField ftCepPropriedade;
	
	private static ClienteProprietarioFisica proprietario;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmCadastroPropriedade.class.getResource("/br/com/images/home_badge.png")));
		setBounds(100, 100, 640, 650);
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
		
		JRadioButton rdbtnTerreno = new JRadioButton("Terreno");
		rdbtnTerreno.setBounds(67, 32, 109, 23);
		panelTipoPropriedade.add(rdbtnTerreno);
		
		JRadioButton rdbtnGalpao = new JRadioButton("Galp\u00E3o");
		rdbtnGalpao.setBounds(67, 52, 109, 23);
		panelTipoPropriedade.add(rdbtnGalpao);
		
		JRadioButton rdbtnSala = new JRadioButton("Sala");
		rdbtnSala.setBounds(67, 72, 109, 23);
		panelTipoPropriedade.add(rdbtnSala);
		
		JRadioButton rdbtnImovelParaRenda = new JRadioButton("Im\u00F3vel para Renda");
		rdbtnImovelParaRenda.setBounds(67, 92, 147, 23);
		panelTipoPropriedade.add(rdbtnImovelParaRenda);
		
		JRadioButton rdbtnPredio = new JRadioButton("Pr\u00E9dio");
		rdbtnPredio.setBounds(267, 92, 109, 23);
		panelTipoPropriedade.add(rdbtnPredio);
		
		JRadioButton rdbtnCasa = new JRadioButton("Casa");
		rdbtnCasa.setBounds(267, 32, 109, 23);
		panelTipoPropriedade.add(rdbtnCasa);
		
		JRadioButton rdbtnApartamento = new JRadioButton("Apartamento");
		rdbtnApartamento.setBounds(267, 52, 109, 23);
		panelTipoPropriedade.add(rdbtnApartamento);
		
		JRadioButton rdbtnSobrado = new JRadioButton("Sobrado");
		rdbtnSobrado.setBounds(267, 72, 109, 23);
		panelTipoPropriedade.add(rdbtnSobrado);
		
		//Cria ButtonGroup
		
		ButtonGroup group = new ButtonGroup();  
		group.add(rdbtnTerreno);  
		group.add(rdbtnGalpao);
		group.add(rdbtnSala);
		group.add(rdbtnImovelParaRenda);
		group.add(rdbtnCasa);
		group.add(rdbtnApartamento);
		group.add(rdbtnSobrado);
		group.add(rdbtnPredio);
		
		//Cria ButtonGroup Estado do Imovel
		
		ButtonGroup grupoEstadoImovel= new ButtonGroup();  
		
		JPanel panelInfoProprietario = new JPanel();
		panelInfoProprietario.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es Propriet\u00E1rio*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfoProprietario.setBounds(10, 161, 457, 151);
		contentPane.add(panelInfoProprietario);
		panelInfoProprietario.setLayout(null);
		
		JLabel lblProprietario = new JLabel("Propriet\u00E1rio:");
		lblProprietario.setBounds(25, 30, 73, 14);
		panelInfoProprietario.add(lblProprietario);
		
		JButton btnSelecionarProprietrio = new JButton("Selecionar Propriet\u00E1rio");
		btnSelecionarProprietrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new frmConsultaClienteProprietario();
			}
		});
		btnSelecionarProprietrio.setBounds(96, 26, 232, 23);
		panelInfoProprietario.add(btnSelecionarProprietrio);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(52, 60, 46, 14);
		panelInfoProprietario.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(58, 90, 40, 14);
		panelInfoProprietario.add(lblCpf);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(52, 120, 46, 14);
		panelInfoProprietario.add(lblEmail);
		
		txtNomeProprietario = new JTextField();
		txtNomeProprietario.setEditable(false);
		txtNomeProprietario.setBounds(96, 57, 232, 20);
		panelInfoProprietario.add(txtNomeProprietario);
		txtNomeProprietario.setColumns(10);
		
		txtCpfProprietario = new JTextField();
		txtCpfProprietario.setEditable(false);
		txtCpfProprietario.setColumns(10);
		txtCpfProprietario.setBounds(95, 87, 125, 20);
		panelInfoProprietario.add(txtCpfProprietario);
		
		txtEmailProprietario = new JTextField();
		txtEmailProprietario.setEditable(false);
		txtEmailProprietario.setColumns(10);
		txtEmailProprietario.setBounds(96, 117, 232, 20);
		panelInfoProprietario.add(txtEmailProprietario);
		
		JPanel panelInfoPropriedade = new JPanel();
		panelInfoPropriedade.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es Propriedade*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfoPropriedade.setBounds(10, 323, 457, 290);
		contentPane.add(panelInfoPropriedade);
		panelInfoPropriedade.setLayout(null);
		
		JLabel lblEnderecoPropriedade = new JLabel("Endere\u00E7o:");
		lblEnderecoPropriedade.setBounds(30, 30, 66, 14);
		panelInfoPropriedade.add(lblEnderecoPropriedade);
		
		txtEnderecoPropriedade = new JTextField();
		txtEnderecoPropriedade.setBounds(96, 27, 233, 20);
		panelInfoPropriedade.add(txtEnderecoPropriedade);
		txtEnderecoPropriedade.setColumns(10);
		
		JLabel lblCepPropriedade = new JLabel("CEP:");
		lblCepPropriedade.setBounds(344, 30, 24, 14);
		panelInfoPropriedade.add(lblCepPropriedade);
		
		ftCepPropriedade = new JFormattedTextField(Mascara.setMaskCepInTf(ftCepPropriedade));
		ftCepPropriedade.setBounds(373, 27, 66, 20);
		ftCepPropriedade.setColumns(10);
		panelInfoPropriedade.add(ftCepPropriedade);
		
		JLabel lblNumeroPropriedade = new JLabel("N\u00BA:");
		lblNumeroPropriedade.setBounds(352, 61, 16, 14);
		panelInfoPropriedade.add(lblNumeroPropriedade);
		
		txtNumeroPropriedade = new JTextField();
		txtNumeroPropriedade.setBounds(96, 89, 233, 20);
		txtNumeroPropriedade.setColumns(10);
		panelInfoPropriedade.add(txtNumeroPropriedade);
		
		JLabel lblComplementoPropriedade = new JLabel("Complemento:");
		lblComplementoPropriedade.setBounds(10, 60, 75, 14);
		panelInfoPropriedade.add(lblComplementoPropriedade);
		
		txtComplementoPropriedade = new JTextField();
		txtComplementoPropriedade.setBounds(96, 58, 233, 20);
		txtComplementoPropriedade.setColumns(10);
		panelInfoPropriedade.add(txtComplementoPropriedade);
		
		JLabel lblSelecionarImagens = new JLabel("Selecionar Imagens:");
		lblSelecionarImagens.setBounds(10, 120, 122, 14);
		panelInfoPropriedade.add(lblSelecionarImagens);
		
		JLabel lblMetragem = new JLabel("Metragem m\u00B2:");
		lblMetragem.setBounds(10, 90, 89, 14);
		panelInfoPropriedade.add(lblMetragem);
		
		txtMetragem = new JTextField();
		txtMetragem.setBounds(373, 58, 66, 20);
		panelInfoPropriedade.add(txtMetragem);
		txtMetragem.setColumns(10);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(30, 151, 59, 58);
		panelInfoPropriedade.add(scrollPane);
		
		ScrollPane scrollPane_1 = new ScrollPane();
		scrollPane_1.setBounds(93, 151, 59, 58);
		panelInfoPropriedade.add(scrollPane_1);
		
		ScrollPane scrollPane_2 = new ScrollPane();
		scrollPane_2.setBounds(155, 151, 59, 58);
		panelInfoPropriedade.add(scrollPane_2);
		
		ScrollPane scrollPane_3 = new ScrollPane();
		scrollPane_3.setBounds(30, 215, 59, 58);
		panelInfoPropriedade.add(scrollPane_3);
		
		ScrollPane scrollPane_4 = new ScrollPane();
		scrollPane_4.setBounds(93, 215, 59, 58);
		panelInfoPropriedade.add(scrollPane_4);
		
		ScrollPane scrollPane_5 = new ScrollPane();
		scrollPane_5.setBounds(155, 215, 59, 58);
		panelInfoPropriedade.add(scrollPane_5);
		
		JButton btnProcurar = new JButton("Procurar...");
		btnProcurar.setBounds(297, 165, 105, 23);
		panelInfoPropriedade.add(btnProcurar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(297, 215, 105, 23);
		panelInfoPropriedade.add(btnExcluir);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setBounds(257, 165, 25, 25);
		panelInfoPropriedade.add(lblSearch);
		lblSearch.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/search-ico.png")));
		
		JLabel lblExcluirImagem = new JLabel("");
		lblExcluirImagem.setBounds(257, 215, 25, 25);
		panelInfoPropriedade.add(lblExcluirImagem);
		lblExcluirImagem.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/delete-.png")));
		
		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/clear.png")));
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaFormulario();
			}
		});
		btnLimparCampos.setBounds(477, 394, 145, 30);
		contentPane.add(btnLimparCampos);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/delete-.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnCancelar.setBounds(477, 447, 145, 30);
		contentPane.add(btnCancelar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/save.png")));
		btnCadastrar.setBounds(477, 503, 145, 30);
		contentPane.add(btnCadastrar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Estado do Im\u00F3vel*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(481, 11, 141, 139);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//Buttons Estado Imovel
		
		JRadioButton rdbtnVenda = new JRadioButton("\u00C0 Venda");
		rdbtnVenda.setBounds(20, 30, 109, 23);
		panel.add(rdbtnVenda);
		grupoEstadoImovel.add(rdbtnVenda);
		
		JRadioButton rdbtnVendida = new JRadioButton("Vendida");
		rdbtnVendida.setBounds(20, 50, 109, 23);
		panel.add(rdbtnVendida);
		grupoEstadoImovel.add(rdbtnVendida);
		
		JRadioButton rdbtnAluguel = new JRadioButton("Aluguel");
		rdbtnAluguel.setBounds(20, 70, 109, 23);
		panel.add(rdbtnAluguel);
		grupoEstadoImovel.add(rdbtnAluguel);
		
		JRadioButton rdbtnAlugado = new JRadioButton("Alugado");
		rdbtnAlugado.setBounds(20, 90, 109, 23);
		panel.add(rdbtnAlugado);
		rdbtnAlugado.setHorizontalAlignment(SwingConstants.LEFT);
		grupoEstadoImovel.add(rdbtnAlugado);		
		
		setLocationRelativeTo(null);		
	}
	
	public void fechar(){
		this.dispose();
	}
	
	public static void setProprietario(ClienteProprietarioFisica p){
		txtNomeProprietario.setText(p.getNome());
		txtEmailProprietario.setText(p.getEmail());
		txtCpfProprietario.setText(p.getCpf());
		
		proprietario = p;
	}
	
	public void limpaFormulario(){
		txtEnderecoPropriedade.setText("");
		txtCepPropriedade.setText("");
		txtNumeroPropriedade.setText("");
		txtComplementoPropriedade.setText("");
	}
}
