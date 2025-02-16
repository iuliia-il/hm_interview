package com.example.hm_interview.specification;

import com.example.hm_interview.model.Transaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionSpecification {

    public static Specification<Transaction> filterTransactions(String type, String actor, LocalDateTime from, LocalDateTime to, String key, String value) {
        return new Specification<Transaction>() {
            @Override
            public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (type != null) {
                    predicates.add(criteriaBuilder.like(root.get("type"), type.replace("*", "%")));
                }

                if (actor != null) {
                    predicates.add(criteriaBuilder.like(root.get("actor"), actor.replace("*", "%")));
                }

                if (from != null && to == null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("timestamp"), from));
                } else if (from == null && to != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("timestamp"), to));
                } else if (from != null && to != null) {
                    predicates.add(criteriaBuilder.between(root.get("timestamp"), from, to));
                }

                if (key != null && value != null) {
                    predicates.add(criteriaBuilder.equal(
                            criteriaBuilder.function("JSON_EXTRACT", String.class, root.get("transactionData"),
                                    criteriaBuilder.literal("$.\"" + key + "\"")),
                            value));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }

}
