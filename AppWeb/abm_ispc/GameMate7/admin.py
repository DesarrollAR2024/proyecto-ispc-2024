#from django.contrib import admin
#from .models import Categoria, Cliente, Compatibilidad, Direccion, Dispositivos, Envio, Facturacion, Galeria, Juegos, Jugar, Orden, Pedido, Producto, Proveedor, Redes, Usuarios

from django.contrib import admin
from .models import Categoria, Cliente, Proveedor, Direccion, Usuarios, Juegos, Jugar, Dispositivos, Compatibilidad, Envio, Orden, Facturacion, Galeria, Pedido, Producto, Redes, CustomUser

# Register your models here.

class ClienteAdmin(admin.ModelAdmin):
    list_display = ("id_cliente", "nombre_completo", "apellido", "dni", "telefono", "nickname")
class CategoriaAdmin(admin.ModelAdmin):
    list_display = ("id_categoria", "nombre", "informacion")
class ProveedorAdmin(admin.ModelAdmin):
    list_display = ("id_proveedor", "nombre", "telefono", "cuil", "telefono", "email")
class DireccionAdmin(admin.ModelAdmin):
    list_display = ("id_direccion", "direccion", "cod_post", "provincia", "localidad")
class UsuariosAdmin(admin.ModelAdmin):
    list_display = ("user", "nombre", "apellido", "fecha_nac", "provincia", "email")
class JuegosAdmin(admin.ModelAdmin):
    list_display = ("id_juego", "juego")
class JugarAdmin(admin.ModelAdmin):
    list_display = ("nivel_jugador",)
class DispositivosAdmin(admin.ModelAdmin):
    list_display = ("id_dispositivos", "pc")
class EnvioAdmin(admin.ModelAdmin):
    list_display = ("id_envio", "fecha_envio", "localidad", "provincia", "cod_post", "estado_envio", "direccion_envio", "metodo_envio")
class OrdenAdmin(admin.ModelAdmin):
    list_display = ("id_orden", "fecha", "monto_total", "precio_unitario", "cantidad_pedido")
class FacturacionAdmin(admin.ModelAdmin):
    list_display = ("numero_factura", "metodo_pago", "costo_envio", "fecha_factura", "fecha_pago", "monto_total", "estado_pago")
class PedidoAdmin(admin.ModelAdmin):
    list_display = ("id_pedido", "fecha_pedido", "estado_pedido")
class ProductoAdmin(admin.ModelAdmin):
    list_display = ("id_producto", "precio", "nombre", "descripcion")
class RegistroAdmin(admin.ModelAdmin):
    list_display = ( "nombre", "apellido", 'nombre_usuario', "email" )
class CustomUserAdmin(admin.ModelAdmin):
    list_display = ( "email",)
   

admin.site.register(Categoria)
admin.site.register(Cliente, ClienteAdmin)
admin.site.register(Proveedor)
admin.site.register(Direccion)
admin.site.register(Usuarios)
admin.site.register(Juegos)
admin.site.register(Jugar)
admin.site.register(Dispositivos)
admin.site.register(Compatibilidad)
admin.site.register(Envio)
admin.site.register(Orden)
admin.site.register(Facturacion)
admin.site.register(Galeria)
admin.site.register(Pedido)
admin.site.register(Producto)
admin.site.register(Redes)
admin.site.register(CustomUser, CustomUserAdmin)
















