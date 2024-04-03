package com.mobilebankingapi.init;

import com.mobilebankingapi.domain.AccountType;
import com.mobilebankingapi.domain.CardType;
import com.mobilebankingapi.domain.Role;
import com.mobilebankingapi.feature.accounttype.AccountTypeRepository;
import com.mobilebankingapi.feature.cardtype.CardTypeRepository;
import com.mobilebankingapi.feature.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DataInit {

    private final RoleRepository roleRepository;
    private final CardTypeRepository cardTypeRepository;
    private final AccountTypeRepository accountTypeRepository;
    @PostConstruct
    void init() {
        if (roleRepository.count() < 1) {
            Role user = new Role();
            user.setName("USER");

            Role customer = new Role();
            customer.setName("CUSTOMER");

            Role staff = new Role();
            staff.setName("STAFF");

            Role admin = new Role();
            admin.setName("ADMIN");

            roleRepository.saveAll(
                    List.of(user, customer, staff, admin)
            );
        }
    }

    @PostConstruct
    void initAccountType() {
        if (accountTypeRepository.count() < 1) {
            AccountType payroll = new AccountType();
            payroll.setName("PAYROLL");
            payroll.setName("PAYROLL");
            payroll.setAlias("PAYROLL");
            payroll.setDeleted(false);
            payroll.setDescription("Payroll Of Account Type");

            AccountType saving = new AccountType();
            saving.setName("SAVING");
            saving.setAlias("SAVING");
            saving.setDeleted(false);
            saving.setDescription("Saving Of Account Type");

            accountTypeRepository.saveAll(
                    List.of(payroll,saving)
            );
        }
    }

    @PostConstruct
    void initCardType() {
        if(cardTypeRepository.count() < 1){
            CardType visa = new CardType();
            visa.setName("Visa");
            visa.setIsDeleted(false);

            CardType masterCard = new CardType();
            masterCard.setName("MasterCard");
            masterCard.setIsDeleted(false);

            cardTypeRepository.saveAll(List.of(visa,masterCard));
        }
    }
}
