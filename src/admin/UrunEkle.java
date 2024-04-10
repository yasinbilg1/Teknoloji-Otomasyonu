package admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UrunEkle extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UrunEkle frame = new UrunEkle();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UrunEkle() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 530, 477);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Marka Adı");
        lblNewLabel.setForeground(new Color(255, 255, 255));

        textField = new JTextField();
        textField.setColumns(10);

        JLabel lblModel = new JLabel("Model");
        lblModel.setForeground(Color.WHITE);

        textField_1 = new JTextField();
        textField_1.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Kapasite");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "32 GB", "64 GB", "128 GB", "256 GB", "512 GB", "1 TB" }));

        JLabel lblNewLabel_1_1 = new JLabel("Renk");
        lblNewLabel_1_1.setForeground(Color.WHITE);

        JComboBox<String> comboBox_1 = new JComboBox<>();
        comboBox_1.setModel(new DefaultComboBoxModel<>(new String[] { "Siyah", "Beyaz ", "Mavi", "Kırmızı", "Gri" }));

        JLabel lblNewLabel_1_2 = new JLabel("Fiyat");
        lblNewLabel_1_2.setForeground(Color.WHITE);

        textField_2 = new JTextField();
        textField_2.setColumns(10);

        JLabel lblNewLabel_1_2_1 = new JLabel("Stok");
        lblNewLabel_1_2_1.setForeground(Color.WHITE);

        textField_3 = new JTextField();
        textField_3.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("ÜRÜN EKLEME");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JButton btnStokFiyatGuncelle = new JButton("Stok ve Fiyat Güncelleme");
        btnStokFiyatGuncelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // "Stok ve Fiyat Güncelleme" butonuna tıklandığında StokFiyatGuncelle.java dosyasına yönlendirme
                dispose(); // Bu pencereyi kapat
                StokFiyatGuncelle stokFiyatGuncellePencere = new StokFiyatGuncelle();
                stokFiyatGuncellePencere.setVisible(true);
            }
        });

        JButton btnUrunSilme = new JButton("Ürün Silme");
        btnUrunSilme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // "Ürün Silme" butonuna tıklandığında UrunSil.java dosyasına yönlendirme
                dispose(); // Bu pencereyi kapat
                UrunSil urunSilPencere = new UrunSil();
                urunSilPencere.setVisible(true);
            }
        });

        JButton btnUrunEkle = new JButton("Ürün Ekle");
        btnUrunEkle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String madi = textField.getText();
                String model = textField_1.getText();
                String kapasite = comboBox.getSelectedItem().toString();
                String renk = comboBox_1.getSelectedItem().toString();
                String fiyatStr = textField_2.getText();
                String stokStr = textField_3.getText();

                if (fiyatStr.matches("\\d+") && stokStr.matches("\\d+")) {
                    int fiyat = Integer.parseInt(fiyatStr);
                    int stok = Integer.parseInt(stokStr);

                    try {
                        // Veritabanı bağlantısı
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalprojesql", "root", "686668");

                        // SQL sorgusu
                        String sqlQuery = "INSERT INTO finalprojesql.telefon (telefon_marka, telefon_model, telefon_kapasite, telefon_renk, telefon_fiyat, telefon_stok) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                        // Parametreleri ayarla
                        preparedStatement.setString(1, madi);
                        preparedStatement.setString(2, model);
                        preparedStatement.setString(3, kapasite);
                        preparedStatement.setString(4, renk);
                        preparedStatement.setInt(5, fiyat);
                        preparedStatement.setInt(6, stok);

                        // Sorguyu çalıştır
                        int affectedRows = preparedStatement.executeUpdate();

                        if (affectedRows > 0) {
                            JOptionPane.showMessageDialog(null, "Ürün başarıyla eklendi!", "Başarılı",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Ürün eklenirken bir hata oluştu!", "Hata",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        // Kaynakları kapat
                        preparedStatement.close();
                        connection.close();

                    } catch (ClassNotFoundException | SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Fiyat ve stok için geçerli sayısal değerler girin.", "Hata",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addGap(207).addComponent(lblNewLabel_2))
                .addGroup(gl_contentPane.createSequentialGroup().addGap(156)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                .addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel).addGap(18)
                                        .addComponent(textField))
                                .addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
                                        .createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(lblModel, GroupLayout.PREFERRED_SIZE, 47,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_1).addComponent(lblNewLabel_1_1,
                                                GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                        .addGap(18)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(textField_1).addComponent(comboBox_1, 0,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(comboBox, 0, 124, Short.MAX_VALUE)))
                                .addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
                                        .createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 41,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_1_2_1, GroupLayout.PREFERRED_SIZE, 41,
                                                GroupLayout.PREFERRED_SIZE))
                                        .addGap(24)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                .addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 124,
                                                        Short.MAX_VALUE)
                                                .addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 124,
                                                        Short.MAX_VALUE)
                                                .addComponent(btnUrunEkle, GroupLayout.DEFAULT_SIZE, 124,
                                                        Short.MAX_VALUE)))))
                .addGroup(gl_contentPane.createSequentialGroup().addComponent(btnStokFiyatGuncelle).addGap(18)
                        .addComponent(btnUrunSilme, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
        );
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnStokFiyatGuncelle)
                                .addComponent(btnUrunSilme))
                        .addGap(14).addComponent(lblNewLabel_2).addGap(18).addGroup(gl_contentPane
                                .createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(textField,
                                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                                .addGap(18).addGroup(gl_contentPane
                                        .createParallelGroup(Alignment.BASELINE).addComponent(lblModel).addComponent(
                                                textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(18).addGroup(gl_contentPane
                                        .createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
                                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(18).addGroup(gl_contentPane
                                        .createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblNewLabel_1_1)
                                        .addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(18).addGroup(gl_contentPane
                                        .createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1_2)
                                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(18).addGroup(gl_contentPane
                                        .createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1_2_1)
                                        .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(37).addComponent(btnUrunEkle).addContainerGap(93, Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);
    }
}
