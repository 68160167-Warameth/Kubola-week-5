import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Lab8 {
    private JFrame frame;
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File currentFile = null; // เก็บไฟล์ที่กำลังเปิดใช้งานอยู่

    public Lab05_8() {
        // สร้างหน้าต่างหลัก
        frame = new JFrame("Java Simple Text Editor");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // สร้างพื้นที่พิมพ์ข้อความ
        textArea = new JTextArea();
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        fileChooser = new JFileChooser();

        // สร้างเมนู
        createMenuBar();

        frame.setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        // สร้างรายการเมนูย่อย
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save As...");
        JMenuItem exitItem = new JMenuItem("Exit");

        // เพิ่มรายการลงในเมนู File
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // --- ตั้งค่าเหตุการณ์ (Actions) ---

        // New
        newItem.addActionListener(e -> {
            textArea.setText("");
            currentFile = null;
            frame.setTitle("Java Simple Text Editor - New File");
        });

        // Open
        openItem.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
                openFile(currentFile);
            }
        });

        // Save
        saveItem.addActionListener(e -> {
            if (currentFile != null) {
                saveToFile(currentFile);
            } else {
                saveAs();
            }
        });

        // Save As
        saveAsItem.addActionListener(e -> saveAs());

        // Exit
        exitItem.addActionListener(e -> System.exit(0));
    }

    private void openFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            textArea.setText("");
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            frame.setTitle("Editing: " + file.getName());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error opening file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveAs() {
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            saveToFile(currentFile);
        }
    }

    private void saveToFile(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(textArea.getText());
            frame.setTitle("Editing: " + file.getName());
            JOptionPane.showMessageDialog(frame, "File Saved!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // ใช้ SwingUtilities เพื่อให้รันใน Thread ที่ถูกต้องของ GUI
        SwingUtilities.invokeLater(() -> new Lab05_8());
    }
}