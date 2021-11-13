package telegrambot.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telegrambot.entities.StatusGift.MappedEnum;
import telegrambot.entities.StatusGift.STATUS_GIFT;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Component
@Entity
@MappedEnum(enumClass =  STATUS_GIFT.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "findAllByChatId", query = "SELECT g FROM Gift g WHERE g.chatIdGiftOwner like :chatId")
})
public class Gift {

    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    @Column(name ="numberId", unique = true, nullable = false)
    private Integer giftId;

    @Column(name = "gift_status_admin", nullable = false)
    @NotBlank
    @Enumerated(EnumType.STRING)
    private STATUS_GIFT statusGiftAdmin;

    @Column (name = "gift_status_gift_owner", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank
    private STATUS_GIFT statusGiftOwn;

    @Column(name = "gift_status_giving", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank
    private STATUS_GIFT statusGiftAnother;

    @Column (name = "name_gift", nullable = false)
    @NotBlank
    private String nameGift;

    @Column (name = "product_description")
    private String descriptionGift;

    @Column(name = "chat_id_gift_owner")
    private Integer chatIdGiftOwner;

    @Column (name = "chat_id_presenter")
    private Integer chatIdPresenter;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name="gift_weblinks",
            joinColumns = @JoinColumn(name="gift_id"),
            inverseJoinColumns = @JoinColumn(name="weblinks_id")
    )
    private Collection<WebLinks> linksList;

    @OneToMany
    @JoinTable(name="gift_comments",
            joinColumns = @JoinColumn(name="gift_id"),
            inverseJoinColumns = @JoinColumn(name="comments_id")
    )
    private Collection<Comments> comments;
}

