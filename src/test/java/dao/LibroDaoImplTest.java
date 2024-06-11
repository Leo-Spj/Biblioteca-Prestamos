package dao;

import com.utp.biblioteca.resources.dao.impl.LibroDaoImpl;
import com.utp.biblioteca.resources.modelo.Libro;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LibroDaoImplTest {


    @Test
    public void testBuscarTodosLibros() {
        LibroDaoImpl libroDaoImpl = new LibroDaoImpl();
        List<Libro> libros = libroDaoImpl.buscarTodos();

        if (!libros.isEmpty()) {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        } else {
            System.out.println("No se encontraron libros");
        }
    }
}
