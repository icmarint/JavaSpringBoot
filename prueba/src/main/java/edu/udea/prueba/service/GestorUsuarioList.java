package edu.udea.prueba.service;

import edu.udea.prueba.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GestorUsuarioList {

    private ArrayList<Usuario> usuarios;

    public GestorUsuarioList(){

        this.usuarios = new ArrayList<>();

        this.usuarios.add(new Usuario("Prueba1", "Prueba", "1234"));
        this.usuarios.add(new Usuario("Prueba1", "andres", "1234"));

    }

    public Usuario getUsuario(String nombreUsuario) throws Exception {
        for(Usuario usuario: this.usuarios){
            if(usuario.getNombreUsuario().equals(nombreUsuario)){
                return usuario;
            }
        }
        throw new Exception("Usuario no Existe");
    }

    public String setUsuario(Usuario usuario_parametro) throws Exception {
        try {
            getUsuario(usuario_parametro.getNombreUsuario());
        } catch (Exception e) {
            this.usuarios.add(usuario_parametro);
            return "Creación del usuario exitosa";
        }
        throw new Exception("Usuario Existe");
    }

    public Usuario updateUsuario(Usuario usuario_update, String id) throws Exception {
        try {
            Usuario usuario_bd = getUsuario(id);
            if(usuario_update.getNombreUsuario()!=null && !usuario_update.getNombreUsuario().equals("")) {
                usuario_bd.setNombreUsuario(usuario_update.getNombreUsuario());
            }

            if(usuario_update.getNombre()!=null && !usuario_update.getNombre().equals("")) {
                usuario_bd.setNombre(usuario_update.getNombre());
            }
            if(usuario_update.getPassword()!=null && !usuario_update.getPassword().equals("")) {
                usuario_bd.setPassword(usuario_update.getPassword());
            }
            return usuario_bd;
        } catch (Exception e) {
            throw new Exception("Usuario NO existe, fallo actualización de datos");
        }
    }

    public Usuario updateUsuarioAll(Usuario usuario_update, String id) throws Exception {
        try {
            Usuario usuario_bd = getUsuario(id);
            usuario_bd.setNombreUsuario(usuario_update.getNombreUsuario());
            usuario_bd.setNombre(usuario_update.getNombre());
            usuario_bd.setPassword(usuario_update.getPassword());

            return usuario_bd;

        } catch (Exception e) {
            throw new Exception("Usuario NO existe, fallo actualización de datos");
        }
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String deleteUsuario(String id) throws Exception {
        try{
            Usuario usuario = getUsuario(id);
            this.usuarios.remove(usuario);
            return "Eliminado Exitoso";
        }catch (Exception e){
            throw new Exception("Usuario NO existe para Eliminar");
        }
    }
}
