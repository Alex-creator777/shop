Данный проект представляет собой полноценное веб-приложение для управления интернет-магазином. Он содержит набор REST API-эндпоинтов для работы с товарами, заказами, скидками, корзинами, избранным, складами, пополнениями запасов и пр. Приложение построено на Spring Boot, использует PostgreSQL в качестве СУБД и активно применяет Spring Data JPA для работы с базой данных.

Все изменения схемы базы данных хранятся и мигрируются с помощью Liquibase. Для удобного и безопасного преобразования данных из сущностей в DTO (и наоборот) используется MapStruct. Пагинация реализована в сервисах для получения списка товаров и заказов, а также для обеспечения масштабирования при большом количестве записей.

В проекте применена разделённая логика:

ServiceRequest-классы отвечают за «командные» операции (создание, обновление, удаление, применение скидки и т.д.).
ServiceResponse-классы используются для «чтения» данных и возврата списков DTO.
Для документирования API подключён Swagger (springdoc-openapi), который предоставляет удобный интерфейс для тестирования и просмотра всех доступных эндпоинтов (доступно по адресу http://localhost:8080/swagger-ui/index.html после запуска).

Ключевые особенности и функциональность
1. Управление товарами (Products)
ProductController и сервис ProductService (ответ на запросы “чтения”) обеспечивают получение списка товаров с пагинацией и получение товара по ID.
ProductControllerRequest (и соответствующие сервисы в пакете serviceRequest) реализуют создание, обновление (PUT), частичное обновление (PATCH) и «мягкое» удаление товара с помощью флага isDeleted.
Частичное обновление (PATCH) позволяет менять лишь некоторые поля без необходимости передавать полностью все данные о товаре.
«Мягкое» удаление (soft delete) означает, что при удалении записи о товаре в базе просто выставляется флаг isDeleted = true, чтобы сохранить всю связанную статистику.
2. Управление категориями (Categories)
Существуют CategoryController и CategoryService, позволяющие получить список категорий и детальную информацию о конкретной категории.
Связь «один ко многим» (OneToMany) с товарами: у каждой категории может быть много продуктов.
3. Управление клиентами (Customers)
CustomerController и CustomerService предоставляют эндпоинты для получения списка клиентов и отдельного клиента по ID.
4. Корзины (Carts) и их позиции (CartItems)
CartControllerRequest и CartServiceRequest реализуют логику создания и получения корзин.
Каждая корзина привязана к пользователю (через userId), может хранить несколько позиций с товарами.
CartCalculator считает общую сумму корзины (суммирует price * quantity для всех позиций).
CartItemControllerRequest / CartItemServiceRequest отвечают за добавление позиций (товаров) в корзину.
5. Избранные товары (Favorites)
FavoriteControllerRequest и FavoriteServiceRequest — функционал «избранного»: добавление и удаление продукта из списка избранных у конкретного клиента.
FavoriteControllerResponse и FavoriteServiceResponse — получение списка избранных товаров по customerId.
6. Заказы (Orders) и позиции в заказе (OrderItems)
OrderController и OrderService позволяют получать заказы с пагинацией, а также детальный просмотр конкретного заказа по ID.
Перед возвращением данных о заказе происходит пересчёт итоговой суммы с учётом позиций и всех применённых скидок.
OrderItemController и OrderItemService (Response-слой) позволяют читать элементы заказа.
OrderItemControllerRequest и OrderItemServiceRequest — добавление новых позиций в заказ, с пересчётом итоговой суммы.
7. Оформление заказа из корзины
OrderCreationControllerRequest / OrderCreationServiceRequest оформляют заказ на основе корзины.
Происходит перенос всех позиций из CartItemEntity в OrderItemEntity и формирование финального заказа.
8. Скидки (Discounts) и применение скидок к заказу
DiscountControllerRequest / DiscountServiceRequest — создание и обновление скидок.
Поддерживаются поля: промокод, описание, процент, период действия, флаг активности и т.д.
DiscountControllerResponse / DiscountServiceResponse — получение скидки по ID и списка всех скидок.
OrderDiscountControllerRequest / OrderDiscountServiceRequest — применение скидки к заказу, сохранение записи в таблицу order_discounts.
OrderDiscountControllerResponse / OrderDiscountServiceResponse — просмотр, какие скидки применены к конкретному заказу.
OrderDiscountCalculator автоматически суммирует проценты нескольких скидок и пересчитывает итоговую сумму заказа (но не более 100% в сумме).
9. Оплата (Payments) и транзакции (Transactions)
PaymentControllerRequest / PaymentServiceRequest — создание записи о платеже, а также обновление статуса платежа (например, при успехе или ошибке).
PaymentControllerResponse / PaymentServiceResponse — получение данных о платеже по его ID или по orderId.
TransactionControllerRequest / TransactionServiceRequest — логирование событий в процессе обработки платежа (инициация, успех, отказ и т.д.).
TransactionControllerResponse / TransactionServiceResponse — просмотр журнала (лога) транзакций, связанных с платежом.
10. Склады (Warehouses), остатки и пополнение (StockItems, Restocks)
WarehouseController / WarehouseService — получение списка складов и деталей по конкретному складу.
StockItemController / StockItemService (Response-слой) — чтение позиций складских остатков.
StockItemControllerRequest / StockItemServiceRequest — добавление новых позиций на склад или изменение их количества (через PATCH-логику StockItemQuantityChangeRequestDto).
InventoryCalculator отвечает за корректный пересчёт (увеличение/уменьшение) остатков.
RestockController / RestockService — просмотр актов пополнения запасов (доставка товаров на склад).
11. Логирование
В ProductService (и ряде других сервисов) используется логгер SLF4J (через LoggerFactory.getLogger).
Логирование запросов (INFO-уровень) для отображения информации о количестве найденных продуктов, запросах по ID и т.д.
Логи по умолчанию сохраняются в файл, заданный в application.properties (пример: logging.file.name=logs/app.log).
12. Валидация (Validation)
Активно используется Jakarta Validation (Bean Validation) через аннотации @NotNull, @Min, @Size и т.д. для DTO-классов.
В контроллерах методы помечены аннотацией @Valid, что позволяет автоматически проверять корректность входных данных и выбрасывать исключения при нарушениях.
13. Тестирование
В проекте присутствуют модульные тесты на JUnit + Mockito (например, ProductServiceTest).
Используются моки репозиториев и проверка ожидаемых результатов при вызове сервисных методов.
14. Swagger / OpenAPI 3
Для автоматической генерации и просмотра документации всех REST-эндпоинтов задействована библиотека springdoc-openapi.
Документация доступна по адресу: http://localhost:8080/swagger-ui/index.html.
Технологии и инструменты
Spring Boot: фундамент фреймворка, упрощающий конфигурацию и запуск приложения.
Spring Data JPA: упрощённая работа с базой данных, использование репозиториев (Repository) и автоматическая генерация запросов.
PostgreSQL: основная СУБД проекта.
Liquibase: управление миграциями БД (SQL-файлы с changelog-скриптами, перечисленными в master.xml).
MapStruct: автоматическое маппирование сущностей в DTO и обратно.
Разделены мапперы для запросов (*RequestMapper) и ответов (*ResponseMapper).
Jakarta Validation (Bean Validation): проверка входных данных в DTO на корректность (например, @NotNull, @Min, @Size).
Swagger (springdoc-openapi): автоматическая генерация документации и UI для тестирования REST-эндпоинтов.
SLF4J / Logback: логирование на различных уровнях (INFO, ERROR и т.д.).
JUnit 5 + Mockito: модульное тестирование сервисов, использование мок-объектов.
Основные схемы базы данных (Liquibase)
Файлы миграции находятся в папке resources/db/changelog:

changelog-0001-init.sql: создание основных таблиц (categories, products, warehouses, stock_items, restocks, customers, orders, order_items).
changelog-0003-add-is-deleted.sql: добавление колонки is_deleted в таблицу products для «мягкого» удаления.
changelog-0005-carts-and-cart-items.sql: создание таблиц carts и cart_items для корзины пользователя.
changelog-0006-favorites.sql: создание таблицы favorites.
changelog-0007-payments-and-transactions.sql: создание таблиц payments и transactions для платёжной логики.
changelog-0008-discounts-and-order-discounts.sql: создание таблиц discounts и order_discounts.
master.xml: главный файл, в котором подключаются все указанные SQL-скрипты.
Пагинация
В проектах для получения списка товаров, заказов и других больших выборок применена пагинация:

GET /api/products?page=0&size=10
page — номер страницы (начинается с 0).
size — количество элементов на странице.
Как запустить проект
Требования:

Java 17 или выше
PostgreSQL
Maven (для сборки и управления зависимостями)
Настройка БД:

Создайте базу данных shop в PostgreSQL.
В файле application.properties пропишите параметры подключения:
properties

spring.datasource.url=jdbc:postgresql://localhost:5432/shop
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
Сборка и запуск:


mvn clean install
mvn spring-boot:run
Доступ к Swagger UI (после запуска приложения):


http://localhost:8080/swagger-ui/index.html
Структура проекта
Контроллеры (api/controllersRequest и api/controllersResponse)

*ControllerRequest — операции создания/редактирования/удаления (Command).
*ControllerResponse — операции чтения (Query).
DTO (dto/request и dto/response)

Разделение на DTO-запросы (когда мы создаём или обновляем сущность) и DTO-ответы (что возвращается клиенту).
Слой сервисов (service/serviceRequest и service/serviceResponse)

serviceRequest — бизнес-логика для изменений (create, update, delete, apply discount и т.д.).
serviceResponse — бизнес-логика для чтения (getAll, getById).
Мапперы (mapper/request и mapper/response)

MapStruct-интерфейсы для автоматического преобразования между сущностями (Entity) и DTO.
Репозитории (repository)

Интерфейсы, унаследованные от JpaRepository, реализующие основные CRUD-операции.
Модели (model)

JPA-сущности (Entity) для каждой таблицы базы данных.
Содержат отношения (OneToMany, ManyToOne и т.д.) и поля для хранения данных.
Миграции Liquibase (resources/db/changelog)

Набор SQL-скриптов для автоматического создания и изменения структуры БД.
Тесты (src/test/java)

Примеры модульных тестов на JUnit + Mockito.
Резюме по применённым навыкам
Spring Boot (REST-приложение, конфигурация автозапуска).
Spring Data JPA (CRUD-операции, создание репозиториев, пагинация).
PostgreSQL (основная база данных, написание миграций для схемы).
Liquibase (управление миграциями, разделение на changelog-файлы).
MapStruct (автоматическое маппирование Entity ↔ DTO).
Валидация (Bean Validation аннотациями @NotNull, @Min, @Size и т.д.).
Swagger / OpenAPI (документирование API, удобное тестирование эндпоинтов).
Логирование (SLF4J/Logback, вывод информации на уровень INFO, добавление логов для ключевых операций).
Тестирование (JUnit 5, Mockito для мока репозиториев и проверки бизнес-логики).
Архитектура (разделение на Controller, Service, Repository, DTO, Mapper, гибкое расширение функционала).
Обработка «мягкого» удаления (Soft delete) через флаг isDeleted.
Частичное обновление (PATCH) и полный PUT/POST — разные модели DTO.
Работа со скидками, платежами и транзакциями (применение скидок, логирование процессов оплаты).
Работа с корзинами и избранным — дополнительный уровень функционала для e-commerce.
Проект подходит в качестве примера интернет-магазина с широкой функциональностью: от учёта остатков на складах и корзины до системы скидок и логирования платёжных транзакций. Архитектурная разбивка по пакетам и классам упрощает поддержку и развитие проекта.