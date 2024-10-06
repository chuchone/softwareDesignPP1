<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cambiar Número de Teléfono</title>
    <style>
        body {
            background-color: #d9d9d9;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; 
        }
        .container {
            width: 400px; 
            padding: 30px; 
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2); 
            background-color: #6f4c3e; 
            color: white;
            text-align: center;
        }
        h1 {
            margin-bottom: 20px; 
            font-size: 24px; 
        }
        label {
            margin-bottom: 10px;
            display: block;
            font-size: 16px;
        }
        input[type="text"], input[type="submit"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-radius: 4px;
            font-size: 16px;
        }
        input[type="text"] {
            background-color: #ffffff;
            color: #333;
        }
        input[type="submit"] {
            background-color: #4CAF50; 
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Cambiar Teléfono</h1>
        <form action="CambiarTelefonoServlet" method="post">
            <label for="identidad">Identidad del Cliente:</label>
            <input type="text" id="identidad" name="identidad" required>

            <label for="nuevoTelefono">Nuevo Número de Teléfono:</label>
            <input type="text" id="nuevoTelefono" name="nuevoTelefono" required pattern="^\d{8}$" title="Debe ser un número de exactamente 8 dígitos, sin decimales.">
            <input type="submit" value="Actualizar Teléfono">
        </form>
    </div>
</body>
</html>
