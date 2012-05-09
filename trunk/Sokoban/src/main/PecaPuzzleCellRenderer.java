package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import sokoban.Celula;
import utils.ImageLoader;

public class PecaPuzzleCellRenderer extends JLabel implements TableCellRenderer {

    public PecaPuzzleCellRenderer() {
	setBackground(Color.WHITE);
	setOpaque(true);
	setFont(new Font("Monospaced", Font.BOLD, 49));
	this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
	    boolean isSelected, boolean hasFocus, int row, int column) {

	ImageLoader loader = ImageLoader.getLoader();
	setText("");
	Celula cont = (Celula) value;

	if (cont.isParede()) {
	    setIcon(loader.getIcon(Propriedades.PAREDE_IMAGEM));
	} else if (cont.temAgente()) {
	    setIcon(loader.getIcon(Propriedades.AGENTE_IMAGEM));
	} else if (cont.temCaixote()) {
	    if (cont.isObjetivo()) {
		setIcon(loader.getIcon(Propriedades.CAIXOTE_NO_DESTINO_IMAGEM));
	    } else {
		setIcon(loader.getIcon(Propriedades.CAIXOTE_IMAGEM));
	    }
	} else if (cont.isObjetivo()) {
	    setIcon(loader.getIcon(Propriedades.DESTINO_IMAGEM));
	} else {
	    setIcon(loader.getIcon(Propriedades.VAZIO_IMAGEM));
	}

	return this;
    }
}
