/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import agente.Estado;
import agente.No;
import java.util.Queue;

/**
 *
 * @author Renato
 */
public interface NodeCollection extends Queue<No> {
    public boolean contemEstado(Estado e);
}
