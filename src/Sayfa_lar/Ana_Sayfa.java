package Sayfa_lar;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

public class Ana_Sayfa extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Ana_Sayfa frame = new Ana_Sayfa();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Ana_Sayfa() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1050, 825);
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JButton btnAnaSayfa = new JButton("Anasayfa");
        btnAnaSayfa.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnAnaSayfa.addActionListener(e -> {
            Ana_Sayfa anaSayfa = new Ana_Sayfa();
            anaSayfa.setVisible(true);
            dispose();
        });
        
        JButton btnSepet = new JButton("Sepet");
        btnSepet.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnSepet.addActionListener(e -> {
            SepetEkran Sepet = new SepetEkran();
            Sepet.setVisible(true);
            dispose();
        });

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBackground(Color.GRAY);
        lblNewLabel.setIcon(new ImageIcon("İmage\\Foto.png"));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnAnaSayfa, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnSepet, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(785, Short.MAX_VALUE))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap(54, Short.MAX_VALUE)
                    .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 978, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnAnaSayfa, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSepet, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 682, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(436, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
