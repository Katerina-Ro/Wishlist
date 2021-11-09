package telegrambot.modelEntities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@MappedSuperclass /*Аннотация @MappedSuperclass позволяет включать класс и его jpa аннотации в производный класс,
                   не делая базовый класс сущностью. Типичное использование в примере выше — абстрактный базовый класс,
                  несущий в себе суррогатный первичный ключ
                   @Access(AccessType.PROPERTY) // Обобщение: вы можете использовать аннотацию AccessType.FIELD для 'обычного' доступа
                   к полю, и аннотацию AccessType.PROPERTY для доступа к полю через get/set.

                   https://www.baeldung.com/jpa-composite-primary-keys
                   2. Составные первичные ключи
                    Составной первичный ключ, также называемый составным ключом, представляет собой комбинацию двух или более столбцов, образующих первичный ключ для таблицы.
                    В JPA у нас есть два варианта определения составных ключей: аннотации @IdClass и @EmbeddedId .
                    Чтобы определить составные первичные ключи, мы должны следовать некоторым правилам:
                    Составной класс первичного ключа должен быть открытым.
                    -У него должен быть конструктор без аргументов.
                    -Он должен определять методы equals () и hashCode () .
                    -Он должен быть Serializable.
                    */
@Table(name = "gift_owner")
@Getter
@Setter
public abstract class AbstractIdGifPhoneNumberEntity implements Serializable {

    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY) // идентичность поддерживает столбцы идентичности в DB2,
                  // MySQL, MS SQL Server, Sybase и HypersonicSQL. Возвращаемый идентификатор имеет тип long, short
                  // или int.
                  //IDENTITY является хорошим выбором только в том случае, если вы не можете использовать SEQUENCE
                  // (например, MySQL), поскольку он отключает пакетное обновление JDBC.
    protected Integer id; // аналог giftId и number

    /*
    @Column(name = "chat_id", unique = true, nullable = false)
    @NotBlank // @NotBlank применяется только к строкам и проверяет, что строка не пуста.
    //@NotNull применяется к CharSequence, Collection, Map или Array и проверяет, что объект не равен null. Но при этом он может быть пуст.
    //@NotEmpty применяется к CharSequence, Collection, Map или Array и проверяет, что он не null имеет размер больше 0.
    //Аннотация @Size(min=6) пропустит строку состоящую из 6 пробелов и/или символов переноса строки, а @NotBlank не пропустит.
    protected long chatId; */
}
