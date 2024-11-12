<%-- 
    Document   : salir
    Created on : 24 sep. 2024, 08:18:19
    Author     : Nelson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Salir - Sistema Bancario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e6f7ff;
            margin: 0;
            padding: 20px;
        }

        header {
            background-color: #66b3ff;
            color: #4d4d4d;
            padding: 20px;
            text-align: center;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        h1 {
            margin: 0;
            font-size: 2em;
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
        }

        p.intro {
            text-align: justify;
            font-size: 1.1em;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table,
        th,
        td {
            border: 1px solid #4d4d4d;
            padding: 8px;
        }

        th {
            background-color: #66b3ff;
            color: white;
        }

        td {
            text-align: center;
        }

        .footer {
            margin-top: 30px;
            text-align: center;
            font-weight: bold;
        }

        .back-button {
            display: block;
            width: 120px;
            margin: 20px auto;
            padding: 10px;
            background-color: #cce6ff;
            color: #4d4d4d;
            text-align: center;
            border-radius: 5px;
            text-decoration: none;
            font-size: 1em;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #66b3ff;
        }
    </style>
</head>

<body>
    <header>
        <h1>Proyecto - Diseño de Software</h1>
    </header>

    <div class="container">
        <p class="intro">El proyecto implica diseñar e implementar un sistema de software para la gestión de cuentas bancarias en un cajero
            automático sin tarjeta (Cardless).</p>

                <table>
            <tr>
                <th>Código</th>
                <th>Descripción</th>
                <th>Estado</th>
            </tr>
            <tr><td>REQ_F1</td><td>Crear cuentas bancarias con identificador único (CTA1, CTA2, …, CTAN).</td><td>Completo</td></tr>
            <tr><td>REQ_F2</td><td>Almacenar la fecha automáticamente por el sistema de la creación de cuentas bancarias generadas.</td><td>Completo</td></tr>
            <tr><td>REQ_F3</td><td>Asignar estatus de cuenta (activa/inactiva) con estatus inicial por defecto como “activa”.</td><td>Completo</td></tr>
            <tr><td>REQ_F4</td><td>Establecer la moneda de todas las cuentas como colones (moneda local) al crearlas.</td><td>Completo</td></tr>
            <tr><td>REQ_F5</td><td>Registrar clientes físicos y jurídicos con un identificador único (CTE1, CTE2, CTEN) generado automáticamente.</td><td>Completo</td></tr>
            <tr><td>REQ_F6</td><td>Registrar la información completa del cliente físico y jurídico.</td><td>Completo</td></tr>
            <tr><td>REQ_F7</td><td>Permitir a los clientes auto-gestionarse para darse de alta como cliente físico o jurídico.</td><td>Completo</td></tr>
            <tr><td>REQ_F8</td><td>Registrar transacciones de depósito con monto, fecha y si generó comisión respectiva.</td><td>Completo</td></tr>
            <tr><td>REQ_F9</td><td>Registrar transacciones de retiro con monto, fecha y si generó comisión respectiva.</td><td>Completo</td></tr>
            <tr><td>REQ_F10</td><td>Aplicar comisiones de un 5% sobre el monto a partir de la cuarta transacción.</td><td>Completo</td></tr>
            <tr><td>REQ_F11</td><td>Eliminar transacciones relacionadas si la cuenta es eliminada.</td><td>Completo</td></tr>
            <tr><td>REQ_F12</td><td>Impedir retiros si el saldo de la cuenta no cubre el monto del retiro y la comisión.</td><td>Completo</td></tr>
            <tr><td>REQ_F13</td><td>Permitir a los dueños de cuenta establecer un PIN alfanumérico de 6 caracteres con al menos una letra mayúscula y un número.</td><td>Completo</td></tr>
            <tr><td>REQ_F14</td><td>Validar el formato del PIN usando expresiones regulares.</td><td>Completo</td></tr>
            <tr><td>REQ_F15</td><td>Almacenar el PIN de forma encriptada.</td><td>Completo</td></tr>
            <tr><td>REQ_F16</td><td>Inactivar la cuenta si el PIN es ingresado incorrectamente 3 veces seguidas.</td><td>Completo</td></tr>
            <tr><td>REQ_F17</td><td>Enviar una palabra al azar al teléfono del cliente al realizar un retiro.</td><td>Completo</td></tr>
            <tr><td>REQ_F18</td><td>Bloquear la cuenta si la palabra es ingresada incorrectamente 3 veces seguidas.</td><td>Completo</td></tr>
            <tr><td>REQ_F19</td><td>Notificar al cliente el motivo de la inactivación de su cuenta mediante mensaje en pantalla y correo electrónico.</td><td>Completo</td></tr>
            <tr><td>REQ_F20</td><td>Consultar el tipo de cambio (compra y venta) del dólar en el web service del BCCR.</td><td>Completo</td></tr>
            <tr><td>REQ_F21</td><td>Almacenar los datos generados en memoria secundaria usando un motor de bases de datos.</td><td>Completo</td></tr>
            <tr><td>REQ_F22</td><td>Validar los datos de contacto de clientes (teléfono y correo electrónico) usando expresiones regulares.</td><td>Completo</td></tr>
            <tr><td>REQ_F23</td><td>Consultar el estatus de cuentas en colones y enviar dicha información al email del cliente en un PDF.</td><td>Completo</td></tr>
            <tr><td>REQ_F24</td><td>Descifrar el PIN de las cuentas.</td><td>Completo</td></tr>
            <tr><td>REQ_F25</td><td>Permitir depósitos en colones en las cuentas de los clientes.</td><td>Completo</td></tr>
            <tr><td>REQ_F26</td><td>Permitir retiros en colones en las cuentas de los clientes.</td><td>Completo</td></tr>
            <tr><td>REQ_F27</td><td>Permitir cambio del PIN de la cuenta.</td><td>Completo</td></tr>
            <tr><td>REQ_F28</td><td>Cambiar el número telefónico registrado por el cliente.</td><td>Completo</td></tr>
            <tr><td>REQ_F29</td><td>Cambiar el correo electrónico registrado por el cliente.</td><td>Completo</td></tr>
            <tr><td>REQ_F30</td><td>Transferir dinero entre cuentas propias del mismo cliente.</td><td>Completo</td></tr>
            <tr><td>REQ_F31</td><td>Confirmar la existencia de un cliente físico para evitar duplicados.</td><td>Completo</td></tr>
            <tr><td>REQ_F32</td><td>Confirmar la existencia de un cliente jurídico para evitar duplicados.</td><td>Completo</td></tr>
            <tr><td>REQ_F33</td><td>Confirmar que todos los datos del formulario se llenaron correctamente.</td><td>Completo</td></tr>
            <tr><td>REQ_F34</td><td>Permitir depósitos en dólares en las cuentas de los clientes.</td><td>Completo</td></tr>
            <tr><td>REQ_F35</td><td>Permitir retiros en dólares en las cuentas de los clientes.</td><td>Completo</td></tr>
            <tr><td>REQ_F36</td><td>Consultar el estatus de cuentas en dólares y enviar información al email del cliente en PDF.</td><td>Completo</td></tr>
            <tr><td>REQ_F37</td><td>Consultar todas las transacciones de una misma cuenta.</td><td>Completo</td></tr>
            <tr><td>REQ_F38</td><td>Generar un PDF de estado de cuenta en dos idiomas.</td><td>Completo</td></tr>
            <tr><td>REQ_F39</td><td>Registrar todas las acciones de los usuarios en bitácoras con detalles de acción.</td><td>Completo</td></tr>
            <tr><td>REQ_F40</td><td>Respaldar las bitácoras en JSON, CSV y trama plana.</td><td>Completo</td></tr>
            <tr><td>REQ_F41</td><td>Almacenar y consultar bitácoras de forma persistente en memoria secundaria.</td><td>Completo</td></tr>
            <tr><td>REQ_F42</td><td>Implementar autenticación mediante reconocimiento facial en tiempo real para usuarios.</td><td>Completo</td></tr>
            <tr><td>REQ_F43</td><td>Permitir consulta de bitácoras en formatos JSON, CSV o trama plana.</td><td>Completo</td></tr>
            <tr><td>REQ_F44</td><td>Implementar análisis de sentimientos en texto.</td><td>Completo</td></tr>
            <tr><td>REQ_F45</td><td>Generar una nube de palabras (wordcloud) basada en análisis de sentimientos.</td><td>Completo</td></tr>
            <tr><td>REQ_F46</td><td>Generar audio a partir de texto.</td><td>Completo</td></tr>
            <tr><td>REQ_NF47</td><td>Implementar cifrado fuerte para almacenar el PIN.</td><td>Completo</td></tr>
            <tr><td>REQ_NF48</td><td>Implementar descifrado rápido para el PIN.</td><td>Completo</td></tr>
            <tr><td>REQ_NF49</td><td>Integrar cajeros con el web service del BCCR para tipo de cambio y compra del dólar.</td><td>Completo</td></tr>
            <tr><td>REQ_NF50</td><td>Garantizar compatibilidad para consultas sobre el tipo de compra del dólar.</td><td>Completo</td></tr>
            <tr><td>REQ_NF51</td><td>Enviar PDF en un formato atractivo.</td><td>Completo</td></tr>
            <tr><td>REQ_NF52</td><td>Enviar SMS con clave de acceso en formato atractivo.</td><td>Completo</td></tr>
            <tr><td>REQ_NF53</td><td>Enviar notificaciones de inactivación en SMS en formato atractivo.</td><td>Completo</td></tr>
            <tr><td>REQ_NF54</td><td>Enviar notificaciones de inactivación en email en formato atractivo.</td><td>Completo</td></tr>
            <tr><td>REQ_NF55</td><td>Enviar notificaciones de bloqueo en SMS en formato atractivo.</td><td>Completo</td></tr>
            <tr><td>REQ_NF56</td><td>Enviar notificaciones de bloqueo en email en formato atractivo.</td><td>Completo</td></tr>
            <tr><td>REQ_NF57</td><td>Integrar el sistema con la API de OpenAI.</td><td>Completo</td></tr>
            <tr><td>REQ_NF58</td><td>Aplicar el patrón Decorador para generar archivos PDF en dos idiomas.</td><td>Completo</td></tr>
            <tr><td>REQ_NF59</td><td>Usar el patrón Observador para generar registros automáticos en JSON, CSV y trama plana.</td><td>Completo</td></tr>
            <tr><td>REQ_NF60</td><td>Usar el patrón Singleton para una instancia única de base de datos.</td><td>Completo</td></tr>
            <tr><td>REQ_NF61</td><td>Presentar las bitácoras en pantalla en un formato atractivo.</td><td>Completo</td></tr>
            <tr><td>REQ_NF62</td><td>Utilizar el patrón Singleton para crear una instancia única para interactuar con la base de datos.</td><td>Completo</td></tr>
            <tr><td>REQ_NF63</td><td>Presenta en un formato atractivo las bitácoras en pantalla.</td><td>Completo</td></tr>
      
        </table>

        <div class="footer">
            <p>Programado por:</p>
            <p>Nelson Chavarria Aragón</p>
            <p>Keleer Jiménez</p>
            <p>Ignacio Solano Delgado</p>
        </div>

        <a href="index.jsp" class="back-button">Volver al inicio</a>
    </div>
</body>

</html>