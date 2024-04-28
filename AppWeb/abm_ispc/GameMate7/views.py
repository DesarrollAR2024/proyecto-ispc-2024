from django.shortcuts import render
from django.contrib.auth import authenticate, login, logout
from rest_framework import viewsets
from rest_framework import status, generics
from .serializer import UserSerializer, ProductoSerializer, CategoriaSerializer, ProveedorSerializer, FacturacionSerializer, AgregarProductoSerializer
from .models import Usuarios, Producto, Categoria, Proveedor, Facturacion, CustomUser
from rest_framework.permissions import IsAdminUser, AllowAny, IsAuthenticated
from rest_framework.response import Response
from rest_framework.views import APIView
from django.urls import reverse
# se agrega paypal
from paypal.standard.forms import PayPalPaymentsForm

import json
from django.shortcuts import get_object_or_404

# Se agrega para gestionar vista de login y logout
from django.contrib.auth import authenticate, login, logout
from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.permissions import BasePermission

# Create your views here.


class UsuariosViewSet(viewsets.ModelViewSet):
    queryset = CustomUser.objects.all()
    serializer_class = UserSerializer


class verProductos(viewsets.ReadOnlyModelViewSet):
    permission_classes = [AllowAny]
    # permission_classes = [IsAuthenticated]
    queryset = Producto.objects.all()
    serializer_class = ProductoSerializer
    http_method_names = ['get']


class agregarProducto(generics.ListCreateAPIView):
    queryset = Producto.objects.all()
    serializer_class = AgregarProductoSerializer
    http_method_names = ['get', 'post']
   # permission_classes = [IsAdminUser]
    permission_classes = [AllowAny]

    def get_objet(self):
        queryset = self.get_queryset()
        serializer = AgregarProductoSerializer(queryset, many=True)
        if self.request.user.is_authenticated:
            return Response(serializer.data)


class modificarProducto(generics.RetrieveUpdateDestroyAPIView):
    queryset = Producto.objects.all()
    serializer_class = AgregarProductoSerializer
    #permission_classes = [IsAdminUser]
    permission_classes = [AllowAny]


class verCategorias(viewsets.ReadOnlyModelViewSet):
    permission_classes = [AllowAny]
    queryset = Categoria.objects.all()
    serializer_class = CategoriaSerializer


class verProveedores(viewsets.ModelViewSet):
    permission_classes = [AllowAny]
    queryset = Proveedor.objects.all()
    serializer_class = ProveedorSerializer

# PAGOS


class FacturacionViewSet(viewsets.ModelViewSet):
    queryset = Facturacion.objects.all()
    serializer_class = FacturacionSerializer

    def perform_create(self, serializer):
        # aquí obtenemos el id del usuario
        usuario_id = self.request.data.get('usuario')
        # y lo buscamos en la base de datos
        usuario = Usuarios.objects.get(id=usuario_id)
        # pasamos el objeto encontrado al serializer
        serializer.save(usuario=usuario)

# VISTA DE LOS USUARIOS REGISTRADOS


class ProfileView(generics.RetrieveUpdateAPIView):
    # Solo usuarios logueados pueden ver.
    permission_classes = [IsAuthenticated]
    serializer_class = UserSerializer
    http_method_names = ['get', 'patch']

    def get_object(self):
        if self.request.user.is_authenticated:
            return self.request.user

# Se agregan las clase de login y logout


class LoginView(APIView):
    permission_classes = [AllowAny]

    def post(self, request):
        email = request.data.get('email', None)
        password = request.data.get('password', None)
        user = authenticate(email=email, password=password)

        if user:
            login(request, user)
            return Response(UserSerializer(user).data, status=status.HTTP_200_OK)

        return Response(status=status.HTTP_404_NOT_FOUND)


class LogoutView(APIView):
    permission_classes = [AllowAny]

    def post(self, request):
        logout(request)

        return Response(status=status.HTTP_200_OK)


class SignupView(generics.CreateAPIView):
    serializer_class = UserSerializer


class ListarUsuarios(generics.ListCreateAPIView):
    queryset = CustomUser.objects.all()
    serializer_class = UserSerializer
    http_method_names = ['get', 'post']
    permission_classes = [IsAdminUser]
    # permission_classes = [AllowAny]

    def get_objet(self):
        queryset = self.get_queryset()
        serializer = UserSerializer(queryset, many=True)
        if self.request.user.is_authenticated:
            return Response(serializer.data)


class modificarUsuario(generics.RetrieveUpdateDestroyAPIView):
    queryset = CustomUser.objects.all()
    serializer_class = UserSerializer
    permission_classes = [IsAdminUser]
    # permission_classes = [AllowAny]

def home(request):
    paypal_dict= {
        'business': 'id@business.example.com',
        'amount': '1.00',
        'currency_code': 'GBP',
        'item_name': 'book',
        'notify_url': request.build_absolute_uri(reverse('paypal-ipn')),
        'return':request.build_absolute_uri(reverse('successful')),
        'cancel_return':request.build_absolute_uri(reverse('cancelled')),
    }

    form= PayPalPaymentsForm(initial=paypal_dict)
    context= {'form': form}
    return render(request, 'index.html', context)