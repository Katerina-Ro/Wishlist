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

    @Column(name = "name_user")
    @NotBlank
    private String Name;

    @OneToMany
    //@JoinColumn(name = "gift_id")
    private List<Gift> listGifts;

    @OneToMany
    //@JoinColumn(name = "id_links")
    private List<WebLinks> listLinks;

    @OneToMany
    private List<Comments> listComments;

    @Column(name = "active")
    private boolean activeUser = true;
}
