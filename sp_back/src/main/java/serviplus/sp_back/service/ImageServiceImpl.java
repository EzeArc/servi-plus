package serviplus.sp_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import serviplus.sp_back.entity.Image;
import serviplus.sp_back.repository.ImageRepository;

@Service
public class ImageServiceImpl implements IImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Image> listAllImage() {
        return imageRepository.findAll();
    }

    @Override
    @Transactional
    public Image createImage(Image image) {
        image.setState(false);
        return imageRepository.save(image);
    }

    @Override
    @Transactional
    public Image updateImage(Image image) {
        Image imageDB = getImage(image.getId());
        if (imageDB == null) {
            return null;
        }
        imageDB.setName(image.getName());
        imageDB.setMime(image.getMime());
        imageDB.setContent(image.getContent());
        return imageRepository.save(imageDB);
    }

    @Override
    @Transactional
    public Image deleteImage(Long id) {
        Image imageDB = getImage(id);
        if (imageDB == null) {
            return null;
        }
        imageDB.setState(true);
        return imageRepository.save(null);
    }

}
