package dao;

import com.utp.biblioteca.resources.dao.StoredProcedureDAO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class StoredProcedureDAOTest {

    private StoredProcedureDAO storedProcedureDAO = new StoredProcedureDAO();

    @Test
    public void testSpRealizarPrestamo() {
        try {
            storedProcedureDAO.spRealizarPrestamo(87654321, 1, 7);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSpDevolverLibro() {
        try {
            storedProcedureDAO.spDevolverLibro(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

