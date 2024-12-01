package pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//класс-модель для товара
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {
    String name;
    String article;
    String manufacturer;
    String price;

    String warehouse;
}
