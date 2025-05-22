package estructuras;

/**
 * Implementación de una Tabla Hash para almacenar el estado de los jugadores.
 * Cada entrada almacena: nombre, puntaje, cartas en mano, estado actual.
 */
public class TablaHash<K, V> {

    private static final int CAPACIDAD_INICIAL = 16;
    private static final double FACTOR_CARGA = 0.75;

    private Entrada<K, V>[] tabla;
    private int tamaño;
    private int umbralRehash;

    /**
     * Clase interna que representa una entrada en la tabla hash.
     */
    private static class Entrada<K, V> {
        private final K clave;
        private V valor;
        private Entrada<K, V> siguiente;

        public Entrada(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = null;
        }
    }

    /**
     * Constructor de la tabla hash.
     */
    @SuppressWarnings("unchecked")
    public TablaHash() {
        this.tabla = new Entrada[CAPACIDAD_INICIAL];
        this.tamaño = 0;
        this.umbralRehash = (int) (CAPACIDAD_INICIAL * FACTOR_CARGA);
    }

    /**
     * Calcula el índice en la tabla para una clave dada.
     * 
     * @param clave La clave para la cual calcular el índice.
     * @return El índice calculado.
     */
    private int obtenerIndice(K clave) {
        int hashCode = clave.hashCode();
        return (hashCode & 0x7FFFFFFF) % tabla.length;
    }

    /**
     * Inserta o actualiza un par clave-valor en la tabla hash.
     * 
     * @param clave La clave a insertar o actualizar.
     * @param valor El valor asociado a la clave.
     */
    public void insertar(K clave, V valor) {
        if (clave == null) {
            throw new IllegalArgumentException("La clave no puede ser null");
        }

        if (tamaño >= umbralRehash) {
            rehash();
        }

        int indice = obtenerIndice(clave);
        Entrada<K, V> entrada = tabla[indice];

        // Buscar si la clave ya existe
        while (entrada != null) {
            if (entrada.clave.equals(clave)) {
                entrada.valor = valor;
                return;
            }
            entrada = entrada.siguiente;
        }

        // Insertar nueva entrada al inicio de la lista
        Entrada<K, V> nuevaEntrada = new Entrada<>(clave, valor);
        nuevaEntrada.siguiente = tabla[indice];
        tabla[indice] = nuevaEntrada;
        tamaño++;
    }

    /**
     * Obtiene el valor asociado a una clave.
     * 
     * @param clave La clave a buscar.
     * @return El valor asociado a la clave o null si no existe.
     */
    public V obtener(K clave) {
        if (clave == null) {
            return null;
        }

        int indice = obtenerIndice(clave);
        Entrada<K, V> entrada = tabla[indice];

        while (entrada != null) {
            if (entrada.clave.equals(clave)) {
                return entrada.valor;
            }
            entrada = entrada.siguiente;
        }

        return null;
    }

    /**
     * Elimina una entrada de la tabla hash.
     * 
     * @param clave La clave de la entrada a eliminar.
     * @return El valor asociado a la clave eliminada o null si no existe.
     */
    public V eliminar(K clave) {
        if (clave == null) {
            return null;
        }

        int indice = obtenerIndice(clave);
        Entrada<K, V> actual = tabla[indice];
        Entrada<K, V> anterior = null;

        while (actual != null) {
            if (actual.clave.equals(clave)) {
                if (anterior == null) {
                    tabla[indice] = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                tamaño--;
                return actual.valor;
            }
            anterior = actual;
            actual = actual.siguiente;
        }

        return null;
    }

    /**
     * Verifica si la tabla hash contiene una clave.
     * 
     * @param clave La clave a verificar.
     * @return true si la clave existe, false en caso contrario.
     */
    public boolean contiene(K clave) {
        return obtener(clave) != null;
    }

    /**
     * Devuelve el tamaño de la tabla hash.
     * 
     * @return El número de entradas en la tabla hash.
     */
    public int tamaño() {
        return tamaño;
    }

    /**
     * Verifica si la tabla hash está vacía.
     * 
     * @return true si la tabla hash está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return tamaño == 0;
    }

    /**
     * Redimensiona la tabla hash cuando el factor de carga supera el umbral.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        Entrada<K, V>[] tablaAntigua = tabla;
        int nuevaCapacidad = tabla.length * 2;
        tabla = new Entrada[nuevaCapacidad];
        umbralRehash = (int) (nuevaCapacidad * FACTOR_CARGA);

        // Reinserta todas las entradas en la nueva tabla
        for (Entrada<K, V> entrada : tablaAntigua) {
            while (entrada != null) {
                int indice = obtenerIndice(entrada.clave);
                Entrada<K, V> siguiente = entrada.siguiente;
                entrada.siguiente = tabla[indice];
                tabla[indice] = entrada;
                entrada = siguiente;
            }
        }
    }

    /**
     * Muestra el estado actual de la tabla hash.
     * 
     * @return Una representación en cadena de la tabla hash.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TablaHash[tamaño=").append(tamaño).append("]\n");

        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] != null) {
                sb.append(i).append(": ");
                Entrada<K, V> entrada = tabla[i];
                while (entrada != null) {
                    sb.append("[")
                            .append(entrada.clave)
                            .append("=>").append(entrada.valor)
                            .append("]")
                            .append(entrada.siguiente != null ? " -> " : "");
                    entrada = entrada.siguiente;
                }
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Método recursivo para mostrar todas las claves en la tabla hash.
     * 
     * @return Una representación en cadena de todas las claves.
     */
    public String mostrarClaves() {
        StringBuilder sb = new StringBuilder();
        sb.append("Claves: [");
        mostrarClavesRecursivo(0, sb);
        sb.append("]");
        return sb.toString();
    }

    private void mostrarClavesRecursivo(int indice, StringBuilder sb) {
        if (indice >= tabla.length) {
            return;
        }

        Entrada<K, V> entrada = tabla[indice];
        while (entrada != null) {
            sb.append(entrada.clave);
            entrada = entrada.siguiente;
            if (entrada != null || indice < tabla.length - 1) {
                sb.append(", ");
            }
        }

        mostrarClavesRecursivo(indice + 1, sb);
    }
}