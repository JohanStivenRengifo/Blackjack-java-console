package estructuras;

/*
 * Implementación Thread-Safe de una Pila (Stack) genérica.
 */
public class Pila<T> {

    private volatile Nodo<T> cima;
    private volatile int tamaño;

    /*
     * Nodo interno con semántica inmutable.
     * Garantiza consistencia en operaciones concurrentes.
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
     * Constructor de la pila.
     */
    public Pila() {
        this.cima = null;
        this.tamaño = 0;
    }

    /**
     * Agrega un elemento a la cima de la pila.
     * 
     * @param dato El elemento a agregar.
     */
    public void apilar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.siguiente = cima;
        cima = nuevoNodo;
        tamaño++;
    }

    /**
     * Elimina y devuelve el elemento en la cima de la pila.
     * 
     * @return El elemento en la cima de la pila o null si está vacía.
     */
    public T desapilar() {
        if (cima == null) {
            return null;
        }

        T dato = cima.dato;
        cima = cima.siguiente;
        tamaño--;

        return dato;
    }

    /**
     * Devuelve el elemento en la cima de la pila sin eliminarlo.
     * 
     * @return El elemento en la cima de la pila o null si está vacía.
     */
    public T verCima() {
        return cima != null ? cima.dato : null;
    }

    /**
     * Verifica si la pila está vacía.
     * 
     * @return true si la pila está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return cima == null;
    }

    /**
     * Devuelve el tamaño de la pila.
     * 
     * @return El número de elementos en la pila.
     */
    public int tamaño() {
        return tamaño;
    }

    /**
     * Muestra el estado actual de la pila.
     * 
     * @return Una representación en cadena de la pila.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cima -> ");

        Nodo<T> actual = cima;
        while (actual != null) {
            sb.append("[")
                    .append(actual.dato)
                    .append("]")
                    .append(" -> ");
            actual = actual.siguiente;
        }

        sb.append("null");
        return sb.toString();
    }

    /**
     * Método recursivo para mostrar la pila en orden inverso (desde la base hasta
     * la cima).
     * 
     * @return Una representación en cadena de la pila en orden inverso.
     */
    public String mostrarInverso() {
        StringBuilder sb = new StringBuilder();
        sb.append("Base -> ");
        mostrarInversoRecursivo(cima, sb);
        sb.append("Cima");
        return sb.toString();
    }

    private void mostrarInversoRecursivo(Nodo<T> nodo, StringBuilder sb) {
        if (nodo == null) {
            return;
        }

        mostrarInversoRecursivo(nodo.siguiente, sb);

        sb.append("[")
                .append(nodo.dato)
                .append("]")
                .append(" -> ");
    }
}