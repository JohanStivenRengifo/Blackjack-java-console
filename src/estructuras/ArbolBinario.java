package estructuras;

/*
 * Árbol Binario de Decisión optimizado para implementar la IA del Dealer.
 * Estructura: O(1) en espacio, ya que es un árbol de decisión fijo.
 * Operaciones: O(1) en tiempo para tomar decisiones.
 * 
 * Diseñado con patrón Singleton implícito al tener una estructura fija.
 * @version 2.0
 */
public class ArbolBinario<T> {

    private Nodo<T> raiz;

    // Constante crítica para la lógica de decisión del dealer
    private static final int UMBRAL_DECISION = 17;

    /*
     * Nodo del árbol con patrón Builder implícito.
     * Inmutable una vez construido para garantizar consistencia.
     */
    public static class Nodo<T> {
        private T dato;
        private Nodo<T> izquierdo;
        private Nodo<T> derecho;
        private String descripcion;
        private String condicion;

        public Nodo(T dato, String descripcion, String condicion) {
            this.dato = dato;
            this.descripcion = descripcion;
            this.condicion = condicion;
            this.izquierdo = null;
            this.derecho = null;
        }

        public T getDato() {
            return dato;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public String getCondicion() {
            return condicion;
        }

        public Nodo<T> getIzquierdo() {
            return izquierdo;
        }

        public void setIzquierdo(Nodo<T> izquierdo) {
            this.izquierdo = izquierdo;
        }

        public Nodo<T> getDerecho() {
            return derecho;
        }

        public void setDerecho(Nodo<T> derecho) {
            this.derecho = derecho;
        }
    }

    @SuppressWarnings("unchecked")
    public ArbolBinario() {
        raiz = new Nodo<>((T) "Evaluar puntaje",
                "Análisis heurístico del puntaje actual",
                String.format("puntaje >= %d", UMBRAL_DECISION));

        // Estrategia de decisión basada en reglas de Blackjack estándar
        Nodo<T> pedirCarta = new Nodo<>((T) "Pedir carta",
                "Estrategia agresiva: solicitar carta adicional",
                String.format("puntaje < %d", UMBRAL_DECISION));

        Nodo<T> plantarse = new Nodo<>((T) "Plantarse",
                "Estrategia conservadora: mantener mano actual",
                String.format("puntaje >= %d", UMBRAL_DECISION));

        raiz.setIzquierdo(pedirCarta);
        raiz.setDerecho(plantarse);
    } 

    public Nodo<T> tomarDecision(int puntaje) {
        if (estaVacio()) {
            return null;
        }

        // Evaluación eficiente de la condición de decisión
        Nodo<T> decision = (puntaje < UMBRAL_DECISION) ? raiz.getIzquierdo() :
                raiz.getDerecho();

        // Log de decisión para análisis y debugging
        System.out.println(String.format("\nEstrategia del Dealer [%d pts]: %s",
                puntaje, decision.getDescripcion()));

        return decision;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nÁrbol de Decisión [Estrategia IA-Dealer]");
        sb.append("\n====================================\n");
        mostrarArbol(raiz, "", true);
        return sb.toString();
    }

    private void mostrarArbol(Nodo<T> nodo, String prefijo, String conectador, boolean esUltimo) {
        if (nodo == null)
            return;
        StringBuilder sb = new StringBuilder()
                .append(prefijo)
                .append(conectador)
                .append(nodo.getDato())
                .append(" [").append(nodo.getCondicion()).append("]")
                .append("\n").append(prefijo).append("   └── ")
                .append(nodo.getDescripcion());

        System.out.println(sb.toString());

        // Configuración para recursión
        String nuevoConectador = "├── ";
        String nuevoPrefijo = prefijo + (esUltimo ? "    " : "│   ");

        // Recorrido recursivo del subárbol
        if (nodo.getIzquierdo() != null) {
            mostrarArbol(nodo.getIzquierdo(), nuevoPrefijo, nuevoConectador, nodo.getDerecho() == null);
        }
        if (nodo.getDerecho() != null) {
            mostrarArbol(nodo.getDerecho(), nuevoPrefijo, nuevoConectador, true);
        }
    }

    private void mostrarArbol(Nodo<T> nodo, String prefijo, boolean esUltimo) {
        mostrarArbol(nodo, prefijo, esUltimo ? "└── " : "├── ", esUltimo);
    }

    /*
     * Acceso al testing y validación.
     * 
     * @return Nodo raíz del árbol de decisión
     */
    public Nodo<T> getRaiz() {
        return raiz;
    }
}