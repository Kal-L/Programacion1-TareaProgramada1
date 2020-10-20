
/**
 * Controlador del juego 4 en linea
 *
 * @author AleAraya
 * @version 08/010/2020
 */
import javax.swing.JOptionPane;
public class Juego
{
    // instance variables - replace the example below with your own
    private Tablero tablero;
    private Interfaz interfaz;

    /**
     * Constructor for objects of class Juego
     */
    public Juego(Tablero tablero, Interfaz interfaz)
    {
        this.tablero = tablero;
        this.interfaz = interfaz;
    }

    /**
     * Veriifca el turno que solicitó el usuario para ver si es válido o no
     *
     * @param  respuesta  Es la respuesta del usuario a la del método solicitarTurno()
     * @return    Retorna "true' si el turno que solicitó el usuario es válido,
     * y retorna "false" si el turno es inválido
     */
    public boolean verificarTurno(String respuesta){
        //Este método solo aplica para el caso en el que no es contra la máquina
        String respuestaAnterior = tablero.getRespuestaAnterior();
        if(respuestaAnterior.equals(respuesta)){
            JOptionPane.showMessageDialog(null, "No sea tramposo, no es su turno");
            return false;
        }
        tablero.setRespuestaAnterior(respuesta);
        return true;
    }
    
    
    /**
     * Es el método para invocar el método setTablero() del modelo
     *
     */
    public void setTableroControlador(){
        tablero.setTablero();
    }
    
    
    /**
     * Es el método para invocar el método imprimirTablero(String[][] tablero) de la vista
     *
     */
    public void  imprimirTableroControlador(){
        interfaz.imprimirTablero(tablero.getTablero());
    }
    
    
    /**
     * Obtiene la columna que eligió el usuario para dejar caer su ficha
     *
     * @return    Retorna la columna que eligió el usuario del método usuarioColumna(String[][] tablero)  
     */
    public int obtenerColumna(){
        int columna = interfaz.usuarioColumna(tablero.getTablero());
        return columna;
    }
    
    
    /**
     * Representa los turnos de la modalidad de juego entre 2 oponentes y sus movivimientos de fichas
     *
     * @param  respuesta    Es la respuesta del usuario a la del método solicitarTurno()
     */
    public void turnoUsuarios(String respuesta){
        //Este método solo aplica para el caso en el que no es contra la máquina
        int columna = 0;
        switch(respuesta){
            case "A":
                columna = obtenerColumna();
                try{
                    while(verificarJugada(columna) == false){
                        JOptionPane.showMessageDialog(null, "Jugada inválida, seleccionó una columna que estaba llena");
                        columna = obtenerColumna();
                    }
                }
                catch(ArrayIndexOutOfBoundsException excepcionArreglo){
                    JOptionPane.showMessageDialog(null, "Hubo un error con el tablero debido a datos inválidos");
                }   
                tablero.movimientoFichas(columna,"B");
                break;
            case "B":
                columna = obtenerColumna();
                try{
                    while(verificarJugada(columna) == false){
                        JOptionPane.showMessageDialog(null, "Jugada inválida, seleccionó una columna que estaba llena");
                        columna = obtenerColumna();
                    }
                }
                catch(ArrayIndexOutOfBoundsException excepcionArreglo){
                    JOptionPane.showMessageDialog(null, "Hubo un error con el tablero debido a datos inválidos");
                }   
                tablero.movimientoFichas(columna,"N");
                break;                
            default:
        }
    }
    
    
    /**
     * Verifica si la columna que eligió el usuario para dejar caer su ficha no está llena 
     * de fichas para que se válida su juego 
     *
     * @param  columna  Es la respuesta del usuario del método usuarioColumna(String[][] tablero) 
     * de la vista, que representa la columna donde quiere dejar caer la ficha
     * @return    Retorna "true" si la elección de columna es válida, y "false" el caso contrario
     */
    public boolean verificarJugada(int columna){
        boolean[] copiaColumnas = tablero.getcolumnasLlenas();
        try{
            if(copiaColumnas[columna] == true){
                return false;
            }
        }
        catch(ArrayIndexOutOfBoundsException excepcionArreglo){
            JOptionPane.showMessageDialog(null, "Hubo un error con el tablero debido a datos inválidos");
        }
        return true;
    }
    
    
    /**
     * Representa el orden de los turnos en la modalidad de juego contra la computadora 
     * y sus movimientos de fichas
     *
     * @param  respuesta  Es la respuesta del usuario al método ordenTurno() de la vista
     */
    public void turnoIA(String respuesta){
        //Este método solo aplica para jugar contra la máquina
        int columna = 0;
        switch(respuesta){
            case "A":
                columna = obtenerColumna();
                while(verificarJugada(columna) == false){
                    JOptionPane.showMessageDialog(null, "Jugada inválida, seleccionó una columna que estaba llena");
                    columna = obtenerColumna();
                }
                tablero.movimientoFichas(columna,"B");
                imprimirTableroControlador();
                interfaz.despliegaTurnoVirtual();
                movimientoComputadora();
                imprimirTableroControlador();
                break;
            case "B":  
                interfaz.despliegaTurnoVirtual();
                movimientoComputadora();
                imprimirTableroControlador();
                columna = obtenerColumna();
                while(verificarJugada(columna) == false){
                    JOptionPane.showMessageDialog(null, "Jugada inválida, seleccionó una columna que estaba llena");
                    columna = obtenerColumna();
                }
                tablero.movimientoFichas(columna,"B");
                imprimirTableroControlador();
                break;
            default:
        }
    }
    
    
    /**
     * Genera los movimientos de fichas del jugador virtual
     *
     */
    public void movimientoComputadora(){
        int columna = (int)(Math.random()*7);
        
        while(verificarJugada(columna) == false){
            columna = (int)(Math.random()*7);
        }
        
        tablero.movimientoFichas(columna,"N");
    }
    
    
    /**
     * Ejecuta la modalidad de juego entre 2 oponentes
     *
     */
    public void jugarUsuarios(){
        setTableroControlador();
       
        while( (!tablero.victory("B")) && (!tablero.victory("N"))
            && (!tablero.empateJuego()) ){
                String respuesta = interfaz.solicitarTurno();
                while(verificarTurno(respuesta) == false){
                    respuesta = interfaz.solicitarTurno();
                }
                turnoUsuarios(respuesta);
        }
        
        if(tablero.victory("B") == true){
            JOptionPane.showMessageDialog(null, "¡Ha ganado el jugador 1!");
        }else if(tablero.victory("N") == true){
                    JOptionPane.showMessageDialog(null, "¡Ha ganado el jugador 2!");
        }else if(tablero.empateJuego() == true){
                    JOptionPane.showMessageDialog(null, "¡Empate!");
        }
    }
    
    
    /**
     * Ejecuta la modalidad de juego contra la computadora
     *
     */
    public void jugarComputadora(){
        setTableroControlador();
        String respuesta = interfaz.ordenTurno();
        while( (!tablero.victory("B")) && (!tablero.victory("N"))
            && (!tablero.empateJuego()) ){
                turnoIA(respuesta);
        }
        if(tablero.victory("B") == true){
            JOptionPane.showMessageDialog(null, "¡Has ganado!");
        }else if(tablero.victory("N") == true){
            JOptionPane.showMessageDialog(null, "¡Ha ganado el jugador virtual!");
        }else if(tablero.empateJuego() == true){
            JOptionPane.showMessageDialog(null, "¡Empate!");
        }
    }
    
    
    /**
     * Ejecuta el menú de juego para que el decida que modalidad de juego jugar
     *
     */
    public void iniciarJuego(){
        String respuesta = interfaz.preguntarJuego();
            switch(respuesta){
                case "A":
                    jugarUsuarios();
                    break;
                case "B":
                    jugarComputadora();
                    break;
                default:
            }   
    }
    
    
    public static void main (String args[]){
        Tablero tablero = new Tablero();
        Interfaz interfaz = new Interfaz();
        Juego juego = new Juego(tablero,interfaz);
        juego.iniciarJuego();
    }
    
}
