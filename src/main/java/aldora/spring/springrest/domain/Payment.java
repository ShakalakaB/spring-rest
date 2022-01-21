package aldora.spring.springrest.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(indexes = @Index(name = "state_index", columnList = "state"))
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * 付款状态：0-未付款 1-已付款 2-已退款 3-部分退款
     */
    @Column(name = "state", nullable = false)
    private Integer state;
}
