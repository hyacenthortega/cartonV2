package Items;

import Supplier.SupplierList;
import com.mysql.cj.jdbc.Blob;
import com.toedter.calendar.JDateChooser;
import databaseconnection.DataBaseConnection;
import java.awt.Color;
import java.awt.Image;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.TableRowSorter;
import raven.glasspanepopup.GlassPanePopup;

public class Items extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private AddItem addItem;
    private Timer timer;
    private UpdateItem update;

   

    public Items() throws Exception {
        initComponents();
        populateTable();
        update = new UpdateItem();
        timer = new Timer(1000, (e) -> {
            try {
                populateTable();
            } catch (Exception ex) {
                Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        timer.start();
        addItem = new AddItem(this);

       
    }

    private PreparedStatement p;

    private void populateTable() throws Exception {
        try {

            DataBaseConnection.getInstance().ConnectToDatabase();

            String sql = "SELECT * FROM additems";
            PreparedStatement p = DataBaseConnection.getInstance().getConnection().prepareStatement(sql);

            ResultSet rs = p.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            int c = rsd.getColumnCount();

            DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                for (int i = 0; i <= c; i++) {
                    Blob blob = (Blob) rs.getBlob("Image");
                    itemTable.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
                    ImageIcon imageicon = ImageRenderer.blobToImageIcon(blob, 100, 100);
                    v.add(imageicon);
                    v.add(rs.getString("supplier"));
                    v.add(rs.getString("productname"));
                    v.add(rs.getString("productId"));
                    v.add(rs.getString("category"));
                    v.add(rs.getString("quantity"));
                    v.add(rs.getString("cost"));
                    v.add(rs.getString("price"));
                    v.add(rs.getString("status"));
                    v.add(rs.getString("date"));
                }
                model.addRow(v);
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        txtfield = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        itemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Supplier", "Product Name", "Product ID", "Category", "Quantity", "Cost", "Price", "Status", "Date Modifiied"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        itemTable.setRowHeight(40);
        itemTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(itemTable);

        txtfield.setText("Search here");
        txtfield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtfieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtfieldFocusLost(evt);
            }
        });
        txtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfieldKeyReleased(evt);
            }
        });

        jButton1.setText("Add Item");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 412, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfieldKeyReleased
        DefaultTableModel table = (DefaultTableModel) itemTable.getModel();
        TableRowSorter<DefaultTableModel> tbl = new TableRowSorter<>(table);
        itemTable.setRowSorter(tbl);
        tbl.setRowFilter(RowFilter.regexFilter(txtfield.getText()));

    }//GEN-LAST:event_txtfieldKeyReleased

    private void txtfieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfieldFocusGained
        if (txtfield.getText().equals("Search here")) {
            txtfield.setText("");
            txtfield.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtfieldFocusGained

    private void txtfieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfieldFocusLost
        if (txtfield.getText().equals("")) {
            txtfield.setText("Search here");
            txtfield.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtfieldFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GlassPanePopup.showPopup(addItem);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void itemTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemTableMouseClicked
        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
        int SelectedRows = itemTable.getSelectedRow();

        ImageIcon image = (ImageIcon) itemTable.getValueAt(SelectedRows, 0);
        Image pic = image.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(pic);
        update.imageLbl.setIcon(img);

        update.suppFld.setText(itemTable.getValueAt(SelectedRows, 1).toString());
        update.proName.setText(itemTable.getValueAt(SelectedRows, 2).toString());
        update.proId.setText(itemTable.getValueAt(SelectedRows, 3).toString());
        update.catFld.setText(itemTable.getValueAt(SelectedRows, 4).toString());
        update.quanFld.setText(itemTable.getValueAt(SelectedRows, 5).toString());
        update.costFld.setText(itemTable.getValueAt(SelectedRows, 6).toString());
        update.priceFld.setText(itemTable.getValueAt(SelectedRows, 7).toString());
        update.statusFld.setText(itemTable.getValueAt(SelectedRows, 8).toString());

        try {
            String dateString = itemTable.getValueAt(SelectedRows, 9).toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Modify the format as per your date string
            java.util.Date date = dateFormat.parse(dateString);
            update.dateChooser.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        GlassPanePopup.showPopup(update);

    }//GEN-LAST:event_itemTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable itemTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtfield;
    // End of variables declaration//GEN-END:variables

}
