package dao;

import com.utp.biblioteca.resources.dao.UsuarioDAO;
import com.utp.biblioteca.resources.modelo.Usuario;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UsuarioDAOTest {

    @Test
    public void testBuscarTodos() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.buscarTodos();

        if (!usuarios.isEmpty()) {
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        } else {
            System.out.println("No se encontraron usuarios");
        }

    }
}