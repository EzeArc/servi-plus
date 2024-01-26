package serviplus.sp_back.service;

import java.util.List;
import serviplus.sp_back.entity.Image;

public interface IImageService {

    public Image getImage(Long id);
    public List<Image> listAllImage();
    public Image createImage(Image image);
    public Image updateImage(Image image);
    public Image deleteImage(Long id);
}
