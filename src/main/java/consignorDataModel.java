//This is the consignorDataModel.  Handles the consignor table interfacing with the JTable.

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class consignorDataModel extends AbstractTableModel {

    private int rowCount = 0;
    private int colCount = 0;
    ResultSet resultSet;

    public consignorDataModel(ResultSet rs) {
        this.resultSet = rs;
        setup();
    }

    private void setup(){

        countRows();

        try{
            colCount = resultSet.getMetaData().getColumnCount();

        } catch (SQLException se) {
            System.out.println("Error counting columns" + se);
        }

    }

    public void updateResultSet(ResultSet newRS){
        resultSet = newRS;
        setup();
        fireTableDataChanged();
    }


    private void countRows() {
        rowCount = 0;
        try {
            //Move cursor to the start...
            resultSet.beforeFirst();
            // next() method moves the cursor forward one row and returns true if there is another row ahead
            while (resultSet.next()) {
                rowCount++;

            }
            resultSet.beforeFirst();

        } catch (SQLException se) {
            System.out.println("Error counting rows " + se);
        }

    }
    @Override
    public int getRowCount() {
        countRows();
        return rowCount;
    }

    @Override
    public int getColumnCount(){
        return colCount;
    }

    @Override
    public Object getValueAt(int row, int col){
        try{
            //  System.out.println("get value at, row = " +row);
            resultSet.absolute(row+1);
            Object o = resultSet.getObject(col+1);
            return o.toString();
        }catch (SQLException se) {
            System.out.println(se);
            //se.printStackTrace();
            return se.toString();

        }
    }

    //returns true if successful, false if error occurs
    public boolean insertRow(String name, String phoneNumber) {

        try {
            //Move to insert row, insert the appropriate data in each column, insert the row, move cursor back to where it was before we started
            resultSet.moveToInsertRow();
            resultSet.updateString(DB.NAME_COL, name);
            resultSet.updateString(DB.PHONE_COL, phoneNumber);
            resultSet.insertRow();
            resultSet.moveToCurrentRow();
            fireTableDataChanged();
            //This change goes to DB but is *not* reflected in this result set
            //So need to close and re-open result set to see latest data
            //Return true to the calling method so we know that the ResultSet
            //was successfully updated, and it can request a new ResultSet for this tablemodel.
            return true;

        } catch (SQLException e) {
            System.out.println("Error adding row");
            System.out.println(e);
            return false;
        }

    }


}
