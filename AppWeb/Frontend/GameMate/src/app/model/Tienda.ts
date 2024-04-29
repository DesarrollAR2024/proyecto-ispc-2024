export class Tienda {
    id_producto!: number;
    precio: number;
    stock: number;
    nombre: string;
    descripcion: string;
    categoria: string;
    imagen: string;
    cantidad: number;

    constructor(precio: number, stock: number, nombre: string, descripcion: string, categoria: string, imagen: string) {
        this.precio = precio;
        this.stock = stock;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.imagen = imagen;
        this.cantidad = 0;
    }
}