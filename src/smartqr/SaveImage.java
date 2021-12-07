/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartqr;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Arafat Hossain Ar
 */
public class SaveImage {

    /**
     * @param args the command line arguments
     */
    int opIndex;
    private BufferedImage bi, biFiltered;
    int w, h;

    public SaveImage() {
        try {
            bi = ImageIO.read(new File("temp.PNG"));
            w = bi.getWidth(null);
            h = bi.getHeight(null);
            if (bi.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi2
                        = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi2.getGraphics();
                big.drawImage(bi, 0, 0, null);
                biFiltered = bi = bi2;
                System.out.println("loaded");
            }
        } catch (IOException e) {
            System.out.println("Image could not be read");
            System.exit(1);
        }
    }

    public void saveImage() {

        String format = "PNG";

        File saveFile = new File("qr." + format);
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        chooser.setSelectedFile(saveFile);
        int rval = chooser.showSaveDialog(chooser);
        if (rval == JFileChooser.APPROVE_OPTION) {
            saveFile = chooser.getSelectedFile();
            try {
                ImageIO.write(biFiltered, format, saveFile);
            } catch (IOException ex) {
            }
        }

    }

//    public static void main(String[] args) {
//        // TODO code application logic here
//        SaveImage si = new SaveImage();
//        si.saveImage();
//    }

}
