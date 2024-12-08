# AlbaranCheck-java
Programa de escritorio en Java. Utiliza Swing para la interfaz. Es el primero de una serie de repositorios donde iré cambiando o agregando tecnologías y herramientas al miso programa.

INTRODUCCIÓN
	Como parte de mi camino educativo quiero realizar un proyecto completo para una aplicación/programa que tenga una utilidad práctica real y que cubra una necesidad actual de un  grupo de empresas. La idea sale de mi experiencia como franquiciado DIA y de la actual necesidad que observo en franquiciados actuales de esa cadena de supermercados.
	Lo central es pasar por todas las fases del diseño de software, utilizar varias tecnologías para un mismo proyecto y observar sus diferencias en cuanto a cuándo y cómo aplicar cada tipo de opción o tecnología en cada tipo de proyecto.
Mi visión es crear una aplicación fácil de usar y eficiente para verificar la recepción de productos. Esta aplicación permitirá a los usuarios cargar albaranes en formato PDF, escanear códigos de barras de productos/bultos y verificar si todos los productos del albarán han sido recibidos. La aplicación proporcionará un informe de recepción indicando los productos recibidos, faltantes y cualquier error encontrado.
La aplicación final será una aplicación para android que podrá descargarse desde PlayStore y podrá utilizar la cámara o un lector de códigos de barra bluetooth. Pero antes de llegar allí quiero hacer todas estas versiones:



### Tabla de Versiones del Software

| Versión | Tecnología                         | Plataforma | Objetivo                                                                 | Características                                                                                                                                                 |
|---------|------------------------------------|------------|--------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| V 0.1   | Java (sin frameworks)              | Escritorio | Validar la idea y la funcionalidad principal.                             | Lectura de códigos de barras, comparación básica con el albarán (cargado manualmente como texto), informe simple de productos recibidos/faltantes.              |
| V 0.2   | Java (sin frameworks), MySQL       | Escritorio | Almacenar datos de albaranes y recepciones de forma permanente.           | Conexión a base de datos, consultas SQL básicas para guardar y recuperar datos de albaranes, productos y recepciones.                                           |
| V 1.0   | Java (sin frameworks), MySQL, JPA (Hibernate) | Escritorio | Simplificar el acceso a la base de datos y mejorar la mantenibilidad del código. | Mapeo objeto-relacional con JPA, uso de entidades y repositorios para interactuar con la base de datos, carga de albaranes en PDF, informe más completo con detalles de las recepciones. |





V 0.1: Prototipo Básico (Java sin frameworks y sin persistencia)
Introducción
Descripción del problema y objetivos del proyecto.
	Cargar en el programa un PDF con el albarán que envía la compañía. Leer el albarán y presentarlo por pantalla de forma resumida. Luego mostrar un campo para escanear los bultos/artículos para crear una lista de productos verificados. Al final se realizará la comparación de las dos tablas y se emitirá un resultado.
Alcance y limitaciones de esta versión.
	Esta versión inicial solo servirá para probar los usos principales del programa, las clases que lo ejecutan y el diseño de la interfaz gráfica para programas/aplicaciones de escritorio. No guardará histórico de albaranes, verificaciones, usuarios, etc. Se usará un txt para guardar la lista de los productos con sus EAN (Bulto y Producto) y se cargará esa lista al inicio de la aplicación.
Requisitos y Casos de Uso
Requisitos Funcionales
RF.01 - Carga de Albaranes: El sistema debe permitir al usuario cargar un archivo PDF que contenga el albarán de entrega.
RF.02 - Lectura y Visualización de Albarán: El sistema debe ser capaz de leer el contenido del albarán PDF y mostrar la información relevante (número de albarán, fecha, proveedor, lista de productos y cantidades) en una interfaz de usuario clara y concisa.
RF.03 - Escaneo de Códigos de Barras: El sistema debe permitir al usuario escanear los códigos de barras de los productos recibidos utilizando un lector de códigos de barras conectado al ordenador.
RF.04 - Verificación de Recepción: El sistema debe comparar los productos escaneados con la lista de productos del albarán y marcar aquellos que han sido recibidos correctamente.
RF.05 - Generación de Informe de Recepción: El sistema debe generar un informe que indique los productos recibidos, los productos faltantes y cualquier discrepancia encontrada entre el albarán y los productos escaneados.
Requisitos No Funcionales
RNF.01 - Usabilidad: La interfaz de usuario debe ser intuitiva y fácil de usar, permitiendo a los usuarios realizar las tareas de forma rápida y eficiente.
RNF.02 - Rendimiento: El sistema debe ser capaz de procesar albaranes y escanear productos de forma rápida y sin retrasos significativos.
RNF.03 - Portabilidad: El sistema debe ser fácilmente ejecutable en diferentes máquinas sin necesidad de instalaciones complejas.


