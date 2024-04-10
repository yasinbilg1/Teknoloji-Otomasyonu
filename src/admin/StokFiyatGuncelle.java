package admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class StokFiyatGuncelle extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField textFieldStok;
    private JTextField textFieldFiyat;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StokFiyatGuncelle frame = new StokFiyatGuncelle();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StokFiyatGuncelle() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 806, 532);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.GRAY);

        setContentPane(contentPane);

        table = new JTable();
        tableModel = new DefaultTableModel();
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JLabel lblNewLabel = new JLabel("Güncel Stok");
        lblNewLabel.setForeground(Color.WHITE);

        JLabel lblNewLabel_1 = new JLabel("Güncel Fiyat");
        lblNewLabel_1.setForeground(Color.WHITE);

        textFieldStok = new JTextField();
        textFieldStok.setColumns(10);

        textFieldFiyat = new JTextField();
        textFieldFiyat.setColumns(10);

        JButton btnUrunEkleme = new JButton("Ürün Ekleme");
        btnUrunEkleme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // "Ürün Ekleme" butonuna tıklandığında UrunEkle.java dosyasına yönlendirme
                dispose(); // Bu pencereyi kapat
                UrunEkle urunEklePencere = new UrunEkle();
                urunEklePencere.setVisible(true);
            }
        });

        getContentPane().add(btnUrunEkleme);
    

        JButton btnUrunSilme = new JButton("Ürün Silme");
        btnUrunSilme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // "Ürün Silme" butonuna tıklandığında UrunSil.java dosyasına yönlendirme
                dispose(); // Bu pencereyi kapat
                UrunSil urunSilPencere = new UrunSil();
                urunSilPencere.setVisible(true);
            }
        });

        JButton btnGuncelle = new JButton("Güncelle");
        btnGuncelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int urunID = (int) tableModel.getValueAt(selectedRow, 0);
                    updateProduct(urunID);
                } else {
                    // Kullanıcı bir ürün seçmediğinde uyarı ver
                    // Burada bir JOptionPane.showMessageDialog kullanabilirsiniz
                    JOptionPane.showMessageDialog(null, "Lütfen bir ürün seçin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JLabel lblNewLabel_2 = new JLabel("Stok ve Fiyat Güncelleme");
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnUrunEkleme)
                            .addGap(39)
                            .addComponent(btnUrunSilme))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(88)
                            .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblNewLabel)
                                .addComponent(lblNewLabel_1))
                            .addGap(37)
                            .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(textFieldFiyat)
                                .addComponent(textFieldStok, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                            .addGap(40)
                            .addComponent(btnGuncelle, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(29)
                            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 720, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(264)
                            .addComponent(lblNewLabel_2)))
                    .addContainerGap(168, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUrunEkleme)
                        .addComponent(btnUrunSilme))
                    .addGap(46)
                    .addComponent(lblNewLabel_2)
                    .addGap(33)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNewLabel)
                        .addComponent(textFieldStok, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(29)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNewLabel_1)
                        .addComponent(textFieldFiyat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuncelle))
                    .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
        initializeTable(); // Tabloyu başlangıçta doldur
    }

    private void initializeTable() {
        String[] columnNames = { "Telefon ID", "Marka", "Model", "Kapasite", "Renk", "Fiyat", "Stok" };
        tableModel.setColumnIdentifiers(columnNames);
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalprojesql", "root", "686668");
            String sqlQuery = "SELECT * FROM finalprojesql.telefon";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int telefonID = resultSet.getInt("telefon_id");
                String marka = resultSet.getString("telefon_marka");
                String model = resultSet.getString("telefon_model");
                String kapasite = resultSet.getString("telefon_kapasite");
                String renk = resultSet.getString("telefon_renk");
                int fiyat = resultSet.getInt("telefon_fiyat");
                int stok = resultSet.getInt("telefon_stok");

                tableModel.addRow(new Object[] { telefonID, marka, model, kapasite, renk, fiyat, stok });
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateProduct(int urunID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalprojesql", "root", "686668");

            int yeniStok = Integer.parseInt(textFieldStok.getText());
            double yeniFiyat = Double.parseDouble(textFieldFiyat.getText());

            String sqlQuery = "UPDATE telefon SET telefon_stok = ?, telefon_fiyat = ? WHERE telefon_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, yeniStok);
            preparedStatement.setDouble(2, yeniFiyat);
            preparedStatement.setInt(3, urunID);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Ürün başarıyla güncellendi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(null, "Ürün güncellenirken bir hata oluştu!", "Hata", JOptionPane.ERROR_MESSAGE);
            }

            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}
