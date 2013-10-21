package br.com.sani.telas;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.sani.bean.Endereco;
import br.com.sani.dao.EnderecoDAO;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;
import br.com.sani.util.TelaUtil;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmVenda extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeCorretor;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField textField;
	private JFormattedTextField ftCep;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JTextField txtCpfCorretor;
	private JTextField txtCreci;
	private JTextField txtNomeProprietario;
	private JTextField txtCpfProprietario;
	private JTextField txtRgProprietario;
	//private JTextField txtCidade;
	//private JTextField txtBairro;
	//private JTextField txtEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmVenda frame = new frmVenda();
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
	public frmVenda() throws ParseException {
		setResizable(false);
		setType(Type.UTILITY);
		SwingUtil.lookWindows(this);
		setTitle("Venda");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmVenda.class.getResource("/br/com/images/HOME.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelPropriedade = new JPanel();
		panelPropriedade.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Propriedade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPropriedade.setBounds(10, 156, 433, 191);
		contentPane.add(panelPropriedade);
		panelPropriedade.setLayout(null);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(98, 62, 308, 20);
		panelPropriedade.add(txtEndereco);
		txtEndereco.setEditable(false);
		txtEndereco.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(33, 65, 55, 14);
		panelPropriedade.add(lblEndereco);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(58, 34, 30, 14);
		panelPropriedade.add(lblCep);
		
		ftCep = new JFormattedTextField(Mascara.setMaskCepInTf(ftCep));
		ftCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				buscarEndereco();
				txtNumero.requestFocus();
			}
		});
		ftCep.setBounds(98, 31, 68, 20);
		panelPropriedade.add(ftCep);
		
		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(246, 96, 46, 14);
		panelPropriedade.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(302, 93, 104, 20);
		panelPropriedade.add(txtNumero);
		txtNumero.setColumns(10);
		
		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setBounds(10, 163, 78, 14);
		panelPropriedade.add(lblComplemento);
		
		textField = new JTextField();
		textField.setBounds(98, 160, 308, 20);
		panelPropriedade.add(textField);
		textField.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(42, 96, 46, 14);
		panelPropriedade.add(lblBairro);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(42, 127, 46, 14);
		panelPropriedade.add(lblCidade);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(248, 127, 38, 14);
		panelPropriedade.add(lblEstado);
		
		txtBairro = new JTextField();
		txtBairro.setEditable(false);
		txtBairro.setBounds(98, 93, 126, 20);
		panelPropriedade.add(txtBairro);
		txtBairro.setColumns(10);
		
		txtCidade = new JTextField();
		txtCidade.setEditable(false);
		txtCidade.setBounds(98, 124, 126, 20);
		panelPropriedade.add(txtCidade);
		txtCidade.setColumns(10);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setBounds(302, 124, 104, 20);
		panelPropriedade.add(txtEstado);
		txtEstado.setColumns(10);
		
		JPanel panelCorretor = new JPanel();
		panelCorretor.setBorder(new TitledBorder(null, "Vendedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCorretor.setBounds(10, 11, 433, 138);
		contentPane.add(panelCorretor);
		panelCorretor.setLayout(null);
		
		txtNomeCorretor = new JTextField();
		txtNomeCorretor.setBounds(77, 32, 180, 20);
		panelCorretor.add(txtNomeCorretor);
		txtNomeCorretor.setEditable(false);
		txtNomeCorretor.setColumns(10);
		
		JButton btnProcurarVendedor = new JButton("Procurar");
		btnProcurarVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmConsultaFuncionario frmCF = new frmConsultaFuncionario();
				frmCF.setVisible(true);
			}
		});
		btnProcurarVendedor.setBounds(308, 63, 115, 20);
		panelCorretor.add(btnProcurarVendedor);
		btnProcurarVendedor.setIcon(null);
		
		JLabel lblNomeCorretor = new JLabel("Nome:");
		lblNomeCorretor.setBounds(21, 35, 37, 14);
		panelCorretor.add(lblNomeCorretor);
		
		txtCpfCorretor = new JTextField();
		txtCpfCorretor.setEditable(false);
		txtCpfCorretor.setBounds(77, 63, 180, 20);
		panelCorretor.add(txtCpfCorretor);
		txtCpfCorretor.setColumns(10);
		
		JLabel lblCpfCorretor = new JLabel("CPF:");
		lblCpfCorretor.setBounds(29, 66, 29, 14);
		panelCorretor.add(lblCpfCorretor);
		
		JLabel lblCreci = new JLabel("CRECI:");
		lblCreci.setBounds(21, 95, 37, 14);
		panelCorretor.add(lblCreci);
		
		txtCreci = new JTextField();
		txtCreci.setEditable(false);
		txtCreci.setBounds(77, 94, 86, 20);
		panelCorretor.add(txtCreci);
		txtCreci.setColumns(10);
		
		JPanel panelProprietario = new JPanel();
		panelProprietario.setBorder(new TitledBorder(null, "Proprietario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProprietario.setBounds(453, 11, 243, 138);
		contentPane.add(panelProprietario);
		panelProprietario.setLayout(null);
		
		JLabel lblNomeProprietario = new JLabel("Nome:");
		lblNomeProprietario.setBounds(10, 22, 31, 14);
		panelProprietario.add(lblNomeProprietario);
		
		txtNomeProprietario = new JTextField();
		txtNomeProprietario.setEditable(false);
		txtNomeProprietario.setBounds(66, 19, 167, 20);
		panelProprietario.add(txtNomeProprietario);
		txtNomeProprietario.setColumns(10);
		
		JLabel lblCpfProprietario = new JLabel("CPF:");
		lblCpfProprietario.setBounds(18, 53, 23, 14);
		panelProprietario.add(lblCpfProprietario);
		
		txtCpfProprietario = new JTextField();
		txtCpfProprietario.setEditable(false);
		txtCpfProprietario.setBounds(66, 50, 105, 20);
		panelProprietario.add(txtCpfProprietario);
		txtCpfProprietario.setColumns(10);
		
		JButton btnProcurarProprietario = new JButton("Procurar");
		btnProcurarProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnProcurarProprietario.setBounds(66, 107, 105, 20);
		panelProprietario.add(btnProcurarProprietario);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setBounds(23, 78, 18, 14);
		panelProprietario.add(lblRg);
		
		txtRgProprietario = new JTextField();
		txtRgProprietario.setEditable(false);
		txtRgProprietario.setBounds(66, 76, 105, 20);
		panelProprietario.add(txtRgProprietario);
		txtRgProprietario.setColumns(10);
	}
	
	private void buscarEndereco(){
		try{
			Endereco endereco = new EnderecoDAO().buscarPorCep(TelaUtil.getCep(ftCep, true));
			if(endereco != null){
				txtEndereco.setText(endereco.getEndereco());
				txtCidade.setText(endereco.getCidade());
				txtBairro.setText(endereco.getBairro());
				txtEstado.setText(endereco.getEstado());
			}else{
				JOptionPane.showMessageDialog(null, "O cep digitado n�o existe!");
				ftCep.setText("");
				ftCep.requestFocus();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}