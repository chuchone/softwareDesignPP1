<%-- 
    Document   : accesoBitacoras
    Created on : 9 nov. 2024, 05:01:07
    Author     : Nelson
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Acceder a la Cámara</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-color: #f5f5f5;
            font-family: Arial, sans-serif;
        }

        h1 {
            color: #333;
        }

        .camera-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            border: 2px solid #ddd;
            border-radius: 10px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        video {
            width: 100%;
            max-width: 400px;
            border-radius: 10px;
            margin-bottom: 20px;
        }

        .button-container {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
            font-weight: bold;
            color: #fff;
            background-color: #4CAF50;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <h1>Acceso a las bitacoras</h1>
    <div class="camera-container">
        <video id="video" autoplay></video>
        <a href="ConsultarBitacorasServlet" class="consult-option">Consultar</a>
        <a href="index.jsp" class="consult-option">salir</a>
    </div>

    <script>
        // Iniciar la cámara del usuario
        const video = document.getElementById("video");

        if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
            navigator.mediaDevices.getUserMedia({ video: true })
                .then(stream => {
                    video.srcObject = stream;
                })
                .catch(error => {
                    console.error("Error al acceder a la cámara: ", error);
                });
        } else {
            alert("La cámara no es compatible con este navegador.");
        }
    </script>

</body>
</html>
