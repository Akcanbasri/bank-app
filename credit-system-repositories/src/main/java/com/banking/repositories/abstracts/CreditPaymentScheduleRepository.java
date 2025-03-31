package com.banking.repositories.abstracts;

import com.banking.entities.CreditPaymentSchedule;
import com.banking.entities.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CreditPaymentScheduleRepository extends BaseRepository<CreditPaymentSchedule, Long> {

    List<CreditPaymentSchedule> findByCreditApplicationId(Long creditApplicationId);

    List<CreditPaymentSchedule> findByCreditApplicationIdOrderByPaymentDate(Long creditApplicationId);

    List<CreditPaymentSchedule> findByCreditApplicationIdAndStatus(Long creditApplicationId, PaymentStatus status);

    List<CreditPaymentSchedule> findByCreditApplicationIdAndPaymentDateBefore(Long creditApplicationId, LocalDate date);

    List<CreditPaymentSchedule> findByCreditApplicationIdAndPaymentDateAfter(Long creditApplicationId, LocalDate date);

    List<CreditPaymentSchedule> findByCreditApplicationIdAndPaymentDateBetween(Long creditApplicationId,
            LocalDate startDate, LocalDate endDate);

    List<CreditPaymentSchedule> findByPaymentDateAndStatus(LocalDate date, PaymentStatus status);

    Page<CreditPaymentSchedule> findByCreditApplicationId(Long creditApplicationId, Pageable pageable);
}