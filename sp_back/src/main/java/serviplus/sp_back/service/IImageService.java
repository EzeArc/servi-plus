package serviplus.sp_back.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import serviplus.sp_back.entity.Image;

public interface IImageService {

    public Image getImage(Long id);

    public List<Image> listAllImage();

    public Image saveImage(MultipartFile imageReceived);

    public Image updateImage(MultipartFile imageReceived, Long idImage);

    public Image deleteImage(Long id);
}
