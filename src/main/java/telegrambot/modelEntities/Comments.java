package telegrambot.modelEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comments extends AbstractIdGifPhoneNumberEntity{

    @Id
    @Column(name = "idComments", unique = true, nullable = false)
    private int idComment;

    @Column (name = "Comments")
    @Setter
    private String comment;
}
