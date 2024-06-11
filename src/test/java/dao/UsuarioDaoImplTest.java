package dao;

import com.utp.biblioteca.resources.dao.impl.UsuarioDaoImpl;
import com.utp.biblioteca.resources.modelo.Usuario;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UsuarioDaoImplTest {

    @Test
    public void testBuscarTodos() {
        UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
        List<Usuario> usuarios = usuarioDaoImpl.buscarTodos();

        if (!usuarios.isEmpty()) {
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        } else {
            System.out.println("No se encontraron usuarios");
        }

    }
}