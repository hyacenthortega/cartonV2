
package Category;

import Controller.AddCategories;
import com.mysql.cj.jdbc.Blob;
import databaseconnection.DataBaseConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.CategoryModel;

/**
 *
 * @author home
 */
public class AddCategory extends javax.swing.JPanel {

    private JPanel imagePanel;
   ///

    public AddCategory() {
        initComponents();
       imagePanel= new JPanel();
        
        jname.setMaximumSize(new Dimension(10, Short.MAX_VALUE)); // Set maximum width
        jname.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the left
        jname.setAutoscrolls(true);
    }

    private PreparedStatement p;

    private void populateImagePanel() throws Exception {
        try {
            DataBaseConnection.getInstance().ConnectToDatabase();

            String sql = "SELECT * FROM addcategory";
            PreparedStatement p = DataBaseConnection.getInstance().getConnection().prepareStatement(sql);

            ResultSet rs = p.executeQuery();

            imagePanel.removeAll();
            imagePanel.revalidate();
            imagePanel.repaint();

            // Set the background color of the panel
            imagePanel.setBackground(Color.WHITE);

            // Create a JPanel with GridLayout for images and labels
            JPanel panel = new JPanel(new GridLayout(0, 6, 10, 10)); // 5 columns, spacing of 10 pixels
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            while (rs.next()) {
                Blob blob = (Blob) rs.getBlob("Image");
                byte[] imageData = blob.getBytes(1, (int) blob.length());

                ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                BufferedImage bufferedImage = ImageIO.read(bis);

                if (bufferedImage != null) {
                    int scaledWidth = 100;
                    int scaledHeight = 100;
                    Image scaledImage = bufferedImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    JLabel imageLabel = new JLabel(scaledIcon);
                    JLabel categoryName = new JLabel(rs.getString("categoryname"));

                    // Set alignment of the category name label
                    categoryName.setHorizontalAlignment(SwingConstants.CENTER);

                    // Create a JPanel to hold each image and its label
                    JPanel imagePanelWithLabel = new JPanel(new BorderLayout());
                    imagePanelWithLabel.add(imageLabel, BorderLayout.CENTER);
                    imagePanelWithLabel.add(categoryName, BorderLayout.SOUTH);

                    // Add the image and label panel to the main panel
                    panel.add(imagePanelWithLabel);
                }

                bis.close();
            }

            imagePanel.add(panel);
            imagePanel.revalidate();
            imagePanel.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (p != null) {
                p.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        categoryField = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jpic = new javax.swing.JLabel();
        upload = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jname = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Monospac821 BT", 1, 18)); // NOI18N
        jLabel1.setText("Add New Category");

        categoryField.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N

        add.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        add.setText("Add ");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jLabel2.setText("Category Name");

        jpic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        upload.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        upload.setText("Upload image");
        upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadActionPerformed(evt);
            }
        });

        cancel.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jname.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jname.setText("name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(categoryField, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jname)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(add)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancel))
                            .addComponent(jpic, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(upload)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(upload)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpic, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jname)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add)
                    .addComponent(cancel))
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelActionPerformed
    private String selectedImagePath = "";
    private void uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpeg");
        fileChooser.addChoosableFileFilter(fnef);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedImageFile = fileChooser.getSelectedFile();
            jname.setText(selectedImageFile.getName());
            selectedImagePath = selectedImageFile.getAbsolutePath();

            ImageIcon im = new ImageIcon(selectedImagePath);
            Image pic = im.getImage().getScaledInstance(jpic.getWidth(), jpic.getHeight(), Image.SCALE_SMOOTH);
            jpic.setIcon(new ImageIcon(pic));
        }
    }//GEN-LAST:event_uploadActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        String categoryname = categoryField.getText();
        JLabel categoryLabel = new JLabel(categoryname);

        if (categoryname.isEmpty() || selectedImagePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add both category name and upload an image.");
            return;
        }

        byte[] imageData = null;
        try {
            imageData = Files.readAllBytes(Paths.get(selectedImagePath));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading the image.");
            return;
        }

        ImageIcon im = new ImageIcon(selectedImagePath);
        Image image = im.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledIcon);

        // Creating a new panel for the added category
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(categoryLabel, BorderLayout.SOUTH);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        imagePanel.add(panel);
        imagePanel.revalidate();
        imagePanel.repaint();

        categoryField.setText("");
        selectedImagePath = "";
        jpic.setIcon(null);
        jname.setText("");

        AddCategories addcategories = new AddCategories();
        addcategories.addCategoriesToDatabase(new CategoryModel(categoryname, imageData));

        try {
            populateImagePanel();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Added Successfully");

    }//GEN-LAST:event_addActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton cancel;
    private javax.swing.JTextField categoryField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jname;
    private javax.swing.JLabel jpic;
    private javax.swing.JButton upload;
    // End of variables declaration//GEN-END:variables
}
