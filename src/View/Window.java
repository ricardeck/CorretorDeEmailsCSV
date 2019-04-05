package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Application.Program;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JLabel lblAdicionarEmail;
	private JButton btnCriarCsv;
	private JButton btnAddDomnio;
	private JTextField txtDominio;
	private JLabel lblAdicionarDomnio;
	private Program program = new Program();
	private JTextField txtcorrecoes;
	private JLabel lblQtd;
	private JLabel label;
	private JTextField textPercente;
	private JButton btnFileEmail;
	private JButton btnFileDomnio;
	private JTextField textFileEmail;
	private JTextField textFileDominio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public Window() throws IOException {
		setTitle("Corretor de Email");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 355, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);

		JButton btnAdd = new JButton("Add Email");
		btnAdd.setToolTipText("Adiciona o email inserido");

		btnAdd.setBounds(10, 11, 108, 23);
		layeredPane.add(btnAdd);

		txtEmail = new JTextField();
		txtEmail.setBounds(128, 13, 193, 20);
		layeredPane.add(txtEmail);
		txtEmail.setColumns(10);

		lblAdicionarEmail = new JLabel("Adicionar novo email");
		lblAdicionarEmail.setBounds(128, 2, 151, 14);
		layeredPane.add(lblAdicionarEmail);

		btnCriarCsv = new JButton("Gerar CSV");
		btnCriarCsv.setToolTipText("Gera o arquivo CSV");
		btnCriarCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					program.carregaCsv();
					program.gravaCsv();
					JOptionPane.showMessageDialog(null, "Arquivo gerado");
					String correcoes = String.format("%.0f", program.getNumeroCorrecoes());
					txtcorrecoes.setText(correcoes);
					String percentual = String.format("%.2f", program.getPercenteCorrecoes());
					textPercente.setText(percentual + "%");

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCriarCsv.setBounds(10, 110, 108, 23);
		layeredPane.add(btnCriarCsv);

		btnAddDomnio = new JButton("Add Domínio");
		btnAddDomnio.setToolTipText("Adiciona o dominio inserido");
		btnAddDomnio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!txtDominio.getText().isEmpty()) {
						program.addDominio(txtDominio.getText());
						JOptionPane.showMessageDialog(null, "Dominio adicionado");
						txtDominio.setText("");
					} else
						JOptionPane.showMessageDialog(null, "Campo domínio em branco");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddDomnio.setBounds(10, 57, 108, 23);
		layeredPane.add(btnAddDomnio);

		txtDominio = new JTextField();
		txtDominio.setBounds(128, 59, 193, 20);
		layeredPane.add(txtDominio);
		txtDominio.setColumns(10);

		lblAdicionarDomnio = new JLabel("Adicionar novo domínio");
		lblAdicionarDomnio.setBounds(128, 45, 151, 14);
		layeredPane.add(lblAdicionarDomnio);

		txtcorrecoes = new JTextField();
		txtcorrecoes.setForeground(Color.RED);
		txtcorrecoes.setToolTipText("Corre\u00E7\u00F5es efetuadas");
		txtcorrecoes.setHorizontalAlignment(SwingConstants.CENTER);
		txtcorrecoes.setText("0");
		txtcorrecoes.setBounds(128, 110, 60, 23);
		layeredPane.add(txtcorrecoes);
		txtcorrecoes.setColumns(10);

		JLabel lblCorrees = new JLabel("Corre\u00E7\u00F5es");
		lblCorrees.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorrees.setBounds(125, 85, 193, 14);
		layeredPane.add(lblCorrees);

		lblQtd = new JLabel("Qtd");
		lblQtd.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtd.setBounds(143, 91, 29, 14);
		layeredPane.add(lblQtd);

		label = new JLabel("%");
		label.setBounds(279, 91, 19, 14);
		layeredPane.add(label);

		textPercente = new JTextField();
		textPercente.setForeground(Color.RED);
		textPercente.setHorizontalAlignment(SwingConstants.CENTER);
		textPercente.setText("0%");
		textPercente.setBounds(258, 110, 60, 23);
		layeredPane.add(textPercente);
		textPercente.setColumns(10);

		btnFileEmail = new JButton("File Email");
		btnFileEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser abrir = new JFileChooser();
				int retorno = abrir.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION) {
					program.setLocalArquivoEmail(abrir.getSelectedFile().getAbsolutePath());
					JOptionPane.showMessageDialog(null, program.getLocalArquivoEmail());
					textFileEmail.setText(program.getLocalArquivoEmail());
				}
			}
		});
		btnFileEmail.setBounds(10, 144, 108, 23);
		layeredPane.add(btnFileEmail);

		btnFileDomnio = new JButton("File Dom\u00EDnio");
		btnFileDomnio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser abrir = new JFileChooser();
				int retorno = abrir.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION) {
					program.setLocalArquivoDominio(abrir.getSelectedFile().getAbsolutePath());
					JOptionPane.showMessageDialog(null, program.getLocalArquivoDominio());
					textFileDominio.setText(program.getLocalArquivoDominio());
				}

			}
		});
		btnFileDomnio.setBounds(10, 178, 108, 23);
		layeredPane.add(btnFileDomnio);

		textFileEmail = new JTextField();
		textFileEmail.setBounds(128, 146, 193, 20);
		layeredPane.add(textFileEmail);
		textFileEmail.setColumns(10);
		textFileEmail.setText(program.getLocalArquivoEmail());

		textFileDominio = new JTextField();
		textFileDominio.setBounds(128, 180, 193, 20);
		layeredPane.add(textFileDominio);
		textFileDominio.setColumns(10);
		textFileDominio.setText(program.getLocalArquivoDominio());
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (!txtEmail.getText().isEmpty() && txtEmail.getText().indexOf("@") < -1) {
						program.addEmail(txtEmail.getText());
						JOptionPane.showMessageDialog(null, "Email adicionado");
						txtEmail.setText("");
					} else if (txtEmail.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Campo email em branco");
					else if (txtEmail.getText().indexOf("@") == -1) {
						JOptionPane.showMessageDialog(null, "Campo email Inválido");
					}
					else {
						program.addEmail(txtEmail.getText());
						JOptionPane.showMessageDialog(null, "Email adicionado");
						txtEmail.setText("");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}
}
