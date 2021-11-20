package telegrambot.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "links")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WebLinks {

    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    @Column(name = "id_links", unique = true, nullable = false)
    private Integer idLinks;

    @Column (name = "weblink")
    @Setter
    private String webLink;

    @Setter
    @OneToOne
    @JoinColumn(name = "number_id")
    private Gift gift;

    @Override
    public String toString() {
        return webLink;
    }
}
