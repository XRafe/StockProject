package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.project.stock.dto.product.*;
import org.project.stock.dto.product.document.ProductDocumentEntranceDto;
import org.project.stock.dto.product.document.ProductDocumentReplaceDto;
import org.project.stock.dto.product.document.ProductDocumentSaleDto;
import org.project.stock.entity.Product;
import org.project.stock.repository.ProductRepository;
import org.project.stock.service.impl.JpaProductService;
import org.project.stock.service.mapper.ProductMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductServiceTest {

    @InjectMocks
    private JpaProductService jpaProductService;

    @Spy
    private ProductMapper productMapper;

    @Mock

    private ProductRepository productRepository;

    private static List<ProductEntranceDto> PRODUCT_ENTRANCE_LIST = new ArrayList<>();

    private static List<ProductReplaceDto> PRODUCT_REPLACE_LIST = new ArrayList<>();
    private static List<ProductSaleDto> PRODUCT_SALE_LIST = new ArrayList<>();
    private static Product PRODUCT = new Product("123", "name-1", 10, 100L, 200L, 1);

    static {
        PRODUCT_ENTRANCE_LIST.add(new ProductEntranceDto("123", "name-1", 5, 100L));
        PRODUCT_ENTRANCE_LIST.add(new ProductEntranceDto("134", "name-2", 10, 100L));
        PRODUCT_ENTRANCE_LIST.add(new ProductEntranceDto("112", "name-3", 15, 100L));
        PRODUCT_ENTRANCE_LIST.add(new ProductEntranceDto("143", "name-4", 20, 100L));
        PRODUCT_ENTRANCE_LIST.add(new ProductEntranceDto("1123", "name-5", 25, 100L));
        PRODUCT_ENTRANCE_LIST.add(new ProductEntranceDto("1543", "name-6", 30, 100L));

        PRODUCT_REPLACE_LIST.add(new ProductReplaceDto("1234", 5));
        PRODUCT_REPLACE_LIST.add(new ProductReplaceDto("154", 10));
        PRODUCT_REPLACE_LIST.add(new ProductReplaceDto("164", 15));
        PRODUCT_REPLACE_LIST.add(new ProductReplaceDto("187", 20));
        PRODUCT_REPLACE_LIST.add(new ProductReplaceDto("190", 25));

        PRODUCT_SALE_LIST.add(new ProductSaleDto("1987", "name-1", 100L, 10));
        PRODUCT_SALE_LIST.add(new ProductSaleDto("1876", "name-2", 100L, 15));
        PRODUCT_SALE_LIST.add(new ProductSaleDto("1987", "name-3", 100L, 20));
        PRODUCT_SALE_LIST.add(new ProductSaleDto("143", "name-4", 100L, 25));
        PRODUCT_SALE_LIST.add(new ProductSaleDto("1223", "name-5", 100L, 30));
    }

    @Test
    void testEntranceNewProductMethodIsSuccessful() {
        when(productRepository.existsByArticleAndStockId(anyString(), anyInt())).thenReturn(false);

        List<ProductDto> testList = jpaProductService.entranceProduct(
                new ProductDocumentEntranceDto((PRODUCT_ENTRANCE_LIST), 1));

        for (ProductDto productDto : testList) {
            Assertions.assertEquals(productDto.getStockId(), 1);

            Assertions.assertNull(productDto.getPriceLastSale());

            Assertions.assertNotNull(productDto.getArticle());
            Assertions.assertNotNull(productDto.getName());
            Assertions.assertNotNull(productDto.getCount());
            Assertions.assertNotNull(productDto.getPriceLastBuy());
        }
    }

    @Test
    void testEntranceExistingProductMethodIsSuccessful() {
        Product product = new Product("", "", 10, null, null, null);

        when(productRepository.existsByArticleAndStockId(anyString(), anyInt())).thenReturn(true);
        when(productRepository.findByArticleAndStockId(anyString(), anyInt()))
                .thenReturn(Optional.of
                        (product));

        List<ProductDto> testList = jpaProductService.entranceProduct(
                new ProductDocumentEntranceDto((PRODUCT_ENTRANCE_LIST), 1));

        Assertions.assertEquals(testList.get(0).getCount(), 15);

        Assertions.assertNotEquals(testList.get(1).getCount(), PRODUCT_ENTRANCE_LIST.get(1).getCount());
        Assertions.assertNotEquals(testList.get(2).getCount(), PRODUCT_ENTRANCE_LIST.get(2).getCount());
        Assertions.assertNotEquals(testList.get(3).getCount(), PRODUCT_ENTRANCE_LIST.get(3).getCount());
        Assertions.assertNotEquals(testList.get(4).getCount(), PRODUCT_ENTRANCE_LIST.get(4).getCount());
        Assertions.assertNotEquals(testList.get(5).getCount(), PRODUCT_ENTRANCE_LIST.get(5).getCount());

    }


    @Test
    void testReplaceNewProductMethodIsSuccessful() {
        Product product = new Product("", "", 100, null, null, null);

        when(productRepository.existsByArticleAndStockId(anyString(), anyInt())).thenReturn(false);
        when(productRepository.findByArticleAndStockId(anyString(), anyInt()))
                .thenReturn(Optional.of
                        (product));

        List<ProductDto> testList = jpaProductService.replaceProduct(1,
                new ProductDocumentReplaceDto(PRODUCT_REPLACE_LIST, 2));

        Assertions.assertEquals(testList.get(0).getCount(), 5);
        Assertions.assertEquals(testList.get(1).getCount(), 10);
        Assertions.assertEquals(testList.get(2).getCount(), 15);
        Assertions.assertEquals(testList.get(3).getCount(), 20);
        Assertions.assertEquals(testList.get(4).getCount(), 25);


        Assertions.assertEquals(testList.get(0).getStockId(), 1);
        Assertions.assertEquals(testList.get(1).getStockId(), 1);
        Assertions.assertEquals(testList.get(2).getStockId(), 1);
        Assertions.assertEquals(testList.get(3).getStockId(), 1);
        Assertions.assertEquals(testList.get(4).getStockId(), 1);
    }

    @Test
    void testSaleProductMethodIsSuccessful() {
        Product product = new Product("", "", 100, null, null, null);

        when(productRepository.findByArticleAndStockId(anyString(), anyInt()))
                .thenReturn(Optional.of(product));

        List<ProductDto> testList = jpaProductService
                .saleProduct(new ProductDocumentSaleDto(PRODUCT_SALE_LIST, 1));

        Assertions.assertEquals(testList.get(0).getCount(), 90);

        Assertions.assertNotEquals(testList.get(1).getCount(), PRODUCT_ENTRANCE_LIST.get(1).getCount());
        Assertions.assertNotEquals(testList.get(2).getCount(), PRODUCT_ENTRANCE_LIST.get(2).getCount());
        Assertions.assertNotEquals(testList.get(3).getCount(), PRODUCT_ENTRANCE_LIST.get(3).getCount());
        Assertions.assertNotEquals(testList.get(4).getCount(), PRODUCT_ENTRANCE_LIST.get(4).getCount());
    }

    @Test
    void testEditProductMethodIsSuccessful() {
        Product product = new Product("", "", 100, null, null, null);

        when(productRepository.findById(anyInt()))
                .thenReturn(Optional.of(product));

        ProductDto productDto = jpaProductService.editProduct(anyInt(),
                new ProductEditDto("123", "name-1", 10));

        Assertions.assertEquals(productDto.getArticle(), "123");
        Assertions.assertEquals(productDto.getCount(), 10);
        Assertions.assertEquals(productDto.getName(), "name-1");
    }
}
