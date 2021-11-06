package telegrambot.modelEntities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import telegrambot.modelEntities.StatusGift.MappedEnum;
import telegrambot.modelEntities.StatusGift.StatusGift;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Component //("gift")
@Entity
@Table(name = "gift")
@MappedEnum(enumClass =  StatusGift.class)
@NoArgsConstructor
@AllArgsConstructor
public class Gift extends AbstractIdGifPhoneNumberEntity {

    @Column(name = "Gift_status_admin", nullable = false)
    @NotBlank
    @Enumerated(EnumType.STRING)
    private StatusGift statusGiftAdmin;

    @Column (name = "Gift_status_gift_owner", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank
    private StatusGift statusGiftOwn;

    @Column(name = "Gift_status_giving", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank
    private StatusGift statusGiftAnother;

    @Column (name = "Name_gift", nullable = false)
    @NotBlank
    private String nameGift;

    @Column (name = "Product_description")
    private String commentGift;

    /*
    @Column (name = "Phone_number_owner", nullable = false)
    @NotBlank
    private String numberPhoneGiftOwner; */

    @Column (name = "Phone_number_presenter")
    private String numberPhonePresenter;

    /*
     {
        statusGiftAdmin = StatusGift.ACTIVE;
        statusGiftOwn = StatusGift.ACTIVE;
        statusGiftAnother = StatusGift.ACTIVE;
    } */

    public StatusGift getStatusGiftAdmin() {
        return statusGiftAdmin;
    }

    public void setStatusGiftAdmin(StatusGift statusGiftAdmin) {
        this.statusGiftAdmin = statusGiftAdmin;
    }

    public StatusGift getStatusGiftOwn() {
        return statusGiftOwn;
    }

    public void setStatusGiftOwn(StatusGift statusGiftOwn) {
        this.statusGiftOwn = statusGiftOwn;
    }

    public StatusGift getStatusGiftAnother() {
        return statusGiftAnother;
    }

    public void setStatusGiftAnother(StatusGift statusGiftAnother) {
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

    public String getNumberPhonePresenter() {
        return numberPhonePresenter;
    }

    public void setNumberPhonePresenter(String numberPhonePresenter) {
        this.numberPhonePresenter = numberPhonePresenter;
    }
}
