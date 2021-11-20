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
    @Column(name = "chat_id", nullable = false, unique = true)
    @NotNull
    private Long chatId;

    @Column(name = "name_user", unique = true)
    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "giftOwner")
   /*
    @JoinTable(name="gift_owner_gifts",
            joinColumns = @JoinColumn(name="gift_owner_id"),
            inverseJoinColumns = @JoinColumn(name="gifts_id")
    ) */
    //@JoinColumn(name = "number_id") // referencedColumnName="id_customer", insertable=false, updatable=false)
    private List<Gift> listGifts;

    @Column(name = "active")
    private boolean activeUser = true;
}