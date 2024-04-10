package Sayfa_lar;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import admin.StokFiyatGuncelle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Kayit_Giris extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField Sifre;
    private JTextField KullaniciAdi;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Kayit_Giris frame = new Kayit_Giris();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Kayit_Giris() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 550);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);

        JButton btnNewButton = new JButton("Giriş Yap");
        btnNewButton.setBackground(Color.DARK_GRAY);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String kullaniciAdi = KullaniciAdi.getText();
                String sifre = new String(Sifre.getPassword());

                if (girisKontrol(kullaniciAdi, sifre)) {
                    // Giriş başarılı, StokFiyatGuncelle sayfasına yönlendir
                    dispose(); // Giriş sayfasını kapat
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Hatalı giriş. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnKayitOl = new JButton("Kayıt Ol");
        btnKayitOl.setForeground(Color.WHITE);
        btnKayitOl.setBackground(Color.DARK_GRAY);
        btnKayitOl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                KayitSayfasi kayitSayfasi = new KayitSayfasi();
                kayitSayfasi.setVisible(true);
                dispose();
            }
        });

        Sifre = new JPasswordField();
        KullaniciAdi = new JTextField();
        KullaniciAdi.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Kullanıcı Adı:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setForeground(Color.WHITE);

        JLabel lblNewLabel_1_1 = new JLabel("Şifre:");
        lblNewLabel_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel lblNewLabel_2 = new JLabel("Hesabınız Yok mu?");
        lblNewLabel_2.setForeground(Color.WHITE);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(97)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                    .addComponent(lblNewLabel_1)
                                    .addComponent(KullaniciAdi, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                                    .addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Sifre, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
                                .addGap(86))
                            .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(137)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                    .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnKayitOl, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                                .addGap(137)))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(188)
                            .addComponent(lblNewLabel_2)
                            .addContainerGap())))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                            .addGap(116)
                            .addComponent(lblNewLabel_1)
                            .addGap(18)
                            .addComponent(KullaniciAdi, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(Sifre, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addGap(23)
                            .addComponent(lblNewLabel_2)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(btnKayitOl, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel, GroupLayout.PREFERRED_SIZE, 485, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(22, 22, 316, 300);
        lblNewLabel.setIcon(new ImageIcon("İmage\\Giris1.png"));
        panel.add(lblNewLabel);

        contentPane.setLayout(gl_contentPane);
    }

    private boolean girisKontrol(String kullaniciAdi, String sifre) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalprojesql", "root", "686668")) {
            // İlk olarak admin tablosunda kontrol et
        	String adminQuery = "SELECT * FROM admin WHERE BINARY kullanici_mail = ? AND BINARY kullanici_sifre = ?";
            try (PreparedStatement adminStatement = connection.prepareStatement(adminQuery)) {
                adminStatement.setString(1, kullaniciAdi);
                adminStatement.setString(2, sifre);
                try (ResultSet adminResult = adminStatement.executeQuery()) {
                    if (adminResult.next()) {
                        // Admin girişi başarılı, StokFiyatGuncelle sayfasına yönlendir
                        StokFiyatGuncelle stokFiyatGuncellePencere = new StokFiyatGuncelle();
                        stokFiyatGuncellePencere.setVisible(true);
                        dispose(); // Giriş sayfasını kapat
                        return true;
                    }
                }
            }

            // Eğer admin girişi değilse, kullanici_isim tablosunda kontrol et
            String kullaniciQuery = "SELECT * FROM kullanici WHERE kullanici_mail = ? AND kullanici_sifre = ?";
            try (PreparedStatement kullaniciStatement = connection.prepareStatement(kullaniciQuery)) {
                kullaniciStatement.setString(1, kullaniciAdi);
                kullaniciStatement.setString(2, sifre);
                try (ResultSet kullaniciResult = kullaniciStatement.executeQuery()) {
                    if (kullaniciResult.next()) {
                        // Kullanıcı girişi başarılı, Ana_Sayfa sayfasına yönlendir
                        Ana_Sayfa anaSayfa = new Ana_Sayfa();
                        anaSayfa.setVisible(true);
                        dispose(); // Giriş sayfasını kapat
                        return true;
                    }
                }
            }

            // Hatalı giriş durumu
            JOptionPane.showMessageDialog(contentPane, "Hatalı giriş. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
