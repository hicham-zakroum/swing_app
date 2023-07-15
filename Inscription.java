
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.event.*;
import java.sql.*;

public class Inscription extends JFrame {

	private final JTextField textField,textField_1,textField_3,textField_4,textField_5;
	private final JPasswordField textField_2;
	private final JPanel contentPane;
	Connection con;
	PreparedStatement prepared;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new Inscription().setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Inscription() {
		setTitle("Register page");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(13, 211, 187));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		con = ConnectionBD.Connection_Mysql();

		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setForeground(new Color(0, 0, 0));
		editorPane.setFont(new Font("Tahoma", Font.BOLD, 25));
		editorPane.setBackground(new Color(255, 69, 0));
		editorPane.setText("  INSCRIVEZ_VOUS");
		editorPane.setBounds(0, 0, 1000, 46);
		contentPane.add(editorPane);

		createJlabel("Nom",40,140,234,41);
		createJlabel("Prenom",40,190,234,41);
		createJlabel("DateNaiss",40,240,234,41);

		createJlabel("CNE",510,140,234,41);
		createJlabel("CNI",510,190,234,41);
		createJlabel("Password",510,240,234,41);

		JLabel label_6 = new JLabel("Vous avez deja un compte !");
		label_6.setForeground(new Color(0, 80, 255));
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(15, 390, 269, 41);
		contentPane.add(label_6);

		textField = createJTextField(140,140,327,33);
		textField_3 = createJTextField(140,190,327,33);
		textField_4 = createJTextField(140,240,327,33);

		textField_5 = createJTextField(620,140,327,33);
		textField_1 = createJTextField(620,190,327,33);

		textField_2 = new JPasswordField();
		textField_2.setBounds(620, 240, 327, 33);
		textField_2.setBackground(new Color(224, 255, 255));
		textField_2.setForeground(new Color(139, 69, 19));
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_2.setColumns(8);
		contentPane.add(textField_2);



		JButton button = new JButton("Inscrire");
		button.setBackground(Color.white);
		button.setFont(new Font("Tahoma", Font.BOLD, 16));
		button.setForeground(new Color(0, 0, 0));
		button.addActionListener(e -> {

			String sql="insert into etudient( nom , prenom , dateNais,cne,cni,password) values( ? , ? , ? , ? , ? , ? ) ";

			try {
				String password = String.valueOf(textField_2.getPassword());
				if (!textField.getText().equals("") && !textField_1.getText().equals("") && !password.equals("") && !textField_3.getText().equals("") && !textField_4.getText().equals("") && !textField_5.getText().equals("") ) {

					prepared = con.prepareStatement(sql);
					prepared.setString(1, textField.getText());
					prepared.setString(2, textField_3.getText());
					prepared.setString(3, textField_4.getText());
					prepared.setString(4, textField_5.getText());
					prepared.setString(5,textField_1.getText());
					prepared.setString(6,password);
					prepared.execute();
					JOptionPane.showMessageDialog(null, "Utilisateur bien Ajouter :)");
					Connection_frame c = new Connection_frame();
					c.setVisible(true);
					c.setLocationRelativeTo(null);
					this.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "il faut traiter tous les champs correctement :(");}

			} catch (SQLException e1) {

				e1.printStackTrace();
			}


		});
		button.setBounds(420, 340, 147, 41);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBorder(null);
		contentPane.add(button);

		JLabel label_7 = new JLabel(" Connexion");
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection_frame conexion = new Connection_frame();
				conexion.setVisible(true);
				dispose();

			}
		});
		label_7.setForeground(new Color(255, 69, 0));
		label_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_7.setBounds(210, 390, 83, 41);
		contentPane.add(label_7);
	}
	public void createJlabel(String name,int x,int y ,int w,int h){
		JLabel label = new JLabel(name);
		label.setForeground(new Color(1, 1, 2));
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(x, y, w, h);
		contentPane.add(label);
	}
	public JTextField createJTextField(int x, int y,int w,int h){
		JTextField textField = new JTextField();
		textField.setBackground(new Color(224, 255, 255));
		textField.setForeground(new Color(139, 69, 19));
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setBounds(x, y, w, h);
		textField.setColumns(4);
		contentPane.add(textField);


		return textField;
	}

}
