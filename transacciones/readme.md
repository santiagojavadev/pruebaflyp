1. iniciar con docker compose usando archivo adjunto docker-compose.yaml (modificar la ruta del volumen para guardar
    los datos de postgres y redis).
2. crear en la base de datos postgres las bases de datos clientes y transacciones.
3. iniciar los dos microservicios transacciones y clientes.


apis creadas:

clientes

crear cliente
POST localhost:8081/api/clientes
{"tipoIdentificacion": "CEDULA",
"numeroIdentificacion": "123456789",
"nombres": "santiago",
"apellido": "morales",
"correoElectronico": "santiagomorales@test.com",
"fechaNacimiento": "1980-03-05"}

PUT localhost:8081/api/clientes/{id de tipo uuid}
{"tipoIdentificacion": "CEDULA",
"numeroIdentificacion": "123456789",
"nombres": "santiago",
"apellido": "morales",
"correoElectronico": "santiagomorales@test.com",
"fechaNacimiento": "1980-03-05"}


eliminar cliente (validando cuentas asociadas)
DELETE localhost:8081/api/clientes/{id de tipo uuid}

get cliente por documento
GET localhost:8081/api/clientes/documento?tipo=CEDULA&numero=123456789


cuentas

get cuentas por documento de cliente
GET localhost:8080/api/cuentas/documento?tipo=CEDULA&numero=123456789


crear cuenta POST localhost:8080/api/cuentas
{
"tipoCuenta": "AHORROS",
"numeroCuenta": "1234567890",
"tipoIdentificacionCliente": "CEDULA",
"numeroIdentificacionCliente": "123456789",
"saldo": 15004566,
"estadoCuenta": "ACTIVA"
}


transacciones

POST localhost:8080/api/transacciones
{
"tipoTransaccion": "CONSIGNACION",
"cuenta": "1234567890",
"valor": 500.00
}