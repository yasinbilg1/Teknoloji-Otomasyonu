package Sayfa_lar;

import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class KayitSayfasi extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldAd;
    private JTextField textFieldSoyad;
    private JTextField textFieldMail;
    private JTextField textFieldTelefon;
    private JPasswordField passwordFieldKayit;
    private JPasswordField passwordFieldRepeatKayit;
    private JLabel lblErrorMessageKayit;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    KayitSayfasi frame = new KayitSayfasi();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public KayitSayfasi() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 550);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblAd = new JLabel("Ad:");
        lblAd.setForeground(Color.WHITE);

        JLabel lblSoyad = new JLabel("Soyad:");
        lblSoyad.setForeground(Color.WHITE);

        JLabel lblMail = new JLabel("Mail:");
        lblMail.setForeground(Color.WHITE);

        JLabel lblTelefon = new JLabel("Telefon:");
        lblTelefon.setForeground(Color.WHITE);

        JLabel lblPasswordKayit = new JLabel("Şifre:");
        lblPasswordKayit.setForeground(Color.WHITE);

        JLabel lblPasswordRepeatKayit = new JLabel("Şifre Tekrarı:");
        lblPasswordRepeatKayit.setForeground(Color.WHITE);

        textFieldAd = new JTextField();
        textFieldSoyad = new JTextField();
        textFieldMail = new JTextField();
        textFieldTelefon = new JTextField();
        textFieldTelefon.setBackground(Color.WHITE);
        passwordFieldKayit = new JPasswordField();
        passwordFieldRepeatKayit = new JPasswordField();

        // Kayıt Ol Butonu ve Hata Mesajı Etiketi
        JButton btnKayitOl = new JButton("Kayıt Ol");
        btnKayitOl.setBackground(Color.DARK_GRAY);
        lblErrorMessageKayit = new JLabel("");
        lblErrorMessageKayit.setForeground(Color.WHITE);

        // Kayıt Ol Butonu için ActionListener
        btnKayitOl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Kullanıcı adı, şifre ve şifre tekrarı değerlerini al
                String ad = textFieldAd.getText();
                String soyad = textFieldSoyad.getText();
                String mail = textFieldMail.getText();
                String telefon = textFieldTelefon.getText();
                String password = new String(passwordFieldKayit.getPassword());
                String passwordRepeat = new String(passwordFieldRepeatKayit.getPassword());

                // Şifre ve şifre tekrarı kontrolü
                if (password.equals(passwordRepeat)) {
                    // Kayıt işlemleri burada gerçekleştirilecek
                    if (kayitOl(ad, soyad, mail, telefon, password)) {
                        // Eğer kayıt başarılıysa Kayit_Giris sayfasına yönlendirilecek
                        Kayit_Giris kayitGiris = new Kayit_Giris();
                        kayitGiris.setVisible(true);
                        dispose(); // Kayıt sayfasını kapat
                    } else {
                        // Kayıt başarısızsa hata mesajını göster
                        lblErrorMessageKayit.setText("Kayıt başarısız. Lütfen tekrar deneyin.");
                    }
                } else {
                    // Şifreler uyuşmuyorsa hata mesajını göster
                    lblErrorMessageKayit.setText("Şifreler uyuşmuyor. Lütfen tekrar deneyin.");
                }
            }
        });

        // GroupLayout kullanarak bileşenlerin düzeni
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(50)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblAd)
                        .addComponent(lblSoyad)
                        .addComponent(lblMail)
                        .addComponent(lblTelefon)
                        .addComponent(lblPasswordKayit)
                        .addComponent(lblPasswordRepeatKayit))
                    .addGap(37)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(textFieldTelefon, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(passwordFieldRepeatKayit, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(passwordFieldKayit, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(textFieldMail, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(textFieldSoyad, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(textFieldAd, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                    .addGap(150))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(300)
                    .addComponent(btnKayitOl, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(400, Short.MAX_VALUE))
                .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                    .addContainerGap(300, Short.MAX_VALUE)
                    .addComponent(lblErrorMessageKayit)
                    .addGap(250))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(90)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblAd)
                        .addComponent(textFieldAd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblSoyad)
                        .addComponent(textFieldSoyad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblMail)
                        .addComponent(textFieldMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblTelefon)
                        .addComponent(textFieldTelefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPasswordKayit)
                        .addComponent(passwordFieldKayit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPasswordRepeatKayit)
                        .addComponent(passwordFieldRepeatKayit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addComponent(btnKayitOl)
                    .addGap(18)
                    .addComponent(lblErrorMessageKayit)
                    .addContainerGap(61, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        // Ekranın ortasına konumlandırma
        setLocationRelativeTo(null);
    }

    private boolean kayitOl(String ad, String soyad, String mail, String telefon, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalprojesql", "root", "686668");

            // Kullanıcı adının benzersiz olup olmadığını kontrol et
            if (!kullaniciMailBenzersizMi(mail, connection)) {
                lblErrorMessageKayit.setText("Bu mail adresi zaten kullanılıyor. Lütfen başka bir mail adresi deneyin.");
                return false;
            }

            // Kullanıcıyı veritabanına ekle
            String query = "INSERT INTO kullanici (kullanici_isim, kullanici_soyad, kullanici_mail, kullanici_telefon, kullanici_sifre) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, ad);
                preparedStatement.setString(2, soyad);
                preparedStatement.setString(3, mail);
                preparedStatement.setString(4, telefon);
                preparedStatement.setString(5, password);

                int result = preparedStatement.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean kullaniciMailBenzersizMi(String mail, Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM kullanici WHERE kullanici_mail = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, mail);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0;
                }
            }
        }
        return false;
    }
}
