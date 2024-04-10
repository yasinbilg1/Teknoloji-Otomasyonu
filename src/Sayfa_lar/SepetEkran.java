package Sayfa_lar;

import javax.swing.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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

public class SepetEkran extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableUrunler;
    private DefaultTableModel tableModelUrunler;
    private JTable tableSepet;
    private DefaultTableModel tableModelSepet;
    private Connection connection;

    
    
    public SepetEkran() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 762, 563);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblUrunler = new JLabel("Ürünler");
        lblUrunler.setForeground(Color.WHITE);
        lblUrunler.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JScrollPane scrollPaneUrunler = new JScrollPane();

        tableUrunler = new JTable();
        tableModelUrunler = new DefaultTableModel(new Object[][] {}, new String[] { "Telefon ID", "Marka", "Model", "Renk", "Kapasite", "Fiyat" });

        tableUrunler.setModel(tableModelUrunler);
        scrollPaneUrunler.setViewportView(tableUrunler);

        JLabel lblSepet = new JLabel("Ürün Sepeti");
        lblSepet.setForeground(Color.WHITE);
        lblSepet.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JScrollPane scrollPaneSepet = new JScrollPane();

        tableSepet = new JTable();
        tableModelSepet = new DefaultTableModel(new Object[][] {}, new String[] { "Telefon ID", "Marka", "Model", "Renk", "Kapasite", "Fiyat" });

        tableSepet.setModel(tableModelSepet);
        scrollPaneSepet.setViewportView(tableSepet);

        JButton btnSepeteEkle = new JButton("Sepete Ekle");
        btnSepeteEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
                int selectedRow = tableUrunler.getSelectedRow();
                if (selectedRow != -1) {
                    int telefonID = (int) tableModelUrunler.getValueAt(selectedRow, 0);
                    urunSepeteEkle(telefonID);
                    urunlariSepeteYukle(); // Eklenen ürünleri tabloya yükle

                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen bir ürün seçin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        

        
           
        
        
        JButton btnSepetiBosalt = new JButton("Sepeti Boşalt");
        btnSepetiBosalt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sepetiBosalt();
                urunlariSepeteYukle(); // Sepet boşaltıldığında tabloyu güncelle
                JOptionPane.showMessageDialog(null, "Sepet boşaltıldı.");
            }
        });

        JButton btnSatinAl = new JButton("Satın Al");
        btnSatinAl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                satinAl();
            }
        });
        
        JButton btnNewButton = new JButton("Anasayfa");
        btnNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Butona tıklandığında yapılacak işlemler
                Ana_Sayfa anaSayfa = new Ana_Sayfa(); // Ana_Sayfa.java sınıfından uygun ismi kullanın
                anaSayfa.setVisible(true);
                dispose(); // Aktif pencereyi kapat
            }
        });

        // GroupLayout kodunu güncelle
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addGroup(gl_contentPane
                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPaneUrunler, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createSequentialGroup().addComponent(lblUrunler)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227,
                                        Short.MAX_VALUE)
                                .addComponent(btnSepeteEkle))
                        .addComponent(scrollPaneSepet, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createSequentialGroup().addComponent(lblSepet)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(btnSatinAl))
                        .addComponent(btnSepetiBosalt)
                        .addComponent(btnNewButton)) // "Anasayfa" butonunu ekle
                        .addContainerGap()));
        
                     
        

        GroupLayout gl_contentPane1 = new GroupLayout(contentPane);
        gl_contentPane1.setHorizontalGroup(
        	gl_contentPane1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane1.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPane1.createParallelGroup(Alignment.LEADING)
        				.addComponent(scrollPaneUrunler, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
        				.addGroup(gl_contentPane1.createSequentialGroup()
        					.addComponent(lblUrunler)
        					.addGap(18)
        					.addComponent(btnNewButton)
        					.addPreferredGap(ComponentPlacement.RELATED, 455, Short.MAX_VALUE)
        					.addComponent(btnSepeteEkle))
        				.addComponent(scrollPaneSepet, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
        				.addGroup(gl_contentPane1.createSequentialGroup()
        					.addComponent(lblSepet)
        					.addPreferredGap(ComponentPlacement.RELATED, 481, Short.MAX_VALUE)
        					.addComponent(btnSatinAl))
        				.addComponent(btnSepetiBosalt))
        			.addContainerGap())
        );
        gl_contentPane1.setVerticalGroup(
        	gl_contentPane1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane1.createSequentialGroup()
        			.addGroup(gl_contentPane1.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane1.createParallelGroup(Alignment.TRAILING)
        					.addGroup(gl_contentPane1.createSequentialGroup()
        						.addContainerGap()
        						.addComponent(btnSepeteEkle))
        					.addComponent(lblUrunler))
        				.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
        			.addGap(17)
        			.addComponent(scrollPaneUrunler, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lblSepet)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPaneSepet, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnSatinAl)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnSepetiBosalt)
        			.addContainerGap(61, Short.MAX_VALUE))
        );

        contentPane.setLayout(gl_contentPane1);

        // Veritabanı bağlantısını yap
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalprojesql", "root", "686668");
            urunleriTabloyaYukle(); // Başlangıçta ürünleri yükle
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Ürünleri getirme sorgusu
    public void urunleriTabloyaYukle() {
        try {
            String sqlQuery = "SELECT * FROM telefon WHERE telefon_stok > 0"; // Sadece stokta olan ürünleri getir
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int telefonID = resultSet.getInt("telefon_id");
                String marka = resultSet.getString("telefon_marka");
                String model = resultSet.getString("telefon_model");
                String kapasite = resultSet.getString("telefon_kapasite");
                String renk = resultSet.getString("telefon_renk");
                int fiyat = resultSet.getInt("telefon_fiyat");

                urunEkle(telefonID, marka, model, renk, kapasite, fiyat);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 // Ürünü sepete ekleme sorgusu
    public void urunSepeteEkle(int telefonID) {
        try {
            // Stok kontrolü
            String stokKontrolQuery = "SELECT telefon_stok FROM telefon WHERE telefon_id = ?";
            PreparedStatement stokKontrolStatement = connection.prepareStatement(stokKontrolQuery);
            stokKontrolStatement.setInt(1, telefonID);
            ResultSet resultSet = stokKontrolStatement.executeQuery();

            if (resultSet.next()) {
                int stokMiktari = resultSet.getInt("telefon_stok");

                // Eğer stok miktarı 0 veya daha azsa, ürün sepete eklenemez
                if (stokMiktari <= 0) {
                    JOptionPane.showMessageDialog(null, "Üzgünüz, bu ürün stokta yok!", "Hata", JOptionPane.ERROR_MESSAGE);
                    
                    
                    return;
                }

                // Eğer sepete aynı üründen sadece bir tanesi eklenebilir olacaksa,
                // önceden aynı ürünü sepete eklenip eklenmediğini kontrol et
                String kontrolQuery = "SELECT * FROM sepet WHERE telefon_id = ?";
                PreparedStatement kontrolStatement = connection.prepareStatement(kontrolQuery);
                kontrolStatement.setInt(1, telefonID);
                ResultSet kontrolResultSet = kontrolStatement.executeQuery();

                if (kontrolResultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Bu ürünü sepete zaten eklediniz.", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Eğer stok kontrolünden ve sepet kontrolünden geçerse, ürünü sepete ekle
                String sepetEkleQuery = "INSERT INTO sepet (telefon_id) VALUES (?)";
                PreparedStatement sepetEkleStatement = connection.prepareStatement(sepetEkleQuery);
                sepetEkleStatement.setInt(1, telefonID);

                int affectedRows = sepetEkleStatement.executeUpdate();

                if (affectedRows >= 1) {
                    JOptionPane.showMessageDialog(null, "Ürün sepete eklendi.");
                    urunlariSepeteYukle(); // Eklenen ürünleri tabloya yükle
                } else {
                    JOptionPane.showMessageDialog(null, "Ürün sepete eklenirken bir hata oluştu!", "Hata", JOptionPane.ERROR_MESSAGE);
                }

                resultSet.close();
                stokKontrolStatement.close();
                sepetEkleStatement.close();
                kontrolResultSet.close();
                kontrolStatement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Eklenen ürünleri tabloya ekleme
    public void urunlariSepeteYukle() {
        // Sepetteki ürünleri temizle
        tableModelSepet.setRowCount(0);

        try {
            String sqlQuery = "SELECT * FROM sepet s JOIN telefon u ON s.telefon_id = u.telefon_id";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int telefonID = resultSet.getInt("u.telefon_id");
                String marka = resultSet.getString("u.telefon_marka");
                String model = resultSet.getString("u.telefon_model");
                String kapasite = resultSet.getString("u.telefon_kapasite");
                String renk = resultSet.getString("u.telefon_renk");
                int fiyat = resultSet.getInt("u.telefon_fiyat");

                urunEkleAltKisim(telefonID, marka, model, renk, kapasite, fiyat);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

 // Satın alma işlemi
    public void satinAl() {
        // Sepetteki ürünleri getir
        try {
            String sqlQuery = "SELECT * FROM sepet s JOIN telefon u ON s.telefon_id = u.telefon_id";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int telefonID = resultSet.getInt("telefon_id");
                String marka = resultSet.getString("telefon_marka");
                String model = resultSet.getString("telefon_model");
                String kapasite = resultSet.getString("telefon_kapasite");
                String renk = resultSet.getString("telefon_renk");
                int fiyat = resultSet.getInt("telefon_fiyat");

                // Stok kontrolü
                String stokKontrolQuery = "SELECT telefon_stok FROM telefon WHERE telefon_id = ?";
                PreparedStatement stokKontrolStatement = connection.prepareStatement(stokKontrolQuery);
                stokKontrolStatement.setInt(1, telefonID);
                ResultSet stokKontrolResultSet = stokKontrolStatement.executeQuery();

                if (stokKontrolResultSet.next()) {
                    int stokMiktari = stokKontrolResultSet.getInt("telefon_stok");

                    // Eğer stok miktarı 0 veya daha azsa, "Stokta Yok" hatası ver
                    if (stokMiktari <= 0) {
                        JOptionPane.showMessageDialog(null, "Üzgünüz, ürün stokta yok!", "Hata", JOptionPane.ERROR_MESSAGE);
                        
                        
                        SepetEkran guncel=new SepetEkran();
                        guncel.setVisible(true);
                        dispose();
                        
                        
                        return;
                    }
                }

                // Eğer stok kontrolünden geçerse, satın alma işlemini gerçekleştir
                satinAlIslemi(telefonID);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        try {
            String bosaltQuery = "DELETE FROM sepet";
            PreparedStatement bosaltStatement = connection.prepareStatement(bosaltQuery);
            bosaltStatement.executeUpdate();
            bosaltStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void satinAlIslemi(int telefonID) {
        try {
            // Örneğin, satın alındığında stoktan düşürme işlemi
            String stokDusurQuery = "UPDATE telefon SET telefon_stok = telefon_stok - 1 WHERE telefon_id = ?";
            PreparedStatement stokDusurStatement = connection.prepareStatement(stokDusurQuery);
            stokDusurStatement.setInt(1, telefonID);
            int affectedRows = stokDusurStatement.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Satın alma işlemi başarılı!");
                
              
                
                urunlariSepeteYukle(); // Satın alma işlemi sonrasında tabloyu güncelle
                
                SepetEkran guncel=new SepetEkran();
                guncel.setVisible(true);
                dispose();
                
                
                
                
            } else {
                JOptionPane.showMessageDialog(null, "Satın alma işlemi sırasında bir hata oluştu!", "Hata", JOptionPane.ERROR_MESSAGE);
                
               
                
            }

            stokDusurStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // Sepeti boşaltma işlemi
    public void sepetiBosalt() {
        try {
            String bosaltQuery = "DELETE FROM sepet";
            PreparedStatement bosaltStatement = connection.prepareStatement(bosaltQuery);
            bosaltStatement.executeUpdate();
            bosaltStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Eklenen ürünü alt kısımda gösterme
    public void urunEkleAltKisim(int telefonID, String marka, String model, String kapasite, String renk, int fiyat) {
        Vector<Object> row = new Vector<Object>();
        row.add(telefonID);
        row.add(marka);
        row.add(model);
        row.add(kapasite);
        row.add(renk);
        row.add(fiyat);
        tableModelSepet.addRow(row);
    }

    // Eklenen ürünü tabloya ekleme
    public void urunEkle(int telefonID, String marka, String model, String kapasite, String renk, int fiyat) {
        Vector<Object> row = new Vector<Object>();
        row.add(telefonID);
        row.add(marka);
        row.add(model);
        row.add(renk); // Kapasite eklemeyi unutmuştum, burada ekledim
        row.add(kapasite);
        row.add(fiyat);
        tableModelUrunler.addRow(row);
    }

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SepetEkran frame = new SepetEkran();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}