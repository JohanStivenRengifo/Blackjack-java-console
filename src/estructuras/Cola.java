package estructuras;

/**
 * Implementación de una Cola (Queue) para controlar los turnos de juego.
 * Sigue el principio FIFO (First In, First Out).
 */
public class Cola<T> {

    private Nodo<T> frente; private Nodo<T> ultimo;
    private int tamaño;

    /**
     * Clase interna que representa un nodo de la cola.
     */
    private static class Nodo<T> {
        private T dato;
        private Nodo<T> siguiente;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    /**
     * Constructor de la cola.
     */
    public Cola() {
        this.frente = null;
        this.ultimo = null;
        this.tamaño = 0;
    }

    /**
     * Agrega un elemento al final de la cola.
     * 
     * @param dato El elemento a agregar.
     */
    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        
        if (estaVacia()) {
            frente = nuevoNodo;
        } else {
            ultimo.siguiente = nuevoNodo;
        }
        
        ultimo = nuevoNodo;
        tamaño++;
    }

    /**
     * Elimina y devuelve el elemento al frente de la cola.
     * 
     * @return El elemento al frente de la cola o null si está vacía.
     */
    public T desencolar() {
        if (estaVacia()) {
            return null;
        }
        
        T dato = frente.dato;
        frente = frente.siguiente;
        
        if (frente == null) {
            ultimo = null;
        }
        
        tamaño--;
        return dato;
    }

    /**
     * Devuelve el elemento al frente de la cola sin eliminarlo.
     * 
     * @return El elemento al frente de la cola o null si está vacía.
     */
    public T verFrente() {
        return frente != null ? frente.dato : null;
    }

    /**
     * Verifica si la cola está vacía.
     * 
     * @return true si la cola está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return frente == null;
    }

    /**
     * Devuelve el tamaño de la cola.
     * 
     * @return El número de elementos en la cola.
     */
    public int tamaño() {
        return tamaño;
    }

    /**
     * Muestra el estado actual de la cola.
     * 
     * @return Una representación en cadena de la cola.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Frente -> ");

        Nodo<T> actual = frente;
        while (actual != null) {
            sb.append("[")
                    .append(actual.dato)
                    .append("]")
                    .append(" -> ");
            actual = actual.siguiente;
        }

        sb.append("Final");
        return sb.toString();
    }

    /**
     * Método recursivo para mostrar la cola.
     * 
     * @return Una representación en cadena de la cola usando recursividad.
     */
    public String mostrarRecursivo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Frente -> ");
        mostrarRecursivoAux(frente, sb);
        sb.append("Final");
        return sb.toString();
    }

    private void mostrarRecursivoAux(Nodo<T> nodo, StringBuilder sb) {
        if (nodo == null) {
            return;
        }

        sb.append("[")
                .append(nodo.dato)
                .append("]")
                .append(" -> ");

        mostrarRecursivoAux(nodo.siguiente, sb);
    }
}