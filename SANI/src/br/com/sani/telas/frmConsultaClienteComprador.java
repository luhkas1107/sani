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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import br.com.sani.bean.ClienteCompradorFisica;
import br.com.sani.bean.ClienteCompradorJuridica;
import br.com.sani.dao.ClienteCompradorFisicaDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.text.ParseException;
import java.util.List;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;

public class frmConsultaClienteComprador extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldConsultaNomeClienteComprador;
	private JTextField txtFieldConsultaEnderecoClienteComprador;
	private JTable table;
	private JMenuItem smEditarRegistro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//sempre que a tela for carregada ele ja pesquisa
					new frmConsultaClienteComprador();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public frmConsultaClienteComprador(){
		initComponents();
		buscar(); // tem que ser antes do setVisible, vamos testar
		setVisible(true);
	}


	public void initComponents() {
		SwingUtil.lookWindows(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmConsultaClienteComprador.class.getResource("/br/com/images/search.png")));
		setTitle("Consulta de Cliente Comprador");
		setBounds(100, 100, 690, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(9, 89, 645, 2);
		panel.add(separator);
		
		JLabel label = new JLabel("Filtro:");
		label.setBounds(10, 11, 46, 14);
		panel.add(label);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 36, 68, 14);
		panel.add(lblNome);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(325, 36, 78, 14);
		panel.add(lblEndereo);
		
		txtFieldConsultaNomeClienteComprador = new JTextField();
		txtFieldConsultaNomeClienteComprador.setBounds(57, 33, 259, 20);
		txtFieldConsultaNomeClienteComprador.setColumns(10);
		panel.add(txtFieldConsultaNomeClienteComprador);
		
		txtFieldConsultaEnderecoClienteComprador = new JTextField();
		txtFieldConsultaEnderecoClienteComprador.setBounds(395, 33, 259, 20);
		txtFieldConsultaEnderecoClienteComprador.setColumns(10);
		panel.add(txtFieldConsultaEnderecoClienteComprador);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 61, 46, 14);
		panel.add(lblEstado);
		
		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBoxEstado.setBounds(57, 58, 45, 20);
		panel.add(comboBoxEstado);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnCancelar.setBounds(10, 362, 89, 23);
		panel.add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 102, 623, 254);
		panel.add(scrollPane);
		
		table = new JTable();
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
		popupMenu.add(smEditarRegistro);
		
		JLabel lblRemove = new JLabel("");
		lblRemove.setToolTipText("Apagar Cliente");
		lblRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Remove o Usuario Selecionado
				
			}
		});
		lblRemove.setIcon(new ImageIcon(frmConsultaClienteComprador.class.getResource("/br/com/images/delete-.png")));
		lblRemove.setBounds(583, 361, 25, 25);
		panel.add(lblRemove);
		
		JLabel lblEdit = new JLabel("");
		lblEdit.setToolTipText("Editar Cliente");
		lblEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Edita o Cliente Selecionado
				JOptionPane.showMessageDialog(null, "Teste");
			}
		});
		lblEdit.setIcon(new ImageIcon(frmConsultaClienteComprador.class.getResource("/br/com/images/edit-.png")));
		lblEdit.setBounds(618, 361, 25, 25);
		panel.add(lblEdit);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setToolTipText("Pesquisar Clientes");
		lblSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Faz a Busca no BD
				buscar();
			}
		});
		lblSearch.setIcon(new ImageIcon(frmConsultaClienteComprador.class.getResource("/br/com/images/search-ico.png")));
		lblSearch.setBounds(618, 56, 25, 25);
		panel.add(lblSearch);
		
		setLocationRelativeTo(null);		
	}
	
	public void fechar(){
		this.dispose();
	}
	
	//metodo que executa a pesquisa, dps vc add os filtros
	private void buscar(){
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();//pega o modelo da tabela
		dtm.setRowCount(0);//define que a primeira linha começa em 0,  se nao fizer ele deixa uma linha em branco
		try{
			List<Object> listaClientes = new ClienteCompradorFisicaDAO().buscarTodos();//executa a pesquisa
			
			//precisa percorrer a lista de resultados
			for(Object item : listaClientes){
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
	private void addTable(DefaultTableModel dtm, Object obj) throws ParseException{
		Object[] object = new Object[5];//quantidade de colunas da Jtable
		int i = 0;
		
		//popula o array de objetos, e como se cada posicao do array fosse 
		//uma coluna da tabela
		if(obj instanceof ClienteCompradorFisica){
			ClienteCompradorFisica cf = (ClienteCompradorFisica) obj;
			
			object[i++] = cf.getClienteComprador().getCodCliComprador();//coluna 1
			object[i++] = cf.getNome(); //coluna 2
			object[i++] = Mascara.setMaskCpfInTable(cf.getCpf());//col 3
			object[i++] = Mascara.setMaskTelefoneInTable(cf.getClienteComprador().getTelefone()); //col 4
			object[i++] = cf.getEmail(); // col 5
			
			//pronto, se for fisica ja ta populado
		}else if(obj instanceof ClienteCompradorJuridica){
			ClienteCompradorJuridica cj = (ClienteCompradorJuridica) obj;
			
			object[i++] = cj.getClienteComprador().getCodCliComprador(); //col 1
			object[i++] = cj.getRazaoSocial(); // col 2
			object[i++] = Mascara.setMaskCnpjInTable(cj.getCnpj()); // col 3 formatada!!!!
			object[i++] = Mascara.setMaskTelefoneInTable(cj.getClienteComprador().getTelefone()); //col 4 formatada!!!
			object[i++] = cj.getEmail();
			
			//pronto, se for juridica ja populado
		}
		
		//agora com o objeto ja pode-se inserir na tabela
		
		dtm.addRow(object);//adiciona a linha na JTable
	}
	
	
	//metodo que joga o item para edicao
	private void editar() {
		int row = table.getSelectedRow();//pega linha selecionada
		
		//verifica se realmente tem alguma linha selecionada
		if(row != -1){
			int codigo = Integer.parseInt((String) table.getValueAt(row, 0));//retorna a id da primeira coluna
		}		
	}
	
	private void deletar(){
		//deleta cliente selecionado
		int row = table.getSelectedRow();//pega linha selecionada
		
		//verifica se realmente tem alguma linha selecionada
		if(row != -1){
			int codigo = Integer.parseInt((String) table.getValueAt(row, 0));//retorna a id da primeira coluna
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
