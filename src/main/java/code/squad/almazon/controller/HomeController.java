package code.squad.almazon.controller;

import code.squad.almazon.model.DetalleOrden;
import code.squad.almazon.model.Orden;
import code.squad.almazon.model.Producto;
import code.squad.almazon.model.Usuario;
import code.squad.almazon.service.IDetalleOrdenService;
import code.squad.almazon.service.IOrdenService;
import code.squad.almazon.service.IUsuarioService;
import code.squad.almazon.service.IProductoService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IDetalleOrdenService detalleOrdenService;

    //para almacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    // datos de la orden
    Orden orden = new Orden();

    @GetMapping("")
    public String home(Model model, HttpSession session){

        log.info("Session del usuario: {}",session.getAttribute("idusuario"));
        model.addAttribute("productos",productoService.findAll());
        return "usuario/home";
    }
    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model){
        log.info("Id producto enviado como parametro {}",id);
        Producto producto;
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();
        model.addAttribute("producto", producto);

        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;
        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("Producto añadido: {}",optionalProducto.get());
        log.info("Cantidad: {}",cantidad);
        producto = optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);

        // validar que el producto no se añada mas de 2 veces
        Integer idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        if (!ingresado){
            detalles.add(detalleOrden);
        }

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart",detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }
    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable Integer id, Model model) {

        List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

        for (DetalleOrden detalleOrden: detalles) {
            if (detalleOrden.getProducto().getId() != id){
                ordenesNueva.add(detalleOrden);
            }
        }
        detalles = ordenesNueva;

        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart",detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }
    @GetMapping("/getCart")
    public String getCart(Model model){
        model.addAttribute("cart",detalles);
        model.addAttribute("orden", orden);
        return "/usuario/carrito";
    }
    @GetMapping("/order")
    public String order(Model model, HttpSession session){

        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        model.addAttribute("cart",detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario",usuario);

        return "usuario/resumenorden";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session){
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        orden.setUsuario(usuario);
        ordenService.save(orden);

        for (DetalleOrden dt: detalles){
            dt.setOrden(orden);
            detalleOrdenService.save(dt);
        }

        orden = new Orden();
        detalles.clear();

        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model){
        log.info("Nombre del prodcuto: {}",nombre);
        List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
        model.addAttribute("productos",productos);

        return "usuario/home";
    }




}
