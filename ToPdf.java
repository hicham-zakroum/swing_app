
import java.awt.*;
import java.awt.Font;
import java.io.*;
import java.sql.*;
import java.util.stream.Stream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

public class ToPdf extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Connection con = ConnectionBD.Connection_Mysql();
    private final String ID;

    public ToPdf(String ID) {
        this.ID=ID;
        setTitle("To PDF Page");
        setBounds(100, 100, 1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        JButton generateButton = new JButton("Telecharger PDF");
        generateButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        generateButton.setBounds(530, 200, 200, 50);
        generateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        generateButton.setBorder(null);
        generateButton.addActionListener(e -> generatePDF());


        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(13, 211, 187));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.add(generateButton);

        JEditorPane pane1 = new JEditorPane();
        pane1.setEditable(false);
        pane1.setFont(new Font("Tahoma", Font.BOLD, 25));
        pane1.setBackground(new Color(255, 69, 0));
        pane1.setText("  IMPRIMER LE FICHIER");
        pane1.setBounds(0, 0, 1000, 48);
        contentPane.add(pane1);

        JLabel label = new JLabel("Telecharger votre document PDF :");
        label.setForeground(new Color(1, 1, 2));
        label.setFont(new Font("Tahoma", Font.BOLD, 22));
        label.setBounds(120, 200, 500, 50);
        contentPane.add(label);


        setVisible(true);
    }

    private void generatePDF() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("file.pdf"));
            document.open();
            Image img = Image.getInstance("./lib/img/fslogo.png");
            img.setAlignment(Element.ALIGN_CENTER);
            document.add(img);
            String sql = "SELECT * FROM etudient where cne=?";

            PreparedStatement prepared = con.prepareStatement(sql);
            prepared.setString(1,ID);
            ResultSet res = prepared.executeQuery();
            while (res.next()) {
                String column1Data = res.getString("nom");
                String column2Data = res.getString("prenom");
                String column3Data = res.getString("cne");



                Paragraph paragraph = new Paragraph("nom      : " + column1Data);
                Paragraph paragraph1 = new Paragraph("prenom : " + column2Data);
                Paragraph paragraph2 = new Paragraph("cne       : " + column3Data);
                paragraph.setSpacingBefore(60f);

                document.add(paragraph);
                document.add(paragraph1);
                document.add(paragraph2);
            }
            res.close();
            PdfPTable table = new PdfPTable(3);
            table.setSpacingAfter(60f);
            table.setSpacingBefore(60f);
            addTableHeader(table);
            addRows(table);

            document.add(table);
            document.close();

            int option = JOptionPane.showOptionDialog(
                    this,
                    "Telechargement reussie!",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new Object[]{"OK"},
                    "OK");
            if (option == JOptionPane.OK_OPTION) {
                try {
                    File pdfFile = new File("file.pdf");
                    if (pdfFile.exists()) {
                        if (Desktop.isDesktopSupported()) {
                            Desktop.getDesktop().open(pdfFile);
                            this.dispose();
                        } else {
                            System.out.println("Desktop is not supported.");
                        }
                    } else {
                        System.out.println("PDF existe pas.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Probleme dans telechargement.");
        }
    }

    private void addRows(PdfPTable table) throws SQLException {
        String DATA_QUERY = "SELECT * FROM module WHERE code_m IN (SELECT code_m FROM etud_mod WHERE cne='"+ID+"');";
        PreparedStatement stmt = con.prepareStatement(DATA_QUERY);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String column1Data = rs.getString("titre");
            String column2Data = rs.getString("semestre");
            String column3Data = rs.getString("code_m");

            PdfPCell cell1 = new PdfPCell(new Phrase(column1Data));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setPadding(5);

            PdfPCell cell2 = new PdfPCell(new Phrase(column2Data));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setPadding(5);
            PdfPCell cell3 = new PdfPCell(new Phrase(column3Data));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setPadding(5);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
        }
        rs.close();
        stmt.close();
    }
    private void addTableHeader(PdfPTable table) {
        Stream.of("MODULE", "SEMESTER","CODE MODULE")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(new BaseColor(255, 69, 0));
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    header.setPadding(5);
                    table.addCell(header);
                });
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ToPdf pdf = new ToPdf("test");
                pdf.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }});
    }
}