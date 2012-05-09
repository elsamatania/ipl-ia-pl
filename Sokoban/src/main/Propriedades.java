package main;

public interface Propriedades {
  public static final String IMAGE_DIR = "/imagens/";
  public static final String AGENTE_IMAGEM = IMAGE_DIR + "agente.gif";
  public static final String CAIXOTE_IMAGEM = IMAGE_DIR + "caixote.gif";
  public static final String CAIXOTE_NO_DESTINO_IMAGEM = IMAGE_DIR + "caixoteNoDestino.gif";
  public static final String DESTINO_IMAGEM = IMAGE_DIR + "destino.gif";
  public static final String PAREDE_IMAGEM = IMAGE_DIR + "parede.gif";
  public static final String VAZIO_IMAGEM = IMAGE_DIR + "vazio.gif";
  
  

  public static final int CELL_WIDTH = 42;
  public static final int CELL_HEIGHT = 42;

  public static final int PUZZLE_WIDTH = 3;
  public static final int PUZZLE_HEIGHT = 3;
}
