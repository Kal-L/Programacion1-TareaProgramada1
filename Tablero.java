
/**
 * Modelo del juego 4 en línea
 *
 * @author AleAraya
 * @version 08/10/2020
 */
import javax.swing.JOptionPane;
public class Tablero
{
    // instance variables - replace the example below with your own
    private String[][] tablero;
    private boolean[] columnasLlenas;
    private String respuestaAnterior;//Este atributo va a servir para guardar la respuesta del usuario
    //para usarla en el método verificarTurno()
    
    /**
     * Constructor for objects of class Tablero
     */
    public Tablero()
    {
        tablero = new String[6][7];
        columnasLlenas = new boolean[7];
        respuestaAnterior = "";
    }
    
    
    /**
     * Settea el tablero asignando a todas las entradas del tablero con el valor "0"
     *
     */
    public void setTablero(){
        for(int fila = 0; fila<tablero.length; fila++){
            for(int columna = 0; columna<tablero[fila].length; columna++){
                tablero[fila][columna] = "0";
            }
        }
        
        //Se settea este tablero de esta forma para poder utilizar el método "equals"
        //de tipo String en los métodos de movimiento de fichas
    }
    

    /**
     * Método que representa la "caída" de fichas en el tablero
     *
     * @param  columna  Es la columna del tablero que eligió el usuario donde colocar su ficha
     * @param  colorFicha  Es el color de la ficha que tiene que caer en el tablero 
     * @return      Su return se útilizo nada más para salir del método cuando cae la ficha
     */
    public boolean movimientoFichas(int columna, String colorFicha){
        for(int fila = 5; fila > -1; fila--){
            try{
                if(tablero[fila][columna].equals("0")){
                    tablero[fila][columna] = colorFicha;
                    columnasLlenas[columna] = false;
                    return true;
                }
            }
            catch(ArrayIndexOutOfBoundsException excepcionArreglo){
                JOptionPane.showMessageDialog(null, "Hubo un error con el tablero debido a datos inválidos");
            }
        }
        try{
            columnasLlenas[columna] = true;
        }
        catch(ArrayIndexOutOfBoundsException excepcionArreglo){
                JOptionPane.showMessageDialog(null, "Hubo un error con el tablero debido a datos inválidos");
        }
        return false;
    }
    
    
    /**
     * Método que verifica si un jugador ya ganó, mediante recorridos por el tablero para verificar
     * si alguien ganó por columnas, filas o diagonal
     *
     * @param  colorFicha  Es el color de la ficha a la que se va evaluar el método
     * @return    Retorna "true" si un jugador ganó y "false" si aún no ha ganado 
     */
    public boolean victory(String colorFicha){
        //Victoria por filas
        for(int fila = 0; fila<tablero.length; fila++){
            for(int columna = 0; columna<tablero[0].length-3; columna++){
                if( (colorFicha.equals(tablero[fila][columna])) && (colorFicha.equals(tablero[fila][columna+1]))
                && (colorFicha.equals(tablero[fila][columna+2])) && (colorFicha.equals(tablero[fila][columna+3])) ){
                    return true;
                }
            }
        }
                
        //Victoria por columnas
        for(int columna = 0; columna<tablero[0].length; columna++){
            for(int fila = 0; fila<tablero.length-3; fila++){
                if( (colorFicha.equals(tablero[fila][columna])) && (colorFicha.equals(tablero[fila+1][columna])) 
                    && (colorFicha.equals(tablero[fila+2][columna])) && (colorFicha.equals(tablero[fila+3][columna])) == true ){
                        return true;
                }
            }
        }
        
        //Victoria por diagonal de pendiente negativa
        for(int fila = 0; fila<tablero.length-3; fila++){
            for(int columna = 0; columna<tablero[0].length-3; columna++){
                if( (colorFicha.equals(tablero[fila][columna])) && (colorFicha.equals(tablero[fila+1][columna+1])) 
                    && (colorFicha.equals(tablero[fila+2][columna+2])) && (colorFicha.equals(tablero[fila+3][columna+3]))){
                        return true;
                }
            }
        }
        
        //Victoria por diagonal de pendiente positiva
        for(int fila = 3; fila<tablero.length; fila++){
            for(int columna = 0; columna<tablero[0].length-3; columna++){
                if( (colorFicha.equals(tablero[fila][columna])) && (colorFicha.equals(tablero[fila-1][columna+1])) 
                    && (colorFicha.equals(tablero[fila-2][columna+2])) && (colorFicha.equals(tablero[fila-3][columna+3]))){
                        return true;
                }
            }
        }
    
        return false;
    }
    
    
    /**
     * Método que verifica si los jugadores empataron, mediante un recorrido por el tablero para verificar
     * si en la primera fila del tablero está lleno de fichas o no
     *
     * @return    Retorna "true" si los jugadores empataron, y "false" si aún no han empatado
     */
    public boolean empateJuego(){
        int contador = 0;
        for(int columna = 0; columna<tablero[0].length; columna++){
            if(!"0".equals(tablero[0][columna])){
                contador++;
            }
        }
        
        if(contador == 7){
            return true;
        }
       
        return false;    
    }
    
    
    /**
     * Método get del atributo "tablero"
     *
     * @return    Retorna el tablero del juego
     */
    public void setRespuestaAnterior(String respuesta){
        respuestaAnterior = respuesta;
    }
    
    
    /**
     * Método get del atributo "tablero"
     *
     * @return    Retorna el tablero del juego
     */
    public String[][] getTablero(){
        return tablero;
    }
    

    /**
     * Métood get del atributo "columnasLlenas"
     *
     * @return    Retorna el vector atributo "columnasLlenas"
     */
    public boolean[] getcolumnasLlenas(){
        return columnasLlenas;
    }
    
    
    /**
     * Método get del atributo "posicionNegras"
     *
     * @return    Retorna la posición de las fichas negras en el tablero
     */
    public String getRespuestaAnterior(){
        return respuestaAnterior;
    }
}
