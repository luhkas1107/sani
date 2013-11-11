package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.sani.bean.ClienteProprietario;
import br.com.sani.bean.ClienteProprietarioFisica;
import br.com.sani.bean.ClienteProprietarioJuridica;
import br.com.sani.dao.ClienteProprietarioDAO;
import br.com.sani.dao.ClienteProprietarioFisicaDAO;
import br.com.sani.dao.ClienteProprietarioJuridicaDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.util.FormatarNumero;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;

import javax.swing.JPopupMenu;

import java.awt.Component;

import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;

public class frmConsultaClienteProprietario extends JFrame implements MouseListener {

	private JPanel contentPane;
	private JTable table;

	private int requisicao = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new frmConsultaClienteProprietario(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public frmConsultaClienteProprietario(int requisicao){
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmConsultaClienteProprietario.class.getResource("/br/com/images/search.png")));
		setTitle("Consulta de Cliente Propriet\u00E1rio");
		setBounds(100, 100, 690, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(9, 102, 645, 289);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nome/Raz\u00E3o Social", "CPF/CNPJ", "Telefone"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(102);
		table.getColumnModel().getColumn(1).setPreferredWidth(255);
		table.getColumnModel().getColumn(2).setPreferredWidth(155);
		table.getColumnModel().getColumn(3).setPreferredWidth(111);
		scrollPane.setViewportView(table);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mntmEditarCadastro = new JMenuItem("Editar Registro");
		mntmEditarCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mntmEditarCadastro.setIcon(new ImageIcon(frmConsultaClienteProprietario.class.getResource("/br/com/images/edit-.png")));
		popupMenu.add(mntmEditarCadastro);
		
		JMenuItem mntmExcluirCadastro = new JMenuItem("Excluir Registro");
		mntmExcluirCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				excluir();
			}
		});
		mntmExcluirCadastro.setIcon(new ImageIcon(frmConsultaClienteProprietario.class.getResource("/br/com/images/delete-.png")));
		popupMenu.add(mntmExcluirCadastro);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Filtro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(9, 11, 645, 81);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		setLocationRelativeTo(null);
	}
	
	private void buscar(){
		try{
			DefaultTableModel dtm =(DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			
			List<ClienteProprietario> lista = new ClienteProprietarioDAO().buscarTodos();
			if(lista != null){
				for(ClienteProprietario obj : lista){
					addTable(dtm, obj);
				}
			}
		}catch (DAOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void addTable(DefaultTableModel dtm, ClienteProprietario obj) throws ParseException{
		Object[] object = new Object[4];
		int i = 0;
		
		if(obj.getTpCliente().equals("PF")){
			object[i++] = FormatarNumero.formatNumero(4, obj.getClienteProprietarioFisica().getCodCliProprietario().getCodCliProprietario());
			object[i++] = obj.getClienteProprietarioFisica().getNome();
			object[i++] = Mascara.setMaskCpfInTable(obj.getClienteProprietarioFisica().getCpf());
			object[i++] = Mascara.setMaskTelefoneInTable(obj.getClienteProprietarioFisica().getCodCliProprietario().getTelefone());
		}else{
			object[i++] = FormatarNumero.formatNumero(4, obj.getCodCliProprietario());
			object[i++] = obj.getClienteProprietarioJuridica().getRazaoSocial();
			object[i++] = Mascara.setMaskCnpjInTable(obj.getClienteProprietarioJuridica().getCnpj());
			object[i++] = Mascara.setMaskTelefoneInTable(obj.getTelefone());
		}
		
		dtm.addRow(object);
	}
	
	public void retornarRequisicao(){
		int row = table.getSelectedRow();
		try{
			if(row != -1){
				int id = Integer.parseInt((String) table.getValueAt(row, 0));
				if(this.requisicao == 1){
					ClienteProprietario cli = new ClienteProprietarioDAO().buscarPorId(id);
					Object[] object = new Object[4];
					
					if(cli.getTpCliente().equals("PF")){
						object[0] = cli.getCodCliProprietario();
						object[1] = cli.getClienteProprietarioFisica().getNome();
						object[2] = Mascara.setMaskCpfInTable(cli.getClienteProprietarioFisica().getCpf());
						object[3] = cli.getClienteProprietarioFisica().getEmail();
					}else{
						object[0] = cli.getCodCliProprietario();
						object[1] = cli.getClienteProprietarioJuridica().getRazaoSocial();
						object[2] = Mascara.setMaskCnpjInTable(cli.getClienteProprietarioJuridica().getCnpj());
						object[3] = "";
					}
					
					frmCadastroPropriedade.setProprietario(object);
					dispose();
				}
			}
		}catch (DAOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void excluir(){
		int row = table.getSelectedRow();
		try{
			if(row != -1){
				int id = Integer.parseInt((String) table.getValueAt(row, 0));
				ClienteProprietario cliente = new ClienteProprietarioDAO().buscarPorId(id);
				
				int question = JOptionPane.showConfirmDialog(this, "Deseja exluir o registro: " + id + " ?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(cliente != null && question == 0){
					if(cliente.getTpCliente().equals("PF")){
						new ClienteProprietarioFisicaDAO().exluirClienteProprietarioFisica(id);
					}else{
						new ClienteProprietarioJuridicaDAO().exluirClienteProprietarioJuridica(id);
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

	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getSource() == table){
			if(event.getClickCount() > 1){
				retornarRequisicao();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
