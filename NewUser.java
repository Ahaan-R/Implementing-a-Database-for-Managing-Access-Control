import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class NewUser extends JFrame {

    private JPanel contentPane;
    private JTextField ID;
    private JTextField Initial;
    private JTextField LastName;
    private JTextField Phone;



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
                    NewBook frame = new NewBook();
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
    public NewUser() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 468, 412);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Please enter the following fields to insert the data of the new user" +
                ":");
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 13));
        lblNewLabel.setBounds(30, 11, 415, 46);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("ID");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1.setBounds(30, 61, 86, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblSubject = new JLabel("Initial");
        lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblSubject.setBounds(30, 98, 86, 14);
        contentPane.add(lblSubject);

        JLabel lblTitle = new JLabel("LastName");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblTitle.setBounds(30, 143, 86, 14);
        contentPane.add(lblTitle);

        JLabel lblBind = new JLabel("Phone");
        lblBind.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblBind.setBounds(30, 179, 86, 14);
        contentPane.add(lblBind);



        ID = new JTextField();
        ID.setBounds(173, 58, 240, 20);
        contentPane.add(ID);
        ID.setColumns(10);

        Initial = new JTextField();
        Initial.setBounds(173, 95, 240, 20);
        contentPane.add(Initial);
        Initial.setColumns(10);

        LastName = new JTextField();
        LastName.setColumns(10);
        LastName.setBounds(173, 140, 240, 20);
        contentPane.add(LastName);

        Phone = new JTextField();
        Phone.setColumns(10);
        Phone.setBounds(173, 178, 240, 20);
        contentPane.add(Phone);



        JButton btnNewButton = new JButton("SUBMIT");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createNewBook(ID,Initial,LastName,Phone);
                System.exit(0);
            }
        });
        btnNewButton.setBounds(325, 339, 89, 23);
        contentPane.add(btnNewButton);
    }
    private void createNewBook(final JTextField ID, final JTextField Initial,
                               final JTextField LastName, final JTextField Phone) {
        Boolean in_lib;
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/security", "root", "root");
            PreparedStatement prep = conn.prepareStatement("insert into useraccount values (?,?,?,?)");) {
            //log.info("Connected");


            prep.setString(1, ID.getText());
            prep.setString(2,  Initial.getText());
            prep.setString(3, LastName.getText());
            prep.setString(4, Phone.getText());


            int res = prep.executeUpdate();

            if(res == 1 ) {
                JOptionPane.showMessageDialog(null, "User added successfully");
            }
            //log.info("Inserted into database");
        }catch(java.sql.SQLIntegrityConstraintViolationException userExsistException) {
            JOptionPane.showMessageDialog(null, "User wasn't added.PLease try again.");
        }
        catch (Exception exception) {
            //log.error("Failed to insert into table", exception);

        }
    }
}