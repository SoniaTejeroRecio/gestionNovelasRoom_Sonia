Proyecto Individual_SoniaTejeroRecio
Comenzamos 🚀
Es importante recalcar que todos los bloques de código se han realizado utilizando el lenguaje de programación Kotlin.  
¿Cómo he estructurado el proyecto?
MainActivity: La pantalla principal de la aplicación. Muestra un fondo de imagen y permite al usuario agregar y mostrar una lista de novelas mediante diálogos.
Pantallas
MainActivity
Descripción: La pantalla principal de la aplicación.
Funcionalidades:
Muestra un fondo de imagen.
Permite al usuario agregar una novela mediante un diálogo.
Muestra una lista de novelas guardadas en un diálogo.
Bases de Datos
Descripción
La aplicación utiliza una base de datos local para almacenar la lista de novelas. Esto permite que los datos persistan entre sesiones de la aplicación.  
Implementación
Room Database: Se utiliza la biblioteca Room para la gestión de la base de datos. Room proporciona una capa de abstracción sobre SQLite para permitir un acceso fluido a la base de datos mientras se aprovechan las capacidades completas de SQLite.
Componentes
Entidad (Entity): Representa una tabla en la base de datos.  
@Entity(tableName = "novelas")
data class Novela(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "titulo") val titulo: String
)
DAO (Data Access Object): Contiene los métodos utilizados para acceder a la base de datos.  
@Dao
interface NovelaDao {
    @Query("SELECT * FROM novelas")
    fun getAll(): List<Novela>

    @Insert
    fun insert(novela: Novela)

    @Delete
    fun delete(novela: Novela)
}
Base de Datos (Database): La base de datos principal que contiene la configuración de la base de datos y las entidades.  
@Database(entities = [Novela::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun novelaDao(): NovelaDao
}
Uso
Inicialización: La base de datos se inicializa en la clase MainActivity o en un ViewModel según las mejores prácticas de arquitectura.  
val db = Room.databaseBuilder(
    applicationContext,
    AppDatabase::class.java, "novelas-database"
).build()
Operaciones CRUD: Se realizan operaciones CRUD (Crear, Leer, Actualizar, Eliminar) a través del DAO.  
val novelaDao = db.novelaDao()
novelaDao.insert(Novela(titulo = "Nueva Novela"))
val novelas = novelaDao.getAll()
Corrección 🖇️
_Repositorio de github:_(https://github.com/SoniaTejeroRecio/gestionNovelasRoom_Sonia.git)
