
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;

public class Connection_frame extends JFrame {

	private final JTextField textField ;
	private final JPasswordField textField_1 ;
	private final JPanel contentPane;
	Connection con;
	ResultSet res = null;
	PreparedStatement prepared = null;


	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Connection_frame frame = new Connection_frame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Connection_frame() {
		setTitle("Login page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(13, 211, 187));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		con = ConnectionBD.Connection_Mysql();
		contentPane.setLayout(null);

		JEditorPane seConnecter = new JEditorPane();
		seConnecter.setBounds(0, 0, 1000, 45);
		seConnecter.setEditable(false);
		seConnecter.setFont(new Font("Tahoma", Font.BOLD, 26));
		seConnecter.setForeground(new Color(0, 0, 0));
		seConnecter.setText("  SE CONNECTER");
		seConnecter.setBackground(new Color(255, 69, 0));
		contentPane.add(seConnecter);

		createJlabel("cne",221,143,152,31);
		createJlabel("password",221,222,152,31);


		textField = new JTextField();
		textField.setBounds(383, 147, 256, 27);
		textField.setBackground(new Color(224, 255, 255));
		textField.setForeground(new Color(255, 0, 0));
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setColumns(10);
		contentPane.add(textField);

		textField_1 = new JPasswordField();
		textField_1.setBounds(383, 222, 256, 27);
		textField_1.setBackground(new Color(224, 255, 255));
		textField_1.setForeground(new Color(255, 0, 0));
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_1.setColumns(10);
		contentPane.add(textField_1);

		JButton button = new JButton("Connecter");
		button.setBounds(440, 300, 121, 31);
		button.setForeground(Color.black);
		button.setFont(new Font("Tahoma", Font.BOLD, 16));
		button.addActionListener(arg0 -> {

			String cne = textField.getText();
			String password = String.valueOf(textField_1.getPassword());

			String sql = "select cne , password from etudient";
			if(cne.equals("") && password.equals("")) {
				JOptionPane.showMessageDialog(null, "il faut Remplisser les champs vides :(");

			}else {
				try {
					prepared=con.prepareStatement(sql);
					res=prepared.executeQuery();
					int i=0;
					while (res.next()) {
						String cne1 = res.getString("cne");
						String password1 = res.getString("password");

						if (cne1.equals(cne) && password1.equals(password)) {
							JOptionPane.showMessageDialog(null, "connexion reussite :)");
							i=1;
							INFOSFILIERE home = new INFOSFILIERE(cne);
							home.setVisible(true);
							home.setLocationRelativeTo(null);
							this.dispose();
						}

					}
					if(i==0) {
						JOptionPane.showMessageDialog(null, "Connexion Echouee , le Mot de passe ou Username est Incorrect :(");
					}

				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		button.setBorder(null);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(button);



		JButton button_1 = new JButton("S'inscrire ");
		button_1.addActionListener(e -> {
			Inscription i = new Inscription();
			i.setVisible(true);
			i.setLocationRelativeTo(null);
			this.dispose();
		});
		button_1.setBounds(20, 400, 120, 31);
		button_1.setForeground(new Color(1, 1, 2));
		button_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		button_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button_1.setBorder(null);
		contentPane.add(button_1);

	}
	public void createJlabel(String name,int x,int y ,int w,int h){
		JLabel label = new JLabel(name);
		label.setForeground(new Color(1, 1, 2));
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(x, y, w, h);
		contentPane.add(label);
	}
}