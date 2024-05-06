from django.urls import path, include
from rest_framework import routers
from GameMate7 import views
from .views import LoginView, LogoutView, SignupView, ProfileView, ListarUsuarios, agregarProducto, modificarProducto, modificarUsuario
router = routers.DefaultRouter()
# router.register(r'usuarios', views.UsuariosViewSet)
router.register(r'productos', views.verProductos)
router.register(r'categorias', views.verCategorias)
router.register(r'proveedores', views.verProveedores)
router.register(r'facturaciones', views.FacturacionViewSet)


urlpatterns = [
    path('', include(router.urls)),
    path('auth/login/', 
         LoginView.as_view(), name='auth_login'),
    path('auth/logout/', 
         LogoutView.as_view(),           name='auth_logout'),
    path('auth/reset/', 
         include('django_rest_passwordreset.urls',
            namespace='password_reset')),
    path('auth/registro/', 
         SignupView.as_view(), name='auth_signup'),
    path('user/profile/', 
         ProfileView.as_view(), name='user_profile'),
    path('usuarios/', 
         ListarUsuarios.as_view(), name='listar_usuarios'),
    path('modificarusuario/<int:pk>/',            modificarUsuario.as_view(),
    name='modificar_usuarios'),
    path('modificarproducto/<int:pk>/',
        modificarProducto.as_view(), name='modificar_producto'),
    path('agregarproducto/', 
        agregarProducto.as_view(), name='agregar_producto'),
    

]

