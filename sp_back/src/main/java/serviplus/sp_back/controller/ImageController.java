package serviplus.sp_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import serviplus.sp_back.entity.Category;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.service.CategoryServiceImpl;
import serviplus.sp_back.service.ClientServiceImpl;

@RestController
@RequestMapping("/image")
@CrossOrigin(value = "http://localhost:4321/image")
public class ImageController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @GetMapping("/categoryImage/{id}")
    public ResponseEntity<byte[]> categoryImage(@PathVariable Long id) {
        try {
            Category categoryDB = (Category) categoryServiceImpl.getCategory(id);
            if (categoryDB == null) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Category image not found with id: " + id);
            }
            byte[] image = categoryDB.getImage().getContent();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Error updating Category image: " + e.getMessage(), e);
        }

    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<byte[]> clientAndProviderImage(@PathVariable Long id) {
        try {
            Client userDB = (Client) clientServiceImpl.getClient(id);
            if (userDB == null) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User image not found with id: " + id);
            }
            byte[] image = userDB.getImage().getContent();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Error updating User image: " + e.getMessage(), e);
        }

    }
}
