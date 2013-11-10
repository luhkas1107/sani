package br.com.sani.telas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTitledSeparator;

import br.com.sani.bean.Proposta;
import br.com.sani.dao.PropostaDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.exception.EntradaUsuarioException;
import br.com.sani.util.FormatarNumero;
import br.com.sani.util.Mascara;
import br.com.sani.util.Moeda;
import br.com.sani.util.SwingUtil;
import br.com.sani.util.TelaUtil;

public class frmGerarProposta extends JFrame {

	private JPanel contentPane;
	private JTextField txtProposta;
	private static JTextField txtCodComprador;
	private static JTextField txtNomeComprador;
	private JTextField txtFormaPagamento;
	private JTextField txtValorProposta;
	private static JTextField txtEndereco;
	private static JTextField txtProprietario;
	private JXDatePicker dtProposta;
	private JXDatePicker dtValidade;
	private JComboBox cbTipoProposta;
	private static JFormattedTextField ftxTelefone;
	
	private static int codPropriedade = 0;
	private static int codComprador = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new frmGerarProposta();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public frmGerarProposta(){
		try {
			montarComponentes();
			
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Gerar nova Proposta de Venda/Loca\u00E7\u00E3o");
		setBounds(100, 100, 656, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es Gerais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 630, 394);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNrProposta = new JLabel("Nr. Proposta:");
		lblNrProposta.setBounds(63, 42, 65, 14);
		panel.add(lblNrProposta);
		
		txtProposta = new JTextField();
		txtProposta.setEditable(false);
		txtProposta.setBounds(138, 39, 112, 20);
		panel.add(txtProposta);
		txtProposta.setColumns(10);
		
		JLabel lblClienteComprador = new JLabel("Cliente Comprador*:");
		lblClienteComprador.setBounds(29, 79, 99, 14);
		panel.add(lblClienteComprador);
		
		txtCodComprador = new JTextField();
		txtCodComprador.setName("Cliente Comprador");
		txtCodComprador.setEditable(false);
		txtCodComprador.setBounds(138, 76, 112, 20);
		panel.add(txtCodComprador);
		txtCodComprador.setColumns(10);
		
		txtNomeComprador = new JTextField();
		txtNomeComprador.setName("Nome Cliente");
		txtNomeComprador.setEditable(false);
		txtNomeComprador.setBounds(260, 76, 235, 20);
		panel.add(txtNomeComprador);
		txtNomeComprador.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new frmConsultaClientesComprador(1);
			}
		});
		btnPesquisar.setBounds(505, 75, 89, 23);
		panel.add(btnPesquisar);
		
		JLabel lblFormaDePagto = new JLabel("Forma de Pagto*:");
		lblFormaDePagto.setBounds(39, 112, 89, 14);
		panel.add(lblFormaDePagto);
		
		txtFormaPagamento = new JTextField();
		txtFormaPagamento.setName("Forma de pagamento");
		txtFormaPagamento.setBounds(138, 109, 357, 20);
		panel.add(txtFormaPagamento);
		txtFormaPagamento.setColumns(10);
		
		JLabel lblDataProposta = new JLabel("Data Proposta*:");
		lblDataProposta.setBounds(49, 148, 79, 14);
		panel.add(lblDataProposta);
		
		dtProposta = new JXDatePicker();
		dtProposta.setName("Data de Proposta");
		dtProposta.setFormats(new String[] {"dd/MM/yyyy"});
		dtProposta.getEditor().setLocation(0, 145);
		dtProposta.setBounds(138, 145, 113, 20);
		dtProposta.setDate(new Date());
		((JButton) dtProposta.getComponent(1)).setIcon(new ImageIcon(frmGerarProposta.class.getResource("/br/com/images/_Calendar.png")));
		panel.add(dtProposta);
		
		JLabel lblValidadeProposta = new JLabel("Validade Proposta*:");
		lblValidadeProposta.setBounds(273, 148, 99, 14);
		panel.add(lblValidadeProposta);
		
		dtValidade = new JXDatePicker();
		dtValidade.setName("Data de Validade Proposta");
		dtValidade.setFormats(new String[] {"dd/MM/yyyy"});
		dtValidade.setBounds(382, 144, 113, 22);
		((JButton) dtValidade.getComponent(1)).setIcon(new ImageIcon(frmGerarProposta.class.getResource("/br/com/images/_Calendar.png")));
		panel.add(dtValidade);
		
		JLabel lblTipoDeProposta = new JLabel("Tipo de Proposta*:");
		lblTipoDeProposta.setBounds(281, 185, 91, 14);
		panel.add(lblTipoDeProposta);
		
		cbTipoProposta = new JComboBox();
		cbTipoProposta.setModel(new DefaultComboBoxModel(new String[] {"Aluguel", "Compra"}));
		cbTipoProposta.setBounds(382, 182, 113, 20);
		panel.add(cbTipoProposta);
		
		JLabel lblValorPropostaR = new JLabel("Valor Proposta R$*:");
		lblValorPropostaR.setBounds(29, 185, 99, 14);
		panel.add(lblValorPropostaR);
		
		txtValorProposta = new JTextField();
		txtValorProposta.setName("Valor da proposta");
		txtValorProposta.setDocument(new Moeda());
		txtValorProposta.setText("0");
		txtValorProposta.setBounds(138, 182, 112, 20);
		panel.add(txtValorProposta);
		txtValorProposta.setColumns(10);
		
		JXTitledSeparator titledSeparator = new JXTitledSeparator();
		titledSeparator.setTitle("Propriedade*");
		titledSeparator.setBounds(29, 230, 572, 14);
		panel.add(titledSeparator);
		
		JButton btnSelecione = new JButton("Selecione");
		btnSelecione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new frmConsultaPropriedade(1);
			}
		});
		btnSelecione.setBounds(505, 255, 89, 23);
		panel.add(btnSelecione);
		
		JLabel lblCep = new JLabel("Endere\u00E7o:");
		lblCep.setBounds(79, 294, 49, 14);
		panel.add(lblCep);
		
		txtEndereco = new JTextField();
		txtEndereco.setEditable(false);
		txtEndereco.setBounds(138, 291, 357, 20);
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblProprietario = new JLabel("Proprietario*:");
		lblProprietario.setBounds(63, 259, 67, 14);
		panel.add(lblProprietario);
		
		txtProprietario = new JTextField();
		txtProprietario.setEditable(false);
		txtProprietario.setBounds(138, 255, 357, 20);
		panel.add(txtProprietario);
		txtProprietario.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(505, 360, 89, 23);
		panel.add(btnCancelar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}
		});
		btnLimpar.setBounds(406, 360, 89, 23);
		panel.add(btnLimpar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inserirProposta();
			}
		});
		btnSalvar.setBounds(307, 360, 89, 23);
		panel.add(btnSalvar);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(82, 327, 46, 14);
		panel.add(lblTelefone);
		
		ftxTelefone = new JFormattedTextField(Mascara.setMaskTelefoneInTf(ftxTelefone));
		ftxTelefone.setEditable(false);
		ftxTelefone.setBounds(138, 324, 112, 20);
		panel.add(ftxTelefone);
		
		setLocationRelativeTo(null);
	}
	
	public static void setPropriedade(Object[] propriedade){
		codPropriedade = (int) propriedade[0];
		txtProprietario.setText((String) propriedade[1]);
		txtEndereco.setText((String) propriedade[2]);
		ftxTelefone.setText((String) propriedade[3]);
	}
	
	public static void setComprador(Object[] comprador){
		codComprador = (int) comprador[0];
		txtCodComprador.setText(FormatarNumero.formatNumero(5, (int) comprador[0]));
		txtNomeComprador.setText((String) comprador[1]);
	}
	
	private Proposta getBean() throws EntradaUsuarioException, ParseException{
		Proposta p = new Proposta();
		
		if(codComprador != 0){
			p.setCodCliComprador(codComprador);
		}else{
			JOptionPane.showMessageDialog(this, "Selecione um cliente Comprador!", "Atenção!", JOptionPane.WARNING_MESSAGE);
			throw new EntradaUsuarioException(txtCodComprador);
		}
		
		if(codPropriedade != 0){
			p.setCodPropriedade(codPropriedade);
		}else{
			JOptionPane.showMessageDialog(this, "Selecione uma propriedade!", "Atenção!", JOptionPane.WARNING_MESSAGE);
			throw new EntradaUsuarioException(txtProprietario);
		}
		
		p.setDataProposta(TelaUtil.getDateFromDatePicker(dtProposta));
		p.setFormaPagamento(TelaUtil.getCampoObrigatorio(txtFormaPagamento, true));
		p.setTipoProposta(cbTipoProposta.getSelectedItem().toString().substring(0, 1));
		p.setValidadeProposta(TelaUtil.getDateFromDatePicker(dtValidade));
		p.setValorProposta(TelaUtil.getCampoObrigatorioDouble(txtValorProposta));
		
		return p;
		
	}
	
	private void limparFormulario(){
		txtCodComprador.setText("");
		txtEndereco.setText("");
		txtFormaPagamento.setText("");
		txtNomeComprador.setText("");
		txtProposta.setText("");
		txtProprietario.setText("");
		txtValorProposta.setText("");
		ftxTelefone.setText("");
		
		codComprador = 0;
		codPropriedade = 0;
	}
	
	private void inserirProposta(){
		try {
			Proposta p = getBean();
			new PropostaDAO().inserir(p);
			JOptionPane.showMessageDialog(this, "Proposta gerada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
			limparFormulario();
		} catch (EntradaUsuarioException | ParseException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
}
