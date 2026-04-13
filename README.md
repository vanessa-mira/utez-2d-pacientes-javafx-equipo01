Tarea integradora
Sistema de Gestión de Pacientes
utez-2d-pacientes-javafx-equipo01

Descripción del Proyecto
Es una aplicación de escritorio desarrollada en Java 21 con JavaFX, diseñada para optimizar la
administración de expedientes en consultorios médicos. El sistema garantiza la integridad de la
información mediante validaciones estrictas y asegura la continuidad de los datos a través de
persistencia en archivos planos (CSV), eliminando la necesidad de una base de datos externa compleja.

Funcionalidades Principales

1. Gestión Integral de Pacientes (CRUD)

Registro Inteligente: Formulario dinámico con validaciones en tiempo real (CURP única,
formato de teléfono, rangos de edad).

Visualización Centralizada: Tabla de datos interactiva que permite el filtrado y
visualización rápida de la lista de pacientes.

Edición Progresiva: Actualización de datos existentes sin pérdida de consistencia.

Borrado Lógico: Implementación de seguridad que marca a los pacientes como INACTIVOS
en lugar de eliminarlos permanentemente, preservando el historial clínico.

2. Persistencia y Seguridad de Datos

- Almacenamiento Local: Los datos se sincronizan automáticamente en el archivo data/pacientes.csv.
- Carga Predictiva: Al iniciar, el sistema reconstruye el estado de la aplicación
  leyendo el repositorio local.
- Sincronización: Cada operación de escritura dispara un evento de guardado para evitar pérdida de
  información ante cierres inesperados.

3. Dashboard de Estadísticas

Panel de indicadores en tiempo real que muestra:
- Total de registros.
- Pacientes en estado Activo.
- Pacientes en estado Inactivo.

Arquitectura de Software

El sistema utiliza una arquitectura desacoplada para facilitar el mantenimiento y la escalabilidad:

models: Definición de la entidad Paciente.
views: Archivos FXML que definen la interfaz gráfica y estilos CSS.
controllers: Orquestadores entre la vista y la lógica de negocio (MainController).
services: Lógica de procesamiento y manejo de listas observables (ObservableList).
repositories: Capa de acceso a datos (DAO) encargada del parseo del archivo CSV.

Stack Tecnológico y Reglas de Negocio

Core: Java 21 & JavaFX 21.
Gestor de Dependencias: Maven.
Formato de Datos: Comma-Separated Values (CSV).

Reglas de Validación Estrictas:

Nombre: Mínimo 10 caracteres para evitar registros incompletos.
Edad: Validación numérica en el rango de 0 a 120 años.
CURP: 18 caracteres alfanuméricos con verificación de duplicidad.

Instalación y Ejecución

1. Clonar/Abrir: Importar el proyecto en IntelliJ IDEA o cualquier IDE compatible con Maven.
2. Dependencias: Ejecutar "mvn clean install" para configurar las librerías de JavaFX.
3. Lanzamiento: Ejecutar la clase "HelloApplication o Launcher" para inicializar la aplicación.

Equipo de Desarrollo

Vanessa Alejandra Miranda Diaz
Silvana Lizeth Escamilla Carrillo