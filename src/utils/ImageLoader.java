package utils;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 * <p>Title: ImageLoader</p>
 * <p>Description: Apenas � possivel criar um objecto deste tipo.</p>
 * <p>Esse objecto ser� respons�vel pela cria��o de imagens.
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ImageLoader {

  /**
   * Hashtable que conter� todas as imagens lidas do disco.
   */
  private Map<String,Image> imagens;

  /**
   * �nico objecto do tipo ImageLoader que � criado (Reparem que � criado dentro da classe).
   */ 
  private static final ImageLoader loader = new ImageLoader();

  /**
   * Construtor privado de modo a que ningu�m possa criar objectos deste tipo.
   */
  private ImageLoader() {
    imagens = new HashMap<String,Image>();
  }

  /**
   * M�todo que cria uma imagem a partir do nome do ficheiro em que est� armazenada.
   * @param nome String: nome da imagem
   * @return Image: devolve a imagem criada
   */
  public Image getImage(String nome) {
    if (nome.equals(""))
      return null;
    Image img = imagens.get(nome);
    if (img!=null)
      return img;

    MediaTracker media = new MediaTracker(new Component() {});
    try {
      img = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/imagens/"+nome));
      if (img == null)
        throw new Exception("Bitmap não encontrado: " + nome);
      media.addImage(img, 0);
      imagens.put(nome, img);
      media.waitForID(0);
      if (media.isErrorAny())
        throw new Exception("Media ERROR");
    }
    catch (Exception e) {
      System.err.println("Bitmap não encontrado: " + nome);
      throw new RuntimeException(e.toString());
    }

    return img;
  }


  /**
   * Método que cria um ícone a partir do nome do ficheiro em que está armazenada a imagem correspondente.
   * @param nome String: nome da imagem
   * @return Image: devolve o �cone criado
   */
  public ImageIcon getIcon(String nome) {
    Image img=getImage(nome);
    
    if (img!=null)
      return new ImageIcon(img);
    
    return null;
  }

  /**
   * Devolve o único objecto deste tipo que foi criado.
   * @return ImageLoader
   */
  public static ImageLoader getLoader() {
    return loader;
  }

}
