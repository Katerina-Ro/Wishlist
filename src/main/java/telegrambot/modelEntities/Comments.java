package telegrambot.modelEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(
        @NamedQuery(name = " ", query = "INSERT into comments (Comments, idGift, chat_id_commentator) " +
                "VALUES (:Comments, :idGift, :chat_id_commentator)")
)
public class Comments extends AbstractIdGifPhoneNumberEntity{

    @Id
    @Column(name = "idComments", unique = true, nullable = false)
    private int idComment;

    @Column (name = "Comments")
    @Setter
    private String comment;

    @Column (name = "idGift")
    @Setter
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="Number")
    private Gift gift;

    @Column (name = "chat_id_commentator")
    @Setter
    private String commentatorName;
}
