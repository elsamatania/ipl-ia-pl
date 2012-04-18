package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import utils.ImageLoader;


public class PecaPuzzleCellRenderer extends JLabel implements TableCellRenderer{

    public PecaPuzzleCellRenderer() {
        setBackground(Color.WHITE);
        setOpaque(true);
        setFont(new Font("Monospaced", Font.BOLD, 49));
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus, int row,
                                                   int column) {
//          if(((Integer)value).intValue() == 0)
//            setText("");
//          else
//            setText(((Integer)value).toString());

        ImageLoader loader=ImageLoader.getLoader();
        setText("");
        if (((Integer)value).intValue() == 0)
          setIcon(loader.getIcon(Propriedades.EMPTY_IMAGE));
        else
          setIcon(loader.getIcon(Propriedades.IMAGE_PREFIX + ((Integer)value).intValue() + Propriedades.IMAGE_SUFFIX));
        return this;
    }

}
