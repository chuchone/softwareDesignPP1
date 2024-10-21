<%-- 
    Document   : consultarTransacciones
    Created on : 6 oct. 2024, 21:33:04
    Author     : Nelson
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Identificación</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            width: 300px;
            text-align: center;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px;
            width: 100%;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error-message {
            color: red;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Ingrese su Identificación</h2>
        <form action="ImprimirCuentasServlet" method="POST" onsubmit="return validarFormulario()">
            <input type="text" id="identificacion" name="identificacion" placeholder="Identificación" maxlength="11" required>
            <div id="error" class="error-message"></div>
            <input type="submit" value="Enviar">
        </form>
    </div>

    <script>
        function validarFormulario() {
            const identificacion = document.getElementById("identificacion").value;
            const errorDiv = document.getElementById("error");
            errorDiv.textContent = "";

            if (identificacion.length > 11) {
                errorDiv.textContent = "La identificación no debe superar los 11 caracteres.";
                return false;
            }
            return true;
        }
    </script>
</body>
</html>
