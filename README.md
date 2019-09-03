# -job4j_hibernate
hibernate

## Описание проекта к заданию "Реализовать площадку продаж машин."

**Содержание**

[TOCM]

[TOC]
#Описание
Задание выполнено на основе базы данных PostgreSQL, библиотеки Hibernate, страниц  JSP, JSTL. Mapping базы данных выполнен при помощи аннотаций. Динамическое обновление контента web страниц (лист объявлений) на стороне клиента основано на REST API Json. В проекте не используется модель MVC.

#Структура базы даных

##Диаграмма БД
![](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/resources/imagesProject/DB.png?raw=true)

##Таблицы БД
Основная таблица:** Cars**
поля: 
- title - заглавие;
- addesc - основное описание объявления;
- cost - стоимость;
- mileage - пробег;
- power - мощность;
- status - продано/ не продано;

**имеет связь ManyToOne со следующими таблицами:**
- *aduser* - сущность AdUser {id, name} - пользователь
- *body* - сущность Body {id, name} - тип корпуса
- *brand* - сущность Brand {id, name} - марка
- *drive* - сущность Drive {id, name} - тип привода
- *engine* - сущьность Engine {id, name} -  тип мотора
- *while* - сущность Wheel {id, name} - тип рулевого колеса.

**связь ManyToMany при помощи линк-таблицы *cars_images* с таблицей:**
- *image* - сущьность Image{id, name};

#Структура и описание проекта

## Models


Модели/Сущности выполнены практически по одному типу, в виде полей:  индентификатор, имя. кроме сущностей Cars и AdUser.

`<link>` : <https://github.com/IgorAntov/-job4j_hibernate/tree/master/src/main/java/ru/job4jhibernate/admanager/models>

при помощи аннотаций сконфигурировано поле **name** как уникальное и not null.
Сущьность AdUser состоит из полей id, имя (name) и пароль(password).

Модель Cars.
При помощи аннотаций сконфигурированы связи ManyToOne и ManyToMany.  
```java
@Entity
@Table(name = "cars")
@FilterDef(name="brandFilter"
        , parameters=
        @ParamDef( name="brand", type = "integer")


)
@FilterDef(name="dateFilter",
        parameters=@ParamDef( name="dateParam", type="date" ) )
@Filter(name = "brandFilter", condition = "brand_id = :brand")
@Filter(
        name = "dateFilter",
        condition="created >= :dateParam"
)
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String addesc;
    private String cost;
    private String mileage;
    private String power;
    private String status;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created")
  private Date created = new Date();
    @ManyToOne
    @JoinColumn(name = "brand_id", foreignKey=@ForeignKey(name = "fk_brand_id"))
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "body_id", foreignKey=@ForeignKey(name = "fk_body_id"))
    private Body body;
    @ManyToOne
    @JoinColumn(name = "drive_id", foreignKey=@ForeignKey(name = "fk_drive_id"))
    private Drive drive;
    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey=@ForeignKey(name = "fk_engine_id"))
    private Engine engine;
    @ManyToOne
    @JoinColumn(name = "transmission_id", foreignKey=@ForeignKey(name = "fk_transmission_id"))
    private Transmission transmission;
    @ManyToOne
    @JoinColumn(name = "wheel_id", foreignKey=@ForeignKey(name = "fk_wheel_id"))
    private Wheel wheel;
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey=@ForeignKey(name = "fk_user_id"))
    private AdUser aduser;
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "car_image",
            joinColumns =  @JoinColumn(name = "cars_id", nullable = false, updatable = false, foreignKey=@ForeignKey(name = "fk_car_id")) ,
            foreignKey= @ForeignKey(name = "fk_cars_id") ,
            inverseJoinColumns =  @JoinColumn(name = "image_id", nullable = false, updatable = false, foreignKey=@ForeignKey(name = "fk_image_id"))
    )


    Set<Image> imageSet = new HashSet<>();

    public Cars() {
        this.status = "not sold";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Set<Image> getImageSet() {
        return imageSet;
    }

    public void setImageSet(Set<Image> imageSet) {
        this.imageSet = imageSet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AdUser getAduser() {
        return aduser;
    }

    public void setAduser(AdUser aduser) {
        this.aduser = aduser;
    }

    public String getAddesc() {
        return addesc;
    }

    public void setAddesc(String addesc) {
        this.addesc = addesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars = (Cars) o;
        return id == cars.id &&
                cost == cars.cost &&
                mileage == cars.mileage &&
                power == cars.power &&
                Objects.equals(title, cars.title) &&
                Objects.equals(addesc, cars.addesc) &&
                Objects.equals(brand, cars.brand) &&
                Objects.equals(body, cars.body) &&
                Objects.equals(drive, cars.drive) &&
                Objects.equals(engine, cars.engine) &&
                Objects.equals(transmission, cars.transmission) &&
                Objects.equals(wheel, cars.wheel) &&
                Objects.equals(aduser, cars.aduser) &&
                Objects.equals(imageSet, cars.imageSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, mileage, power, brand, body, drive, engine, transmission, wheel, addesc);
    }
}

```
##Utils

Имеет в своем составе только один класс сингелтон **HibernateSessionFactoryUtil** c конфигурацией окружения Hibernate. 
```java
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("admanager.cfg.xml");
                configuration.addAnnotatedClass(AdUser.class);
                configuration.addAnnotatedClass(Brand.class);
                configuration.addAnnotatedClass(Transmission.class);
                configuration.addAnnotatedClass(Engine.class);
                configuration.addAnnotatedClass(Drive.class);
                configuration.addAnnotatedClass(Wheel.class);
                configuration.addAnnotatedClass(Body.class);
                configuration.addAnnotatedClass(Image.class);
                configuration.addAnnotatedClass(Cars.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Ad Manager Error: " + e);
            }
        }
        return sessionFactory;
    }
}
```
 Файл конфигурации admanager.cfg.xml:
