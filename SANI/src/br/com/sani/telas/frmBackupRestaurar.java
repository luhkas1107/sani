package br.com.sani.telas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.swing.ImageIcon;

public class frmBackupRestaurar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCaminho;

	
	Date dataHoje;
	SimpleDateFormat formataData;	
	File arquivo, dir; 
	String caminho, caminhoImagem;
	String nomeArquivo = "";
	String extensaoArquivo =".bak";
	
	String arquivoFinal =null;
	
	public static void main(String[] args) {
		try {
			frmBackupRestaurar dialog = new frmBackupRestaurar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmBackupRestaurar() {
		SwingUtil.lookWindows(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmBackupRestaurar.class.getResource("/br/com/images/restore.png")));
		setTitle("Restaurar Backup");
		setBounds(100, 100, 487, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);

		JButton btnSalvar = new JButton("Restaurar");
		btnSalvar.setIcon(new ImageIcon(frmBackupRestaurar.class.getResource("/br/com/images/restore.png")));
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
							resultado=BackupDAO.backup_restore(caminho);
							JOptionPane.showMessageDialog(null, "Backup restaurado com suscesso!");
//	        				
						} catch (Exception e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e.getMessage());
							e.printStackTrace();
						}
        					
        					
        					
        					
//        				}
        				
//        		}else{
        			
//        			try {
//        				boolean resultado;
//						resultado=BackupDao.backup_novo(caminho);
//						JOptionPane.showMessageDialog(null, "Backup realizado com suscesso!");
//    					
//					} catch (exception e) {
//						// TODO Auto-generated catch block
//						JOptionPane.showMessageDialog(null, e.getMessage());
//						e.printStackTrace();
//					}
//    					
////        			JOptionPane.showMessageDialog(null, "Backup realizado com suscesso!");
    			
//        		}

			
			}
		});
		btnSalvar.setBounds(334, 139, 120, 32);
		contentPanel.add(btnSalvar);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setIcon(new ImageIcon(frmBackupRestaurar.class.getResource("/br/com/images/search-ico.png")));
		btnProcurar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser arquivo = new JFileChooser();
				arquivoFinal =null;
				arquivo.setVisible(true);
				
				int result = arquivo.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION){
            		arquivoFinal = arquivo.getSelectedFile().toString();//.concat(".bak");
            		txtCaminho.setText(arquivoFinal);
           		
            		File file = new File(arquivoFinal);
            		System.out.println(arquivoFinal);
            		System.out.println(file);
            		
            		/**if(file.exists()){
            			Object[] options = {"Sim", "Não"};
            				
            				int opcao = JOptionPane.showOptionDialog(null, "Este arquivo já existe. Quer sobreescrever este arquivo?", "Atenção!!!",
            						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            				if(opcao==JOptionPane.YES_OPTION){
            					caminho = Class.class.getResource("/").toString();	
            	            	caminho = arquivoFinal.replace("/","\\\\");
            	            	caminho = arquivoFinal.replace("file:\\\\","");
            	            	caminho = arquivoFinal.replace("C:","c:");
            	            	JOptionPane.showMessageDialog(null, caminho);
            	            	JOptionPane.showMessageDialog(null, arquivoFinal);
                    			
            	            	
         						try {
         							boolean resultado;
        							resultado=BackupDao.backup_novo(caminho);
        							if(resultado==true){
        							JOptionPane.showMessageDialog(null, "Backup realizado com suscesso!");
        							}
        						} catch (exception e) {
        							// TODO Auto-generated catch block
        							JOptionPane.showMessageDialog(null, e.getMessage());
        							e.printStackTrace();
        						}
                					
            					
            					
            				}
            				
            		}else{
            			
            			caminho = Class.class.getResource("/").toString();	
    	            	caminho = arquivoFinal.replace("/","\\\\");
    	            	caminho = arquivoFinal.replace("file:\\\\","");
    	            	caminho = arquivoFinal.replace("C:","c:");
    	            	JOptionPane.showMessageDialog(null, caminho);
    	            	JOptionPane.showMessageDialog(null, arquivoFinal);
            			
            			
 						try {
 							boolean resultado;
							resultado=BackupDao.backup_novo(caminho);
							if(resultado==true){
							JOptionPane.showMessageDialog(null, "Backup realizado com suscesso!");
							}
						} catch (exception e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e.getMessage());
							e.printStackTrace();
						}
        					
            		}**/

            	}
			
			}
		});
		btnProcurar.setBounds(328, 84, 133, 32);
		contentPanel.add(btnProcurar);
		 
		txtCaminho = new JTextField();
		txtCaminho.setEditable(false);
		txtCaminho.setBounds(58, 90, 266, 20);
		//txtCaminho.setText("c:\\SANI\\Backup\\");
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
		btnVoltar.setIcon(new ImageIcon(frmBackupRestaurar.class.getResource("/br/com/images/delete-.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			
			}
		});
		btnVoltar.setBounds(10, 139, 104, 32);
		contentPanel.add(btnVoltar);
	}
}
