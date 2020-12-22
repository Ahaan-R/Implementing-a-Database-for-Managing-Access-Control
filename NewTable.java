import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class NewTable extends JFrame {

    private JPanel contentPane;
    private JTextField ID;
    private JTextField TableName;
    private JTextField Description;




    /**
     *
     */
    //private static Logger log = LoggerFactory.getLogger(NewBook.class);
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // log.info("Executing Program");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NewTable frame = new NewTable();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public NewTable() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 468, 412);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Please enter the following fields to insert the data of the new role.The Roles are ceo,guest,employee,teamlead,manager  " +
                ":");
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 13));
        lblNewLabel.setBounds(30, 11, 415, 46);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("ID");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1.setBounds(30, 61, 86, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblSubject = new JLabel("TableName");
        lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblSubject.setBounds(30, 98, 86, 14);
        contentPane.add(lblSubject);







        ID = new JTextField();
        ID.setBounds(173, 58, 240, 20);
        contentPane.add(ID);
        ID.setColumns(10);

        TableName = new JTextField();
        TableName.setBounds(173, 95, 240, 20);
        contentPane.add(TableName);
        TableName.setColumns(10);







        JButton btnNewButton = new JButton("SUBMIT");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createNewRole(ID,TableName);
                System.exit(0);
            }
        });
        btnNewButton.setBounds(325, 339, 89, 23);
        contentPane.add(btnNewButton);
    }
    private void createNewRole(final JTextField ID, final JTextField TableName) {
        Boolean in_lib;
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/security", "root", "root");
            PreparedStatement prep = conn.prepareStatement("insert into dbtable values (?,?)");) {
            //log.info("Connected");


            prep.setString(1, ID.getText());
            prep.setString(2,  TableName.getText());



            int res = prep.executeUpdate();

            if(res == 1 ) {
                JOptionPane.showMessageDialog(null, "table added for User  successfully");
            }
            //log.info("Inserted into database");
        }catch(java.sql.SQLIntegrityConstraintViolationException userExsistException) {
            JOptionPane.showMessageDialog(null, "table wasn't added.Please try again.");
        }
        catch (Exception exception) {
            //log.error("Failed to insert into table", exception);

        }
    }
}