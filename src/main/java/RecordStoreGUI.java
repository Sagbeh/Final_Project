import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    DB guiDB;


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
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        guiDB = new DB();

        resultsJTable.setGridColor(Color.BLACK);
        resultsJTable.setModel(DB.cDM);
        String[] columnNames = {"CID", "Name", "Phone Number"};
        for(int i=0;i<columnNames.length;i++){
            resultsJTable.getColumnModel().getColumn(i).setHeaderValue(columnNames[i]);
        }

    }

    private void configureMenus() {

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

        addConsignorMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

                label4.setText("Name: ");
                label5.setText("Phone Number: ");
                addButton.setText("Add Consignor");


            }
        });

        searchConsignorsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

                label5.setText("Search by name or phone number");
                searchButton.setText("Search");


            }
        });

        searchRecordsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

                notificationCheckBox.setText("Notified?");
                label5.setText("Search by Artist or Title");
                searchButton.setText("Search");
                updateButton.setText("Update Record as Notified");


            }
        });

        addNewRecordMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        makeSaleMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                selectButton.setVisible(true);
                notificationCheckBox.setVisible(false);

                label5.setText("Search by Artist or Title");
                searchButton.setText("Search");
                selectButton.setText("Sell Record");

            }
        });

        searchSalesMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

                label5.setText("Search by Artist or Title");
                searchButton.setText("Search");


            }
        });

        payConsignorsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

                notificationCheckBox.setText("Paid?");
                label4.setText("SID: ");
                label5.setText("Search invoices by SID: ");
                searchButton.setText("Search");
                selectButton.setText("Select Sale");
                updateButton.setText("Pay Consignor");


            }
        });

        viewPaymentsMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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


                label5.setText("Search by CID");
                searchButton.setText("Search");
                updateButton.setText("View Total for Consignor");

            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quit = JOptionPane.showConfirmDialog(RecordStoreGUI.this, "Quit Program","Quit", JOptionPane.OK_CANCEL_OPTION);
                if (quit == JOptionPane.OK_OPTION) {
                    DB.shutdown();
                    System.exit(0);
                }
            }
        });



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
