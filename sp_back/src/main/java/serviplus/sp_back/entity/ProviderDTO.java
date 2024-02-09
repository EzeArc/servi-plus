package serviplus.sp_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDTO {
    private Long id;
    private String name;
    private String nameImage;
    private String mime;
    private byte[] content;
    private Category category;
    private Double rating;
    private Double salary;
}
