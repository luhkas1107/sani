package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.ScrollPane;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SwingConstants;

import br.com.sani.bean.cp;
import br.com.sani.bean.Propriedade;
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
	private JLabel lblClear;
	private JTextField txtMetragem;
	
	private static cp proprietario;

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
	 */
	public frmCadastroPropriedade() {
		SwingUtil.lookWindows(this);
		setResizable(false);
		setTitle("Cadastro de Propriedade");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmCadastroPropriedade.class.getResource("/br/com/images/home_badge.png")));
		setBounds(100, 100, 458, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTipoPropriedade = new JPanel();
		panelTipoPropriedade.setBounds(0, 0, 432, 173);
		contentPane.add(panelTipoPropriedade);
		panelTipoPropriedade.setLayout(null);
		
		JLabel lblTipoDePropriedade = new JLabel("Tipo de Propriedade:");
		lblTipoDePropriedade.setBounds(10, 11, 118, 14);
		panelTipoPropriedade.add(lblTipoDePropriedade);
		
		JRadioButton rdbtnComercial = new JRadioButton("Comercial");
		rdbtnComercial.setBounds(20, 32, 109, 23);
		panelTipoPropriedade.add(rdbtnComercial);
		
		JRadioButton rdbtnResidencial = new JRadioButton("Residencial");
		rdbtnResidencial.setBounds(179, 32, 109, 23);
		panelTipoPropriedade.add(rdbtnResidencial);
		
		//Cria o ButtonGroup para Residencial e Comercial
		
		ButtonGroup grupoPrincipal = new ButtonGroup();  
		grupoPrincipal.add(rdbtnComercial);  
		grupoPrincipal.add(rdbtnResidencial);
		
		//RadioButton Comercial
		
		JRadioButton rdbtnTerrenoComercial = new JRadioButton("Terreno");
		rdbtnTerrenoComercial.setBounds(30, 60, 109, 23);
		panelTipoPropriedade.add(rdbtnTerrenoComercial);
		
		JRadioButton rdbtnGalpao = new JRadioButton("Galp\u00E3o");
		rdbtnGalpao.setBounds(30, 80, 109, 23);
		panelTipoPropriedade.add(rdbtnGalpao);
		
		JRadioButton rdbtnSala = new JRadioButton("Sala");
		rdbtnSala.setBounds(30, 100, 109, 23);
		panelTipoPropriedade.add(rdbtnSala);
		
		JRadioButton rdbtnImovelParaRenda = new JRadioButton("Im\u00F3vel para Renda");
		rdbtnImovelParaRenda.setBounds(30, 120, 147, 23);
		panelTipoPropriedade.add(rdbtnImovelParaRenda);
		
		JRadioButton rdbtnPredio = new JRadioButton("Pr\u00E9dio");
		rdbtnPredio.setBounds(30, 140, 109, 23);
		panelTipoPropriedade.add(rdbtnPredio);
		
		//Cria ButtonGroup para Comercial
		
		ButtonGroup grupoComercial = new ButtonGroup();  
        grupoComercial.add(rdbtnTerrenoComercial);  
        grupoComercial.add(rdbtnGalpao);
        grupoComercial.add(rdbtnSala);
        grupoComercial.add(rdbtnImovelParaRenda);
        grupoComercial.add(rdbtnPredio);      
        		
		//RadioButton Residencial
		
		JRadioButton rdbtnCasa = new JRadioButton("Casa");
		rdbtnCasa.setBounds(189, 80, 109, 23);
		panelTipoPropriedade.add(rdbtnCasa);
		
		JRadioButton rdbtnApartamento = new JRadioButton("Apartamento");
		rdbtnApartamento.setBounds(189, 100, 109, 23);
		panelTipoPropriedade.add(rdbtnApartamento);
		
		JRadioButton rdbtnTerrenoResidencial = new JRadioButton("Terreno");
		rdbtnTerrenoResidencial.setBounds(189, 58, 109, 23);
		panelTipoPropriedade.add(rdbtnTerrenoResidencial);
		
		JRadioButton rdbtnSobrado = new JRadioButton("Sobrado");
		rdbtnSobrado.setBounds(189, 120, 109, 23);
		panelTipoPropriedade.add(rdbtnSobrado);
		
		//Cria ButtonGroup para Residencial
		
		ButtonGroup grupoResidencial = new ButtonGroup();  
		grupoResidencial.add(rdbtnCasa);  
		grupoResidencial.add(rdbtnApartamento);
		grupoResidencial.add(rdbtnTerrenoResidencial);
		grupoResidencial.add(rdbtnSobrado);
		
		JLabel lblEstadoImovel = new JLabel("Estado Im\u00F3vel:");
		lblEstadoImovel.setBounds(300, 10, 98, 14);
		panelTipoPropriedade.add(lblEstadoImovel);
		
		//Buttons Estado Imovel
		
		JRadioButton rdbtnVenda = new JRadioButton("\u00C0 Venda");
		rdbtnVenda.setBounds(310, 32, 109, 23);
		panelTipoPropriedade.add(rdbtnVenda);
		
		JRadioButton rdbtnAluguel = new JRadioButton("Aluguel");
		rdbtnAluguel.setBounds(310, 72, 109, 23);
		panelTipoPropriedade.add(rdbtnAluguel);
		
		JRadioButton rdbtnVendida = new JRadioButton("Vendida");
		rdbtnVendida.setBounds(310, 52, 109, 23);
		panelTipoPropriedade.add(rdbtnVendida);
		
		JRadioButton rdbtnAlugado = new JRadioButton("Alugado");
		rdbtnAlugado.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnAlugado.setBounds(309, 92, 109, 23);
		panelTipoPropriedade.add(rdbtnAlugado);
		
		//Cria ButtonGroup Estado do Imovel
		
		ButtonGroup grupoEstadoImovel= new ButtonGroup();  
		grupoEstadoImovel.add(rdbtnVenda);
		grupoEstadoImovel.add(rdbtnAluguel);
		grupoEstadoImovel.add(rdbtnVendida);
		grupoEstadoImovel.add(rdbtnAlugado);		
		
		JPanel panelInfoProprietario = new JPanel();
		panelInfoProprietario.setBounds(0, 185, 432, 151);
		contentPane.add(panelInfoProprietario);
		panelInfoProprietario.setLayout(null);
		
		JLabel lblInformacoesProprietario = new JLabel("Informa\u00E7\u00F5es Propriet\u00E1rio");
		lblInformacoesProprietario.setBounds(10, 11, 172, 14);
		panelInfoProprietario.add(lblInformacoesProprietario);
		
		JLabel lblProprietario = new JLabel("Propriet\u00E1rio:");
		lblProprietario.setBounds(20, 36, 78, 14);
		panelInfoProprietario.add(lblProprietario);
		
		JButton btnSelecionarProprietrio = new JButton("Selecionar Propriet\u00E1rio");
		btnSelecionarProprietrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new frmMostraProprietario();
			}
		});
		btnSelecionarProprietrio.setBounds(96, 32, 172, 23);
		panelInfoProprietario.add(btnSelecionarProprietrio);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 66, 46, 14);
		panelInfoProprietario.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(20, 96, 46, 14);
		panelInfoProprietario.add(lblCpf);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(20, 126, 46, 14);
		panelInfoProprietario.add(lblEmail);
		
		txtNomeProprietario = new JTextField();
		txtNomeProprietario.setEditable(false);
		txtNomeProprietario.setBounds(67, 63, 242, 20);
		panelInfoProprietario.add(txtNomeProprietario);
		txtNomeProprietario.setColumns(10);
		
		txtCpfProprietario = new JTextField();
		txtCpfProprietario.setEditable(false);
		txtCpfProprietario.setColumns(10);
		txtCpfProprietario.setBounds(67, 93, 125, 20);
		panelInfoProprietario.add(txtCpfProprietario);
		
		txtEmailProprietario = new JTextField();
		txtEmailProprietario.setEditable(false);
		txtEmailProprietario.setColumns(10);
		txtEmailProprietario.setBounds(67, 123, 172, 20);
		panelInfoProprietario.add(txtEmailProprietario);
		
		JPanel panelInfoPropriedade = new JPanel();
		panelInfoPropriedade.setBounds(0, 347, 432, 240);
		contentPane.add(panelInfoPropriedade);
		panelInfoPropriedade.setLayout(null);
		
		JLabel lblInformacoesPropriedade = new JLabel("Informa\u00E7\u00F5es Propriedade");
		lblInformacoesPropriedade.setBounds(10, 11, 157, 14);
		panelInfoPropriedade.add(lblInformacoesPropriedade);
		
		JLabel lblEnderecoPropriedade = new JLabel("Endere\u00E7o:");
		lblEnderecoPropriedade.setBounds(20, 36, 66, 14);
		panelInfoPropriedade.add(lblEnderecoPropriedade);
		
		txtEnderecoPropriedade = new JTextField();
		txtEnderecoPropriedade.setBounds(81, 33, 233, 20);
		panelInfoPropriedade.add(txtEnderecoPropriedade);
		txtEnderecoPropriedade.setColumns(10);
		
		JLabel lblCepPropriedade = new JLabel("CEP:");
		lblCepPropriedade.setBounds(324, 36, 46, 14);
		panelInfoPropriedade.add(lblCepPropriedade);
		
		txtCepPropriedade = new JTextField();
		txtCepPropriedade.setColumns(10);
		txtCepPropriedade.setBounds(356, 33, 66, 20);
		panelInfoPropriedade.add(txtCepPropriedade);
		
		JLabel lblNumeroPropriedade = new JLabel("N\u00BA");
		lblNumeroPropriedade.setBounds(20, 61, 29, 14);
		panelInfoPropriedade.add(lblNumeroPropriedade);
		
		txtNumeroPropriedade = new JTextField();
		txtNumeroPropriedade.setColumns(10);
		txtNumeroPropriedade.setBounds(47, 58, 46, 20);
		panelInfoPropriedade.add(txtNumeroPropriedade);
		
		JLabel lblComplementoPropriedade = new JLabel("Complemento:");
		lblComplementoPropriedade.setBounds(103, 61, 95, 14);
		panelInfoPropriedade.add(lblComplementoPropriedade);
		
		txtComplementoPropriedade = new JTextField();
		txtComplementoPropriedade.setColumns(10);
		txtComplementoPropriedade.setBounds(193, 58, 61, 20);
		panelInfoPropriedade.add(txtComplementoPropriedade);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(20, 106, 59, 58);
		panelInfoPropriedade.add(scrollPane);
		
		JButton btnProcurar = new JButton("Procurar...");
		btnProcurar.setBounds(277, 130, 105, 23);
		panelInfoPropriedade.add(btnProcurar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(277, 190, 105, 23);
		panelInfoPropriedade.add(btnExcluir);
		
		JLabel lblSelecionarImagens = new JLabel("Selecionar Imagens:");
		lblSelecionarImagens.setBounds(20, 89, 122, 14);
		panelInfoPropriedade.add(lblSelecionarImagens);
		
		ScrollPane scrollPane_1 = new ScrollPane();
		scrollPane_1.setBounds(83, 106, 59, 58);
		panelInfoPropriedade.add(scrollPane_1);
		
		ScrollPane scrollPane_2 = new ScrollPane();
		scrollPane_2.setBounds(145, 106, 59, 58);
		panelInfoPropriedade.add(scrollPane_2);
		
		ScrollPane scrollPane_3 = new ScrollPane();
		scrollPane_3.setBounds(20, 170, 59, 58);
		panelInfoPropriedade.add(scrollPane_3);
		
		ScrollPane scrollPane_4 = new ScrollPane();
		scrollPane_4.setBounds(83, 170, 59, 58);
		panelInfoPropriedade.add(scrollPane_4);
		
		ScrollPane scrollPane_5 = new ScrollPane();
		scrollPane_5.setBounds(145, 170, 59, 58);
		panelInfoPropriedade.add(scrollPane_5);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/search-ico.png")));
		lblSearch.setBounds(245, 130, 25, 25);
		panelInfoPropriedade.add(lblSearch);
		
		JLabel lblExcluirImagem = new JLabel("");
		lblExcluirImagem.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/delete-.png")));
		lblExcluirImagem.setBounds(245, 190, 25, 25);
		panelInfoPropriedade.add(lblExcluirImagem);
		
		JLabel lblMetragem = new JLabel("Metragem m\u00B2:");
		lblMetragem.setBounds(268, 61, 89, 14);
		panelInfoPropriedade.add(lblMetragem);
		
		txtMetragem = new JTextField();
		txtMetragem.setBounds(356, 58, 66, 20);
		panelInfoPropriedade.add(txtMetragem);
		txtMetragem.setColumns(10);
		
		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaFormulario();
			}
		});
		btnLimparCampos.setBounds(36, 598, 127, 23);
		contentPane.add(btnLimparCampos);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnCancelar.setBounds(200, 598, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(341, 598, 101, 23);
		contentPane.add(btnCadastrar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 178, 432, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 340, 422, 2);
		contentPane.add(separator_1);
		
		JLabel lblSave = new JLabel("");
		lblSave.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/save.png")));
		lblSave.setBounds(312, 598, 25, 25);
		contentPane.add(lblSave);
		
		JLabel lblCancelar = new JLabel("");
		lblCancelar.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/delete-.png")));
		lblCancelar.setBounds(173, 598, 25, 25);
		contentPane.add(lblCancelar);
		
		lblClear = new JLabel("");
		lblClear.setIcon(new ImageIcon(frmCadastroPropriedade.class.getResource("/br/com/images/clear.png")));
		lblClear.setBounds(10, 598, 25, 25);
		contentPane.add(lblClear);
		
		setLocationRelativeTo(null);		
	}
	
	public void fechar(){
		this.dispose();
	}
	
	public static void setProprietario(cp p){
		txtNomeProprietario.setText(p.getNome());
		txtEmailProprietario.setText(p.getEmailPessoal());
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
