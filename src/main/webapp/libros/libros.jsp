<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, com.utp.biblioteca.resources.modelo.* "%>

<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Librería Prestamos</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
        }
        .bg-ocre-dark {
            background-color: #8B4513; /* Ocre oscuro */
        }
        .text-ocre-dark {
            color: #8B4513;
        }
        .bg-crema-claro {
            background-color: #FFFDD0; /* Crema claro */
        }
        .image-container {
            width: 100px; /* Ajusta el tamaño según tus necesidades */
            height: 150px; /* Ajusta el tamaño según tus necesidades */
            background-color: #cc9966; /* Color ocre */
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden; /* Para asegurarse de que la imagen se ajuste al contenedor */
        }
        .image-container img {
            display: none; /* Ocultar imagen inicialmente */
        }
        .image-container.loaded img {
            display: block; /* Mostrar imagen cuando se haya cargado */
        }

    </style>
</head>

<body class="bg-warmGray-100 text-warmGray-800 antialiased">
<header class="bg-crema-claro py-2 shadow-sm">
    <div class="container mx-auto flex justify-between items-center px-6">
        <div class="flex space-x-4">
            <a href="<c:url value='/' />" class="text-sm font-bold text-ocre-dark">Quiénes somos</a>
            <!--
             <a href="#" class="text-sm font-bold text-ocre-dark">WhatsApp</a>
             -->

        </div>
        <a href="#" class="text-sm font-bold text-ocre-dark">Soy Bibliotecario</a>
    </div>
</header>
<div class="bg-white shadow py-4">
    <div class="container mx-auto flex flex-col sm:flex-row justify-between items-center px-6">
        <a href="<c:url value='/' />">
            <img src="https://i.postimg.cc/CxFmPffS/temp-Image-Tacv-RE.avif" alt="La Librería Logo" class="h-12 mb-4 sm:mb-0">
        </a>
        <div class="relative w-full sm:w-1/2 mt-2 sm:mt-0">
            <input type="text" placeholder="Título, Autor o ISBN" class="w-full border border-gray-300 rounded-full py-2 pl-4 pr-10 text-warmGray-800">
            <button class="absolute right-4 top-1/2 transform -translate-y-1/2 text-warmGray-800">
                <i class="fas fa-search"></i>
            </button>
        </div>
    </div>
</div>

<div class="bg-crema-claro py-2 px-4 shadow text-ocre-dark">
    <div class="container mx-auto flex flex-col md:flex-row justify-between items-center space-y-4 md:space-y-0 px-6">
        <div class="flex space-x-4 font-semibold">
            <a href="<c:url value='/libros/' />" class="text-sm">Libros</a>
            <a href="<c:url value='/gestion/Prestamos' />" class="text-sm">Realizar Préstamo</a>
            <a href="<c:url value='/gestion/Devoluciones' />" class="text-sm">Devoluciones</a>
            <a href="<c:url value='/faces/usuarios/formularioUsuario.xhtml' />" class="text-sm">Registrar Usuario</a>
            <a href="<c:url value='/faces/libros/formularioLibro.xhtml' />" class="text-sm">Registrar Libro</a>
        </div>

        <div class="flex space-x-4">
            <a href="#"><i class="fab fa-facebook-f text-ocre-dark"></i></a>
            <a href="#"><i class="fab fa-twitter text-ocre-dark"></i></a>
            <a href="#"><i class="fab fa-instagram text-ocre-dark"></i></a>
            <a href="#"><i class="fab fa-youtube text-ocre-dark"></i></a>
            <a href="#"><i class="fab fa-whatsapp text-ocre-dark"></i></a>
        </div>
    </div>
</div>

<nav class="bg-ocre-dark py-2 text-white">
</nav>

<main class="container mx-auto py-8">
    <% Integer paginaActual = (Integer) session.getAttribute("paginaActual");
        Integer cantidadPorPagina = (Integer) session.getAttribute("cantidadPorPagina");
        Integer totalPaginas = (Integer) session.getAttribute("totalPaginas"); %>

    <section>
        <h2 class="ml-2 text-2xl font-bold mb-4">REPERTORIO DE LIBROS</h2>
        <div class="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-5 lg:grid-cols-7 gap-4">
            <c:choose>
                <c:when test="${empty libros}">
                    <p class="text-center">No se encontraron libros.</p>
                </c:when>
                <c:otherwise>
                    <c:forEach var="libro" items="${libros}">
                        <div class="text-center">
                            <div class="image-container mx-auto mb-2">
                                <img style="box-shadow: 0px 0px 10px rgba(0,0,0,0.5);" src="${libro.link_imagen}" alt="Portada de ${libro.titulo}" class="book-cover">                            </div>
                            <p class="font-bold">${libro.titulo}</p>
                            <p class="text-xs">${libro.autor.nombre}</p>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="pagination mt-4 flex justify-center items-center">
            <c:if test="${paginaActual > 1}">
                <a href="<c:url value='/libros/?accion=paginar&pagina=${paginaActual - 1}' />" class="text-blue-500 hover:underline">Anterior</a>
            </c:if>
            <span class="mx-2">Página ${paginaActual} de ${totalPaginas}</span>
            <c:if test="${paginaActual < totalPaginas}">
                <a href="<c:url value='/libros/?accion=paginar&pagina=${paginaActual + 1}' />" class="text-blue-500 hover:underline">Siguiente</a>
            </c:if>
        </div>
    </section>
</main>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const images = document.querySelectorAll('.image-container img');

        images.forEach(img => {
            img.onload = function() {
                img.parentElement.classList.add('loaded');
            };
        });
    });
</script>
</body>
</html>
