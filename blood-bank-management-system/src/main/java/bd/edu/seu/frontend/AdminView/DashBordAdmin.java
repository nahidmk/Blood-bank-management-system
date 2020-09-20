package bd.edu.seu.frontend.AdminView;

import bd.edu.seu.Backend.Model.Request;
import bd.edu.seu.Backend.Service.RequestService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Dial;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "dash-bord-admin",layout = AdminMainView.class)
@PageTitle("JBMK | RequestGrid(Admin)")
public class DashBordAdmin extends VerticalLayout {
    //todo its your job

    private RequestService requestService;
    public DashBordAdmin(RequestService requestService) {
        super();
        this.requestService = requestService;


        Grid<Request> requestGrid = new Grid<>(Request.class);

        List<Request> requestList = requestService.findAll().stream().filter(i -> i.getId() > 0).collect(Collectors.toList());
        requestGrid.getColumns().forEach(col->col.setAutoWidth(true));
        requestGrid.setItems(requestList);

        Button update = new Button("Update");
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button close = new Button(VaadinIcon.CLOSE_CIRCLE.create());
        Select<String> select = new Select<>("Request","Accepted","Delivered","Denied");
        Dialog dialog = new Dialog();
        dialog.setCloseOnOutsideClick(false);
        dialog.setCloseOnEsc(false);
        dialog.add(select,update,close);

        //todo grid click listener
        requestGrid.addItemClickListener(requestItemClickEvent -> {
            Request request;
            request = requestItemClickEvent.getItem();
            if(request.getStatus().equals("Delivered") || request.getStatus().equals("Request") || request.getStatus().equals("Accepted") || request.getStatus().equals("Denied")){
                dialog.open();
                select.setValue(request.getStatus());
                update.addClickListener(clickEvent -> {
                    request.setStatus(select.getValue());
                    requestService.update(request);
                    requestGrid.deselect(request);
                    UI.getCurrent().getPage().reload();
                    dialog.close();
                });
                close.addClickListener(clickEvent -> {
                    requestGrid.deselectAll();
                    UI.getCurrent().getPage().reload();
                    dialog.close();
                });
            }else{
                requestGrid.deselectAll();
                select.setVisible(false);
                update.setVisible(false);
                dialog.close();
            }
            requestGrid.deselectAll();
        });



        //todo search button procedure
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
                requestGrid.setItems(requestService.FindByRequredDateForAll(date.getValue()));
                serachDialog.close();
            });

            asSendingdDate.addClickListener(clickEvent -> {
                requestGrid.setItems(requestService.FindBySendingDateForAll(date.getValue()));
                serachDialog.close();
            });


            close1.addClickListener(clickEvent -> {
                UI.getCurrent().getPage().reload();
                serachDialog.close();
            });
        });

        add(search,requestGrid);
    }

}
