package edu.udea.prueba.repositorio;

import edu.udea.prueba.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    @Modifying
    @Query("UPDATE Usuario u SET u.nombre= :nombre, u.password= :password WHERE u.nombreUsuario= :id")
    public int update(String nombre, String password, String id);
}
