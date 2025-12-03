# Sistema de Gestión de Almacén ITLA

## Descripción

Sistema de gestión de almacén desarrollado como proyecto final de Programación 1. Es una aplicación de escritorio construida en Java que permite administrar usuarios y productos de manera eficiente mediante una interfaz gráfica intuitiva. El sistema cuenta con funcionalidades completas de autenticación, gestión de usuarios y control de inventario de productos, todo conectado a una base de datos MySQL en la nube.

La aplicación implementa un sistema de login seguro, registro de usuarios, y operaciones CRUD (Crear, Leer, Actualizar, Eliminar) tanto para usuarios como para productos, facilitando la gestión integral de un almacén.

## Características Principales

- **🔐 Sistema de Autenticación**
  - Login seguro con validación de credenciales
  - Registro de nuevos usuarios con validación de datos
  - Gestión de sesiones de usuario

- **👥 Gestión de Usuarios**
  - Visualización de todos los usuarios registrados en tabla
  - Crear nuevos usuarios con información completa (nombre, apellido, teléfono, email)
  - Editar información de usuarios existentes
  - Eliminar usuarios del sistema
  - Búsqueda y filtrado de usuarios

- **📦 Gestión de Productos**
  - Visualización de inventario completo en tabla
  - Agregar nuevos productos con detalles (nombre, marca, categoría, precio, stock)
  - Actualizar información de productos
  - Eliminar productos del inventario
  - Control de stock y precios

- **💎 Interfaz Gráfica Moderna**
  - Diseño limpio y profesional con paleta de colores personalizada
  - Componentes Swing optimizados para una experiencia de usuario fluida
  - Feedback visual en todas las operaciones
  - Navegación intuitiva entre módulos

## Tecnologías Utilizadas

### Lenguaje de Programación
- **Java** (JDK 8 o superior)

### Interfaz Gráfica
- **Java Swing** - Framework para la creación de interfaces gráficas de escritorio

### Base de Datos
- **MySQL 8.0** - Sistema de gestión de bases de datos relacional
- **MySQL Connector/J 9.5.0** - Driver JDBC para conectividad con MySQL

### Herramientas de Desarrollo
- **IntelliJ IDEA** - IDE recomendado para desarrollo
- **Git** - Control de versiones

### Patrones y Arquitectura
- Arquitectura en capas (MVC adaptado)
- Patrón Singleton para gestión de conexiones
- Patrón Factory para creación de objetos
- Patrón Repository para acceso a datos

## Estructura del Proyecto

```
Proyecto-Final-P1/
│
├── src/                          # Código fuente de la aplicación
│   ├── db/                       # Capa de conexión a base de datos
│   │   └── DatabaseConnection.java    # Singleton para conexión MySQL
│   │
│   ├── model/                    # Modelos de dominio (Entidades)
│   │   ├── Usuario.java         # Clase de entidad Usuario
│   │   └── Producto.java        # Clase de entidad Producto
│   │
│   ├── repository/               # Capa de acceso a datos (DAO)
│   │   ├── UsuarioRepository.java     # Operaciones CRUD de usuarios
│   │   └── ProductoRepository.java    # Operaciones CRUD de productos
│   │
│   ├── factory/                  # Fábricas para creación de objetos
│   │   ├── UsuarioFactory.java        # Factory para instancias de Usuario
│   │   └── ProductoFactory.java       # Factory para instancias de Producto
│   │
│   ├── ui/                       # Capa de presentación (Interfaz gráfica)
│   │   ├── Main.java            # Punto de entrada de la aplicación
│   │   ├── LoginForm.java       # Formulario de inicio de sesión
│   │   ├── RegisterForm.java    # Formulario de registro de usuarios
│   │   ├── MainMenu.java        # Menú principal del sistema
│   │   ├── UserManagement.java  # Interfaz de gestión de usuarios
│   │   ├── ProductManagement.java     # Interfaz de gestión de productos
│   │   └── ProductFormDialog.java     # Diálogo para formularios de productos
│   │
│   └── lib/                      # Librerías externas
│       └── mysql-connector-j-9.5.0.jar    # Driver JDBC de MySQL
│
├── .gitignore                    # Archivos ignorados por Git
├── Proyecto Final P1.iml         # Archivo de configuración de IntelliJ
└── README.md                     # Este archivo
```

## Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

1. **Java Development Kit (JDK) 8 o superior**
   - Verificar instalación: `java -version`
   - Descargar desde: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) o [OpenJDK](https://openjdk.org/)

2. **IntelliJ IDEA** (Recomendado)
   - Community Edition (gratuita) o Ultimate Edition
   - Descargar desde: [JetBrains IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

3. **Conexión a Internet**
   - Necesaria para conectarse a la base de datos MySQL en la nube (Aiven)

### Configurar el Proyecto en IntelliJ IDEA

1. **Clonar el Repositorio**
   ```bash
   git clone https://github.com/Patsydev/Proyecto-Final-P1.git
   cd Proyecto-Final-P1
   ```

2. **Abrir el Proyecto en IntelliJ**
   - Abre IntelliJ IDEA
   - Selecciona `File > Open`
   - Navega hasta la carpeta del proyecto y selecciona el directorio raíz
   - IntelliJ detectará automáticamente la configuración del proyecto

3. **Configurar el JDK**
   - Ve a `File > Project Structure > Project`
   - En `Project SDK`, selecciona tu JDK instalado (mínimo Java 8)
   - En `Project language level`, selecciona la versión compatible

4. **Verificar las Dependencias**
   - El driver MySQL Connector está incluido en `src/lib/mysql-connector-j-9.5.0.jar`
   - IntelliJ debería detectarlo automáticamente
   - Si no está configurado, ve a `File > Project Structure > Libraries > + > Java`
   - Selecciona el archivo JAR en `src/lib/`

### Compilar y Ejecutar

**Opción 1: Desde IntelliJ IDEA**

1. Abre la clase `Main.java` ubicada en `src/ui/Main.java`
2. Haz clic derecho en la clase y selecciona `Run 'Main.main()'`
3. O presiona `Shift + F10` para ejecutar

**Opción 2: Desde la Terminal**

1. Navega al directorio del proyecto:
   ```bash
   cd Proyecto-Final-P1
   ```

2. Compila el proyecto:
   ```bash
   # En Linux/Mac:
   javac -cp "src/lib/*:src" -d out src/ui/*.java src/model/*.java src/repository/*.java src/factory/*.java src/db/*.java
   ```

3. Ejecuta la aplicación:
   ```bash
   # En Linux/Mac:
   java -cp "src/lib/*:out" ui.Main
   ```

**En Windows, usa punto y coma (;) en lugar de dos puntos (:):**
```bash
javac -cp "src/lib/*;src" -d out src/ui/*.java src/model/*.java src/repository/*.java src/factory/*.java src/db/*.java
java -cp "src/lib/*;out" ui.Main
```

## Arquitectura del Proyecto

El proyecto sigue una arquitectura en capas que separa las responsabilidades y facilita el mantenimiento:

### Capa de Presentación (UI)

**Responsabilidad:** Interacción con el usuario mediante interfaces gráficas

- **Main.java**: Punto de entrada que inicializa la aplicación en el Event Dispatch Thread de Swing
- **LoginForm.java**: Formulario de autenticación que valida credenciales contra la base de datos
- **RegisterForm.java**: Permite el registro de nuevos usuarios con validación de campos
- **MainMenu.java**: Menú principal que proporciona acceso a los módulos de gestión
- **UserManagement.java**: Interfaz completa para CRUD de usuarios con tabla de visualización
- **ProductManagement.java**: Interfaz para gestión de inventario de productos
- **ProductFormDialog.java**: Diálogo modal para crear/editar productos

**Características:**
- Uso extensivo de componentes Swing (JFrame, JPanel, JTable, JButton, etc.)
- Paleta de colores personalizada para una experiencia visual coherente
- Manejo de eventos mediante listeners
- Validación de entrada en el cliente antes de enviar a la capa de negocio

### Capa de Negocio

**Responsabilidad:** Lógica de negocio y reglas de validación

- **Factory Package**: Implementa el patrón Factory para la creación de objetos
  - **UsuarioFactory.java**: Centraliza la creación de instancias de Usuario
  - **ProductoFactory.java**: Centraliza la creación de instancias de Producto

**Características:**
- Validación de reglas de negocio
- Creación de objetos de dominio con valores por defecto
- Separación de la lógica de creación de objetos

### Capa de Datos

**Responsabilidad:** Persistencia y acceso a datos

**Conexión a Base de Datos:**
- **DatabaseConnection.java**: Implementa el patrón Singleton para gestionar una única conexión a MySQL durante el ciclo de vida de la aplicación
  - Configuración JDBC con MySQL Connector
  - Gestión de credenciales (URL, usuario, contraseña)
  - Manejo de excepciones de conexión

**Modelos de Dominio:**
- **Usuario.java**: Entidad que representa un usuario del sistema
  - Atributos: idUser, userName, nombre, apellido, telefono, email, password
  - Getters y setters para todos los atributos
  
- **Producto.java**: Entidad que representa un producto del inventario
  - Atributos: idProducto, nombreProducto, marcaProducto, categoriaProducto, precioProducto, stockProducto
  - Getters y setters para todos los atributos

**Repositorios (Patrón DAO):**
- **UsuarioRepository.java**: Gestiona operaciones CRUD para usuarios
  - `registrar()`: Inserta nuevo usuario
  - `login()`: Autentica usuario por userName y password
  - `getAll()`: Recupera todos los usuarios
  - `actualizar()`: Modifica datos de usuario existente
  - `eliminar()`: Elimina usuario por ID
  - `getById()`: Busca usuario específico por ID

- **ProductoRepository.java**: Gestiona operaciones CRUD para productos
  - `findAll()`: Lista todos los productos
  - `save()`: Inserta nuevo producto
  - `update()`: Actualiza producto existente
  - `delete()`: Elimina producto por ID
  - `findById()`: Busca producto específico

**Características:**
- Uso de PreparedStatement para prevenir inyección SQL
- Manejo robusto de excepciones SQLException
- Reutilización de conexión mediante Singleton
- Separación clara entre operaciones de lectura y escritura

### Patrones de Diseño Utilizados

1. **Singleton Pattern**
   - **Implementado en:** DatabaseConnection, UsuarioRepository, ProductoRepository
   - **Propósito:** Garantizar una única instancia de la conexión a base de datos y repositorios
   - **Beneficio:** Ahorro de recursos y coherencia en el acceso a datos

2. **Factory Pattern**
   - **Implementado en:** UsuarioFactory, ProductoFactory
   - **Propósito:** Encapsular la lógica de creación de objetos de dominio
   - **Beneficio:** Flexibilidad para cambiar la implementación sin afectar el código cliente

3. **Repository Pattern (DAO)**
   - **Implementado en:** UsuarioRepository, ProductoRepository
   - **Propósito:** Abstraer el acceso a datos y proporcionar una interfaz orientada a colecciones
   - **Beneficio:** Separación de la lógica de negocio de la persistencia

4. **MVC (Modelo-Vista-Controlador) Adaptado**
   - **Modelo:** Clases en package `model`
   - **Vista:** Clases en package `ui`
   - **Controlador:** Lógica distribuida en repositories y factories
   - **Beneficio:** Separación de responsabilidades y facilidad de mantenimiento

5. **Layered Architecture**
   - **Capas:** Presentación → Negocio → Datos
   - **Propósito:** Organizar el código en capas con responsabilidades definidas
   - **Beneficio:** Modularidad, escalabilidad y facilidad de testing

## Licencia

Este proyecto es de código abierto y está disponible bajo la [Licencia MIT](https://opensource.org/licenses/MIT).

## Autor

**Patsydev**
- GitHub: [@Patsydev](https://github.com/Patsydev)
- Proyecto: Sistema de Gestión de Almacén ITLA
- Curso: Programación 1 - Proyecto Final

---

**Nota:** Este proyecto fue desarrollado con fines educativos como parte del curso de Programación 1. Las credenciales de la base de datos están incluidas en el código para facilitar la evaluación, pero en un entorno de producción deberían manejarse mediante variables de entorno o archivos de configuración seguros.
