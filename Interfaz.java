
/**
 * Vista del juego 4 en línea 
 *
 * @author AleAraya
 * @version 08/10/2020
 */
import javax.swing.JOptionPane;
public class Interfaz
{

    /**
     * Constructor for objects of class Interfaz
     */
    public Interfaz()
    {
        
    }

    /**
     * Despliega un mensaje al usuario sobre quién va a solicitar el turno
     *
     * @return    Retorna la respuesta del usuario a la pregunta del método
     */
    public String solicitarTurno(){
        //Este método es para el juego entre usuarios
        String entrada = "";
        while((!"A".equals(entrada)) && (!"B".equals(entrada))){
            entrada = JOptionPane.showInputDialog("¿Quién desea solicitar el turno?\n"
                                                    +"A. Jugador 1\n"
                                                    +"B. Jugador 2\n");
        }
        return entrada;
    }
    
    
    /**
     * Despliega un mensaje al usuario para que decida quién quiere jugar primero
     *
     * @return    Retorna la respuesta del usuario a la pregunta del método
     */
    public String ordenTurno(){
        //Este método es para el juego contra IA
        String entrada = "";
        while((!"A".equals(entrada)) && (!"B".equals(entrada))){
            entrada = JOptionPane.showInputDialog("¿Quién desea que juegue primero?\n"
                                                    +"A. Blancas\n"
                                                    +"B. Negras\n");
        }
        return entrada;
    }
    
    
    /**
     * Despliega un mensaje al usuario preguntandolé en cuál columna quiere dejar
     * caer su ficha
     *
     * @param  tablero  Es el tablero del juego
     * @return    Retorna la respuesta del usuario a la pregunta del método
     */
    public int usuarioColumna(String[][] tablero){
        String resultado = "";
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j<tablero[0].length; j++){
                resultado += tablero[i][j];
                resultado += "       ";
            }
            resultado += "\n";
        }
        int columna = -1;
        try{
            columna = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el número que representa la columna donde quiere colocar la ficha:\n"
                                                                        +"0: columna 1\n"
                                                                        +"1: columna 2\n"
                                                                        +"2: columna 3\n"
                                                                        +"3: columna 4\n"
                                                                        +"4: columna 5\n"
                                                                        +"5: columna 6\n"
                                                                        +"6: columna 7\n"
                                                                        +"\n" +resultado));                                                        
        }
        catch(NumberFormatException excepcionNumeroFormato){
            JOptionPane.showMessageDialog(null, "Ha ocurrido en un error en la conversión de números enteros a cadenas");
        }
        return columna;
    }
    
    
    /**
     * Imprime el tablero del juego en un JOptionPane
     *
     * @param  tablero  Es el tablero del juego
     */
    public void imprimirTablero(String[][] tablero){
        String resultado = "";
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j<tablero[0].length; j++){
                resultado += tablero[i][j];
                resultado += "       ";
            }
            resultado += "\n";
        }
        JOptionPane.showMessageDialog(null, resultado);
    }
    
    
    /**
     * Despliega un mensaje al usuario preguntadolé la modalidad de juego que desea jugar
     *
     * @return     Retorna la respuesta del usuario a la pregunta del método
     */
    public String preguntarJuego(){
        String respuesta = "";
        while((!"A".equals(respuesta)) && (!"B".equals(respuesta))){
            respuesta = JOptionPane.showInputDialog(null, "Eliga la modalidad de juego que quiera jugar:\n"
                                                        +"A. Jugar contra un oponente\n"
                                                        +"B. Jugar contra la computadora\n");
        }
        return respuesta;
    }
    
    
    /**
     * Despliega un mensaje informando que es el turno del jugador virtual
     *
     */
        public void despliegaTurnoVirtual(){
        JOptionPane.showMessageDialog(null, "Turno del jugador virtual");
    }
}


