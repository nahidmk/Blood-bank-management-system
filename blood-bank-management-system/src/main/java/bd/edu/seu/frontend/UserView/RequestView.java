package bd.edu.seu.frontend.UserView;

import bd.edu.seu.Backend.Model.Request;
import bd.edu.seu.Backend.Model.User;
import bd.edu.seu.Backend.Service.RequestService;
import bd.edu.seu.Backend.Service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
//import javafx.scene.control.ButtonBar;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "request-view",layout = UserMainView.class)
@PageTitle("JBMK | Request(user)")
public class RequestView extends VerticalLayout {

    private UserService userService;
    private RequestService requestService;

    public RequestView(HttpSession httpSession,UserService userService,RequestService requestService) {
        super();
        this.userService = userService;
        this.requestService = requestService;
        createReqPage(httpSession);
    }

    private void createReqPage(HttpSession httpSession) {
        Select<String> bloodGroup = new Select<>("A(+ve)","A(-ve)","B(+ve)","B(-ve)","O(+ve)","O(-ve)","AB(+ve)","AB(-ve)");
        bloodGroup.setLabel("Blood Group");
        TextField quantity = new TextField("Quantity","quantity as mili");
        DatePicker requiredDate = new DatePicker("Required Date");
        LocalDate  SendingDate = LocalDate.now();
        Select<String> status = new Select<>("Request");
        status.setLabel("Status");
        String userNumber = (String) httpSession.getAttribute("username");
        if(userNumber==null) userNumber="unknown";



        FormLayout formLayout = new FormLayout();
        formLayout.add(bloodGroup,quantity,requiredDate,status);
        Button save = new Button("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.setSizeFull();

        //todo grid update
        Grid<Request> requestGrid = new Grid<>(Request.class);
        requestGrid.getColumns().forEach(col->col.setAutoWidth(true));
        User user = userService.getUser(userNumber);
        requestGrid.setItems(requestService.getUserRequest(user.getId()).stream().filter(u->u.getId()>0).collect(Collectors.toList()));


        //todo dialog setup for grid click listener
        Button payment = new Button("Send");
        payment.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        TextField transactionId = new TextField("Transaction Id","transaction id");
        Dialog dialog = new Dialog();
        dialog.setCloseOnOutsideClick(false);
        dialog.setCloseOnEsc(false);
        Button close = new Button(VaadinIcon.CLOSE_CIRCLE.create());

        dialog.add(transactionId,payment,close);


        //todo grid item clicked
        String finalUserNumber1 = userNumber;
        requestGrid.addItemClickListener(requestItemClickEvent -> {
            Request item = requestItemClickEvent.getItem();
            if(item.getStatus().equals("Accepted")){
                dialog.open();
                bloodGroup.setValue(item.getBloodGroup());
                quantity.setValue(item.getQuantity()+"");
                requiredDate.setValue(item.getRequreDate());
                status.setValue(item.getStatus());
                transactionId.setPlaceholder("Enter transaction id");
                payment.addClickListener(i->{
                    if(!transactionId.isEmpty()){
                        item.setTransactionID(transactionId.getValue());
                        requestService.update(item);
                        UI.getCurrent().getPage().reload();
                        dialog.close();
                    }else{
                        Notification.show("Enter Transaction id");
                    }
                });
                close.addClickListener(closeEvent->{
                    UI.getCurrent().getPage().reload();
                    dialog.close();
                });
            }else{
                bloodGroup.setValue("");
                quantity.setValue("");
                status.setValue("");
                transactionId.setValue("");
                requiredDate.clear();
            }
        });

        //todo req send function
        String finalUserNumber = userNumber;
        String finalUserNumber2 = userNumber;
        save.addClickListener(clickEvent->{
            if(!bloodGroup.isEmpty() && !quantity.isEmpty() && !requiredDate.isEmpty() && !status.isEmpty()){
                sendReq(bloodGroup.getValue(),quantity.getValue(),requiredDate.getValue(),SendingDate,status.getValue(),transactionId.getValue(), finalUserNumber);
            }
            UI.getCurrent().getPage().reload();
        });


        //todo search button with dialog
        Button search = new Button(VaadinIcon.SEARCH.create());
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Dialog serachDialog = new Dialog();
        DatePicker date = new DatePicker();
        Button asRequiredDate = new Button("asRequiredDate");
        Button asSendingdDate = new Button("asSendingDate");
        Button close1 = new Button(VaadinIcon.CLOSE_CIRCLE.create());
        serachDialog.add(date,asRequiredDate,asSendingdDate,close1);


        search.addClickListener(i->{
            serachDialog.open();
            asRequiredDate.addClickListener(clickEvent -> {
                requestGrid.setItems(requestService.FindByRequredDate(date.getValue(),user.getId()));
                serachDialog.close();
            });

            asSendingdDate.addClickListener(clickEvent -> {
                requestGrid.setItems(requestService.FindBySendingDate(date.getValue(),user.getId()));
                serachDialog.close();
            });


            close1.addClickListener(clickEvent -> {
                UI.getCurrent().getPage().reload();
                serachDialog.close();
            });
        });

        add(search,formLayout,save,requestGrid);
    }


    private void sendReq(String value, String value1, LocalDate value2, LocalDate sendingDate, String value3, String value4, String userNumber) {

        int quantity=0;
        try {
            quantity = Integer.parseInt(value1);
        }catch (Exception e){
            Notification.show("Enter valid number as quantity");
        }

        Request request = new Request();
        request.setId(requestService.getMaxId()+1);
        request.setBloodGroup(value);
        request.setQuantity(quantity);
        request.setRequreDate(value2);
        request.setSendingDate(sendingDate);
        request.setStatus(value3);
        request.setTransactionID(value4);
        request.setUser(userService.getUser(userNumber));

        requestService.SaveRequest(request);

    }
}
