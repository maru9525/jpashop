package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") //default 로 Book이 들어가긴함.
@Getter @Setter
public class Book extends Item{

    private String author;
    private String isbn;

}
