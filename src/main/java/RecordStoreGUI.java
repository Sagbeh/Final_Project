/* This is the gui that runs the show.  A JTable is used to interface with the database.
* The Jbuttons, labels, and text fields are used throughout and change function depending on what menu item is selected.  More information is below*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class RecordStoreGUI extends JFrame implements WindowListener{

    private JMenuBar menuBar;
    private JPanel rootPanel;
    private JPanel tablePanel;
    private JTable resultsJTable;
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JPanel menuPanel;
    private JButton addButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton selectButton;
    private JCheckBox notificationCheckBox;
    private JRadioButton viewAllRecordsRadioButton;
    private JRadioButton viewBargainBinRadioButton;
    private JRadioButton viewDonationsRadioButton;

    //allows me to call query methods
    DB guiDB;

    //These functions determine what the buttons do
    int searchFunction;
    int addFunction;
    int updateFunction;
    int selectFunction;

    //global variables for invoice updates
    double cProfit = 0.4;
    double sProfit = 0.6;
    double amountPaid = 0.00;


    public RecordStoreGUI() {

        super("Record Store System");
        setContentPane(rootPanel);
        configureMenus();
        pack();
        addWindowListener(this);
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);
        text4.setVisible(false);
        text5.setVisible(false);
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        addButton.setVisible(false);
        searchButton.setVisible(false);
        updateButton.setVisible(false);
        selectButton.setVisible(false);
        notificationCheckBox.setVisible(false);
        viewAllRecordsRadioButton.setVisible(false);
        viewBargainBinRadioButton.setVisible(false);
        viewDonationsRadioButton.setVisible(false);
        ButtonGroup group = new ButtonGroup(); //creates a button group so only one radio button can be selected at a time
        group.add(viewAllRecordsRadioButton);
        group.add(viewBargainBinRadioButton);
        group.add(viewDonationsRadioButton);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        guiDB = new DB();


    }

    //Configures JMenu items and buttons
    private void configureMenus() {

        //Creates the menu
        menuBar = new JMenuBar();

        JMenu consignorsMenu = new JMenu("Consignors");
        JMenu recordsMenu = new JMenu("Records");
        JMenu salesMenu = new JMenu("Sales");
        JMenu invoicesMenu = new JMenu("Invoices");
        JMenu quitMenu = new JMenu("Close Program");
        JMenuItem searchConsignorsMenu = new JMenuItem("Search Consignors");
        JMenuItem addConsignorMenu = new JMenuItem("Add Consignor");
        JMenuItem searchRecordsMenu = new JMenuItem("Search Records");
        JMenuItem addNewRecordMenu = new JMenuItem("Add a new record");
        JMenuItem makeSaleMenu = new JMenuItem("Make a Sale");
        JMenuItem searchSalesMenu = new JMenuItem("Search Sales");
        JMenuItem payConsignorsMenu = new JMenuItem("Pay Consignor");
        JMenuItem viewPaymentsMenu = new JMenuItem("View Payments to Consignors");
        JMenuItem quit = new JMenuItem("Quit");


        menuPanel.add(menuBar, BorderLayout.WEST);

        consignorsMenu.add(searchConsignorsMenu);
        consignorsMenu.add(addConsignorMenu);
        recordsMenu.add(searchRecordsMenu);
        recordsMenu.add(addNewRecordMenu);
        salesMenu.add(makeSaleMenu);
        salesMenu.add(searchSalesMenu);
        invoicesMenu.add(payConsignorsMenu);
        invoicesMenu.add(viewPaymentsMenu);
        invoicesMenu.add(quit);
        quitMenu.add(quit);

        menuBar.add(consignorsMenu);
        menuBar.add(recordsMenu);
        menuBar.add(salesMenu);
        menuBar.add(invoicesMenu);
        menuBar.add(quitMenu);


        //This menu allows you to add a consignor
        addConsignorMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addFunction = 1;


                //Set's the tables model
                resultsJTable.setGridColor(Color.BLACK);
                resultsJTable.setModel(DB.cDM);
                String[] columnNames = {"CID", "Name", "Phone Number"};
                for (int x = 0; x < columnNames.length; x++) {
                    resultsJTable.getColumnModel().getColumn(x).setHeaderValue(columnNames[x]);
                }


                //Throughout the code I make certain gui items visible and invisible depending on the menu being used
                text1.setVisible(false);
                text2.setVisible(false);
                text3.setVisible(false);
                text4.setVisible(true);
                text5.setVisible(true);
                label1.setVisible(false);
                label2.setVisible(false);
                label3.setVisible(false);
                label4.setVisible(true);
                label5.setVisible(true);
                addButton.setVisible(true);
                searchButton.setVisible(false);
                updateButton.setVisible(false);
                selectButton.setVisible(false);
                notificationCheckBox.setVisible(false);
                viewAllRecordsRadioButton.setVisible(false);
                viewBargainBinRadioButton.setVisible(false);
                viewDonationsRadioButton.setVisible(false);


                label4.setText("Name: ");
                label5.setText("Phone Number: ");
                addButton.setText("Add Consignor");


            }
        });

        //allows you to search for consignors
        searchConsignorsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                searchFunction = 1;

                resultsJTable.setGridColor(Color.BLACK);
                resultsJTable.setModel(DB.cDM);
                String[] columnNames = {"CID", "Name", "Phone Number"};
                for (int x = 0; x < columnNames.length; x++) {
                    resultsJTable.getColumnModel().getColumn(x).setHeaderValue(columnNames[x]);
                }

                text1.setVisible(false);
                text2.setVisible(false);
                text3.setVisible(false);
                text4.setVisible(false);
                text5.setVisible(true);
                label1.setVisible(false);
                label2.setVisible(false);
                label3.setVisible(false);
                label4.setVisible(false);
                label5.setVisible(true);
                addButton.setVisible(false);
                searchButton.setVisible(true);
                updateButton.setVisible(false);
                selectButton.setVisible(false);
                notificationCheckBox.setVisible(false);
                viewAllRecordsRadioButton.setVisible(false);
                viewBargainBinRadioButton.setVisible(false);
                viewDonationsRadioButton.setVisible(false);


                label5.setText("Search by name or phone number");
                searchButton.setText("Search");


            }
        });


        //allows you to search for records, and mark records as notified
        searchRecordsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateFunction = 1;
                searchFunction = 2;

                resultsJTable.setGridColor(Color.BLACK);
                resultsJTable.setModel(DB.rDM);
                String[] columnNames = {"RID", "CID", "Artist", "Title", "Sales Price", "Status", "Notified", "Sold", "Date Added"};
                for (int x = 0; x < columnNames.length; x++) {
                    resultsJTable.getColumnModel().getColumn(x).setHeaderValue(columnNames[x]);
                }

                text1.setVisible(false);
                text2.setVisible(false);
                text3.setVisible(false);
                text4.setVisible(false);
                text5.setVisible(true);
                label1.setVisible(false);
                label2.setVisible(false);
                label3.setVisible(false);
                label4.setVisible(false);
                label5.setVisible(true);
                addButton.setVisible(false);
                searchButton.setVisible(true);
                updateButton.setVisible(true);
                selectButton.setVisible(false);
                notificationCheckBox.setVisible(true);
                viewAllRecordsRadioButton.setVisible(true);
                viewBargainBinRadioButton.setVisible(true);
                viewDonationsRadioButton.setVisible(true);

                notificationCheckBox.setText("Notified?");
                label5.setText("Search by Artist or Title");
                searchButton.setText("Search");
                updateButton.setText("Update Record as Notified");


            }
        });

        //Menu used to add a new record
        addNewRecordMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addFunction = 2;
                selectFunction = 1;
                searchFunction = 1;

                resultsJTable.setGridColor(Color.BLACK);
                resultsJTable.setModel(DB.cDM);
                String[] columnNames = {"CID", "Name", "Phone Number"};
                for (int x = 0; x < columnNames.length; x++) {
                    resultsJTable.getColumnModel().getColumn(x).setHeaderValue(columnNames[x]);

                }

                text1.setVisible(true);
                text2.setVisible(true);
                text3.setVisible(true);
                text4.setVisible(true);
                text5.setVisible(true);
                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                label4.setVisible(true);
                label5.setVisible(true);
                addButton.setVisible(true);
                searchButton.setVisible(true);
                updateButton.setVisible(false);
                selectButton.setVisible(true);
                notificationCheckBox.setVisible(false);
                viewAllRecordsRadioButton.setVisible(false);
                viewBargainBinRadioButton.setVisible(false);
                viewDonationsRadioButton.setVisible(false);



                text1.setEditable(false);
                label1.setText("CID: ");
                label2.setText("Artist: ");
                label3.setText("Title: ");
                label4.setText("Sale Price: ");
                label5.setText("Search for consignor by name or phone number");
                searchButton.setText("Search Consignor");
                addButton.setText("Add Record");
                selectButton.setText("Select Consignor");


            }
        });

        //This is where you can sell a record

        makeSaleMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                searchFunction = 6;
                updateFunction = 2;

                resultsJTable.setGridColor(Color.BLACK);
                resultsJTable.setModel(DB.rDM);
                String[] columnNames = {"RID", "CID", "Artist", "Title", "Sales Price", "Status", "Notified", "Sold", "Date Added"};
                for (int x = 0; x < columnNames.length; x++) {
                    resultsJTable.getColumnModel().getColumn(x).setHeaderValue(columnNames[x]);
                }

                text1.setVisible(false);
                text2.setVisible(false);
                text3.setVisible(false);
                text4.setVisible(false);
                text5.setVisible(true);
                label1.setVisible(false);
                label2.setVisible(false);
                label3.setVisible(false);
                label4.setVisible(false);
                label5.setVisible(true);
                addButton.setVisible(false);
                searchButton.setVisible(true);
                updateButton.setVisible(true);
                selectButton.setVisible(false);
                notificationCheckBox.setVisible(false);
                viewAllRecordsRadioButton.setVisible(false);
                viewBargainBinRadioButton.setVisible(false);
                viewDonationsRadioButton.setVisible(false);

                label5.setText("Search by Artist or Title");
                searchButton.setText("Search");
                updateButton.setText("Sell Record");

            }
        });

        //sales can be viewed here
        searchSalesMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                searchFunction = 3;

                resultsJTable.setGridColor(Color.BLACK);
                resultsJTable.setModel(DB.sDM);
                String[] columnNames = {"SID", "RID", "Sold Price", "Sold Date"};
                for (int x = 0; x < columnNames.length; x++) {
                    resultsJTable.getColumnModel().getColumn(x).setHeaderValue(columnNames[x]);
                }

                text1.setVisible(false);
                text2.setVisible(false);
                text3.setVisible(false);
                text4.setVisible(false);
                text5.setVisible(true);
                label1.setVisible(false);
                label2.setVisible(false);
                label3.setVisible(false);
                label4.setVisible(false);
                label5.setVisible(true);
                addButton.setVisible(false);
                searchButton.setVisible(true);
                updateButton.setVisible(false);
                selectButton.setVisible(false);
                notificationCheckBox.setVisible(false);
                viewAllRecordsRadioButton.setVisible(false);
                viewBargainBinRadioButton.setVisible(false);
                viewDonationsRadioButton.setVisible(false);

                label5.setText("Search by Artist or Title");
                searchButton.setText("Search");


            }
        });


        //This is where you pay consignors
        payConsignorsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateFunction = 3;
                searchFunction = 4;
                selectFunction = 2;

                resultsJTable.setGridColor(Color.BLACK);
                resultsJTable.setModel(DB.iDM);
                String[] columnNames = {"IID", "SID", "CID", "CID Profit", "Store Profit", "Amount Paid", "Balance", "Invoice Date"};
                for (int x = 0; x < columnNames.length; x++) {
                    resultsJTable.getColumnModel().getColumn(x).setHeaderValue(columnNames[x]);
                }

                text1.setVisible(false);
                text2.setVisible(false);
                text3.setVisible(false);
                text4.setVisible(true);
                text5.setVisible(true);
                label1.setVisible(false);
                label2.setVisible(false);
                label3.setVisible(false);
                label4.setVisible(true);
                label5.setVisible(true);
                addButton.setVisible(false);
                searchButton.setVisible(true);
                updateButton.setVisible(true);
                selectButton.setVisible(true);
                notificationCheckBox.setVisible(true);
                viewAllRecordsRadioButton.setVisible(false);
                viewBargainBinRadioButton.setVisible(false);
                viewDonationsRadioButton.setVisible(false);

                notificationCheckBox.setText("Paid?");
                label4.setText("SID: ");
                label5.setText("Search invoices by SID: ");
                searchButton.setText("Search");
                selectButton.setText("Select Sale");
                updateButton.setText("Pay Consignor");


            }
        });

        //Here you can see how much each consignor has been paid
        viewPaymentsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                resultsJTable.setGridColor(Color.BLACK);
                resultsJTable.setModel(DB.iDM);
                String[] columnNames = {"IID", "SID", "CID", "CID Profit", "Store Profit", "Amount Paid", "Balance", "Invoice Date"};
                for (int x = 0; x < columnNames.length; x++) {
                    resultsJTable.getColumnModel().getColumn(x).setHeaderValue(columnNames[x]);
                }

                updateFunction = 4;
                searchFunction = 5;


                text1.setVisible(false);
                text2.setVisible(false);
                text3.setVisible(false);
                text4.setVisible(false);
                text5.setVisible(true);
                label1.setVisible(false);
                label2.setVisible(false);
                label3.setVisible(false);
                label4.setVisible(false);
                label5.setVisible(true);
                addButton.setVisible(false);
                searchButton.setVisible(true);
                updateButton.setVisible(true);
                selectButton.setVisible(false);
                notificationCheckBox.setVisible(false);
                viewAllRecordsRadioButton.setVisible(false);
                viewBargainBinRadioButton.setVisible(false);
                viewDonationsRadioButton.setVisible(false);


                label5.setText("Search by CID");
                searchButton.setText("Search");
                updateButton.setText("View Total for Consignor");

            }
        });

        //quits the program
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quit = JOptionPane.showConfirmDialog(RecordStoreGUI.this, "Quit Program", "Quit", JOptionPane.OK_CANCEL_OPTION);
                if (quit == JOptionPane.OK_OPTION) {
                    DB.shutdown();
                    System.exit(0);
                }
            }
        });

        //switch block changes button functionality throughout the program
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (searchFunction) {
                    case 1:
                        String search = text5.getText();
                        DB.searchConsignors(search);
                        String[] consignorsColumns = {"CID", "Name", "Phone Number"};
                        for (int x = 0; x < consignorsColumns.length; x++) {
                            resultsJTable.getColumnModel().getColumn(x).setHeaderValue(consignorsColumns[x]);
                        }
                        break;
                    case 2:
                        search = text5.getText();
                        if (viewAllRecordsRadioButton.isSelected()) {
                            DB.searchAllRecords(search);
                        }
                        if (viewBargainBinRadioButton.isSelected()) {
                            DB.searchBargainRecords(search);
                        }
                        if (viewDonationsRadioButton.isSelected()) {
                            DB.searchDonationRecords(search);
                        }


                        break;
                    case 3:
                        search = text5.getText();
                        DB.searchSales(search);
                        String[] salesColumns = {"SID", "RID", "Sold Date", "Sold Price"};
                        for (int x = 0; x < salesColumns.length; x++) {
                            resultsJTable.getColumnModel().getColumn(x).setHeaderValue(salesColumns[x]);
                        }
                        break;
                    case 4:
                        search = text5.getText();
                        DB.searchInvoicesBySID(search);
                        String[] invoicesColumns = {"IID", "SID", "CID", "CID Profit", "Store Profit", "Amount Paid", "Balance", "Invoice Date"};
                        for (int x = 0; x < invoicesColumns.length; x++) {
                            resultsJTable.getColumnModel().getColumn(x).setHeaderValue(invoicesColumns[x]);
                        }
                        break;
                    case 5:
                        search = text5.getText();
                        DB.searchInvoicesByCID(search);
                        resultsJTable.setGridColor(Color.BLACK);
                        resultsJTable.setModel(DB.iDM);
                        String[] columnNames = {"IID", "SID", "CID", "CID Profit", "Store Profit", "Amount Paid", "Balance", "Invoice Date"};
                        for (int x = 0; x < columnNames.length; x++) {
                            resultsJTable.getColumnModel().getColumn(x).setHeaderValue(columnNames[x]);
                        }
                        break;
                    case 6:
                        search = text5.getText();
                        DB.searchRecordsForSale(search);

                        break;
                }

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (addFunction) {
                    case 1:
                        String name = text4.getText();
                        String phone = text5.getText();

                        if (name.isEmpty()) {
                            JOptionPane.showMessageDialog(RecordStoreGUI.this, "Enter a name");
                                return;
                        }

                        if (phone.isEmpty()){
                                JOptionPane.showMessageDialog(RecordStoreGUI.this, "Enter a phone number");
                                return;
                            }

                        DB.cDM.insertRow(name, phone);
                        JOptionPane.showMessageDialog(RecordStoreGUI.this, "Consignor Added!");

                        break;
                    case 2:

                        try {
                            int cid = Integer.parseInt(text1.getText());

                            String artist = text2.getText();
                            String title = text3.getText();
                            Double salesPrice = Double.parseDouble(text4.getText());
                            int status = 1;
                            String notified = "N";
                            String sold = "N";
                            java.util.Date date = new java.util.Date();
                            Date dateAdded = new java.sql.Date(date.getTime());

                            if (artist.isEmpty()) {
                                JOptionPane.showMessageDialog(RecordStoreGUI.this, "Enter a artist");
                                return;
                            }
                            if (title.isEmpty()) {
                                JOptionPane.showMessageDialog(RecordStoreGUI.this, "Enter a title");
                                return;
                            }

                            if (text4.getText().isEmpty()) {
                                JOptionPane.showMessageDialog(RecordStoreGUI.this, "Enter a sales price");
                                return;

                            }

                            DB.rDM.insertRow(cid, artist, title, salesPrice, status, notified, sold, (java.sql.Date) dateAdded);
                            JOptionPane.showMessageDialog(RecordStoreGUI.this, "Record Added!");

                            break;
                        }catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(RecordStoreGUI.this, "CID and Sales Price must be numbers. \n" +
                                "Sales Price must be in this format: 00.00 \n" +
                                "CID can be searched and added by selecting \"Select Consignor\". \n" +
                                "If you don't see a CID from your search, you can add a consignor from the Consignor menu.");
                        return;


                    }
                }

            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                switch (updateFunction) {

                        case 1:
                            if (notificationCheckBox.isSelected()) {
                                DB.rDM.notify(resultsJTable.getSelectedRow(), "Y");
                            } else {
                                JOptionPane.showMessageDialog(RecordStoreGUI.this, "Select a record to update");
                                return;
                            }
                            break;
                        case 2:
                            int row = resultsJTable.getSelectedRow();
                            if (row == -1) {

                                JOptionPane.showMessageDialog(RecordStoreGUI.this, "Select a record to sell");
                                return;
                            }

                            //This section is special.  When a record is sold, a sales record and invoice record are created, and the record is marked as sold
                            java.util.Date date = new java.util.Date();
                            Date soldDate = new java.sql.Date(date.getTime());

                            DB.sDM.insertRow(Integer.parseInt(DB.rDM.getValueAt(row, 0).toString()), Double.parseDouble(DB.rDM.getValueAt(row, 4).toString()), (java.sql.Date) soldDate);
                            DB.iDM.insertRow(guiDB.getSID(Integer.parseInt(DB.rDM.getValueAt(row, 0).toString())), Integer.parseInt(DB.rDM.getValueAt(row, 1).toString()),
                                    (Double.parseDouble(DB.rDM.getValueAt(row, 4).toString())) * cProfit, (Double.parseDouble(DB.rDM.getValueAt(row, 4).toString())) * sProfit,
                                    amountPaid, (Double.parseDouble(DB.rDM.getValueAt(row, 4).toString())) * cProfit, (java.sql.Date) soldDate);
                            DB.rDM.sold(row);

                            break;
                        case 3:
                            row = resultsJTable.getSelectedRow();

                            if (notificationCheckBox.isSelected()) {
                                guiDB.iDM.updateRow(row, Double.parseDouble(DB.iDM.getValueAt(row, 3).toString()), 0.00);
                            } else {
                                JOptionPane.showMessageDialog(RecordStoreGUI.this, "Select an invoice to update");
                                return;
                            }
                        case 4:
                            int search = Integer.parseInt(text5.getText());
                            DB.runTotals(search);
                            String[] invoicesColumns = {"CID", "Total Paid"};
                            for (int x = 0; x < invoicesColumns.length; x++) {
                                resultsJTable.getColumnModel().getColumn(x).setHeaderValue(invoicesColumns[x]);

                            }

                    }


                    } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(RecordStoreGUI.this, "Invoice is already paid");
                    return;
                }

            }

        });

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultsJTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(RecordStoreGUI.this, "Select a row");
                    return;
                }
                switch (selectFunction) {
                    case 1:
                        getCID(selectedRow);

                        break;
                    case 2:
                        getSalesID(selectedRow);

                        break;

                }

            }

        });

    }

    //getCID and getSalesID are used for the payment menus to populate data
    private void getCID(int cid) {
        text1.setText(DB.cDM.getValueAt(cid, 0).toString());
    }

    private void getSalesID(int sid) {
        text4.setText(DB.sDM.getValueAt(sid, 0).toString());
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
