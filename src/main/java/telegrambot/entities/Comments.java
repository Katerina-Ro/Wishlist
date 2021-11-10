package telegrambot.entities;

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
public class Comments {

    @Id
    @Column(name = "id_comments", unique = true, nullable = false)
    private Integer idComment;

    @Column (name = "Comments")
    @Setter
    private String comment;

    //@Column (name = "idGift")
    @Setter
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "gift_id")
    private Gift gift;

    //@Column (name = "chat_id_commentator")
    @ManyToOne
    @JoinColumn(name = "chat_id")
    @Setter
    private GiftOwner commentator;
}
