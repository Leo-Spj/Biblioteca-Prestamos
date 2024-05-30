package dao;

import com.utp.biblioteca.resources.dao.UsuarioDAO;
import com.utp.biblioteca.resources.modelo.Usuario;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOTest {

    @Test
    public void testBuscarTodos() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.buscarTodos();

        // Verificar que la lista no está vacía
        assertFalse(usuarios.isEmpty());

        // Verificar que todos los elementos en la lista son instancias de Usuario
        for (Object obj : usuarios) {
            assertTrue(obj instanceof Usuario);
        }

        // Imprimir los usuarios
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}