package br.com.sani.util;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class CloseListener extends WindowAdapter {

	private static final Logger LOG = Logger.getLogger(CloseListener.class);
	
	private boolean silentMode;
	private String message;
	private String title;
	private boolean disposeOnClose;

	public CloseListener(boolean silentMode, String message, String title) {
		this(silentMode, message, title, false);
	}
	
	/**
	 * @param silentMode
	 * @param message
	 * @param title
	 * @param disposeOnClose
	 *            true para DISPOSE_ON_CLOSE, false para EXIT_ON_CLOSE
	 */
	public CloseListener(boolean silentMode, String message, String title, boolean disposeOnClose) {
		this.silentMode = silentMode;
		this.message = message;
		this.title = title;
		this.disposeOnClose = disposeOnClose;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Component component = e.getComponent();
		
		if (component instanceof JFrame) {
			JFrame frame = (JFrame) component;
			if (!confirmWindowClosing(component)) {
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				return;
			}
			if (disposeOnClose) {
				LOG.info("Descarregando tela " + frame.getClass().getName());
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			} else {
				LOG.info("Fechando tela " + frame.getClass().getName());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		} else if (component instanceof JDialog) {
			JDialog frame = (JDialog) component;
			if (!confirmWindowClosing(component)) {
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				return;
			}
			if (disposeOnClose) {
				LOG.info("Descarregando tela " + frame.getClass().getName());
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			} else {
				LOG.info("Fechando tela " + frame.getClass().getName());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		}
	}

	private boolean confirmWindowClosing(Component frame) {
		if (silentMode) {
			return true;
		}
		if (message == null) {
			return true;
		}

		int result = JOptionPane.showConfirmDialog(frame, message, title,
				JOptionPane.YES_NO_OPTION);

		return (result == JOptionPane.YES_OPTION);

	}
}
