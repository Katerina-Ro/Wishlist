package telegrambot.modelEntities;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

/*Пакеты javax.* определяются JSRs (запросы спецификации java). Как и интерфейс, пакеты javax определяют контракты.
    Поставщики, такие как hibernate, обеспечивают реализацию. Используя импорт javax, вы отделяете себя от конкретного
    поставщика и сохраняете возможность переключиться на другую реализацию в будущем */

@Component
@Entity
//Примечание: если вы не используете @Table, тогда hibernate считает, что @Entity является вашим именем таблицы
// по умолчанию
@Table(name = "gift_owner")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TelegramUser extends AbstractIdGifPhoneNumberEntity{

    @Column(name = "Name", nullable = false)
    @NotBlank
    private String Name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "links")
    private Collection<WebLinks> listLinks;

    @Column(name = "Active")
    private boolean active;
}
