package telegrambot.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity // обязательно
@Table(name = "links") //необязательная, по умолчанию - название класса
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "findAllLinks", query = "SELECT w FROM WebLinks w"),
        @NamedQuery(name = "findLinksWithName", query = "SELECT w FROM WebLinks w WHERE w.web_link = :web_link"),
        @NamedQuery(name = "findLinksWithId", query = "SELECT w FROM WebLinks w WHERE w.idLinks = :idLinks")
})
public class WebLinks {

    @Id // обязательно
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    @Column(name = "id_Links", unique = true, nullable = false)
    private Integer idLinks;

    @Column (name = "Weblink") // необязательно
    @Setter
    private String web_link;

    @Column (name = "chat_id_owner")
    @Setter
    @ManyToMany (fetch = FetchType.LAZY)
    private Collection<GiftOwner> telegramUser;

    @Column (name = "idGift")
    @Setter
    @ManyToMany
    @JoinTable(name="gift_weblinks",
            joinColumns = @JoinColumn(name="weblinks_id"),
            inverseJoinColumns = @JoinColumn(name="gift_id")
    )
    private Collection<Gift> gift;
}
