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
import br.com.sani.exception.DAOException;
import br.com.sani.util.FormatarNumero;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;

public class frmConsultaClienteProprietario extends JFrame implements MouseListener {

	private JPanel contentPane;
	private JTextField txtFieldConsultaNomeClienteComprador;
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
		setTitle("Escolha o Propriet\u00E1rio");
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
		
		txtFieldConsultaNomeClienteComprador = new JTextField();
		txtFieldConsultaNomeClienteComprador.setBounds(57, 33, 388, 20);
		txtFieldConsultaNomeClienteComprador.setColumns(10);
		panel.add(txtFieldConsultaNomeClienteComprador);
		
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
		
		JLabel lblRemove = new JLabel("");
		lblRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Remove o Usuario Selecionado
			}
		});
		lblRemove.setIcon(new ImageIcon(frmConsultaClienteProprietario.class.getResource("/br/com/images/delete-.png")));
		lblRemove.setBounds(583, 361, 25, 25);
		panel.add(lblRemove);
		
		JLabel lblEdit = new JLabel("");
		lblEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Edita o Cliente Selecionado
				JOptionPane.showMessageDialog(null, "Teste");
			}
		});
		lblEdit.setIcon(new ImageIcon(frmConsultaClienteProprietario.class.getResource("/br/com/images/edit-.png")));
		lblEdit.setBounds(618, 361, 25, 25);
		panel.add(lblEdit);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setToolTipText("Pesquisar");
		lblSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Faz a Busca no BD
				buscar();
			}
		});
		lblSearch.setIcon(new ImageIcon(frmConsultaClienteProprietario.class.getResource("/br/com/images/search-ico.png")));
		lblSearch.setBounds(618, 33, 25, 25);
		panel.add(lblSearch);
		
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
			object[i++] = FormatarNumero.formatNumero(4, obj.getClienteProprietarioJuridica().getClienteProprietario().getCodCliProprietario());
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
						object[0] = cli.getClienteProprietarioFisica().getCodCliProprietario();
						object[1] = cli.getClienteProprietarioFisica().getNome();
						object[2] = Mascara.setMaskCpfInTable(cli.getClienteProprietarioFisica().getCpf());
						object[4] = cli.getClienteProprietarioFisica().getEmail();
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
	
	public void fechar(){
		this.dispose();
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
}
