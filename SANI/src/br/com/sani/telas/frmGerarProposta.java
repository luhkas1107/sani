package br.com.sani.telas;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

import java.awt.Toolkit;

import com.google.gdata.data.calendar.CalendarExtendedProperty;
import com.toedter.calendar.JDateChooser;

public class frmGerarProposta extends JFrame {

	private JPanel contentPane;
	private JTextField txtProposta;
	private static JTextField txtCodComprador;
	private static JTextField txtNomeComprador;
	private JTextField txtFormaPagamento;
	private JTextField txtValorProposta;
	private static JTextField txtEndereco;
	private static JTextField txtProprietario;
	private JDateChooser dtProposta;
	private JDateChooser dtValidade;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmGerarProposta.class.getResource("/br/com/images/_1proposta_icon.png")));
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
		txtProposta.setBounds(138, 39, 112, 20);
		txtProposta.setEditable(false);
		panel.add(txtProposta);
		txtProposta.setColumns(10);
		
		JLabel lblClienteComprador = new JLabel("Cliente Comprador*:");
		lblClienteComprador.setBounds(29, 79, 99, 14);
		panel.add(lblClienteComprador);
		
		txtCodComprador = new JTextField();
		txtCodComprador.setBounds(138, 76, 112, 20);
		txtCodComprador.setName("Cliente Comprador");
		txtCodComprador.setEditable(false);
		panel.add(txtCodComprador);
		txtCodComprador.setColumns(10);
		
		txtNomeComprador = new JTextField();
		txtNomeComprador.setBounds(260, 76, 235, 20);
		txtNomeComprador.setName("Nome Cliente");
		txtNomeComprador.setEditable(false);
		panel.add(txtNomeComprador);
		txtNomeComprador.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(505, 75, 115, 32);
		btnPesquisar.setIcon(new ImageIcon(frmGerarProposta.class.getResource("/br/com/images/search-ico.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new frmConsultaClientesComprador(1);
			}
		});
		panel.add(btnPesquisar);
		
		JLabel lblFormaDePagto = new JLabel("Forma de Pagto*:");
		lblFormaDePagto.setBounds(39, 112, 89, 14);
		panel.add(lblFormaDePagto);
		
		txtFormaPagamento = new JTextField();
		txtFormaPagamento.setBounds(138, 109, 357, 20);
		txtFormaPagamento.setName("Forma de pagamento");
		panel.add(txtFormaPagamento);
		txtFormaPagamento.setColumns(10);
		
		JLabel lblDataProposta = new JLabel("Data Proposta*:");
		lblDataProposta.setBounds(49, 148, 79, 14);
		panel.add(lblDataProposta);
		
		JLabel lblValidadeProposta = new JLabel("Validade Proposta*:");
		lblValidadeProposta.setBounds(273, 148, 99, 14);
		panel.add(lblValidadeProposta);
		
		JLabel lblTipoDeProposta = new JLabel("Tipo de Proposta*:");
		lblTipoDeProposta.setBounds(281, 185, 91, 14);
		panel.add(lblTipoDeProposta);
		
		cbTipoProposta = new JComboBox();
		cbTipoProposta.setBounds(382, 182, 113, 20);
		cbTipoProposta.setModel(new DefaultComboBoxModel(new String[] {"Aluguel", "Compra"}));
		panel.add(cbTipoProposta);
		
		JLabel lblValorPropostaR = new JLabel("Valor Proposta R$*:");
		lblValorPropostaR.setBounds(29, 185, 99, 14);
		panel.add(lblValorPropostaR);
		
		txtValorProposta = new JTextField();
		txtValorProposta.setBounds(138, 182, 112, 20);
		txtValorProposta.setName("Valor da proposta");
		txtValorProposta.setDocument(new Moeda());
		txtValorProposta.setText("0");
		panel.add(txtValorProposta);
		txtValorProposta.setColumns(10);
		
		JXTitledSeparator titledSeparator = new JXTitledSeparator();
		titledSeparator.setBounds(29, 230, 572, 14);
		titledSeparator.setTitle("Propriedade*");
		panel.add(titledSeparator);
		
		JButton btnSelecione = new JButton("Pesquisar");
		btnSelecione.setBounds(505, 255, 115, 32);
		btnSelecione.setIcon(new ImageIcon(frmGerarProposta.class.getResource("/br/com/images/search-ico.png")));
		btnSelecione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new frmConsultaPropriedade(1);
			}
		});
		panel.add(btnSelecione);
		
		JLabel lblCep = new JLabel("Endere\u00E7o:");
		lblCep.setBounds(79, 294, 49, 14);
		panel.add(lblCep);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(138, 291, 357, 20);
		txtEndereco.setEditable(false);
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblProprietario = new JLabel("Proprietario*:");
		lblProprietario.setBounds(63, 259, 67, 14);
		panel.add(lblProprietario);
		
		txtProprietario = new JTextField();
		txtProprietario.setBounds(138, 255, 357, 20);
		txtProprietario.setEditable(false);
		panel.add(txtProprietario);
		txtProprietario.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(273, 351, 123, 32);
		btnCancelar.setIcon(new ImageIcon(frmGerarProposta.class.getResource("/br/com/images/delete-.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnCancelar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(406, 351, 108, 32);
		btnLimpar.setIcon(new ImageIcon(frmGerarProposta.class.getResource("/br/com/images/clear.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}
		});
		panel.add(btnLimpar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(524, 351, 96, 32);
		btnSalvar.setIcon(new ImageIcon(frmGerarProposta.class.getResource("/br/com/images/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inserirProposta();
				pdf();
			}
		});
		panel.add(btnSalvar);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(82, 327, 46, 14);
		panel.add(lblTelefone);
		
		dtProposta = new JDateChooser();
		dtProposta.setBounds(138, 143, 107, 19);
		panel.add(dtProposta);
		dtProposta.setDateFormatString("dd/MM/yyyy");
		
		dtValidade = new JDateChooser();
		dtValidade.setBounds(395, 140, 100, 19);
		panel.add(dtValidade);
		dtValidade.setDateFormatString("dd/MM/yyyy");
		
		ftxTelefone = new JFormattedTextField(Mascara.setMaskTelefoneInTf(ftxTelefone));
		ftxTelefone.setBounds(138, 324, 112, 20);
		ftxTelefone.setEditable(false);
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
		
		p.setDataProposta(dtProposta.getDate());
		p.setFormaPagamento(TelaUtil.getCampoObrigatorio(txtFormaPagamento, true));
		p.setTipoProposta(cbTipoProposta.getSelectedItem().toString().substring(0, 1));
		p.setValidadeProposta(dtValidade.getDate());
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
	
	public void pdf(){
		File pdf = new File("src/br/com/sani/doc/proposta_de_compra.pdf");
		try {  
		  Desktop.getDesktop().open(pdf);  
		} catch(Exception ex) {  
		  ex.printStackTrace();  
		  JOptionPane.showMessageDialog(null, "Erro: " + ex);  
		}
	}
}
