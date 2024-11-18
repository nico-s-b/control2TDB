# Pasos para ejecutar el backend:
- Crear base de datos 'control2TBD', puerto 5432, usuario 'postgres' y contraseña 'admin' en pgAdmin.
- Ejecutar 'createDB.sql' en pgAdmin para la base de datos 'control2TBD'.
- Asegurar que no hayan procesos en ejecución que hagan uso del puerto 8090.
- Asegurar de tener Java JDK 17 instalado.
- Desde el IDE, ejecutar 'Control2Application' (probado en IntelliJ).

# Cómo se realizó la implementación
1. Creación de CRUD simple para las entidades: User, Task.
2. Creación de script de creación de la base de Datos.
3. Implementar 'Marcar tareas como Completadas'.
4. Implementar sistema de registro de usuarios con nombre de usuario y contraseña.
5. Implementar autenticación y autorización via JWT ().
6. Crear CRUD para entidad: Notifier (se eligió crear Notifier para satisfacer los requerimientos del Control).
7. Implementar búsqueda de tareas (Task) por filtros.
