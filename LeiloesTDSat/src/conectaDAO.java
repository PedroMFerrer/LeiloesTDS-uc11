
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Adm
 */
public class conectaDAO {

    public static Connection connectDB() {
        Connection conn = null;

        try {

            String url = "jdbc:mysql://localhost/uc11?user=root&password=Amordoce1409@"
                    + "&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

            conn = DriverManager.getConnection(url);

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }

}
