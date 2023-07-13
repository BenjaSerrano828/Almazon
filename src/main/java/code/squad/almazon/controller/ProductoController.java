package code.squad.almazon.controller;


import code.squad.almazon.model.Producto;
import code.squad.almazon.model.Usuario;
import code.squad.almazon.service.IUsuarioService;
import code.squad.almazon.service.IProductoService;
import code.squad.almazon.service.UploadFileService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private UploadFileService upload;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("productos",productoService.findAll());
        return "productos/show";
    }
    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        LOGGER.info("Este es el objeto producto: {}",producto);

        Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        producto.setUsuario(u);

        //imagen
        if (producto.getId() == null) { // cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        productoService.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Producto producto = new Producto();
        Optional<Producto> optionalProducto =productoService.get(id);
        producto = optionalProducto.get();

        LOGGER.info("Producto buscado: {}",producto);
        model.addAttribute("producto",producto);
        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        Producto p = new Producto();
        p = productoService.get(producto.getId()).get();
        if (file.isEmpty()) { // cuando editamos el producto pero no cambiamos la imagen
            producto.setImagen(p.getImagen());
        }else { // cuando editamos el producto y cambiamos la imagen
            if (!p.getImagen().equals("default.jpg")){ // eliminar cuando no sea la imagen por defecto
                upload.deleteImage(p.getImagen());
            }
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(p.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Producto p;
        p = productoService.get(id).get();
        if (!p.getImagen().equals("default.jpg")){ // eliminar cuando no sea la imagen por defecto
            upload.deleteImage(p.getImagen());
        }
        productoService.delete(id);
        return "redirect:/productos";
    }

}
