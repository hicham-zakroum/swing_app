
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class INFOMODULE extends JFrame {
	static final int MAX_SELECTIONS = 7;
	private final JPanel contentPane,pane1,pane2,pane3;
	Connection con;
	ArrayList<Integer> arrlist = new ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				INFOMODULE frame = new INFOMODULE("k13", "2", "SVI");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}});
	}
	public INFOMODULE(String ID, String fillier, String fil) {
		setTitle("Info Module page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1000, 600);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(13, 211, 187));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pane1 = new JPanel();
		pane1.setBackground(new Color(1, 1, 2));
		pane1.setBounds(90,130,210,340);
		pane1.setLayout(null);
		contentPane.add(pane1);

		pane2 = new JPanel();
		pane2.setBackground(new Color(1, 1, 2));
		pane2.setBounds(390,130,210,340);
		pane2.setLayout(null);
		contentPane.add(pane2);

		pane3 = new JPanel();
		pane3.setBackground(new Color(1, 1, 2));
		pane3.setBounds(690,130,210,340);
		pane3.setLayout(null);
		contentPane.add(pane3);
		con = ConnectionBD.Connection_Mysql();



		JLabel labelFil = createJlabel(fil,470,70,139,39);
		labelFil.setForeground(new Color(0,0, 0));
		contentPane.add(labelFil);
		JLabel labelS1 = createJlabel("S1",90,30,76,33);
		pane1.add(labelS1);

		String[][] data = new String[18][3];
		try {
			int code_f= Integer.parseInt(fillier);
			String req = "SELECT * FROM module WHERE code_f="+code_f;
			PreparedStatement st = con.prepareStatement(req);
			ResultSet rs = st.executeQuery();
			int j = 0;
			while (rs.next()) {
				String titre = rs.getString(1);
				String id = String.valueOf(rs.getInt(2));
				String semestre = rs.getString(3);
				data[j][0] = titre;
				data[j][1] = id;
				data[j][2] = semestre;
				j += 1;
			}
			System.out.println(data[0][0]);

		} catch (Exception e2) {
			System.out.println("il n'y a pas de connection a la base de donn�es " + e2);
		}
		JCheckBox checkBox_1_1 = createJCheckBox(data[0][0], data[0][1], 15, 90, 180, 20);
		pane1.add(checkBox_1_1);
		JCheckBox checkBox_1_2 = createJCheckBox(data[1][0], data[1][1], 15, 130, 180, 20);
		pane1.add(checkBox_1_2);
		JCheckBox checkBox_1_3 = createJCheckBox(data[2][0], data[2][1], 15, 170, 180, 20);
		pane1.add(checkBox_1_3);
		JCheckBox checkBox_1_4 = createJCheckBox(data[3][0], data[3][1], 15, 210, 180, 20);
		pane1.add(checkBox_1_4);
		JCheckBox checkBox_1_5 = createJCheckBox(data[4][0], data[4][1], 15, 250, 180, 20);
		pane1.add(checkBox_1_5);
		JCheckBox checkBox_1_6 = createJCheckBox(data[5][0], data[5][1], 15, 290, 180, 20);
		pane1.add(checkBox_1_6);

		JLabel labelS3 = createJlabel("S3",90,30,76,33);
		pane2.add(labelS3);

		JCheckBox checkBox_2_1 = createJCheckBox(data[6][0], data[6][1], 15, 90, 180, 20);
		pane2.add(checkBox_2_1);
		JCheckBox checkBox_2_2 = createJCheckBox(data[7][0], data[7][1], 15, 130, 180, 20);
		pane2.add(checkBox_2_2);
		JCheckBox checkBox_2_3 = createJCheckBox(data[8][0], data[8][1], 15, 170, 180, 20);
		pane2.add(checkBox_2_3);
		JCheckBox checkBox_2_4 = createJCheckBox(data[9][0], data[9][1], 15, 210, 180, 20);
		pane2.add(checkBox_2_4);
		JCheckBox checkBox_2_5 = createJCheckBox(data[10][0], data[10][1], 15, 250, 180, 20);
		pane2.add(checkBox_2_5);
		JCheckBox checkBox_2_6 = createJCheckBox(data[11][0], data[11][1], 15, 290, 180, 20);
		pane2.add(checkBox_2_6);

		JLabel labeS5 = createJlabel("S5",90,30,76,33);
		pane3.add(labeS5);

		JCheckBox checkBox_3_1 = createJCheckBox(data[12][0], data[12][1], 15, 90, 180, 20);
		pane3.add(checkBox_3_1);
		JCheckBox checkBox_3_2 = createJCheckBox(data[13][0], data[13][1], 15, 130, 180, 20);
		pane3.add(checkBox_3_2);
		JCheckBox checkBox_3_3 = createJCheckBox(data[14][0], data[14][1], 15, 170, 180, 20);
		pane3.add(checkBox_3_3);
		JCheckBox checkBox_3_4 = createJCheckBox(data[15][0], data[15][1], 15, 210, 180, 20);
		pane3.add(checkBox_3_4);
		JCheckBox checkBox_3_5 = createJCheckBox(data[16][0], data[16][1], 15, 250, 180, 20);
		pane3.add(checkBox_3_5);
		JCheckBox checkBox_3_6 = createJCheckBox(data[17][0], data[17][1], 15, 290, 180, 20);
		pane3.add(checkBox_3_6);

		JButton button = new JButton("Confirmer");
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(415, 500, 156, 32);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBorder(null);
		contentPane.add(button);

		JEditorPane pane = new JEditorPane();
		pane.setEditable(false);
		pane.setFont(new Font("Tahoma", Font.BOLD, 25));
		pane.setForeground(Color.BLACK);
		pane.setText("  CHOIX DES MODULES");
		pane.setBackground(new Color(255, 69, 0));
		pane.setBounds(0, 0, 1000, 44);
		contentPane.add(pane);

		JButton btnNewButton_2 = new JButton("retour");
		btnNewButton_2.addActionListener(e -> {
			INFOSFILIERE info = new INFOSFILIERE(ID);
			info.setVisible(true);
			this.dispose();
			info.setLocationRelativeTo(null);
		});

		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(10, 50, 120, 32);
		btnNewButton_2.setBackground(new Color(1, 1, 2));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnNewButton_2);

		ActionListener checkboxListener = e -> {
			JCheckBox checkBox2 = (JCheckBox) e.getSource();
			if (checkBox2.isSelected()) {
				if (arrlist.size() >= MAX_SELECTIONS) {
					String idd = checkBox2.getActionCommand();
					arrlist.remove(Integer.valueOf(idd));
					checkBox2.setSelected(false);
					JOptionPane.showMessageDialog(contentPane,
							"Vous avez depasse le nombre maximal des choix de module (" + (MAX_SELECTIONS -1) + ").",
							"Maximum modules",
							JOptionPane.WARNING_MESSAGE);}}
		};


		ActionListener check = e -> {
			JCheckBox checkBox1 = (JCheckBox) e.getSource();
			boolean bool3 = checkBox_3_1.isSelected() || (checkBox_3_2.isSelected()) || (checkBox_3_3.isSelected()) || (checkBox_3_4.isSelected()) || (checkBox_3_5.isSelected()) || (checkBox_3_6.isSelected());
			boolean bool1 = (checkBox_1_1.isSelected()) || (checkBox_1_2.isSelected()) || (checkBox_1_3.isSelected()) || (checkBox_1_4.isSelected()) || (checkBox_1_5.isSelected()) || (checkBox_1_6.isSelected());
			if(bool1 && bool3){
				String idd = checkBox1.getActionCommand();
				arrlist.remove(Integer.valueOf(idd));
				checkBox1.setSelected(false);
				JOptionPane.showMessageDialog(contentPane,
						"Il faut choisir les modules correcte",
						"Semester issues",
						JOptionPane.WARNING_MESSAGE);
			}
		};

		checkBox_1_1.addActionListener(check);
		checkBox_1_2.addActionListener(check);
		checkBox_1_3.addActionListener(check);
		checkBox_1_4.addActionListener(check);
		checkBox_1_5.addActionListener(check);
		checkBox_1_6.addActionListener(check);
		checkBox_2_1.addActionListener(check);
		checkBox_2_2.addActionListener(check);
		checkBox_2_3.addActionListener(check);
		checkBox_2_4.addActionListener(check);
		checkBox_2_5.addActionListener(check);
		checkBox_2_6.addActionListener(check);
		checkBox_3_1.addActionListener(check);
		checkBox_3_2.addActionListener(check);
		checkBox_3_3.addActionListener(check);
		checkBox_3_4.addActionListener(check);
		checkBox_3_5.addActionListener(check);
		checkBox_3_6.addActionListener(check);

		checkBox_1_1.addActionListener(checkboxListener);
		checkBox_1_2.addActionListener(checkboxListener);
		checkBox_1_3.addActionListener(checkboxListener);
		checkBox_1_4.addActionListener(checkboxListener);
		checkBox_1_5.addActionListener(checkboxListener);
		checkBox_1_6.addActionListener(checkboxListener);
		checkBox_2_1.addActionListener(checkboxListener);
		checkBox_2_2.addActionListener(checkboxListener);
		checkBox_2_3.addActionListener(checkboxListener);
		checkBox_2_4.addActionListener(checkboxListener);
		checkBox_2_5.addActionListener(checkboxListener);
		checkBox_2_6.addActionListener(checkboxListener);
		checkBox_3_1.addActionListener(checkboxListener);
		checkBox_3_2.addActionListener(checkboxListener);
		checkBox_3_3.addActionListener(checkboxListener);
		checkBox_3_4.addActionListener(checkboxListener);
		checkBox_3_5.addActionListener(checkboxListener);
		checkBox_3_6.addActionListener(checkboxListener);

		ActionListener updateData = eee -> {
			JCheckBox checkBox4 = (JCheckBox) eee.getSource();
			if (!checkBox4.isSelected()) {
				String idd = checkBox4.getActionCommand();
				arrlist.remove(Integer.valueOf(idd));
			}
		};

		ActionListener getData = ee -> {
			JCheckBox checkBox = (JCheckBox) ee.getSource();
			if (checkBox.isSelected()) {
				String idd = checkBox.getActionCommand();
				arrlist.add(Integer.valueOf(idd));
				System.out.println(arrlist);
			}
		};

		checkBox_1_1.addActionListener(updateData);
		checkBox_1_2.addActionListener(updateData);
		checkBox_1_3.addActionListener(updateData);
		checkBox_1_4.addActionListener(updateData);
		checkBox_1_5.addActionListener(updateData);
		checkBox_1_6.addActionListener(updateData);
		checkBox_2_1.addActionListener(updateData);
		checkBox_2_2.addActionListener(updateData);
		checkBox_2_3.addActionListener(updateData);
		checkBox_2_4.addActionListener(updateData);
		checkBox_2_5.addActionListener(updateData);
		checkBox_2_6.addActionListener(updateData);
		checkBox_3_1.addActionListener(updateData);
		checkBox_3_2.addActionListener(updateData);
		checkBox_3_3.addActionListener(updateData);
		checkBox_3_4.addActionListener(updateData);
		checkBox_3_5.addActionListener(updateData);
		checkBox_3_6.addActionListener(updateData);

		checkBox_1_1.addActionListener(getData);
		checkBox_1_2.addActionListener(getData);
		checkBox_1_3.addActionListener(getData);
		checkBox_1_4.addActionListener(getData);
		checkBox_1_5.addActionListener(getData);
		checkBox_1_6.addActionListener(getData);
		checkBox_2_1.addActionListener(getData);
		checkBox_2_2.addActionListener(getData);
		checkBox_2_3.addActionListener(getData);
		checkBox_2_4.addActionListener(getData);
		checkBox_2_5.addActionListener(getData);
		checkBox_2_6.addActionListener(getData);
		checkBox_3_1.addActionListener(getData);
		checkBox_3_2.addActionListener(getData);
		checkBox_3_3.addActionListener(getData);
		checkBox_3_4.addActionListener(getData);
		checkBox_3_5.addActionListener(getData);
		checkBox_3_6.addActionListener(getData);

		button.addActionListener(eee -> {
			for (int s : arrlist) {
				sendData(s,ID);
			}
			JOptionPane.showMessageDialog(null, "modules bien Ajouter :)");
			ToPdf pdf = new ToPdf(ID);
			pdf.setVisible(true);
			pdf.setLocationRelativeTo(null);
			this.dispose();
		});
	}

	public void sendData(Integer data1,String iddd){
		try {
			String req = "INSERT INTO etud_mod( cne, code_m) values( ? , ? );";
			PreparedStatement st = con.prepareStatement(req);
			st.setString(1, iddd);
			st.setInt(2, data1);
			st.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public JLabel createJlabel(String name,int x,int y ,int w,int h){
		JLabel label = new JLabel(name);
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(x, y, w, h);
		return  label;
	}
	public JCheckBox createJCheckBox(String data1,String data2,int x,int y,int w,int h){
		JCheckBox checkBox = new JCheckBox(data1);
		checkBox.setActionCommand(data2);
		checkBox.setBackground(new Color(255, 69, 0));
		checkBox.setForeground(new Color(255, 255, 255));
		checkBox.setBounds(x, y, w, h);
		checkBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return checkBox;
	}
}