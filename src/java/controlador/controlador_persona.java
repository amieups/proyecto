/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import modelo.Persona;
/**
 *
 * @author AnthOnY
 */
@ManagedBean(name = "c_persona")

public class controlador_persona {
    
    conexion c=new conexion();
      private Persona p=new Persona();
    private static List<controlador_persona> lista=new ArrayList<>();

   
    public Persona getP() {
        return p;
    }

    public void setP(Persona p) {
        this.p = p;
    }

    public List<controlador_persona> getLista() {
        return lista;
    }

    public void setLista(List<controlador_persona> lista) {
        controlador_persona.lista = lista;
    }
    
    
    
      //Guardar
    public void insertar ()throws SQLException{
      
        Connection con=c.getConexion();
        if(con != null){
            String sql = "INSERT into persona (nombre,apellido,cedula,correo,contraseña,id_tipo_person) VALUES"
                    + "('"+ p.getNombre()+"','"+p.getApellido()+"','"+p.getCedula()
                    +"','"+p.getCorreo()+"','"+p.getContraseña()+"','"+p.getTipo()
                    +"');";
            c.Ejecutar(sql);
        }
        
        c.cerrarConexion();
        p.setNombre("");
        p.setApellido("");
        p.setCedula("");
        p.setCorreo("");
        p.setContraseña("");
        p.setTipo("");
       
    }
   
          public void listar_tabla() throws SQLException{
        String sql ="SELECT nombre,apellido,cedula,correo,tipo from persona, tipo_persona where tipo_persona.id_tipo_person= persona.id_tipo_person";
        ResultSet rs =null;
        conexion con = new conexion();
        lista.clear();
        rs=con.Consulta(sql);
        while (rs.next()){
            controlador_persona ce = new controlador_persona();
            
           
            ce.p.setNombre(rs.getString(1));
            ce.p.setApellido(rs.getString(2));
             ce.p.setCedula(rs.getString(3));
             ce.p.setCorreo(rs.getString(4));
             ce.p.setTipo(rs.getString(5));
         
       
            lista.add(ce);
            
        }
        con.cerrarConexion();
    }
    
    
}

