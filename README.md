# Instrucciones Para Ejecutar el proyecto desde Netbeans

## Informacion General
- **Alumno:** Rita Shantal de Leon Sanchez
- **Carnet:** 7690-16-3770  
- **Curso:** Programacion 2  
- **Proyecto:** Sistema de Biblioteca, proyecto Final 
- **Fecha:** sabado 15 de noviembre de 2025  
- **Version:** 1.0  

## Contenido del Proyecto
Este proyecto incluye:

1. **CRUD de Autores**  
2. **CRUD de Categorias**  
3. **CRUD de Libros**  
4. **Ingreso/Login**  
5. **Acerca de** (con foto)  

Todas las operaciones de los CRUD incluyen:
- Formulario con `JTextField` y `JComboBox`  
- `JTable` para listar registros  
- Botones: Nuevo/Limpiar, Guardar, Modificar/Actualizar, Eliminar, Listar  
- Validaciones con `JOptionPane`  
- Confirmación antes de eliminar  
- Listado siempre actualizado  

## Base de Datos
- La base de datos se llama: `biblioteca`  
- Se incluyen las tablas: autores, categorias, libros, acercaa_de  
- El script SQL con DDL e inserts mínimos está en: (script_sql/biblioteca.sql)  

### Como importar la base de datos:
1. Abrir MySQL Workbench   
2. Ejecutar el script `biblioteca.sql` para crear tablas y registros minimos.  


## Configuracion en NetBeans
Para ejecutar el proyecto desde NetBeans:

1. Abrir NetBeans y seleccionar ´File > Open Project`  
2. Navegar hasta la carpeta del proyecto (`ProyectoBiblioteca`) y abrirlo.  
3. Verificar que el MySQL Connector/J este agregado:  
   - Clic derecho sobre el proyecto > Properties > Libraries  
   - Asegurarse de que el .jar del conector este en Compile-time Libraries  
4. Ejecutar el proyecto: clic derecho en el proyecto > Run  
5. El sistema se abrira con la ventana de Ingreso, donde pedira usuario y contraseña.  

## Acerca de
- El formulario muestra informacion del alumno: Carne, Nombre completo y demas datos.  
- La foto se debe cargar en el boton Cargar Foto,como referencia en la carpeta src/imagenes hay una fotografia y se guarda la ruta en la base de datos.  
- Los datos del alumno se toman de la tabla `acercaa_de`.  


## Nota Importante
- El proyecto no requiere ejecucion del .jar
- Todo se puede ejecutar directamente desde NetBeans.  
- Revisar que la base de datos y el MySQL Connector esten correctamente configurados antes de ejecutar.  
