package telegrambot.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telegrambot.entities.StatusGift.MappedEnum;
import telegrambot.entities.StatusGift.STATUS_GIFT;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
@MappedEnum(enumClass =  STATUS_GIFT.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Gift {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    @Column(name ="number_id", unique = true)
    private Integer giftId;

    @Column(name = "gift_status_admin", nullable = false)
    @NotBlank
    @Enumerated(EnumType.STRING)
    private STATUS_GIFT statusGiftAdmin = STATUS_GIFT.ACTIVE;

    @Column (name = "gift_status_gift_owner", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank
    private STATUS_GIFT statusGiftOwn = STATUS_GIFT.ACTIVE;

    @Column(name = "gift_status_giving", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank
    private STATUS_GIFT statusGiftAnother = STATUS_GIFT.NOT_ACTIVE;

    @Column (name = "name_gift", nullable = false)
    @NotBlank
    private String nameGift;

    @Column (name = "product_description")
    private String descriptionGift;

    //@Column(name = "chat_id_gift_owner")
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "gift_owner_id")//, referencedColumnName="chat_id", insertable=false, updatable=false)
    private GiftOwner giftOwner;

    //@Column (name = "chat_id_presenter")
    @ManyToOne (fetch = FetchType.EAGER)
    //Если будет падать ошибка, связанная с этим полем, то во владельце нужно создать еще одно поле без колонки, аналогичное владельцу подарка только для дарящего. Связи те же
    @JoinColumn(name = "gift_presenter_id")//, referencedColumnName="chat_id", insertable=false, updatable=false)
    private GiftOwner giftPresenter;

    @ManyToMany //(fetch = FetchType.EAGER)
    @JoinTable(name="gift_weblinks",
            joinColumns = @JoinColumn(name="gift_id"),
            inverseJoinColumns = @JoinColumn(name="weblinks_id"))
    private Collection<WebLinks> linksList;

    @OneToMany
    @JoinTable(name="gift_comments",
            joinColumns = @JoinColumn(name="gift_id"),
            inverseJoinColumns = @JoinColumn(name="comments_id")
    )
    private Collection<Comments> comments;

    @Override
    public String toString() {
        return "Пожелание:" +
                "Номер подарка: " + giftId +
                ", statusGiftAdmin=" + statusGiftAdmin +
                ", Статус подарка: " + statusGiftOwn +
                ", Его кто-то выбрал, чтобы подарить? " + statusGiftAnother +
                ", Наименование пожелания: " + nameGift + '\'' +
                ", Описание пожелания " + descriptionGift + '\'' +
                ", Чье пожелание: " + giftOwner +
                ", giftPresenter " + giftPresenter +
                ", Web- ссылки на подарок: " + linksList +
                ", Комментарии: " + comments;
    }
}
