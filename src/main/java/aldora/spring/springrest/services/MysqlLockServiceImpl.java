package aldora.spring.springrest.services;

import aldora.spring.springrest.domain.Customer;
import aldora.spring.springrest.domain.Payment;
import aldora.spring.springrest.repositories.CustomerRepository;
import aldora.spring.springrest.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MysqlLockServiceImpl implements MysqlLockService {
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public void execute(Integer value) {
        CompletableFuture<Void> asyncLock = xLockOnNormalIndex(value);
//        CompletableFuture<Void> asyncInsertOnLock = insertOnGAPLock(value);
//        try {
//            asyncInsertOnLock.get();
//        } catch (InterruptedException | ExecutionException e) {
//            log.error("insertOnGAPLock error: ", e);
//        }
    }

    /**
     * exclusive lock
     */
    CompletableFuture<Void> xLockOnNormalIndex(Integer value) {
        return CompletableFuture.runAsync(() -> {
            SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
            Session session = sessionFactory.openSession();
            session.beginTransaction();

//            selectForUpdate(value, session);
//            select(value, session);
//            update(value, session);
//            insert(session);
            insert1(session);

            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            session.getTransaction().commit();
        });
    }

    private void selectForUpdate(Integer value, Session session) {
        Query query = session.createNativeQuery(String.format("SELECT * FROM payment WHERE state = %s FOR UPDATE", value));
//            Query query = session.createNativeQuery(String.format("SELECT * FROM payment WHERE state < %s FOR UPDATE", value));
        query.getResultList();
    }

    private void select(Integer value, Session session) {
        Query query = session.createNativeQuery(String.format("SELECT * FROM payment WHERE state = %s", value));
        query.getResultList();
    }

    private void update(Integer value, Session session) {
        Query query = session.createNativeQuery(String.format("UPDATE payment SET description = 'updated' WHERE id = %s", value));
        query.executeUpdate();
    }

    private void insert(Session session) {
        Optional<Customer> customerOptional = customerRepository.findById(1L);
        Customer customer = customerOptional.get();

        Payment payment = new Payment();
        payment.setState(104);
        payment.setDescription("payment from app");
        payment.setCustomer(customer);
        session.save(payment);
    }

    private void insert1(Session session) {
        Query query = session.createNativeQuery("insert into test.payment (description, state, customer_id) values ( 'payment3', 104, 1);");
        query.executeUpdate();
    }

    CompletableFuture<Void> insertOnGAPLock(Integer value) {
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Optional<Customer> customerOptional = customerRepository.findById(1L);
            Customer customer = customerOptional.get();

            Payment payment = new Payment();
            payment.setState(value + 2);
            payment.setDescription("inserted payment");
            payment.setCustomer(customer);
            session.save(payment);

            session.getTransaction().commit();
        }).exceptionally(e -> {
            log.error("insertOnGAPLock error: ", e);
            throw new RuntimeException(e.getMessage() == null ? "unknown error" : e.getMessage());
        });
    }
}
