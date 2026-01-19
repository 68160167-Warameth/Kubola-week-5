import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Lab6 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Save File with JFileChooser");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. สร้าง TextArea และ ScrollPane
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        // 2. สร้าง JMenuBar (แถบเมนูด้านบน)
        JMenuBar menuBar = new JMenuBar();

        // 3. สร้าง JMenu (หัวข้อเมนูหลัก)
        JMenu fileMenu = new JMenu("File");

        // 4. สร้าง JMenuItem (รายการเมนูย่อย)
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        // เพิ่มเมนูย่อยลงในเมนูหลัก
        fileMenu.add(saveItem);
        fileMenu.addSeparator(); // เพิ่มเส้นคั่นระหว่าง Save กับ Exit
        fileMenu.add(exitItem);

        // เพิ่มเมนูหลักลงในแถบเมนู
        menuBar.add(fileMenu);

        // ใส่ JMenuBar ลงใน Frame
        frame.setJMenuBar(menuBar);

        // --- ทำงานเมื่อคลิกเมนู Save ---
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        PrintWriter writer = new PrintWriter(file);
                        writer.write(textArea.getText());
                        writer.close();
                        JOptionPane.showMessageDialog(frame, "Save file successfully.");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error. Unable to save file.");
                    }
                }
            }
        });

        // --- ทำงานเมื่อคลิกเมนู Exit ---
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // ปิดโปรแกรม
            }
        });

        // จัดวาง Layout
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}