from rest_framework import serializers
from .models import Usuarios, Producto, Categoria, Proveedor, Facturacion, CustomUser
from django.contrib.auth import get_user_model
from django.contrib.auth.hashers import make_password

# class UsuariosSerializer(serializers.ModelSerializer):
#  class Meta:
#   model= CustomUser
#   fields='__all__'


class UserSerializer(serializers.ModelSerializer):
    usuario = serializers.CharField(
        max_length=20)
    nombre = serializers.CharField(
        max_length=20)
    apellido = serializers.CharField(
        max_length=20)
    email = serializers.EmailField(
        required=True)
    password = serializers.CharField(
        min_length=8)
    is_active = serializers.BooleanField(default=True)
    is_admin = serializers.BooleanField(default=False)
    is_staff = serializers.BooleanField(default=False)

    class Meta:
        model = get_user_model()
        fields = ('usuario', 'nombre', 'apellido', 'email',
                  'password', 'is_active', 'is_admin', 'is_staff')

    def validate_password(self, value):
        return make_password(value)


class ProductoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Producto
        fields = '__all__'


class AgregarProductoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Producto
        fields = '__all__'


class CategoriaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Categoria
        fields = '__all__'


class ProveedorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Proveedor
        fields = '__all__'


class FacturacionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Facturacion
        fields = '__all__'
