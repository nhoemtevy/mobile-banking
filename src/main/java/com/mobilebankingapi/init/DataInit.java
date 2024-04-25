package com.mobilebankingapi.init;

import com.mobilebankingapi.domain.AccountType;
import com.mobilebankingapi.domain.Authority;
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
    void initRole() {
        if (roleRepository.count() < 1) {
            Authority userRead = new Authority();
            userRead.setName("user:read");
            Authority userWrite = new Authority();
            userWrite.setName("user:write");
            Authority transactionRead = new Authority();
            transactionRead.setName("transaction:read");
            Authority transactionWrite = new Authority();
            transactionWrite.setName("transaction:write");
            Authority accountRead = new Authority();
            accountRead.setName("account:read");
            Authority accountWrite = new Authority();
            accountWrite.setName("account:write");
            Authority accountTypeRead = new Authority();
            accountTypeRead.setName("accountType:read");
            Authority accountTypeWrite = new Authority();
            accountTypeWrite.setName("accountType:write");

            Role user = new Role();
            user.setName("USER");
            user.setAuthorities(List.of(
                    userRead, transactionRead,
                    accountRead, accountTypeRead
            ));

            Role customer = new Role();
            customer.setName("CUSTOMER");
            customer.setAuthorities(List.of(
                    userWrite, transactionWrite,
                    accountWrite
            ));

            Role staff = new Role();
            staff.setName("STAFF");
            staff.setAuthorities(List.of(
                    accountTypeWrite
            ));

            Role admin = new Role();
            admin.setName("ADMIN");
            admin.setAuthorities(List.of(
                    userWrite, accountWrite,
                    accountTypeWrite
            ));

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
            payroll.setAlias("payroll");
            payroll.setDeleted(false);
            payroll.setDescription("Payroll Of Account Type");

            AccountType saving = new AccountType();
            saving.setName("SAVING");
            saving.setAlias("saving");
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
