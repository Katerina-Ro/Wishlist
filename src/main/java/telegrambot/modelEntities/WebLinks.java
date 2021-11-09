package telegrambot.modelEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/*
@Entity c Hibernate 4+ устарела. Вместо этого вы должны использовать @DynamicUpdate, но здесь возникают сложности с
заполнением полей, которые только создаются (т.е. заполняются впервые), т.к. update не выполняет insert
 */

//@Component
@Entity
@Table(name = "links")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "findAllLinks", query = "SELECT w FROM WebLinks w"),
        @NamedQuery(name = "findLinksWithName", query = "SELECT w FROM WebLinks w WHERE w.web_link = :web_link"),
        @NamedQuery(name = "findLinksWithId", query = "SELECT w FROM WebLinks w WHERE w.idLinks = :idLinks")
})
public class WebLinks extends AbstractIdGifPhoneNumberEntity {

    @Id
    @Column(name = "idLinks", unique = true, nullable = false)
    private int idLinks;

    /*
    @Column (name = "Phone_number_owner", unique = true, nullable = false)
    @JoinColumn(name = "Phone_number")
    @NotBlank
    private String phone_number_owner; */

    @Column (name = "Weblink")
    @Setter
    private String web_link;

    @Column (name = "chat_id_owner")
    @Setter
    @ManyToMany (fetch = FetchType.LAZY, mappedBy = "links")
    private Collection<TelegramUser> telegramUser;

    @Column (name = "idGift")
    @Setter
    @ManyToMany (fetch = FetchType.LAZY, mappedBy = "links")
    private Collection<Gift> gift;
}
