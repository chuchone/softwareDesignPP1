<%-- 
    Document   : estadoCuentaColones
    Created on : 6 oct. 2024, 18:39:40
    Author     : Nelson
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Estado de Cuenta y Validación de Cuenta - CAC</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #00aaff;
            color: white;
            text-align: center;
            padding: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin: 0;
            font-size: 2.5em;
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-box {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
        .form-box h2 {
            color: #00aaff;
            margin-bottom: 20px;
        }
        .input-group {
            margin-bottom: 20px;
        }
        .input-group label {
            display: block;
            font-size: 1.1em;
            color: #333;
            margin-bottom: 8px;
        }
        .input-group input, .input-group select {
            width: 100%;
            padding: 10px;
            font-size: 1.1em;
            border: 2px solid #ddd;
            border-radius: 5px;
            transition: border-color 0.3s ease;
        }
        .input-group input:focus, .input-group select:focus {
            border-color: #00aaff;
            outline: none;
        }
        .error-message {
            color: red;
            font-size: 0.9em;
        }
        .submit-btn {
            width: 100%;
            padding: 12px;
            background-color: #00aaff;
            color: white;
            font-size: 1.2em;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .submit-btn:hover {
            background-color: #008ecc;
        }
    </style>
</head>
<body>
    <header>
        <h1>Centro de Atención al Cliente (CAC)</h1>
    </header>

    <div class="container">
        <div class="form-box">
            <h2>Estado de Cuenta y Validar Cuenta</h2>
            <form action="EnviarStatusCuentaDolaresServlet" method="post">
                <div class="input-group">
                    <label for="numeroCuenta">Número de Cuenta:</label>
                    <input type="text" id="numeroCuenta" name="numeroCuenta" required>
                </div>
                <div class="input-group">
                    <label for="pinCuenta">PIN de la Cuenta (7 dígitos):</label>
                    <input type="password" id="pinCuenta" name="pinCuenta" required>
                </div>
                <div class="input-group">
                    <label for="idioma">Seleccione el idioma:</label>
                    <select id="idioma" name="idioma" required>
                        <option value="ENGLISH">Inglés</option>
                        <option value="SPANISH">Español</option>
                        <option value="FRENCH">Francés</option>
                        <option value="GERMAN">Alemán</option>
                        <option value="ITALIAN">Italiano</option>
                        <option value="PORTUGUESE">Portugués</option>
                        <option value="CHINESE">Chino</option>
                        <option value="JAPANESE">Japonés</option>
                        <option value="KOREAN">Coreano</option>
                        <option value="RUSSIAN">Ruso</option>
                        <option value="ARABIC">Árabe</option>
                        <option value="HINDI">Hindi</option>
                        <option value="BENGALI">Bengalí</option>
                        <option value="PUNJABI">Panyabí</option>
                        <option value="URDU">Urdu</option>
                    </select>
                </div>
            

                <button type="submit" class="submit-btn">Consultar Estado de Cuenta y Validar</button>
            </form>
        </div>
    </div>
</body>
</html>

