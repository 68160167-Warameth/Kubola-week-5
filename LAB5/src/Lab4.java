import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter; // นำเข้าคลาสสำหรับเขียนไฟล์
import java.io.IOException;

public class Lab4 {

    public static void main(String[] args) {

        // สร้าง Frame
        JFrame frame = new JFrame("Program with JTextArea and Save");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // สร้าง TextArea
        JTextArea textArea = new JTextArea(8, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // ใส่ ScrollBar ให้ TextArea
        JScrollPane scrollPane = new JScrollPane(textArea);

        // สร้างปุ่ม
        JButton button = new JButton("Show message");
        JButton buttonSave = new JButton("Save"); // สร้างปุ่ม Save เพิ่ม

        // สร้าง Panel สำหรับวางปุ่ม 2 ปุ่มคู่กันที่ด้านล่าง
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        buttonPanel.add(buttonSave);

        // เมื่อกดปุ่ม Show message
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                JOptionPane.showMessageDialog(frame, text,
                        "Your message: ", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // เมื่อกดปุ่ม Save
        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // กำหนดตำแหน่งไฟล์ (D:\message.txt)
                    FileWriter writer = new FileWriter("D:\\message.txt");
                    writer.write(textArea.getText());
                    writer.close();

                    JOptionPane.showMessageDialog(frame, "Save complete ");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // จัด Layout
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH); // เพิ่ม Panel ที่มี 2 ปุ่มเข้าไป

        // แสดงหน้าจอ
        frame.setVisible(true);
    }
}