package aptech.t2008m.testspringboot.entity;

import aptech.t2008m.testspringboot.entity.enums.StreetStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "streets")
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameStreet;

    @ManyToOne
    @JoinColumn(name = "districtId", insertable = false, updatable = false)
    @JsonIgnore
    private District districtId ;

    @CreationTimestamp
    private Date createdAt;
    private String description;

    @Basic
    private int status;
    @Transient
    private StreetStatus productStatus;

    @PostLoad
    void fillTransient() {
        this.productStatus = StreetStatus.of(status);
    }

    @PrePersist
    void fillPersistent() {
        this.status = this.productStatus.getValue();
    }


}
