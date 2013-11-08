package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.sani.dao.BackupDAO;
import br.com.sani.util.SwingUtil;

public class frmBackupCriar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCaminho;

	
	Date dataHoje;
	SimpleDateFormat formataData;	
	File arquivo, dir; 
	String caminho, caminhoImagem;
	String nomeArquivo = "";
	String extensaoArquivo = ".bak";
	JButton btnProcurar = new JButton();
	
	String arquivoFinal = null;
	
	public static void main(String[] args) {
		try {
			frmBackupCriar dialog = new frmBackupCriar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmBackupCriar() {
		SwingUtil.lookWindows(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmBackupCriar.class.getResource("/br/com/images/backup.png")));
		setTitle("Novo Backup");
		setBounds(100, 100, 487, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(frmBackupCriar.class.getResource("/br/com/images/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = txtCaminho.getText();
				caminho = Class.class.getResource("/").toString();	
            	caminho = text.replace("/","\\\\");
            	caminho = text.replace("file:\\\\","");
				caminho = text.replace("C:","c:");
				

        		System.out.println(arquivoFinal);
        		System.out.println(caminho);
        					
 						try {
 							boolean resultado;
							resultado=BackupDAO.backup_novo(caminho);
							JOptionPane.showMessageDialog(null, "Backup realizado com suscesso!");
//	        				
						} catch (Exception e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e.getMessage());
							e.printStackTrace();
						}
			}
		});
		btnSalvar.setBounds(342, 139, 104, 32);
		contentPanel.add(btnSalvar);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setIcon(new ImageIcon(frmBackupCriar.class.getResource("/br/com/images/search-ico.png")));
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backup();
			}
		});
		btnProcurar.setBounds(328, 84, 133, 32);
		contentPanel.add(btnProcurar);
		Date hora = new Date();
		String formato = "ddMMyyHHmmSS";
		
		DateFormat dateformatMedium = new SimpleDateFormat(formato);
		
		String agora = ("backupSANI-" +dateformatMedium.format(hora));
		 
		txtCaminho = new JTextField();
		txtCaminho.setEditable(false);
		txtCaminho.setBounds(58, 90, 266, 20);
		//txtCaminho.setText("C:\\SANI\\Backup\\"+agora+".bak");
		contentPanel.add(txtCaminho);
		
		txtCaminho.setColumns(10);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setBounds(10, 93, 40, 14);
		contentPanel.add(lblDestino);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 54, 471, 2);
		contentPanel.add(separator);
		
		JLabel lblBackup = new JLabel("Backup");
		lblBackup.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackup.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBackup.setBounds(0, 11, 440, 32);
		contentPanel.add(lblBackup);
		
		JButton btnVoltar = new JButton("Cancelar");
		btnVoltar.setIcon(new ImageIcon(frmBackupCriar.class.getResource("/br/com/images/delete-.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			
			}
		});
		btnVoltar.setBounds(10, 139, 104, 32);
		contentPanel.add(btnVoltar);
	}
	
	public void backup(){
		
		JFileChooser arquivo = new JFileChooser();
		arquivoFinal = null;
		arquivo.setVisible(true);
					
		int result = arquivo.showSaveDialog(null);
		            	
    	if(result == JFileChooser.APPROVE_OPTION){
    		arquivoFinal = arquivo.getSelectedFile().toString().concat(".bak");
    		txtCaminho.setText(arquivoFinal);
    	            		
    		File file = new File(arquivoFinal);
    		System.out.println(arquivoFinal);
    		System.out.println(file);
    	
    	}
	}
	
}
