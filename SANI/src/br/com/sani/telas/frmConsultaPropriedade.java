package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.com.sani.bean.ClienteProprietario;
import br.com.sani.bean.Propriedade;
import br.com.sani.dao.ClienteCompradorFisicaDAO;
import br.com.sani.dao.ClienteCompradorJuridicaDAO;
import br.com.sani.dao.ClienteProprietarioDAO;
import br.com.sani.dao.PropriedadeDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.util.FormatarNumero;
import br.com.sani.util.SwingUtil;
import br.com.sani.util.TelaUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jdesktop.xswingx.JXSearchField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmConsultaPropriedade extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private int requisicao = 0;
	private JXSearchField txtFiltro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new frmConsultaPropriedade(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public frmConsultaPropriedade(int requisicao){
		this.requisicao = requisicao;
		montarComponentes();
		buscar();
		setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public void montarComponentes() {
		SwingUtil.lookWindows(this);
		setTitle("Consulta de Propriedades");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmConsultaPropriedade.class.getResource("/br/com/images/search.png")));
		setBounds(100, 100, 690, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 645, 311);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				retornaRequisicao();
			}
		});
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Endere\u00E7o", "Propriet\u00E1rio", "Valor R$"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(69);
		table.getColumnModel().getColumn(1).setPreferredWidth(291);
		table.getColumnModel().getColumn(2).setPreferredWidth(182);
		table.getColumnModel().getColumn(3).setPreferredWidth(92);
		scrollPane.setViewportView(table);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mntmEditarRegistro = new JMenuItem("Editar Registro");
		mntmEditarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editar();
			}
		});
		mntmEditarRegistro.setIcon(new ImageIcon(frmConsultaPropriedade.class.getResource("/br/com/images/edit-.png")));
		popupMenu.add(mntmEditarRegistro);
		
		JMenuItem mntmExcluirRegistro = new JMenuItem("Excluir Registro");
		mntmExcluirRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		mntmExcluirRegistro.setIcon(new ImageIcon(frmConsultaPropriedade.class.getResource("/br/com/images/delete-.png")));
		popupMenu.add(mntmExcluirRegistro);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Filtro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 644, 58);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txtFiltro = new JXSearchField();
		txtFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar();
			}
		});
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				buscar();
			}
		});
		txtFiltro.setPrompt("Digite o nome do Proprietario");
		txtFiltro.setToolTipText("Digite o nome do Proprietario");
		txtFiltro.setBounds(130, 25, 504, 22);
		panel_1.add(txtFiltro);
		
		setLocationRelativeTo(null);
	}
	
	private void excluir(){
		int row = table.getSelectedRow();
		try{
			if(row != -1){
				int id = Integer.parseInt(String.valueOf((Object) table.getValueAt(row, 0)));
				
				int question = JOptionPane.showConfirmDialog(this, "Deseja exluir o registro: " + id + " ?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(question == 0){
					new PropriedadeDAO().exluir(id);
					JOptionPane.showMessageDialog(this, "Registro apagado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
					buscar();
				}
			}
		}catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	private void editar(){
		int row = table.getSelectedRow();
		try{
			if(row != -1){
				int id = Integer.parseInt(String.valueOf((Object) table.getValueAt(row, 0)));
				
				Propriedade p = new PropriedadeDAO().buscarPorId(id);
				
				if(p != null){
					new frmCadastroPropriedade(p);
				}
				
			}
		}catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void buscar(){
		try {
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);

			List<Propriedade> lista = new PropriedadeDAO().buscarPorNome(txtFiltro.getText());
			
			if(lista != null){
				for(Propriedade p : lista){
					ClienteProprietario pro = new ClienteProprietarioDAO().buscarPorId(p.getClienteProprietario().getCodCliProprietario());
					p.setClienteProprietario(pro);
					addTable(dtm, p);
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	private void addTable(DefaultTableModel dtm, Propriedade p){
		Object[] object = new Object[4];
		int i = 0;
		
		String proprietario = "";
		
		if(p.getClienteProprietario().getTpCliente().equals("PF")){
			proprietario = p.getClienteProprietario().getClienteProprietarioFisica().getNome();
		}else{
			proprietario = p.getClienteProprietario().getClienteProprietarioJuridica().getRazaoSocial();
		}
		
		object[i++] = FormatarNumero.formatNumero(5, p.getCodPropriedade());
		object[i++] = p.getEndereco().getEndereco() + ", " + p.getNumeroEndereco() + " " + p.getEndereco().getBairro() + " - " + p.getEndereco().getCidade() +
				"/" + p.getEndereco().getEstado() + " CEP: " + p.getEndereco().getCep();
		object[i++] = proprietario;
		object[i++] = TelaUtil.formataDinheiro(p.getValorPropriedade());
		
		dtm.addRow(object);
	}
	
	private void retornaRequisicao(){
		int row = table.getSelectedRow();
		try{
			if(row != -1){
				int codigo = Integer.parseInt((String) table.getValueAt(row, 0));
				Object[] retorno = new Object[4];
				if(this.requisicao == 1){
					Propriedade p = new PropriedadeDAO().buscarPorId(codigo);
					ClienteProprietario cli = new ClienteProprietarioDAO().buscarPorId(p.getClienteProprietario().getCodCliProprietario());
					
					String nmProprietario = "";
					if(cli.getTpCliente().equals("PF")){
						nmProprietario = cli.getClienteProprietarioFisica().getNome();
					}else{
						nmProprietario = cli.getClienteProprietarioJuridica().getRazaoSocial();
					}
					
					retorno[0] = (int) p.getCodPropriedade();
					retorno[1] = nmProprietario;
					retorno[2] = (String) p.getEndereco().getEndereco() + ", " + p.getNumeroEndereco() + " " + p.getEndereco().getBairro() + " - " + p.getEndereco().getCidade() +
							"/" + p.getEndereco().getEstado() + " CEP: " + p.getEndereco().getCep();
					retorno[3] = (String) cli.getTelefone();
					
					frmGerarProposta.setPropriedade(retorno);
					dispose();
				}
			}
		}catch(DAOException e){
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






