package ru.leontev.shop.service.serviceResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;
import ru.leontev.shop.dto.response.ProductResponseDto;
import ru.leontev.shop.mapper.response.ProductResponseMapper;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    // Мокируем зависимость репозитория
    @Mock
    private ProductRepository productRepository;

    // Мокируем зависимость маппера для преобразования сущности в DTO
    @Mock
    private ProductResponseMapper productResponseMapper;

    // Внедряем моки в тестируемый сервис
    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        // Инициализируем моки перед каждым тестом
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Тестирование метода getAllProducts()
     * Проверяем, что при передаче параметров возвращается страница с правильным количеством элементов
     */
    @Test
    void testGetAllProducts() {
        // Задаем входные параметры
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

        // Создаем фиктивный список сущностей
        List<ProductEntity> products = List.of(
                createProduct(1L, "Product 1", BigDecimal.valueOf(100)),
                createProduct(2L, "Product 2", BigDecimal.valueOf(200))
        );
        // Создаем страницу с данными
        Page<ProductEntity> productPage = new PageImpl<>(products, pageable, products.size());

        // Настраиваем поведение репозитория
        when(productRepository.findByIsDeletedFalse(pageable)).thenReturn(productPage);

        // Настраиваем маппер, который преобразует каждую сущность в DTO
        ProductResponseDto dto1 = createProductResponseDto(1L, "Product 1", BigDecimal.valueOf(100));
        ProductResponseDto dto2 = createProductResponseDto(2L, "Product 2", BigDecimal.valueOf(200));
        // При вызове маппера для каждой сущности возвращаем соответствующий DTO
        when(productResponseMapper.productToProductDto(any(ProductEntity.class)))
                .thenReturn(dto1, dto2);

        // Вызываем тестируемый метод
        Page<ProductResponseDto> result = productService.getAllProducts(page, size);

        // Проверяем результат
        assertNotNull(result, "Результирующая страница не должна быть null");
        assertEquals(2, result.getTotalElements(), "Общее количество элементов должно быть 2");

        // Проверяем, что репозиторий и маппер были вызваны нужное количество раз
        verify(productRepository, times(1)).findByIsDeletedFalse(pageable);
        verify(productResponseMapper, times(2)).productToProductDto(any(ProductEntity.class));
    }

    /**
     * Тестирование метода getProductById() для случая, когда продукт найден.
     */
    @Test
    void testGetProductByIdFound() {
        Long id = 1L;
        // Создаем фиктивную сущность продукта
        ProductEntity product = createProduct(id, "Test Product", BigDecimal.valueOf(150));

        // Настраиваем репозиторий, чтобы вернуть продукт по заданному ID
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Настраиваем маппер для преобразования сущности в DTO
        ProductResponseDto dto = createProductResponseDto(id, "Test Product", BigDecimal.valueOf(150));
        when(productResponseMapper.productToProductDto(product)).thenReturn(dto);

        // Вызываем метод
        ProductResponseDto result = productService.getProductById(id);

        // Проверяем результат
        assertNotNull(result, "DTO не должно быть null");
        assertEquals(id, result.getId(), "ID должен совпадать");
        assertEquals("Test Product", result.getName(), "Название должно совпадать");

        // Проверяем, что репозиторий и маппер были вызваны один раз
        verify(productRepository, times(1)).findById(id);
        verify(productResponseMapper, times(1)).productToProductDto(product);
    }

    /**
     * Тестирование метода getProductById() для случая, когда продукт не найден.
     * Ожидается выброс исключения.
     */
    @Test
    void testGetProductByIdNotFound() {
        Long id = 1L;
        // Репозиторий возвращает пустой Optional
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Проверяем, что выбрасывается RuntimeException с ожидаемым сообщением
        RuntimeException exception = assertThrows(RuntimeException.class, () -> productService.getProductById(id));
        assertEquals("Product not found", exception.getMessage());

        // Убеждаемся, что маппер не был вызван
        verify(productResponseMapper, never()).productToProductDto(any());
    }

    // Вспомогательный метод для создания ProductEntity
    private ProductEntity createProduct(Long id, String name, BigDecimal price) {
        ProductEntity product = new ProductEntity();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        // Можно задать и другие поля, если необходимо
        return product;
    }

    // Вспомогательный метод для создания ProductResponseDto
    private ProductResponseDto createProductResponseDto(Long id, String name, BigDecimal price) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(id);
        dto.setName(name);
        dto.setPrice(price);
        // Можно задать и другие поля
        return dto;
    }
}
