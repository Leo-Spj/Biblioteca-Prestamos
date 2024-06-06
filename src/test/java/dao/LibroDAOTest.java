package dao;

import com.utp.biblioteca.resources.dao.LibroDAO;
import com.utp.biblioteca.resources.modelo.Libro;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LibroDAOTest {


    @Test
    public void testBuscarTodosLibros() {
        LibroDAO libroDAO = new LibroDAO();
        List<Libro> libros = libroDAO.buscarTodos();

        if (!libros.isEmpty()) {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        } else {
            System.out.println("No se encontraron libros");
        }
    }
}
