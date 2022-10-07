package edu.udea.prueba.controlador;

import edu.udea.prueba.model.ROLES;
import edu.udea.prueba.model.Usuario;
import edu.udea.prueba.service.GestorUsuarioInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FrontendController {

    @Autowired
    private GestorUsuarioInterface gestorUsuario;

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("formUsuario", new Usuario());
        return "login";
    }

    @GetMapping("/welcome")
    public String getWelcome(Model model){
        model.addAttribute("usuarios", gestorUsuario.getUsuarios());
        return "welcome";
    }

    @GetMapping("/addUser")
    public String getAddUser(Model model){
        model.addAttribute("user", new Usuario());
        model.addAttribute("ROLES", ROLES.values());
        return "add-user";
    }

    @PostMapping("/usuario_parametro/front")
    public String postUsuario(
            @ModelAttribute("user") Usuario usuario_parametro){
        try {
            String mensaje = gestorUsuario.setUsuario(usuario_parametro);
            return "redirect:/welcome";
        } catch (Exception e) {
            return "redirect:/error";
        }

    }

    @GetMapping("/update-user")
    public String getUpdateUser(Model model){
        return "update-user";
    }

    @GetMapping("/usuario_parametro/front/{id}")
    public String getUsuario(@PathVariable String id, Model model){
        try {
            model.addAttribute("userUpdate", gestorUsuario.getUsuario(id));
            model.addAttribute("ROLES", ROLES.values());
            return "update-user";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @DeleteMapping("/usuario/front/{id}")
    public String deleteUser(@PathVariable String id, Model model){
        try{
            gestorUsuario.deleteUsuario(id);
            return "redirect:/welcome";
        } catch (Exception e){
            return "redirect:/error";
        }
    }

    @PutMapping("/usuario_parametro/front/update")
    public String putUsuario(@ModelAttribute("userUpdate") Usuario usuario){
        try {
            gestorUsuario.updateUsuario(usuario, usuario.getNombreUsuario());
            return "redirect:/welcome";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

}
