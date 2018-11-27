package juego;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReversiTest {

    private Reversi reversi;

    private void assertCasilleroEn(int fila, int columna, Casillero casillero) {
        
        assertEquals("casillero (" + fila + ", " + columna + ")", 
                     casillero, reversi.obtenerCasillero(fila, columna));
    }

    private void assertCasilleroEnRango(int filaDesde, int columnaDesde, int filaHasta, int columnaHasta, Casillero casillero) {

        for (int fila = filaDesde; fila <= filaHasta; fila++) {
            
            for (int columna = columnaHasta; columna <= columnaHasta; columna++) {
                
                assertCasilleroEn(fila, columna, casillero);
            }
        }
    }
    
    private void assertCasilleroLibreEnRango(int filaDesde, int filaHasta, int columnaDesde, int columnaHasta) {
        
        assertCasilleroEnRango(filaDesde, columnaDesde, filaHasta, columnaHasta, Casillero.LIBRE);
    }
    
    private void assertFichaBlancaEn(int fila, int columna) {
        
        assertCasilleroEn(fila, columna, Casillero.BLANCAS);
    }
    
    private void assertFichaNegraEn(int fila, int columna) {
        
        assertCasilleroEn(fila, columna, Casillero.NEGRAS);
    }
    
    @Test
    public void crearConDimensionMinimaDe4() {

        reversi = new Reversi(4, "Juan", "Pedro");
        
        assertEquals("filas", 4, reversi.contarFilas());
        assertEquals("columnas", 4, reversi.contarColumnas());
    }
    
    @Test
    public void crearConDimensionDe6() {
        
        reversi = new Reversi(6, "Juan", "Pedro");
        
        assertEquals("filas", 6, reversi.contarFilas());
        assertEquals("columnas", 6, reversi.contarColumnas());
    }
    
    @Test(expected = Error.class)
    public void crearConDimensionDe3LanzaExcepcionPorqueEsMenorAlMinimo() {
        
        new Reversi(3, "Juan", "Carlos");
    }

    @Test(expected = Error.class)
    public void crearConDimensionDe5LanzaExcepcionPorqueEsImpar() {
        
        new Reversi(5, "Marta", "Julio");
    }
    
    @Test(expected = Error.class) 
    public void crearConDimensionDe2LanzaExcepcionPorqueEsMenorAlMinimoAunqueSeaPar() {
        
        new Reversi(2, "Juan", "Maria");
    }
    
    @Test
    public void ambosJugadoresEmpiezanElJuegoCon2Fichas() {
        
        reversi = new Reversi(8, "Carlos", "Rosa");
        
        assertEquals("fichas negras", 2, reversi.contarFichasNegras());
        assertEquals("fichas blancas", 2, reversi.contarFichasBlancas());
    }
    
    @Test
    public void empiezaJugandoMarcosElJugadorConLasFichasNegras() {
        
        final String jugadorConFichasNegras = "Marcos";
        reversi = new Reversi(8, jugadorConFichasNegras, "Jorgelina");
        
        assertEquals("jugador actual", jugadorConFichasNegras, reversi.obtenerJugadorActual());
    }

    @Test
    public void empiezaJugandoJuliaElJugadorConLasFichasNegras() {
        
        final String jugadorConFichasNegras = "Julia";
        reversi = new Reversi(8, jugadorConFichasNegras, "Jorgelina");
        
        assertEquals("jugador actual", jugadorConFichasNegras, reversi.obtenerJugadorActual());
    }

    @Test
    public void inicialmenteElJuegoNoTermino() {
        
        reversi = new Reversi(4, "Juan", "Pedro");
        
        assertFalse("no termino", reversi.termino());
    }
    
    @Test
    public void inicialmenteElJuegoNoTieneGanador() {
        
        reversi = new Reversi(8, "Maria", "Laura");
        
        assertFalse("no hay ganador", reversi.hayGanador());
        assertNull("no existe el ganador", reversi.obtenerGanador());
    }

    @Test
    public void distribucionInicialDeFichasEnUnTableroDe4x4() {
        
        reversi = new Reversi(4, "Lucia", "Oscar");
        
        assertFichaBlancaEn(2,2);
        assertFichaNegraEn (2,3);
        assertFichaNegraEn (3,2);
        assertFichaBlancaEn(3,3);
    }
    
    @Test
    public void distribucionInicialDeFichasEnUnTableroDe8x8() {
        
        reversi = new Reversi(8, "Lucia", "Oscar");
        
        assertFichaBlancaEn(4,4);
        assertFichaNegraEn (4,5);
        assertFichaNegraEn (5,4);
        assertFichaBlancaEn(5,5);
    }

    @Test
    public void distribucionInicialDeCasillerosLibresEnUnTableroDe4x4() {
        
        reversi = new Reversi(4, "Martin", "Lucrecia");

        assertCasilleroLibreEnRango(1, 1, 1, 4);
        assertCasilleroLibreEnRango(2, 1, 3, 1);
        assertCasilleroLibreEnRango(2, 4, 3, 4);
        assertCasilleroLibreEnRango(4, 1, 4, 4);
    }
    
    @Test
    public void distribucionInicialDeCasillerosLibresEnUnTableroDe6x6() {
        
        reversi = new Reversi(6, "Martin", "Lucrecia");

        assertCasilleroLibreEnRango(1, 1, 2, 6);
        assertCasilleroLibreEnRango(3, 1, 4, 2);
        assertCasilleroLibreEnRango(3, 5, 4, 6);
        assertCasilleroLibreEnRango(5, 1, 6, 6);
    }
    
    @Test
    public void primeroJuegaMarcosConLasFichasNegras() {
        
        reversi = new Reversi(4, "Marcos", "Luciana");
        
        reversi.colocarFicha(1, 2);
        
        assertFichaNegraEn(1, 2);
    }
    
}
