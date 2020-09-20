package bd.edu.seu.frontend.AdminView;

import bd.edu.seu.Backend.Model.Account;
import bd.edu.seu.Backend.Model.Notice;
import bd.edu.seu.Backend.Service.AccountService;
import bd.edu.seu.Backend.Service.NoticeService;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Route(value = "account-grid",layout = AdminMainView.class)
@PageTitle("JBMK | UserGrid(Admin)")
public class AccountGrid extends VerticalLayout {

    DatePicker datePicker = new DatePicker();
    Grid<Account> accountGrid = new Grid<>(Account.class);
     Button addAccnout = new Button("Add Account");
    private AccountService accountService;
    private final AccountFrom accountFrom;
    public AccountGrid(AccountService accountService)
    {
        this.accountService = accountService;
        accountFrom = new AccountFrom(this,accountService);
        addClassName("list");
        setSizeFull();
        configureGrid();
//        accountFrom.addListener(AccountFrom.SaveEvent.class,this::saveAccount);
//        accountFrom.addListener(AccountFrom.DeleteEvent.class, this::deleteAccount);
        accountFrom.addListener(AccountFrom.closeEvent.class, event-> closeEditor());

        Div div = new Div(accountGrid, accountFrom);
        div.setSizeFull();
        div.addClassName("Content");
        add(ToolBar(),div);
        updateGrid();
    }

//    private  void deleteAccount(AccountFrom.DeleteEvent event) {
//        accountService.AccountDelete(event.getAccount());
//        updateGrid();
//        closeEditor();
//        Dialog dialog = new Dialog();
//        dialog.add(new Label("Delete Successful"));
//        dialog.setWidth("1000px");
//        dialog.setHeight("1000px");
//        dialog.open();
//    }

//    private  void saveAccount(AccountFrom.SaveEvent event)  {
//        System.out.println("value = "+ event.getAccount());
//        accountService.SaveAccount(event.getAccount());
//        updateGrid();
//        closeEditor();
//        Dialog dialog = new Dialog();
//        dialog.add(new Label("Save Successful"));
//        dialog.setWidth("1000px");
//        dialog.setHeight("1000px");
//        dialog.open();
//    }
    private void closeEditor() {
        accountFrom.setAccount(null);
        accountFrom.setVisible(false);
        removeClassName("editing");
    }
    private void configureGrid() {
        accountGrid.addClassName("Grid");
        accountGrid.setSizeFull();
        accountGrid.setColumns("name","phone","transactionDate","amount");
        accountGrid.getColumns().forEach(col-> col.setAutoWidth(true));
    }

    public void updateGrid() {

        LocalDate date = datePicker.getValue();
        if(date==null)
        {
            List<Account> all = accountService.findAll();
            Collections.reverse(all);
            accountGrid.setItems(all);
        }else{
            accountGrid.setItems(accountService.findByTransactionDate(date));
        }

    }
    private HorizontalLayout ToolBar() {

        datePicker.setPlaceholder("Filter by Date....");
        datePicker.setClearButtonVisible(true);
        datePicker.addValueChangeListener(e-> updateGrid());
        addAccnout.addClickListener(click-> CreateAccount());
        HorizontalLayout layout = new HorizontalLayout(datePicker,addAccnout);
        layout.addClassName("Toolbar");
        return layout;
    }


    private void CreateAccount() {
        accountGrid.asSingleSelect().clear();
        EditAccount(new Account());
    }
    private void EditAccount(Account value) {
        if(value==null)
        {
            closeEditor();
        }else
        {
            accountFrom.setAccount(value);
            accountFrom.setVisible(true);
            addClassName("editing");
        }

    }

}
