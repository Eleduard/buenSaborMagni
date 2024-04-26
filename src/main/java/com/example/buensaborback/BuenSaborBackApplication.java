package com.example.buensaborback;

import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.entities.enums.Estado;
import com.example.buensaborback.domain.entities.enums.FormaPago;
import com.example.buensaborback.domain.entities.enums.TipoEnvio;
import com.example.buensaborback.domain.entities.enums.TipoPromocion;
import com.example.buensaborback.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class BuenSaborBackApplication {
// Aca tiene que inyectar todos los repositorios
// Es por ello que deben crear el paquete reositorio

// Ejemplo  @Autowired
//	private ClienteRepository clienteRepository;

	private static final Logger logger = LoggerFactory.getLogger(BuenSaborBackApplication.class);

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ProvinciaRepository provinciaRepository;

	@Autowired
	private LocalidadRepository localidadRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private SucursalRepository	sucursalRepository;

	@Autowired
	private DomicilioRepository domicilioRepository;

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ArticuloInsumoRepository articuloInsumoRepository;

	@Autowired
	private ArticuloManufacturadoRepository articuloManufacturadoRepository;

	@Autowired
	private ImagenRepository imagenRepository;

	@Autowired
	private PromocionRepository promocionRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private  FacturaRepository facturaRepository;

	@Autowired
	private  PedidoRepository pedidoRepository;

	@Autowired
	private DetallePedidoRepository detallePedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(BuenSaborBackApplication.class, args);
		logger.info("Estoy activo en el main");
	}


	@Bean
	CommandLineRunner init() {
		return args -> {
			logger.info("----------------ESTOY----FUNCIONANDO---------------------");
			// Etapa del dashboard
			// Crear 1 pais
			// Crear 2 provincias para ese pais
			// crear 2 localidades para cada provincia
			Pais pais1 = Pais.builder().nombre("Argentina").build();
			Provincia provincia1 = Provincia.builder().nombre("Mendoza").pais(pais1).build();
			Provincia provincia2 = Provincia.builder().nombre("Cordoba").pais(pais1).build();
			Localidad localidad1 = Localidad.builder().nombre("Lujan de Cuyo").provincia(provincia1).build();
			Localidad localidad2 = Localidad.builder().nombre("Godoy Cruz").provincia(provincia1).build();
			Localidad localidad3 = Localidad.builder().nombre("Achiras").provincia(provincia2).build();
			Localidad localidad4 = Localidad.builder().nombre("Agua de Oro").provincia(provincia2).build();

			paisRepository.save(pais1);
			provinciaRepository.save(provincia1);
			provinciaRepository.save(provincia2);
			localidadRepository.save(localidad1);
			localidadRepository.save(localidad2);
			localidadRepository.save(localidad3);
			localidadRepository.save(localidad4);

			// Crear 1 empresa
			// Crear 2 sucursales para esa empresa
			// crear los Domicilios para esas sucursales
			Empresa empresaBrown = Empresa.builder().nombre("Lo de Brown").cuil(30503167).razonSocial("Venta de Alimentos").build();
			empresaRepository.save(empresaBrown);
			Sucursal sucursalChacras = Sucursal.builder().nombre("En chacras").horarioApertura(LocalTime.of(17,0)).horarioCierre(LocalTime.of(23,0)).empresa(empresaBrown).build();
			Sucursal sucursalGodoyCruz = Sucursal.builder().nombre("En godoy cruz").horarioApertura(LocalTime.of(16,0)).horarioCierre(LocalTime.of(23,30)).empresa(empresaBrown).build();
			sucursalRepository.save(sucursalChacras);
			sucursalRepository.save(sucursalGodoyCruz);
			Domicilio domicilioViamonte = Domicilio.builder().cp(5509).calle("Viamonte").numero(500).localidad(localidad1).build();
			Domicilio domicilioSanMartin = Domicilio.builder().cp(5511).calle("San Martin").numero(789).localidad(localidad2).build();
			domicilioRepository.save(domicilioViamonte);
			domicilioRepository.save(domicilioSanMartin);
			sucursalChacras.setDomicilio(domicilioViamonte);
			sucursalGodoyCruz.setDomicilio(domicilioSanMartin);
			empresaBrown.getSucursales().add(sucursalChacras);
			empresaBrown.getSucursales().add(sucursalGodoyCruz);

			// Crear Unidades de medida
			UnidadMedida unidadMedidaLitros = UnidadMedida.builder().denominacion("Litros").build();
			UnidadMedida unidadMedidaGramos = UnidadMedida.builder().denominacion("Gramos").build();
			UnidadMedida unidadMedidaCantidad = UnidadMedida.builder().denominacion("Cantidad").build();
			UnidadMedida unidadMedidaPorciones = UnidadMedida.builder().denominacion("Porciones").build();
			unidadMedidaRepository.save(unidadMedidaLitros);
			unidadMedidaRepository.save(unidadMedidaGramos);
			unidadMedidaRepository.save(unidadMedidaCantidad);
			unidadMedidaRepository.save(unidadMedidaPorciones);

			// Crear Categorías de productos y subCategorías de los mismos
			Categoria categoriaBebidas = Categoria.builder().denominacion("Bebidas").build();
			Categoria categoriaPizzas = Categoria.builder().denominacion("Pizzas").build();
			Categoria categoriaInsumos = Categoria.builder().denominacion("Insumos").build();
			Categoria categoriaGaseosas = Categoria.builder().denominacion("Gaseosas").build();
			Categoria categoriaTragos = Categoria.builder().denominacion("Tragos").build();
			categoriaRepository.save(categoriaBebidas);
			categoriaRepository.save(categoriaPizzas);
			categoriaRepository.save(categoriaInsumos);
			categoriaRepository.save(categoriaGaseosas);
			categoriaRepository.save(categoriaTragos);
			categoriaBebidas.getSubCategorias().add(categoriaGaseosas);
			categoriaBebidas.getSubCategorias().add(categoriaTragos);

			// Crear Insumos , coca cola , harina , etc
			ArticuloInsumo cocaCola = ArticuloInsumo.builder().denominacion("Coca cola").unidadMedida(unidadMedidaLitros).esParaElaborar(false).stockActual(5).stockMaximo(50).precioCompra(50.0).precioVenta(70.0).categoria(categoriaGaseosas).build();
			ArticuloInsumo harina = ArticuloInsumo.builder().denominacion("Harina").unidadMedida(unidadMedidaGramos).esParaElaborar(true).stockActual(4).stockMaximo(40).precioCompra(40.0).precioVenta(60.5).categoria(categoriaInsumos).build();
			ArticuloInsumo queso = ArticuloInsumo.builder().denominacion("Queso").unidadMedida(unidadMedidaGramos).esParaElaborar(true).stockActual(20).stockMaximo(50).precioCompra(23.6).precioVenta(66.6).categoria(categoriaInsumos).build();
			ArticuloInsumo tomate = ArticuloInsumo.builder().denominacion("Tomate").unidadMedida(unidadMedidaCantidad).esParaElaborar(true).stockActual(20).stockMaximo(50).precioCompra(23.6).precioVenta(66.6).categoria(categoriaInsumos).build();
			articuloInsumoRepository.save(cocaCola);
			articuloInsumoRepository.save(harina);
			articuloInsumoRepository.save(queso);
			articuloInsumoRepository.save(tomate);

			// Crear Promocion:
			Promocion promocion1 = Promocion.builder().denominacion("Promo XL").build();
			Promocion promocion2 = Promocion.builder().denominacion("Promo L").build();
			promocionRepository.save(promocion1);
			promocionRepository.save(promocion2);

			// crear fotos para cada insumo
			Imagen imagenCoca = Imagen.builder().url("https://m.media-amazon.com/images/I/51v8nyxSOYL._SL1500_.jpg").articulo(cocaCola).promocion(promocion1).build();
			Imagen imagenHarina = Imagen.builder().url("https://mandolina.co/wp-content/uploads/2023/03/648366622-1024x683.jpg").articulo(harina).promocion(promocion1).build();
			Imagen imagenQueso = Imagen.builder().url("https://superdepaso.com.ar/wp-content/uploads/2021/06/SANTAROSA-PATEGRAS-04.jpg").articulo(queso).promocion(promocion1).build();
			Imagen imagenTomate = Imagen.builder().url("https://thefoodtech.com/wp-content/uploads/2020/06/Componentes-de-calidad-en-el-tomate-828x548.jpg").articulo(tomate).promocion(promocion1).build();

			imagenRepository.save(imagenCoca);
			imagenRepository.save(imagenHarina);
			imagenRepository.save(imagenQueso);
			imagenRepository.save(imagenTomate);

			cocaCola.getImagenes().add(imagenCoca);
			harina.getImagenes().add(imagenHarina);
			queso.getImagenes().add(imagenQueso);
			tomate.getImagenes().add(imagenTomate);

			// Crear Articulos Manufacturados
			ArticuloManufacturado pizzaMuzarella = ArticuloManufacturado.builder().denominacion("Pizza Muzarella").descripcion("Una pizza clasica").unidadMedida(unidadMedidaPorciones).precioVenta(130.0).tiempoEstimadoMinutos(15).preparacion("Esto se prepara asi").categoria(categoriaPizzas).build();
			ArticuloManufacturado pizzaNapolitana = ArticuloManufacturado.builder().denominacion("Pizza Napolitana").descripcion("Una pizza clasica con tomate").unidadMedida(unidadMedidaPorciones).precioVenta(150.0).tiempoEstimadoMinutos(15).preparacion("Esto se prepara asi").categoria(categoriaPizzas).build();
			articuloManufacturadoRepository.save(pizzaMuzarella);
			articuloManufacturadoRepository.save(pizzaNapolitana);

			// Crear fotos para los artículos manufacturados
			Imagen imagenPizzaMuzarella = Imagen.builder().url("https://storage.googleapis.com/fitia-api-bucket/media/images/recipe_images/1002846.jpg").articulo(pizzaMuzarella).promocion(promocion2).build();
			Imagen imagenPizzaNapolitana = Imagen.builder().url("https://assets.elgourmet.com/wp-content/uploads/2023/03/8metlvp345_portada-pizza-1024x686.jpg.webp").articulo(pizzaNapolitana).promocion(promocion2).build();
			imagenRepository.save(imagenPizzaMuzarella);
			imagenRepository.save(imagenPizzaNapolitana);

			pizzaMuzarella.getImagenes().add(imagenPizzaMuzarella);
			pizzaNapolitana.getImagenes().add(imagenPizzaNapolitana);

			// Establcer las relaciones entre estos objetos.
			ArticuloManufacturadoDetalle detalleHarinaPizzaMuzarella = ArticuloManufacturadoDetalle.builder().cantidad(10d).articuloInsumo(harina).articuloManufacturado(pizzaMuzarella).build();
			ArticuloManufacturadoDetalle detalleQuesoPizzaMuzarella = ArticuloManufacturadoDetalle.builder().cantidad(20d).articuloInsumo(queso).articuloManufacturado(pizzaMuzarella).build();
			ArticuloManufacturadoDetalle detalleHarinaPizzaNapolatina = ArticuloManufacturadoDetalle.builder().cantidad(30d).articuloInsumo(harina).articuloManufacturado(pizzaNapolitana).build();
			ArticuloManufacturadoDetalle detalleQuesoPizzaNapolatina = ArticuloManufacturadoDetalle.builder().cantidad(10d).articuloInsumo(queso).articuloManufacturado(pizzaNapolitana).build();
			ArticuloManufacturadoDetalle detalleTomatePizzaNapolatina = ArticuloManufacturadoDetalle.builder().cantidad(20d).articuloInsumo(tomate).articuloManufacturado(pizzaNapolitana).build();
			articuloManufacturadoDetalleRepository.save(detalleHarinaPizzaMuzarella);
			articuloManufacturadoDetalleRepository.save(detalleQuesoPizzaMuzarella);
			articuloManufacturadoDetalleRepository.save(detalleHarinaPizzaNapolatina);
			articuloManufacturadoDetalleRepository.save(detalleQuesoPizzaNapolatina);
			articuloManufacturadoDetalleRepository.save(detalleTomatePizzaNapolatina);
			pizzaMuzarella.getArticuloManufacturadoDetalles().add(detalleHarinaPizzaMuzarella);
			pizzaMuzarella.getArticuloManufacturadoDetalles().add(detalleQuesoPizzaMuzarella);
			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalleHarinaPizzaNapolatina);
			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalleQuesoPizzaNapolatina);
			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalleTomatePizzaNapolatina);

			// Establecer relaciones de las categorias
			categoriaInsumos.getArticulos().add(harina);
			categoriaInsumos.getArticulos().add(tomate);
			categoriaInsumos.getArticulos().add(queso);
			categoriaGaseosas.getArticulos().add(cocaCola);
			categoriaPizzas.getArticulos().add(pizzaMuzarella);
			categoriaPizzas.getArticulos().add(pizzaNapolitana);
			categoriaRepository.save(categoriaInsumos);
			categoriaRepository.save(categoriaGaseosas);
			categoriaRepository.save(categoriaPizzas);

			// Crear promocion para sucursal - Dia de los enamorados
			// Tener en cuenta que esa promocion es exclusivamente para una sucursal determinada d euna empresa determinada
			Promocion promocionDiaEnamorados = Promocion.builder().denominacion("Dia de los Enamorados")
					.fechaDesde(LocalDate.of(2024,2,13))
					.fechaHasta(LocalDate.of(2024,2,15))
					.horaDesde(LocalTime.of(0,0))
					.horaHasta(LocalTime.of(23,59))
					.descripcionDescuento("14 de febrero es el día de los enamorados")
					.precioPromocional(180d)
					.tipoPromocion(TipoPromocion.Promocion)
					.build();
			promocionDiaEnamorados.getArticulos().add(cocaCola);
			promocionDiaEnamorados.getArticulos().add(pizzaNapolitana);
			promocionRepository.save(promocionDiaEnamorados);

			Imagen imagenPromocionEnamorados = Imagen.builder().url("https://www.bbva.com/wp-content/uploads/2021/02/san-valentin-14-febrero-corazon-amor-bbva-recurso-1920x1280-min.jpg").articulo(pizzaNapolitana).promocion(promocionDiaEnamorados).build();
			imagenRepository.save(imagenPromocionEnamorados);

			promocionDiaEnamorados.getImagenes().add(imagenPromocionEnamorados);

			promocionRepository.save(promocionDiaEnamorados);

			//Agregar categorias y promociones a sucursales
			sucursalChacras.getCategorias().add(categoriaBebidas);
			sucursalChacras.getCategorias().add(categoriaPizzas);
			sucursalChacras.getPromociones().add(promocionDiaEnamorados);

			sucursalGodoyCruz.getCategorias().add(categoriaBebidas);
			sucursalGodoyCruz.getCategorias().add(categoriaPizzas);

			sucursalRepository.save(sucursalChacras);
			sucursalRepository.save(sucursalGodoyCruz);


			logger.info("Sucursal Chacras: nombre={}, horarioApertura={}, horarioCierre={}",sucursalChacras.getNombre(), sucursalChacras.getHorarioApertura(), sucursalChacras.getHorarioCierre());
			logger.info("Sucursal Godoy Cruz: nombre={}, horarioApertura={}, horarioCierre={}",sucursalGodoyCruz.getNombre(), sucursalGodoyCruz.getHorarioApertura(), sucursalGodoyCruz.getHorarioCierre());


			//agregar domicilios de cliente
			Domicilio domicilioCliente1 = Domicilio.builder().calle("Sarmiento").numero(123).cp(5507).localidad(localidad1).build();
			Domicilio domicilioCliente2 = Domicilio.builder().calle("San martin").numero(412).cp(5501).localidad(localidad2).build();
			domicilioRepository.save(domicilioCliente1);
			domicilioRepository.save(domicilioCliente2);

			// agregar usuario
			Usuario usuario1 = Usuario.builder().username("pepe-honguito75").auth0Id("iVBORw0KGgoAAAANSUhEUgAAAK0AAACUCAMAAADWBFkUAAABEVBMVEX").build();
			usuarioRepository.save(usuario1);

			Imagen imagenUsuario = Imagen.builder().url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsa2xSPPay4GD7E3cthBMCcvPMADEjFufUWQ&s").build();
			imagenRepository.save(imagenUsuario);

			// agregar cliente
			Cliente cliente1 = Cliente.builder().nombre("Alejandro").email("alex@gmail.com").apellido("Lencinas").telefono("2634666666").usuario(usuario1).fechaNacimiento(LocalDate.of(1990, 12, 15)).imagen(imagenUsuario).build();
			cliente1.getDomicilios().add(domicilioCliente1);
			cliente1.getDomicilios().add(domicilioCliente2);
			clienteRepository.save(cliente1);

			// agregar factura
			Factura factura = Factura.builder().fechaFacturacion(LocalDate.of(2024, 2, 13)).formaPago(FormaPago.MercadoPago).mpMerchantOrderId(1).mpPaymentId(1).mpPaymentType("mercado pago").mpPreferenceId("0001").totalVenta(2500d).build();
			facturaRepository.save(factura);

			// agregar pedido
			Pedido pedido = Pedido.builder()
					.domicilio(domicilioCliente1)
					.estado(Estado.Entregado)
					.factura(factura)
					.formaPago(FormaPago.MercadoPago)
					.fechaPedido(LocalDate.of(2024, 4, 18))
					.horaEstimadaFinalizacion(LocalTime.of(12, 30))
					.sucursal(sucursalChacras)
					.tipoEnvio(TipoEnvio.Delivery)
					.total(200d)
					.totalCosto(180d)
					.cliente(cliente1)
					.build();
			pedidoRepository.save(pedido);

			cliente1.getPedidos().add(pedido);

			// agregar detalle pedido
			DetallePedido detallePedido1 = DetallePedido.builder().articulo(pizzaMuzarella).cantidad(1).subTotal(130d).pedido(pedido).build();
			DetallePedido detallePedido2 = DetallePedido.builder().articulo(cocaCola).cantidad(1).subTotal(70d).pedido(pedido).build();
			detallePedidoRepository.save(detallePedido1);
			detallePedidoRepository.save(detallePedido2);

			pedido.getDetallePedidos().add(detallePedido1);
			pedido.getDetallePedidos().add(detallePedido2);

		};
	}
}

