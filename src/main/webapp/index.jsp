<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, com.utp.biblioteca.resources.modelo.* "%>
<%
    List<Libro> topLibros = (List<Libro>) request.getAttribute("topLibros");
    
%>
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
    </style>
</head>

<body class="bg-warmGray-100 text-warmGray-800 antialiased">
<header class="bg-crema-claro py-2 shadow-sm">
    <div class="container mx-auto flex justify-between items-center px-6">
        <div class="flex space-x-4">
            <a href="#" class="text-sm font-bold text-ocre-dark">Quiénes somos</a>
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
            <a href="<c:url value='/faces/usuarios/formularioUsuario.xhtml' />" class="text-sm">Registrar Usuario</a>
            <a href="<c:url value='/faces/libros/formularioLibro.xhtml' />" class="text-sm">Registrar Libro</a>
            <a href="<c:url value='/gestion/Devoluciones' />" class="text-sm">Devoluciones</a>
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

        <h2 class="mt-4 ml-2 text-2xl font-bold mb-4 col-span-2">Acerca de Nosotros</h2>
        <section class="mb-12 grid grid-cols-1 sm:grid-cols-2 gap-2 justify-items-center align-items-center">
            <div class="mx-2 text-lg bg-ocre-dark text-white p-4 rounded">
                Somos una biblioteca comprometida con la difusión de la cultura y el conocimiento. Creemos firmemente en el poder de la educación y el acceso a la información como herramientas para el desarrollo personal y social. Por eso, ofrecemos préstamos de libros de manera gratuita.
            </div>
            <div class="mx-2 text-lg bg-ocre-dark text-white p-4 rounded">
                Nuestra misión es fomentar la lectura y el aprendizaje, proporcionando un espacio seguro y acogedor para todos. Nos enorgullece ser parte de la comunidad y agradecemos a nuestros usuarios por sus donaciones y apoyo continuo.
            </div>
        </section>

        <section class="mb-12">
            <h2 class="ml-2 text-2xl font-bold mb-4">LIBROS MÁS SOLICITADOS DEL MES!</h2>

            <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 justify-items-center align-items-center">

                <c:forEach var="libro" items="${topLibros}">
                    <div class="bg-crema-claro p-4 rounded-lg max-w-md flex flex-col sm:flex-row items-center">
                        <div class="text-center sm:text-left">
                            <p class="text-xs">${libro.autor.nombre}</p>
                            <h3 class="font-bold">${libro.titulo}</h3>
                            <p>${libro.descripcion}</p>
                        </div>
                        <img src="${libro.link_imagen}" alt="Portada de ${libro.titulo}" class="mt-4 sm:mt-0 sm:ml-4">
                    </div>
                </c:forEach>

            </div>

        </section>


    </main>
</body>
</html>