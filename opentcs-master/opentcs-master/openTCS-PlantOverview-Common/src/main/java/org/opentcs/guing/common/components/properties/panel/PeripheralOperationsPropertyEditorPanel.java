/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing.common.components.properties.panel;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.requireNonNull;
import javax.inject.Inject;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import org.opentcs.guing.base.components.properties.type.PeripheralOperationsProperty;
import org.opentcs.guing.base.components.properties.type.Property;
import org.opentcs.guing.base.model.PeripheralOperationModel;
import org.opentcs.guing.common.components.dialogs.DetailsDialogContent;
import org.opentcs.guing.common.components.dialogs.StandardDetailsDialog;
import org.opentcs.guing.common.persistence.ModelManager;
import org.opentcs.guing.common.util.I18nPlantOverview;
import org.opentcs.thirdparty.guing.common.jhotdraw.util.ResourceBundleUtil;

/**
 * User interface to edit a peripheral operations property.
 *
 * @author Leonard Schüngel (Fraunhofer IML)
 */
public class PeripheralOperationsPropertyEditorPanel
    extends JPanel
    implements DetailsDialogContent {

  /**
   * The bundle to be used.
   */
  private final ResourceBundleUtil bundle
      = ResourceBundleUtil.getBundle(I18nPlantOverview.PROPERTIES_PATH);
  /**
   * Manager of the system model.
   */
  private final ModelManager modelManager;
  /**
   * The property to edit.
   */
  private PeripheralOperationsProperty fProperty;

  /**
   * Creates a new instance.
   *
   * @param modelManager Manages the system model.
   */
  @Inject
  public PeripheralOperationsPropertyEditorPanel(ModelManager modelManager) {
    this.modelManager = requireNonNull(modelManager, "modelManager");

    initComponents();

    setPreferredSize(new Dimension(600, 250));

    itemsTable.setModel(new ItemsTableModel());
  }

  @Override
  public void setProperty(Property property) {
    fProperty = (PeripheralOperationsProperty) property;
    ((ItemsTableModel) itemsTable.getModel()).setValues(fProperty.getValue());
  }

  @Override
  public void updateValues() {
    fProperty.setValue(((ItemsTableModel) itemsTable.getModel()).getValues());
  }

  @Override
  public Property getProperty() {
    return fProperty;
  }

  @Override
  public String getTitle() {
    return bundle.getString("peripheralOperationsPropertyEditorPanel.title");
  }

  /**
   * Edits the selected value.
   */
  protected void edit() {
    int selectedRow = itemsTable.getSelectedRow();
    if (selectedRow == -1) {
      return;
    }
    PeripheralOperationModel selectedModel = ((ItemsTableModel) itemsTable.getModel())
        .getValues().get(selectedRow);

    JDialog parent = (JDialog) getTopLevelAncestor();
    PeripheralOperationPanel content = new PeripheralOperationPanel(modelManager.getModel());
    content.setPeripheralOpartionModel(selectedModel);
    StandardDetailsDialog dialog = new StandardDetailsDialog(parent, true, content);
    dialog.setLocationRelativeTo(parent);
    dialog.setVisible(true);

    if (dialog.getReturnStatus() == StandardDetailsDialog.RET_OK
        && content.getPeripheralOperationModel().isPresent()) {
      ((ItemsTableModel) itemsTable.getModel())
          .getValues().set(selectedRow, content.getPeripheralOperationModel().get());
      ((ItemsTableModel) itemsTable.getModel()).fireTableRowsUpdated(selectedRow, selectedRow);
    }
  }

  /**
   * Adds a new entry.
   */
  protected void add() {
    JDialog parent = (JDialog) getTopLevelAncestor();
    PeripheralOperationPanel content = new PeripheralOperationPanel(modelManager.getModel());
    StandardDetailsDialog dialog = new StandardDetailsDialog(parent, true, content);
    dialog.setLocationRelativeTo(parent);
    dialog.setVisible(true);

    if (dialog.getReturnStatus() == StandardDetailsDialog.RET_OK
        && content.getPeripheralOperationModel().isPresent()) {
      ItemsTableModel model = (ItemsTableModel) itemsTable.getModel();
      List<PeripheralOperationModel> values = model.getValues();
      values.add(content.getPeripheralOperationModel().get());
      model.setValues(values);
      model.fireTableRowsInserted(values.size() - 1, values.size() - 1);
    }
  }

  // CHECKSTYLE:OFF
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    itemsScrollPane = new javax.swing.JScrollPane();
    itemsTable = new javax.swing.JTable();
    controlPanel = new javax.swing.JPanel();
    addButton = new javax.swing.JButton();
    editButton = new javax.swing.JButton();
    removeButton = new javax.swing.JButton();
    rigidArea = new javax.swing.JPanel();
    moveUpButton = new javax.swing.JButton();
    moveDownButton = new javax.swing.JButton();

    setLayout(new java.awt.BorderLayout());

    itemsTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {},
        {},
        {},
        {}
      },
      new String [] {

      }
    ));
    itemsScrollPane.setViewportView(itemsTable);

    add(itemsScrollPane, java.awt.BorderLayout.CENTER);

    controlPanel.setLayout(new java.awt.GridBagLayout());

    addButton.setFont(addButton.getFont());
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/org/opentcs/plantoverview/panels/propertyEditing"); // NOI18N
    addButton.setText(bundle.getString("peripheralOperationsPropertyEditorPanel.button_add.text")); // NOI18N
    addButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addButtonActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
    controlPanel.add(addButton, gridBagConstraints);

    editButton.setFont(editButton.getFont());
    editButton.setText(bundle.getString("peripheralOperationsPropertyEditorPanel.button_edit.text")); // NOI18N
    editButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        editButtonActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 0);
    controlPanel.add(editButton, gridBagConstraints);

    removeButton.setFont(removeButton.getFont());
    removeButton.setText(bundle.getString("peripheralOperationsPropertyEditorPanel.button_remove.text")); // NOI18N
    removeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        removeButtonActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
    controlPanel.add(removeButton, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.weighty = 1.0;
    controlPanel.add(rigidArea, gridBagConstraints);

    moveUpButton.setFont(moveUpButton.getFont());
    moveUpButton.setText(bundle.getString("peripheralOperationsPropertyEditorPanel.button_up.text")); // NOI18N
    moveUpButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        moveUpButtonActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 0);
    controlPanel.add(moveUpButton, gridBagConstraints);

    moveDownButton.setFont(moveDownButton.getFont());
    moveDownButton.setText(bundle.getString("peripheralOperationsPropertyEditorPanell.button_down.text")); // NOI18N
    moveDownButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        moveDownButtonActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
    controlPanel.add(moveDownButton, gridBagConstraints);

    add(controlPanel, java.awt.BorderLayout.EAST);
  }// </editor-fold>//GEN-END:initComponents

  private void moveDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownButtonActionPerformed
    int selectedRow = itemsTable.getSelectedRow();
    if (selectedRow == -1 || selectedRow == itemsTable.getModel().getRowCount() - 1) {
      return;
    }

    ItemsTableModel model = (ItemsTableModel) itemsTable.getModel();
    List<PeripheralOperationModel> values = model.getValues();
    PeripheralOperationModel value = values.get(selectedRow);
    values.remove(selectedRow);
    values.add(selectedRow + 1, value);
    model.setValues(values);
    model.fireTableRowsUpdated(selectedRow, selectedRow + 1);
    itemsTable.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
  }//GEN-LAST:event_moveDownButtonActionPerformed

  private void moveUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpButtonActionPerformed
    int selectedRow = itemsTable.getSelectedRow();
    if (selectedRow == -1 || selectedRow == 0) {
      return;
    }

    ItemsTableModel model = (ItemsTableModel) itemsTable.getModel();
    List<PeripheralOperationModel> values = model.getValues();
    PeripheralOperationModel value = values.get(selectedRow);
    values.remove(selectedRow);
    values.add(selectedRow - 1, value);
    model.setValues(values);
    model.fireTableRowsUpdated(selectedRow - 1, selectedRow);
    itemsTable.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
  }//GEN-LAST:event_moveUpButtonActionPerformed

  private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
    int selectedRow = itemsTable.getSelectedRow();
    if (selectedRow == -1) {
      return;
    }

    ItemsTableModel model = (ItemsTableModel) itemsTable.getModel();
    List<PeripheralOperationModel> values = model.getValues();
    values.remove(selectedRow);
    model.setValues(values);
    model.fireTableRowsDeleted(selectedRow, selectedRow);
  }//GEN-LAST:event_removeButtonActionPerformed

  private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
    edit();
  }//GEN-LAST:event_editButtonActionPerformed

  private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
    add();
  }//GEN-LAST:event_addButtonActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addButton;
  private javax.swing.JPanel controlPanel;
  private javax.swing.JButton editButton;
  private javax.swing.JScrollPane itemsScrollPane;
  private javax.swing.JTable itemsTable;
  private javax.swing.JButton moveDownButton;
  private javax.swing.JButton moveUpButton;
  private javax.swing.JButton removeButton;
  private javax.swing.JPanel rigidArea;
  // End of variables declaration//GEN-END:variables
  // CHECKSTYLE:ON

  protected class ItemsTableModel
      extends AbstractTableModel {

    /**
     * Column classes.
     */
    private final Class<?>[] COLUMN_CLASSES = new Class<?>[]{
      String.class,
      String.class,
      String.class,
      Boolean.class
    };

    /**
     * The column names.
     */
    private final String[] COLUMN_NAMES = new String[]{
      bundle.getString("peripheralOperationsPropertyEditorPanel.table_resources.column_location.headerText"),
      bundle.getString("peripheralOperationsPropertyEditorPanel.table_resources.column_operation.headerText"),
      bundle.getString("peripheralOperationsPropertyEditorPanel.table_resources.column_trigger.headerText"),
      bundle.getString("peripheralOperationsPropertyEditorPanel.table_resources.column_completion.headerText")
    };

    private final int COLUMN_LOCATION = 0;
    private final int COLUMN_OPERATION = 1;
    private final int COLUMN_TRIGGER = 2;
    private final int COLUMN_COMPLETION_REQUIRED = 3;

    /**
     * Values in this model.
     */
    private List<PeripheralOperationModel> values = new ArrayList<>();

    @Override
    public Class<?> getColumnClass(int columnIndex) {
      return COLUMN_CLASSES[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
      return COLUMN_NAMES[columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
      return false;
    }

    @Override
    public int getRowCount() {
      return values.size();
    }

    @Override
    public int getColumnCount() {
      return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      if (rowIndex < 0 || rowIndex >= getRowCount()) {
        return null;
      }
      PeripheralOperationModel entry = values.get(rowIndex);
      switch (columnIndex) {
        case COLUMN_LOCATION:
          return entry.getLocationName();
        case COLUMN_OPERATION:
          return entry.getOperation();
        case COLUMN_TRIGGER:
          return entry.getExecutionTrigger().name();
        case COLUMN_COMPLETION_REQUIRED:
          return entry.isCompletionRequired();
        default:
          throw new IllegalArgumentException("Invalid column index: " + columnIndex);
      }
    }

    public void setValues(List<PeripheralOperationModel> values) {
      this.values = values;
    }

    public List<PeripheralOperationModel> getValues() {
      return this.values;
    }

  }
}
