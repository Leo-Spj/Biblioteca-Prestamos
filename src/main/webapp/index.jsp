<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>El Virrey Librería</title>
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
        .bg-crema-claro {
            background-color: #FFFDD0; /* Crema claro */
        }
    </style>
</head>

<body class="bg-white text-gray-800">
    <header class="bg-crema-claro py-2">
        <div class="container mx-auto flex justify-between items-center">
            <div class="flex space-x-4">
                <a href="#" class="text-sm">Quiénes somos</a>
                <a href="#" class="text-sm">WhatsApp</a>
            </div>
            <a href="#" class="text-sm">Mi cuenta</a>
        </div>
    </header>
    <div class="bg-white py-4">
        <div class="container mx-auto flex flex-col sm:flex-row justify-between items-center">
            <img src="https://placehold.co/150x50" alt="El Virrey Librería Logo" class="h-12 mb-4 sm:mb-0">
            <div class="relative w-full sm:w-1/2">
                <input type="text" placeholder="Título, Autor o ISBN" class="w-full border border-gray-300 rounded-full py-2 px-4">
                <button class="absolute right-2 top-2 text-brown-700">
                    <i class="fas fa-search"></i>
                </button>
            </div>
        </div>
    </div>

    <div class="bg-ocre-dark py-2 px-4">
        <div class="container mx-auto flex flex-col md:flex-row justify-between items-center space-y-4 md:space-y-0">
            <div class="flex space-x-4">
                <a href="#" class="text-white">Libros</a>
                <a href="#" class="text-white">Donaciones</a>
            </div>

            <div class="flex space-x-4 text-white">
                <a href="#"><i class="fab fa-facebook-f"></i></a>
                <a href="#"><i class="fab fa-twitter"></i></a>
                <a href="#"><i class="fab fa-instagram"></i></a>
                <a href="#"><i class="fab fa-youtube"></i></a>
                <a href="#"><i class="fab fa-whatsapp"></i></a>
            </div>
        </div>
    </div>

    <nav class="bg-brown-700 py-2">
    </nav>

    <main class="container mx-auto py-8">
        <section class="mb-12">
            <h2 class="text-2xl font-bold mb-4">DESTACADOS DEL MES</h2>
            <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
                <div class="bg-crema-claro p-4 rounded-lg flex flex-col sm:flex-row items-center">
                    <div class="text-center sm:text-left">
                        <h3 class="font-bold">GRANTA 25 - PERÚ</h3>
                        <p>42 escritores despliegan la realidad del país, atravesada por la historia, la política, el arte, el amor y la barbarie...</p>
                    </div>
                    <img src="https://placehold.co/100x150" alt="Portada de GRANTA 25 - PERÚ" class="mt-4 sm:mt-0 sm:ml-4">
                </div>
                <div class="bg-crema-claro p-4 rounded-lg flex flex-col sm:flex-row items-center">
                    <div class="text-center sm:text-left">
                        <h3 class="font-bold">NUESTROS MUERTOS</h3>
                        <p>El drama humano de decenas de familias que buscan esclarecer la verdad, y que termina por revelar una cadena de negligencias.</p>
                    </div>
                    <img src="https://placehold.co/100x150" alt="Portada de NUESTROS MUERTOS" class="mt-4 sm:mt-0 sm:ml-4">
                </div>
                <div class="bg-crema-claro p-4 rounded-lg flex flex-col sm:flex-row items-center">
                    <div class="text-center sm:text-left">
                        <h3 class="font-bold">EL INVENCIBLE VERANO DE LILIANA</h3>
                        <p>Una excavación en la vida de una mujer que careció del lenguaje necesario para luchar contra la violencia sexista</p>
                    </div>
                    <img src="https://placehold.co/100x150" alt="Portada de EL INVENCIBLE VERANO DE LILIANA" class="mt-4 sm:mt-0 sm:ml-4">
                </div>
            </div>
        </section>

        <section>
            <h2 class="text-2xl font-bold mb-4">ULTIMOS LIBROS AGREGADOS</h2>
            <div class="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-5 lg:grid-cols-7 gap-4">

                <c:choose>
                    <c:when test="${empty libros}">
                        <p>No se encontraron libros.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="libro" items="${libros}">
                            <div class="text-center">
                                <img src="${libro.link_imagen}" alt="Portada de ${libro.titulo}" class="mx-auto mb-2">
                                <p class="font-bold">${libro.titulo}</p>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

        </section>
    </main>
</body>
</html>
