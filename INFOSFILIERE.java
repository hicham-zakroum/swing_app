
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class INFOSFILIERE extends JFrame {

	Connection con;
	private final JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {

			try {
				INFOSFILIERE frame = new INFOSFILIERE("test");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	String fillier;
	public  INFOSFILIERE(String ID) {
		setTitle("Info Filiere page");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBackground(new Color(13, 211, 187));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		con = ConnectionBD.Connection_Mysql();


		JEditorPane pane1 = new JEditorPane();
		pane1.setEditable(false);
		pane1.setFont(new Font("Tahoma", Font.BOLD, 25));
		pane1.setBackground(new Color(255, 69, 0));
		pane1.setForeground(Color.BLACK);
		pane1.setText("  CHOIX DES FILIERES");
		pane1.setBounds(0, 0, 1000, 48);
		contentPane.add(pane1);


		createJlabel("FILIERE",217,200,222,21);


		String[] s2 = {"SMIA","SVT","SMPC"};
		JComboBox<String> comboBox = createComboBox(s2,549,200,222,22);



		JButton button = new JButton("Confirmer");
		button.setBackground(new Color(224, 255, 255));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.addActionListener(e -> {

			try {


				String req3 = " UPDATE etudient SET filiere1 = ? WHERE cne=?;";
				PreparedStatement st3 = con.prepareStatement(req3);
				st3.setString(1 , (String) comboBox.getSelectedItem());
				st3.setString(2,ID);
				st3.executeUpdate();
				JOptionPane.showMessageDialog(null,"enregistrer");
				fillier = (String) comboBox.getSelectedItem();
				String fil = (String) comboBox.getSelectedItem();
				assert fillier != null;
				switch (fillier) {
					case "SMIA" -> fillier = "1";
					case "SVT" -> fillier = "2";
					case "SMPC" -> fillier = "3";
				}
				st3.close();


				String req = "SELECT * FROM etud_mod WHERE cne = ?";
				PreparedStatement statement = con.prepareStatement(req);
				statement.setString(1, ID);
				ResultSet resultSet = statement.executeQuery();

				System.out.println(resultSet.next());
				if (resultSet.next()) {
					int option = JOptionPane.showOptionDialog(
							this,
							"Vous etes deja enregistre",
							"Confirmation",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE,
							null,
							new Object[]{"Telecharger PDF", "Reinscrire"},
							"Telecharger PDF"
					);

					if (option == JOptionPane.YES_OPTION) {
						ToPdf pdf = new ToPdf(ID);
						pdf.setVisible(true);
						pdf.setLocationRelativeTo(null);
						this.dispose();
					} else if (option == JOptionPane.NO_OPTION) {
						String deleteReq = "DELETE FROM etud_mod WHERE cne = ?";
						PreparedStatement deleteStatement = con.prepareStatement(deleteReq);
						deleteStatement.setString(1, ID);
						deleteStatement.execute();
						deleteStatement.close();

						INFOMODULE home = new INFOMODULE(ID, fillier, fil);
						home.setVisible(true);
						home.setLocationRelativeTo(null);
						this.dispose();
					}
				}
				else {
					INFOMODULE home = new INFOMODULE(ID, fillier, fil);
					home.setVisible(true);
					home.setLocationRelativeTo(null);
					this.dispose();
				}

				statement.close();
				resultSet.close();

			} catch (Exception e2) {
				System.out.println("il n'y a pas de connection a la base de donnees "+e2);
			}
		});



		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(410, 320, 133, 30);
		button.setBorder(null);
		contentPane.add(button);




	}
	public void createJlabel(String name,int x,int y ,int w,int h){
		JLabel label = new JLabel(name);
		label.setForeground(new Color(1, 1, 2));
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(x, y, w, h);
		contentPane.add(label);
	}
	public JComboBox<String> createComboBox(String[] s,int x,int y,int w,int h){
		JComboBox<String> comboBox = new JComboBox<>(s);
		comboBox.setForeground(new Color(255, 69, 0));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setBackground(new Color(224, 255, 255));
		comboBox.setBounds(x, y, w, h);
		comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(comboBox);
		return comboBox;
	}
}
