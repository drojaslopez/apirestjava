docker-compose up -d

con el contenedor andando debemos levantar la bd

docker ps

docker exec -it [CONTAINER ID] bash


# ===================================================================
# = DATASOURCE CONFIGURATION (Configuración del origen de datos)  =
# ===================================================================
# URL de conexión a la base de datos PostgreSQL
# Reemplaza 'localhost', '5432', y 'nombre_tu_base_de_datos' con tus valores.
spring.datasource.url=jdbc:postgresql://localhost:5432/dbapijava

# Nombre de usuario para la conexión a la base de datos
spring.datasource.username=postgres
# Contraseña para la conexión a la base de datos
spring.datasource.password=root

# Driver JDBC de PostgreSQL. Spring Boot generalmente lo infiere, pero es bueno especificarlo.
spring.datasource.driver-class-name=org.postgresql.Driver

# ===================================================================
# = CONNECTION POOL CONFIGURATION (Configuración del pool de conexiones) =
# = HikariCP es el pool de conexiones por defecto y recomendado en Spring Boot 2.x+
# ===================================================================
# Número máximo de conexiones que el pool puede mantener.
# Ajusta este valor según la carga esperada de tu aplicación y los recursos del servidor de BD.
spring.datasource.hikari.maximum-pool-size=10

# Número mínimo de conexiones inactivas que HikariCP intentará mantener en el pool.
spring.datasource.hikari.minimum-idle=5

# Tiempo máximo (en milisegundos) que un cliente esperará por una conexión del pool.
# Si se supera este tiempo sin obtener una conexión, se lanzará una SQLException.
spring.datasource.hikari.connection-timeout=30000 
# 30 segundos

# Tiempo máximo (en milisegundos) que una conexión puede permanecer inactiva en el pool.
# Después de este tiempo, las conexiones inactivas pueden ser eliminadas (hasta minimum-idle).
spring.datasource.hikari.idle-timeout=600000 
# 10 minutos

# Tiempo máximo (en milisegundos) que una conexión puede estar en uso (viva).
# Pasado este tiempo, la conexión será retirada del pool y cerrada (incluso si está activa).
# Ayuda a prevenir fugas de conexiones y a reciclar conexiones antiguas.
# Un valor de 0 significa vida infinita (no recomendado para producción).
spring.datasource.hikari.max-lifetime=1800000 
# 30 minutos

# Nombre para identificar el pool de conexiones en logs y JMX.
spring.datasource.hikari.pool-name=SpringBootHikariCP

# Query para validar las conexiones antes de entregarlas y periódicamente.
# Es importante para asegurar que las conexiones en el pool son válidas.
# Para PostgreSQL, 'SELECT 1' es una consulta ligera y eficiente.
spring.datasource.hikari.connection-test-query=SELECT 1

# ===================================================================
# = JPA/HIBERNATE CONFIGURATION (Configuración de JPA/Hibernate)    =
# ===================================================================
# Dialecto de Hibernate para PostgreSQL. Es crucial para que Hibernate genere SQL compatible.
# Spring Boot a menudo lo infiere, pero especificarlo es una buena práctica.
# Asegúrate de usar el dialecto correspondiente a tu versión de PostgreSQL.
# Ejemplos:
# org.hibernate.dialect.PostgreSQL9Dialect (para PostgreSQL 9.x)
# org.hibernate.dialect.PostgreSQL10Dialect (para PostgreSQL 10+)
# org.hibernate.dialect.PostgreSQLDialect (versión más genérica, puede funcionar para versiones recientes)
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Estrategia de generación del esquema de la base de datos (DDL - Data Definition Language).
# Opciones comunes:
#   validate: Valida el esquema existente, no hace cambios. Lanza un error si el esquema no coincide con las entidades.
#   update: Actualiza el esquema si es necesario. Útil durante el desarrollo, pero ¡cuidado en producción!
#   create: Crea el esquema cada vez, borrando los datos anteriores. Útil para pruebas.
#   create-drop: Crea el esquema al inicio y lo borra al final. Útil para pruebas.
#   none: No hace nada con el esquema. Recomendado para producción donde se gestiona el esquema manualmente (ej. con Flyway o Liquibase).
spring.jpa.hibernate.ddl-auto=validate 
# O 'none' para producción

# Muestra el SQL generado por Hibernate en la consola.
# Útil para depuración durante el desarrollo. Desactivar en producción por rendimiento y seguridad.
spring.jpa.show-sql=true 
# Cambiar a 'false' en producción

# Formatea el SQL mostrado en la consola para que sea más legible.
# Solo tiene efecto si spring.jpa.show-sql=true. Desactivar en producción.
spring.jpa.properties.hibernate.format_sql=true 
# Cambiar a 'false' en producción

# Permite a Hibernate generar comentarios en el SQL para entender mejor qué operación se está realizando.
# Solo tiene efecto si spring.jpa.show-sql=true. Desactivar en producción.
spring.jpa.properties.hibernate.use_sql_comments=true 
# Cambiar a 'false' en producción

# Estrategia de nombrado de tablas y columnas.
# 'org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy' es la estrategia por defecto de Spring Boot,
# que convierte camelCase a snake_case (ej. 'miEntidad' -> 'mi_entidad').
# 'org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl' mantiene los nombres tal como están definidos en las entidades.
spring.jpa.hibernate.naming.physical-strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy

# (Opcional) Estrategia de nombrado implícito.
# 'org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy' es la predeterminada.
# spring.jpa.hibernate.naming.implicit-strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy

# (Opcional) Habilitar estadísticas de Hibernate (útil para monitorizar el rendimiento con JMX).
# spring.jpa.properties.hibernate.generate_statistics=true

# (Opcional) Configuración del tamaño del batch para escrituras.
# Puede mejorar el rendimiento en operaciones de inserción/actualización masivas.
# spring.jpa.properties.hibernate.jdbc.batch_size=20
# spring.jpa.properties.hibernate.order_inserts=true
# spring.jpa.properties.hibernate.order_updates=true

# ===================================================================
# = LOGGING CONFIGURATION (Configuración de Logging)               =
# ===================================================================
# Nivel de log para las consultas SQL de Hibernate (si spring.jpa.show-sql=false)
# logging.level.org.hibernate.SQL=DEBUG

# Nivel de log para los parámetros de las consultas de Hibernate (si spring.jpa.show-sql=false)
# logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

# Nivel de log general para tu aplicación

#logging.level.com.tu.paquete=INFO # Reemplaza 'com.tu.paquete' con el paquete raíz de tu aplicación

# ===================================================================
# = OTRAS CONFIGURACIONES RECOMENDADAS                            =
# ===================================================================
# Puerto en el que correrá tu aplicación Spring Boot
server.port=8080

# Context path de la aplicación (opcional)
# server.servlet.context-path=/mi-aplicacion

# Configuración de la zona horaria para JPA/Hibernate y serialización JSON.
# Es importante para manejar fechas y horas consistentemente.
spring.jackson.time-zone=UTC
spring.jpa.properties.hibernate.jdbc.time_zone=UTC

# Forzar el uso de identificadores entre comillas en el SQL generado por Hibernate.
# Esto puede ser útil si tus nombres de tablas o columnas usan mayúsculas/minúsculas mezcladas
# o palabras reservadas de SQL, aunque la mejor práctica es usar snake_case y evitar palabras reservadas.
# spring.jpa.properties.hibernate.globally_quoted_identifiers=true