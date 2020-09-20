package bd.edu.seu.frontend.AdminView;

import bd.edu.seu.Backend.Model.Notice;
import bd.edu.seu.Backend.Model.User;
import bd.edu.seu.Backend.Service.NoticeService;
import bd.edu.seu.Backend.Service.UserService;
import bd.edu.seu.frontend.DonorView.DonorMainView;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.List;

@Route(value = "user-grid",layout = AdminMainView.class)
@PageTitle("JBMK | UserGrid(Admin)")
public class UserGrid extends VerticalLayout {

    TextField textField = new TextField();
    Grid<User> userGrid = new Grid<>(User.class);
    private UserService userService;
    public UserGrid(UserService userService)
    {
        this.userService = userService;
        addClassName("list");
        setSizeFull();
        configureGrid();
        Div div = new Div(userGrid);
        div.setSizeFull();
        div.addClassName("Content");
        add(ToolBar(),div);
        updateGrid();
    }

    private void configureGrid() {
        userGrid.addClassName("Grid");
        userGrid.setSizeFull();
        userGrid.setColumns("name","address","phone","email");
        userGrid.getColumns().forEach(col-> col.setAutoWidth(true));
    }

    public void updateGrid() {

        String date = textField.getValue();
        if(date.isEmpty())
        {
            userGrid.setItems(userService.FindAll());
        }else{
            userGrid.setItems(userService.FindByName(date));
        }

    }
    private HorizontalLayout ToolBar() {

        textField.setPlaceholder("Filter by Name...");
        textField.setClearButtonVisible(true);
        textField.setValueChangeMode(ValueChangeMode.LAZY);
        textField.addValueChangeListener(e-> updateGrid());
        H1 h1 = new H1("Admin penal");
        HorizontalLayout layout = new HorizontalLayout(textField,h1);
        layout.addClassName("Toolbar");
        return layout;
    }
}