Descripción detallada de los casos de uso principales.
CU.01 - Cargar Albarán: El usuario selecciona un archivo PDF del albarán y lo carga en el sistema.
CU.02 - Ver Albarán: El usuario visualiza la información del albarán en la pantalla.
CU.03 - Escanear Producto: El usuario escanea el código de barras de un producto recibido.
CU.04 - Verificar Recepción: El sistema compara el producto escaneado con el albarán y lo marca como recibido o faltante.
CU.05 - Generar Informe: El usuario solicita la generación del informe de recepción y el sistema lo muestra en pantalla o lo guarda en un archivo.
CU.06 - Exportar Informe: El usuario guarda el informe de recepción en un archivo (PDF, CSV, etc.).
Diseño
Diagrama de clases (UML).


@startuml
class Main {
    + main(args: String[]): void
}
class GUI {
    - controlador: Controlador
    + mostrarVentanaCargaAlbaran(): void
    + obtenerCodigoBarrasEscaneado(): String
    + mostrarInforme(informe: String): void
}
class Controlador {
    - albaranes: List<Albaran>
    - productosVerificados: List<ProductoVerificado>
    + cargarAlbaran(archivo: File): void
    + escanearProducto(codigoBarras: String): void
    + generarInforme(): String
}
class Albaran {
    - numero: String
    - fecha: Date
    - proveedor: String
    - productos: List<ProductoEnAlbaran>
}
class ProductoEnAlbaran {
    - producto: Producto
    - cantidadEsperada: int
}
class Producto {
    - codigoBarras: String
    - descripcion: String
}
class ProductoVerificado {
    - producto: Producto
    - albaran: Albaran
    - cantidadRecibida: int
}
Main -- GUI
GUI -- Controlador
Controlador -- Albaran
Controlador -- ProductoVerificado
Albaran -- ProductoEnAlbaran
ProductoEnAlbaran -- Producto
ProductoVerificado -- Producto
ProductoVerificado -- Albaran

@enduml





Descripción de la estructura del código y los componentes principales.
Main crea instancias de GUI y Controlador.
GUI se muestra al usuario y espera interacciones.
Cuando el usuario realiza una acción (cargar albarán, escanear producto, generar informe), GUI llama al método correspondiente de Controlador.
Controlador realiza la lógica de negocio necesaria y devuelve el resultado a GUI.
GUI actualiza la interfaz de usuario para mostrar el resultado al usuario.


V 0.2: Almacenamiento en Base de Datos (Java sin frameworks, MySQL)
Introducción
Objetivos de esta versión: persistencia de datos.
Diseño de la Base de Datos
Modelo entidad-relación (ER).
Descripción de las tablas y relaciones.
Implementación
Conexión a la base de datos.
Consultas SQL para guardar y recuperar datos.
Actualización de la interfaz de usuario para interactuar con la base de datos.
Pruebas y Resultados
Pruebas de la funcionalidad de la base de datos.
Evaluación del rendimiento y optimización de consultas.

V 1.0: Mapeo Objeto-Relacional (Java sin frameworks, MySQL, Hibernate)
Introducción
Objetivos de esta versión: simplificar el acceso a la base de datos.
Diseño con JPA
Entidades JPA y mapeo objeto-relacional.
Repositorios JPA.
Implementación
Integración de JPA en el código existente.
Refactorización del código para usar entidades y repositorios.
Carga de albaranes en PDF (PDFBox).
V 2.0: Versión Web (Java, Spring Boot, Spring MVC, Thymeleaf, MySQL)
Introducción
Objetivos de esta versión: crear una interfaz web.
Diseño de la Interfaz de Usuario
Wireframes y prototipos.
Tecnologías de frontend (Thymeleaf).
Implementación
Controladores y vistas para la interfaz web.
Autenticación de usuarios.
V 3.0: Versión Móvil (FlutterFlow, FireBase)
Introducción
Objetivos de esta versión: crear una aplicación móvil nativa.
Diseño de la Interfaz de Usuario
Wireframes y prototipos para dispositivos móviles.
Diseño de la experiencia de usuario (UX) para móviles.
Implementación
Widgets y componentes de Flutter.
Acceso a la cámara y al lector de códigos de barras.
Almacenamiento local de datos en FireBase.

