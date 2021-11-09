package telegrambot.modelEntities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import telegrambot.modelEntities.StatusGift.MappedEnum;
import telegrambot.modelEntities.StatusGift.STATUS_GIFT;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Component //("gift")
@Entity
@Table(name = "gift")
@MappedEnum(enumClass =  STATUS_GIFT.class)
@NoArgsConstructor
@AllArgsConstructor
public class Gift extends AbstractIdGifPhoneNumberEntity {

    @Column(name = "Gift_status_admin", nullable = false)
    @NotBlank
    @Enumerated(EnumType.STRING)
    private STATUS_GIFT statusGiftAdmin;

    @Column (name = "Gift_status_gift_owner", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank
    private STATUS_GIFT statusGiftOwn;

    @Column(name = "Gift_status_giving", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank
    private STATUS_GIFT statusGiftAnother;

    @Column (name = "Name_gift", nullable = false)
    @NotBlank
    private String nameGift;

    @Column (name = "Product_description")
    private String commentGift;

    /*
    @Column (name = "Phone_number_owner", nullable = false)
    @NotBlank
    private String numberPhoneGiftOwner; */

    @Column (name = "chat_id_presenter")
    private String chatIdPresenter;

    @ManyToMany
    @JoinTable(name="gift_links",
            joinColumns = @JoinColumn(name="gift_id", referencedColumnName="number"),
            inverseJoinColumns = @JoinColumn(name="links_id", referencedColumnName="idLinks")
    )
    private Collection<WebLinks> linksList;

    /*
     {
        statusGiftAdmin = StatusGift.ACTIVE;
        statusGiftOwn = StatusGift.ACTIVE;
        statusGiftAnother = StatusGift.ACTIVE;
    } */

    public STATUS_GIFT getStatusGiftAdmin() {
        return statusGiftAdmin;
    }

    public void setStatusGiftAdmin(STATUS_GIFT statusGiftAdmin) {
        this.statusGiftAdmin = statusGiftAdmin;
    }

    public STATUS_GIFT getStatusGiftOwn() {
        return statusGiftOwn;
    }

    public void setStatusGiftOwn(STATUS_GIFT statusGiftOwn) {
        this.statusGiftOwn = statusGiftOwn;
    }

    public STATUS_GIFT getStatusGiftAnother(STATUS_GIFT notActive) {
        return statusGiftAnother;
    }

    public void setStatusGiftAnother(STATUS_GIFT statusGiftAnother) {
        this.statusGiftAnother = statusGiftAnother;
    }

    public String getNameGift() {
        return nameGift;
    }

    public void setNameGift(String nameGift) {
        this.nameGift = nameGift;
    }

    public String getCommentGift() {
        return commentGift;
    }

    public void setCommentGift(String commentGift) {
        this.commentGift = commentGift;
    }

    /*
    public String getNumberPhoneGiftOwner() {
        return numberPhoneGiftOwner;
    }

    public void setNumberPhoneGiftOwner(String numberPhoneGiftOwner) {
        this.numberPhoneGiftOwner = numberPhoneGiftOwner;
    } */

    public String getChatIdPresenter() {
        return chatIdPresenter;
    }

    public void setChatIdPresenter(String chatIdPresenter) {
        this.chatIdPresenter = chatIdPresenter;
    }
}

