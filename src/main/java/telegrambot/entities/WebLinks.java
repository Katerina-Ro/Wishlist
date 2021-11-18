package telegrambot.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity // обязательно
@Table(name = "links") //необязательная, по умолчанию - название класса
@Getter
@NoArgsConstructor
@AllArgsConstructor
/*
@NamedQueries({
        @NamedQuery(name = "findAllLinks", query = "SELECT w FROM WebLinks w"),
        @NamedQuery(name = "findLinksWithName", query = "SELECT w FROM WebLinks w WHERE w.web_link = :web_link"),
        @NamedQuery(name = "findLinksWithId", query = "SELECT w FROM WebLinks w WHERE w.idLinks = :idLinks")
}) */
public class WebLinks {

    @Id // обязательно
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    @Column(name = "id_Links", unique = true, nullable = false)
    private Integer idLinks;

    @Column (name = "weblink") // необязательно
    @Setter
    private String webLink;

    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="gift_weblinks",
            joinColumns = @JoinColumn(name="weblinks_id"),
            inverseJoinColumns = @JoinColumn(name="gift_id"))
    private List<Gift> gift;

    @Override
    public String toString() {
        return webLink;
    }
}
