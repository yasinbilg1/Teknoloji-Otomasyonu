package admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class UrunSil extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel tableModel;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UrunSil frame = new UrunSil();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UrunSil() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 530, 477);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("ÜRÜN SİLME");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JButton btnUrunSilme = new JButton("Ürünü Sil");
        btnUrunSilme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int urunID = (int) tableModel.getValueAt(selectedRow, 0);
                    deleteProduct(urunID);
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen bir ürün seçin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnHepsiniSil = new JButton("Hepsini Sil");
        btnHepsiniSil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteAllProducts();
            }
        });

        JScrollPane scrollPane = new JScrollPane();

        table = new JTable();
        scrollPane.setViewportView(table);
        tableModel = new DefaultTableModel();
        table.setModel(tableModel);
        initializeTable();

        JButton btnUrunEkle = new JButton("Ürün Ekle");
        btnUrunEkle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // "Ürün Ekle" butonuna tıklandığında UrunEkle.java dosyasına yönlendirme
                dispose(); // Bu pencereyi kapat
                UrunEkle urunEklePencere = new UrunEkle();
                urunEklePencere.setVisible(true);
            }
        });

        JButton btnStokFiyatGuncelle = new JButton("Stok Fiyat Güncelle");
        btnStokFiyatGuncelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // "Stok Fiyat Güncelle" butonuna tıklandığında StokFiyatGuncelle.java dosyasına yönlendirme
                dispose(); // Bu pencereyi kapat
                StokFiyatGuncelle stokFiyatGuncellePencere = new StokFiyatGuncelle();
                stokFiyatGuncellePencere.setVisible(true);
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                    .addComponent(btnStokFiyatGuncelle)
                                    .addGap(18)
                                    .addComponent(btnUrunEkle, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
                                .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                    .addComponent(btnHepsiniSil, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                                    .addGap(41)
                                    .addComponent(lblNewLabel)))
                            .addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                            .addComponent(btnUrunSilme, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(10, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnStokFiyatGuncelle)
                        .addComponent(btnUrunEkle))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(72)
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(btnHepsiniSil)
                                .addComponent(btnUrunSilme))
                            .addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblNewLabel)
                            .addPreferredGap(ComponentPlacement.RELATED)))
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
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
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();

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

    private void deleteProduct(int urunID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalprojesql", "root", "686668");

            // Sepet tablosundan ilgili ürünün kayıtlarını sil
            String deleteFromSepetQuery = "DELETE FROM sepet WHERE telefon_id = ?";
            PreparedStatement deleteFromSepetStatement = connection.prepareStatement(deleteFromSepetQuery);
            deleteFromSepetStatement.setInt(1, urunID);
            deleteFromSepetStatement.executeUpdate();
            deleteFromSepetStatement.close();

            // Ürünü urun_telefon tablosundan sil
            String deleteFromUrunQuery = "DELETE FROM finalprojesql.telefon WHERE telefon_id = ?";
            PreparedStatement deleteFromUrunStatement = connection.prepareStatement(deleteFromUrunQuery);
            deleteFromUrunStatement.setInt(1, urunID);

            int affectedRows = deleteFromUrunStatement.executeUpdate();

            if (affectedRows > 0) {
                // Ürün başarıyla silindi, bilgi penceresi göster
                JOptionPane.showMessageDialog(null, "Ürün başarıyla silindi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            } else {
                // Ürün silinirken bir hata oluştu, hata penceresi göster
                JOptionPane.showMessageDialog(null, "Ürün silinirken bir hata oluştu!", "Hata", JOptionPane.ERROR_MESSAGE);
            }

            deleteFromUrunStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteAllProducts() {
        int confirmResult = JOptionPane.showConfirmDialog(null, "Tüm ürünleri silmek istediğinize emin misiniz?", "Onay",
                JOptionPane.YES_NO_OPTION);
        if (confirmResult == JOptionPane.YES_OPTION) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalprojesql", "root", "686668");

                // Sepet tablosunu temizle
                String deleteAllFromSepetQuery = "DELETE FROM sepet";
                PreparedStatement deleteAllFromSepetStatement = connection.prepareStatement(deleteAllFromSepetQuery);
                deleteAllFromSepetStatement.executeUpdate();
                deleteAllFromSepetStatement.close();

                // Ürünleri urun_telefon tablosundan sil
                String deleteAllFromUrunQuery = "DELETE FROM finalprojesql.telefon";
                PreparedStatement deleteAllFromUrunStatement = connection.prepareStatement(deleteAllFromUrunQuery);
                deleteAllFromUrunStatement.executeUpdate();
                deleteAllFromUrunStatement.close();

                // Tüm ürünler başarıyla silindi, bilgi penceresi göster
                JOptionPane.showMessageDialog(null, "Tüm ürünler başarıyla silindi!", "Başarılı",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshTable();

                connection.close();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
