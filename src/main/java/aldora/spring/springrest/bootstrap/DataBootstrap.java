package aldora.spring.springrest.bootstrap;

import aldora.spring.springrest.domain.Category;
import aldora.spring.springrest.domain.Customer;
import aldora.spring.springrest.domain.Payment;
import aldora.spring.springrest.repositories.CategoryRepository;
import aldora.spring.springrest.repositories.CustomerRepository;
import aldora.spring.springrest.repositories.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataBootstrap implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;

    public DataBootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
                         PaymentRepository paymentRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCustomers();
        loadCategories();
        loadPayments();
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");

        customerRepository.save(customer2);

        System.out.println("Customers loaded = " + customerRepository.count());
    }

    private void loadPayments() {
        Optional<Customer> customerOptional = customerRepository.findById(1L);
        Customer customer = customerOptional.get();

        Payment payment1 = new Payment();
        payment1.setDescription("payment1");
        paymentRepository.save(payment1);

        Payment payment2 = new Payment();
        payment2.setDescription("payment2");
        payment2.setCustomer(customer);
        paymentRepository.save(payment2);

        Payment payment3 = new Payment();
        payment3.setDescription("payment3");
        payment3.setCustomer(customer);
        paymentRepository.save(payment3);

        System.out.println("Payments loaded = " + paymentRepository.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data loaded = " + categoryRepository.count());
    }
}
