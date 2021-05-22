package com.udistrital.finanzas.controller;

import com.udistrital.finanzas.entity.ExpenseEntity;
import com.udistrital.finanzas.entity.RevenueEntity;
import com.udistrital.finanzas.model.MonthlySummary;
import com.udistrital.finanzas.model.Summary;
import com.udistrital.finanzas.repository.ExpenseRepository;
import com.udistrital.finanzas.repository.RevenueRepository;
import com.udistrital.finanzas.util.SummaryAux;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
public class SummaryController {


    private final RevenueRepository revenueRepository;

    private final ExpenseRepository expenseRepository;

    SummaryController(RevenueRepository revenueRepository, ExpenseRepository expenseRepository) {
        this.revenueRepository = revenueRepository;
        this.expenseRepository = expenseRepository;

    }

    @GetMapping("/summary/{id}")
    Summary one(@PathVariable Long id) {
        return summaryData(id);
    }

    private Summary summaryData(Long id) {
        List<RevenueEntity> listRevenue = revenueRepository.findIdByClientId(id);
        List<ExpenseEntity> listExpense = expenseRepository.findIdByClientId(id);
        long totalRevenue =0;
        long totalExpense =0;
        Summary summary = new Summary();
        List<MonthlySummary> listMonthly = new ArrayList<>();
        HashMap<String, MonthlySummary> summaryMonth = new HashMap<String, MonthlySummary>();

        for(RevenueEntity r: listRevenue){
            String month = convertMonth(r.getDate().getMonth());
            MonthlySummary sum = summaryMonth.get(month);
            if(sum != null){
                sum.setRevenue(sum.getRevenue() + r.getAmount());
            }else{
                sum = new MonthlySummary();
                sum.setRevenue(r.getAmount());
            }
            sum.setMonth(month);
            summaryMonth.put(month,sum);
            totalRevenue = totalRevenue + r.getAmount();
        }
        for(ExpenseEntity e: listExpense){
            String month = convertMonth(e.getDate().getMonth());
            MonthlySummary sum = summaryMonth.get(month);
            if(sum != null){
                sum.setExpense(sum.getExpense() + e.getAmount());
            }else{
                sum = new MonthlySummary();
                sum.setExpense(e.getAmount());
            }
            sum.setMonth(month);
            summaryMonth.put(month,sum);
            totalExpense = totalExpense + e.getAmount();
        }


        Iterator it = summaryMonth.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            listMonthly.add((MonthlySummary) pair.getValue());
            it.remove();

        }

        summary.setTotalExpense(totalExpense);
        summary.setTotalRevenue(totalRevenue);
        summary.setMonthlySummary(listMonthly);
        summary.setListExpense(listExpense);

        return summary;
    }

    public String convertMonth(int month){
        switch (month)
        {
            case 0:
                return "Enero";
            case 1:
                return "Febrero";
            case 2:
                return "Marzo";
            case 3:
                return "Abril";
            case 4:
                return "Mayo";
            case 5:
                return "Junio";
            case 6:
                return "Julio";
            case 7:
                return "Agosto";
            case 8:
                return "Septiembre";
            case 9:
                return "Octubre";
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";
            default:
                return "";
        }
    }
}