```java
<?xml version="1.0" encoding="UTF-8"?>
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">org.postgresql.Driver</property>
        <property name="connection.url">jdbs:postgresql://localhost:5432/dbstore</property>
        <property name="connection.driver_class">org.postgresql.Driver</property>
        <property name="connection.username">pcontrol</property>
        <property name="connection.password">pcontrol</property>
        <property name="dialect">org.hibernate.dialect.PostgreSQL94Dialect</property>
        <property name="hibernate.hbm2ddl.auto">create</property>
        <property name="show_sql">true</property>
</session-factory>
</hibernate-configuration>
```
##Services
Для инициализации проекта и создания необходимых записей в структуре БД проекта используется класс CarsService. Класс выполняет вставку записей в таблицы базы данных   brand, driveI, engine, transmission, wheel. Это необходмо для того, чтобы при выполнении добавления объявления в проект, можно было выбрать из выпадающих списков характеристики автомобиля.

`<link>` : <https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/java/ru/job4jhibernate/admanager/services/CarsService.java>

##DAO
Data Access Object Class. 
Уровень Peristance в задании реализован в классе CarsStore. 
Класс выполняет основные операции вставки, обновления, поиска в БД. Класс реализует DAO интерфесы доступа относительно каждой сущности.

`<link>` : <https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/java/ru/job4jhibernate/admanager/dao/CarsStore.java>

##Servlets

Обмен данными между клиентом и сервером реализован на сервлетах, а именно:

###Список объявлений
- [CarList.java](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/java/ru/job4jhibernate/admanager/servlets/CarList.java)
-  [index.jsp](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/webapp/WEB-INF/views/index.jsp)
###Регистрация пользователя
- [AddUser.java](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/java/ru/job4jhibernate/admanager/servlets/AddUser.java)
- [adduser.jsp](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/webapp/WEB-INF/views/adduser.jsp)
###Вход на сайт
- [SignIn.java](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/java/ru/job4jhibernate/admanager/servlets/SignIn.java)
- [signin.jsp](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/webapp/WEB-INF/views/signin.jsp)
###Выход
- [SignOut.java](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/java/ru/job4jhibernate/admanager/servlets/SignOut.java)
###Вставка объявления:
- [AddAds.java](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/java/ru/job4jhibernate/admanager/servlets/AddAds.java)
- [addads.jsp](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/webapp/WEB-INF/views/addads.jsp)
###Просмотр конкретнного объявления :
- [ShowAds.java](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/java/ru/job4jhibernate/admanager/servlets/ShowAds.java)
- [showads.jsp](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/webapp/WEB-INF/views/showads.jsp)
###Выгрузка изображения :
- [DownloadImage.java](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/java/ru/job4jhibernate/admanager/servlets/DownloadImage.java)
###Изменение статуса объявления :
- [AdsUpdate.java](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/java/ru/job4jhibernate/admanager/servlets/AdsUpdate.java)
##Фильтрация
Динамическая фильтрация выполнена при помощи аннотаций в классе Cars
```java
@FilterDef(name="brandFilter", parameters=@ParamDef( name="brand", type = "integer"))
@FilterDef(name="dateFilter",  parameters=@ParamDef( name="dateParam", type="date" ) )
@Filter(name = "brandFilter", condition = "brand_id = :brand")
@Filter( name = "dateFilter", condition="created >= :dateParam")
public class Cars { 
@Id  
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
```
Управление фильтрами выполняется в методе filtersCars(FilterList filterList) класса CarStore

      @Override
        public List<Cars> filtersCars(FilterList filterList) {
            final Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    
            try {
                List<Cars> result;
                filterEnable(filterList, session);
                result = (List<Cars>) session.createQuery("From Cars c").list();
                filterDisable(filterList, session);
                return result;
            } catch (final Exception e) {
                session.getTransaction().rollback();
                throw e;
            } finally {
                session.close();
            }
        }
    
        private void filterDisable(FilterList filterList, Session session) {
            if (!filterList.getPeriod().isEmpty()) {
                session.disableFilter("dateFilter");
            }
            if (!filterList.getBrand().isEmpty()) {
                session.disableFilter("brandFilter");
            }
        }
    
        private void filterEnable(FilterList filterList, Session session) {
            if (!filterList.getBrand().isEmpty()) {
                session.enableFilter("brandFilter").setParameter("brand", Integer.parseInt(filterList.getBrand()));
            }
            if (!filterList.getPeriod().isEmpty()) {
                Date currentDate = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(currentDate);
                c.add(Calendar.DATE, -1 * Integer.parseInt(filterList.getPeriod()));
                Date currentDatePlusDays = c.getTime();
                session.enableFilter("dateFilter").setParameter("dateParam", currentDatePlusDays);
            }
        }    }
    

#Навигация по сайту проекта
##Главная старница
![](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/resources/imagesProject/listForm.png?raw=true)
##Вход на сайт
![](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/resources/imagesProject/regForm.png?raw=true)
##Добавление объявления
![](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/resources/imagesProject/addForm.png?raw=true)
##Просмотр объявление 
Поменять статус объявления может только пользователь который его создал. Проверка статуса выполняется на странице showads.jsp
![](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/resources/imagesProject/infoForm.png?raw=true)
##Фильтрация
![](https://github.com/IgorAntov/-job4j_hibernate/blob/master/src/main/resources/imagesProject/filterForm.png?raw=true)

