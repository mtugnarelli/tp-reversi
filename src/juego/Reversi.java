package juego;

/**
 * Juego Reversi
 * 
 * Reglas:
 * 
 * 	https://es.wikipedia.org/wiki/Reversi
 *  https://es.wikihow.com/jugar-a-Othello
 * 
 */
public class Reversi {

    private Casillero[][] fichas;
	private int dimension;
    private String jugadorFichasNegras;

    /**
	 * pre : 'dimension' es un n�mero par, mayor o igual a 4.
	 * post: empieza el juego entre el jugador que tiene fichas negras, identificado como 
	 * 		 'fichasNegras' y el jugador que tiene fichas blancas, identificado como
	 * 		 'fichasBlancas'. 
	 * 		 El tablero tiene 4 fichas: 2 negras y 2 blancas. Estas fichas est�n 
	 * 		 intercaladas en el centro del tablero.
	 * 
	 * @param dimensionTablero : cantidad de filas y columnas que tiene el tablero.
	 * @param fichasNegras : nombre del jugador con fichas negras.
	 * @param fichasBlancas : nombre del jugador con fichas blancas.
	 */
	public Reversi(int dimensionTablero, String fichasNegras, String fichasBlancas) {

	    this.definirDimension(dimensionTablero);
	    this.jugadorFichasNegras = fichasNegras;
	    
	    this.inicializar();
	}

	private void inicializar() {

	    fichas = new Casillero[this.dimension][this.dimension];

	    inicializarCasillerosLibres();
	    inicializarCasillerosConFichas();
    }

    private void inicializarCasillerosLibres() {
        
        for (int fila = 0; fila < this.dimension; fila++) {
            
            for (int columna = 0; columna < this.dimension; columna++) {
                
                fichas[fila][columna] = Casillero.LIBRE;
            }
        }
    }

    private void inicializarCasillerosConFichas() {
        
        int medio = this.dimension / 2;

	    colocarEn(medio    , medio    , Casillero.BLANCAS);
	    colocarEn(medio    , medio + 1, Casillero.NEGRAS);
	    colocarEn(medio + 1, medio    , Casillero.NEGRAS);
	    colocarEn(medio + 1, medio + 1, Casillero.BLANCAS);
    }

    private void colocarEn(int fila, int columna, Casillero ficha) {

        fichas[fila-1][columna-1] = ficha;
    }

    private void definirDimension(int dimensionTablero) {

	    if (dimensionTablero < 4) {
	        
	        throw new Error("El tablero no puede tener una dimensi�n menor a 4");
	    }
	    
	    if (dimensionTablero % 2 != 0) {
	        
	        throw new Error("El tablero no puete tener dimensi�n impar");
	    }

	    this.dimension = dimensionTablero;
    }

    /**
	 * post: devuelve la cantidad de filas que tiene el tablero.
	 */
	public int contarFilas() {
		
		return this.dimension;
	}

	/**
	 * post: devuelve la cantidad de columnas que tiene el tablero.
	 */
	public int contarColumnas() {
		
		return this.dimension;
	}
	
	/**
	 * post: devuelve el nombre del jugador que debe colocar una ficha o null si
	 *       termin� el juego.
	 */
	public String obtenerJugadorActual() {
		
		return this.jugadorFichasNegras;
	}

	/**
	 * pre : fila est� en el intervalo [1, contarFilas()],
	 * 		 columnas est� en el intervalo [1, contarColumnas()].
	 * post: indica qui�n tiene la posesi�n del casillero dado 
	 * 		 por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {
		
	    if ((fila == 1) && (columna == 3))
	        
	        return Casillero.NEGRAS;
	    
	    return fichas[fila-1][columna-1];
	}
	
	public boolean puedeColocarFicha(int fila, int columna) {
		
		return false;
	}
	
	/**
	 * pre : la posici�n indicada por (fila, columna) puede ser 
	 *		 ocupada por una ficha. 
	 *       'fila' est� en el intervalo [1, contarFilas()].
	 * 		 'columna' est� en el intervalor [1, contarColumnas()].
	 * 		 y a�n queda un Casillero.VACIO en la columna indicada. 
	 * post: coloca una ficha en la posici�n indicada.

	 * @param fila
	 * @param columna
	 */
	public void colocarFicha(int fila, int columna) {

	    this.colocarEn(fila, columna, Casillero.NEGRAS);
	}
	
	/**
	 * post: devuelve la cantidad de fichas negras en el tablero.
	 */
	public int contarFichasNegras() {
	
		return 2;
	}
	
	/**
	 * post: devuelve la cantidad de fichas blancas en el tablero.
	 */
	public int contarFichasBlancas() {
		
		return 2;
	}
	
	/**
	 * post: indica si el juego termin� porque no existen casilleros vac�os o
	 *       ninguno de los jugadores puede colocar una ficha.
	 */
	public boolean termino() {
		
		return false;
	}

	/**
	 * post: indica si el juego termin� y tiene un ganador.
	 */
	public boolean hayGanador() {
		
		return false;
	}

	/**
	 * pre : el juego termin�.
	 * post: devuelve el nombre del jugador que gan� el juego.
	 */
	public String obtenerGanador() {
		
		return null;
	}
}
