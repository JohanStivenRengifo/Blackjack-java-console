package estructuras;

import java.util.Iterator;

/*
 * Implementación de Lista Enlazada Simple.
 */
public class ListaEnlazada<T> implements Iterable<T> {

    private Nodo<T> cabeza;
    private int tamaño;

    /*
     * Nodo interno con patrón de diseño Memento implícito
     * para mantener el estado de cada elemento.
     */
    private static class Nodo<T> {
        private final T dato;
        private Nodo<T> siguiente;

        /*
         * Constructor que implementa patrón Builder para nodos
         * 
         * @param dato Elemento a almacenar
         */
        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    /*
     * Constructor por defecto.
     * Inicialización eficiente con estado vacío.
     */
    public ListaEnlazada() {
        this.cabeza = null;
        this.tamaño = 0;
    } /*
       * Inserción al final de la lista.
       * Complejidad: O(n) - requiere recorrer hasta el último nodo
       * 
       * @param dato Elemento a insertar
       * 
       * @throws IllegalArgumentException si el dato es null
       */

    public void agregar(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException("No se permiten elementos null");
        }

        Nodo<T> nuevoNodo = new Nodo<>(dato);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }

        tamaño++;
    } /*
       * Elimina y retorna el primer elemento.
       * Complejidad: O(1) - operación directa sobre la cabeza
       * 
       * @return Elemento eliminado o null si la lista está vacía
       */

    public T eliminarPrimero() {
        if (estaVacia()) {
            return null;
        }

        T dato = cabeza.dato;
        cabeza = cabeza.siguiente;
        tamaño--;

        return dato;
    }

    /*
     * Verificación de estado vacío.
     * Complejidad: O(1) - checkeo directo de la cabeza
     * 
     * @return true si no hay elementos, false en caso contrario
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    public int tamaño() {
        return tamaño;
    }

    /*
     * Genera representación visual de la estructura.
     * Complejidad: O(n) - requiere recorrido completo
     * Formato: [elem1] -> [elem2] -> ... -> null
     * 
     * @return Cadena con la representación visual de la lista
     */
    @Override
    public String toString() {
        if (estaVacia()) {
            return "[]->null";
        }
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = cabeza;
        sb.append("[").append(actual.dato).append("]->");
        while (actual.siguiente != null) {
            actual = actual.siguiente;
            sb.append("[").append(actual.dato).append("]->");
        }
        sb.append("null"); // Terminador de lista
        return sb.toString();
    }

    public String recorrerRecursivo() {
        return recorrerRecursivoHelper(cabeza, new StringBuilder()).toString();
    }

    /*
     * Método auxiliar para la recursión.
     * Implementa el caso base y la recursión.
     */
    private StringBuilder recorrerRecursivoHelper(Nodo<T> nodo, StringBuilder sb) {
        if (nodo == null) {
            return sb.append("null");
        }
        sb.append("[").append(nodo.dato).append("]->");
        return recorrerRecursivoHelper(nodo.siguiente, sb);
    }

    public T obtenerElemento(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException(
                    String.format("Índice %d fuera de rango [0,%d]", indice, tamaño - 1));
        }

        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    /*
     * Implementación del patrón Iterator.
     * Permite uso de la estructura en bucles for-each.
     * 
     * @return Iterator sobre los elementos de la lista
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T dato = actual.dato;
                actual = actual.siguiente;   
                return dato;
            }
        };
    }
}