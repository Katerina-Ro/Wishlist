package telegrambot.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telegrambot.entities.StatusGift.MappedEnum;
import telegrambot.entities.StatusGift.STATUS_GIFT;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    private GiftOwner giftOwner;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "gift_presenter_id")
    private GiftOwner giftPresenter;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_links")
    private WebLinks link;

    @Override
    public String toString() {
        return "Наименование пожелания: " + nameGift + ", \n" +
                "Описание пожелания: " + descriptionGift +  ", \n" +
                "Пожелание видно всем? " + statusGiftOwn.getStatusGift() + ", \n" +
                "Его кто-то выбрал, чтобы подарить? " + statusGiftAnother.getStatusGift() +  ", \n" +
                "Web - ссылка на подарок: " + link;
    }
}
