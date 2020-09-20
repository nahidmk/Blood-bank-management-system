package bd.edu.seu.frontend.AdminView;

import bd.edu.seu.Backend.Model.Account;
import bd.edu.seu.Backend.Service.AccountService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountFrom extends FormLayout {

    private TextField name = new TextField("name");
    private TextField phone = new TextField("phone");
    private DatePicker transactionDate = new DatePicker("transactionDate");
    private TextField amount = new TextField("amount");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button cancel = new Button("cancel");
    private AccountGrid accountGrid;
    private AccountService accountService;
    Binder<Account> binder = new Binder<>(Account.class);
    public AccountFrom(AccountGrid accountGrid, AccountService accountService) {
        this.accountService = accountService;
        this.accountGrid = accountGrid;
        addClassName("form");
//        binder.bindInstanceFields(this);
        add(name, phone, transactionDate, amount, buttonConfigure());
    }

    public void setAccount(Account account)
    {
        binder.setBean(account);
    }
    private Component buttonConfigure() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);


        save.addClickListener(event-> ValidAndSave());
//        delete.addClickListener(event-> fireEvent(new DeleteEvent(this,binder.getBean())));
        cancel.addClickListener(event-> fireEvent(new closeEvent(this)));


//        binder.addStatusChangeListener(event->save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save,cancel);
    }

    private void ValidAndSave() {
        if(name.getValue()!="" && phone.getValue()!=""&& transactionDate.getValue()!=null && amount.getValue()!="")
        {
//            fireEvent(new SaveEvent(this,binder.getBean()));
            Account account = new Account(name.getValue(),phone.getValue(),transactionDate.getValue(),amount.getValue());
            accountService.SaveAccount(account);
            accountGrid.updateGrid();
            name.setValue("");
            phone.setValue("");
            transactionDate.setValue(null);
            amount.setValue("");
        }else
        {
            Dialog dialog = new Dialog();
            dialog.add(new Label("Complete the Form"));

            dialog.setWidth("300px");
            dialog.setHeight("300px");
            dialog.open();
        }
    }


    public static abstract class AccountFormEvent extends ComponentEvent<AccountFrom>
    {
        private Account account;
        public AccountFormEvent(AccountFrom source, Account account) {
            super(source, false);
            this.account = account;
        }

        public Account getAccount()
        {
            return account;
        }
    }
//
//    public static class SaveEvent extends AccountFormEvent
//    {
//
//        public SaveEvent(AccountFrom source, Account account) {
//            super(source, account);
//        }
//    }
//    public static class DeleteEvent extends AccountFormEvent
//    {
//
//        public DeleteEvent(AccountFrom source, Account account) {
//            super(source, account);
//        }
//    }
//
    public static class closeEvent extends AccountFormEvent {
        public closeEvent(AccountFrom source) {
            super(source,null);
        }
    }
//
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener)
    {
        return getEventBus().addListener(eventType, listener);
    }

}
