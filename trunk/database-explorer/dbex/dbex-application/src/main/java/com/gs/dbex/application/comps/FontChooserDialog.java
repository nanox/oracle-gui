
/*
 * FontChooserDialog.java
 *
 * Created on Jun 9, 2009, 4:50:55 PM
 */

package com.gs.dbex.application.comps;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

/**
 *
 * @author sabuj.das
 */
public class FontChooserDialog extends javax.swing.JDialog {

    public static String[] installedFontNames;
    public static Integer[] fontSizes = new Integer[92];
    public static String[] fontStyles = new String[4];
    public static final int OK_OPTION = 1110;
    public static final int CANCEL_OPTION = 1111;

    private int selectedOption = CANCEL_OPTION;

    static{
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        installedFontNames = ge.getAvailableFontFamilyNames();

        for(int i=8, j=0; j< fontSizes.length; i++,j++)
            fontSizes[j] = i;
        fontStyles[0] = "Plain";
        fontStyles[1] = "Bold";
        fontStyles[2] = "Italic";
        fontStyles[3] = "Bold Italic";
    }

    public int getSelectedOption() {
        return selectedOption;
    }
    public int showFontChooserDialog(){
        this.setVisible(true);
        return selectedOption;
    }
    /** Creates new form FontChooserDialog */
    public FontChooserDialog(java.awt.Frame parent, boolean modal, Font f) {
        super(parent, modal);
        selectedFont = f;
        oldFont = f;
        initComponents();
        fontNameList.setSelectedValue(f.getName(), true);
        int style = f.getStyle();
        switch(style){
            case Font.PLAIN:
                fontStyleList.setSelectedValue("Plain", true);
                break;
            case Font.BOLD:
                fontStyleList.setSelectedValue("Bold", true);
                break;
            case Font.ITALIC:
                fontStyleList.setSelectedValue("Italic", true);
                break;
            case Font.BOLD + Font.ITALIC:
                fontStyleList.setSelectedValue("Bold Italic", true);
                break;
        }
        //fontSizeTF.setText(""+f.getSize());
        fontSizeList.setSelectedValue(f.getSize(), true);
        previewLabel.setFont(f);
        getRootPane().setDefaultButton(okButton);
        bringToCenter();
    }

