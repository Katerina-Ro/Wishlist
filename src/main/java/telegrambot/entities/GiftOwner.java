package telegrambot.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

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
public class GiftOwner extends AbstractIdGifPhoneNumberEntity{

    @Column(name = "Name", nullable = false)
    @NotBlank
    private String Name;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<WebLinks> listLinks;

    @OneToMany
    private List<Comments> listComments;

    @Column(name = "Active")
    private boolean active;
}
