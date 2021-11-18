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
import java.util.List;

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

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id")//, referencedColumnName="chat_id")//, insertable=false, updatable=false)
    private GiftOwner giftOwner;


    @ManyToOne (fetch = FetchType.EAGER)
    //Если будет падать ошибка, связанная с этим полем, то во владельце нужно создать еще одно поле без колонки, аналогичное владельцу подарка только для дарящего. Связи те же
    @JoinColumn(name = "gift_presenter_id")//, referencedColumnName="chat_id", insertable=false, updatable=false)
    private GiftOwner giftPresenter;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(name="gift_weblinks",
            joinColumns = @JoinColumn(name="gift_id"),
            inverseJoinColumns = @JoinColumn(name="weblinks_id"))
    private List<WebLinks> linksList;

    @Override
    public String toString() {
        return "Наименование пожелания: " + nameGift + ", \n" +
                "Описание пожелания: " + descriptionGift +  ", \n" +
                "Статус подарка: " + statusGiftOwn.getStatusGift() + ", \n" +
                "Его кто-то выбрал, чтобы подарить? " + statusGiftAnother.getStatusGift() +  ", \n" +
                "Web - ссылки на подарок: " + linksList;
    }
}
