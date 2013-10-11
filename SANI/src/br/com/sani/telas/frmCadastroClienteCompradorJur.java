package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.sani.bean.ClienteComprador;
import br.com.sani.dao.ClienteCompradorJuridicaDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.exception.EntradaUsuarioException;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;
import br.com.sani.util.TelaUtil;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import org.jdesktop.swingx.JXDatePicker;
import java.awt.Font;

public class frmCadastroClienteCompradorJur extends JFrame {

	private JPanel contentPane;
	private JTextField txtRazaoSocial;
	private JTextField txtEndereco;
	private JTextField txtNumeroResidencia;
	private JTextField txtComplemento;
	private JTextField txtEmail;
	private JFormattedTextField ftCnpj;
	
	private String[] estadoCivil = new String[]{"S", "C", "D", "V"};
	private JFormattedTextField ftCep;
	private JFormattedTextField ftTelefone;
	private JFormattedTextField ftCelular;
	private JFormattedTextField ftInscEstadual;
	private JXDatePicker dtFundacao;
	private JLabel lblDataDeFundacao;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JButton btnCancelar;
	private JComboBox cbRamoAtuacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new frmCadastroClienteCompradorJur();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public frmCadastroClienteCompradorJur(){
		try {
			montarComponentes();
			
			setLocationRelativeTo(null);
			setVisible(true);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public void montarComponentes() throws ParseException {
		SwingUtil.lookWindows(this);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmCadastroClienteCompradorJur.class.getResource("/br/com/images/home_badge.png")));
		setTitle("Cadastro Cliente Comprador / Pessoa Juridica");
		setBounds(100, 100, 747, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		ButtonGroup grupoSexo = new ButtonGroup();
		
		
		btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setIcon(new ImageIcon(frmCadastroClienteCompradorJur.class.getResource("/br/com/images/clear.png")));
		btnLimpar.setBounds(575, 302, 146, 29);
		panel.add(btnLimpar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(frmCadastroClienteCompradorJur.class.getResource("/br/com/images/delete-.png")));
		btnCancelar.setBounds(575, 347, 146, 29);
		panel.add(btnCancelar);
		
		btnSalvar = new JButton("Cadastrar");
		btnSalvar.setIcon(new ImageIcon(frmCadastroClienteCompradorJur.class.getResource("/br/com/images/apply-.png")));
		btnSalvar.setBounds(575, 257, 146, 29);
		panel.add(btnSalvar);
		
		JPanel panelEndereco = new JPanel();
		panelEndereco.setBounds(15, 183, 435, 193);
		panelEndereco.setBorder(new TitledBorder(null, "Endere\u00E7o*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panelEndereco);
		panelEndereco.setLayout(null);
		
		txtEndereco = new JTextField();
		txtEndereco.setEnabled(false);
		txtEndereco.setBounds(94, 56, 318, 20);
		panelEndereco.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		ftCep = new JFormattedTextField(Mascara.setMaskCepInTf(ftCep));
		ftCep.setBounds(94, 27, 86, 20);
		panelEndereco.add(ftCep);
		ftCep.setColumns(10);
		
		txtComplemento = new JTextField();
		txtComplemento.setBounds(94, 150, 318, 20);
		panelEndereco.add(txtComplemento);
		txtComplemento.setColumns(10);
		
		JLabel lblNumeroClienteComprador = new JLabel("N\u00FAmero:");
		lblNumeroClienteComprador.setBounds(264, 90, 50, 14);
		panelEndereco.add(lblNumeroClienteComprador);
		
		JLabel lblCepClienteComprador = new JLabel("CEP:");
		lblCepClienteComprador.setBounds(21, 30, 32, 14);
		panelEndereco.add(lblCepClienteComprador);
		
		JLabel lblEnderecoClienteComprador = new JLabel("Endere\u00E7o:\r\n");
		lblEnderecoClienteComprador.setBounds(21, 60, 58, 14);
		panelEndereco.add(lblEnderecoClienteComprador);
		
		JLabel lblComplementoClienteComprador = new JLabel("Complemento:");
		lblComplementoClienteComprador.setBounds(21, 153, 69, 14);
		panelEndereco.add(lblComplementoClienteComprador);
		
		txtNumeroResidencia = new JTextField();
		txtNumeroResidencia.setBounds(326, 87, 86, 20);
		panelEndereco.add(txtNumeroResidencia);
		txtNumeroResidencia.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(21, 90, 46, 14);
		panelEndereco.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setEditable(false);
		txtBairro.setBounds(94, 87, 154, 20);
		panelEndereco.add(txtBairro);
		txtBairro.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(21, 122, 46, 14);
		panelEndereco.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setEditable(false);
		txtCidade.setBounds(94, 119, 154, 20);
		panelEndereco.add(txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(264, 122, 50, 14);
		panelEndereco.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		txtEstado.setBounds(326, 119, 86, 20);
		panelEndereco.add(txtEstado);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(321, 27, 91, 20);
		panelEndereco.add(btnPesquisar);
		
		JPanel panelContato = new JPanel();
		panelContato.setBounds(465, 11, 251, 161);
		panelContato.setBorder(new TitledBorder(null, "Contato*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panelContato);
		panelContato.setLayout(null);
		
		ftCelular = new JFormattedTextField(Mascara.setMaskCelularInTf(ftCelular));
		ftCelular.setBounds(136, 27, 91, 20);
		panelContato.add(ftCelular);
		ftCelular.setColumns(10);
		
		JLabel lblTelefoneResidencialClienteComprador = new JLabel("Telefone Residencial:");
		lblTelefoneResidencialClienteComprador.setBounds(22, 60, 102, 14);
		panelContato.add(lblTelefoneResidencialClienteComprador);
		
		JLabel lblTelefoneCelularClienteComprador = new JLabel("Telefone Celular: *");
		lblTelefoneCelularClienteComprador.setBounds(22, 30, 91, 14);
		panelContato.add(lblTelefoneCelularClienteComprador);
		
		JLabel lblEmailClienteComprador = new JLabel("Email: *");
		lblEmailClienteComprador.setBounds(22, 90, 37, 14);
		panelContato.add(lblEmailClienteComprador);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(74, 87, 153, 20);
		panelContato.add(txtEmail);
		txtEmail.setColumns(10);
		
		ftTelefone = new JFormattedTextField(Mascara.setMaskTelefoneInTf(ftTelefone));
		ftTelefone.setBounds(136, 57, 91, 20);
		panelContato.add(ftTelefone);
		ftTelefone.setColumns(10);
		
		JPanel ppanelInfo = new JPanel();
		ppanelInfo.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es B\u00E1sicas*", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ppanelInfo.setBounds(15, 11, 435, 161);
		panel.add(ppanelInfo);
		ppanelInfo.setLayout(null);
		
		JLabel lblCpfClienteComprador = new JLabel("CNPJ:");
		lblCpfClienteComprador.setBounds(21, 60, 32, 14);
		ppanelInfo.add(lblCpfClienteComprador);
		
		JLabel lblNomeClienteComprador = new JLabel("Raz\u00E3o Social:");
		lblNomeClienteComprador.setBounds(21, 30, 71, 14);
		ppanelInfo.add(lblNomeClienteComprador);
		
		ftCnpj = new JFormattedTextField(Mascara.setMaskCnpjInTf(ftCnpj));
		ftCnpj.setBounds(102, 57, 110, 20);
		ppanelInfo.add(ftCnpj);
		ftCnpj.setColumns(10);
		
		JLabel lblRgClienteComprador = new JLabel("Inscri\u00E7\u00E3o Estadual:");
		lblRgClienteComprador.setBounds(222, 60, 91, 14);
		ppanelInfo.add(lblRgClienteComprador);
		
		ftInscEstadual = new JFormattedTextField(Mascara.setMaskInscrEstadualInTf(ftInscEstadual));
		ftInscEstadual.setBounds(321, 57, 96, 20);
		ppanelInfo.add(ftInscEstadual);
		ftInscEstadual.setColumns(10);
		
		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setBounds(102, 27, 315, 20);
		ppanelInfo.add(txtRazaoSocial);
		txtRazaoSocial.setColumns(10);
		
		lblDataDeFundacao = new JLabel("Dt. Funda\u00E7\u00E3o:");
		lblDataDeFundacao.setBounds(21, 92, 71, 14);
		ppanelInfo.add(lblDataDeFundacao);
		
		dtFundacao = new JXDatePicker();
		dtFundacao.setFormats(new String[] {"dd/MM/yyyy"});
		dtFundacao.setBounds(102, 89, 110, 20);
		ppanelInfo.add(dtFundacao);
		
		cbRamoAtuacao = new JComboBox();
		cbRamoAtuacao.setToolTipText("Ramo de Atividade");
		cbRamoAtuacao.setFont(new Font("Tahoma", Font.PLAIN, 9));
		cbRamoAtuacao.setMaximumRowCount(20);
		cbRamoAtuacao.setModel(new DefaultComboBoxModel(new String[] {"1 Agricultura (lavouras permanentes e tempor\u00E1rias, horticultura, floricultura e outras culturas)", "2 Produ\u00E7\u00E3o de sementes e mudas certificadas ", "3 Pecu\u00E1ria (bovinos, su\u00EDnos, aves, equinos, ovinos, caprinos e outros)", "4 Atividades de ca\u00E7a", "5 Produ\u00E7\u00E3o florestal", "6 Pesca e aquicultura", "7 Atividades de apoio \u00E0 agricultura, pecu\u00E1ria, pesca, ca\u00E7a e produ\u00E7\u00E3o florestal", "8 Extra\u00E7\u00E3o e beneficiamento de carv\u00E3o mineral", "9 Extra\u00E7\u00E3o e beneficiamento de petr\u00F3leo, g\u00E1s natural, xisto e areias betuminosas", "10 Extra\u00E7\u00E3o e beneficiamento de min\u00E9rio de ferro, inclusive pelotiza\u00E7\u00E3o e sinteriza\u00E7\u00E3o", "11 Extra\u00E7\u00E3o e beneficiamento de minerais met\u00E1licos n\u00E3o ferrosos (alum\u00EDnio, estanho, mangan\u00EAs, chumbo, zinco, ni\u00F3bio, n\u00EDquel etc)", "12 Extra\u00E7\u00E3o e beneficiamento de minerais n\u00E3o-met\u00E1licos (calc\u00E1rio, m\u00E1rmore, ard\u00F3sia e outras pedras, sal, areias, argilas e outras)", "13 Atividades de apoio \u00E0 extra\u00E7\u00E3o de petr\u00F3leo e g\u00E1s natural ", "14 Atividades de apoio \u00E0 extra\u00E7\u00E3o de minerais, exceto petr\u00F3leo e g\u00E1s natural", "15 Fabrica\u00E7\u00E3o de produtos derivados carnes, inclusive abate ", "16 Fabrica\u00E7\u00E3o e preserva\u00E7\u00E3o de produtos do pescado", "17 Fabrica\u00E7\u00E3o de conservas de frutas, legumes e outros vegetais ", "18 Fabrica\u00E7\u00E3o de margarinas, \u00F3leos e produtos de gorduras vegetais e animais", "19 Fabrica\u00E7\u00E3o de produtos derivados do leite, latic\u00EDnios, sorvetes e outros gelados comest\u00EDveis", "20 Fabrica\u00E7\u00E3o de massas, biscoitos, bolachas, panifica\u00E7\u00E3o, derivados do cacau, chocolates e outros confeitos", "21 Fabrica\u00E7\u00E3o e moagem de produtos amil\u00E1ceos (arroz, trigo, farinhas e similares) e de alimentos para animais", "22 Fabrica\u00E7\u00E3o e refino de a\u00E7\u00FAcar", "23 Torrefa\u00E7\u00E3o e moagem de caf\u00E9", "24 Fabrica\u00E7\u00E3o de outros produtos aliment\u00EDcios", "25 Fabrica\u00E7\u00E3o de bebidas alc\u00F3olicas e n\u00E3o alc\u00F3olicas (refrigerantes, sucos, refrescos, ch\u00E1s, xaropes etc)", "26 Processamento e fabrica\u00E7\u00E3o de produtos do fumo ", "27 Fabrica\u00E7\u00E3o de produtos t\u00EAxteis (fia\u00E7\u00E3o, tecelagem, malharia e outros artefatos t\u00EAxteis, inclusive acabamento)", "28 Confec\u00E7\u00E3o de artigos do vestu\u00E1rio e acess\u00F3rios", "29 Curtimento e outras prepara\u00E7\u00F5es de couro", "30 Fabrica\u00E7\u00E3o de artigos para viagem e de artefatos diversos de couro", "31 Fabrica\u00E7\u00E3o de cal\u00E7ados e de partes para cal\u00E7ados de todos os tipos", "32 Fabrica\u00E7\u00E3o de produtos de madeira, inclusive embalagens e exceto m\u00F3veis", "33 Fabrica\u00E7\u00E3o de celulose, papel e produtos de papel, inclusive embalagens", "34 Impress\u00E3o e reprodu\u00E7\u00E3o de grava\u00E7\u00F5es gr\u00E1ficas (jornais, livros, revistas, material publicit\u00E1rio e outros produtos gr\u00E1ficos)", "35 Impress\u00E3o e reprodu\u00E7\u00E3o de grava\u00E7\u00F5es (som, v\u00EDdeo, imagens, softwares)", "36 Coquerias (carv\u00E3o mineral)", "37 Fabrica\u00E7\u00E3o de produtos derivados do petr\u00F3leo", "38 Fabrica\u00E7\u00E3o de biocombust\u00EDveis", "39 Fabrica\u00E7\u00E3o de produtos qu\u00EDmicos inorg\u00E2nicos (\u00E1lcalis, adubos, fertilizantes, gases industriais)", "40 Fabrica\u00E7\u00E3o de produtos qu\u00EDmicos org\u00E2nicos (produtos petroqu\u00EDmicos b\u00E1sicos)", "41 Fabrica\u00E7\u00E3o de resinas e elast\u00F4meros (resinas termopl\u00E1sticas, resinas termofixas, elast\u00F4meros)", "42 Fabrica\u00E7\u00E3o de fibras artificiais e sint\u00E9ticas", "43 Fabrica\u00E7\u00E3o de defensivos agr\u00EDcolas e desinfestantes domissanit\u00E1rios", "44 Fabrica\u00E7\u00E3o de sab\u00F5es, detergentes, produtos de limpeza, cosm\u00E9ticos, produtos de perfumaria e de higiene pessoal", "45 Fabrica\u00E7\u00E3o de tintas, vernizes, esmaltes, lacas, impermeabilizantes, solventes e produtos afins", "46 Fabrica\u00E7\u00E3o de produtos e preparados qu\u00EDmicos diversos (adesivos, selantes, explosivos, aditivos, catalisadores etc)", "47 Fabrica\u00E7\u00E3o de produtos farmoqu\u00EDmicos e farmac\u00EAuticos (medicamentos para uso humano e veterin\u00E1rio)", "48 Fabrica\u00E7\u00E3o de produtos de material borracha (pneum\u00E1ticos e de c\u00E2maras-de-ar, pneum\u00E1ticos usados, outros artefatos de borracha)", "49 Fabrica\u00E7\u00E3o de produtos de material pl\u00E1stico (laminados planos e tubulares, embalagens, tubos e acess\u00F3rios, artefatos para uso industrial, pessoal e dom\u00E9stico)", "50 Fabrica\u00E7\u00E3o de vidro e de produtos do vidro (vidros plano e de seguran\u00E7a, embalagens e artigos diversos)", "51 Fabrica\u00E7\u00E3o de cimento e concreto (massa de concreto e argamassa)", "52 Fabrica\u00E7\u00E3o de artefatos de concreto, cimento, fibrocimento, gesso, estruturas pr\u00E9-moldadas diversas e materiais semelhantes ", "53 Fabrica\u00E7\u00E3o de produtos cer\u00E2micos (produtos cer\u00E2micos refrat\u00E1rios, azulejos, pisos)", "54 Fabrica\u00E7\u00E3o de outros produtos de minerais n\u00E3o-met\u00E1licos (aparelhamento de pedras e fabrica\u00E7\u00E3o de outros produtos), inclusive cal e gesso", "55 Siderurgia, produ\u00E7\u00E3o de ferro-gusa e de ferroligas (semiacabados, tarugos, laminados, arames, tubos de a\u00E7o sem costura, relaminados, trefilados e perfilados)", "56 Metalurgia (tubos outros, artigos de alum\u00EDnio e suas ligas em formas prim\u00E1rias, metalurgia de metais preciosos, do cobre, zinco e laminados de zinco, soldas e \u00E2nodos para galvanoplastia)", "57 Fundi\u00E7\u00E3o em geral", "58 Fabrica\u00E7\u00E3o de produtos de metal (estruturas, caldeiras, tanques, forjados, artefatos, usinagem, solda, tratamento e revestimento em metais, cutelaria, serralheria, ferramentas, embalagens, trefilados)", "59 Fabrica\u00E7\u00E3o de equipamento b\u00E9lico pesado, armas e muni\u00E7\u00F5es", "60 Fabrica\u00E7\u00E3o de equipamentos e componentes de inform\u00E1tica e perif\u00E9ricos, de comunica\u00E7\u00E3o e transmiss\u00E3o, de audio e v\u00EDdeo, cinematogr\u00E1ficos, produtos eletr\u00F4nicos e \u00F3pticos", "61 Fabrica\u00E7\u00E3o de aparelhos e instrumentos de medida, teste e controle", "62 Fabrica\u00E7\u00E3o de cron\u00F4metros e rel\u00F3gios", "63 Fabrica\u00E7\u00E3o de aparelhos m\u00E9dico-hospitalares (eletrom\u00E9dicos e eletroterap\u00EAuticos e equipamentos de irradia\u00E7\u00E3o)", "64 Fabrica\u00E7\u00E3o de outros produtos auxiliares e complementares aos de inform\u00E1tica e eletr\u00F4nicos", "65 Fabrica\u00E7\u00E3o de m\u00E1quinas, aparelhos e materiais el\u00E9tricos (geradores, motores, transformadores, pilhas, baterias, acumuladores)", "66 Fabrica\u00E7\u00E3o de equipamento de controle e de transmiss\u00E3o de energia el\u00E9trica (cabos, fios, condutores, controles de energia)", "67 Fabrica\u00E7\u00E3o de l\u00E2mpadas e outros equipamentos de ilumina\u00E7\u00E3o ", "68 Fabrica\u00E7\u00E3o de eletrodom\u00E9sticos (fog\u00F5es, refrigeradores, m\u00E1quinas de lavar e secar para uso dom\u00E9stico, uso pessoal etc)", "69 Fabrica\u00E7\u00E3o de m\u00E1quinas e equipamentos (motores, bombas, turbinas, compressores, v\u00E1lvulas e registros, m\u00E1quinas-ferramenta e outros, al\u00E9m de suas pe\u00E7as e acess\u00F3rios)", "70 Fabrica\u00E7\u00E3o de equipamentos e aparelhos el\u00E9tricos n\u00E3o especificados anteriormente", "71 Fabrica\u00E7\u00E3o de tratores e de m\u00E1quinas e equipamentos para a agricultura e pecu\u00E1ria", "72 Fabrica\u00E7\u00E3o de ve\u00EDculos automotores, reboques e carrocerias ", "73 Fabrica\u00E7\u00E3o de pe\u00E7as e acess\u00F3rios para ve\u00EDculos automotores (autope\u00E7as)", "74 Fabrica\u00E7\u00E3o de embarca\u00E7\u00F5es e seus equipamentos, pe\u00E7as e acess\u00F3rios", "75 Fabrica\u00E7\u00E3o de ve\u00EDculos ferrovi\u00E1rios e seus equipamentos, pe\u00E7as e acess\u00F3rios", "76 Fabrica\u00E7\u00E3o de aeronaves e seus equipamentos, pe\u00E7as e acess\u00F3rios equipamentos, pe\u00E7as e acess\u00F3rios", "78 Fabrica\u00E7\u00E3o de m\u00F3veis", "79 Fabrica\u00E7\u00E3o de artigos de joalheria, bijuteria e semelhantes", "80 Fabrica\u00E7\u00E3o de instrumentos musicais", "81 Fabrica\u00E7\u00E3o de artefatos para pesca e esporte", "82 Fabrica\u00E7\u00E3o de brinquedos e jogos recreativos", "83 Fabrica\u00E7\u00E3o de instrumentos e materiais para uso m\u00E9dico e odontol\u00F3gico e de artigos \u00F3pticos", "84 Fabrica\u00E7\u00E3o de produtos diversos", "85 Manuten\u00E7\u00E3o, repara\u00E7\u00E3o e instala\u00E7\u00E3o de m\u00E1quinas e equipamentos", "86 Gera\u00E7\u00E3o de energia el\u00E9trica", "87 Transmiss\u00E3o e distribui\u00E7\u00E3o de energia el\u00E9trica ", "88 Transporte e distribui\u00E7\u00E3o de g\u00E1s", "89 Produ\u00E7\u00E3o, transporte e distribui\u00E7\u00E3o de outros combust\u00EDveis, l\u00EDquidos e gases", "90 Capta\u00E7\u00E3o, tratamento e distribui\u00E7\u00E3o de \u00E1gua e atividades relacionadas", "91 Capta\u00E7\u00E3o e tratamento de esgoto e atividades relacionadas", "92 Coleta, tratamento, processamento, descontamina\u00E7\u00E3o e gest\u00E3o de res\u00EDduos", "93 Constru\u00E7\u00E3o de edif\u00EDcios e habita\u00E7\u00F5es (empreendimentosimobili\u00E1rios em geral)", "94 Obras de infra-estrutura (ferrovias, rodovias, barragens e obras urbanas e similares) e servi\u00E7os para constru\u00E7\u00E3o", "95 Com\u00E9rcio e repara\u00E7\u00E3o de ve\u00EDculos automotores, motocicletas e outros equipamentos de transporte", "96 Com\u00E9rcio por atacado, exceto ve\u00EDculos automotores e motocicletas", "97 Com\u00E9rcio varejista", "98 Transporte ferrovi\u00E1rio, metroferrovi\u00E1rio e similares", "99 Transporte rodovi\u00E1rio de passageiros", "100 Transporte rodovi\u00E1rio de carga", "101 Transporte dutovi\u00E1rio", "102 Transporte aquavi\u00E1rio (carga e de passageiros)", "103 Transporte a\u00E9reo (carga e de passageiros)", "104 Armazenamento, carga e descarga e suas atividades auxiliares, inclusive transporte e gest\u00E3o/administra\u00E7\u00E3o", "105 Atividades de correio, de malote e de entrega", "106 Alojamento (hot\u00E9is e similares) e atividades relacionadas", "107 Servi\u00E7os de alimenta\u00E7\u00E3o (restaurantes, bares, cantinas, cozinhas industriais) e atividades relacionadas", "108 Edi\u00E7\u00E3o, edi\u00E7\u00E3o integrada \u00E0 impress\u00E3o e distribui\u00E7\u00E3o de publica\u00E7\u00F5es (livros, jornais, revistas etc)", "109 Atividades cinematogr\u00E1ficas, produ\u00E7\u00E3o de v\u00EDdeos e de programas de televis\u00E3o, grava\u00E7\u00E3o de som e de edi\u00E7\u00E3o de m\u00FAsica", "110 Atividades de r\u00E1dio", "111 Atividades de televis\u00E3o", "112 Telecomunica\u00E7\u00F5es, inclusive servi\u00E7os de internet", "113 Operadoras de televis\u00E3o por assinatura", "114 Servi\u00E7os de tecnologia de informa\u00E7\u00E3o, inclusive tratamento e armazenagem de dados em geral", "115 Atividades de servi\u00E7os financeiros", "116 Seguros, Resseguros, Previd\u00EAncia Complementar e Planos de S\u00E1ude", "117 Atividades Auxiliares dos Servi\u00E7os Financeiros, Seguros, Previd\u00EAncia Complementar e Planos de S\u00E1ude", "118 Aluguel e venda de im\u00F3veis habitacionais, comerciais e industriais", "119 Aluguel de meios de transporte", "120 Aluguel e gest\u00E3o de outros ativos n\u00E3o-financeiros ", "121 Aluguel de m\u00E1quinas e equipamentos", "122 Atividades jur\u00EDdicas, de contabilidade e de autitoria ", "123 Atividades de administra\u00E7\u00E3o, de consultoria e assessoria em gest\u00E3o empresarial", "124 Servi\u00E7os de arquitetura e engenharia, testes e atividades t\u00E9cnicas relacionadas", "125 Pesquisa e desenvolvimento cient\u00EDfico", "126 Publicidade e pesquisas de mercado e de opini\u00E3o p\u00FAblica", "127 Atividades profissionais, cient\u00EDficas e t\u00E9cnicas n\u00E3o especificadas ", "128 Atividades veterin\u00E1rias", "129 Sele\u00E7\u00E3o, agenciamento e loca\u00E7\u00E3o de m\u00E3o-de-obra", "130 Ag\u00EAncias de viagens, operadores tur\u00EDsticos e servi\u00E7os de reserva", "131 Atividades de vigil\u00E2ncia, seguran\u00E7a e investiga\u00E7\u00E3o", "132 Servi\u00E7os combinados para apoio a edif\u00EDcios, inclusive limpeza e paisag\u00EDsticas", "133 Servi\u00E7os de escrit\u00F3rio e apoio administrativo e outros", "134 Administra\u00E7\u00E3o p\u00FAblica, defesa e seguridade social", "135 Educa\u00E7\u00E3o", "136 Atividades de aten\u00E7\u00E3o \u00E0 sa\u00FAde humana", "137 Servi\u00E7os de assist\u00EAncia social sem alojamento", "138 Atividades art\u00EDsticas, criativas e de espet\u00E1culos", "139 Atividades ligadas ao patrim\u00F4nio cultural e ambiental", "140 Atividades de explora\u00E7\u00E3o de jogos de azar e apostas", "141 Atividades esportivas, de recrea\u00E7\u00E3o e lazer", "142 Atividades de organiza\u00E7\u00F5es associativas", "143 Servi\u00E7os de repara\u00E7\u00E3o e manuten\u00E7\u00E3o de equipamentos de inform\u00E1tica, comunica\u00E7\u00E3o e de objetos pessoais e dom\u00E9sticos ", "144 Outras atividades de servi\u00E7os pessoais e dom\u00E9sticos"}));
		cbRamoAtuacao.setBounds(102, 121, 315, 22);
		ppanelInfo.add(cbRamoAtuacao);
		
		JLabel lblRamoDeAtividade = new JLabel("Ramo de Ativ.:");
		lblRamoDeAtividade.setBounds(21, 125, 81, 14);
		ppanelInfo.add(lblRamoDeAtividade);
		
		setLocationRelativeTo(null);		
	}
}