    private void bringToCenter() {
        Dimension pd = getParent().getSize();
        Dimension sz = this.getSize();
        setLocation(new Point(
                getParent().getLocation().x + (pd.width/2 - sz.width/2),
                getParent().getLocation().y + (pd.height/2 - sz.height/2)
            ));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fontNameList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        fontStyleList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        fontSizeList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fontNameTextField = new javax.swing.JTextField();
        fontStyleTextField = new javax.swing.JTextField();
        fontSizeTF = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        previewPanel = new javax.swing.JPanel();
        previewLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Select Font");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(" Font "));

        fontNameList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = installedFontNames;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        fontNameList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        fontNameList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                fontNameListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(fontNameList);

        fontStyleList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = fontStyles;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        fontStyleList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        fontStyleList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                fontStyleListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(fontStyleList);

        fontSizeList.setModel(new javax.swing.AbstractListModel() {
            Integer[] strings = fontSizes;
            public int getSize() { return strings.length; }
            public Integer getElementAt(int i) { return strings[i]; }
        });
        fontSizeList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        fontSizeList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                fontSizeListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(fontSizeList);

        jLabel1.setText("Font Name");

        jLabel2.setText("Style");

        jLabel3.setText("Size");

        fontNameTextField.setEditable(false);
        fontNameTextField.setCaretColor(new java.awt.Color(0, 0, 204));

        

        fontNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fontNameTextFieldActionPerformed(evt);
            }
        });
        fontNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fontNameTextFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fontNameTextFieldKeyTyped(evt);
            }
        });

        fontStyleTextField.setEditable(false);

        
        fontStyleTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fontStyleTextFieldActionPerformed(evt);
            }
        });

        fontSizeTF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#"))));
        fontSizeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fontSizeTFActionPerformed(evt);
            }
        });
        fontSizeTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fontSizeTFFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(fontNameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(fontStyleTextField)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(fontSizeTF, 0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fontNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fontStyleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fontSizeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(" Preview "));

        previewPanel.setBackground(new java.awt.Color(255, 255, 255));
        previewPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        previewLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        previewLabel.setText("AaBbCcYyZz");

        javax.swing.GroupLayout previewPanelLayout = new javax.swing.GroupLayout(previewPanel);
        previewPanel.setLayout(previewPanelLayout);
        previewPanelLayout.setHorizontalGroup(
            previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(previewPanelLayout.createSequentialGroup()
                .addComponent(previewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        previewPanelLayout.setVerticalGroup(
            previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(previewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(previewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(okButton)
                        .addGap(34, 34, 34)
                        .addComponent(cancelButton)
                        .addGap(139, 139, 139))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );


        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cancel();
        selectedOption = CANCEL_OPTION;
    }//GEN-LAST:event_formWindowClosing

    private void fontStyleTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontStyleTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fontStyleTextFieldActionPerformed

    private void fontSizeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontSizeTFActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_fontSizeTFActionPerformed

    private void fontNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fontNameTextFieldKeyTyped
        
}//GEN-LAST:event_fontNameTextFieldKeyTyped

    private void fontNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontNameTextFieldActionPerformed

}//GEN-LAST:event_fontNameTextFieldActionPerformed

    private void fontNameListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_fontNameListValueChanged
        updatePreview();
    }//GEN-LAST:event_fontNameListValueChanged

    private void fontNameTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fontNameTextFieldKeyPressed
        
    }//GEN-LAST:event_fontNameTextFieldKeyPressed

    private void fontSizeListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_fontSizeListValueChanged
        fontSizeTF.setText(fontSizeList.getSelectedValue().toString());
        updatePreview();
    }//GEN-LAST:event_fontSizeListValueChanged

    private void fontSizeTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fontSizeTFFocusLost
        int s = Integer.parseInt(fontSizeTF.getText());
        fontSizeList.setSelectedValue(s, true);
        updatePreview();
    }//GEN-LAST:event_fontSizeTFFocusLost

    private void fontStyleListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_fontStyleListValueChanged
        updatePreview();
    }//GEN-LAST:event_fontStyleListValueChanged

    private void ok() {
        updatePreview();
        oldFont = selectedFont;
        
    }
    private void cancel() {
        selectedFont = oldFont;
        
    }

    public Font getSelectedFont(){
        return selectedFont;
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        ok();
        selectedOption = OK_OPTION;
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        cancel();
        selectedOption = CANCEL_OPTION;
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed


    private Font selectedFont;
    private Font oldFont;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JList fontNameList;
    private javax.swing.JTextField fontNameTextField;
    private javax.swing.JList fontSizeList;
    private javax.swing.JFormattedTextField fontSizeTF;
    private javax.swing.JList fontStyleList;
    private javax.swing.JTextField fontStyleTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel previewLabel;
    private javax.swing.JPanel previewPanel;
    // End of variables declaration//GEN-END:variables

    private void updatePreview() {
        int style = 0;
        if(fontStyleList.getSelectedValue() != null){
            if(fontStyleList.getSelectedValue().equals("Plain"))
                style = Font.PLAIN;
            else if(fontStyleList.getSelectedValue().equals("Bold"))
                style = Font.BOLD;
            else if(fontStyleList.getSelectedValue().equals("Italic"))
                style = Font.ITALIC;
            else if(fontStyleList.getSelectedValue().equals("Bold Italic"))
                style = Font.BOLD + Font.ITALIC;
        } else
            style = selectedFont.getStyle();
        int size = 8;
        try{
            size = Integer.parseInt(fontSizeTF.getText());
        }catch(NumberFormatException ne){
            size = selectedFont.getSize();
        }
        String n = fontNameList.getSelectedValue().toString();
        if(n == null || n.equals("")){
            n = selectedFont.getName();
        }
        Font f = new Font(n, style,size );

        previewLabel.setFont(f);
        selectedFont = f;
    }

}
