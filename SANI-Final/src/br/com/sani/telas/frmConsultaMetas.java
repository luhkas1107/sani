package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.sani.bean.Metas;
import br.com.sani.dao.MetasDAO;
import br.com.sani.exception.DAOException;
import br.com.sani.exception.EntradaUsuarioException;
import br.com.sani.util.DbUtil;
import br.com.sani.util.Mascara;
import br.com.sani.util.SwingUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmConsultaMetas extends JFrame {

	private JPanel contentPane;
	//private JTextField txtFieldConsultaDataInicioMetas;
	//private JTextField txtFieldConsultaDataFinalMetas;
	private JTable table;
	
	private JFormattedTextField ftFieldConsultaDataInicioMetas;
	private JFormattedTextField ftFieldConsultaDataFinalMetas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmConsultaMetas frame = new frmConsultaMetas();
					frame.setVisible(true);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Throwable 
	 */
	public frmConsultaMetas() throws Throwable {
		SwingUtil.lookWindows(this);
		setTitle("Consulta Metas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmConsultaMetas.class.getResource("/br/com/images/home_badge.png")));
		setBounds(100, 100, 690, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 75, 645, 2);
		panel.add(separator);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setBounds(10, 11, 46, 14);
		panel.add(lblFiltro);
		
		JLabel lblDataInicioMetas = new JLabel("Data de In\u00EDcio:");
		lblDataInicioMetas.setBounds(10, 36, 89, 14);
		panel.add(lblDataInicioMetas);
		
		JLabel lblDataFinalMetas = new JLabel("Data Final:");
		lblDataFinalMetas.setBounds(208, 36, 78, 14);
		panel.add(lblDataFinalMetas);
		
		JButton button_1 = new JButton("Cancelar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		button_1.setBounds(10, 368, 89, 23);
		panel.add(button_1);
		
		ftFieldConsultaDataInicioMetas = new JFormattedTextField(Mascara.setMaskDateInTf(ftFieldConsultaDataInicioMetas));
		ftFieldConsultaDataInicioMetas.setBounds(99, 33, 86, 20);
		panel.add(ftFieldConsultaDataInicioMetas);
		ftFieldConsultaDataInicioMetas.setColumns(10);
		
		ftFieldConsultaDataFinalMetas = new JFormattedTextField(Mascara.setMaskDateInTf(ftFieldConsultaDataFinalMetas));
		ftFieldConsultaDataFinalMetas.setColumns(10);
		ftFieldConsultaDataFinalMetas.setBounds(275, 33, 86, 20);
		panel.add(ftFieldConsultaDataFinalMetas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 644, 242);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data de In\u00EDcio", "Data Final", "Descri\u00E7\u00E3o"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(79);
		table.getColumnModel().getColumn(1).setPreferredWidth(81);
		table.getColumnModel().getColumn(2).setPreferredWidth(460);
		scrollPane.setViewportView(table);
		
		JLabel lblEdit = new JLabel("");
		lblEdit.setToolTipText("Editar Meta");
		lblEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEdit.setIcon(new ImageIcon(frmConsultaMetas.class.getResource("/br/com/images/edit-.png")));
		lblEdit.setBounds(630, 366, 25, 25);
		panel.add(lblEdit);
		
		JLabel lblDelete = new JLabel("");
		lblDelete.setToolTipText("Apagar Meta");
		lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDelete.setIcon(new ImageIcon(frmConsultaMetas.class.getResource("/br/com/images/delete-.png")));
		lblDelete.setBounds(592, 366, 25, 25);
		panel.add(lblDelete);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					buscarMeta();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		lblSearch.setToolTipText("Pesquisar Metas");
		lblSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSearch.setIcon(new ImageIcon(frmConsultaMetas.class.getResource("/br/com/images/search-ico.png")));
		lblSearch.setBounds(610, 33, 25, 25);
		panel.add(lblSearch);
		
		setLocationRelativeTo(null);
	}

	public void fechar() {
		this.dispose();
	}
	
	private Metas getBean(ResultSet result) throws SQLException, DAOException{
		Metas metas = new Metas();
		
		metas.setIdMeta(result.getInt("idMeta"));
		metas.setDescrMeta(result.getString("descrMeta"));
		metas.setDataInicio(DbUtil.getJavaDate(result, "dataInicio"));
		metas.setDataFim(DbUtil.getJavaDate(result, "dataFinal"));
		metas.setStMeta(result.getString("stMeta"));
		
		return metas;
	}
	
	private void buscarMeta() throws SQLException{
		try{
			Metas m = getBean(null);
			new MetasDAO().buscarTodos();
			//limpaFormulario();
			JOptionPane.showMessageDialog(this, "Busca efetuada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
		}catch(DAOException e){
			e.printStackTrace();
		}
	}	
}
