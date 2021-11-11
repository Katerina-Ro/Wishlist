package telegrambot.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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
public class GiftOwner{

    @Id
    @Column(name = "chat_id", nullable = false)
    @NotNull
    private Integer chatId;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String Name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "giftId")
    private List<Gift> listGifts;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Links")
    private List<WebLinks> listLinks;

    @OneToMany
    private List<Comments> listComments;

    @Column(name = "active")
    private boolean activeUser = true;
}
