package br.com.sani.telas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import br.com.sani.bean.Metas;
import br.com.sani.dao.MetasDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.exception.EntradaUsuarioException;
import br.com.sani.util.SwingUtil;
import br.com.sani.util.TelaUtil;

import com.toedter.calendar.JDateChooser;

public class frmCadastroMetas extends JFrame {

	private JPanel contentPane;
	private JTextArea txtAreaMeta;
	private JEditorPane epDescrMeta;
	private JDateChooser dtDataFim;
	private JDateChooser dtInicio;
	private JButton btnCadastrarMeta;
	private JButton btnLimpar;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCadastroMetas frame = new frmCadastroMetas();
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
	public frmCadastroMetas() {
		SwingUtil.lookWindows(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmCadastroMetas.class.getResource("/br/com/images/meta.png")));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Cadastro de Metas");
		setResizable(false);
		setBounds(100, 100, 430, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMeta = new JLabel("Meta:");
		lblMeta.setBounds(10, 11, 46, 14);
		contentPane.add(lblMeta);
		
		JLabel lblDataDaMeta = new JLabel("Data de In\u00EDcio:");
		lblDataDaMeta.setBounds(10, 172, 86, 14);
		contentPane.add(lblDataDaMeta);
		
		JLabel lblDataFinal = new JLabel("Data Final:");
		lblDataFinal.setBounds(230, 172, 69, 14);
		contentPane.add(lblDataFinal);
		
		btnCadastrarMeta = new JButton("Cadastrar");
		btnCadastrarMeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvarMeta();
			}
		});
		btnCadastrarMeta.setBounds(314, 238, 100, 23);
		contentPane.add(btnCadastrarMeta);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();		
			}
		});
		
		btnCancelar.setBounds(46, 238, 89, 23);
		contentPane.add(btnCancelar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaFormulario();
			}
		});
		btnLimpar.setBounds(175, 238, 89, 23);
		contentPane.add(btnLimpar);
		
		JLabel lblSave = new JLabel("");
		lblSave.setIcon(new ImageIcon(frmCadastroMetas.class.getResource("/br/com/images/save.png")));
		lblSave.setBounds(279, 238, 25, 25);
		contentPane.add(lblSave);
		
		JLabel lblCancelar = new JLabel("");
		lblCancelar.setIcon(new ImageIcon(frmCadastroMetas.class.getResource("/br/com/images/delete-.png")));
		lblCancelar.setBounds(145, 238, 25, 25);
		contentPane.add(lblCancelar);
		
		JLabel lblClear = new JLabel("");
		lblClear.setIcon(new ImageIcon(frmCadastroMetas.class.getResource("/br/com/images/clear.png")));
		lblClear.setBounds(10, 238, 25, 25);
		contentPane.add(lblClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 11, 350, 127);
		contentPane.add(scrollPane);
		
		epDescrMeta = new JEditorPane();
		scrollPane.setViewportView(epDescrMeta);
		
		dtInicio = new JDateChooser();
		dtInicio.setDateFormatString("dd/MM/yyyy");
		dtInicio.setBounds(114, 172, 100, 19);
		contentPane.add(dtInicio);
		
		dtDataFim = new JDateChooser();
		dtDataFim.setDateFormatString("dd/MM/yyyy");
		dtDataFim.setBounds(314, 172, 100, 19);
		contentPane.add(dtDataFim);
	}
	
	public void fechar(){
		this.dispose();
	}
	
	public void limpaFormulario(){
		epDescrMeta.setText("");
		dtInicio.setDate(null);
		dtDataFim.setDate(null);
			
	}
	
	private Metas getBean() throws EntradaUsuarioException{
		Metas m = new Metas();
		
		m.setDescrMeta(TelaUtil.getCampoObrigatorio(epDescrMeta, true));
		m.setDataInicio(dtInicio.getDate());
		m.setDataFim(dtDataFim.getDate());
		
		return m;
	}
	
	private void salvarMeta(){
		try{
			Metas m = getBean();
			new MetasDAO().inserir(m);
			limpaFormulario();
			JOptionPane.showMessageDialog(this, "Cadastro efetuado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
		}catch(DAOException e){
			e.printStackTrace();
		} catch (EntradaUsuarioException e) {
			e.printStackTrace();
		}
	}
}
