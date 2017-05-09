import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class invoiceDataModel extends AbstractTableModel {

    private int rowCount = 0;
    private int colCount = 0;
    ResultSet resultSet;

    public invoiceDataModel(ResultSet rs) {
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
    public boolean insertRow(int sid, int cid, double cidProfit, double storeProfit, double amountPaid, double balance, Date paymentDate) {

        try {
            //Move to insert row, insert the appropriate data in each column, insert the row, move cursor back to where it was before we started
            resultSet.moveToInsertRow();
            resultSet.updateInt(DB.SID_COL, sid);
            resultSet.updateInt(DB.CID_COL, cid);
            resultSet.updateDouble(DB.CIDPROFIT_COL, cidProfit);
            resultSet.updateDouble(DB.STOREPROFIT_COL, storeProfit);
            resultSet.updateDouble(DB.AMOUNTPAID_COL, amountPaid);
            resultSet.updateDouble(DB.BALANCE_COL, balance);
            paymentDate = new Date();
            resultSet.updateDate(DB.PAYMENTDATE_COL, (java.sql.Date) paymentDate);
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

    public boolean updateRow(int row, int sid, int cid, double cidProfit, double storeProfit, double amountPaid, double balance, Date paymentDate) {

        try {
            resultSet.moveToInsertRow();
            resultSet.updateInt(DB.SID_COL, sid);
            resultSet.updateInt(DB.CID_COL, cid);
            resultSet.updateDouble(DB.CIDPROFIT_COL, cidProfit);
            resultSet.updateDouble(DB.STOREPROFIT_COL, storeProfit);
            resultSet.updateDouble(DB.AMOUNTPAID_COL, amountPaid);
            resultSet.updateDouble(DB.BALANCE_COL, balance);
            paymentDate = new Date();
            resultSet.updateDate(DB.PAYMENTDATE_COL, (java.sql.Date) paymentDate);
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