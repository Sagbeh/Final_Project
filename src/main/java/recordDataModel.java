import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class recordDataModel extends AbstractTableModel {

    private int rowCount = 0;
    private int colCount = 0;
    ResultSet resultSet;

    public recordDataModel(ResultSet rs) {
        this.resultSet = rs;
        setup();
    }

    private void setup() {

        countRows();

        try {
            colCount = resultSet.getMetaData().getColumnCount();

        } catch (SQLException se) {
            System.out.println("Error counting columns" + se);
        }

    }

    public void updateResultSet(ResultSet newRS) {
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
    public int getColumnCount() {
        return colCount;
    }

    @Override
    public Object getValueAt(int row, int col) {
        try {
            //  System.out.println("get value at, row = " +row);
            resultSet.absolute(row + 1);
            Object o = resultSet.getObject(col + 1);
            return o.toString();
        } catch (SQLException se) {
            System.out.println(se);
            //se.printStackTrace();
            return se.toString();

        }
    }

    //returns true if successful, false if error occurs
    public boolean insertRow(int cid, String artist, String title, double salesPrice, int status, String notified, int sold, Date dateAdded) {

        try {
            //Move to insert row, insert the appropriate data in each column, insert the row, move cursor back to where it was before we started
            resultSet.moveToInsertRow();
            resultSet.updateInt(DB.CID_COL, cid);
            resultSet.updateString(DB.ARTIST_COL, artist);
            resultSet.updateString(DB.TITLE_COL, title);
            resultSet.updateDouble(DB.SALESPRICE_COL, salesPrice);
            resultSet.updateInt(DB.STATUS_COL, status);
            resultSet.updateString(DB.NOTIFIED_COL, notified);
            resultSet.updateInt(DB.SOLD_COL, sold);
            dateAdded = new Date();
            resultSet.updateDate(DB.DATEADDED_COL, (java.sql.Date) dateAdded);
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

    public boolean updateRow(int row, int cid, String artist, String title, double salesPrice, int status, String notified, int sold) {

        try {
            resultSet.absolute(row+1);
            resultSet.moveToInsertRow();
            resultSet.updateInt(DB.CID_COL, cid);
            resultSet.updateString(DB.ARTIST_COL, artist);
            resultSet.updateString(DB.TITLE_COL, title);
            resultSet.updateDouble(DB.SALESPRICE_COL, salesPrice);
            resultSet.updateInt(DB.STATUS_COL, status);
            resultSet.updateString(DB.NOTIFIED_COL, notified);
            resultSet.updateInt(DB.SOLD_COL, sold);
            resultSet.updateRow();
            fireTableDataChanged();
            return true;

        } catch (Exception e) {
            System.out.println("Error updating row");
            System.out.println(e);
            return false;
        }

    }

}