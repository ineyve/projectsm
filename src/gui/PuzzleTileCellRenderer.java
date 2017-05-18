package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import utils.ImageLoader;

public class PuzzleTileCellRenderer extends JLabel implements TableCellRenderer {

    public PuzzleTileCellRenderer() {
        setBackground(Color.WHITE);
        setOpaque(true);
        setFont(new Font("Monospaced", Font.BOLD, 49));
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected,
            boolean hasFocus, int row,
            int column) {
        table.setShowGrid(false);

       ImageLoader loader = ImageLoader.getLoader();
       setText("");
       
        if(row == 2 && column ==5)
           setIcon(loader.getIcon(Properties.DOOR));
        else
        switch(((Integer) value).intValue()){ //????
            case 1:
               setIcon(loader.getIcon(Properties.FORKLIFT));
               break;
            case 2:
               setIcon(loader.getIcon(Properties.BOX_H));
               break;
            case 3:
               setIcon(loader.getIcon(Properties.BOX_V));
               break;
            case 4:
               setIcon(loader.getIcon(Properties.BOX2_H));
               break;
            case 5:
               setIcon(loader.getIcon(Properties.BOX2_V));
               break;
            case 6:
               setIcon(loader.getIcon(Properties.BOX3_H));
               break;
            case 7:
               setIcon(loader.getIcon(Properties.BOX3_V));
               break;
            case 8:
               setIcon(loader.getIcon(Properties.BOX4_H));
               break;
            case 9:
               setIcon(loader.getIcon(Properties.BOX4_V));
               break;
            case 14:
               setIcon(loader.getIcon(Properties.BOX2_H2));
               break;
            case 15:
               setIcon(loader.getIcon(Properties.BOX2_V2));
               break;
            case 16:
               setIcon(loader.getIcon(Properties.BOX3_H2));
               break;
            case 17:
               setIcon(loader.getIcon(Properties.BOX3_V2));
               break;
            case 18:
               setIcon(loader.getIcon(Properties.BOX4_H2));
               break;
            case 19:
               setIcon(loader.getIcon(Properties.BOX4_V2));
               break;  
            default:
                setIcon(loader.getIcon(Properties.EMPTY_IMAGE));
                break;
           //setIcon(loader.getIcon(Properties.IMAGE_PREFIX + ((Integer) value).intValue() + Properties.IMAGE_SUFFIX));
        }
        return this;
    }
}
