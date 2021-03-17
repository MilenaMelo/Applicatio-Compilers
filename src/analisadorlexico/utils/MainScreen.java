package analisadorlexico.utils;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;


import analisadorlexico.analyser.Analisador;

public class MainScreen extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
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
	public MainScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 899, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JLabel lblInsertPath = new JLabel("Insira o caminho do diretório a ser analisado.");
		
		JLabel lblAnalisadorLexico = new JLabel("Analisador Léxico");
		lblAnalisadorLexico.setForeground(SystemColor.desktop);
		lblAnalisadorLexico.setFont(new Font("Dialog", Font.BOLD, 30));

		textField = new JTextField();
		textField.setColumns(10);
		textField.setText("");
		
		final Analisador analisador = new Analisador();

		final JLabel validatedCode = new JLabel("Código validado com sucesso.");
		validatedCode.setForeground(UIManager.getColor("OptionPane.questionDialog.border.background"));
		validatedCode.setVisible(false);
		
		final JLabel errorCode = new JLabel("Código com erros. Veja o log em ");
		errorCode.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		errorCode.setVisible(false);
		
		JButton btnSetect = new JButton("Selecionar");
		btnSetect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    JFileChooser openDirectory = new JFileChooser("input/" + "");  
			    int pathReturned = openDirectory.showOpenDialog(null);
			    String path = "";
			    validatedCode.setVisible(false);
			    errorCode.setVisible(false);
			               if (pathReturned == JFileChooser.APPROVE_OPTION){
			            	   	 path = openDirectory.getSelectedFile().getAbsolutePath();  
			                     textField.setText(path);  
			               }
			} 
		});
 		
		JButton btnParsing = new JButton("Analisar!");
		btnParsing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Caminho do arquivo não encontrado");
				else{
					try {
						analisador.parsing(textField.getText());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Análise realizada");
					System.exit(0);
				}
				
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAnalisadorLexico)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(validatedCode)
											.addGap(198))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblInsertPath)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textField, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)))
									.addGap(18)
									.addComponent(btnSetect))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(341)
							.addComponent(btnParsing))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(325)
							.addComponent(errorCode)))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblAnalisadorLexico)
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInsertPath)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSetect))
					.addGap(29)
					.addComponent(btnParsing)
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addComponent(validatedCode)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(errorCode)
					.addGap(22))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
