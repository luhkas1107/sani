package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import br.com.sani.bean.ClienteComprador;
import br.com.sani.bean.ClienteCompradorFisica;
import br.com.sani.bean.ClienteCompradorJuridica;
import br.com.sani.dao.ClienteCompradorFisicaDAO;
import br.com.sani.dao.ClienteCompradorJuridicaDAO;
import br.com.sani.dao.ClienteProprietarioFisicaDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JPopupMenu;

import java.awt.Component;

import javax.swing.JMenuItem;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.border.TitledBorder;
import org.jdesktop.xswingx.JXSearchField;

public class frmConsultaClientesComprador extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JMenuItem smEditarRegistro;
	
	private int requisicao = 0; //guarda o numero da tela que chamou, zero siginifica que ninguem chamou
	private JXSearchField txtFiltro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new frmConsultaClientesComprador(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public frmConsultaClientesComprador(int requisicao){
		this.requisicao = requisicao;
		initComponents();
		buscar(); 
		setVisible(true);
	}


	public void initComponents() {
		SwingUtil.lookWindows(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmConsultaClientesComprador.class.getResource("/br/com/images/search.png")));
		setTitle("Consulta de Cliente Comprador");
		setBounds(100, 100, 690, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(9, 79, 645, 312);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() > 1){
					retornaRequisicao();
				}
			}
		});
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nome/Raz\u00E3o Social", "CPF/CNPJ", "Telefone", "Email"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(54);
		table.getColumnModel().getColumn(1).setPreferredWidth(197);
		table.getColumnModel().getColumn(2).setPreferredWidth(128);
		table.getColumnModel().getColumn(3).setPreferredWidth(105);
		table.getColumnModel().getColumn(4).setPreferredWidth(140);
		scrollPane.setViewportView(table);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		
		//FAZER A EDIÇÃO!!!
		
		smEditarRegistro = new JMenuItem("Editar Registro");
		smEditarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		smEditarRegistro.setIcon(new ImageIcon(frmConsultaClientesComprador.class.getResource("/br/com/images/edit-.png")));
		popupMenu.add(smEditarRegistro);
		
		JMenuItem mntmExcluirRegistro = new JMenuItem("Excluir Registro");
		mntmExcluirRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				excluir();
			}
		});
		mntmExcluirRegistro.setIcon(new ImageIcon(frmConsultaClientesComprador.class.getResource("/br/com/images/delete-.png")));
		popupMenu.add(mntmExcluirRegistro);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Filtro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(9, 11, 645, 57);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txtFiltro = new JXSearchField();
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				buscar();
			}
		});
		txtFiltro.setToolTipText("Digite o nome ou raz\u00E3o social");
		txtFiltro.setPrompt("Digite o nome ou raz\u00E3o social");
		txtFiltro.setBounds(95, 24, 540, 22);
		panel_1.add(txtFiltro);
		
		setLocationRelativeTo(null);		
	}
	
	//metodo que executa a pesquisa, dps vc add os filtros
	private void buscar(){
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();//pega o modelo da tabela
		dtm.setRowCount(0);//define que a primeira linha começa em 0,  se nao fizer ele deixa uma linha em branco
		try{
			List<ClienteComprador> listaClientes = new ClienteCompradorFisicaDAO().buscarPorNome(txtFiltro.getText());//executa a pesquisa
			
			//precisa percorrer a lista de resultados
			for(ClienteComprador item : listaClientes){
				//para cada item lido, chama o metodo que adiciona na tabela
				addTable(dtm, item);
				
				
			}
		}catch(ParseException e){
			e.printStackTrace();
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(this, "Erro ao executar Pesquisa!", "Erro Fatal.", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	
	//metodo que popula jTable
	private void addTable(DefaultTableModel dtm, ClienteComprador obj) throws ParseException{
		Object[] object = new Object[5];//quantidade de colunas da Jtable
		int i = 0;
		
		//popula o array de objetos, e como se cada posicao do array fosse 
		//uma coluna da tabela
		if(obj.getTpCliente().equals("PF")){
			object[i++] = obj.getCodCliComprador();
			object[i++] = obj.getClienteCompradorFisica().getNome();
			object[i++] = Mascara.setMaskCpfInTable(obj.getClienteCompradorFisica().getCpf());//col 3
			object[i++] = Mascara.setMaskTelefoneInTable(obj.getTelefone()); //col 4
			object[i++] = obj.getClienteCompradorFisica().getEmail(); // col 5
			
			//pronto, se for fisica ja ta populado
		}else{
			
			object[i++] = obj.getCodCliComprador();
			object[i++] = obj.getClienteCompradorJuridica().getRazaoSocial();
			object[i++] = Mascara.setMaskCnpjInTable(obj.getClienteCompradorJuridica().getCnpj()); // col 3 formatada!!!!
			object[i++] = Mascara.setMaskTelefoneInTable(obj.getTelefone()); //col 4 formatada!!!
			object[i++] = obj.getClienteCompradorJuridica().getEmail();
			
		}
		
		//agora com o objeto ja pode-se inserir na tabela
		
		dtm.addRow(object);//adiciona a linha na JTable
	}
	
	
	//metodo que joga o item para edicao
	private void editar() {
		int row = table.getSelectedRow();//pega linha selecionada
		
		try{
			//verifica se realmente tem alguma linha selecionada
			if(row != -1){
				int codigo = Integer.parseInt(String.valueOf((Object) table.getValueAt(row, 0)));
				
				ClienteComprador cliente = new ClienteCompradorFisicaDAO().buscarPorCodigo(codigo);
				
				if(cliente.getTpCliente().equals("PF")){
					new frmCadastroClienteCompradorFis(cliente.getClienteCompradorFisica());
				}else{
					new frmCadastroClienteCompradorJur(cliente.getClienteCompradorJuridica());
				}
				
				
			}	
		}catch(DAOException e){
			e.printStackTrace();
		}
	}
	
	private void excluir(){
		int row = table.getSelectedRow();
		try{
			if(row != -1){
				int id = Integer.parseInt(String.valueOf((Object) table.getValueAt(row, 0)));
				ClienteComprador cliente = new ClienteCompradorFisicaDAO().buscarPorCodigo(id);
				
				int question = JOptionPane.showConfirmDialog(this, "Deseja exluir o registro: " + id + " ?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(cliente != null && question == 0){
					if(cliente.getTpCliente().equals("PF")){
						new ClienteCompradorFisicaDAO().exluirClienteCompradorFisica(id);
					}else{
						new ClienteCompradorJuridicaDAO().exluirClienteCompradorJuridica(id);
					}
					JOptionPane.showMessageDialog(this, "Registro apagado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
					buscar();
				}
			}
		}catch (DAOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void retornaRequisicao(){
		int row = table.getSelectedRow();
		try{
			if(row != -1){
				ClienteCompradorFisicaDAO dao = new ClienteCompradorFisicaDAO();
				int codigo = Integer.parseInt(String.valueOf((Object)table.getValueAt(row, 0)));
				Object[] retorno = new Object[4];
				
				//requisicao 1 = tela Gerar Proposta
				if(this.requisicao == 1){
					ClienteComprador cliente = dao.buscarPorCodigo(codigo);
					
					if(cliente.getTpCliente().equals("PF")){
						retorno[0] = cliente.getCodCliComprador();
						retorno[1] = cliente.getClienteCompradorFisica().getNome();
						
						frmGerarProposta.setComprador(retorno);
						dispose();
					}else{
						retorno[0] = cliente.getCodCliComprador();
						retorno[1] = cliente.getClienteCompradorJuridica().getRazaoSocial();
						
						frmGerarProposta.setComprador(retorno);
						dispose();
 					}
					
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
