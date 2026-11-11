package cl.duoc.veterinaria.repository;

import cl.duoc.veterinaria.model.Mascota;
import cl.duoc.veterinaria.utils.Conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Recibe los datos verificados
public class MascotaRepository {
    private Connection coneLocal;
    //Traspasar la variable de un lugar a otro, desde utils a reposiroty

    public MascotaRepository() {
    }
    //Crear 5 metodos
    public boolean agregarMascota(Mascota m) throws SQLException {
        //Partimos con try catch para controlar errores
        boolean inserto = false;
        try{
            //Aqui estamos llamando a la clase (CONSTRUCTOR VACÍO DE UTILS)
            coneLocal= new Conexion().getCone();

            //Comando que ejecuta lo que esta dentro de STRING sql como COMANDO sql
            String sql = "INSERT INTO mascota VALUES(?,?,?)";
            PreparedStatement ps = coneLocal.prepareStatement(sql);

            //Cambia los valores dentro de String sql (?,?,?) por orden de dato
            ps.setInt(1,m.getChip());
            ps.setString(2,m.getNombre());
            Date fechita = Date.valueOf(m.getFechaNacimiento());
            ps.setDate(3, fechita);

            //Ejcuta el comando SQL y nos entrega un 1 o un 0.
            // Uno significa que los datos se insertaron los datos en la base de datos,
            // y cero significa que no se insertaron.
            int res = ps.executeUpdate();
            if(res==1){
                inserto = true;
            }
        }catch (Exception e) {

            //Barrera que siempre pasa independiente este bien o mal y cierra la conexión
        }finally{
            coneLocal.close();

        }
        return inserto;
    }
    public void verMascotas(){}
    public void verMascota(int chipMostrar){}
    public boolean eliminarMascota(int chipEliminar) throws SQLException {
        boolean elimino = false;
        try {
            coneLocal = new Conexion().getCone();
            String sql = "DELETE FROM mascota WHERE chip=?";
            PreparedStatement ps = coneLocal.prepareStatement(sql);
            ps.setInt(1, chipEliminar);

            //executeUpdate() nos entrega un 1 si se eliminó la mascota y un 0 si no se eliminó.
            int res = ps.executeUpdate();
            if(res==1){
                elimino = true;
            }
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        } finally {
            coneLocal.close();
        }
        return elimino;
    }
    public boolean modificarMascota(Mascota m) throws SQLException {
        boolean modifico = false;
        try{
            //Aqui estamos llamando a la clase (CONSTRUCTOR VACÍO DE UTILS)
            coneLocal= new Conexion().getCone();

            //Comando que ejecuta lo que esta dentro de STRING sql como COMANDO sql
            String sql = "UPDATE mascota set nombre=?,fecha_nacimiento=? where chip=?";
            //AQUI MODIFICA EL COMANDO SQL PARA ACTUALIZAR
            PreparedStatement ps = coneLocal.prepareStatement(sql);

            //Cambia los valores dentro de String sql (?,?,?) por orden de dato
            ps.setInt(3,m.getChip());
            ps.setString(1,m.getNombre());
            Date fechita = Date.valueOf(m.getFechaNacimiento());
            ps.setDate(2, fechita);

            //Ejcuta el comando SQL y nos entrega un 1 o un 0.
            // Uno significa que los datos se insertaron los datos en la base de datos,
            // y cero significa que no se insertaron.
            int res = ps.executeUpdate();
            if(res==1){
                modifico = true;
            }
        }catch (Exception e) {

            //Barrera que siempre pasa independiente este bien o mal y cierra la conexión
        }finally{
            coneLocal.close();

        }
        return modifico;
    }
}
