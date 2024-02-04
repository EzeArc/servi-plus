package serviplus.sp_back.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    @Transactional(readOnly = true)
    public List<Image> listAllImage() {
        return imageRepository.findAll();
    }

    @Override
    @Transactional
    public Image saveImage(MultipartFile imageReceived) {
        try {
            if (imageReceived != null && !imageReceived.isEmpty()) {
                Image imageDB = new Image();
                imageDB.setMime(imageReceived.getContentType());
                imageDB.setName(imageReceived.getOriginalFilename());
                imageDB.setContent(imageReceived.getBytes());
                imageDB.setState(false);
                return imageRepository.save(imageDB);
            } else {
                System.err.println("El archivo de imagen es nulo o está vacío.");
            }
        } catch (IOException e) {
            System.err.println("Error al guardar la imagen: " + e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public Image updateImage(MultipartFile imageReceived, Long idImage) {
        try {
            if (idImage != null && imageReceived != null && !imageReceived.isEmpty()) {
                Optional<Image> optionalImage = imageRepository.findById(idImage);

                if (optionalImage.isPresent()) {
                    Image imageUpdated = optionalImage.get();
                    imageUpdated.setName(imageReceived.getOriginalFilename());
                    imageUpdated.setMime(imageReceived.getContentType());
                    imageUpdated.setContent(imageReceived.getBytes());

                    return imageRepository.save(imageUpdated);
                } else {
                    System.err.println("No se encontró la imagen con ID: " + idImage);
                }
            } else {
                System.err.println("La imagen o el ID proporcionado son nulos o inválidos.");
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar la imagen: " + e.getMessage());
        }

        return null;
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
