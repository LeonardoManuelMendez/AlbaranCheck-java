package com.leonardo.verificatualbaran.main;

import com.leonardo.verificatualbaran.gui.InicioDeSesion;
import javax.swing.SwingUtilities;





/**
 *
 * @author Leonardo Méndez
 */
public class VerificaTuAlbaran {

    public static void main(String[] args) {
        
       
        SwingUtilities.invokeLater(() -> {
            new InicioDeSesion().setVisible(true);
        });
    }
}
